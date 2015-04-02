package finalProject;
/**
 * @author Eric Le Fort
 * @version 01
 * 
 * Abstract:
 * Handles all 
 */
import java.io.File;

public abstract class DataLoader{

	/**
	 * Reads in from the data file in order to initialize the array of Nodes. The data will be of the form:
	 * fromNodeId	toNodeId
	 * @param file
	 * @return The array of nodes that will store the data for the program.
	 */
	public static Node[] readData(File file){
		//TODO implement
		//Step 1: read in the new Node.
		//Step 2: Binary search for the of the from Node's position to insert it.
		//Step 3: If we find the from Node's id, just append to that Node, otherwise, create new Node.
		//Step 4: Repeat steps 2 and 3 for adding the to Node.
		//Step 5: Lather, rinse, repeat until out of lines in the data file.
		return new Node[0];
	}//readData()
	
	/**
	 * Writes to data using all the edges that we have stored in data.
	 * @param node
	 */
	public static void writeData(Node node){
		//TODO implement
	}//writeData()
	
}//DataLoader
