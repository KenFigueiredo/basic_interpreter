package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

public abstract class MenuItem extends JMenuItem implements ActionListener{
	protected abstract void initialize();
	public abstract void actionPerformed(ActionEvent e);
}
