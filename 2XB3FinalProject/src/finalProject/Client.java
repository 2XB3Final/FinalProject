package finalProject;
/**
 * @author Eric Le Fort
 * @version 01
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Client{

	public static void main(){
		Node[] data = null;
		File file = getFile();
		int choice;
		boolean active = true;

		try{
			data = DataLoader.readData(file);

			while(true){							//Runs until the user lets the program know theyr're finished.
				choice = getChoice();

				if(choice == -1){					//Invalid input.
					System.out.println("Sorry, that isn't a valid option.\n");
				}else if(choice == 1){				//Find origin.
					//TODO
				}else if(choice == 2){				//Find children.
					//TODO
				}else if(choice == 3){				//Find parents.
					//TODO
				}else if(choice == 4){
					//TODO
				}else if(choice == 5){
					//TODO
				}else if(choice == 6){			//Save data
					DataLoader.writeData(data, file);
				}else if(choice == 7){			//User decided to quit.
					break;
				}	
			}
			System.out.println("I hope you enjoyed using the program!");
		}catch(FileNotFoundException fnfe){
			System.out.println("Unfortunately, the data file could not be located.");
		}


	}//main()

	/**
	 * Prompts the user for the path to the file they wish to analyze.
	 * @return The File made using the user input.
	 */
	@SuppressWarnings("resource")			//We should never close System.in.
	private static File getFile(){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the path to the file you would like analyzed.");
		return new File(in.nextLine());		//TODO this is the first test file"data/tinyDAG.txt");
	}//getFile()

	/**
	 * Prompts the user to enter a value between 1 and 7.
	 * 1 - Find the original source of the information.
	 * 2 - Find who a certain person spread the information to.
	 * 3 - Find from whom a certain person heard the information.
	 * 4 - Show all people that spread the information.
	 * 5 - Show all people that did not spread the information.
	 * 6 - Save data.
	 * 7 - Exit the program.
	 * If the user inputs a value that is either not a number or is outside of the range, -1 is returned.
	 * @return An int representing the user's choice.
	 */
	@SuppressWarnings("resource")							//We should never close System.in.
	private static int getChoice(){
		Scanner in = new Scanner(System.in);
		int choice;

		System.out.println("Enter one of the following choices: ");
		System.out.println("1 - Find the original source of the information.");
		System.out.println("2 - Find who a certain person spread the information to.");
		System.out.println("3 - Find from whom a certain person heard the information.");
		System.out.println("4 - Show all people that spread the information.");
		System.out.println("5 - Show all people that did not spread the information.");
		System.out.println("6 - Save data.");
		System.out.println("7 - Exit the program.");
		
		try{
			choice = in.nextInt();
		}catch(InputMismatchException ime){ return -1; }	//User didn't enter a number.
		
		if(choice > 0 && choice < 8){
			return choice;									//The choice is valid!
		}
		return -1;											//Value is outside of the accepted range.
	}//getChoice()

}//Client
