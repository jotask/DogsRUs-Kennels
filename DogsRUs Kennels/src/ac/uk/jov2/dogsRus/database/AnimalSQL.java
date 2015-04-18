package ac.uk.jov2.dogsRus.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ac.uk.jov2.dogsRus.animals.Animal;
import ac.uk.jov2.dogsRus.database.util.ConnectionFactory;
import ac.uk.jov2.dogsRus.database.util.DbUtil;

class AnimalSQL{
	
    private Connection connection;
    private Statement statement;
    
    public AnimalSQL(){}
    
   public Animal getAnimal(int idA) throws SQLException {
       String query = "SELECT * FROM owner WHERE id=" + idA;
       ResultSet rs = null;
       Animal a = null;
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
       return a;
   }

	/*
	private Animal createAnimal(int idid) {
		openConnection();
		String sql;
		sql = "SELECT * from animal WHERE id=" + idid + ";";
		ResultSet r = query(sql);
		
		Animal result = null;
		
		String type = null;
		int id = -1;
		String name = null;
		ArrayList<Owner> owners = null;
		boolean likeBones = false;
		String food = null;
		int mealsDay = -1;
		try {
			type = r.getString("type");
			id = r.getInt("id");
			name = r.getString("name");
			likeBones = r.getBoolean("likebones");
			food = r.getString("food");
			mealsDay = r.getInt("mealsDay");
		} catch (SQLException e1) {
			System.err.println("Error getting the parameter for the animal. DataBase.70");
		}
		
		if(type.equals("dog")){
			result = new Dog(name, owners, likeBones, food, mealsDay);
			
		}else if(type.equals("cat")){
			result = new Cat(name, owners, food, mealsDay, false);
		}
		result.setId(id);
		
		return result;
	}
	
	public void insertAnimal(Animal a, int id_kennel){
		openConnection();
		String sql;
		if(a instanceof Dog){
		sql = "INSERT INTO animal(idKennel, type, name, foodPerDay, favFood, canShareRun, needTakeToWalk, likeBones, needPetting) "
		+ "VALUES(" + id_kennel + ", " + a.getClass().getSimpleName() + ", " + a.getName() + ", " + a.getFeedsPerDay() + ", " + a.getFavouriteFood() + ", , " +  + ")";
		}
		update(sql);
	}
	*/
}
