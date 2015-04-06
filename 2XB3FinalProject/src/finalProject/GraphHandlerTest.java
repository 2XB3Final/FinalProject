package finalProject;
/**
 * @author Eric Le Fort
 * @version 01
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class GraphHandlerTest{
	Node[][] graphs = {
			{new Node(5), new Node(4), new Node(3), new Node(2), new Node(1), new Node(0)},
			{},
			{}
		};
	
	@Test
	public void findNodeTest(){
		DataLoader.mergeSort(graphs[0]);
		for(int i = 0; i < 6; i++){
			assertTrue(i == GraphHandler.findNode(graphs[0], i).getID());
		}
		for(int i = 6; i < 25; i++){
			assertTrue(GraphHandler.findNode(graphs[0], i) == null);
		}
	}//findNodeTest()

}//GraphHandlerTest()
