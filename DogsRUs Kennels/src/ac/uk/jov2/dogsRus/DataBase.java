package ac.uk.jov2.dogsRus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * A class for select all the databases we have
 * @author Jose Vives
 *@version 1.0
 */
public class DataBase {
	
	private Scanner scan;
	private String db;
	private static final String PATH = "db/";
	private File[] files;

	/**
	 * Constructor for this class
	 * Just initialize variables, load all the kennels we have and run the menu
	 */
	public DataBase(){
		scan = new Scanner(System.in);
		db = null;
		loadKennels();
		menu();
	}
	
	/**
	 * Read all the kennels they store on the arraylist
	 */
	private void loadKennels(){
		files = null;
		File directory = new File(PATH);
		files = directory.listFiles();
	}
	
	/**
	 * Just print the menu with options
	 */
	private void printMenu() {
		System.out.println("------------------------------------------------------");
		if(files.length != 1){
			listAllDB();
			System.out.println();
			System.out.println("Type create for create a new Kennel");
			System.out.println("Type delete for delete a Kennel");
			System.out.println("Enter the name of a Kennel for select them or any option:");
		}else{
			System.out.println("Not kennels stored, Let's to create a new one for start using the program!");
			System.out.println("Type create for create a new Kennel");
		}
	}
	
	/**
	 * All the menu code
	 */
	private void menu(){
		String response;
		boolean correct = false;
		
		runChoose: do{
			printMenu();
			scan = new Scanner(System.in);
			response = scan.nextLine().toLowerCase();
			if(response.equals("create")){
				createDB();
				// And restart the loop
				continue runChoose;
			}else if(response.equals("delete")){
				System.out.println("You choose delete");
				deleteDB();
				// And restart the loop
				continue runChoose;
			}else{
				for(File f: files){
					if(f.getName().toLowerCase().equals(response)){
						db = f.getName();
						correct = true;
					}
				}
				if(correct == false){
					System.out.println("Sorry but " + response + " doesn't exist, please enter again the kennel name you want!");
				}
			}
		}while(!correct);
	}

	/**
	 * Print all the kennels we have for use
	 */
	private void listAllDB(){
		 		
		for(File f: files){
			if((!f.getName().startsWith(".")) && f.isFile()){
				System.out.print(f.getName() + "  ");
			}
		}
		System.out.println();
		
	}
	
	/**
	 * Create a new Kennel
	 */
	private void createDB(){

		String name;
		boolean correct = false;
		do{
			boolean nameCorrect = false;
			do{
				System.out.println("Type the name for the new kennel:");
				name = scan.nextLine();
				if(name.toLowerCase().equals("create") || name.toLowerCase().equals("delete")){
					// Check if the input is not any reserved word
					System.out.println("The words \"create\" and \"delete\" are reserved words. Please use another name for the kennel");
				}else{
					nameCorrect = true;
				}
			}while(!nameCorrect);
			
			// Check if the selected name for the kennel exist
			boolean exist = false;
			for(File tmp: files){
				if(tmp.getName().toLowerCase().equals(name.toLowerCase())){
					exist = true;
				}
			}
			
			if(!exist){
				System.out.println("Enter the size of the new Kennel");
				int size = Util.askForInt();
				
				
				try {
					FileOutputStream fos= new FileOutputStream(PATH + name);
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Kennel k = new Kennel(name, size);
				save(name, k);
				correct = true;
			}else{
				System.out.println("Sorry but this name already exist on the DataBase. Please insert a different name for the kennel.");
			}
			
		}while(!correct);
		loadKennels();
	}
	
	/**
	 * Delete a existent kennel file
	 */
	private void deleteDB(){
		String response;
		boolean correct = false;
		do{
			System.out.println("Type \"exit\" for back to menu without delete anything:");
			System.out.println("Type the name of the kennel you want delete: ");
			scan = new Scanner(System.in);
			response = scan.nextLine().toLowerCase();
			
			if(!response.equals("exit")){
				// Check if the selected name for the kennel exist
				for(File tmp: files){
					if(tmp.getName().toLowerCase().equals(response)){
						if(Util.confirm()){
							tmp.delete();
							correct = true;
						}
					}
				}
				
				if(correct == false){
					System.err.println("Sorry but this kennel doesn't exist on the DataBase. Please enter again");
				}
			}else{
				correct = true;
			}
			
		}while(!correct);
		loadKennels();
	}
	
	/**
	 * Know which file is selected
	 * @return the selected kennel
	 */
	public String getDB(){
		String r = PATH + db;
		return r;
	}
	
	/**
	 * Save the created method
	 * @param db Is the name of the new Kennel
	 * @param k is the kennel to serialize
	 */
	private void save(String db, Kennel k){
		try{
			FileOutputStream fos= new FileOutputStream(PATH + db);
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(k);
			oos.close();
			fos.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.err.println("Error trying the save");
		}
	}
	
	/**
	 * Know the path where is the databases
	 * @return the path
	 */
	public static String getPath() {
		return PATH;
	}
	
}