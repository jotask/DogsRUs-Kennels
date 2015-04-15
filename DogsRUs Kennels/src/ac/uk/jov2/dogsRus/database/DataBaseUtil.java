/* Copyright (c) 2001-2005, The HSQL Development Group
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package ac.uk.jov2.dogsRus.database;

import java.io.File;
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

	Connection conn; // our connnection to the db - presist for life of program
	static String db_file_name_prefix = "dogsRus";

	static final String DRIVER = "org.hsqldb.jdbcDriver";
	static final String URL = "jdbc:hsqldb:";

	// we dont want this garbage collected until we are done
	public DataBaseUtil(){
		deleteDB();
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
					
		try {
			createConnection();
		} catch (SQLException | org.hsqldb.HsqlException e) {
			//e.printStackTrace();
			System.out.println("Not exist the DataBase. Creating a new one.");
			new CreateDB();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.err.println("Error on Driver. Class not Found Exception");
			return;
		}finally{
			try {
				createConnection();
			} catch (ClassNotFoundException | SQLException | org.hsqldb.HsqlException e) {
				e.printStackTrace();
				System.out.println("Some big error ocurred. Please contact me: jov2@aber.ac.uk");
				return;
			}
		}
	}
	
	private void deleteDB(){
		try {
			File f1 = new File(db_file_name_prefix+".properties");
			f1.delete();
			File f2 =  new File(db_file_name_prefix+".script");
			f2.delete();
		} catch (Exception e) {
			System.out.println("Error deleting files");
		}
	}
	
	private void createConnection() throws ClassNotFoundException, SQLException, org.hsqldb.HsqlException{
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL + db_file_name_prefix +";ifexists=true");
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
	} // void update()

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
}