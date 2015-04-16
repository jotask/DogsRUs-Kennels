package ac.uk.jov2.dogsRus.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	
	Connection conn;
	
	public CreateDB(){

		DataBaseUtil.loadDriver();
		
		try {
			conn = DriverManager.getConnection(DataBaseUtil.URL + DataBaseUtil.DB_FILE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		createTables();
		
	}
	
	private void createTables(){
		String sql;
		sql = "CREATE TABLE \"kennel\" ( id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, name VARCHAR(256), size INTEGER);";
		update(sql);
		sql = "CREATE TABLE \"owner\" ( id INTEGER IDENTITY, id_animal INTEGER, name VARCHAR(256), phone INTEGER);";
		update(sql);
		sql = "CREATE TABLE \"animal\" ( id INTEGER IDENTITY, id_kennel INTEGER, type VARCHAR(256), name VARCHAR(256));";
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
	}

}
