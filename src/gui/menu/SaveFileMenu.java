package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import gui.MenuItem;

public class SaveFileMenu extends MenuItem {
	
	private JFileChooser fileBrowser;
	private String fileName;
	private String filePath;
	
	public SaveFileMenu(){
		initialize();
	}
	
	protected void initialize(){
		this.setText("Save");
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		initFileBrowser();
		this.addActionListener(this);		
	}
	
	private void initFileBrowser(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO SaveFile as & Save File
		
	}

}
