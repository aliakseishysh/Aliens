package by.shyshaliaksey.aliens.controller.command;

import java.util.Map;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.shyshaliaksey.aliens.controller.RequestAttribute;
import by.shyshaliaksey.aliens.controller.command.CommandAccessChecker;
import by.shyshaliaksey.aliens.controller.command.CommandDefiner;
import by.shyshaliaksey.aliens.controller.command.CommandAccessChecker.MapKey;
import by.shyshaliaksey.aliens.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CommandAccessCheckerTest {
	
	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;

	@BeforeTest
	public void beforeTest() {
		HttpSession httpSession = Mockito.mock(HttpSession.class);
		httpRequest = Mockito.mock(HttpServletRequest.class);
		httpResponse = Mockito.mock(HttpServletResponse.class);
		User user = new User(User.Role.GUEST);
		Mockito.when(httpRequest.getSession()).thenReturn(httpSession);
		Mockito.when(httpSession.getAttribute(RequestAttribute.CURRENT_USER.getValue())).thenReturn(user);
	}
	
	@Test
	public void wrongRoleGuestTest() {
		Map<MapKey, Object> result = CommandAccessChecker.isUserHasPermission(CommandDefiner.ADD_NEW_ALIEN.getCommand(), httpRequest, httpResponse);
		Assert.assertEquals(result.get(MapKey.RESULT), false); 
	}
	
	@Test
	public void correctRoleGuestTest() {
		Map<MapKey, Object> result = CommandAccessChecker.isUserHasPermission(CommandDefiner.OPEN_HOME_PAGE.getCommand(), httpRequest, httpResponse);
		Assert.assertEquals(result.get(MapKey.RESULT), true); 
	}
	
}
