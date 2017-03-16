package gui.textWindow;

import java.awt.Color;

public class KeywordText {
	
	private String word;
	private Color textColor;
	
	public KeywordText(String s, Color c){
		word = s;
		textColor = c;
	}
	
	public void changeColor(Color c){
		textColor = c;
	}
	
	public Color getColor(){
		return textColor;
	}
	
}
