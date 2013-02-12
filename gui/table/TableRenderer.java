package gui.table;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

import app.AminoAcidSequence;

public class TableRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1623858609432388718L;
	
	/**
	 * instance data: background color, text color, text
	 */
	Vector<AminoAcidSequence> alignment;
	
	/**
	 * constructor sets colors and AA
	 */
	public TableRenderer(Vector<AminoAcidSequence> alignment){
		this.alignment = alignment;
	}

	/**
	 * automatically called when cell is rendered
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
                                                 boolean isSelected, boolean hasFocus, 
                                                 int row, int column) {
		
		/** grab individual cell so changes don't apply to whole column */
		Component cell = super.getTableCellRendererComponent(
				table, value, isSelected, hasFocus, row, column);
		
		if(alignment.get(row).getAAType(column).getShading() == 1){
			cell.setBackground(Color.black);
			cell.setForeground(Color.white);
		}
		
		if(alignment.get(row).getAAType(column).getShading() == 2){
			cell.setBackground(Color.gray);
			cell.setForeground(Color.white);
		}
		
		if(alignment.get(row).getAAType(column).getShading() == 3){
			cell.setBackground(Color.lightGray);
			cell.setForeground(Color.white);
		}
		
		if(alignment.get(row).getAAType(column).getShading() == 0){
			cell.setBackground(Color.white);
			cell.setForeground(Color.black);
		}
		
		/** set properties */
		setFont(new Font("Courier", Font.BOLD, 16));
		setHorizontalAlignment(JLabel.CENTER);
		table.setCellSelectionEnabled(true);
		if (table.isCellSelected(row, column))
			setBackground(Color.red);

		/** return new instance of the cell */
		return cell;
	}
}