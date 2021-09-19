package com.wallethub.web.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wallethub.web.core.BaseWebdriver;
import com.wallethub.web.locators.Locators;
import com.wallethub.web.utils.webdriver.CommonUtil;
import com.wallethub.web.utils.webdriver.WaitUtil;

import io.qameta.allure.Step;
import net.bytebuddy.matcher.EqualityMatcher;

public class LandingPage extends BasePage {

	@FindBy(xpath = Locators.LandingPageLocators.REVIEW_STARS)
	private List<WebElement> reviewRatings;

	@FindBy(xpath = Locators.LandingPageLocators.REVIEW_DESCRPTION)
	private List<WebElement> descriptionReview;

	public LandingPage() {
		super("test insurance company metatitle test", "Failed to verify Transfers page title");
		infoLogs("Navigated to landing page");
	}

	@Step("Review star page")
	public ReviewDetailPage reviewStar() {
		int count = 0;
		CommonUtil.scrolIntoElement(reviewRatings.get(0));
		for (WebElement element : reviewRatings) {
			CommonUtil.getActionMethod().moveToElement(element).build().perform();
			count++;
			if (count == reviewRatings.size() - 1) {
				CommonUtil.getActionMethod().click(element).build().perform();
			}
		}

		infoLogs("Clicked on rating");

		return new ReviewDetailPage();
	}

	@Step("Verify the message is same as provided")
	public boolean verifyDescription(String descToVerify) {
		for (WebElement element : descriptionReview) {
			if (element.getText().equalsIgnoreCase(descToVerify)) {
				infoLogs("Successfully verified descrption is same as provdied");
				return true;
			}
		}
		errorLogs("Failed to verify that description is same");
		return false;
	}

}
