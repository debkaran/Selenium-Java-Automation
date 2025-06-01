package databasetesting;

public class TestDBConnection {

	private TestDBConnection() {
	}

	public static void main(String[] args) {
		
		DBManager.setMySQLDBConnection();
	}
}
