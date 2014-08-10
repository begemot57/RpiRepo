package cam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import utils.EmailAttachmentSender;
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
	 private static final String picsDir = "/leo/cam/housemonitor/";
//	private static String picsDir = "/Users/Leo/git/JavaRepo/JavaTestProject/files/";
	private static Calendar cal;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
			"HH:mm:ss");
	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
			"E yyyy.MM.dd HH:mm:ss");
//	private static final int[] workingHours = new int[] { 8, 9, 10, 11, 12, 13, 14,
//			15, 16, 17, 18, 22};
	
	private static final int[] workingHours = new int[] {14};
	
	// SMTP info
	private static final String host = "smtp.gmail.com";
	private static final String port = "587";
	private static final String mailFrom = "leoio1953@gmail.com";
	private static final String password = "passw0rd1953";
    // message info
	private static final String mailTo = "begemot57@hotmail.com";
	private static final String subject = "House monitor pics: ";
	private static final String message = "Today's pics are attached.";
    // attachments
	private static String[] attachFiles = new String[1];
	
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
					System.out.println("Created today's dir: "+todaysDir.getAbsolutePath());
				}
				// check if pic for current hour is there already or it's bad
				// time to take pic now,
				// if not take a pic
				File[] filesInFolder = todaysDir.listFiles();
				int hoursNow = cal.get(Calendar.HOUR_OF_DAY);
				System.out.println("hoursNow: "+hoursNow);
				boolean takePic = false;
				boolean picAlreadyPresent = false;
				for (File file : filesInFolder) {
					file.getName().contains("pic" + hoursNow);
					picAlreadyPresent = true;
					System.out.println("Pic for current hour is already present: "+file.getName());
					break;
				}
				if (!picAlreadyPresent) {
					for (int h : workingHours) {
						if (hoursNow == h) {
							takePic = true;
							System.out.println("Current hour is working hour: "+hoursNow);
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
					System.out.println("Taking pic: "+picName + ".jpg");
				}
				
				//zip up all today's pics and send via e-mail
				//need to sleep first before last taken pic goes in
				if(hoursNow==workingHours[workingHours.length-1]){
					Thread.sleep(10000);
					String zipFileName = todaysDirName+".zip";
					String pathToZip = picsDir+zipFileName;
					ZipUtils.createZip(pathToZip, todaysDir.getAbsolutePath());
					System.out.println("Created zip file with pics: "+pathToZip);
			        attachFiles[0] = pathToZip;
					EmailAttachmentSender.sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
					        subject+todaysDirName, message, attachFiles);
					System.out.println("Email sent to "+mailTo);
				}
				//sleep for one hour
				cal = Calendar.getInstance();
				System.out.println("Go to sleep for one hour at: "+dateTimeFormat.format(cal.getTime()));
				Thread.sleep(3600000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
