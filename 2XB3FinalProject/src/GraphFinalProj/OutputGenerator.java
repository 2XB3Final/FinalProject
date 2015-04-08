package GraphFinalProj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.commons.lang3.time.StopWatch;

public class OutputGenerator {

	public static void formatWatchOutput(PrintStream out, int vertices, long buildTime, long srcTime, long toldTime, long heardTime, long endsTime, long firstTime) {
		long totalTime = buildTime + srcTime + toldTime + heardTime + endsTime + firstTime;
		
		out.println(" ");
		out.println("------------------------------------------------------------");
		out.println(" ");
		out.println("Task Name \t\t Time (ms) \t\t % of Total Time");
		out.println(" ");
		out.println("------------------------------------------------------------");
		out.println(" ");
		out.println("Build digraph \t\t " + buildTime + " \t\t\t " + buildTime*100/totalTime + "%");
		out.println("getSource() \t\t " + srcTime + " \t\t\t " + srcTime*100/totalTime + "%");
		out.println("told() \t\t\t " + toldTime + " \t\t\t " + toldTime*100/totalTime + "%");
		out.println("heard() \t\t " + heardTime + " \t\t\t " + heardTime*100/totalTime + "%");
		out.println("deadEnds() \t\t " + endsTime + " \t\t\t " + endsTime*100/totalTime + "%");
		out.println("heardFromFirst() \t " + firstTime + " (avg:" + firstTime/vertices + ")" +  " \t\t " + firstTime*100/totalTime + "%");
		out.println(" ");
		out.println("------------------------------------------------------------");
		out.println("Total Time: " + totalTime + " ms");


	}

