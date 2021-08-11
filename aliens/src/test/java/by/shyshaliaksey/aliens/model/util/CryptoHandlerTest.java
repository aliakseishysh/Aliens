package by.shyshaliaksey.aliens.model.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CryptoHandlerTest {

	@Test
	public void hashPasswordEqualityTest() {
		byte[] salt = CryptoHandler.createSalt();
		String passwordToHash = "sjakdfKFJH@)#$@FU$)2f420fkljk";
		byte[] password1 = CryptoHandler.hashPassword(passwordToHash, salt);
		byte[] password2 = CryptoHandler.hashPassword(passwordToHash, salt);
		Assert.assertEquals(password1, password2);
	}
	
	
	
}
