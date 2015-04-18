package ac.uk.jov2.dogsRus;

import java.util.Scanner;

public abstract class Util {
	
	private static Scanner scan = new Scanner(System.in);; // so we can read from keyboard

	public static int askForInt(){
		
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
	
	public static boolean confirm(){
		boolean r = false;
		
		System.out.println("Are you sure you want do this action? Y/N");
		String tmp = scan.nextLine();
		if(tmp.toLowerCase().equals("y")){
			r = true;
		}
		
		return r;
	}
	
}
