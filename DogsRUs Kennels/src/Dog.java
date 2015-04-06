import java.util.ArrayList;

/**
 * To support an individual dog
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 */
public class Dog extends Animals{

	private ArrayList<Owner> originalOwners;
	private boolean likesBones;
	private String dogName;
	private String favFood;
	private int foodPerDay;

	/**
	 * Constructor for the dog
	 * @param name The dog's name
	 * @param owners A list of original owners: a copy is made
	 * @param likeBones Does the dog like bones?
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 */
	public Dog(String name, ArrayList<Owner> owners, boolean likeBones, String food, int mealsPerDay) {
		super();
		
		dogName = name;
		originalOwners = new ArrayList<Owner>();
		
		// We make a true copy of the owners ArrayList to make sure that we
		// don't break encapsulation: i.e. don't share object references with
		// other code
		for(Owner o: owners){
			Owner copy = new Owner(o.getName(), o.getPhone());
			originalOwners.add(copy);
		}
		this.likesBones = likeBones;
		this.favFood = food;
		this.foodPerDay = mealsPerDay;
	}

	/**
	 * Note that this only compares equality based on a
	 * dog's name.
	 * @param The other dog to compare against.
	 */
	@Override
	public boolean equals(Object obj) { // Generated by Eclipse to be more robust
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dog other = (Dog) obj;
		if (dogName == null) {
			if (other.dogName != null)
				return false;
		} else if (!dogName.equals(other.dogName))
			return false;
		return true;
	}

	/**
	 * A basic implementation to just return all the data in string form
	 */
	public String toString() {
		return "Dog name:" + dogName + "Likes Bones?:" + likesBones
				+ "Original Owner:" + originalOwners + "Favfood:" + favFood
				+ "FoodPerDay:" + foodPerDay;
	}

}
