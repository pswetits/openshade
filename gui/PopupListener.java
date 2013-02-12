package gui;

import java.awt.event.*;

class PopupListener extends MouseAdapter implements MouseListener {
	
    public void mouseReleased(MouseEvent e) {
        showPopup(e);
    }

    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
        	EditPopup menu = new EditPopup();
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}