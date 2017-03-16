package interpreter;

import java.awt.Color;

public abstract class Keyword {
	
	protected String word;
	protected String description;
	protected Color color;
	
	protected String function;
	
	public Keyword(String func){
		this.function = func;
	}
	
	public abstract String function(String s);
	
	public void changeColor(Color c){
		color = c;
	}
	
	public Color getColor(){
		return color;
	}
	
	public String getDescription(){
		return description;
	}	
}
