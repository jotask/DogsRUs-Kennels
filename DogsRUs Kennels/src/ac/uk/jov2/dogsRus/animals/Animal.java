package ac.uk.jov2.dogsRus.animals;

import java.io.Serializable;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Owner;

/**
 * To support an individual animal
 * @author Chris Loftus and Jose Vives
 * @version 1.2 (19th April 2015)
 */
public class Animal implements Serializable{
	
	private static final long serialVersionUID = 2364837888974161274L;
	
	public String name;
	public ArrayList<Owner> originalOwners;
	public String favFood;
	public int foodPerDay;
	public boolean canShareRun;


	/**
	 * Constructor for the SuperClass Animals
	 * @param name The animal's name
	 * @param owners A list of original owners: a copy is made
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 * @param canShare Know if the animal can share runs
	 */
	public Animal(String n, ArrayList<Owner> owners, String food, int mealsPerDay, boolean canShare){

		name = n;
		originalOwners = new ArrayList<Owner>();
		
		// We make a true copy of the owners ArrayList to make sure that we
		// don't break encapsulation: i.e. don't share object references with
		// other code
		for(Owner o: owners){
			Owner copy = new Owner(o.getName(), o.getPhone());
			originalOwners.add(copy);
		}
		favFood = food;
		foodPerDay = mealsPerDay;
		canShareRun = canShare;
	}
	
	/**
	 * Return the name of the animal
	 * @return the name of the animal
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Change the name of the animal
	 * @param newName
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	/**
	 * Returns a copy of the original owners
	 * @return A copy of the original owners as an array
	 */
	public Owner[] getOriginalOwners(){
		Owner[] result = new Owner[originalOwners.size()];
		result = originalOwners.toArray(result);
		return result;
	}
	
	/**
	 * How many times a day to feed the animal
	 * @param feeds The number of feeds per day
	 */
	public void setFeedsPerDay(int feeds){
		foodPerDay = feeds;
	}
	
	/**
	 * The number of feeds per day the animal is fed
	 * @return The number of feeds per day
	 */
	public int getFeedsPerDay(){
		return foodPerDay;
	}
	
	/**
	 * What's his favourite food?
	 * @param food The food he likes
	 */
	public void setFavouriteFood(String food){
		favFood = food;
	}
	
	/**
	 * The food the animal likes to eat
	 * @return The food 
	 */
	public String getFavouriteFood(){
		return favFood;
	}

	/**
	 * Note that this only compares equality based on a
	 * cat's name.
	 * @param The other dog to compare against.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * To string method
	 * @return A string with all the information from the animal
	 */
	public String toString() {
		return "Animal [name=" + name + ", originalOwners=" + originalOwners
				+ ", favFood=" + favFood + ", foodPerDay=" + foodPerDay
				+ ", canShareRun=" + canShareRun + "]";
	}

}
