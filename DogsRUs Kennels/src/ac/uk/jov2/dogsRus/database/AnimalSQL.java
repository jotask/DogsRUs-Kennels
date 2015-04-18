package ac.uk.jov2.dogsRus.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Owner;
import ac.uk.jov2.dogsRus.animals.Animal;
import ac.uk.jov2.dogsRus.animals.Cat;
import ac.uk.jov2.dogsRus.animals.Dog;
import ac.uk.jov2.dogsRus.database.util.DbUtil;

class AnimalSQL{

	public AnimalSQL(){}

	public static Animal getAnimal(int i) {
		String sql = "SELECT * FROM owner WHERE id=" + i + ";";
		ResultSet rs = DbUtil.queryDB(sql);
		Animal animal = null;
		
		String type = null;
		int id = -1;
		String name = null;
		ArrayList<Owner> owners = null;
		boolean likeBones = false;
		String food = null;
		int mealsDay = -1;
		try {
			type = rs.getString("type");
			id = rs.getInt("id");
			name = rs.getString("name");
			likeBones = rs.getBoolean("likebones");
			food = rs.getString("food");
			mealsDay = rs.getInt("mealsDay");
		} catch (SQLException e1) {
			System.err.println("Error getting the parameter for the animal. DataBase.70");
		}
		
		if(type.equals("dog")){
			animal = new Dog(name, owners, likeBones, food, mealsDay);
			
		}else if(type.equals("cat")){
			animal = new Cat(name, owners, food, mealsDay, false, false);
		}
		animal.setId(id);
		
		return animal;
	}
	
	public void insertAnimal(Animal a, int id_kennel){
		
		String sql = null;
		if(a instanceof Dog){
			sql = "INSERT INTO animal(idKennel, type, name, foodPerDay, favFood, canShareRun, needTakeToWalk, likeBones, needPetting) "
			+ "VALUES(" + id_kennel + ", " + a.getClass().getSimpleName() + ", " + a.getName() + ", " + a.getFeedsPerDay() + ", " + a.getFavouriteFood() + ", , );";
		}else if (a instanceof Cat){
			sql = "INSERT INTO animal(idKennel, type, name, foodPerDay, favFood, canShareRun, needTakeToWalk, likeBones, needPetting) "
			+ "VALUES(" + id_kennel + ", " + a.getClass().getSimpleName() + ", " + a.getName() + ", " + a.getFeedsPerDay() + ", " + a.getFavouriteFood() + ", , );";
		}
		DbUtil.queryDB(sql);
	}
	
}
