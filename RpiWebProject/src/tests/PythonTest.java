package tests;

import java.io.*;

public class PythonTest {

	public static void main(String[] args) {
		PythonTest test = new PythonTest();
//		test.runRuntime();
//		test.runProcess();	
		test.runProcessOnExistngFile();
		
	}

	void runProcessOnExistngFile() {
		try {
			String file = "C:/Leo/programs/eclipse_my/workspace/MyPythonProject/package/PrintTest.py";
			ProcessBuilder pb = new ProcessBuilder("C:/cygwin/bin/python2.7.exe", file);
			pb.directory(new File("C:/Leo/programs/eclipse_my/workspace/MyPythonProject/package"));
			Process p = pb.start();
			
			OutputStream out = p.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			bw.write('f');
			bw.newLine();
			bw.flush();
			Thread.sleep(2000);
			bw.write('b');
			bw.close();
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = in.readLine();
			String ret = "";
			while(line != null){
				ret += line +"\n";
				line = in.readLine();
			}
			System.out.println("value is : " + ret);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void runRuntime() {
		try {

			String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
			BufferedWriter out = new BufferedWriter(new FileWriter(
					"C:/Leo/test1.py"));
			out.write(prg);
			out.close();
			int number1 = 10;
			int number2 = 32;
			Process p = Runtime.getRuntime().exec(
					"python2.7 C:/Leo/test1.py " + number1 + " " + number2);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			int ret = new Integer(in.readLine()).intValue();
			System.out.println("value is : " + ret);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void runProcess() {
		try {
			String file = "C:/Leo/test2.py";
			String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
			BufferedWriter out = new BufferedWriter(new FileWriter(
					file));
			out.write(prg);
			out.close();
			int number1 = 10;
			int number2 = 32;

			ProcessBuilder pb = new ProcessBuilder("C:/cygwin/bin/python2.7.exe",
					file, "" + number1, "" + number2);
			Process p = pb.start();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			int ret = new Integer(in.readLine()).intValue();
			System.out.println("value is : " + ret);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
