package beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Car {
	
	private static BufferedWriter bw;
	private static Process p;
	private static double speed = 1;
	
	public Car(){
		startProcess();
	}
	
	private void startProcess(){
		try {
			if(bw == null){
//				String file = "C:/Leo/programs/eclipse_my/workspace/MyPythonProject/package/PrintTest.py";
//				ProcessBuilder pb = new ProcessBuilder("C:/cygwin/bin/python2.7.exe", file);
//				pb.directory(new File("C:/Leo/programs/eclipse_my/workspace/MyPythonProject/package"));
				
//				String file = "PrintTest.py";
//				ProcessBuilder pb = new ProcessBuilder("python", file);				
//				pb.directory(new File("/leo/pythontest"));
				
				String file = "motorspeed.py";
				ProcessBuilder pb = new ProcessBuilder("python", file);				
				pb.directory(new File("/leo/git/RpiRepo/RpiPythonProject/rpi/motor"));
				
				pb.redirectErrorStream(true);
				p = pb.start();
				OutputStream out = p.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(out));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop(){
		startProcess();
		System.out.println("stop");
		move("stop");
//		try {
//			System.out.println("after stop");
//			bw.close();
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					p.getInputStream()));
//			String line;
//			line = in.readLine();
//			System.out.println("after readLine()");
//			String ret = "";
//			while(line != null){
//				ret += line +"\n";
//				line = in.readLine();
//			}
//			System.out.println("value is : " + ret);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void goForward() {
		startProcess();
		System.out.println("go forward");
		move("e");
	}

	public void goBack() {
		startProcess();
		System.out.println("go back");
		move("d");
	}

	public void goRight() {
		startProcess();
		System.out.println("go right");
		move("f");
	}

	public void goLeft() {
		startProcess();
		System.out.println("go left");
		move("s");
	}
	
	public void off() {
		startProcess();
		System.out.println("off");
		move("off");
		bw = null;
	}
	
	public void goSlower(){
		startProcess();
		System.out.println("go slower: speed "+speed);
		if(speed >= 0.1)
			speed -= 0.1;
		move("speed "+speed);
	}
	
	public void goFaster(){
		startProcess();
		System.out.println("go faster: speed "+speed);
		if(speed <= 0.9)
			speed += 0.1;
		move("speed "+speed);
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
	
	public static void main(String[] args) {
		Car car = new Car();
		car.goForward();
		car.goLeft();
		car.goRight();
		car.stop();
	}

}
