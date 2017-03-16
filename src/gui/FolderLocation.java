package gui;

import java.io.File;

public class FolderLocation {
	
	private String strDir, name;
	private File fileDir;
	
	public FolderLocation(File f){
		fileDir = f;
	}
	
	public String getName(){
		return name;
	}
	
	public String getStrDir(){
		return strDir;
	}
	
	public File getFileDir(){
		return fileDir;
	}
}
