package config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	private Properties properties = null;

	private static final PropertyReader prop = new PropertyReader();

	private PropertyReader() {
	}

	public static PropertyReader getInstance() {
		return prop;
	}

	/**
	 * Loads properties file using classpath (works in Jenkins too)
	 */
	private final Properties loadPropertiesFile() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("configuration.properties")) {
			if (inputStream == null) {
				throw new FileNotFoundException("configuration.properties not found in classpath");
			}
			properties = new Properties();
			properties.load(inputStream);
			System.out.println("Properties file loaded successfully");
		} catch (Exception e) {
			System.out.println("Error loading properties file: " + e.getMessage());
		}
		return properties;
	}

	public String getProperty(String key) {
		if (properties == null) {
			loadPropertiesFile();
		}
		String value = properties.getProperty(key);
		System.out.println("Key : " + key + " Value : " + value);
		return value;
	}
}
