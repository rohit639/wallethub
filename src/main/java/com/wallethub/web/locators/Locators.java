package com.wallethub.web.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.wallethub.web.core.BaseWebdriver;

public class Locators {

	public class BasePageLocator {

	}

	public static class FacebookLandingPageLocators {
		public static final String COMMENT_POST = "//div[@aria-label='Create a post']//span[starts-with(text(),'What')]";
		public static final String COMMENT_INPUT_BOX = "//div[starts-with(@aria-describedby,'placeholder')]";
		public static final String COMMENT_POST_BUTTON = "//div[@aria-label='Post']";

		public static final WebElement checkPostedComment(String xpath) {
			return BaseWebdriver.getDriver().findElement(By.xpath("//div[text()='" + xpath + "']"));
		}

	}

	public class FacebookLoginPageLocators {
		public static final String EMAIL_INPUT_BOX = "email";
		public static final String PASSWORD_INPUT_BOX = "pass";
		public static final String LOGIN_BUTTON = "//button[@name='login']";

	}

	public class LandingPageLocators {
		public static final String REVIEW_STARS = "//div[contains(@class, 'review-action')]//*[local-name()='svg']";
		public static final String REVIEW_DESCRPTION = "//div[@itemprop='description']";
	}

	public class LoginPageLocators {
		public static final String EMAIL_INPUT_BOX = "//input[starts-with(@placeholder,'Email Address')]";
		public static final String PASSWORD_INPUT_BOX = "//input[starts-with(@placeholder,'Password')]";
		public static final String LOGIN_BUTTON = "//span[text()='Login']/..";
		public static final String LOGIN_TAB = "//a[text()='Login']";
	}

	public class ReviewDetailPageLocators {
		public static final String SELECT_BUTTON = "//span[starts-with(text(),'Select')]";
		public static final String REVIEW_INPUT_BOX = "//textarea[starts-with(@placeholder,'Write your review')]";
		public static final String SUBMIT_BUTTON = "//div[text()='Submit']";
		public static final String SELECT_OPTIONS = "//span[starts-with(text(),'Select')]/..//li";
	}

}
