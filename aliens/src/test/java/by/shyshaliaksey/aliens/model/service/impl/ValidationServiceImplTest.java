package by.shyshaliaksey.aliens.model.service.impl;

import java.util.EnumMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.shyshaliaksey.aliens.controller.command.Feedback;

public class ValidationServiceImplTest {

	private ValidationServiceImpl validationService;
	private Map<Feedback.Key, Object> feedback;
	
	@BeforeClass
	public void init() {
		validationService = new ValidationServiceImpl();
		feedback = new EnumMap<>(Feedback.Key.class);
	}
	
	@DataProvider(name = "emailsDataProvider")
	public Object [][] emailsDataProvider() {
		return new Object[][]{
			{"test@test.test", true},
			{"t@t.t", true},
			{"t@t", true},
			{"@.t", false},
			{"", false},
			{"t.t", false}
		};
	}
	
	@DataProvider(name = "loginsDataProvider")
	public Object [][] loginsDataProvider() {
		return new Object[][]{
			{"tttttt", true},
			{"tttttttttttttttttttttttttttttt", true},
			{"______", true},
			{"Test_Test", true},
			{"Test test", false},
			{"Test0_ TestII00__ __ _", false},
			{"000", false},
			{"", false},
			{"<h1>Test</h1>", false}
		};
	}
	
	@DataProvider(name = "alienNamesDataProvider")
	public Object [][] alienNameDataProvider() {
		return new Object[][]{
			{"ttt", true},
			{"tttttttttttttttttttttttttttttt", true},
			{"000", true},
			{"      ", true},
			{"______", true},
			{"Test test", true},
			{"Test_Test", true},
			{"Test0_ TestII00__ __ _", true},
			{"", false},
			{"<h1>Test</h1>", false}
		};
	}
	
	@DataProvider(name = "passwordsDataProvider")
	public Object [][] passwordsDataProvider() {
		return new Object[][]{
			{"tttttttt", true},
			{"tttttttttttttttttttttttttttttt", true},
			{"#*@#!@())#(*!jhsdk-jhFIIffjh", true},
			{"Test_Test", true},
			{"      ", false},
			{"______", false},
			{"Test test", false},
			{"Test0_ TestII00__ __ _", false},
			{"", false},
			{"<h1>Test</h1>", false}
		};
	}
	
	@DataProvider(name = "imagesExtensionsAndSizesDataProvider")
	public Object [][] imagesExtensionsAndSizesDataProvider() {
		return new Object[][]{
			{"jpg", 1, true},
			{"jpeg", 1, true},
			{"png", 1, true},
			{"tttttttttttttttttttttttttttttt", 1, false},
			{"#*@#!@())#(*!jhsdk-jhFIIffjh", 1, false},
			{"Test_Test", 1, false},
			{"      ", 1, false},
			{"______", 1, false},
			{"Test test", 1, false},
			{"Test0_ TestII00__ __ _", 1, false},
			{"", 1, false},
			{"<h1>Test</h1>", 1, false},
			{"jpg", 0, false},
			{"jpg", -1, false},
			{"jpg", -999999999, false},
			{"jpg", 10000001 / 8, false}
		};
	}

	@DataProvider(name = "daysInBanDataProvider")
	public Object [][] daysInBanDataProvider() {
		return new Object[][]{
			{"143", true},
			{"0", false},
			{"99999999999999999999999", false},
			{"-999999999999999999999", false},
			{"asdfgadfgadfg", false}
		};
	}
	
	@DataProvider(name = "alienSmallDescriptionsDataProvider")
	public Object [][] alienSmallDescriptionsDataProvider() {
		return new Object[][]{
			{"tttttt", true},
			{"99999999999999999999999тестtest", true},
			{"-999999999999999999999", true},
			{"asdfgadfgadfg  ajdsfhj gwutf 87 3tf6F! ? :))  8239 238 92 ", true},
			{"", false},
			{"<h1>Test</h1>", false},
		};
	}
	
	@DataProvider(name = "alienFullDescriptionsDataProvider")
	public Object [][] alienFullDescriptionsDataProvider() {
		return new Object[][]{
			{"tttttttttt", true},
			{"99999999999999999999999тестtest", true},
			{"-999999999999999999999", true},
			{"asdfgadfgadfg  ajdsfhj gwutf 87 3tf6F! ? :))  8239 238 92 ", true},
			{"", false},
			{"<h1>Test</h1>", false},
		};
	}
	
	@DataProvider(name = "commentsDataProvider")
	public Object [][] commentsDataProvider() {
		return new Object[][]{
			{"ttt", true},
			{"99999999999999999999999тестtest", true},
			{"-999999999999999999999", true},
			{"asdfgadfgadfg  ajdsfhj gwutf 87 3tf6F! ? :))  8239 238 92 ", true},
			{"", false},
			{"<h1>Test</h1>", false},
		};
	}
	
	@Test(dataProvider = "emailsDataProvider")
	public void emailTest(String data, boolean expected) {
		validationService.validateEmailFormInput(feedback, data);
		boolean actual = (boolean) feedback.get(Feedback.Key.EMAIL_STATUS);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "loginsDataProvider")
	public void loginsTest(String data, boolean expected) {
		validationService.validateLoginFormInput(feedback, data);
		boolean actual = (boolean) feedback.get(Feedback.Key.LOGIN_STATUS);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "passwordsDataProvider")
	public void passwordsTest(String data, boolean expected) {
		validationService.validatePasswordFormInput(feedback, data);
		boolean actual = (boolean) feedback.get(Feedback.Key.PASSWORD_STATUS);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "imagesExtensionsAndSizesDataProvider")
	public void imagesTest(String extension, long size, boolean expected) {
		validationService.validateImageFormInput(feedback, extension, size);
		boolean actual = (boolean) feedback.get(Feedback.Key.IMAGE_STATUS);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "daysInBanDataProvider")
	public void daysInBanTest(String daysInBan, boolean expected) {
		validationService.validateBanFormInput(feedback, "correctlogin", daysInBan);
		boolean actual = (boolean) feedback.get(Feedback.Key.DAYS_TO_BAN_STATUS);
		Assert.assertEquals(actual, expected);
	}

	@Test(dataProvider = "alienNamesDataProvider")
	public void alienNameTest(String alienName, boolean expected) {
		validationService.validateAlienNameFormInput(feedback, alienName);
		boolean actual = (boolean) feedback.get(Feedback.Key.ALIEN_NAME_STATUS);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "alienSmallDescriptionsDataProvider")
	public void alienSmallDescriptionTest(String alienDescription, boolean expected) {
		validationService.validateAlienSmallDescriptionFormInput(feedback, alienDescription);
		boolean actual = (boolean) feedback.get(Feedback.Key.ALIEN_SMALL_DESCRIPTION_STATUS);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "alienFullDescriptionsDataProvider")
	public void alienFullDescriptionTest(String alienDescription, boolean expected) {
		validationService.validateAlienFullDescriptionFormInput(feedback, alienDescription);
		boolean actual = (boolean) feedback.get(Feedback.Key.ALIEN_FULL_DESCRIPTION_STATUS);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "commentsDataProvider")
	public void alienCommentTest(String comment, boolean expected) {
		validationService.validateCommentFormInput(feedback, comment);
		boolean actual = (boolean) feedback.get(Feedback.Key.COMMENT_STATUS);
		Assert.assertEquals(actual, expected);
	}

}
