import java.util.ArrayList;

/**
 * To support an individual dog
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 */
public class Dog extends Animal{

	private static final long serialVersionUID = 8859045667757662983L;
	
	private boolean likesBones;

	/**
	 * Constructor for the dog
	 * @param name The dog's name
	 * @param owners A list of original owners: a copy is made
	 * @param likeBones Does the dog like bones?
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 */
	public Dog(String name, ArrayList<Owner> owners, boolean likeBones, String food, int mealsPerDay) {
		super(name, owners, food, mealsPerDay);
		
		likesBones = likeBones;
		
	}

	/**
	 * Does the dog like bones?
	 * @return true if he does
	 */
	public boolean getLikesBones() {
		return likesBones;
	}

	/**
	 * A basic implementation to just return all the data in string form
	 */
	public String toString() {
		return "Dog name:" + name + "Likes Bones?:" + likesBones
				+ "Original Owner:" + originalOwners + "Favfood:" + favFood
				+ "FoodPerDay:" + foodPerDay;
	}

}
