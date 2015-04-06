package finalProject;
/**
 * @author Eric Le Fort
 * @version 01
 * 
 * Abstract:
 * Handles all necessary operations involving reading and writing data.
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class DataLoader{

	/**
	 * Reads in from the data file in order to initialize the array of Nodes. The data will be of the form:
	 * fromNodeId + \t + toNodeId
	 * @param file
	 * @return The array of nodes that will store the data for the program.
	 * @throws FileNotFoundException
	 */
	public static Node[] readData(File file) throws FileNotFoundException{
		Node[] graph;
		Scanner fileReader = new Scanner(file);
		ArrayList<Node> tempGraph = new ArrayList<Node>();
		ArrayList<String> tempEdgeData = new ArrayList<String>(), tempNodeData = new ArrayList<String>();
		String[] edgeData;
		int[] nodeData;
		Node currentFromNode = null, currentToNode;
		String current, to, from;
		int index;

		while(fileReader.hasNextLine() && !(current = fileReader.nextLine()).equals("")){
			index = current.indexOf('\t');									//Finds the index of the tab.

			tempEdgeData.add(current);
			if(index != -1){												//Two Nodes in the line, add both.
				tempNodeData.add(current.substring(0, index));
				tempNodeData.add(current.substring(index + 1, current.length()));
			}else if(current.length() > 0){									//Only one Node, add that one.
				tempNodeData.add(current);
			}
		}
		fileReader.close();

		nodeData = toIntArray(tempNodeData);
		edgeData = tempEdgeData.toArray(new String[0]);
		tempEdgeData.clear();												//Removes the temporary ArrayLists to clear space.
		tempNodeData.clear();

		mergeSort(nodeData);												//Nodes are now sorted to avoid re-adding.

		try{
			tempGraph.add(new Node(Integer.valueOf(nodeData[0])));			//Creates all Nodes.
			for(int i = 1; i < nodeData.length; i++){
				if(nodeData[i-1] != nodeData[i]){
					tempGraph.add(new Node(Integer.valueOf(nodeData[i])));	//This Node is new, add it in.
				}
			}
		}catch(InputMismatchException ime){
			System.out.println("The file's formatting is corrupted.");
		}
		System.out.println("File read successfully.");
		
		nodeData = null;													//This array isn't needed anymore.
		graph = tempGraph.toArray(new Node[0]);								//Removes the temporary ArrayList.
		tempGraph.clear();

		current = null;
		for(int i = 0; i < edgeData.length; i++){
			if((index = edgeData[i].indexOf('\t')) != -1){
				from = edgeData[i].substring(0, index);
				to = edgeData[i].substring(index + 1, edgeData[i].length());
			}else{
				from = edgeData[i];
				to = "NOT A NUMBER";
			}
			if(current != null && !current.equals(from) || currentFromNode == null){	//Current holds previous from value.
				currentFromNode = GraphHandler.findNode(graph, Integer.parseInt(from));	//Need to find "from" Node.
			}
			try{
				currentToNode = GraphHandler.findNode(graph, Integer.parseInt(to));		//Finds "to" Node.
				if(currentToNode == null){												//"to" Node not found, making a new Node.
					currentToNode = new Node(Integer.parseInt(to));
				}
				currentFromNode.addChild(currentToNode);
			}catch(NumberFormatException nfe){ }										//This edge defines a Node with no children.
			current = from;
		}
		
		for(int i = 0; i < graph.length; i++){											//Sorts the children of each Node.
			graph[i].trimAndSortArrays();
		}

		return graph;
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
			if(children.length == 0){									//NodeID
					writer.write(nodes[i] + newline);
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
	 * Implementation of merge sort for ints.
	 * @param x
	 */
	public static void mergeSort(int[] x){
		merge(x);
	}//mergeSort()

	/**
	 * Implementation of merge sort for Nodes.
	 * @param x
	 */
	public static void mergeSort(Node[] nodes){
		merge(nodes);
	}//mergeSort()
	
	/**
	 * Recursive portion of mergeSort.
	 * @param x
	 * @return Sorted list of Strings.
	 */
	private static Node[] merge(Node[] x){
		Node[] left, right;
		Node temp;
		int currentL = 0, currentR = 0;

		if(x.length == 2){						//Base cases.
			if(x[0].compareTo(x[1]) > 0){
				temp = x[0];
				x[0] = x[1];
				x[1] = temp;
				return x;
			}else{
				return x;
			}
		}else if(x.length < 2){
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
	
	/**
	 * Recursive portion of mergeSort.
	 * @param x
	 * @return Sorted list of ints.
	 */
	private static int[] merge(int[] x){
		int[] left, right;
		int currentL = 0, currentR = 0;

		if(x.length == 2){						//Base cases.
			if(x[0] > x[1]){
				return new int[] {x[1], x[0]};
			}else{
				return x;
			}
		}else if(x.length < 2){
			return x;
		}

		left = merge(Arrays.copyOfRange(x, 0, x.length/2));
		right = merge(Arrays.copyOfRange(x, x.length / 2, x.length));

		for(int i = 0; i < x.length; i++){		//Merges two sorted halves.
			if(currentL >= left.length){
				x[i] = right[currentR];
				currentR++;
			}else if(currentR >= right.length || left[currentL] < right[currentR]){
				x[i] = left[currentL];
				currentL++;
			}else{
				x[i] = right[currentR];
				currentR++;
			}
		}
		return x;
	}//merge()

	/**
	 * Converts all the elements in the given 
	 * @param array
	 * @return An int array containing all the values represented in the String array passed in.
	 */
	private static int[] toIntArray(ArrayList<String> array){
		int[] newArray = new int[array.size()];
		for(int i = 0; i < newArray.length; i++){
			newArray[i] = Integer.valueOf(array.get(0));
			array.remove(0);
		}
		return newArray;
	}//convertToIntArray()	

}//DataLoader
