
package app;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;


import gui.OpenGUI;
import gui.table.SequenceTable;

public class Manager {
	
	String[] names;
	String[] sequences;
	static Vector<AminoAcidSequence> storedAlignment;
	int seqCounter;
	AAShader shadeDriver;
	
	/** 
	 * takes file path as parameter and reads in file as String 
	 */
	public void readIn(File input){
		String dataString = "";
		String dataLine = "";

		try {
			FileInputStream fileIn = new FileInputStream(input);
			DataInputStream dataIn = new DataInputStream(fileIn);
			BufferedReader dataReader = new BufferedReader(new InputStreamReader(dataIn));
			
			/** read data into dataString and print to System.out */
			while((dataLine = dataReader.readLine()) != null){
				dataString += dataLine + "\n";
			}
			dataIn.close(); /** close input stream */

			/** validate sequence correctness and auto-detect format */
			validate(dataString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * validate sequence, detect format, checks for errors 
	 */
	private String validate(String alignment) throws IOException{
		/** (TODO)
		 * call to check correctness of format, includes:
		 * 	-check protein names different
		 * 	-check for nonstandard characters
		 * 	-60 characters per line
		 */
		
		/** calls method to detect format - finished by Kushal */
		String format = autoDetectFormat(alignment);
		if (format.equals("ALN")){
			extractSequences(alignment);
			store(alignment);
			return "***ALN format auto-detected***";
		}
		else return "Error: unsupported format. Please load sequence file formatted in either ALN, " +
			"MSF, FASTA, or Phylip.";
		
	}
	
	/**
	 * called by validate to detect format of read in file
	 */
	private String autoDetectFormat(String alignment){
		/** auto detect formats: ALN(CLUSTAL), MSF, FASTA, Phylip */
		Scanner scan = new Scanner(alignment);
		
		if (scan.next().equalsIgnoreCase("CLUSTAL"))
			return "ALN";
		else return "none";
		
	}
	
	/**
	 * stores array of Strings into Vector of AminoAcidSequences named storedAlignment
	 */
	private void store(String alignment){
		storedAlignment = new Vector<AminoAcidSequence>(500);
		
		/** first extract sequences */
		extractSequences(alignment);
		
		/** add AminoAcidSequence to vector */
		for (int y = 0; y < seqCounter; y++){
			storedAlignment.add(new AminoAcidSequence(sequences[y], names[y]));
		}
		
		/** call to create table */
		showTable();	
	}
	
	/**
	 * extract names and sequences from alignment
	 */
	private void extractSequences(String alignment){
		
		/***************************************
		 * WORKING
		 ***************************************/
		
		/** determine number of sequences in alignment */
		String seqName;
		Scanner lineCount = new Scanner(alignment);
		lineCount.nextLine();
		Scanner lineValidScan = new Scanner(lineCount.nextLine());
		
		try{
			seqName = lineValidScan.next();
		} catch(NoSuchElementException e1){
			lineValidScan = new Scanner(lineCount.nextLine());
			try{
				seqName = lineValidScan.next();
			} catch(NoSuchElementException e2){
				lineValidScan = new Scanner(lineCount.nextLine());
				seqName = lineValidScan.next();
			}
		}
		
		seqCounter = 1;
		boolean realLine = true;
		
		while(realLine){
			try{
				lineValidScan = new Scanner(lineCount.nextLine());
				seqName = lineValidScan.next();
				seqCounter++;
			}
			catch(Exception e){
				realLine = false;
			}
		}
		
		/***************************************
		 * END WORKING
		 ***************************************/

		/** array of Strings to hold sequences and their names before creating AminoAcidSequences */
		sequences = new String[seqCounter];
		names = new String[seqCounter];		
		
		/** instantiate all Strings in arrays */
		for(int x = 0; x < seqCounter; x++){
			names[x] = "";
			sequences[x] = "";
		}
			
		/** scan single String alignment into names and sequences */
		String tempName = "";
		String tempSeq = "";
		Scanner lineScan = new Scanner(alignment);
		lineScan.nextLine(); /** skip first line data */
		while(lineScan.hasNext()){
			Scanner scan = new Scanner(lineScan.nextLine()); /** for some reason, specifying the delimiter will not work */
			
			/** try-catch skips over blank lines in beginning of file and between blocks */
			try{
				tempName = scan.next();
			} catch(NoSuchElementException e1){
				scan = new Scanner(lineScan.nextLine());
				try{
					tempName = scan.next();
				} catch(NoSuchElementException e2){
					scan = new Scanner(lineScan.nextLine());
					tempName = scan.next();
				}
			}
			
			for(int x = 0; x < seqCounter; x++){
				/** if names array is empty, put scanned name in it */
				if(names[x].equals("")){
					names[x] = tempName;
					tempSeq = scan.next();
					/** skip over whitespace */
					while(tempSeq.equals(" "))
						tempSeq = scan.next();
					/** put the rest of the sequence into sequences */
					sequences[x] = tempSeq;
					break;
				}
				/** if tempName matches name of sequence in names */
				else if(tempName.equals(names[x])){
					/** concatenate the rest of the sequence to the end of sequences */
					sequences[x] += scan.next();
					break;
				}
			}			
		}
	}
	
	/**
	 * create table of alignment
	 */
	public void showTable(){
		SequenceTable st = new SequenceTable(storedAlignment);
	    OpenGUI.addTable(st.getTable(), names[0]);

	}
	
	/**
	 * shade alignment in table
	 */
	public void shade(){
		try {
			shadeDriver = new AAShader(storedAlignment);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void setAA(String aa, int x, int y){
		storedAlignment.get(y).setAA(x, aa);
	}

	public void shadeIdentities() {
		// TODO Auto-generated method stub
		shadeDriver.shadeIdentities();
	}

	public void shadeSimilarities() {
		// TODO Auto-generated method stub
		shadeDriver.shadeSimilarities();
	}
	
	public void shadeOtherSimilarities() {
		shadeDriver.shadeOtherSimilarities();
	}
	
	public void setDefaultShading() {
		shadeDriver.setDefaultShading();
	}
	
}
