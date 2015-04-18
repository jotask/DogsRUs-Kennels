package ac.uk.jov2.dogsRus.database.util;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class DbUtil {
	
	private static Connection conn;
	private static Statement statement;
	
	public static ResultSet queryDB(String sql){
		ResultSet rs = null;
		conn = ConnectionFactory.getConnection();
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("Error on the SQL comand: " + sql);
		}
		
		return rs;
	}
	
	public static void updateDB(String sql){
		conn = ConnectionFactory.getConnection();
		try {
			statement = conn.createStatement();
			statement.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("Error on the SQL comand: " + sql);
		}
	}
 
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                /*log or print or ignore*/
            }
        }
    }
 
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                /*log or print or ignore*/
            }
        }
    }
 
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                /*log or print or ignore*/
            }
        }
    }
    
    public static int getRowCount(ResultSet resultSet) {
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
	
	public static int knowID(String table, String name){
		String sql = "SELECT id from \"" + table + "\" WHERE name='" + name + "';";
		ResultSet rs = DbUtil.queryDB(sql);
		
		int r = -1;
		
		try {
			r = Integer.parseInt(rs.getString("id"));
		} catch (NumberFormatException | SQLException e) {
			System.err.println("Error trying to know the ID in the table " + table + " on the name field: " + name);
		}
		
		return r;
	}
}