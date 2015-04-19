package ac.uk.jov2.dogsRus;

import java.util.Scanner;

/**
 * A class for hold generics methods
 * 
 * @author Jose Vives
 * @version 1.0 (19th April 2015)
 */
public abstract class Util {
	
	private static Scanner scan = new Scanner(System.in);

	/**
	 * Call the method askForInt() with an empty string
	 * @return the int the user has input
	 */
	public static int askForInt(){
		int tmp = askForInt("");
		return tmp;
	}
	
	/**
	 * Ask the user for an int, and check if is an int
	 * @param message the message for if I want print on screen
	 * @return the int the user has input
	 */
	public static int askForInt(String message){
		Integer i = null;
		boolean correct = false;
		while(!correct) {
			if(scan.hasNextInt()){
				i = scan.nextInt();
				correct = true;
			}else{
				System.out.println("Is not a number, please enter a number");
				scan.next();
			}
		}
		return i;
	}
	
	/**
	 * Method for ask the user if they are sure want do an action
	 * @return If they want or not
	 */
	public static boolean confirm(){
		boolean r = false;
		System.out.println("Are you sure you want delete this DataBase? Y/N");
		System.out.println("This action CAN'T be undo");
		String response;
		response = scan.nextLine();
		if(response.toLowerCase().equals("y") || response.toLowerCase().equals("yes")){
			r = true;
		}
		return r;
	}
}
