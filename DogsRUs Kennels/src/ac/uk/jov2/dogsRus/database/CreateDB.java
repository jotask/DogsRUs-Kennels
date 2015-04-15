package ac.uk.jov2.dogsRus.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	
	Connection conn;
	
	public CreateDB(){

		try {
			Class.forName(DataBase.DRIVER);
			conn = DriverManager.getConnection(DataBase.URL + DataBase.db_file_name_prefix);
			createTables();
			System.out.println("Creating a DB");
		} catch (SQLException e) {
			System.out.println("SQLException. CreateDB.12");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error on Driver. Class not Found Exception");
			return;
		}
	}
	
	private void createTables(){
		String sql;
		sql = "CREATE TABLE kennel ( id INTEGER IDENTITY, name VARCHAR(256), size INTEGER)";
		update(sql);
		sql = "CREATE TABLE owner ( id INTEGER IDENTITY, id_animal INTEGER, name VARCHAR(256), telephone INTEGER)";
		update(sql);
		sql = "CREATE TABLE animal ( id INTEGER IDENTITY, id_kennel INTEGER, type VARCHAR(256), name VARCHAR(256))";
		update(sql);
	}
	
	public synchronized void update(String expression){
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
	} // void update()

}
