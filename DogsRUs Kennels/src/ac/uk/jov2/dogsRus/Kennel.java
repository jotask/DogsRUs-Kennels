package ac.uk.jov2.dogsRus;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import ac.uk.jov2.dogsRus.animals.Animal;
import ac.uk.jov2.dogsRus.animals.Dog;

/**
 * 
 * To model a Kennel - a collection of dogs
 * 
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 *
 */
public class Kennel implements Serializable{

	private static final long serialVersionUID = 7367152353302086211L;
	private String name;
	private ArrayList<Animal> animals;
	private int nextFreeLocation;
	private int capacity;

	/**
	 * Creates a kennel with a default size 20
	 * 
	 * @param maxNoAnimals
	 *            The capacity of the kennel
	 */
	public Kennel(String name){
		this(name, 20);
	}
	
	/**
	 * Create a kennel
	 * 
	 * @param maxNoDogs
	 *            The capacity of the kennel
	 */
	public Kennel(String n, int maxNoAnimal) {
		nextFreeLocation = 0; // no Dogs in collection at start
		capacity = maxNoAnimal;
		name = n;
		animals = new ArrayList<Animal>(capacity);
	}

	/**
	 * This method sets the value for the name attribute. The purpose of the
	 * attribute is: The name of the kennel e.g. "DogsRUs"
	 * 
	 * @param theName
	 */
	public void setName(String theName) {
		// TODO change the name of the file for when we change the name of the kennel
		String path = DataBase.getPath();
		System.out.println(path);
		File oldfile = new File(path + name);
		File newfile = new File(path + theName);
 
		if(!oldfile.renameTo(newfile)){
			System.err.println("Rename failed");
		}
		name = theName;
	}
	
	/**
	 * Set the size of the kennel
	 * @param capacity The max dogs we can house
	 */
	public void setCapacity(int cap){
		// This should really check to see if we already have dogs
		// in the kennel and reducing the capacity would lead to evictions!
		capacity = cap;
	}
	
	/**
	 * Maximum capacity of the kennels
	 * @return The max size of the kennel
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * This method gets the value for the name attribute. The purpose of the
	 * attribute is: The name of the Kennel e.g. "DogsRUs"
	 * 
	 * @return String The name of the kennel
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method returns the number of dogs in a kennel
	 * 
	 * @return int Current number of dogs in the kennel
	 */
	public int getNumOfAnimals() {
		return nextFreeLocation;
	}

	/**
	 * Enables a user to add a Dog to the Kennel
	 * 
	 * @param theDog
	 *            A new dog to home
	 */
	public void addAnimal(Animal theAnimal) {
		if (nextFreeLocation >= capacity) {
			System.out.println("Sorry kennel is full - cannot add them");
			return;
		}
		// we add in the position indexed by nextFreeLocation
		// This starts at zero
		animals.add(theAnimal);

		// now increment index ready for next one
		nextFreeLocation = nextFreeLocation + 1;
	}

	/**
	 * Enables a user to delete a Animal from the Kennel.
	 * 
	 * @param theAnimal
	 *            The animal to remove
	 */
	public void removeAnimal(String who) {
		Animal which = null;
		// Search for the pet by name
		which = search(who);
		//TODO instanceof operator is considered to be bad OO-design, because it is not scalable
		if (which != null) {
			animals.remove(which); // Requires that Dog has an equals method
			System.out.println("removed " + who);
			nextFreeLocation = nextFreeLocation - 1;
		} else{
			System.err.println("cannot remove - not in kennel");
		}
	}

	/**
	 * @return String showing all the information in the kennel
	 */
	public String toString() {
		String results = "Kennel with name: " + name + " and with a capacity of: " + capacity + " animals contain ";
		if(!animals.isEmpty()){
			results = results + "\n";
			for (Animal d : animals) {
				results = results + d.toString() + "\n";
			}
		}else{
			results = results + "0 animals";
		}
		return results;
	}

	/**
	 * Returns an array of the dogs in the kennels
	 * @return An array of the correct size
	 */
	public Animal[] obtainAllAnimal() {
		Animal[] result = new Animal[animals.size()];
		result = animals.toArray(result);
		return result;
	}

	/**
	 * Only returns those dogs who like bones
	 * @return An array of dogs of the correct size. If no dogs like bones then returns an empty array (size 0)
	 */
	public Animal[] obtainAnimalWhoLikeBones() {
		
		ArrayList<Animal> anims = new ArrayList<Animal>();
		
		for(Animal a: animals){
			
			if(a instanceof Dog) {
				//System.out.println(a.getName()+ "is a dog");
				if(((Dog) a).getLikesBones()){
					anims.add(a);
				}
			}
		}
		
		Animal[] result = new Animal[anims.size()];
		result = anims.toArray(result);
		return result;
	}

	public Animal search(String name) {

		Animal result = null;
		
		for(Animal a: animals){
			if(a.getName().equals(name)){
				System.out.println("We found the pet:");
				result = a;
			}
		}
		return result;
	}

}
