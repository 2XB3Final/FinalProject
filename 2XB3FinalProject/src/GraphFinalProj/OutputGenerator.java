package GraphFinalProj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class OutputGenerator {

	public static void main(String[] args) throws FileNotFoundException {
	       PrintStream output = new PrintStream(new File("data/FinalProject_Output.txt"));
	       output.println("hi");
	       
	       output.close();

	}

}
