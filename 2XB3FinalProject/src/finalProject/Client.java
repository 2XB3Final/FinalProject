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

	public static void main(String[] args){
		Node[] data = null;
		File file = getFile();
		int choice, nodeID;

		try{
			data = DataLoader.readData(file);

			while(true){									//Runs until the user lets the program know theyr're finished.
				if((choice = getChoice()) == -1){			//Invalid input.
					System.out.println("Sorry, that isn't a valid option.\n");
				}else if(choice == 1){						//Find origin.
					nodeID = getNodeID();
					if(nodeID != -1){
						printResults(new Node[]{GraphHandler.findOrigin(data, nodeID)});
					}
				}else if(choice == 2){						//Find children.
					nodeID = getNodeID();
					if(nodeID != -1){
						printResults(GraphHandler.findChildren(data, nodeID));
					}
				}else if(choice == 3){						//Find parents.
					nodeID = getNodeID();
					if(nodeID != -1){
						printResults(GraphHandler.findParents(data, nodeID));
					}
				}else if(choice == 4){						//Find non dead ends
					printResults(GraphHandler.findNonDeadEnds(data));
				}else if(choice == 5){						//Find dead ends.
					printResults(GraphHandler.findDeadEnds(data));
				}else if(choice == 6){						//Save data
					DataLoader.writeData(data, file); 
				}else if(choice == 7){						//User decided to quit.
					break;
				}	
			}
			System.out.println("I hope you enjoyed using the program!");
		}catch(FileNotFoundException fnfe){
			System.out.println("Unfortunately, the data file could not be located.");
		}
	}//main()

	/**
	 * Prints out the array of Nodes passed in line by line.
	 * If the array is empty, prints that out.
	 * @param results
	 */
	private static void printResults(Node[] results){
		if(results.length == 0){
			System.out.println("The results came up empty.\n");
		}

		for(int i = 0; i < results.length; i++){
			System.out.println(results[i]);
		}
		System.out.println();
	}//printResults()

	/**
	 * Prompts the user for the path to the file they wish to analyze.
	 * @return The File made using the user input.
	 */
	@SuppressWarnings("resource")			//We should never close System.in.
	private static File getFile(){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the path to the file you would like analyzed.");
		return new File("data/tinyDAG.txt");	//in.nextLine());//TODO insert this general case instead.
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

	/**
	 * Prompts the user for the ID of the Node from which they wish to start from.
	 * @return An int representing the nodeID.
	 */
	@SuppressWarnings("resource")							//We should never close System.in.
	private static int getNodeID(){
		Scanner in = new Scanner(System.in);
		int nodeID;
		System.out.println("Please enter the ID of the Node you're starting from.");

		try{
			nodeID = in.nextInt();
		}catch(InputMismatchException ime){					//User didn't enter a number.
			System.out.println("An integer must be entered for the node's ID value.\n");
			return -1;
		}	

		if(nodeID > 0){
			return nodeID;									//Valid nodeID!
		}
		System.out.println("The node ID cannot be negative.\n");
		return -1;											//The Node entered was a negative number.
	}//getNodeID()

}//Client
