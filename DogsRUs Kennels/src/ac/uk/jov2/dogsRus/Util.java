package ac.uk.jov2.dogsRus;

import java.util.Scanner;

public abstract class Util {
	
	private static Scanner scan = new Scanner(System.in);

	public static int askForInt(){
		int tmp = askForInt("");
		return tmp;
	}
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
