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
		
		output.println("tinyDAG.txt");
		output.println("-------");
		
		ArrayList<Integer> out = Applications.getSource(gtest);
		output.println("The list of sources are: " + out + ".");
		
		out = Applications.told(gtest);
		output.println("The list of people that told the rumour are: " + out + ".");
        
        out = Applications.heard(gtest);
        output.println("The list of people that heard the rumour are: " + out + ".");
        
        out = Applications.deadEnds(gtest);
        output.println("The list of people that heard but not told the rumour are " + out + ".");
        
        out = Applications.heardFromFirst(gtest, 1);
        if (out == null) {
        	output.println("1 is a source.");
        }
        else {
        	output.println("1 heard the rumour from " + out + " first.");
        }
        out = Applications.heardFromFirst(gtest, 2);
        if (out == null) {
        	output.println("2 is a source.");
        }
        else {
        	output.println("2 heard the rumour from " + out + " first.");
        }
        output.println();
        
        output.println("tinyDAGST.txt");
		output.println("-------------");
		
		out = Applications.getSource(ridtest);
		output.println("The list of sources are: " + out + ".");
		
		out = Applications.told(ridtest);
		output.println("The list of people that told the rumour are: " + out + ".");
        
        out = Applications.heard(ridtest);
        output.println("The list of people that heard the rumour are: " + out + ".");
        
        out = Applications.deadEnds(ridtest);
        output.println("The list of people that heard but not told the rumour are " + out + ".");
        
        out = Applications.heardFromFirst(ridtest, 1200);
        if (out == null) {
        	output.println("1200 is a source.");
        }
        else {
        	output.println("1200 heard the rumour from " + out + " first.");
        }
        out = Applications.heardFromFirst(ridtest, 200);
        if (out == null) {
        	output.println("200 is a source.");
        }
        else {
        	output.println("200 heard the rumour from " + out + " first.");
        }

		output.close();
	}
}