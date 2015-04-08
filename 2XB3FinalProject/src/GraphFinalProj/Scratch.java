package GraphFinalProj;

import java.io.PrintStream;

public class Scratch {
	
	public static void formatWatchOutput(PrintStream out,long srcTime, long toldTime, long heardTime, long endsTime, long firstTime) {
		long totalTime = srcTime + toldTime + heardTime + endsTime + firstTime;
		
		out.println(" ");
		out.println("-----------------------------------------");
		out.println(" ");
		out.println("Task Name \t\t Time (ms) \t\t % Total Time");
		out.println(" ");
		out.println("-----------------------------------------");
		out.println(" ");
		out.println("getSource() \t\t " + srcTime + " \t\t " + srcTime*100/totalTime + "%");
		out.println("told() \t\t " + toldTime + " \t\t " + toldTime*100/totalTime + "%");
		out.println("heard() \t\t " + heardTime + " \t\t " + heardTime*100/totalTime + "%");
		out.println("deadEnds() \t\t " + endsTime + " \t\t " + endsTime*100/totalTime + "%");
		out.println("heardFromFirst() \t\t " + firstTime + " \t\t " + firstTime*100/totalTime + "%");
		out.println(" ");
		out.println("-----------------------------------------");
		out.println("Total Time: " + totalTime + " ms");


	}

}
