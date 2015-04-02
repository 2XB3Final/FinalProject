package finalProject;
/**
 * @author Eric Le Fort
 * @version 01
 */
import java.io.File;

public class GraphHandler{

	public static void main(){
		File file = new File("");//TODO enter file location.
		Node[] data = DataLoader.readData(file);
	}//main()

	/**
	 * Traces back the shortest path from the given node to determine the origin of where it received its
	 * information.
	 * @param node
	 * @return The path to the origin of where the given Node got its information.
	 */
	public static Node[] traceBackToOrigin(Node node){
		
		Node[] parents = node.getParents();

		if(parents.length == 0){
			return new Node[]{node};
		}
		//TODO implement
		return new Node[0];
	}//traceBackToOrigin()

	/**
	 * Traces back the node passed in to determine the original source of its origin.
	 * @param node
	 * @return The Node from which the one passed in originated.
	 */
	public static Node findOrigin(Node node){

		//TODO implement
		return traceBackToOrigin(node)[0];		//Returns the first node in the array holding all those that continued the array.
	}//findOrigin()
	
	/**
	 * Finds all the dead ends, those Nodes with no children, in this graph.
	 * @return An array of nodes that have no children.
	 */
	public static Node[] findDeadEnds(){
		//TODO implement
		return new Node[0];
	}//findDeadEnds()
	
	/**
	 * Looks through the graph represented by the array of Nodes passed in to determine if it has any cycles.
	 * @return Whether or not the graph is acyclic.
	 */
	public static boolean isCyclic(Node[] graph){
		//TODO implement
		return false;
	}//isCyclic()
	
}//GraphHandler
