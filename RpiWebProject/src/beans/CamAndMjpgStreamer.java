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
	private static final String script = "/leo/git/RpiRepo/RpiJavaProject/scripts/cam_and_mjpg_streamer.sh";
	// out stream
	private static PrintWriter out;
	
	public CamAndMjpgStreamer(){
		try {
			out = new PrintWriter(new File("streamer.log"));
			out.write("CamAndMjpgStreamer initialized");
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace(out);
			out.flush();
		}
	}
	
	public void start() {
		out.write("start pic stream and server");
		out.flush();
		execute("start");
	}
	
	public void stop(){
		out.write("stop pic stream and server");
		execute("stop");
	}

	void execute(String action){
		try {
			if(bw == null){
				ProcessBuilder pb = new ProcessBuilder(script, action);
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
				out.write("Close file");
				out.flush();
				out.close();
			}
		}
	}
}
