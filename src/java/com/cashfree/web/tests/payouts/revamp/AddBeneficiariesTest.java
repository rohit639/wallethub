package com.cashfree.web.tests.payouts.revamp;

import com.cashfree.web.core.BaseWebdriver;
import com.cashfree.web.core.Constant;
import com.cashfree.web.core.Constant.TransferMethod;
import com.cashfree.web.pages.LoginPage;
import com.cashfree.web.utils.RandomData;
import com.cashfree.web.utils.RuntimeUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Payout Dashboard")
@Feature("Add Beneficiaries")
public class AddBeneficiariesTest extends BaseWebdriver {

	private final String QA_OWNER = "Kavya";
	private final String DESC = "<p>Please reach out to <a href=\"mailto:qa-tech@cashfree.com\" target=\"_blank\" title=\"QA-Owner="
			+ QA_OWNER + "\">QA-Tech</a>, in case of an issue with.</p>";;

	private final String testCaseURL = "https://docs.google.com/spreadsheets/d/1dxBp74BGwHCjhgSisUcLmhLxs4bcqb90uT8TdsbEFdU/edit#gid=183262873";
	private final String testCaseName = "TestCases";
	LoginPage login;
	String beneId = Constant.BENEFICIARY_ID;

	@Severity(SeverityLevel.BLOCKER)
	@Description("<p> This is priorty 0(most important) test case. </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 0, groups = { "smoke", "payout", "beneficiary", "regression",
			"sanity" }, description = "Verify if user is able to add beneficiary without payment methods")
	private void verifyBeneficiaryAddition() {
		String tempBeneID = createBeneficiaryID();
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD)
				.navigateToPayoutPage()
				.switchAccount("Rohit Automation").navigateToBeneficiaries().addBeneficiary(tempBeneID);
		beneId = tempBeneID;
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("<p> Verify if user is able to add beneficiary with duplicate beneficiary ID </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 0, groups = { "smoke", "payout", "beneficiary", "regression",
			"sanity" }, description = "Verify add beneficiary for duplicate beneficiary ID")
	private void verifyDuplicateBeneficiaryIdAddition() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD)
				.navigateToPayoutPage()
				.switchAccount("Rohit Automation")
				.navigateToBeneficiaries().addDuplicateBeneficiary(beneId);
		;
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> Verify user is able to add new beneficiary with payment method - Bank </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 1, groups = { "payout", "beneficiary", "regression",
			"sanity" }, description = "Verify if user is able to add beneficiary with payment methods - Bank")
	private void verifyPaymentMethodBank() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD)
				.navigateToPayoutPage()
				.switchAccount("Rohit Automation")
				.navigateToBeneficiaries().addBeneficiary(createBeneficiaryID(), TransferMethod.BANK);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> Verify user is able to add new beneficiary with payment method - VPA </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 1, groups = { "payout", "beneficiary", "regression",
			"sanity" }, description = "Verify if user is able to add beneficiary with payment methods - upi vpa")
	private void verifyPaymentMethodVPA() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD)
				.navigateToPayoutPage()
				.switchAccount("Rohit Automation")
				.navigateToBeneficiaries().addBeneficiary(createBeneficiaryID(), TransferMethod.VPA);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> Verify user is able to add new beneficiary with payment method - Card </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 1, groups = { "payout", "beneficiary", "regression",
			"sanity" }, description = "Verify if user is able to add beneficiary with payment methods - card number")
	private void verifyPaymentMethodCard() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD)
				.navigateToPayoutPage()
				.switchAccount("Rohit Automation")
				.navigateToBeneficiaries().addBeneficiary(createBeneficiaryID(), TransferMethod.CARD);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> Verify user is able to delete beneficiary </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, groups = { "payout", "beneficiary", "regression",
			"sanity" }, description = "Verify if user is able to delete beneficiary")
	private void deleteBeneficiary() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD)
				.navigateToPayoutPage()
				.switchAccount("Rohit Automation")
				.navigateToBeneficiaries().deleteBeneficiary();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> Verify user is able to view beneficiary details </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 2, groups = { "payout", "beneficiary", "regression",
			"sanity" }, description = "Verify if on clicking the row, beneficiary details are displayed")
	private void verifyBeneficiaryDetails() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD)
				.navigateToPayoutPage()
				.switchAccount("Rohit Automation")
				.navigateToBeneficiaries().addBeneficiary(createBeneficiaryID()).verifyBeneficiaryDetails();
	}

	private String createBeneficiaryID() {
		String beneId = RandomData.getToDayDate("E_ddMMHHmmssSSS");
		RuntimeUtils.saveAtRunTime("beneID", beneId);
		return beneId;
	}

}