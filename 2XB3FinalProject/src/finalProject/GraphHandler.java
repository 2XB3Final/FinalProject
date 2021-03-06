package finalProject;
/**
 * @author Eric Le Fort
 * @version 01
 */
import java.util.ArrayList;
import java.util.Arrays;

public abstract class GraphHandler{

	/**
	 * Traces back the shortest path from the given node to determine the origin of where it received its
	 * information.
	 * @param nodeID
	 * @param graph
	 * @return The path to the origin of where the given Node got its information.
	 */
	public static Node[] traceBackToOrigin(Node[] graph, int nodeID){
		Node node = findNode(graph, nodeID);
		//TODO implement
		return new Node[0];
	}//traceBackToOrigin()

	/**
	 * Traces back the node passed in to determine the original source of its origin.
	 * @param graph
	 * @param nodeID
	 * @return The Node from which the one passed in originated.
	 */
	public static Node findOrigin(Node[] graph, int nodeID){
		return traceBackToOrigin(graph, nodeID)[0];		//Returns the first node in the array holding all those that continued the array.
	}//findOrigin()
	
	/**
	 * Finds all the dead ends, those Nodes with no children, in this graph.
	 * @param graph
	 * @return An array of nodes that are dead ends.
	 */
	public static Node[] findDeadEnds(Node[] graph){
		ArrayList<Node> deadEnds = new ArrayList<Node>();
		for(int i = 0; i < graph.length; i++){
			if(graph[i].getNumChildren() == 0){		//If the Node has no children, they're a dead end.
				deadEnds.add(graph[i]);
			}
		}
		return deadEnds.toArray(new Node[0]);
	}//findDeadEnds()
	
	/**
	 * Finds all the non dead ends, those Nodes with children, in this graph.
	 * @param graph
	 * @return An array holding all Nodes that aren't dead ends.
	 */
	public static Node[] findNonDeadEnds(Node[] graph){
		ArrayList<Node> deadEnds = new ArrayList<Node>();
		for(int i = 0; i < graph.length; i++){
			if(graph[i].getNumChildren() != 0){		//If the Node has children, they're not a dead end.
				deadEnds.add(graph[i]);
			}
		}
		return deadEnds.toArray(new Node[0]);
	}//findNonDeadEnds()
	
	/**
	 * Looks through the graph represented by the array of Nodes passed in to determine if it has any cycles.
	 * @return Whether or not the graph is acyclic.
	 */
	public static boolean isCyclic(Node[] graph){
		//TODO implement
		return false;
	}//isCyclic()
	
	/**
	 * Looks for a Node with the same nodeID as the one passed in. If the specified Node isn't in the
	 * graph, returns null. Otherwise, this method returns an array of all the children of this Node.
	 * @param graph
	 * @param nodeID
	 * @return The children of the specified Node or null if the Node can't be found.
	 */
	public static Node[] findChildren(Node[] graph, int nodeID){
		Node node = findNode(graph, nodeID);
		if(node == null){ return null; }
		return node.getChildren();
	}//findChildren()
	
	/**
	 * Looks for a Node with the same nodeID as the one passed in. If the specified Node isn't in the
	 * graph, returns null. Otherwise, this method returns an array of all the parents of this Node.
	 * @param graph
	 * @param nodeID
	 * @return The parents of the specified Node or null if the Node can't be found.
	 */
	public static Node[] findParents(Node[] graph, int nodeID){
		Node node = findNode(graph, nodeID);
		if(node == null){ return null; }
		return node.getParents();
	}//findParents()
	
	/**
	 * Searches for a Node with the specified ID value and returns a Node with a matching nodeID.
	 * If no such Node is located, null will be returned.
	 * Assumes array passed in is sorted.
	 * @param graph
	 * @param nodeID
	 * @return The Node with the given nodeID or null if it can't be found.
	 */
	public static Node findNode(Node[] graph, int nodeID){
		if(graph.length == 1){							//One element left to check.
			if(graph[0].getID() != nodeID){				//Not found..
				return null;
			}
			return graph[0];							//We found it!
		}
		
		if(graph[graph.length / 2].getID() == nodeID){	//We found it!
			return graph[graph.length / 2];
		}
		if(graph[graph.length / 2].getID() > nodeID){
			return findNode(Arrays.copyOfRange(graph, 0, graph.length / 2), nodeID);
		}else{
			return findNode(Arrays.copyOfRange(graph, graph.length / 2, graph.length), nodeID);
		}
	}//findNode()
	
}//GraphHandler
