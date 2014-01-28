package de.tarismu.utils.properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import de.tarismu.RPiCC;

public class PropertiesAgent {
	
	private RPiCC main = new RPiCC();
	private Properties properties;
	private File propertiesFile;
	
	public PropertiesAgent(){
		checkForProperties();
	}
	
	/**
	 * Checks for the Properties File and creates it from package if not exists
	 * @throws IOException
	 */
	
	public void checkForProperties() {
		
		try{
		
			propertiesFile = new File(main.getMainFolderPath() + "settings.properties");
			properties = new Properties();
			
			if(!propertiesFile.exists()){
				
				propertiesFile.createNewFile();
				InputStream ressourceInput = main.getClass().getClassLoader().getResourceAsStream("res" + File.separatorChar + "settings.properties");
				OutputStream ressourceOutput = new FileOutputStream(propertiesFile);
				
				int readBytes;
				byte[] buffer = new byte[4096];
				while((readBytes = ressourceInput.read(buffer)) > 0) {
					ressourceOutput.write(buffer, 0, readBytes);
				}
				
				ressourceInput.close();
				ressourceOutput.flush();
				ressourceOutput.close();
				
			}
			
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(propertiesFile));
			properties.load(inputStream);
			inputStream.close();
		
		}catch (IOException e){
			JOptionPane.showMessageDialog(null, "An Exception occurred (P103): " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}
	
	
	
}
