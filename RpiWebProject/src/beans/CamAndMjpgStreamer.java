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
	private static PrintWriter out;
	private static File logFile = new File("streamer.log");
	
	public CamAndMjpgStreamer(){
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
		BufferedWriter bufWriter = null;
		try {
			pb = new ProcessBuilder(scriptName, action);
			pb.directory(new File(scriptDir));
			pb.redirectErrorStream(true);
			Process p = pb.start();
			InputStream inputStream = p.getInputStream();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
			bufWriter = new BufferedWriter(out);
			for (String line = bufReader.readLine(); line != null; line = bufReader.readLine()) {
				bufWriter.write(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace(out);
			out.flush();
		} finally {
			try {
				bufWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
}
