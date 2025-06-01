package config;

public class TestConfig {

	// MYSQL DATABASE DETAILS
	public static String mySqlDriver = "com.mysql.cj.jdbc.Driver";
	public static String mySqlUsername = "root";
	public static String mySqlPassword = "pas$M0rd@9339";
	private static String mySqlIP = "127.0.0.1";
	private static String mySqlPort = "3306";
	private static String mySqlDatabase = "Selenium_Testing";
	public static String mySqlURL = "jdbc:mysql://" + mySqlIP + ":" + mySqlPort + "/" + mySqlDatabase;

	// SENDING MAIL DETAILS
	public static String server = "smtp.gmail.com";
	public static String from = "debkaransinghania@gmail.com";
	public static String password = "rzodscylcjcgfxud";
	public static String[] to = { "debkaransinghania@gmail.com" };
	public static String subject = "Extent Project Report";
	public static String messageBody = "TestMessage";
	public static String attachmentPath = "/Users/debkaransinghania/Downloads/IMG_20240227_191047.jpg";
	public static String attachmentName = "image.jpg";

	private TestConfig() {

	}
}
