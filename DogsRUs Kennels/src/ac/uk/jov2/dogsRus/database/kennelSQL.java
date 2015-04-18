package ac.uk.jov2.dogsRus.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Kennel;
import ac.uk.jov2.dogsRus.animals.Animal;
import ac.uk.jov2.dogsRus.animals.Cat;
import ac.uk.jov2.dogsRus.animals.Dog;
import ac.uk.jov2.dogsRus.database.util.DbUtil;

class KennelSQL {

	public KennelSQL() {}

	public Kennel getKennel(int id) {
		String sql = "SELECT * from \"kennel\" WHERE id='" + id + "';";
		ResultSet rs = DbUtil.queryDB(sql);
		Kennel k = null;

		try {
			rs.next();
			int i = Integer.parseInt(rs.getString("id"));
			String n = rs.getString("name");
			int size = Integer.parseInt(rs.getString("size"));
			k = new Kennel(i, n, size);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return k;
	}

	
	public void insertKennel(Kennel k){
		String sql = "INSERT INTO \"kennel\" (name,size) VALUES ('" + k.getName() + "', '" + k.getCapacity() + "');";
		DbUtil.queryDB(sql);
	}
	
	public void deleteKennel(int id){
		String sql= "DELETE FROM \"kennel\" WHERE id='"+ id +"';";
		DbUtil.queryDB(sql);
	}
	
	public void setKennelName(int id, String newName){
		String sql = "UPDATE \"kennel\" SET name='" + newName + "' WHERE id='" + id + "';";
		DbUtil.queryDB(sql);
	}
	
	public int getKennelCapacity(int id){
		String sql = "SELECT size from \"kennel\" WHERE id='" + id + "';";
		ResultSet rs = DbUtil.queryDB(sql);
		
		int r = -1;
		
		try {
		r = Integer.parseInt(rs.getString("size"));
		} catch (NumberFormatException | SQLException e) {
		System.err.println("Error trying to know the size in the table Kennel");
		}
		
		return r;
	}
	
	public void getKennelName(int id, String newName){
		String sql = "UPDATE \"kennel\" SET name='" + newName + "' WHERE id='" + id + "';";
		DbUtil.queryDB(sql);
	}
	
	public ArrayList<Kennel> allKennels(){
		ArrayList<Kennel> k = new ArrayList<Kennel>();
		String sql = "SELECT * from \"kennel\";";
		ResultSet rs = DbUtil.queryDB(sql);
			
		try {
			while(rs.next()){
				int id = Integer.parseInt(rs.getString("id"));
				String name = rs.getString("name");
				int size = Integer.parseInt(rs.getString("size"));
				Kennel tmp = new Kennel(id, name, size);
				k.add(tmp);
			}
		} catch (SQLException e) {
			System.err.println("Error");
		}
		
		return k;
	}

	public Animal searchPet(int idKennel, String name){
		String sql = "SELECT id from \"animal\" WHERE id_kennel=" + idKennel + "AND name=" + name + ";"; 
		ResultSet rs = DbUtil.queryDB(sql);
		Animal anim = null;
		
		try {
			anim = AnimalSQL.getAnimal(rs.getInt("id")); 
		} catch (SQLException e) { 
			System.err.println("Error creating the animal");
		}
		return anim;
	}

	public Animal[] obtainAllAnimalsInKennel(int idKennel){
		ArrayList<Animal> anims = new ArrayList<Animal>();
		String sql = "SELECT id from \"animal\" WHERE id_kennel=" + idKennel + ";";
		ResultSet rs = DbUtil.queryDB(sql);

		try {
			for(int i = 0; i < rs.getRow(); i++){
				anims.add(AnimalSQL.getAnimal(i));
			}
		} catch (SQLException e) {
			System.err.println("Error");
		}
		
		Animal[] result = new Animal[anims.size()];
		result = anims.toArray(result);
		return result;
	}
  
	public void setKennelCapacity(int id, int cap) {
		String sql = "UPDATE \"kennel\" SET size='" + cap + "' WHERE id='" + id + "';";
		DbUtil.queryDB(sql);
	}

	public void addAnimal(Animal a) {
		if(a instanceof Dog){
			System.out.println("it's a dog");
		}else if(a instanceof Cat){
			System.out.println("it's a cat");
		}
	}
	
}
