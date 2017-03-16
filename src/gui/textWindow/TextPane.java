package gui.textWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import gui.Window;
import interpreter.Interpreter;

public class TextPane extends JPanel {	
	private Window gui;
	private File openFile;
	private JTextPane textEditor;
	private BufferedReader buffRead;
	private JScrollPane scrollPane;
	
	private TextHighlighter highlighter;	
	private Interpreter interpreter;
	
		public TextPane(Window gui, File f){
			this.gui = gui;
			openFile = f;
			
			initialize();
		}
	
		private void initialize(){
			
			this.setLayout(new BorderLayout());
			
			initTextArea();
			scrollPane = new JScrollPane(textEditor);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.getVerticalScrollBar().setUnitIncrement(16);
			this.add(scrollPane, BorderLayout.CENTER);
			
			highlighter = new TextHighlighter(textEditor);
			interpreter = new Interpreter(textEditor, openFile.getName());
		
		}
	
		private void initTextArea(){
			textEditor = new JTextPane();
			textEditor.setSize(35,200);
			
			try {
				buffRead = new BufferedReader(new FileReader(openFile));
				textEditor.read(buffRead, openFile.toString());
				buffRead.close();
				textEditor.requestFocus();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			textEditor.setBackground(Color.WHITE);
			textEditor.setForeground(Color.BLACK);
			textEditor.setFont(new Font("Courier New",Font.PLAIN,12));
			textEditor.setEditable(true);
			
			Document doc = textEditor.getDocument();
			
			if(doc instanceof PlainDocument)
				doc.putProperty(PlainDocument.tabSizeAttribute, 4);
		}
		
		public String getTitle(){
			return openFile.getName();
		}
		
		public void startInterpreter(){
			interpreter.startRun();
		}
}
