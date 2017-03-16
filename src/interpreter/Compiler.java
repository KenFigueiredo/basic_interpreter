package interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Compiler {
	
	private String file;
	private String fileName;
	
	public Compiler(String f, String fN){
		file = f;
		fileName = fN + ".java";
		
		writeFile();
		runProcess("javac " + fileName);
		runProcess("java " + fN);
	}
	
	
	private void writeFile(){
		try {
			PrintWriter pw = new PrintWriter(fileName);
			pw.write(file);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void runProcess(String command){
		try {
			Process process = Runtime.getRuntime().exec(command);
			runLog(command + " stdout:", process.getInputStream());
			runLog(command + " stderr:", process.getErrorStream());
			process.waitFor();
			System.out.println(command + " exitValue()" + process.exitValue());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void runLog(String name, InputStream in){
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		try {
			while((line = br.readLine()) != null){
				System.out.println(name + " " + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
