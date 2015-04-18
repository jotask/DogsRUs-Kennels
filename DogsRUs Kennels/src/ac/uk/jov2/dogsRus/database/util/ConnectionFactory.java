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

    //private constructor
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
   
/*
	static void loadDriver() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Problem loading JDBC driver: " + e);
			throw new IllegalStateException(
					"HSQLDB driver couldn't be loadded. Class not found");
		}
	}

	void createConnection() throws SQLException, org.hsqldb.HsqlException {
		conn = DriverManager.getConnection(URL + DB_FILE + ";ifexists=true");
	}

	public void shutdown() {
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
			// dump(rs);
			st.close();
			// NOTE!! if you close a statement the associated ResultSet
			// is
			// closed too
			// so you should copy the contents to some other object.
			// the result set is invalidated also if you recycle an Statement
			// and try to execute some other query before the result set has
			// been
			// completely examined.
		} catch (SQLException ex3) {
			ex3.printStackTrace();
		}
		return rs;
	}

	// use for SQL commands CREATE, DROP, INSERT and UPDATE
	public synchronized void update(String expression) {
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

	protected void openConnection() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL + DB_FILE + "");
		} catch (SQLException e) {
			System.err.println("Error trying to connect to the database");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Driver not found");
		}
	}
	
	protected int getRowCount(ResultSet resultSet) {
	    if (resultSet == null) {
	        return 0;
	    }
	    int count = 0;
	    try {
			while(resultSet.next()){
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return count;
	}
	
	public int knowID(String table, String name){
		openConnection();
		String sql;
		sql = "SELECT id from \"" + table + "\" WHERE name='" + name + "';";
		ResultSet rs = query(sql);
		
		int r = -1;
		
		try {
			r = Integer.parseInt(rs.getString("id"));
		} catch (NumberFormatException | SQLException e) {
			System.err.println("Error trying to know the ID in the table " + table + " on the name field: " + name);
		}
		
		return r;
	}
	*/
}