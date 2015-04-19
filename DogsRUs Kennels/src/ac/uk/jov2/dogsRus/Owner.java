package ac.uk.jov2.dogsRus;

import java.io.Serializable;

/**
 * 
 * @author Lynda Thomas, Chris Loftus and Jose Vives
 * @version 1.2 (April 19th 2015)
 * 
 */
public class Owner implements Serializable{

	private static final long serialVersionUID = -1650996327202128911L;
	private String name;
	private String phone;
	
	/**
	 * Constructor for the owner
	 * @param n The owner name
	 * @param p The owner telephone
	 */
	public Owner(String n, String p){
		name = n;
		phone = p;
	}

	/**
	 * Know the name of the owner
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change the name of the owner
	 * @param name the new name for the owner
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * Know the phone of this owner
	 * @return the telephone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Change the telephone number of the owner
	 * @param phone the new telephone nomber
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * Equals method compare two object for see if they are the same
	 * @param the other object to compare
	 */
	public boolean equals(Object obj) {
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

	/**
	 * Convert all the information of the owner to a string
	 */
	public String toString() {
		return name + " " + phone;
	}

}
