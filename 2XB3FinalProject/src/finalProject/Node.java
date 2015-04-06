package finalProject;
/**
 * @author Eric Le Fort
 * @version 01
 * 
 * Abstract:
 * Each Node has a list of parents and children as well as an ID value. The children represent who the information has been
 * spread to and the parents represent who the information has come from.
 */

public class Node{
	private Node[] parents;
	private Node[] children;
	private int nodeID, numChildren, numParents;

	/**
	 * Creates a new Node that will be identified using the provided id.
	 * @param nodeID
	 */
	public Node(int nodeID){
		this.nodeID = nodeID;
		numChildren = 0;
		numParents = 0;
		parents = new Node[5];
		children = new Node[5];
	}//Constructor

	/**
	 * Adds the specified Node as a child of this Node, adds this Node as a parent of the child node.
	 * @param child
	 */
	public void addChild(Node child){
		if(numChildren >= children.length){			//The children array is full, must be enlarged.
			enlargeArrays(5);
		}
		children[numChildren] = child;
		child.addParent(this);						//Includes this node as the parent of its child.
		numChildren++;
	}//addChild()

	/**
	 * Adds the specified Node as a parent of this Node.
	 * @param parent
	 */
	private void addParent(Node parent){
		if(numParents >= parents.length){			//The parents array is full, must be enlarged.
			enlargeArrays(5);
		}
		parents[numParents] = parent;
		numParents++;
	}//addParent()

	/**
	 * Copies over the contents of the old arrays into new ones that are larger by the amount specified by the parameter
	 * passed in.
	 * Assumes that the integer passed in is positive.
	 * @param numToAdd
	 */
	private void enlargeArrays(int numToAdd){
		Node[] tempChildren = children, tempParents = parents;
		parents = new Node[parents.length + numToAdd];
		children = new Node[parents.length + numToAdd];

		for(int i = 0; i < numChildren; i++){		//Copies over the contents of children.
			children[i] = tempChildren[i];
		}

		for(int i = 0; i < numParents; i++){		//Copies over the contents of parents.
			parents[i] = tempParents[i];
		}
	}//enlargeArrays()

	/**
	 * Removes null entries in the children and parent arrays.
	 * Note: this method is liable to increase computation down the road as the arrays will be full.
	 */
	public void trimArrays(){
		Node[] tempParents = new Node[numParents], tempChildren = new Node[numChildren];

		for(int i = 0; i < numParents; i++){
			tempParents[i] = parents[i];
		}
		for(int i = 0; i < numChildren; i++){
			tempChildren[i] = children[i];
		}
		
		parents = tempParents;
		children = tempChildren;
	}//trimArrays()

	/**
	 * Checks to see whether the given Object is equivalent to this Node.
	 * @return Whether the two Nodes are equivalent.
	 */
	@Override
	public boolean equals(Object obj){ return obj instanceof Node && ((Node)obj).nodeID == nodeID; }//equals()

	/**
	 * Compares this node to that Node. Returns 1 if this Node is larger, 0 if the Nodes are equal and -1 if this
	 * Node is smaller.
	 * @param that
	 * @return 1, -1 or 0;
	 */
	public int compareTo(Node that){
		if(nodeID > that.nodeID){
			return 1;
		}else if(nodeID < that.nodeID){
			return -1;
		}
		return 0;
	}//compareTo()

	/**
	 * Returns this Node's ID value as a String.
	 * @return A String representation of this Node.
	 */
	@Override
	public String toString(){ return Integer.toString(nodeID); }//toString()

	// Getters & Setters //
	public Node[] getParents(){ return parents; }//getParents()
	public Node[] getChildren(){ return children; }//getChildren()
	public int getID(){ return nodeID; }//getID()
	public int getNumParents(){ return numParents; }//getNumParents()
	public int getNumChildren(){ return numChildren; }//getNumChildren()
}//Node
