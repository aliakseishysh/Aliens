package by.shyshaliaksey.webproject.controller.command;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CommandDefinerTest {

	@Test
	public void badInputTest() {
		Command command = CommandDefiner.defineCommand("dfasdfasdfasdf");
		Assert.assertEquals(command.getClass(), CommandDefiner.OPEN_404_ERROR_PAGE.getCommand().getClass());
	}

	@Test
	public void nullInputTest() {
		Command command = CommandDefiner.defineCommand(null);
		Assert.assertEquals(command.getClass(), CommandDefiner.OPEN_404_ERROR_PAGE.getCommand().getClass());
	}
	
	@Test
	public void openHomePageTest() {
		Command command = CommandDefiner.defineCommand(CommandDefiner.OPEN_HOME_PAGE.getValue());
		Assert.assertEquals(command.getClass(), CommandDefiner.OPEN_HOME_PAGE.getCommand().getClass());
	}
}
