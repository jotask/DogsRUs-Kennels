package ac.uk.jov2.dogsRus.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ac.uk.jov2.dogsRus.Owner;
import ac.uk.jov2.dogsRus.database.util.ConnectionFactory;
import ac.uk.jov2.dogsRus.database.util.DbUtil;

class OwnerSQL{
	
    private Connection connection;
    private Statement statement;
    
    public OwnerSQL(){}
    
   public Owner getOwner(int idO) throws SQLException {
       String query = "SELECT * FROM owner WHERE id=" + idO;
       ResultSet rs = null;
       Owner o = null;
       try {
           connection = ConnectionFactory.getConnection();
           statement = connection.createStatement();
           rs = statement.executeQuery(query);
//           . . . .
//           . . . .
       } finally {
           DbUtil.close(rs);
           DbUtil.close(statement);
           DbUtil.close(connection);
       }
       return o;
   }
	
    /*
	public void insertOwner(Owner own){
		openConnection();
		String sql;
		sql = "INSERT INTO owner(id_animal, name,telephone) VALUES(1," + own.getName() + ", " + own.getPhone() + ")";
		update(sql);
	}
	
	private Owner createOwner(int id){
		openConnection();
		String sql;
		sql = "SELECT * from \"owner\" WHERE idAnimal=" + id + ";";
		ResultSet r = query(sql);
		
		Owner result = null;
		
		int i = -1;
		String name = null;
		String phone = null;
		try {
			i = r.getInt("id");
			name = r.getString("name");
			phone = r.getString("phone");
		} catch (SQLException e1) {
			System.err.println("Error getting the parameter for the owner. DataBase.176");
		}
		
		result = new Owner(name, phone);

		result.setId(i);
		
		return result;
		
	}
	
	public Owner[] getOwners(int idAnimal){
		ArrayList<Owner> o = new ArrayList<Owner>();
		openConnection();
		String sql;
		sql = "SELECT id from \"owner\" WHERE id_animal=" + idAnimal + ";";
		ResultSet r = query(sql);

		try {
			for(int i = 0; i < r.getRow(); i++){
				o.add(createOwner(i));
			}
		} catch (SQLException e) {
			System.err.println("Error creating the animal");
		}
		
		Owner[] owners = new Owner[o.size()];
		owners = o.toArray(owners);
		return owners;
	}*/
}
