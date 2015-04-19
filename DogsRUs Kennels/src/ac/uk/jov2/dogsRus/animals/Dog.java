package ac.uk.jov2.dogsRus.animals;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Owner;

/**
 * To support an individual Dog
 * @author Chris Loftus and Jose Vives
 * @version 1.2 (19th April 2015)
 */
public class Dog extends Animal{

	private static final long serialVersionUID = 8859045667757662983L;
	
	private boolean likesBones;
	private boolean needTaketoWalk;

	/**
	 * Constructor for the dog
	 * @param name The dog's name
	 * @param owners A list of original owners: a copy is made
	 * @param likeBones Does the dog like bones?
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 * @param canShare Know if the animal can share runs. Always false for dogs
	 * @param needTakeWalk Know if the dog needs take to walk outside
	 */
	public Dog(String name, ArrayList<Owner> owners, boolean likeBones, String food, int mealsPerDay, boolean needTakeWalk) {
		super(name, owners, food, mealsPerDay, false);
		likesBones = likeBones;
		needTaketoWalk = needTakeWalk;
	}

	/**
	 * They need go outside to walk
	 * @return If they need
	 */
	public boolean isNeedTaketoWalk() {
		return needTaketoWalk;
	}
	/**
	 * Change if the dog need go outside to walk
	 * @param needTaketoWalk the changed state
	 */
	public void setNeedTaketoWalk(boolean needTakeWalk) {
		needTaketoWalk = needTakeWalk;
	}

	/**
	 * Change if the dog likes bones or not
	 * @param likesBones 
	 */
	public void setLikesBones(boolean likesBones) {
		this.likesBones = likesBones;
	}

	/**
	 * Does the dog like bones?
	 * @return true if he does
	 */
	public boolean getLikesBones() {
		return likesBones;
	}
	
	/**
	 * To string method
	 * @return A string with all the information from the dog
	 */
	public String toString() {
		return "Dog with name: " + name + ", Likes Bones?: " + likesBones
				+ ", Original Owner/s: " + originalOwners + ", Favfood: " + favFood
				+ ", FoodPerDay: " + foodPerDay + ", The need take to walk: " + needTaketoWalk + ", Can Share a Run: " + canShareRun;
	}

}
