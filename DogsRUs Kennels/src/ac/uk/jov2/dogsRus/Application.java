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
 * This class runs a Kennel
 * 
 * @author Lynda Thomas and Chris Loftus
 * @version 1.1 (16th March 2015)
 */
public class Application {
	private static String filename; // holds the name of the file
	private static Kennel kennel; // holds the kennel
	private Scanner scan; // so we can read from keyboard

	/*
	 * Notice how we can make this private, since we only call from main which
	 * is in this class. We don't want this class to be used by any other class.
	 */
	private Application() {
		scan = new Scanner(System.in);
		chooseKennel();
	}
	
	private void chooseKennel() {
		//System.out.print("Please enter the filename of kennel information: ");
		//filename = scan.next();
		filename = "dogsrus";
		kennel = new Kennel();
		load(filename);	
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
		numTimes = askForInt();
		scan.nextLine();
		Dog dog = new Dog(name, owners, lb, fav, numTimes);
		kennel.addAnimal(dog);
	}

	private void addCat() {
		System.out.println("enter on separate lines: name, owner-name, owner-phone, likeBones?, favourite food, number of times fed");
		String name = scan.nextLine();
		ArrayList<Owner> owners = getOwners();
		System.out.println("What is his/her favourite food?");
		String fav;
		fav = scan.nextLine();
		System.out.println("How many times is he/she fed a day? (as a number)");
		int numTimes;
		numTimes = askForInt();
		scan.nextLine();
		Cat cat = new Cat(name, owners, fav, numTimes);
		kennel.addAnimal(cat);
		
	}
	
	private int askForInt(){
		
		int r = -1;
		boolean canExit = false;
		
		do{
			try {
				r = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Is not a number. Please enter a valid number");
				scan.next();
			}
			if(r != -1){
				canExit = true;
			}
		}while(!canExit);
		
		return r;
		
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
			case "7":
				displayAllAnimals();
			    break;
			case "8":
				printAll();
			    break;
			case "9":
				chooseKennel();
			    break;
			case "Q":
				break;
			default:
				System.out.println("Try again");
			}
		} while (!(response.equals("Q")));
	}

	private void displayAllAnimals() {
		Animal[] allAnimals = kennel.obtainAllAnimal();
						
		for (Animal anim : allAnimals){
			System.out.println(anim);
		}
		
	}

	private void setKennelCapacity() {
		System.out.print("Enter max number of dogs: ");
		int max = scan.nextInt();
		scan.nextLine();
		kennel.setCapacity(max);
	}

	private void printDogsWithBones() {
		Animal[] animalWithBones = kennel.obtainAnimalWhoLikeBones();
		if(animalWithBones != null){
			System.out.println("Dogs with bones: ");
			for (Animal d: animalWithBones){
				System.out.println(d);
			}
		}else{
			System.out.println("Sorry we don't have any dogs who like bones in this kennel.");
		}
	}

	/*
	 * printAll() method runs from the main and prints status
	 */
	private void printAll() {
		System.out.println(kennel);
	}

	private void removeDog() {
		System.out.println("Which dog do you want to remove?");
		String dogtoberemoved;
		dogtoberemoved = scan.nextLine();
		kennel.removeAnimal(dogtoberemoved);
	}

	private void searchForAnimal() {
		System.out.println("which Animal do you want to search for?");
		String name = scan.nextLine();
		Animal anim = kennel.search(name);
		if (anim != null){
			System.out.println(anim.toString());
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
		System.out.println("---- " + kennel.getName() + " ----");
		System.out.println("1 - Add a new Animal");
		System.out.println("2 - set up Kennel name");
		System.out.println("3 - print all dogs who like bones");
		System.out.println("4 - search for a pet");
		System.out.println("5 - remove a pet");
		System.out.println("6 - set kennel capacity");
		System.out.println("7 - display all animals");
		System.out.println("8 - Print Kennel");
		System.out.println("9 - change Kennel");
		System.out.println("q - Quit");
	}

   private void save(String file){
	   System.out.println("saveMethod");
       try{
         FileOutputStream fos= new FileOutputStream(file);
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(kennel);
         oos.close();
         fos.close();
       }catch(IOException ioe){
            ioe.printStackTrace();
        }
       System.out.println("SaveMethodDone!");
   }

   private void load(String file){
	   System.out.println("loadMethod");
	   Kennel k;
       try
       {
           FileInputStream fis = new FileInputStream(file);
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
	   System.out.println("loadMethodDone!");
   }

	// /////////////////////////////////////////////////
	public static void main(String args[]) {
		System.out.println("**********HELLO***********");
		Application app = new Application();
		app.runMenu();
		app.save(filename);
		System.out.println("***********GOODBYE**********");
	}
}
