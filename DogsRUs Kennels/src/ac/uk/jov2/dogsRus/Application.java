package ac.uk.jov2.dogsRus;

import java.util.ArrayList;
import java.util.Scanner;

import ac.uk.jov2.dogsRus.animals.Animal;
import ac.uk.jov2.dogsRus.animals.Cat;
import ac.uk.jov2.dogsRus.animals.Dog;
import ac.uk.jov2.dogsRus.database.DataBase;

/**
 * This class runs a Kennel
 * 
 * @author Lynda Thomas and Chris Loftus
 * @version 1.1 (16th March 2015)
 */
public class Application {

	private static Kennel kennel; // holds the kennel
	private Scanner scan; // so we can read from keyboard
	private static DataBase db;

	/*
	 * Notice how we can make this private, since we only call from main which
	 * is in this class. We don't want this class to be used by any other class.
	 */
	private Application() {
		db = new DataBase();
		scan = new Scanner(System.in);
		chooseKennel();
	}
	
	private void kennelMenu(ArrayList<Kennel> k){
		if(!k.isEmpty()){
			for(Kennel tmp: k){
				if(tmp.getId() == -1){
					// If is -1 the id is corrupted because is the default ID for kennel
					System.err.print(tmp.getName() + "	");
				}else{
					System.out.print(tmp.getName() + "	");
				}
			}
			System.out.println();
			System.out.println("Type create for create a new Kennel");
			System.out.println("Type delete for create a new Kennel");
			System.out.println("Enter the name of a Kennel for select them or any option:");
		}else{
			System.out.println("Not kennels stored, Let's to create a new one for start using the program!");
			createKennel(k);
		}
	}
	
	private void chooseKennel(){
		System.out.println("------------------------------------------------------");
		ArrayList<Kennel> k = new ArrayList<Kennel>();
		k = db.allKennels();
		
		String response;
		boolean correct = false;
		
		do{
			kennelMenu(k);
			scan = new Scanner(System.in);
			response = scan.nextLine().toLowerCase();
			if(response.equals("create")){
				createKennel(k);
			}else if(response.equals("delete")){
				deleteKennel(k);
			}else{
				for(Kennel tmp: k){
					if(tmp.getName().toLowerCase().equals(response)){
						selectKennel(tmp.getId());
						correct = true;
					}
				}
				if(correct == false){
					System.out.println("Sorry but " + response + " doesn't exist, please enter again the kennel name you want!");
				}
			}
		}while(!correct);
	}
	
	private void createKennel(ArrayList<Kennel> k){
		// FIXME When is created a kennel you can't choose that kennel created. You need to close and open the program for selectd that kennel
		String name;
		boolean correct = false;
		do{
			boolean nameCorrect = false;
			do{
				System.out.println("Type the name for the new kennel:");
				name = scan.nextLine();
				if(name.toLowerCase().equals("create") || name.toLowerCase().equals("delete")){
					// Check if the input is not any reserved word
					System.out.println("The words \"create\" and \"delete\" are words reserved. Please use another name");
				}else{
					nameCorrect = true;
				}
			}while(!nameCorrect);
			
			// Check if the selected name for the kennel exist
			boolean exist = false;
			for(Kennel tmp: k){
				if(tmp.getName().toLowerCase().equals(name.toLowerCase())){
					exist = true;
				}
			}
			
			if(!exist){
				System.out.println("Enter the size of the new Kennel");
				int s = askForInt();
				Kennel tmpK = new Kennel(name, s);
				db.insertKennel(tmpK);
				correct = true;
			}else{
				System.out.println("Sorry but this name already exist on the DataBase. Please insert a different name for the kennel.");
			}
			
		}while(!correct);
		
		chooseKennel();
	}
	
	private void deleteKennel(ArrayList<Kennel> k){

		String response;
		boolean correct = false;
		do{
			System.out.println("Type \"exit\" for back to menu:");
			System.out.println("Type the name of the kennel you want:");
			scan = new Scanner(System.in);
			response = scan.nextLine().toLowerCase();
			
			if(!response.equals("exit")){
				// Check if the selected name for the kennel exist
				for(Kennel tmp: k){
					if(tmp.getName().toLowerCase().equals(response)){
						db.deleteKennel(tmp.getId());
						correct = true;
					}
				}
				
				if(correct == false){
					System.out.println("Sorry but this kennel doesn't exist on the DataBase. Please enter again");
				}
			}else{
				correct = true;
			}
			
		}while(!correct);
		
		chooseKennel();
		
	}
	
	private void selectKennel(int id){
		kennel = db.getKennel(id);
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
		System.out.println("Enter the name for the kennel");
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

	// /////////////////////////////////////////////////
	public static void main(String args[]) {
		System.out.println("**********HELLO***********");
		Application app = new Application();
		app.runMenu();
		db.shutdown();
		System.out.println("***********GOODBYE**********");
	}
}
