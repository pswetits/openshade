package gui;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPopupMenu;

import app.Manager;

import java.awt.event.*;
import java.io.File;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class OpenGUI {

	private JFrame frame;
	
	/** 
	 * instantiate display components 
	 */
	private JPanel displayPanel;
	private static JScrollPane scrollPane;
	private static JTabbedPane tabbedPane;
	private static JTable[] tabTable = new JTable[5];
	private static int tabCount = 0;
	static JButton shadeButton;
	
	
	/** 
	 * instantiate manager 
	 */
	private static Manager man = new Manager();

	
	/**
	 * boolean true if table is currently being displayed, error check for shading nothing
	 */
	static boolean tableIn = false;
	static boolean shadeId= false;
	static boolean shadeSim = false;
	static boolean shadeOtherSim = false;
	
	/**
	 * Listeners
	 */
	static PopupListener popupListener;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenGUI window = new OpenGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpenGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 309);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		displayPanel = new JPanel();
		
		JPanel buttonPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(displayPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
						.addComponent(buttonPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(displayPanel, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		/***************************************************************
		 * Set up table listener
		 ***************************************************************/
		popupListener = new PopupListener();
		
		/**
		 * instantiate shadeButton and add action event to shade sequences
		 */
		shadeButton = new JButton("Shade");
		buttonPanel.add(shadeButton);
		shadeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				man.shade();
				man.setDefaultShading();
				refresh();
				if(tableIn){
					if(shadeId){
						man.shadeIdentities();
						if(shadeSim){
							man.shadeSimilarities();
							if(shadeOtherSim)
								man.shadeOtherSimilarities();
						}
					}
				}
			}
		});
		
		JCheckBox idBox = new JCheckBox("Shade Identities");
		final JCheckBox simBox = new JCheckBox("Shade Similarities");
		final JCheckBox otherSimBox = new JCheckBox("Shade Other Similarities");


		buttonPanel.add(idBox);
		idBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (getIdOption()){
					simBox.setEnabled(false);
					otherSimBox.setEnabled(false);
					shadeId = false;
				}
				else{
					simBox.setEnabled(true);
					shadeId = true;
				}
			}
		});
		
		
		simBox.setEnabled(false);
		buttonPanel.add(simBox);
		simBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (getSimOption()){
					otherSimBox.setEnabled(false);
					shadeSim = false;
				}
				else{
					otherSimBox.setEnabled(true);
					shadeSim = true;
				}
			}
		});
		
		otherSimBox.setEnabled(false);
		buttonPanel.add(otherSimBox);
		otherSimBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (getOtherSimOption())
					shadeOtherSim = false;
				else shadeOtherSim = true;
			}
		});
		
		
		/**
		 * grouplayout code for tabbedPane
		 */
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_displayPanel = new GroupLayout(displayPanel);
		gl_displayPanel.setHorizontalGroup(
			gl_displayPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
		);
		gl_displayPanel.setVerticalGroup(
			gl_displayPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_displayPanel.createSequentialGroup()
					.addContainerGap(8, Short.MAX_VALUE)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
		);
		
		
		displayPanel.setLayout(gl_displayPanel);
		frame.getContentPane().setLayout(groupLayout);
		
		
		/**
		 * Set up JMenu items
		 */
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		/**
		 * set up open button with action event to open file chooser
		 */
		JMenuItem openMenu = new JMenuItem("Open");
		openMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
		fileMenu.add(openMenu);
		
	
	}
	
	
	
	/**
	 * sets up FileChooser and calls readIn() method of man
	 * @param evt
	 */
	private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        // Instantiates JFileChooser to open file when open menu is clicked
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showOpenDialog(null);
		
        //tries to open file
        if (status == JFileChooser.APPROVE_OPTION) {
            File input = chooser.getSelectedFile();
            man.readIn(input);
        }
	}
	
	public static void refreshShading(){
		man.shade();
		man.setDefaultShading();
		refresh();
		if(tableIn){
			if(shadeId){
				man.shadeIdentities();
				if(shadeSim){
					man.shadeSimilarities();
					if(shadeOtherSim)
						man.shadeOtherSimilarities();
				}
			}
		}
	}
	
	private static void addTab(String tabName){
		scrollPane = new JScrollPane(tabTable[tabCount]);
		tabbedPane.addTab(tabName, null, scrollPane, null);
		tabbedPane.validate();
		tabCount++;

	}
	
	public static int getRowSelection(){
		int y = 0;
		if(tabCount > 0)
			y = tabTable[tabCount-1].getSelectedRow(); 
		/**int y = 0;
		if(tabCount > 0){
			Point p = tabTable[tabCount-1].getMousePosition();
			y = tabTable[tabCount-1].rowAtPoint(p);
		}*/
		return y;
	}
	
	public static int getColumnSelection(){
		int x = 0;
		if(tabCount > 0)
			x = tabTable[tabCount-1].getSelectedColumn(); 
		/**int x = 0;
		if(tabCount > 0) {
			Point p = tabTable[tabCount-1].getMousePosition();
			x = tabTable[tabCount-1].columnAtPoint(p);
		} */
		return x;
	}
	

	
	public static void addTable(JTable table, String tabName){
		tabTable[tabCount] = table;
		tabTable[tabCount].setComponentPopupMenu(new EditPopup());
		
		//tabTable[tabCount].addMouseListener(popupListener);
		addTab(tabName);
		tableIn = true;
		
	}
	
	
	public static boolean getIdOption(){
		if(shadeId)
			return true;
		else return false;
	}
	
	public static boolean getSimOption(){
		if(shadeSim)
			return true;
		else return false;
	}
	
	public static boolean getOtherSimOption() {
		if (shadeOtherSim)
			return true;
		else return false;
	}
	
	public static void refresh(){
		tabbedPane.repaint();
	}
}
