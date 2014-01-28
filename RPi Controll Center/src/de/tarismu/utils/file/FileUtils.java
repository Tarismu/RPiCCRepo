package de.tarismu.utils.file;

import java.io.File;

import de.tarismu.RPiCC;

public class FileUtils {
	
	private RPiCC main = new RPiCC();
	
	public void checkForDirectory(){
		
		File mainPath = new File(main.getMainFolderPath());
		
		if(!mainPath.isDirectory()){
			mainPath.mkdir();
		}
		
	}
	
}
