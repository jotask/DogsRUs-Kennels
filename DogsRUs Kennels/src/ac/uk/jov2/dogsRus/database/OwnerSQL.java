package ac.uk.jov2.dogsRus.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Owner;
import ac.uk.jov2.dogsRus.database.util.DbUtil;

class OwnerSQL{

	public OwnerSQL(){}

	public Owner getOwner(int id) {
		String sql = "SELECT * from \"owner\" WHERE id='" + id + "';";
		ResultSet rs = DbUtil.queryDB(sql);
		Owner o = null;

		try {
			rs.next();
			int i = Integer.parseInt(rs.getString("id"));
			int idA = Integer.parseInt(rs.getString("id_animal"));
			String name = rs.getString("name");
			String phone = rs.getString("phone");
			o = new Owner(i, idA, name, phone);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

	public void insertOwner(Owner own){
		String sql = "INSERT INTO \"owner\"(id_animal, name,telephone) VALUES(1," + own.getName() + ", " + own.getPhone() + ")";
		DbUtil.queryDB(sql);
	}
	
	public Owner[] getOwners(int idAnimal){
		ArrayList<Owner> o = new ArrayList<Owner>();
		String sql;
		sql = "SELECT * from \"owner\" WHERE id_animal=" + idAnimal + ";";
		ResultSet rs = DbUtil.queryDB(sql);

		try {
			for(int i = 0; i < rs.getRow(); i++){
				o.add(getOwner(i));
			}
		} catch (SQLException e) {
			System.err.println("Error creating the animal");
		}
		
		Owner[] owners = new Owner[o.size()];
		owners = o.toArray(owners);
		return owners;
	}
	
}
