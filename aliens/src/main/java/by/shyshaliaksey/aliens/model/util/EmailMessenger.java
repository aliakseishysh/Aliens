package by.shyshaliaksey.aliens.model.util;

import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.shyshaliaksey.aliens.controller.RequestParameter;
import by.shyshaliaksey.aliens.controller.command.CommandDefiner;
import by.shyshaliaksey.aliens.model.email.EmailPropertiesReader;
import by.shyshaliaksey.aliens.model.util.localization.LocaleAttribute;
import by.shyshaliaksey.aliens.model.util.localization.LocaleKey;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Class {@code EmailMessenger} designed for preparing email messages and
 * sending them
 * 
 * @author Aliaksey Shysh
 *
 */
public class EmailMessenger {

	private static final Logger logger = LogManager.getRootLogger();

	private EmailMessenger() {
	}

	public enum Function {
		REGISTER, CHANGE_EMAIL
	}

	/**
	 * Sends email to specified email address.
	 * 
	 * @param emailTo  {@code String} email address to send message
	 * @param token    {@code String} token for generating message
	 * @param function {@code Function} current function to execute
	 * @param locale   {@code LocaleAttribute} current user locale
	 * @return {@code true} if message sent, {@code false} otherwise
	 */
	public static boolean sendEmail(String emailTo, String token, Function function, LocaleAttribute locale) {
		Properties properties = EmailPropertiesReader.getProperties();
		final String userKey = "mail.smtp.user";
		final String passwordKey = "mail.smtp.password";
		final String user = properties.getProperty(userKey);
		final String password = properties.getProperty(passwordKey);

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
			prepareMessage(message, token, function, locale);
			Transport.send(message);
			return true;
		} catch (MessagingException | UnsupportedOperationException e) {
			logger.log(Level.ERROR, "Can not send message to email: {} {}", emailTo, e.getStackTrace());
			return false;
		}
	}

	/**
	 * Chooses function and builds message for it
	 * 
	 * @param message  {@link Message}
	 * @param token    {@code String} token for generating message
	 * @param function {@code Function} current function to execute
	 * @param locale   {@code LocaleAttribute} current user locale
	 * @throws MessagingException if can not send message
	 */
	private static void prepareMessage(Message message, String token, Function function, LocaleAttribute locale)
			throws MessagingException {
		final String contentType = "text/html; charset=UTF-8";
		message.setHeader("Content-Type", "text/plain; charset=UTF-8");
		switch (function) {
			case REGISTER -> {
				String subjectRegister = locale.getLocalizedMessage(LocaleKey.EMAIL_SUBJECT_REGISTER.getValue());
				String contentRegister = locale.getLocalizedMessage(LocaleKey.EMAIL_CONTENT_REGISTER.getValue());
				message.setSubject(subjectRegister);
				String registerLink = buildRegisterLink(token);
				String registerContent = buildMessage(contentRegister, registerLink);
				message.setContent(registerContent, contentType);
			}
			case CHANGE_EMAIL -> {
				String subjectChangeEmail = locale.getLocalizedMessage(LocaleKey.EMAIL_SUBJECT_CHANGE_EMAIL.getValue());
				String contentChangeEmail = locale.getLocalizedMessage(LocaleKey.EMAIL_CONTENT_CHANGE_EMAIL.getValue());
				message.setSubject(subjectChangeEmail);
				String emailUpdateLink = buildEmailUpdateLink(token);
				String emailUpdateContent = buildMessage(contentChangeEmail, emailUpdateLink);
				message.setContent(emailUpdateContent, contentType);
			}
			default -> {
				logger.log(Level.ERROR, "Current function currently unsupported: {}", function.name());
				throw new UnsupportedOperationException("Current function currently unsupported: " + function.name());
			}
		}
	}

	/**
	 * Builds message from content and link
	 * 
	 * @param content {@code String} localized message content
	 * @param link    {@code String} link to different pages with token parameter
	 * @return {@code String} result message
	 */
	private static String buildMessage(String content, String link) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(content);
		stringBuilder.append(" <a href='");
		stringBuilder.append(link);
		stringBuilder.append("'>");
		stringBuilder.append(link);
		return stringBuilder.append("</a>").toString();
	}

	/**
	 * Builds registration link
	 * 
	 * @param token {@code String} token for generating message
	 * @return {@code String} registration link
	 */
	private static String buildRegisterLink(String token) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(DeploymentPropertiesReader.Deployment.CURRENT_DEPLOYMENT.getValue());
		stringBuilder.append("/");
		stringBuilder.append(RequestParameter.CONTROLLER.getValue());
		stringBuilder.append("?");
		stringBuilder.append(RequestParameter.COMMAND.getValue());
		stringBuilder.append("=");
		stringBuilder.append(CommandDefiner.OPEN_LOGIN_PAGE.getValue());
		stringBuilder.append("&");
		stringBuilder.append(RequestParameter.TOKEN.getValue());
		stringBuilder.append("=");
		stringBuilder.append(token);
		return stringBuilder.toString();
	}

	/**
	 * Builds email update link
	 * 
	 * @param token {@code String} token for generating message
	 * @return {@code String} email update link
	 */
	private static String buildEmailUpdateLink(String token) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(DeploymentPropertiesReader.Deployment.CURRENT_DEPLOYMENT.getValue());
		stringBuilder.append("/");
		stringBuilder.append(RequestParameter.CONTROLLER.getValue());
		stringBuilder.append("?");
		stringBuilder.append(RequestParameter.COMMAND.getValue());
		stringBuilder.append("=");
		stringBuilder.append(CommandDefiner.OPEN_USER_PROFILE_PAGE.getValue());
		stringBuilder.append("&");
		stringBuilder.append(RequestParameter.TOKEN.getValue());
		stringBuilder.append("=");
		stringBuilder.append(token);
		return stringBuilder.toString();
	}

}
