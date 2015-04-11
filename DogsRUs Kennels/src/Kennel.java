import java.util.ArrayList;

/**
 * 
 * To model a Kennel - a collection of dogs
 * 
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 *
 */
public class Kennel {
	private String name;
	private ArrayList<Animal> animals;
	private int nextFreeLocation;
	private int capacity;

	/**
	 * Creates a kennel with a default size 20
	 * 
	 * @param maxNoDogs
	 *            The capacity of the kennel
	 */
	public Kennel(){
		this(20);
	}
	
	/**
	 * Create a kennel
	 * 
	 * @param maxNoDogs
	 *            The capacity of the kennel
	 */
	public Kennel(int maxNoAnimal) {
		nextFreeLocation = 0; // no Dogs in collection at start
		capacity = maxNoAnimal;
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
	 * Enables a user to delete a Dog from the Kennel.
	 * 
	 * @param theDog
	 *            The dog to remove
	 */
	public void removeDog(String who) {
		Animal which = null;
		// Search for the dog by name
		for (Animal d : animals) {
			if (who.equals(d.getName())) {
				which = d;
			}
		}
		if (which != null) {
			animals.remove(which); // Requires that Dog has an equals method
			System.out.println("removed " + who);
			nextFreeLocation = nextFreeLocation - 1;
		} else
			System.err.println("cannot remove - not in kennel");
	}

	/**
	 * @return String showing all the information in the kennel
	 */
	public String toString() {
		String results = "Data in Kennel " + name + " is:";
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
		//TODO ENTER CODE HERE
		Animal[] result = null;

		// Create a copy of the ArrayList and return as an array of the correct
		// size
		return result;
	}

	public Animal search(String name) {
		//TODO ENTER CODE HERE
		Animal result = null;

		return result;
	}

	
}
