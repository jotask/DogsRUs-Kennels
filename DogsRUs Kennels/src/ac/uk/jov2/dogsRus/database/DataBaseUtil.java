package ac.uk.jov2.dogsRus.database;

//import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Title: Testdb Description: simple hello world db example of a standalone
 * persistent db application
 *
 * every time it runs it adds four more rows to sample_table it does a query and
 * prints the results to standard out
 *
 * Author: Karl Meissner karl@meissnersd.com
 */
public class DataBaseUtil {
	
	//Connection c = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "sa", "");

	Connection conn; // our connnection to the db - presist for life of program
	
	static String DB_FILE = "dogsRus";

	static final String DRIVER = "org.hsqldb.jdbcDriver";
	static final String PATH = "db\\";
	static final String URL = "jdbc:hsqldb:file:" + PATH;
	

	public DataBaseUtil(){
		// TODO delete deleteDB method
		//deleteDB();
		// Load the HSQL Database Engine JDBC driver
		// hsqldb.jar should be in the class path or made part of the current
		// jar
		

		// connect to the database. This will load the db files and start the
		// database if it is not alread running.
		// db_file_name_prefix is used to open or create files that hold the
		// state
		// of the db.
		// It can contain directory names relative to the
		// current working directory
		
		loadDriver();
		
		try {
			createConnection();
		} catch (SQLException | org.hsqldb.HsqlException e) {
			System.out.println("Not exist the DataBase. Creating a new one.");
			new CreateDB();
		}finally{
			try {
				createConnection();
			} catch ( SQLException | org.hsqldb.HsqlException e) {
				e.printStackTrace();
				System.out.println("Some big error ocurred. Please contact me: jov2@aber.ac.uk");
				return;
			}
		}
	}

	static void loadDriver() {
		try {
			Class.forName(DRIVER);
		}
		catch (Exception e) {
			System.out.println("Problem loading JDBC driver: " + e);
			throw new IllegalStateException("HSQLDB driver couldn't be loadded.");
		}
	}
	
//	private void deleteDB(){
//		try {
//			File f1 = new File(DB_FILE+".properties");
//			f1.delete();
//			File f2 =  new File(DB_FILE+".script");
//			f2.delete();
//		} catch (Exception e) {
//			System.err.println("Error deleting files");
//		}
//	}
	
	void createConnection() throws SQLException, org.hsqldb.HsqlException{
		conn = DriverManager.getConnection(URL + DB_FILE +";ifexists=true");
	}

	public void shutdown(){
		try {
			Statement st = conn.createStatement();
		
			// db writes out to files and performs clean shuts down
			// otherwise there will be an unclean shutdown
			// when program ends
			st.execute("SHUTDOWN");
			conn.close(); // if there are no other open connection
		} catch (SQLException ex3) {
			ex3.printStackTrace();
		}
	}

	// use for SQL command SELECT
	public synchronized ResultSet query(String expression) {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement(); // statement objects can be reused with
			// repeated calls to execute but we
			// choose to make a new one each time
			rs = st.executeQuery(expression); // run the query
			// do something with the result set.
			//dump(rs);
			st.close();
			// NOTE!! if you close a statement the associated ResultSet
			// is
			// closed too
			// so you should copy the contents to some other object.
			// the result set is invalidated also if you recycle an Statement
			// and try to execute some other query before the result set has been
			// completely examined.
		} catch (SQLException ex3) {
			ex3.printStackTrace();
		}
		closeConnection();
		return rs;
	}

	// use for SQL commands CREATE, DROP, INSERT and UPDATE
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
		closeConnection();
	}

	public static void dump(ResultSet rs) throws SQLException {

		// the order of the rows in a cursor
		// are implementation dependent unless you use the SQL ORDER statement
		ResultSetMetaData meta = rs.getMetaData();
		int colmax = meta.getColumnCount();
		int i;
		Object o = null;

		// the result set is a cursor into the data. You can only
		// point to one row at a time
		// assume we are pointing to BEFORE the first row
		// rs.next() points to next row and returns true
		// or false if there is no next row, which breaks the loop
		for (; rs.next();) {
			for (i = 0; i < colmax; ++i) {
				o = rs.getObject(i + 1); // Is SQL the first column is indexed

				// with 1 not 0
				System.out.print(o.toString() + " ");
			}

			System.out.println(" ");
		}
	}
	
	protected void openConnection(){
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL + DB_FILE +"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error trying to connect to the database");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Driver not found");
		}
	}
	private void closeConnection(){
		Statement sql;
		try {
			sql = conn.createStatement();
			sql.execute("SHUTDOWN");
			sql.close();
		} catch (SQLException e) {
			System.err.println("Error trying close the connection. DataBaseUtil.199");
		}
	}
}