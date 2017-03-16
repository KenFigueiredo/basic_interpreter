package interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.swing.JTextPane;

public class Interpreter {
		
	private interface runFunctions{
		String run(String s);
	}

	private JTextPane editor;
	private Map<String, Callable> functionMap = new HashMap<>();
	private String interpretedClass;
	private Functions functions;
	private Logic logic;
	private Math math;
	
	private String runtime;
	private String fileName;
	
		public Interpreter(JTextPane ed, String fN){
			editor = ed;
			formatFileName(fN);
			initializeFunctions();
		}
		
		public void startRun(){
			initializeClass();
			fillBody();
			endClass();	
			new Compiler(interpretedClass, fileName);
			
		}
		
		private void formatFileName(String fN){
			fileName = fN.substring(0, fN.indexOf('.'));
			fileName.trim();
			
			if(fileName.contains(" ")){
				String[] temp = fileName.split(" ");
				fileName = "";
				
				for(int i = 0; i < temp.length; i++)
					fileName += temp[i];	
			}
		}
		
		private void initializeFunctions(){
			functions = new Functions();
				functionMap.put("PRINT", () -> functions.PRINT(runtime));
			logic = functions.getLogicLib();
				functionMap.put("FOR", () -> logic.FOR(runtime));
				functionMap.put("NEXT", () -> logic.NEXT(runtime));
			
			math = functions.getMathLib();	
		}
		
		private void initializeClass(){
			interpretedClass = "public class " + fileName + "{\n" +
							   "public static void main(String[] args){\n";
		}
		
		private void fillBody(){
			String[] lines = editor.getText().split("\n");
			
			for(int i = 0; i < lines.length; i++){
				String[] curLine = lines[i].split(" ");
				
				runtime = lines[i].substring(curLine[0].length(), lines[i].length()).trim();
				
				try {
					interpretedClass += functionMap.get(curLine[1]).call();	
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		}
		
		private void endClass(){
			interpretedClass += "}}";
		}
}
