package ac.uk.jov2.dogsRus;

/**
 * 
 * @author Lynda Thomas and Chris Loftus
 * @version 1.0 (March 19th 2015)
 *
 */
public class Owner{

	private static int id = -1;
	private String name;
	private String phone;
	
	public Owner(String n, String p){
		name = n;
		phone = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public boolean equals(Object obj) { // Robust version generated by Eclipse (source menu)
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Owner other = (Owner) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + " " + phone;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static void setId(int i) {
		if(id == -1){
			id = i;
		}else{
			System.err.println("Sorry but you can't change the ID");
		}
	}

}
