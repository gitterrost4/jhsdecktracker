package hsdecktracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private static Properties globalConfig;
	
	private static void loadConfig(){
		Properties defaultProps = new Properties();
		try{
			InputStream defaultInputStream = Configuration.class.getResourceAsStream("/config/default.conf");
			if(defaultInputStream != null) {
				defaultProps.load(defaultInputStream);				
			}
		} catch(IOException e){
			
		}
		globalConfig = new Properties(defaultProps);
		try{
			File propFile = new File("jhsdecktracker.conf");
			if(propFile.exists()){
				FileInputStream fis = new FileInputStream("jhsdecktracker.conf");
				globalConfig.load(new FileInputStream("jhsdecktracker.conf"));				
			}
		} catch (IOException e){
			
		}
	}
	static {
		loadConfig();
	}
	public static Properties getConfig(){
		return globalConfig;
	}
	
	public static void storeConfig() {
		try{
			FileWriter writer = new FileWriter("jhsdecktracker.conf");
			globalConfig.store(writer,"");
		} catch(IOException e){
			System.err.println("Couldn't save config");
		}
	}
}
