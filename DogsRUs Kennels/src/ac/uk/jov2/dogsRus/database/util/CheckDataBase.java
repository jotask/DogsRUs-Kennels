package ac.uk.jov2.dogsRus.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckDataBase{
	
	private static Connection conn;

	public CheckDataBase(){
		
		try {
			conn = DriverManager.getConnection(ConnectionFactory.URL + ";ifexists=true");
		} catch (SQLException e) {
			System.out.println("Doesn't exist the DataBase. Creating a new one");
			try {
				conn = DriverManager.getConnection(ConnectionFactory.URL + ";");
				createTables();
				System.out.println("DataBaseCreated Succesfully!");
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				System.err.println("Some big error ocurred, can't connect to the database. Please contact to me: jov2@aber.ac.uk");
				System.exit(0);
			}
		}

	}
	
	private void createTables(){
		String sql;
		sql = "CREATE TABLE \"kennel\" ( id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, name VARCHAR(256), size INTEGER);";
		update(sql);
		sql = "CREATE TABLE \"owner\" ( id INTEGER IDENTITY, id_animal INTEGER, name VARCHAR(256), phone VARCHAR(25));";
		update(sql);
		sql = "CREATE TABLE \"animal\" ( id INTEGER IDENTITY, id_kennel INTEGER, type VARCHAR(256), name VARCHAR(256), favFood VARCHAR(256), foodPerDay VARCHAR(256), canShareRun BOOLEAN, needTakeToWalk BOOLEAN, likeBones BOOLEAN, needsPetting BOOLEAN);";
		update(sql);
	}
	
	private synchronized void update(String expression){
		try {
			Statement st = null;
			st = conn.createStatement(); // statements
			int i = st.executeUpdate(expression); // run the query
			if (i == -1) {
				System.out.println("db error : " + expression);
			}
		
			st.close();
		} catch (SQLException ex3) {
			ex3.printStackTrace();
		}
	}

}
