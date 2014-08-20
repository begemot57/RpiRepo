package beans;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CamAndMjpgStramer {
	
	private static BufferedWriter bw;
	ProcessBuilder pb;
	private static final String script = "/leo/git/RpiRepo/RpiJavaProject/scripts/cam_and_mjpg_streamer.sh";
	
	public void start() {
		System.out.println("start pic stream and server");
		execute("start");
	}
	
	public void stop(){
		System.out.println("stop pic stream and server");
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
			e.printStackTrace();
		}
	}
}
