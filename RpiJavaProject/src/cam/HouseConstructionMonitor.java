package cam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	private static Calendar cal;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd");
	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
			"E yyyy.MM.dd 'at' HH:mm:ss");
	private static final SimpleDateFormat numbersOnlyFormat = new SimpleDateFormat(
			"yyyyMMdd'_'HHmm");
	private static final int[] workingHours = new int[] { 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18 };

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
	// out stream
	private static PrintWriter out;

	public static void main(String[] args) {
		HouseConstructionMonitor monitor = new HouseConstructionMonitor();
		monitor.run();
	}

	public void run() {
		try {
			// setup out stream
			cal = Calendar.getInstance();
			String log_file_name = numbersOnlyFormat.format(cal.getTime())
					.concat(".log");
			out = new PrintWriter(new File(log_file_name));
			String processId = ManagementFactory.getRuntimeMXBean().getName();
			out.write("Current process id: " + processId + "\n");
			out.flush();
			while (true) {
				// check if current hour is working hour
				boolean isWorkingHourNow = false;
				int hoursNow = cal.get(Calendar.HOUR_OF_DAY);
				for (int h : workingHours) {
					if (hoursNow == h) {
						isWorkingHourNow = true;
						out.write("Current hour is working hour: " + hoursNow
								+ "\n");
						out.flush();
						break;
					}
				}

				if (isWorkingHourNow) {
					// check if today's pic dir is there or create one
					cal = Calendar.getInstance();
					String todaysDirName = dateFormat.format(cal.getTime());
					File todaysDir = new File(picsDir + todaysDirName);
					if (!todaysDir.exists()) {
						todaysDir.mkdir();
						out.write("Created today's dir: "
								+ todaysDir.getAbsolutePath() + "\n");
						out.flush();
					}
					// check if pic for current hour is there already or it's
					// bad
					// time to take pic now,
					// if not take a pic
					File[] filesInFolder = todaysDir.listFiles();
					boolean takePic = false;
					String picHour;
					for (File file : filesInFolder) {
						// pic name has this format "20140815_1252" we extract
						// hous here
						picHour = file.getName().substring(
								file.getName().length() - 8,
								file.getName().length() - 6);
						if (picHour.equals(Integer.toString(hoursNow))) {
							takePic = true;
							out.write("Pic for current hour is already present: "
									+ file.getName() + "\n");
							out.flush();
							break;
						}
					}
					if (takePic) {
						String picName = numbersOnlyFormat
								.format(cal.getTime());
						ProcessBuilder pb = new ProcessBuilder("raspistill",
								"-w", "800", "-h", "600", "-o", picName
										+ ".jpg");
						pb.directory(new File(todaysDir.getAbsolutePath()));
						pb.redirectErrorStream(true);
						p = pb.start();
						OutputStream outStream = p.getOutputStream();
						bw = new BufferedWriter(new OutputStreamWriter(
								outStream));
						out.write("Taking pic: " + picName + ".jpg" + "\n");
						out.flush();
					}

					// zip up all today's pics and send via e-mail
					// need to sleep first before last taken pic goes in
					if (hoursNow == workingHours[workingHours.length - 1]) {
						Thread.sleep(10000);
						String zipFileName = todaysDirName + ".zip";
						String pathToZip = picsDir + zipFileName;
						ZipUtils.createZip(pathToZip,
								todaysDir.getAbsolutePath());
						out.write("Created zip file with pics: " + pathToZip
								+ "\n");
						out.flush();
						attachFiles[0] = pathToZip;
						EmailAttachmentSender.sendEmailWithAttachments(host,
								port, mailFrom, password, mailTo, subject
										+ todaysDirName, message, attachFiles);
						out.write("Email sent to " + mailTo + "\n");
						out.flush();
					}
				}
				// sleep for one hour
				cal = Calendar.getInstance();
				out.write("Go to sleep for one hour at: "
						+ dateTimeFormat.format(cal.getTime()) + "\n");
				out.flush();
				Thread.sleep(3600000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace(out);
		} catch (SecurityException e) {
			e.printStackTrace(out);
		} catch (AddressException e) {
			e.printStackTrace(out);
		} catch (MessagingException e) {
			e.printStackTrace(out);
		} finally {
			if (out != null) {
				out.write("Close file");
				out.flush();
				out.close();
			}
		}

	}

}
