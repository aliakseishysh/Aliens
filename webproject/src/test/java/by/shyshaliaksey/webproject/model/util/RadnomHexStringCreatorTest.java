package by.shyshaliaksey.webproject.model.util;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RadnomHexStringCreatorTest {

	private List<String> names = new ArrayList<>();
	
	@Test(invocationCount = 10)
	public void createRandomNameTest() {
		String name1 = RandomHexStringCreator.createRandomName();
		String name2 = RandomHexStringCreator.createRandomName();
		Assert.assertNotEquals(name1, name2);
	}
	
	@Test(invocationCount = 10000)
	public void createRandomNameRepeatabilityTest() {
		String name = RandomHexStringCreator.createRandomName();
		if (names.contains(name)) {
			throw new AssertionError("Names already contains element " + name);
		} else {
			Assert.assertEquals(true, true);
		}
	}
	
	
}
