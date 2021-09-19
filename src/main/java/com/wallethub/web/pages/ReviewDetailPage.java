package com.wallethub.web.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wallethub.web.locators.Locators;
import com.wallethub.web.utils.RandomData;
import com.wallethub.web.utils.webdriver.WaitUtil;

import io.qameta.allure.Step;

public class ReviewDetailPage extends BasePage {

	@FindBy(xpath = Locators.ReviewDetailPageLocators.SELECT_BUTTON)
	private WebElement selectButton;

	@FindBy(xpath = Locators.ReviewDetailPageLocators.REVIEW_INPUT_BOX)
	private WebElement reviewInputBox;

	@FindBy(xpath = Locators.ReviewDetailPageLocators.SUBMIT_BUTTON)
	private WebElement submitButton;

	@FindBy(xpath = Locators.ReviewDetailPageLocators.SELECT_OPTIONS)
	private List<WebElement> selectOptions;

	public ReviewDetailPage() {
		super("test insurance company metatitle test", "Failed to verify Transfers page title");
		infoLogs("Navigated to review details page");
	}

	@Step("Completeing review in details")
	public LoginPage completeReview(ReviewOptions options, String desc) {
		reviewInputBox.clear();
		reviewInputBox.sendKeys(desc);
		selectButton.click();
		for (WebElement element : selectOptions) {
			if (element.getText().equalsIgnoreCase(options.getText())) {
				element.click();
			}
		}
		submitButton.click();
		infoLogs("Succesfully Reviewed");
		return new LoginPage();
	}

	public enum ReviewOptions {
		ANNUITIES("Annuities"), Health_Insurancel("Health Insurance"), LIFE_INSURANCE("Life Insurance");

		private String text;

		ReviewOptions(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}

		public static ReviewOptions fromString(String text) {
			for (ReviewOptions b : ReviewOptions.values()) {
				if (b.text.equalsIgnoreCase(text)) {
					return b;
				}
			}
			return null;
		}
	}
}
