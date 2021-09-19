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

@Epic("Payout Dashboard")
@Feature("Cashgram")
public class CashgramTest extends BaseWebdriver {

	private final String NODE_TEST_ENVIRONMENT = "133:56";
	private final String QA_OWNER = "Rohit Kumar";
	private final String DESC_PART_1 = "<p>Accroding to <a href=\"https://www.figma.com/proto/d5KxFuVgsOPPlzC1bErPhL/Final-Landing-Page?node-id=";
	private final String DESC_PART_2 = "\"target=\"_blank\">Figma</a>.</p>"
			+ "<p>Please reach out to <a href=\"mailto:qa-tech@cashfree.com\" target=\"_blank\" title=\"QA-Owner="
			+ QA_OWNER + "\">QA-Tech</a>, in case of an issue with.</p>";

	private final String testCaseURL = "https://docs.google.com/spreadsheets/d/17hzNX-7PN3YmYRCaIg_ox1-91km0xu-ZYZ0ZUwI531k/edit#gid=280782238";
	private final String testCaseName = "TestCases";

	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> Verify User is able to create cashgram </p>" + DESC_PART_1 + NODE_TEST_ENVIRONMENT + DESC_PART_2)
	@Story("Merchant Dashboard Revamp")
	@Link(name = testCaseName, url = testCaseURL)
	@Test(enabled = true, priority = 0, groups = { "smoke","cashgram" }, description = "Verify creation of cashgram")
	private void verifyCashgramCreation() {
		LoginPage login = new LoginPage();
		login.login("cfmain@cashfree.com", "cashfree.123").navigateToPayoutPage().switchAccount("Jyothi Gas").navigateToCashgram().createCashgram();

	}

}
