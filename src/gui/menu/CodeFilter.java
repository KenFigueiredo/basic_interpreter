package gui.menu;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class CodeFilter extends FileFilter{

	private ArrayList<String> extensions;
	
	public CodeFilter(){
		extensions = new ArrayList<String>();
		extensions.add("B");
		extensions.add("BAS");
	}
	
	private String getExtension(File f){
		String fileName = f.getName();
		int i = fileName.lastIndexOf('.');
		
		if(i > 0 && i < fileName.length() - 1){
			String s = fileName.substring(i + 1);
			return s;
		}
		
		else 
			return null;
	}
	
	@Override
	public boolean accept(File f) {
		if(f.isDirectory())
			return true;
		
		String s = getExtension(f);
	
		if(s != null){			
			if(extensions.contains(s))
				return true;
		}
		
		return false;
		
	}

	@Override
	public String getDescription() {
		return "BASIC source file (*.B, *.BAS)";
	}
	
	
}
