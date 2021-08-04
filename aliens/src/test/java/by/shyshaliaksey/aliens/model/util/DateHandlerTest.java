package by.shyshaliaksey.aliens.model.util;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.shyshaliaksey.aliens.model.util.DateHandler;

public class DateHandlerTest {

	@Test
	public void expirationDateTest() {
		String expiredDate = DateHandler.prepareDate(-1, Calendar.MINUTE);
		boolean expected = true;
		boolean actual = DateHandler.isExpired(expiredDate);
		Assert.assertEquals(actual, expected);
	}

}
