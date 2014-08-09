package cam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class HouseConstructionMonitor {

	private static Process p;
	private static BufferedWriter bw;
	
	public static void main(String[] args) {
		HouseConstructionMonitor monitor = new HouseConstructionMonitor();
		monitor.run();
	}
	
	public void run(){
		
		int counter = 1;
		while(true){
			try {
//				ProcessBuilder pb = new ProcessBuilder("raspistill -o image"+ ++counter+".jpg");				
				ProcessBuilder pb = new ProcessBuilder("mkdir bla");				
				pb.directory(new File("/leo/cam/test"));
				
				pb.redirectErrorStream(true);
				p = pb.start();
				OutputStream out = p.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(out));
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
