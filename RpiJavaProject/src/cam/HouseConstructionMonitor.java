package cam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utils.ZipUtils;

/**
 * This class is meant to create a directory every day. In this directory it
 * will put N pictures taken by the cam. One pic per hour. After all pics are
 * done they are packaged up in a zip and sent out via e-mail.
 * 
 * @author Leo
 * 
 */
public class HouseConstructionMonitor {

	private static Process p;
	private static BufferedWriter bw;
	 private static String picsDir = "/leo/cam/housemonitor/";
//	private static String picsDir = "/Users/Leo/git/JavaRepo/JavaTestProject/files/";
	private static Calendar cal;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd");
	private static SimpleDateFormat timeFormat = new SimpleDateFormat(
			"HH:mm:ss");
	private static int[] workingHours = new int[] { 8, 9, 10, 11, 12, 13, 14,
			15, 16, 17, 18, 22};
	
	public static void main(String[] args) {
		HouseConstructionMonitor monitor = new HouseConstructionMonitor();
		monitor.run();
	}

	public void run() {
		while (true) {
			try {
				// check if today's pic dir is there or create one
				cal = Calendar.getInstance();
				String todaysDirName = dateFormat.format(cal.getTime());
				File todaysDir = new File(picsDir + todaysDirName);
				if (!todaysDir.exists()) {
					todaysDir.mkdir();
				}
				// check if pic for current hour is there already or it's bad
				// time to take pic now,
				// if not take a pic
				File[] filesInFolder = todaysDir.listFiles();
				int hoursNow = cal.get(Calendar.HOUR_OF_DAY);
				boolean takePic = false;
				boolean picAlreadyPresent = false;
				for (File file : filesInFolder) {
					file.getName().contains("pic" + hoursNow);
					picAlreadyPresent = true;
					break;
				}
				if (!picAlreadyPresent) {
					for (int h : workingHours) {
						if (hoursNow == h) {
							takePic = true;
							break;
						}
					}
				}
				if (takePic) {
					String picName = "pic" + timeFormat.format(cal.getTime());
					ProcessBuilder pb = new ProcessBuilder("raspistill", "-o",
							picName + ".jpg");
					pb.directory(new File(todaysDir.getAbsolutePath()));
					pb.redirectErrorStream(true);
					p = pb.start();
					OutputStream out = p.getOutputStream();
					bw = new BufferedWriter(new OutputStreamWriter(out));
				}
				
				//zip up all today's pics and send via e-mail
				String zipFileName = todaysDirName+".zip";
				ZipUtils appZip = new ZipUtils();
				appZip.generateFileList(new File(todaysDir.getAbsolutePath()));
				appZip.zipIt(todaysDir.getAbsolutePath()+"/"+zipFileName);
				
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}

}
