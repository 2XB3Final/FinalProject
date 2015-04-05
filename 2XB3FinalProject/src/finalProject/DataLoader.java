package finalProject;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author Eric Le Fort
 * @version 01
 * 
 * Abstract:
 * Handles all necessary operations involving reading and writing data.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public abstract class DataLoader{

	/**
	 * Reads in from the data file in order to initialize the array of Nodes. The data will be of the form:
	 * fromNodeId	toNodeId
	 * @param file
	 * @return The array of nodes that will store the data for the program.
	 * @throws FileNotFoundException
	 */
	public static Node[] readData(File file) throws FileNotFoundException{
		Node[] nodes;
		Scanner fileReader = new Scanner(file);
		ArrayList<String> tempData = new ArrayList<String>();
		String[] data;
		
		while(fileReader.hasNextLine()){					//Reads in all data line by line.
			tempData.add(fileReader.nextLine());
		}
		
		data = tempData.toArray(new String[0]);
		tempData.clear();									//Removes the temporary ArrayList to clear space.
		mergeSort(data);									//data is now sorted first by fromNodeID and then toNodeID.
		
		//Note: ensure proper data format, print out "Invalid file format" otherwise.
		//Step 1: Make array of ALL Nodes.
		//TODO implement
		//Step 1: read in the new Node.
		//Step 2: Binary search for the of the from Node's position to insert it.
		//Step 3: If we find the from Node's id, just append to that Node using a sorted insertion, otherwise, create new Node.
		//Step 4: Repeat steps 2 and 3 for adding the to Node.
		//Step 5: Lather, rinse, repeat until out of lines in the data file.
		fileReader.close();
		return new Node[0];
	}//readData()
	
	/**
	 * Writes to data using all the edges that we have stored in data.
	 * @param node
	 */
	public static void writeData(Node[] nodes, File file){
		Node[] children;
		String newline = System.getProperty("line.separator");
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < nodes.length; i++){
				children = nodes[i].getChildren();
				for(int j = 0; j < children.length; j++){
					writer.write(nodes[i] + "\t" + children[j] + newline);	//fromNodeID	toNodeID\n
				}
			}
			writer.close();
			System.out.println(file + " successfully written.");
		}catch(IOException ioe){
			System.out.println("Error writing to the file " + file + ".");
		}
	}//writeData()
	
	/**
	 * Runs through the supplied list of Nodes in order to test various methods. Writes the results of the tests
	 * to the passed in output file.
	 * @param graph
	 * @param file
	 */
	public static void writeResultFile(Node[] graph, File file){
		//TODO determine how we wish to output the results.
	}//writeResultFile()
	
	/**
	 * Implementation of merge sort using Strings.
	 * @param x
	 */
	private static void mergeSort(String[] x){
		merge(x);
	}//mergeSort()
	
	/**
	 * Recursive portion of mergeSort.
	 * @param x
	 * @return Sorted list of Strings.
	 */
	private static String[] merge(String[] x){
		String[] left, right;
		int currentL = 0, currentR = 0;
		
		if(x.length == 2){						//Base cases.
			if(x[0].compareTo(x[1]) > 0){
				return new String[] {x[1], x[0]};
			}else{
				return x;
			}
		}else if(x.length == 1){
			return x;
		}
		
		left = merge(Arrays.copyOfRange(x, 0, x.length/2));
        right = merge(Arrays.copyOfRange(x, x.length / 2, x.length));
        
        for(int i = 0; i < x.length; i++){		//Merges two sorted halves.
        	if(currentL >= left.length){
        		x[i] = right[currentR];
        		currentR++;
        	}else if(currentR >= right.length || left[currentL].compareTo(right[currentR]) < 0){
        		x[i] = left[currentL];
        		currentL++;
        	}else{
        		x[i] = right[currentR];
        		currentR++;
        	}
        }
        return x;
	}//merge()
	
}//DataLoader
