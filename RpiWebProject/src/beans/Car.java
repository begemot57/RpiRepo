package beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Car {
	
	private static BufferedWriter bw;
	
	public Car(){
		try {
			if(bw == null){
//				String file = "C:/Leo/programs/eclipse_my/workspace/MyPythonProject/package/PrintTest.py";
//				ProcessBuilder pb = new ProcessBuilder("C:/cygwin/bin/python2.7.exe", file);
//				pb.directory(new File("C:/Leo/programs/eclipse_my/workspace/MyPythonProject/package"));
				
				String file = "/leo/pythontest/PrintTest.py";
				ProcessBuilder pb = new ProcessBuilder("python", file);
				pb.directory(new File("/leo/pythontest"));
				pb.redirectErrorStream(true);
				
				Process p = pb.start();
				OutputStream out = p.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(out));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void stop(){
		System.out.println("stop");
		move("s");
	}
	
	public void goForward() {
		System.out.println("go forward");
		move("f");
	}

	public void goBack() {
		System.out.println("go back");
		move("b");
	}

	public void goRight() {
		System.out.println("go right");
		move("r");
	}

	public void goLeft() {
		System.out.println("go left");
		move("l");
	}
	
	private void move(String m){
		try {
			bw.write(m);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
