package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropFile {
	
	private Properties prop;
	
	public void loadDefaultPropFile() {
		String fileName = "src/test/resources/test.properties";
		prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(fileName);
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getPropertyValue(String propName) {
		return prop.getProperty(propName);
	}
	
}
