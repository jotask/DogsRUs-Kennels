package ac.uk.jov2.dogsRus.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Kennel;
import ac.uk.jov2.dogsRus.Owner;
import ac.uk.jov2.dogsRus.animals.Animal;
import ac.uk.jov2.dogsRus.animals.Cat;
import ac.uk.jov2.dogsRus.animals.Dog;

public class DataBase extends DataBaseUtil{
	
	public DataBase(){
		super();
	}
	
	public void insertOwner(Owner own){
		String sql;
		sql = "INSERT INTO owner(id_animal, name,telephone) VALUES(1," + own.getName() + ", " + own.getPhone() + ")";
		update(sql);
	}
	
	public void insertKennel(Kennel k){
		String sql;
		sql = "INSERT INTO kennel(name,size) VALUES(" + k.getName() + ", " + k.getCapacity() + ")";
		update(sql);
	}
	
	public void insertAnimal(Animal a, int id_kennel){
		String sql;
		sql = "INSERT INTO animal(kennel, type, name) VALUES(" + id_kennel + ", " + a.getClass().getSimpleName() + ", " + a.getName() + ")";
		update(sql);
	}
	
	public Kennel getKennel(int id){
		Kennel k = null;
		String sql;
		sql = "SELECT * from kennel WHERE id=" + id + ";";
		ResultSet r = query(sql);
		try {
			String name = r.getString("name");
			int size = Integer.parseInt(r.getString("size"));
			k = new Kennel(name, size);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return k;
	}
	
	public Animal searchPet(int idKennel,  String name){
		String sql;
		sql = "SELECT id from animal WHERE id_kennel=" + idKennel + "AND name="+name+";";
		ResultSet r = query(sql);
		
		Animal result = null;
		try {
			result = createAnimal(r.getInt("id"));
		} catch (SQLException e) {
			System.err.println("Error creating the animal");
		}
		
		return result;
	}
	
	private Animal createAnimal(int ido) {
		String sql;
		sql = "SELECT * from animal WHERE id=" + ido + ";";
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
			result = new Cat(name, owners, food, mealsDay);
		}
		result.setId(id);
		
		return result;
	}

	public Animal[] obtainAllAnimalsInKennel(int idKennel){
		ArrayList<Animal> anims = new ArrayList<Animal>();
		
		String sql;
		sql = "SELECT * from animal WHERE id_kennel=" + idKennel + ";";
		ResultSet r = query(sql);
		try {
			for(int i = 0; i < r.getRow(); i++){
				anims.add(createAnimal(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Animal[] result = new Animal[anims.size()];
		result = anims.toArray(result);
		return result;
	}

}
