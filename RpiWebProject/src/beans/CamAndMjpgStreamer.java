package beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CamAndMjpgStreamer {
	
	private static ProcessBuilder pb;
	private static final String scriptDir = "/leo/git/RpiRepo/RpiJavaProject/scripts";
	private static final String scriptName = "./cam_and_mjpg_streamer.sh";
	// out stream
	private static PrintWriter out = null;
	private static BufferedWriter bufWriter = null;
	private static File logFile = new File("streamer.log");
	
	public CamAndMjpgStreamer(){
		if(out == null){
			try {
				out = new PrintWriter(logFile);
				bufWriter = new BufferedWriter(out);
				out.write("JavaBean: Init CamAndMjpgStreamer\n");
				out.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace(out);
				out.flush();
			}
		}
	}
	
	public void start() {
		out.write("JavaBean: Start pic stream and server\n");
		out.flush();
		execute("start");
	}
	
	public void stop(){
		out.write("JavaBean: Stop pic stream and server\n");
		out.flush();
		execute("stop");
	}
	
	public String checkState(){
		out.write("JavaBean: Check state of pic stream and server\n");
		out.flush();
		return execute("checkstate");
	}

	String execute(String action){
		String state = "";
		try {
			pb = new ProcessBuilder(scriptName, action);
			pb.directory(new File(scriptDir));
			pb.redirectErrorStream(true);
			Process p = pb.start();
			InputStream inputStream = p.getInputStream();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
			for (String line = bufReader.readLine(); line != null; line = bufReader.readLine()) {
				state = line;
				bufWriter.write(line + "\n");
			}
			bufWriter.flush();
		} catch (IOException e) {
			e.printStackTrace(out);
			out.flush();
		}
		return state;
	}
}
