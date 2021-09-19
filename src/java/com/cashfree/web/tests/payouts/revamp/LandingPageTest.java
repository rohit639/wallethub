package com.cashfree.web.tests.payouts.revamp;

import org.testng.annotations.Test;

import com.cashfree.web.core.BaseWebdriver;
import com.cashfree.web.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Merchant Dashboard")
@Feature("Landing Page")
@Story("Merchant Revamp")
public class LandingPageTest extends BaseWebdriver {

	private final String NODE_TEST_ENVIRONMENT = "133:56";
	private final String QA_OWNER = "Rohit Kumar";
	private final String DESC_PART_1 = "<p>Accroding to <a href=\"https://www.figma.com/proto/d5KxFuVgsOPPlzC1bErPhL/Final-Landing-Page?node-id=";
	private final String DESC_PART_2 = "\"target=\"_blank\">Figma</a>.</p>"
			+ "<p>Please reach out to <a href=\"mailto:qa-tech@cashfree.com\" target=\"_blank\" title=\"QA-Owner="
			+ QA_OWNER + "\">QA-Tech</a>, in case of an issue with.</p>";;

	private final String testCaseURL = "https://docs.google.com/spreadsheets/d/15T4euIe7jrD0SylqJ15ZV9dZ9qIC0yDXzWUkfCVaz2c/edit#gid=461495627";
	private final String testCaseName = "TestCases";

	/*
	 * @Severity(SeverityLevel.CRITICAL)
	 * 
	 * @Description("<p> Until the Account is activated user should be logged into test environment</p>"
	 * + DESC_PART_1 + NODE_TEST_ENVIRONMENT + DESC_PART_2)
	 * 
	 * @Link(name = testCaseName, url = testCaseURL)
	 * 
	 * @Test(enabled = false, priority = 0, groups = { "smoke" }, description =
	 * "Verify after sign-up the landing page is in test environment.") private void
	 * verifyLandsIntoTest() { LoginPage login = new LoginPage();
	 * login.login("Rohit.kumar+automation@cashfree.com",
	 * "admin@1234").verifyTestEnvironment(); }
	 * 
	 * @Severity(SeverityLevel.CRITICAL)
	 * 
	 * @Description("<p> The test environment should be in centere and carrot orange color #f18221.</p>"
	 * + DESC_PART_1 + NODE_TEST_ENVIRONMENT + DESC_PART_2)
	 * 
	 * @Link(name = testCaseName, url = testCaseURL)
	 * 
	 * @Test(enabled = false, priority = 0, groups = { "smoke" }, description =
	 * "Verify the merchant can view \"Test Environment\" in top-centre with colour \" Carrot Orange<#f18221>.\""
	 * )
	 * 
	 * private void verifyTestEnvironmentColour() { LoginPage login = new
	 * LoginPage(); login.login("Rohit.kumar+automation@cashfree.com",
	 * "admin@1234").verifyTestEnvironmentColour(); }
	 * 
	 * @Severity(SeverityLevel.CRITICAL)
	 * 
	 * @Description("<p> The background for welcome banner should be purple border colour (#6930CA) .</p>"
	 * + DESC_PART_1 + NODE_TEST_ENVIRONMENT + DESC_PART_2)
	 * 
	 * @Link(name = testCaseName, url = testCaseURL)
	 * 
	 * @Test(enabled = false, priority = 0, groups = { "smoke" }, description =
	 * "Verify int test environment view test Dashboard’ CTA will have a purple border colour (#6930CA) "
	 * ) private void verifyWelcomeBannerColor() { LoginPage login = new
	 * LoginPage(); login.login("Rohit.kumar+automation@cashfree.com",
	 * "admin@1234").verifywelcomeBannerColour(); }
	 */
}

/*
 * + " <table>\n" + "  <tr>\n" + "    <th>PRD</th>\n" +
 * "    <th>QA-Owner</th>\n" + "  </tr>\n" + "  <tr>\n" + "    <td>Figma</td>\n"
 * + "    <td>Rohit Kumar</td>\n" + "  </tr>\n" + "</table> "
 */