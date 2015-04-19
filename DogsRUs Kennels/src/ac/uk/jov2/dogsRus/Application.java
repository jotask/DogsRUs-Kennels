package ac.uk.jov2.dogsRus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import ac.uk.jov2.dogsRus.animals.Animal;
import ac.uk.jov2.dogsRus.animals.Cat;
import ac.uk.jov2.dogsRus.animals.Dog;

/**
 * This is the main Class
 * 
 * @author Lynda Thomas, Chris Loftus and Jose Vives
 * @version 1.2 (19th April 2015)
 */
public class Application {

	private static Kennel kennel; // Holds the current kennel
	private Scanner scan; // so we can read from keyboard
	private static DataBase db; // Holds the database and his menu
	
	/**
	 * Private constructor, only can be call on this class 
	 */
	private Application() {
		scan = new Scanner(System.in);
		chooseKennel();
	}
	
	/**
	 *  When this method is call we can change the current kennel
	 */
	private void chooseKennel() {
		db = new DataBase();
		load(db.getDB());	
	}
	
	/**
	 * Sub-Menu for add an animal, this can be Cat or Dog
	 */
	private void addAnimal(){
		String response;
		do {
			System.out.println("1 - Add Dog");
			System.out.println("2 - Add Cat");
			System.out.println("Q - Back to menu");
			System.out.println("Which animal you want add:");
			scan = new Scanner(System.in);
			response = scan.nextLine().toUpperCase();
			switch (response) {
			case "1":
				addDog();
				break;
			case "2":
				addCat();;
				break;
			case "Q":
				break;
			default:
				System.out.println("Try again");
			}
		} while (!(response.equals("Q")));
	}
	
	/**
	 * Create a new Dog and add this new dog to the kennel
	 */
	private void addDog() {
		boolean lb = false;
		boolean takeWal = false;
		System.out.println("Let's enter a new Dog to this kennel");
		String name = scan.nextLine();
		ArrayList<Owner> owners = getOwners();
		System.out.println("Does he like bones? (Y/N)");
		String likeBones;
		likeBones = scan.nextLine().toUpperCase();
		if (likeBones.equals("Y")) {
			lb = true;
		}
		System.out.println("Does he need teek to walk? (Y/N)");
		String takeWalk;
		takeWalk = scan.nextLine().toUpperCase();
		if (takeWalk.equals("Y")) {
			takeWal = true;
		}
		System.out.println("What is his/her favourite food?");
		String fav;
		fav = scan.nextLine();
		System.out.println("How many times is he/she fed a day? (as a number)");
		int numTimes;
		numTimes = Util.askForInt();
		Dog dog = new Dog(name, owners, lb, fav, numTimes, takeWal);
		kennel.addAnimal(dog);
	}
	
	/**
	 * Create a new Cat and add this new cat to the kennel
	 */
	private void addCat() {
		boolean canShare = false;
		boolean needPett = false;
		System.out.println("enter on separate lines: name, owner-name, owner-phone, likeBones?, favourite food, number of times fed");
		String name = scan.nextLine();
		ArrayList<Owner> owners = getOwners();
		System.out.println("What is his/her favourite food?");
		String fav;
		fav = scan.nextLine();
		System.out.println("How many times is he/she fed a day? (as a number)");
		int numTimes;
		numTimes = Util.askForInt();
		System.out.println("They is nice with other cats? Can share a run?");
		String canShareR;
		canShareR = scan.nextLine().toUpperCase();
		if (canShareR.equals("Y")) {
			canShare = true;
		}
		System.out.println("They need daily petting?");
		String dailyPet;
		dailyPet = scan.nextLine().toUpperCase();
		if (dailyPet.equals("Y")) {
			needPett = true;
		}
		Cat cat = new Cat(name, owners, fav, numTimes, canShare, needPett);
		kennel.addAnimal(cat);
		
	}

	/**
	 * runMenu() method runs from the main and allows entry of data etc
	 */
	private void runMenu() {
		String response;
		do {
			printMenu();
			System.out.println("What would you like to do:");
			scan = new Scanner(System.in);
			response = scan.nextLine().toUpperCase();
			switch (response) {
			case "1":
				addAnimal();
				break;
			case "2":
				changeKennelName();
				break;
			case "3":			
				printDogsWithBones();
				break;
			case "4":
				searchForAnimal();
				break;
			case "5":
				removeAnimal();
				break;
			case "6":
				setKennelCapacity();
			    break;
			case "7":
				printAll();
			    break;
			case "8":
				save(db.getDB());
				chooseKennel();
			    break;
			case "Q":
				break;
			default:
				System.out.println("Try again");
			}
		} while (!(response.equals("Q")));
	}
	
