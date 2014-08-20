package beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CamAndMjpgStreamer {
	
	private static BufferedWriter bw;
	ProcessBuilder pb;
	private static final String scriptDir = "/leo/git/RpiRepo/RpiJavaProject/scripts";
	private static final String scriptName = "/cam_and_mjpg_streamer.sh";
	// out stream
	private static PrintWriter out;
	
	public CamAndMjpgStreamer(){
		try {
			out = new PrintWriter(new File("streamer.log"));
			out.write("CamAndMjpgStreamer initialized\n");
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace(out);
			out.flush();
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
				ProcessBuilder pb = new ProcessBuilder(scriptName, action);
				pb.directory(new File(scriptDir));
				pb.redirectErrorStream(true);
				Process p = pb.start();
				OutputStream out = p.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(out));
			}
		} catch (IOException e) {
			e.printStackTrace(out);
			out.flush();
		} finally {
			if (out != null) {
				out.write("Close file\n");
				out.flush();
				out.close();
			}
		}
	}
}
