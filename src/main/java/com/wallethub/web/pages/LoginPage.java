package com.wallethub.web.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wallethub.web.locators.Locators;
import com.wallethub.web.utils.webdriver.WaitUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	@FindBy(xpath = Locators.LoginPageLocators.EMAIL_INPUT_BOX)
	private WebElement emailInput;

	@FindBy(xpath = Locators.LoginPageLocators.PASSWORD_INPUT_BOX)
	private WebElement passwordInput;

	@FindBy(xpath = Locators.LoginPageLocators.LOGIN_BUTTON)
	private WebElement loginButton;

	@FindBy(xpath = Locators.LoginPageLocators.LOGIN_TAB)
	private WebElement loginTab;

	public LoginPage() {
		super("Join WalletHub", "Failed to verify the title of the page");
		infoLogs("Navigated to review details page");
	}

	@Step("About to login to wallethub")
	public LandingPage login(String userName, String password) {

		Assert.assertTrue(WaitUtil.isElemntclickable(loginTab), "Failed as login tab is not clickable");
		loginTab.click();
		emailInput.clear();
		emailInput.sendKeys(userName);
		passwordInput.clear();
		passwordInput.sendKeys(password);
		loginButton.click();
		return new LandingPage();
	}
}
