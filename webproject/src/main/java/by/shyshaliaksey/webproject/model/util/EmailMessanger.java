package by.shyshaliaksey.webproject.model.util;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.webproject.controller.RequestParameter;
import by.shyshaliaksey.webproject.controller.command.CommandValue;
import by.shyshaliaksey.webproject.model.email.EmailPropertiesReader;
import by.shyshaliaksey.webproject.model.util.localization.LocaleAttribute;
import by.shyshaliaksey.webproject.model.util.localization.LocaleKey;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailMessanger {

	private static final Logger logger = LogManager.getRootLogger();

	private EmailMessanger() {
	}
	
	public enum Function {
		REGISTER, CHANGE_EMAIL
	}
	public static boolean sendEmail(String emailTo, String token, String websiteUrl, Function function, LocaleAttribute locale) {
		Properties properties = EmailPropertiesReader.getPropeties();
		final String userKey = "mail.smtp.user";
		final String passwordKey = "mail.smtp.password";
		final String user = properties.getProperty(userKey);
		final String password = properties.getProperty(passwordKey);
//		properties.remove(userKey);
//		properties.remove(passwordKey);

		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			prepareMessage(message, token, websiteUrl, function, locale);
			Transport.send(message);
			return true;
		} catch(MessagingException e) {
			logger.log(Level.ERROR, "Can not send message to email: {} {}", emailTo, e.getStackTrace());
			return false;
		}
	}
	
	private static void prepareMessage(Message message, String token, String websiteUrl, Function function, LocaleAttribute locale) throws MessagingException {
		final String contentType = "text/html; charset=UTF-8";
		message.setHeader("Content-Type", "text/plain; charset=UTF-8");
		switch(function) {
		case REGISTER:
			String subjectRegister = locale.getLocalizedMessage(LocaleKey.EMAIL_SUBJECT_REGISTER.getValue());
			String contentRegister = locale.getLocalizedMessage(LocaleKey.EMAIL_CONTENT_REGISTER.getValue());
			message.setSubject(subjectRegister);
			String registerLink = buildRegisterLink(token, websiteUrl);
			String registerContent = buildMessage(contentRegister, registerLink);
			message.setContent(registerContent, contentType);
			break;
		case CHANGE_EMAIL:
			String subjectChangeEmail = locale.getLocalizedMessage(LocaleKey.EMAIL_SUBJECT_CHANGE_EMAIL.getValue());
			String contentChangeEmail = locale.getLocalizedMessage(LocaleKey.EMAIL_CONTENT_CHANGE_EMAIL.getValue());
			message.setSubject(subjectChangeEmail);
			String emailUpdateLink = buildEmailUpdateLink(token, websiteUrl);
			String emailUpdateContent = buildMessage(contentChangeEmail, emailUpdateLink);
			message.setContent(emailUpdateContent, contentType);
			break;
		}
	}
	
	private static String buildMessage(String content, String link) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(content);
		stringBuilder.append(" <a href='");
		stringBuilder.append(link);
		stringBuilder.append("'>");
		stringBuilder.append(link);
		return stringBuilder.append("</a>").toString();
	}
	
	private static String buildRegisterLink(String token, String websiteUrl) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(websiteUrl);
		stringBuilder.append("/");
		stringBuilder.append(RequestParameter.CONTROLLER.getValue());
		stringBuilder.append("?");
		stringBuilder.append(RequestParameter.COMMAND.getValue());
		stringBuilder.append("=");
		stringBuilder.append(CommandValue.OPEN_LOGIN_PAGE.getValue());
		stringBuilder.append("&");
		stringBuilder.append(RequestParameter.TOKEN.getValue());
		stringBuilder.append("=");
		stringBuilder.append(token);
		return stringBuilder.toString();
	}
	
	private static String buildEmailUpdateLink(String token, String websiteUrl) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(websiteUrl);
		stringBuilder.append("/");
		stringBuilder.append(RequestParameter.CONTROLLER.getValue());
		stringBuilder.append("?");
		stringBuilder.append(RequestParameter.COMMAND.getValue());
		stringBuilder.append("=");
		stringBuilder.append(CommandValue.OPEN_USER_PROFILE_PAGE.getValue());
		stringBuilder.append("&");
		stringBuilder.append(RequestParameter.TOKEN.getValue());
		stringBuilder.append("=");
		stringBuilder.append(token);
		return stringBuilder.toString();
	}
	


}
