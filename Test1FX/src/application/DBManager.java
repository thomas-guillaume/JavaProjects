package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	public static Connection Connector(){
		try {
			Connection
			connection=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\thoma\\Documents\\Cours\\ESILV\\S07\\Java EE & Frameworks\\TutorialFXDB.sqlite");
			return connection;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}