package gui;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import gui.explorer.FolderExplorer;
import gui.textWindow.TabbedFields;
import gui.textWindow.TextPane;

public class Window extends JFrame {
	
	private ArrayList<String> supportedFileTypes;
	
	private File openDir = null;
	private String dir;
	private JMenuBar menubar;
	private FolderExplorer folderExplorer;
	private TabbedFields TextPanel;
	private JTabbedPane ConsolePanel;
	private JSplitPane folderSplit, UISplit;
	
	public Window(){
		initialize();
	}
	
	
	private void initialize(){
		initBG();
		initMenu();
		initFrame();
		
	}
	
	private void initBG(){
		supportedFileTypes = new ArrayList<String>();
		supportedFileTypes.add(".b");
		supportedFileTypes.add(".bas");
	}
	
	private void initUserPanel(){
		
		TextPanel = new TabbedFields(this);
		
		ConsolePanel = new JTabbedPane();
		
		UISplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, TextPanel, ConsolePanel);		
	}
	
	private void initFrame(){
		this.setTitle("BASIC Interpreter");
		this.setSize(800, 640);
		this.setExtendedState(this.getExtendedState() | Window.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initFolderExplorer();
		initUserPanel();
		
		folderSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, folderExplorer, UISplit);
		
		this.add(folderSplit,BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	private void initFolderExplorer(){
		folderExplorer = new FolderExplorer(this);
	}
	
	private void initMenu(){
		menubar = new MenuBar(this);
		this.setJMenuBar(menubar);
	}
	
	public void setOpenDir(File f){
		openDir = f;
		folderExplorer.reloadTree(openDir);
	}
	
	public boolean fileSupported(String s){
		return supportedFileTypes.contains(s.toLowerCase());			
	}
	
	public void openNewTab(File f){
		TextPane p = new TextPane(this,f);
		TextPanel.addNewTab(p);	
	}
	
	public void allowInterpret(boolean b){
		((MenuBar) menubar).allowInterpret(b);
	}
	
	public TextPane getSelectedTab(){
		return TextPanel.getSelectedTab();
	}
	
	public File getOpenDir(){
		return openDir;
	}
	
	public void exitProgram(){
		this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
	}
}
