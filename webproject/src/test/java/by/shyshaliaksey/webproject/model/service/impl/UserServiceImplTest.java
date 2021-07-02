package by.shyshaliaksey.webproject.model.service.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

import org.testng.annotations.Test;

import by.shyshaliaksey.webproject.exception.ServiceException;
import by.shyshaliaksey.webproject.model.service.ServiceProvider;
import by.shyshaliaksey.webproject.model.service.UserService;
import jakarta.xml.bind.DatatypeConverter;

public class UserServiceImplTest {

	@Test
	public void passwordHashTest() throws ServiceException {
		Random random = new Random();
		long time01 = 0;
		long time02 = 0;
		for (int i = 0; i < 10000000; i++) {
			byte[] bytes = new byte[16];
			random.nextBytes(bytes);
			var time1 = System.nanoTime();
			String s1 = DatatypeConverter.printHexBinary(bytes);
			var time2 = System.nanoTime();
			var time3 = System.nanoTime();
			String s2 = bytesToHex(bytes);
			var time4 = System.nanoTime();
			if (!s1.equals(s2)) {
				throw new RuntimeException("Not equals");
			} else {
				time01 += (time2-time1);
				time02 += (time4-time3);
				System.out.println("Standard " + s1 + ": " + time01 + " Non standard" + s2 + ": " + time02);
			}
		}
		
	}
	
	final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
	public String bytesToHex(byte[] bytes) {
	    byte[] hexChars = new byte[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars, StandardCharsets.UTF_8);
	}
	
	
}
