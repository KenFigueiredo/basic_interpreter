package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class NewMenu extends JMenu implements ActionListener {

	public NewMenu(){
		this.setText("New                                    ");
		initialize();
	}
	
	private void initialize(){
		JMenuItem newBasicFile = new JMenuItem("BASIC file           ");
		newBasicFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newBasicFile.addActionListener(this);		
		this.add(newBasicFile);
		
		JMenuItem newFolder = new JMenuItem("Folder");
		newBasicFile.addActionListener(this);		
		this.add(newFolder);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().toString().equals("newBasicFile")){
			//TODO: Create new frame of type Basic
		}
		
		if(e.getSource().toString().equals("newFolder")){
			//TODO: Create new folder
		}
		
	}
}
