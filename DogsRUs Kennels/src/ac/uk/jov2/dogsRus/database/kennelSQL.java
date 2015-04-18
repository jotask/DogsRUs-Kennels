package ac.uk.jov2.dogsRus.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ac.uk.jov2.dogsRus.Kennel;
import ac.uk.jov2.dogsRus.database.util.ConnectionFactory;
import ac.uk.jov2.dogsRus.database.util.DbUtil;

class KennelSQL{
	
    private Connection connection;
    private Statement statement;
    
    public KennelSQL(){}
    
   public Kennel getKennel(int idK) throws SQLException {
       String query = "SELECT * FROM kennel WHERE id=" + idK;
       ResultSet rs = null;
       Kennel k = null;
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
       return k;
   }
    
	/*
	public void insertKennel(Kennel k){
		openConnection();
		String sql;
		sql = "INSERT INTO \"kennel\" (name,size) VALUES ('" + k.getName() + "', '" + k.getCapacity() + "');";
		update(sql);
	}
	
	public void deleteKennel(int id){
		openConnection();
		String sql;
		sql = "DELETE FROM \"kennel\" WHERE id='"+ id +"';";
		update(sql);
	}
	
	public Kennel getKennel(int id){
		openConnection();
		Kennel k = null;
		String sql;
		sql = "SELECT * from \"kennel\" WHERE id='" + id + "';";
		ResultSet r = query(sql);
		try {
			r.next();
			int i = Integer.parseInt(r.getString("id"));
			String n = r.getString("name");
			int size = Integer.parseInt(r.getString("size"));
			k = new Kennel(i, n, size);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return k;
	}
	
	public void setKennelName(int id, String newName){
		openConnection();
		String sql;
		sql = "UPDATE \"kennel\" SET name='" + newName + "' WHERE id='" + id + "';";
		query(sql);
	}
	
	public int knowAnimalsInKennel(int id){
		openConnection();
		String sql;
		sql = "SELECT type from \"animal\" WHERE ID_KENNEL='" + id + "';";
		ResultSet rs = query(sql);
		
		int rowCount = getRowCount(rs);
		
		return rowCount;
	}
	
	public int getKennelCapacity(int id){
		openConnection();
		String sql;
		sql = "SELECT size from \"kennel\" WHERE id='" + id + "';";
		ResultSet rs = query(sql);
		
		int r = -1;
		
		try {
			r = Integer.parseInt(rs.getString("size"));
		} catch (NumberFormatException | SQLException e) {
			System.err.println("Error trying to know the size in the table Kennel");
		}
		
		return r;
	}
	
	public void getKennelName(int id, String newName){
		openConnection();
		String sql;
		sql = "UPDATE \"kennel\" SET name='" + newName + "' WHERE id='" + id + "';";
		query(sql);
	}
	
	public ArrayList<Kennel> allKennels(){
		openConnection();
		ArrayList<Kennel> k = new ArrayList<Kennel>();
		String sql;
		sql = "SELECT * from \"kennel\";";
		ResultSet r = query(sql);
		
		try {
			while(r.next()){
				int id = Integer.parseInt(r.getString("id"));
				String name = r.getString("name");
				int size = Integer.parseInt(r.getString("size"));
				Kennel tmp = new Kennel(id, name, size);
				k.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return k;
	}
	
//	public Animal searchPet(int idKennel,  String name){
//		openConnection();
//		String sql;
//		sql = "SELECT id from \"animal\" WHERE id_kennel=" + idKennel + "AND name=" + name + ";";
//		ResultSet r = query(sql);
//		
//		Animal result = null;
////		try {
////			// TODO
////			//result = createAnimal(r.getInt("id"));
////		} catch (SQLException e) {
////			System.err.println("Error creating the animal");
////		}
////		return result;
//	}

	public Animal[] obtainAllAnimalsInKennel(int idKennel){
		
		// FIXME doesn't work if the sql is empty
		
		openConnection();
		ArrayList<Animal> anims = new ArrayList<Animal>();
		
		String sql;
		sql = "SELECT * from \"animal\" WHERE id_kennel=" + idKennel + ";";
		ResultSet r = query(sql);
		System.out.println(r);
		try {
			for(int i = 0; i < r.getRow(); i++){
				//anims.add(createAnimal(i));
				// TODO
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Animal[] result = new Animal[anims.size()];
		result = anims.toArray(result);
		return result;
	}

	public void setKennelCapacity(int id, int cap) {
		openConnection();
		String sql;
		sql = "UPDATE \"kennel\" SET size='" + cap + "' WHERE id='" + id + "';";
		query(sql);
	}

	public void addAnimal(Animal a) {
		if(a instanceof Dog){
			System.out.println("is a dog");
		}else if(a instanceof Cat){
			System.out.println("is a cat");
		}
		
	}
	*/
}
