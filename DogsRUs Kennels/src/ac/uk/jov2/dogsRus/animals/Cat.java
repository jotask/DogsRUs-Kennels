package ac.uk.jov2.dogsRus.animals;

import java.util.ArrayList;

import ac.uk.jov2.dogsRus.Owner;

/**
 * To support an individual Cat
 * @author Chris Loftus and Jose Vives
 * @version 1.2 (19th April 2015)
 */
public class Cat extends Animal{

	private static final long serialVersionUID = 2304866428860873200L;
	
	private boolean needPetting;

	/**
	 * Constructor for the cat
	 * @param name The cat's name
	 * @param owners A list of original owners: a copy is made
	 * @param likeBones Does the cat like bones?
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 * @param canShare Know if the animal can share runs
	 * @param canShare Know if the animal needs daily petting
	 */
	public Cat(String name, ArrayList<Owner> owners, String food, int mealsPerDay, boolean canShare, boolean needPett) {
		super(name, owners, food, mealsPerDay, canShare);
		needPetting = needPett;
	}

	/**
	 * Know if need daiy pettin
	 * @return A boolean for know if this cat needs daily petting
	 */
	public boolean isNeedPetting() {
		return needPetting;
	}

	/**
	 * Change if need this cat daily petting
	 * @param needPetting For set thi
	 */
	public void setNeedPetting(boolean needPeting) {
		needPetting = needPeting;
	}
	
	/**
	 * To string method
	 * @return A string with all the information from the cat
	 */
	public String toString() {
		return "Cat with name: " + name + ", Original Owner/s: " + originalOwners + ", Favfood: " + favFood
				+ ", FoodPerDay: " + foodPerDay + ", Need Daily Petting: " + needPetting + ", Can Share a Run: " + canShareRun;
	}

}
