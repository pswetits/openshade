package app;

public class AminoAcidUniversal {
    private String name;
    private int position;
    private int shading;
    
    public AminoAcidUniversal(String name, int position, boolean shaded) {
    	/** checks to make sure name is a real AA before instantiating */
    	if(trueAA(name)){
    		this.name = name;
    		this.position = position;
    	} 
    }
    
    /**
     * checks to make sure given char is an actual AA type
     */
    public boolean trueAA(String name){
    	if (name.equals("A") || name.equals("R") || name.equals("N") || name.equals("D") || 
    			name.equals("C") || name.equals("E") || name.equals("Q") || name.equals("G") || 
    			name.equals("H") || name.equals("I") || name.equals("L") || name.equals("K") || 
    			name.equals("M") || name.equals("F") || name.equals("S") || name.equals("T") || 
    			name.equals("W") || name.equals("Y") || name.equals("V") || name.equals("P") || 
    			name.equalsIgnoreCase("X") || name.equals("-"))
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
    	if(trueAA(x))
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


