package de.tarismu;

import java.io.File;

import de.tarismu.frames.RPiCCFrame;
import de.tarismu.utils.file.FileUtils;
import de.tarismu.utils.mysql.MySQLAgent;
import de.tarismu.utils.properties.PropertiesAgent;

public class RPiCC {
	
	/**
	 * Variables section
	 */
	
	private static MySQLAgent mysqlAgent;
	private static PropertiesAgent propertiesAgent;
	private static String mainFolderPath = System.getProperty("user.home") + File.separatorChar + "AppData" + File.separatorChar + "Roaming" + File.separatorChar + "RPiCC" + File.separatorChar;
	private static FileUtils fileUtils;
	
	public static void main(String[] args) {
		initiateFileUtils();
		
		fileUtils.checkForDirectory();
		
		initiateProperties();
		initiateMySQL();
		initiateFileUtils();
		
		RPiCCFrame rpiccFrame = new RPiCCFrame();
		rpiccFrame.initFrame();
	}
	
	/**
	 * Inititates the MySQL Database Agent
	 */
	
	private static void initiateMySQL(){
		mysqlAgent = new MySQLAgent("194.169.211.71", 3306, "ni68218_2sql13", "ni68218_2sql13", "status123");
	}
	
	/**
	 * Initiates the propertiesagent
	 */
	
	private static void initiateProperties(){
		propertiesAgent = new PropertiesAgent();
	}
	
	/**
	 * Initiates the FileUtils
	 */
	private static void initiateFileUtils(){
		fileUtils = new FileUtils();
	}
	
	
	/**
	 * Returns the MySQL Agent
	 * @return
	 */
	
	public MySQLAgent getMySQLAgent(){
		return mysqlAgent;
	}
	
	/**
	 * Returns the Properties Object
	 * @return
	 */
	
	public PropertiesAgent getPropertiesAgent(){
		return propertiesAgent;
	}
	
	/**
	 * Returns the mainFolderPath in which used files are located
	 * @return
	 */
	
	public String getMainFolderPath(){
		return mainFolderPath;
	}
	
	/**
	 * Returns the fileUtils
	 */
	
	public FileUtils getFileUtils(){
		return fileUtils;
	}
	
}
