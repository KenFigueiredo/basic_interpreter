package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import gui.MenuItem;
import gui.Window;

public class OpenDirFileMenu extends MenuItem {
	
	private JFileChooser fileBrowser;
	private Window gui;
	
	public OpenDirFileMenu(Window gui){
		this.gui = gui;
		initialize();
	}

	protected void initialize() {
		this.setText("Open Directory");
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		initFileBrowser();
		this.addActionListener(this);
	}
	
	private void initFileBrowser(){
		fileBrowser = new JFileChooser();
		fileBrowser.setDialogTitle("Open Directory...");
		fileBrowser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this){
			int val = fileBrowser.showOpenDialog(this);
			
			if(val == JFileChooser.APPROVE_OPTION){
				gui.setOpenDir(fileBrowser.getSelectedFile());
			}	
		}
	}

}
