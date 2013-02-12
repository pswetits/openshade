package app;

import java.util.Vector;


public class NucleotideSequence extends Vector<NucleotideUniversal> {    
	private static final long serialVersionUID = -2666782544332527882L;
	
	/**
	 * instance data
	 */
	String name;
    int length;
    Vector<NucleotideUniversal> sequence;
    
    /**
     * constructor instantiates length and sequence, then stores AA
     */
    public NucleotideSequence(String seq, String name){
        length = seq.length();
        sequence = new Vector<NucleotideUniversal>();
        
        /** call to store single AA as NucleotideUniversal in NucleotideSequence */
        storeNuc(seq); 
    }
    
    /** 
     * instantiates NucleotideUniversal object to store AA 
     */
    private void storeNuc(String seq){
    	String currentNucType;
    	for(int x = 0; x < length; x++){
    		currentNucType = Character.toString(seq.charAt(x)); /** type stored as String */
    		NucleotideUniversal n = new NucleotideUniversal(currentNucType, x, false);
    		sequence.add(n); /** add new AA to end of nucleotide chain (NucleotideSequence) */
    	}
    }
    
    /**
     * returns name
     */
    public String getName(){
        return name;
    }
    
    /**
     * returns sequence as String for regex pattern matching
     */
    public String getSeq() {
        return sequence.toString();
    }
    
    /**
     * returns length of sequence
     */
    public int getLength(){
        return length;
    }
    
    /**
     * returns AA in specified position for placement into table 
     */
    public String getNuc(int pos){
    	return sequence.elementAt(pos).toString();
    }
    
    public void setNuc(int pos, String aa){
    	sequence.elementAt(pos).changeType(aa);
    }
    
    public NucleotideUniversal getAAType(int pos){
    	return sequence.elementAt(pos);
    }
    
    public int getNucShade(int pos){
    	return sequence.elementAt(pos).getShading();
    }
}
