package app;

import java.util.Vector;


public class AminoAcidSequence extends Vector<AminoAcidUniversal> {    
	private static final long serialVersionUID = -2666782544332527882L;
	
	/**
	 * instance data
	 */
	String name;
    int length;
    Vector<AminoAcidUniversal> sequence;
    
    /**
     * constructor instantiates length and sequence, then stores AA
     */
    public AminoAcidSequence(String seq, String name){
        length = seq.length();
        sequence = new Vector<AminoAcidUniversal>();
        
        /** call to store single AA as AminoAcidUniversal in AminoAcidSequence */
        storeAA(seq); 
    }
    
    /** 
     * instantiates AminoAcidUniversal object to store AA 
     */
    private void storeAA(String seq){
    	String currentAAType;
    	for(int x = 0; x < length; x++){
    		currentAAType = Character.toString(seq.charAt(x)); /** type stored as String */
    		AminoAcidUniversal aa = new AminoAcidUniversal(currentAAType, x, false);
    		sequence.add(aa); /** add new AA to end of protein chain (AminoAcidSequence) */
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
    public String getAA(int pos){
    	return sequence.elementAt(pos).toString();
    }
    
    public void setAA(int pos, String aa){
    	sequence.elementAt(pos).changeType(aa);
    }
    
    public AminoAcidUniversal getAAType(int pos){
    	return sequence.elementAt(pos);
    }
    
    public int getAAShade(int pos){
    	return sequence.elementAt(pos).getShading();
    }
}
