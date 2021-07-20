package by.shyshaliaksey.webproject.model.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomHexStringCreator {

	private static Random random = new SecureRandom();
	
	private RandomHexStringCreator() {
	}
	
	public static String createRandomName() {
		Long long1 = random.nextLong();
		Long long2 = random.nextLong();
		Long long3 = random.nextLong();
		Long long4 = random.nextLong();
		StringBuilder builder = new StringBuilder();
		builder.append(Long.toHexString(long1));
		builder.append(Long.toHexString(long2));
		builder.append(Long.toHexString(long3));
		builder.append(Long.toHexString(long4));
		return builder.toString();
	}
	
}
