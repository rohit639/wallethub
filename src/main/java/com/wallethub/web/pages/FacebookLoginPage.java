package com.wallethub.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wallethub.web.locators.Locators;
import com.wallethub.web.utils.webdriver.WaitUtil;

import io.qameta.allure.Step;

public class FacebookLoginPage extends BasePage {

	@FindBy(id = Locators.FacebookLoginPageLocators.EMAIL_INPUT_BOX)
	private WebElement emailBox;

	@FindBy(id = Locators.FacebookLoginPageLocators.PASSWORD_INPUT_BOX)
	private WebElement passwordBox;

	@FindBy(xpath = Locators.FacebookLoginPageLocators.LOGIN_BUTTON)
	private WebElement loginButton;

	public FacebookLoginPage() {
		super("Facebook - Log In or Sign Up", "Failed to verify facebook login page title");
		infoLogs("Navigated to facebook login page");
	}

	@Step("About to login to Facebook")
	public FacebookLandingPage loginToFacebook(String userName, String password) {

		Assert.assertTrue(WaitUtil.isElemntclickable(loginButton), "Failed as login button is not clickable");
		emailBox.clear();
		emailBox.sendKeys(userName);
		passwordBox.clear();
		passwordBox.sendKeys(password);
		loginButton.click();
		return new FacebookLandingPage();
	}
}
