package ac.uk.jov2.dogsRus.animals;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Owner;

/**
 * To support an individual dog
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
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
	 */
	public Dog(String name, ArrayList<Owner> owners, boolean likeBones, String food, int mealsPerDay, boolean needTakeWalk) {
		super(name, owners, food, mealsPerDay, false);
		likesBones = likeBones;
		needTaketoWalk = needTakeWalk;
	}

	public boolean isNeedTaketoWalk() {
		return needTaketoWalk;
	}

	public void setNeedTaketoWalk(boolean needTaketoWalk) {
		this.needTaketoWalk = needTaketoWalk;
	}

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
	 * A basic implementation to just return all the data in string form
	 */
	public String toString() {
		return "Dog with name: " + name + ", Likes Bones?: " + likesBones
				+ ", Original Owner/s: " + originalOwners + ", Favfood: " + favFood
				+ ", FoodPerDay: " + foodPerDay + ", The need take to walk: " + needTaketoWalk + ", Can Share a Run: " + canShareRun;
	}

}
