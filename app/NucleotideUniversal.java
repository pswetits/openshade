package app;

public class NucleotideUniversal {
    private String name;
    private int position;
    private int shading;
    
    public NucleotideUniversal(String name, int position, boolean shaded) {
    	/** checks to make sure name is a real AA before instantiating */
    	if(trueNucleotide(name)){
    		this.name = name;
    		this.position = position;
    	} 
    }
    
    /**
     * checks to make sure given char is an actual AA type
     */
    public boolean trueNucleotide(String name){
    	if (name.equals("A") || name.equals("T") || name.equals("C") || name.equals("G") || 
    			name.equals("-"))
    		return true;
    	else return false;
    }
    
    /**
     * return position of AA
     */
    public int getPosition(){
    	return position;
    }
    
    /**
     * set new position of AA
     */
    public void setPosition(int newPos){
    	position = newPos;
    }
    
    public void changeType(String x){
    	if(trueNucleotide(x))
    		name = x; 
    }
    
    public void setShading(int s){
    	this.shading = s;
    }
    
    public int getShading(){
    	return shading;
    }
    
    /**
     * return name as String representation
     */
    public String toString(){
        return name;
    }
    
}


