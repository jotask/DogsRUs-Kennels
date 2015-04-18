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
	private int capacity;
	private int numberAnimals;

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
		capacity = maxNoAnim;
		name= n;
	}

	/**
	 * This method sets the value for the name attribute. The purpose of the
	 * attribute is: The name of the kennel e.g. "DogsRUs"
	 * 
	 * @param theName
	 */
	public void setName(String theName) {
//		db.setKennelName(id, theName);
		name = theName;
	}
	
	/**
	 * Set the size of the kennel
	 * @param db 
	 * @param capacity The max dogs we can house
	 */
	public void setCapacity(int cap){
		// This should really check to see if we already have dogs
		// in the kennel and reducing the capacity would lead to evictions!
		
//		int animalHave = db.knowAnimalsInKennel(id);
		
//		if(animalHave <= cap){
//			capacity = cap;
////			db.setKennelCapacity(id, cap);
//		}
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
		return numberAnimals;
	}

	/**
	 * Enables a user to add a Dog to the Kennel
	 * 
	 * @param theDog
	 *            A new dog to home
	 */
	public void addAnimal(Animal theAnimal) {
		if (numberAnimals >= capacity) {
			System.out.println("Sorry kennel is full - cannot add team");
			return;
		}
		// we add in the position indexed by nextFreeLocation
		// This starts at zero
//		animals.add(theAnimal);

		// now increment index ready for next one
		numberAnimals = numberAnimals + 1;
	}

	/**
	 * Enables a user to delete a Animal from the Kennel.
	 * 
	 * @param theAnimal
	 *            The animal to remove
	 */
	public void removeAnimal(int idKennel, String who) {
		
		Animal which = null;
		
		// Search for the Animal by name
		which = search(idKennel, who);
		
		//TODO instanceof operator is considered to be bad OO-design, because it is not scalable
		
		/*for (Animal d : animals) {
			if (who.equals(d.getName())) {
				which = d;
			}
		}*/
		
		if (which != null) {
//			animals.remove(which); // Requires that Dog has an equals method
			System.out.println("removed " + who);
			numberAnimals = numberAnimals - 1;
		} else{
			System.err.println("cannot remove - not in kennel");
		}
	}

	/**
	 * @return String showing all the information in the kennel
	 */
	public String toString() {
		
		// NOTE I didn't testet out with animals
		
		String results = "Kennel with id " + id + " and name " + name + " with a capacity of " + capacity + " animals contains";
		
		Animal[] animals = obtainAllAnimal();
		if(animals.length != 0){
			for (Animal d : animals) {
				results = results + d.toString() + "\n";
			}
		}else{
			results = results + " 0 animals" + "\n";
		}
		return results;
	}

	/**
	 * Returns an array of the dogs in the kennels
	 * @return An array of the correct size
	 */
	public Animal[] obtainAllAnimal() {
		Animal[] result = null;
		return result;
	}

	/**
	 * Only returns those dogs who like bones
	 * @return An array of dogs of the correct size. If no dogs like bones then returns an empty array (size 0)
	 */
	public Animal[] obtainAnimalWhoLikeBones() {
		
		ArrayList<Animal> anims = new ArrayList<Animal>();

		Animal[] allAnimals = null;
		
		for(Animal a: allAnimals){
			
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

	public Animal search(int idKennel, String name) {

		//Application.getDB().searchPet(idKennel, name);
		
		Animal result = null;

//		for(Animal a: animals){
//			if(a.getName().equals(name)){
//				System.out.println("We found the pet:");
//				result = a;
//			}
//		}
		
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
