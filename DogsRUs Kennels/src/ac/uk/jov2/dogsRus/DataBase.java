package ac.uk.jov2.dogsRus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DataBase {
	
	private Scanner scan;
	private String db;
	private String path = "db/";

	public DataBase(){
		scan = new Scanner(System.in);
		db = null;
		menu();
	}
	
	private void printMenu() {
		System.err.println("---------------------------------------------------------------------");
		System.err.println("1 - Create new Kennel");
		System.err.println("2 - Delete Kennel");
		System.err.println("3 - Choose Kennel");
		System.err.println("Q - Exit");
	}
	
	private void menu(){
		String response;

		printMenu();
		System.err.println("What would you like to do:");
		scan = new Scanner(System.in);
		response = scan.nextLine().toUpperCase();
		switch (response) {
			case "1":
				createDB();
				break;
			case "2":
				deleteDB();
				break;
			case "3":
				chooseDB();
				break;
		default:
			System.out.println("Try again");
		}
	}

	private void chooseDB() {
		listAllDB();
		System.out.println("Which Kennel you want load?");
		String file;
		boolean correct = false;
		do{
			file = scan.nextLine();
			File f = new File(path + file);
			if(f.exists() && !f.isDirectory()) {
				correct = true;
			}else{
				System.out.println("DataBase " + file + " not found. Please enter again");
			}
		}while(!correct);
		db = file;
		System.out.println("DataBase " + file + " selected!");
	}

	private void listAllDB(){
		 
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 
		for (int i = 0; i < listOfFiles.length; i++){
			if (listOfFiles[i].isFile()){
				files = listOfFiles[i].getName();
				System.out.print(files + "    ");
				System.out.println();
			}
		}
	}
	
	private void createDB(){
		System.out.println("Input the name for the DataBase");
		String file;
		file = scan.nextLine();
		File f = new File(path + file);
		if(f.exists() && !f.isDirectory()) {
			System.out.println("this DataBase already exist");
		}else{
			try {
				FileOutputStream fos= new FileOutputStream(path + file);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Kennel k = new Kennel();
			k.setName(file);
			save(path + file, k);
		}
		db = file;
		System.out.println("DataBase " + file + " selected!");
	}
	
	private void deleteDB(){
		listAllDB();
		System.out.println("Input the name for the DataBase");
		String file;
		file = scan.nextLine();
		File f = new File(path + file);
		if(f.exists() && !f.isDirectory()) {
	    	try{
	    		if(confirm() && f.delete()){
	    			System.out.println(f.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}
		menu();
	}
	
	private boolean confirm(){
		boolean r = false;
		System.out.println("Are you sure you want delete this DataBase? Y/N");
		System.out.println("This action CAN'T be undo");
		
		String answer;
		answer = scan.nextLine();
		answer.toLowerCase();
		if(answer.equals("y")){r = true;}
		return r;
	}
	
	public String getDB(){
		String r = path + db;
		return r;
	}
	
   private void save(String db, Kennel k){
       try{
         FileOutputStream fos= new FileOutputStream(db);
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(k);
         oos.close();
         fos.close();
       }catch(IOException ioe){
            ioe.printStackTrace();
            System.out.println("Error trying the save");
        }
   }
	
}
