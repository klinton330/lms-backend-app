package com.app.lms.service;

import org.springframework.stereotype.Service;

import com.app.lms.constants.EmailConstants;

import static com.app.lms.constants.EmailConstants.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
	public void send(String to, String sub, String msg) {

		// Get properties object
		Properties props = new Properties();
		props.put(HOST, HOST_NAME);
		props.put(SOCKET_FACTORY_PORT, SOCKET_FACTORY_PORT_NO);
		props.put(SOCKET_FACTORY_CLASS, SOCKET_FACTORY_CLASS_NAME);
		props.put(SMTP_OAUTH, SMTP_OAUTH_ENABLELD);
		props.put(SMTP_PORT, SMTP_PORT_NO);
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailConstants.USERNAME, EmailConstants.PASSWORD);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * public void sendPassWordToEmail(String password, String to, String firstName)
	 * { String sub = EmailConstants.EMAIL_SUBJECT; String msg = "Hello " +
	 * firstName + ",\n\n Your Password for LMS is: " + password + "\n\n LMS Team";
	 * // send(password, to, sub, msg); }
	 */

	public void sendResetTokenURL(String resetURL, String to, String firstName) {
		String sub = EmailConstants.RESET_LINK_SUBJECT;
		String emailContent = "Hello " + firstName + "\n\nTo reset your password, click the link below:\n" + resetURL
				+ "\n\n LMS Team";
		send(to, sub, emailContent);

	}
}
