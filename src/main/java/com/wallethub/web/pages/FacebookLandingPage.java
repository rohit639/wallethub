package com.wallethub.web.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wallethub.web.locators.Locators;
import com.wallethub.web.utils.CommonUtil;
import com.wallethub.web.utils.webdriver.WaitUtil;

import io.qameta.allure.Step;

public class FacebookLandingPage extends BasePage {

	@FindBy(xpath = Locators.FacebookLandingPageLocators.COMMENT_POST)
	private WebElement postComment;

	@FindBy(xpath = Locators.FacebookLandingPageLocators.COMMENT_INPUT_BOX)
	private WebElement commentInputBox;

	@FindBy(xpath = Locators.FacebookLandingPageLocators.COMMENT_POST_BUTTON)
	private WebElement postButton;

	@FindBy(xpath = Locators.FacebookLandingPageLocators.COMMENT_POST_BUTTON)
	private WebElement postedComment;

	public FacebookLandingPage() {
		super("Facebook", "Failed to verify facebook home page title");
		infoLogs("Navigated to facebook login page");
	}

	@Step("About to post a comment to Facebook")
	public FacebookLandingPage postComment(String desc) {

		Assert.assertTrue(WaitUtil.isElemntclickable(postComment), "Failed as post comment is not clickable");
		com.wallethub.web.utils.webdriver.CommonUtil.javaScriptClick(postComment);
		commentInputBox.clear();
		commentInputBox.sendKeys(desc);
		postButton.click();

		return this;
	}

	public boolean verifyPostedComment(String desc) {
		WebElement commentCheck = Locators.FacebookLandingPageLocators.checkPostedComment(desc);
		Assert.assertTrue(WaitUtil.isElemntVisbleRefress(commentCheck), "Failed as posted comment is not visiable");
		return true;
	}

}
