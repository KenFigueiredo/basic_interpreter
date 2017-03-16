package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import gui.MenuItem;

public class SaveAsFileMenu extends MenuItem{
	
	private JFileChooser fileBrowser;
	private String fileName;
	private String filePath;
	
	public SaveAsFileMenu(){
		initialize();
	}
	
	protected void initialize(){
		this.setText("Save As");
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		
		initFileBrowser();
		this.addActionListener(this);		
	}
	
	private void initFileBrowser(){
		fileBrowser = new JFileChooser();
		fileBrowser.setDialogTitle("Save as...");
		FileFilter filter = new CodeFilter();		
		fileBrowser.addChoosableFileFilter(filter);
		fileBrowser.setAcceptAllFileFilterUsed(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this){
			int choice = fileBrowser.showSaveDialog(this);
			
			if(choice == JFileChooser.APPROVE_OPTION){
				File file = fileBrowser.getSelectedFile();
				// TODO: Save file;
			}
		}
		
	}
}
