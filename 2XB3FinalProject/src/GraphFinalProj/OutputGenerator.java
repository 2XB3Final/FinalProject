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
		output.println("The sources are: " + out + ".");
		
		out = Applications.told(gtest);
		output.println("The people that told the rumour are: " + out + ".");
        
        out = Applications.heard(gtest);
        output.println("The people that heard the rumour are: " + out + ".");
        
        out = Applications.deadEnds(gtest);
        output.println("The people that heard but not told the rumour are " + out + ".");
        
        output.println();
        output.println("Heard from first list: ");
        for (int i = 0; i < gtest.V(); i++) {
        	out = Applications.heardFromFirst(gtest, i);
        	if (out == null) {
            	output.println(i + " is a source.");
            }
            else {
            	output.println(i + " heard the rumour from " + out + " first.");
            }
        }
        output.println();
        
        output.println("tinyDAGST.txt");
		output.println("-------------");
		
		out = Applications.getSource(ridtest);
		output.println("The sources are: " + out + ".");
		
		out = Applications.told(ridtest);
		output.println("The people that told the rumour are: " + out + ".");
        
        out = Applications.heard(ridtest);
        output.println("The people that heard the rumour are: " + out + ".");
        
        out = Applications.deadEnds(ridtest);
        output.println("The people that heard but not told the rumour are " + out + ".");
        
        output.println();
        output.println("Heard from first list: ");
        for (int i = 0; i < ridtest.G().V(); i++) {
        	out = Applications.heardFromFirst(ridtest, i);
        	if (out == null) {
            	output.println(i + " is a source.");
            }
            else {
            	output.println(i + " heard the rumour from " + out + " first.");
            }
        }

		output.close();
	}
}