package gui.table;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import app.AminoAcidSequence;

public class SequenceTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1996425718777920109L;
	
	/** 
	 * instance data 
	 */
	int columnCount;
	int rowCount;
	Vector<AminoAcidSequence> alignment;
	
	/** 
	 * constructor finds number of rows and columns
	 */
	public SequenceTableModel(Vector<AminoAcidSequence> alignment){
		this.alignment = alignment;
		columnCount = ((AminoAcidSequence) alignment.get(0)).getLength();
		rowCount = alignment.size();
	}
	
	/**
	 * sets value in each cell
	 */
    public Object getValueAt(int rowIndex, int columnIndex) {
    	return ((AminoAcidSequence) alignment.get(rowIndex)).getAA(columnIndex);
    }
    
    /**
     * returns number of columns
     */
    public int getColumnCount() {
    	return columnCount;
    }
    
    /**
     * returns number of rows
     */
    public int getRowCount() {
    	return rowCount;
    }

	
}
