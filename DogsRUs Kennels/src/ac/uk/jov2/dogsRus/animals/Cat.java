package ac.uk.jov2.dogsRus.animals;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Owner;

/**
 * To support an individual dog
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 */
public class Cat extends Animal{

	private static final long serialVersionUID = 2304866428860873200L;

	/**
	 * Constructor for the cat
	 * @param name The cat's name
	 * @param owners A list of original owners: a copy is made
	 * @param likeBones Does the cat like bones?
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 */
	public Cat(String name, ArrayList<Owner> owners, String food, int mealsPerDay) {
		super(name, owners, food, mealsPerDay);
				
	}

	/**
	 * A basic implementation to just return all the data in string form
	 */
	public String toString() {
		return "Cat name:" + name + "Original Owner:" + originalOwners + "Favfood:" + favFood
				+ "FoodPerDay:" + foodPerDay;
	}

}
