package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.menu.ExitFileMenu;
import gui.menu.InterpretAndRun;
import gui.menu.NewMenu;
import gui.menu.OpenDirFileMenu;
import gui.menu.OpenFileMenu;
import gui.menu.SaveAsFileMenu;
import gui.menu.SaveFileMenu;

public class MenuBar extends JMenuBar {
	
	private Window gui;
	private JMenuItem newMenu, open, openDir, save, saveAs, exit;
	private JMenuItem interpret, interpretRun, run;
	
	public MenuBar(Window gui){
		this.gui = gui;
		initialize();		
	}
	
	private void initialize(){
		initLookFeel();
		initFileMenu();
		initEditMenu();
		initRunMenu();
		initHelpMenu();	
	}
	
	private void initLookFeel(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	private void initFileMenu(){
		JMenu menu = new JMenu("File");	
			menu.add(newMenu = new NewMenu());
			menu.add(open = new OpenFileMenu(gui));
			menu.add(openDir = new OpenDirFileMenu(gui));
			menu.addSeparator();
			menu.add(save = new SaveFileMenu());
			menu.add(saveAs = new SaveAsFileMenu());
			menu.addSeparator();
			menu.add(exit = new ExitFileMenu(gui));
		this.add(menu);		
	}
	
	public JMenuItem getOpenDirItem(){
		return openDir;
	}
	
	private void initEditMenu(){
		JMenu menu = new JMenu("Edit");
		this.add(menu);	
	}
	
	private void initRunMenu(){
		JMenu menu = new JMenu("Run");
			//menu.add(interpret = new Compile());
			menu.add(interpretRun = new InterpretAndRun(gui));
			//menu.add(run = new Run());
		this.add(menu);	
	}
	
	private void initHelpMenu(){
		JMenu menu = new JMenu("Help");
		this.add(menu);	
	}
	
	public void allowInterpret(boolean b){
		((InterpretAndRun) interpretRun).enableInterpret(b);
	}
}
