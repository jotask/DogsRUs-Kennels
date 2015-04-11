import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs a Kennel
 * 
 * @author Lynda Thomas and Chris Loftus
 * @version 1.1 (16th March 2015)
 */
public class Application {
	private String filename; // holds the name of the file
	private Kennel kennel; // holds the kennel
	private Scanner scan; // so we can read from keyboard

	/*
	 * Notice how we can make this private, since we only call from main which
	 * is in this class. We don't want this class to be used by any other class.
	 */
	private Application() {
		scan = new Scanner(System.in);
		System.out.print("Please enter the filename of kennel information: ");
		filename = scan.next();
		
		kennel = new Kennel();
	}

	/*
	 * initialise() method runs from the main and reads from a file
	 */
	private void initialise() {
		System.out.println("Using file " + filename);
		
		// Using try-with-resource (see my slides from session 15)
		try(FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			Scanner infile = new Scanner(br)){

			String kennelName = infile.nextLine();
			//String kennelName = "dogsrus.txt";
			int kennelSize = infile.nextInt();
			infile.nextLine();
			kennel.setCapacity(kennelSize);
			int numDogs = infile.nextInt();
			infile.nextLine();
			kennel.setName(kennelName);
			for(int i=0; i < numDogs; i++){
				String dogName = infile.nextLine();
				int numOwners = infile.nextInt();
				infile.nextLine();
				ArrayList<Owner> owners = new ArrayList<>();
				for(int oCount=0; oCount < numOwners; oCount++){
					String name = infile.nextLine();
					String phone = infile.nextLine();
					Owner owner = new Owner(name, phone);
					owners.add(owner);
				}
				boolean likesBones = infile.nextBoolean();
				int feedsPerDay = infile.nextInt();
				infile.nextLine();
				String favFood = infile.nextLine();
				
				Animal dog = new Dog(dogName, owners, likesBones, favFood, feedsPerDay);
				
				//System.out.println(dog.toString());
				kennel.addAnimal(dog);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("The file: " + " does not exist. Assuming first use and an empty file." +
		                       " If this is not the first use then have you accidentally deleted the file?");
		} catch (IOException e) {
			System.err.println("An unexpected error occurred when trying to open the file " + filename);
			System.err.println(e.getMessage());
		}
	}
	
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

	private void addDog() {
		boolean lb = false;
		System.out.println("enter on separate lines: name, owner-name, owner-phone, likeBones?, favourite food, number of times fed");
		String name = scan.nextLine();
		ArrayList<Owner> owners = getOwners();
		System.out.println("Does he like bones? (Y/N)");
		String likeBones;
		likeBones = scan.nextLine().toUpperCase();
		if (likeBones.equals("Y")) {
			lb = true;
		}
		System.out.println("What is his/her favourite food?");
		String fav;
		fav = scan.nextLine();
		System.out.println("How many times is he/she fed a day? (as a number)");
		int numTimes;
		numTimes = scan.nextInt(); // This can be improved (InputMismatchException?)
		scan.nextLine();
		Dog dog = new Dog(name, owners, lb, fav, numTimes);
		kennel.addAnimal(dog);
		
	}

	private void addCat() {
		System.out.println("enter on separate lines: name, owner-name, owner-phone, likeBones?, favourite food, number of times fed");
		String name = scan.nextLine();
		ArrayList<Owner> owners = getOwners();
		System.out.println("Does he like bones? (Y/N)");
		System.out.println("What is his/her favourite food?");
		String fav;
		fav = scan.nextLine();
		System.out.println("How many times is he/she fed a day? (as a number)");
		int numTimes;
		numTimes = scan.nextInt(); // This can be improved (InputMismatchException?)
		scan.nextLine();
		Cat cat = new Cat(name, owners, fav, numTimes);
		kennel.addAnimal(cat);
		
	}

	/*
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
				removeDog();
				break;
			case "6":
				setKennelCapacity();
			    break;
			case "Q":
				break;
			default:
				System.out.println("Try again");
			}
		} while (!(response.equals("Q")));
	}

	private void setKennelCapacity() {
		System.out.print("Enter max number of dogs: ");
		int max = scan.nextInt();
		scan.nextLine();
		kennel.setCapacity(max);
	}

	private void printDogsWithBones() {
		Animal[] animalWithBones = kennel.obtainAnimalWhoLikeBones();
		System.out.println("Dogs with bones: ");
		for (Animal d: animalWithBones){
			System.out.println(d);
		}	
	}

	/*
	 * printAll() method runs from the main and prints status
	 */
	private void printAll() {
		System.out.println(kennel);
	}

	/*
	 * save() method runs from the main and writes back to file
	 */
	private void save() {
		try(FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter outfile = new PrintWriter(bw);){
			
			outfile.println(kennel.getName());
			outfile.println(kennel.getCapacity());
			outfile.println(kennel.getNumOfAnimals());
			Animal[] dogs = kennel.obtainAllAnimal();
			for (Animal d: dogs){
				outfile.println(d.getName());
				Owner[] owners = d.getOriginalOwners();
				outfile.println(owners.length);
				for(Owner o: owners){
					outfile.println(o.getName());
					outfile.println(o.getPhone());
				}
				//outfile.println(d.getLikesBones());
				outfile.println(d.getFeedsPerDay());
				outfile.println(d.getFavouriteFood());
			}
		} catch (IOException e) {
			System.err.println("Problem when trying to write to file: " + filename);
		}

	}

	private void removeDog() {
		System.out.println("which dog do you want to remove");
		String dogtoberemoved;
		dogtoberemoved = scan.nextLine();
		kennel.removeDog(dogtoberemoved);
	}

	private void searchForAnimal() {
		System.out.println("which dog do you want to search for");
		String name = scan.nextLine();
		Animal dog = kennel.search(name);
		if (dog != null){
			System.out.println(dog.toString());
		} else {
			System.out.println("Could not find dog: " + name);
		}
	}

	private void changeKennelName() {
		String name = scan.nextLine();
		kennel.setName(name);
	}

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

	private void printMenu() {
		System.out.println("1 - Add a new Animal");
		System.out.println("2 - set up Kennel name");
		System.out.println("3 - print all dogs who like bones");
		System.out.println("4 - search for a dog");
		System.out.println("5 - remove a dog");
		System.out.println("6 - set kennel capacity");
		System.out.println("q - Quit");
	}

	// /////////////////////////////////////////////////
	public static void main(String args[]) {
		System.out.println("**********HELLO***********");
		Application app = new Application();
		app.initialise();
		app.runMenu();
		app.printAll();
		app.save();
		System.out.println("***********GOODBYE**********");
	}
}
