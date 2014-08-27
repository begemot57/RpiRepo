package beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class HouseMonitor {
	
	private static ProcessBuilder pb;
	private static final String scriptDir = "/leo/git/RpiRepo/RpiJavaProject/scripts";
	private static final String scriptName = "./housemonitor.sh";
	// out stream
	private static PrintWriter out;
	private static BufferedWriter bufWriter = null;
	private static File logFile = new File("housemonitor.log");
	
	public HouseMonitor(){
		if(pb == null){
			try {
				out = new PrintWriter(logFile);
				bufWriter = new BufferedWriter(out);
				out.write("JavaBean: Init house moinitor object\n");
				out.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace(out);
				out.flush();
			}
		}
	}
	
	public void start() {
		out.write("JavaBean: Detected Start button click\n");
		out.flush();
		execute("start");
	}
	
	public void stop(){
		out.write("JavaBean: Detected Stop button click\n");
		execute("stop");
	}

	void execute(String action){
		try {
			pb = new ProcessBuilder(scriptName, action);
			pb.directory(new File(scriptDir));
			out.write("JavaBean: run process: "+scriptName+" "+action+"\n");
			out.flush();
			pb.redirectErrorStream(true);
			Process p = pb.start();
			InputStream inputStream = p.getInputStream();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
			for (String line = bufReader.readLine(); line != null; line = bufReader.readLine()) {
				bufWriter.write(line + "\n");
			}
			bufWriter.flush();
		} catch (IOException e) {
			e.printStackTrace(out);
			out.flush();
		} 
	}
}