	/**
	 * Change the capacity from the current kennel
	 */
	private void setKennelCapacity() {
		System.out.print("Enter max number of Animal: ");
		int max = scan.nextInt();
		scan.nextLine();
		kennel.setCapacity(max);
	}
	
	/**
	 * Print all Dogs who like bones in the current kennel
	 */
	private void printDogsWithBones() {
		Animal[] animalWithBones = kennel.obtainAnimalWhoLikeBones();
		if(animalWithBones != null){
			System.out.println("Dogs with bones: ");
			if(animalWithBones.length != 0){
				for (Animal d: animalWithBones){
					System.out.println(d);
				}
			}else{
				System.out.println("There are any dog who like bones in this kennel");
			}
		}else{
			System.out.println("Sorry we don't have any dogs who like bones in this kennel.");
		}
	}

	/**
	 * Prints the status of the current kennel
	 */
	private void printAll() {
		System.out.println(kennel);
	}
	
	/**
	 * Remove one animal from the kennel
	 */
	private void removeAnimal() {
		System.out.println("Which dog do you want to remove?");
		String dogtoberemoved;
		dogtoberemoved = scan.nextLine();
		kennel.removeAnimal(dogtoberemoved);
	}

	/**
	 * Search a animal by the name on the current kennel
	 */
	private void searchForAnimal() {
		System.out.println("Which Animal do you want to search for?");
		String name = scan.nextLine();
		Animal anim = kennel.search(name);
		if (anim != null){
			System.out.println(anim.toString());
		} else {
			System.out.println("Could not find animal with name: " + name);
		}
	}
	
	/**
	 * Ask the user for the new name of the kennel and call the methods in kennel for change his name
	 */
	private void changeKennelName() {
		System.out.println("Enter the name for the kennel");
		String name = scan.nextLine();
		kennel.setName(name);
	}

	/**
	 * Ask and creates owners for when an Animal is added
	 * 
	 * @return An ArrayList with owners
	 */
	private ArrayList<Owner> getOwners() {
		ArrayList<Owner> owners = new ArrayList<Owner>();
		String answer;
		do {
			System.out.println("Enter on separate lines: owner-name owner-phone");
			String ownName = scan.nextLine();
			String ownPhone = scan.nextLine();
			Owner own = new Owner(ownName, ownPhone);
			owners.add(own);
			System.out.println("Another owner (Y/N)?");
			answer = scan.nextLine().toUpperCase();
		} while (!answer.equals("N"));
		return owners;
	}

	/**
	 * Just print the menu
	 */
	private void printMenu() {
		System.out.println("---- " + kennel.getName() + " ----");
		System.out.println("1 - Add a new Animal");
		System.out.println("2 - set up Kennel name");
		System.out.println("3 - print all dogs who like bones");
		System.out.println("4 - search for a pet");
		System.out.println("5 - remove a pet");
		System.out.println("6 - set kennel capacity");
		System.out.println("7 - Print Kennel");
		System.out.println("8 - change Kennel");
		System.out.println("q - Quit");
	}

	/**
	 * Save the current kennel object
	 * Serialize the object and save it on his file
	 * @param db  The current kennel file we are working
	 */
   private void save(String db){
       try{
         FileOutputStream fos= new FileOutputStream(db);
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(kennel);
         oos.close();
         fos.close();
       }catch(IOException ioe){
            ioe.printStackTrace();
        }
   }

	/**
	 * Load a new kennel object
	 * Read a serializabe object kennel and load it
	 * @param db  The current kennel file selected
	 */
   private void load(String db){
	   Kennel k;
       try{
           FileInputStream fis = new FileInputStream(db);
           ObjectInputStream ois = new ObjectInputStream(fis);
           k = (Kennel) ois.readObject();
           ois.close();
           fis.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return;
         }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return;
         }
       kennel = k;
   }

   /**
    * The Main method
    * @param args
    */
	public static void main(String args[]) {
		System.out.println("********** Welcome ***********");
		Application app = new Application();
		app.runMenu();
		app.save(db.getDB());
		System.out.println("*********** GoodBye**********");
	}
}
