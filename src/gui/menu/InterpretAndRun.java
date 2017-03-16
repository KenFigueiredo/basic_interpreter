package gui.menu;

import java.awt.event.ActionEvent;

import gui.MenuItem;
import gui.Window;

public class InterpretAndRun extends MenuItem {
	
	private Window gui;
	
	public InterpretAndRun(Window gui){
		this.gui = gui;
		initialize();
	}
	
	public void enableInterpret(boolean b){
		this.setEnabled(b);
	}
	
	@Override
	protected void initialize() {
		this.setText("Interpret & Run");
		this.setEnabled(false);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this){
			gui.getSelectedTab().startInterpreter();
		}
	}

}
