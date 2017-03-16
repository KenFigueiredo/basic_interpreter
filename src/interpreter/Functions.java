package interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Functions {
	

	public enum mathFunc{
		ABS,ATN,CLOG,COS,DEG,EXP,LOG,RAD,RND,SIN,SQR,STRIG;
	}
	
	public enum logicFunc{
		AND,BYE,CLR,CONT,END,ENTER,FOR,GET,GOSUB,GOTO,IF,LET,NEXT,NOT,ON,OR,POP,
		PRINT,PUT,RUN,STEP,STOP,THEN,TO,XIO;
	}
	
	public enum functions{
		ADR,ASC,CHR$,CLOAD,
		CLOSE,COLOR,COM,CSAVE,DATA,DIM,
		DOS,DRAWTO,FRE,
		GRAPHICS,INPUT,INT,LEN,LIST,LOAD,LOCATE,
		LPRINT,NOTE,OPEN,PADDLE,
		PEEK,PLOT,POINT,POKE,POSITION,PTRIG,
		READ,REM,RESTORE,RETURN,SAVE,SETCOLOR,SGN,
		SOUND,STATUS,STICK,STR$,
		TRAP,USR,VAL;
	}	
	
	
	public ArrayList<String> variables;
	public int varIndex;
	public int varLen;
	
	private Logic logic;
	private Math math;
	
	public Functions(){
		variables = new ArrayList<String>();
		varIndex = 97;
		varLen = 1;
		
		logic = new Logic(this);
	}

 	public Logic getLogicLib(){
		return logic;
	}
	
	public Math getMathLib(){
		return math;
	}
	
	public String getNextVar(){
		
		String t = "";
		
		if(varIndex > 122){
			varLen++;
			varIndex = 97;
		}
		
		for(int i = 0; i < varLen; i++){
			t = "" + (char)varIndex;
		}
		
		variables.add(t);
		varIndex++;
		
		return t;
	}
	
	public String PRINT(String s){
		String arg = s.substring(5).trim();
		return "System.out.println("+ arg +");\n";
	}
	
}
