package by.shyshaliaksey.aliens.model.util;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DateHandlerTest {

	@Test
	public void expirationDateTest() {
		String expiredDate = DateHandler.prepareDate(-1, Calendar.MINUTE);
		boolean actual = DateHandler.isExpired(expiredDate);
		Assert.assertTrue(actual);
	}

}