	public static void main(String[] args) throws FileNotFoundException {
		
		StopWatch watch = new StopWatch();
		watch.start();
		Digraph gtest = new Digraph("2XB3FinalProject/data/tinyDAG.txt");
		watch.stop();
		long buildTime1 = watch.getTime();
		watch.reset();
		watch.start();
		RandomIntDigraph ridtest = new RandomIntDigraph("2XB3FinalProject/data/tinyDAGST.txt");
		watch.stop();
		long buildTime2 = watch.getTime();
		watch.reset();
		PrintStream output = new PrintStream(new File("2XB3FinalProject/data/FinalProject_Output.txt"));
		
		output.println("tinyDAG.txt");
		output.println("-------");
		
		watch.start();
		ArrayList<Integer> out = Applications.getSource(gtest);
		watch.stop();
		long srcTime1 = watch.getTime();
		watch.reset();
		output.println("The sources are: " + out + ".");
		
		watch.start();
		out = Applications.told(gtest);
		watch.stop();
		long toldTime1 = watch.getTime();
		watch.reset();
		output.println("The people that told the rumour are: " + out + ".");
        
		watch.start();
        out = Applications.heard(gtest);
        watch.stop();
		long heardTime1 = watch.getTime();
		watch.reset();
        output.println("The people that heard the rumour are: " + out + ".");
        
		watch.start();
        out = Applications.deadEnds(gtest);
        watch.stop();
		long endsTime1 = watch.getTime();
		watch.reset();
        output.println("The people that heard but not told the rumour are " + out + ".");
        
        ///////////////////////////////////NEW
        output.println();
        for (int i = 0; i < gtest.V(); i++) {
        	output.println("Is " + i + " a spreader? " + Applications.isSpreader(gtest, i));
        }
        
        for (int i = 0; i < gtest.V(); i++) {
        	output.println("Is " + i + " a hearer? " + Applications.isHearer(gtest, i));
        }
        
        output.println();
        output.println("Role ");
        for (int i = 0; i < gtest.V(); i++) {
        	output.println(Applications.roleOf(gtest, i));
        }
        ///////////////////////////////////NEW END
        
        output.println();
        output.println("Heard from first list: ");
		watch.start();
        for (int i = 0; i < gtest.V(); i++) {
        	out = Applications.heardFromFirst(gtest, i);
        	if (out == null) {
            	output.println(i + " is a source.");
            }
            else {
            	output.println(i + " heard the rumour from " + out + " first.");
            }
        }
        watch.stop();
		long firstTime1 = watch.getTime();
		watch.reset();
		
		formatWatchOutput(output,gtest.V(),buildTime1,srcTime1,toldTime1,heardTime1,endsTime1,firstTime1);
		
        output.println();
        
        output.println("tinyDAGST.txt");
		output.println("-------------");
		
		watch.start();
		out = Applications.getSource(ridtest);
		watch.stop();
		srcTime1 = watch.getTime();
		watch.reset();
		output.println("The sources are: " + out + ".");
		
		watch.start();
		out = Applications.told(ridtest);
		watch.stop();
		toldTime1 = watch.getTime();
		watch.reset();
		output.println("The people that told the rumour are: " + out + ".");
        
		watch.start();
        out = Applications.heard(ridtest);
        watch.stop();
		heardTime1 = watch.getTime();
		watch.reset();
        output.println("The people that heard the rumour are: " + out + ".");
        
		watch.start();
        out = Applications.deadEnds(ridtest);
        watch.stop();
		endsTime1 = watch.getTime();
		watch.reset();
        output.println("The people that heard but not told the rumour are " + out + ".");
        
        output.println();
        output.println("Heard from first list: ");
        
		watch.start();
        for (int i = 0; i < ridtest.getKeys().length; i++) {
        	out = Applications.heardFromFirst(ridtest, ridtest.getKeys()[i]);
        	if (out == null) {
        		if (i == 0) {
        			output.println(ridtest.getKeys()[i] + " is a source.");
        		}
        		else {
        			output.println(ridtest.getKeys()[i] + " is a source.");
        		}
            }
            else {
            	output.println(ridtest.getKeys()[i] + " heard the rumour from " + out + " first.");
            }
        }
        watch.stop();
		firstTime1 = watch.getTime();
		watch.reset();
		
		formatWatchOutput(output,ridtest.G().V(),buildTime2,srcTime1,toldTime1,heardTime1,endsTime1,firstTime1);
		
		output.println();

		watch.start();
		ridtest = new RandomIntDigraph("2XB3FinalProject/data/cit-HepPh.txt");
		watch.stop();
		long buildTime3 = watch.getTime();
		watch.reset();
		output.println("cit-HepPh.txt");
		output.println("-------------");

		watch.start();
		out = Applications.getSource(ridtest);
		watch.stop();
		srcTime1 = watch.getTime();
		watch.reset();
		output.println("The sources are: " + out + ".");
		output.println();

		watch.start();
		out = Applications.told(ridtest);
		watch.stop();
		toldTime1 = watch.getTime();
		watch.reset();
		output.println("The people that told the rumour are: " + out + ".");
		output.println();

		watch.start();
		out = Applications.heard(ridtest);
		watch.stop();
		heardTime1 = watch.getTime();
		watch.reset();
		output.println("The people that heard the rumour are: " + out + ".");
		output.println();

		watch.start();
		out = Applications.deadEnds(ridtest);
		watch.stop();
		endsTime1 = watch.getTime();
		watch.reset();
		output.println("The people that heard but not told the rumour are " + out + ".");
		output.println();

		output.println();
		output.println("Heard from first list (sample of first 100): ");
		watch.start();
		for (int i = 0; i < 100; i++) {
			out = Applications.heardFromFirst(ridtest, ridtest.getKeys()[i]);
			if (out == null) {
				output.println(ridtest.getKeys()[i] + " is a source.");
			}
			else {
				output.println(ridtest.getKeys()[i] + " heard the rumour from " + out + " first.");
			}
		}
		watch.stop();
		firstTime1 = watch.getTime();
		watch.reset();
       
		formatWatchOutput(output,100,buildTime3,srcTime1,toldTime1,heardTime1,endsTime1,firstTime1);


		output.close();
	}
}