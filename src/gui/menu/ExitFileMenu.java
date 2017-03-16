package gui.menu;

import java.awt.event.ActionEvent;

import gui.MenuItem;
import gui.Window;

public class ExitFileMenu extends MenuItem {
	
	private Window gui;
	
	public ExitFileMenu(Window gui){
		this.gui = gui;
		initialize();
	}
	
	protected void initialize() {
		this.setText("Exit");
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		gui.exitProgram();
	}
	
}
