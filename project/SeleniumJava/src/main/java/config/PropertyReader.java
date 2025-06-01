package config;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

	private Properties properties = null;

	private String strConfigPath = "/src/main/resources/configuration.properties";

	private static PropertyReader prop = new PropertyReader();

	private PropertyReader() {

	}

	public static PropertyReader getInstance() {

		return prop;
	}

	/**
	 * Method to load the properties file from a specified path
	 * 
	 * @param none
	 * @return none
	 */
	private final Properties loadPropertiesFile() {

		try {
			properties = new Properties();
			String filePath = System.getProperty("user.dir") + strConfigPath;
			FileInputStream fileInputStream = new FileInputStream(filePath);
			properties.load(fileInputStream);
			System.out.println("Properties file loaded successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return properties;
	}

	public String getProperty(String key) {

		if (properties == null) {
			loadPropertiesFile();
		}
		System.out.println("Key : " + key + " Value : " + properties.get(key));
		return (String) properties.get(key);
	}
}
