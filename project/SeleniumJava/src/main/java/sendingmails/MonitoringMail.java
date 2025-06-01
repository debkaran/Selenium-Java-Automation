package sendingmails;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import config.TestConfig;

public class MonitoringMail {
	// public static void sendMail(String mailServer, String from,String username,
	// String password,String port, String[] to, String subject, String messageBody,
	// String attachmentPath, String attachmentName) throws MessagingException,
	// AddressException
	public void sendMail(String mailServer, String from, String[] to, String subject, String messageBody,
			String attachmentPath, String attachmentName) {

		Properties props = new Properties();
		props.setProperty("mail.smtp.host", mailServer);
		props.setProperty("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(props, auth);

		try {
			// set from address, recipient address and subject
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++)
				addressTo[i] = new InternetAddress(to[i]);
			message.setRecipients(Message.RecipientType.TO, addressTo);
			message.setSubject(subject);

			// add message in mail body
			BodyPart body = new MimeBodyPart();
			body.setText(messageBody);
			body.setContent(messageBody, "text/html");

			// add attachment in mail
			BodyPart attachment = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentPath);
			attachment.setDataHandler(new DataHandler(source));
			attachment.setFileName(attachmentName);
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(body);
			multipart.addBodyPart(attachment);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sucessfully Sent mail to All Users");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	private class SMTPAuthenticator extends Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = TestConfig.from;
			String password = TestConfig.password;
			return new PasswordAuthentication(username, password);
		}
	}

}