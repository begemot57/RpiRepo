package beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class HouseMonitor {
	
	private static BufferedWriter bw;
	private static ProcessBuilder pb;
	private static final String scriptDir = "/leo/git/RpiRepo/RpiJavaProject/scripts";
	private static final String scriptName = "./housemonitor.sh";
	// out stream
	private static PrintWriter out;
	private static File logFile = new File("streamer.log");
	
	public HouseMonitor(){
		if(pb == null){
			try {
				out = new PrintWriter(logFile);
				out.write("CamAndMjpgStreamer initialized\n");
				out.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace(out);
				out.flush();
			}
		}
	}
	
	public void start() {
		out.write("start pic stream and server\n");
		out.flush();
		execute("start");
	}
	
	public void stop(){
		out.write("stop pic stream and server\n");
		execute("stop");
	}

	void execute(String action){
		try {
			if(bw == null){
				pb = new ProcessBuilder(scriptName, action);
				pb.directory(new File(scriptDir));
				pb.redirectErrorStream(true);
//				pb.redirectInput(logFile);
				Process p = pb.start();
//				OutputStream pbOut = p.getOutputStream();
//				bw = new BufferedWriter(new OutputStreamWriter(pbOut));
			}
		} catch (IOException e) {
			e.printStackTrace(out);
			out.flush();
		} 
	}
}
