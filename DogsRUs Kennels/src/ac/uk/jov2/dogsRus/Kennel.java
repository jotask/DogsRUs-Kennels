package ac.uk.jov2.dogsRus;

import java.util.ArrayList;

import ac.uk.jov2.dogsRus.animals.Animal;
import ac.uk.jov2.dogsRus.animals.Dog;

/**
 * 
 * To model a Kennel - a collection of animals
 * 
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 *
 */
public class Kennel{

	private static int id = -1;
	private String name;
	private ArrayList<Animal> animals;
	private int nextFreeLocation;
	private int capacity;

	/**
	 * Creates a kennel with a default size 20
	 * @param s 
	 * @param n2 
	 * 
	 * @param maxNoAnimals
	 *            The capacity of the kennel
	 */
	public Kennel(){
		this(-1, "defaultName", 20);
	}
	
	public Kennel(String n, int maxNoAnim) {
		this(-1, n, maxNoAnim);
	}
	
	/**
	 * Create a kennel
	 * 
	 * @param maxNoAnim
	 *            The capacity of the kennel
	 */
	public Kennel(int i, String n, int maxNoAnim) {

		id = i;
		nextFreeLocation = 0;
		capacity = maxNoAnim;
		name= n;
		animals = new ArrayList<Animal>(capacity); // set up default. This can
												// actually be exceeded
												// when using ArrayList but we
												// won't allow that
												// to happen.
	}

	/**
	 * This method sets the value for the name attribute. The purpose of the
	 * attribute is: The name of the kennel e.g. "DogsRUs"
	 * 
	 * @param theName
	 */
	public void setName(String theName) {
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
			System.out.println("Sorry kennel is full - cannot add team");
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
		// Search for the dog by name
		which = search(who);
		//TODO instanceof operator is considered to be bad OO-design, because it is not scalable
		
		/*for (Animal d : animals) {
			if (who.equals(d.getName())) {
				which = d;
			}
		}*/
		
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
		String results = "Kennel with id: " + id + " and name: " + name + " with a capacity of: " + capacity + " animals contain:\n";
		for (Animal d : animals) {
			results = results + d.toString() + "\n";
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
				System.out.println(a.getName()+ "is a dog");
				if(((Dog) a).getLikesBones() == true){
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public static void setId(int i) {
		id = i;
	}

}
