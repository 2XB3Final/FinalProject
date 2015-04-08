package GraphFinalProj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class OutputGenerator {

	public static void main(String[] args) throws FileNotFoundException {
		Digraph gtest = new Digraph("2XB3FinalProject/data/tinyDAG.txt");
		RandomIntDigraph ridtest = new RandomIntDigraph("2XB3FinalProject/data/tinyDAGST.txt");
		PrintStream output = new PrintStream(new File("2XB3FinalProject/data/FinalProject_Output.txt"));
		
		output.println("DIGRAPH");
		output.println("-------");
		
		ArrayList<Integer> out = Applications.getSource(gtest);
		output.println("The list of sources are: " + out + ".");
		
		out = Applications.told(gtest);
		output.println("The list of people that told the rumour are: " + out + ".");
        
        out = Applications.heard(gtest);
        output.println("The list of people that heard the rumour are: " + out + ".");
        
        out = Applications.deadEnds(gtest);
        output.println("The list of people that heard but not told the rumour are " + out + ".");

		output.close();

	}

}
