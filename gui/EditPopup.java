package gui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import java.awt.Point;
import java.awt.event.*;

public class EditPopup extends JPopupMenu{
	JMenuItem editItem;

    public EditPopup(){
    
        editItem = new JMenuItem("Edit AA");
        editItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		showEditDialog(evt);
        	}
        });
        add(editItem);
    }
    
    private void showEditDialog(ActionEvent evt){
    	EditChooserDialog editChooser = new EditChooserDialog();
    	editChooser.setVisible(true);
    }
    
}

