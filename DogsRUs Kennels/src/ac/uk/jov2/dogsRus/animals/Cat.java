package ac.uk.jov2.dogsRus.animals;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Owner;

/**
 * To support an individual dog
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 */
public class Cat extends Animal{

	private boolean needPetting;
	
	/**
	 * Constructor for the cat
	 * @param name The cat's name
	 * @param owners A list of original owners: a copy is made
	 * @param likeBones Does the cat like bones?
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 */
	public Cat(String name, ArrayList<Owner> owners, String food, int mealsPerDay, boolean canShareRun, boolean nP) {
		super(name, owners, food, mealsPerDay, canShareRun);
		needPetting = nP;
	}

	/**
	 * A basic implementation to just return all the data in string form
	 */
	public String toString() {
		return "It's a Cat with name: " + name + ", Original Owner/s: " + originalOwners + ", Favfood: " + favFood
				+ ", FoodPerDay: " + foodPerDay + ", Need Petting: " + needPetting;
	}

}
