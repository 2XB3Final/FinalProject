package GraphFinalProj;

import java.io.PrintStream;

public class Scratch {
	
	public static void formatWatchOutput(PrintStream out,long srcTime, long toldTime, long heardTime, long endsTime, long firstTime) {
		long totalTime = srcTime + toldTime + heardTime + endsTime + firstTime;
		
		out.println(" ");
		out.println("-----------------------------------------");
		out.println(" ");
		out.println("Task Name \t\t Time (ms)");
		
	}

}
