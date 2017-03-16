package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import gui.MenuItem;
import gui.Window;

public class OpenFileMenu extends MenuItem {
	
	private JFileChooser fileBrowser;
	private Window gui;
	
	public OpenFileMenu(Window gui){	
		this.gui = gui;
		initialize();
	}

	protected void initialize(){
		this.setText("Open File");
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		initFileBrowser();
		this.addActionListener(this);
	}
	
	private void initFileBrowser(){
		fileBrowser = new JFileChooser();	
		fileBrowser.setDialogTitle("Open file");
		FileFilter filter = new CodeFilter();		
		fileBrowser.addChoosableFileFilter(filter);
		fileBrowser.setAcceptAllFileFilterUsed(false);
	}
	
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == this){
			int choice = fileBrowser.showOpenDialog(this);
			
			if(choice == JFileChooser.APPROVE_OPTION){
				File file = fileBrowser.getSelectedFile();
				gui.openNewTab(file);
			}
		}
	}
}
