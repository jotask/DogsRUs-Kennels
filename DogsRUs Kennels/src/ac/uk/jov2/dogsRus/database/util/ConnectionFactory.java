package ac.uk.jov2.dogsRus.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    //static reference to itself
    private static ConnectionFactory instance = new ConnectionFactory();

	static String DB_FILE = "dogsRus";

	static final String DRIVER = "org.hsqldb.jdbcDriver";
	static final String PATH = "db\\";
	static final String URL = "jdbc:hsqldb:file:" + PATH;

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
   private Connection createConnection() {
       Connection connection = null;
       try {
           connection = DriverManager.getConnection(URL);
       } catch (SQLException e) {
           System.out.println("ERROR: Unable to Connect to Database.");
       }
       return connection;
   }   
    
   public static Connection getConnection() {
       return instance.createConnection();
   }
   
}