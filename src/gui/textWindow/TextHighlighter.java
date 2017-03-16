package gui.textWindow;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import interpreter.Keyword;

public class TextHighlighter implements DocumentListener {
	
	private Map<String,KeywordText> keyWords;
	private JTextPane editor;
	private StyledDocument doc;

	private StyleContext sc;
	private Style logicStyle, commentStyle;
	private Font font;
	private Color logicColor, commentColor, normalText;
	
	private String KEYWORD_REGEX;
	
	public TextHighlighter(JTextPane ep){
		editor = ep;
		initKeywordsAndStyle();
		doc = editor.getStyledDocument();
		doc.addDocumentListener(this);
	}
	
	private void initKeywordsAndStyle(){
		keyWords = new HashMap<String, KeywordText>();
		sc = new StyleContext();
		
		StringBuilder sb = new StringBuilder("");
		sb.append("(");
		
		//Initialize Logic keywords and styles
			logicColor = Color.MAGENTA;
			String[] logicBlock = {"LET", "DATA", "IF", "THEN", "ELSE", "FOR", "TO", "NEXT",
								   "DO", "LOOP", "GOTO", "GOSUB", "ON", "DEF", "LIST", "PRINT",
								   "INPUT", "TAB", "AT"};
			
			for(int i = 0; i < logicBlock.length; i++){
				keyWords.put(logicBlock[i], new KeywordText(logicBlock[i], logicColor));
				sb.append("\\b").append(logicBlock[i]).append("\\b").append("|");
			}
			
			logicStyle = sc.addStyle("logicText", null);
			font = new Font("Courier New",Font.BOLD,12);
			StyleConstants.setForeground(logicStyle, logicColor);
			StyleConstants.setFontFamily(logicStyle, font.getFamily());
			StyleConstants.setBold(logicStyle, true);
			
		
		//Initialize comment keywords and styles
			commentColor = Color.GREEN;
			String[] commentBlock = {"REM", "USR", "TRON", "TROFF"};
			
			for(int i = 0; i < commentBlock.length; i++){
				keyWords.put(commentBlock[i], new KeywordText(commentBlock[i], commentColor));
				sb.append("\\b").append(commentBlock[i]).append("\\b").append("|");
			}
			commentStyle = sc.addStyle("commentText", null);
			font = new Font("Courier New",Font.BOLD,12);
			StyleConstants.setForeground(commentStyle, commentColor);
			StyleConstants.setFontFamily(commentStyle, font.getFamily());
			StyleConstants.setBold(commentStyle, true);
			
			sb.deleteCharAt(sb.length()-1);
			sb.append(")");
			KEYWORD_REGEX = sb.toString();
			
			normalText = Color.BLACK;
	}

	
	private void checkEditor(String s){	
		Runnable setColor = new Runnable(){		
			@Override
			public void run(){
				clearColor();
				Pattern p = Pattern.compile(KEYWORD_REGEX);
				Matcher match = p.matcher(s.toUpperCase());
				
				while(match.find()){
					int start = match.start();
					int end = match.end() - match.start();
					
					System.out.println("strt: " + match.start() + " end: " + match.end());
					try {
						System.out.println(editor.getText(start, end));
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					try {
						String str = editor.getText(start, end);
						
						if(!checkCase(str)){
							str = str.toUpperCase();
							doc.remove(start, end);
							doc.insertString(start, str, null);
						}
						System.out.println("hl thread: " + str);
						Color c = keyWords.get(str).getColor();
						changeColor(start,end,c);
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
				}
			}	
		};
		
		SwingUtilities.invokeLater(setColor);
	}
	
	private boolean checkCase(String s){
		if(s.equals(s.toUpperCase()))
			return true;
		
		else 
			return false;
	}
	
	private void changeColor(int offset, int length, Color col){
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, col);
		doc.setCharacterAttributes(offset, length, aset, true);
	}
	
	private void clearColor(){
		changeColor(0,editor.getText().length(),normalText);
	}

		
	@Override
	public void changedUpdate(DocumentEvent e) {	
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkEditor(editor.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkEditor(editor.getText());	
	}
	
}
