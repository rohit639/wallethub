package com.cashfree.web.tests.payouts.revamp;

import org.testng.annotations.Test;

import com.cashfree.web.core.BaseWebdriver;
import com.cashfree.web.core.Constant;
import com.cashfree.web.core.Constant.TransferMode;
import com.cashfree.web.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Payout Dashboard")
@Feature("Transfer")
public class TransferTest extends BaseWebdriver {

	private final String QA_OWNER = "Kavya";
	private final String DESC = "<p>Please reach out to <a href=\"mailto:qa-tech@cashfree.com\" target=\"_blank\" title=\"QA-Owner="
			+ QA_OWNER + "\">QA-Tech</a>, in case of an issue with.</p>";;

	private final String testCaseURL = "https://docs.google.com/spreadsheets/d/1dxBp74BGwHCjhgSisUcLmhLxs4bcqb90uT8TdsbEFdU/edit#gid=183262873";
	private final String testCaseName = "TestCases";
	LoginPage login;

	@Severity(SeverityLevel.BLOCKER)
	@Description("<p> This is priorty 0(most important) test case. </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 0, groups = { "smoke", "payout", "transfer", "regression",
			"sanity" }, description = "Verify quick transfer via bank")
	private void verifyQuickTransferBank() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD).navigateToPayoutPage()
				.switchAccount("Rohit Automation").navigateToTransfers()
				.quickTransfer(Constant.BANK_TRANSFER_ID,TransferMode.BANK);
	}
	
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("<p> This is priorty 0(most important) test case. </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 0, groups = { "smoke", "payout", "transfer", "regression",
			"sanity" }, description = "Verify quick transfer via UPI")
	private void verifyQuickTransferVPA() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD).navigateToPayoutPage()
				.switchAccount("Rohit Automation").navigateToTransfers()
				.quickTransfer(Constant.UPI_TRANSFER_ID,TransferMode.VPA);
	}

	//Paytm not working in Gamma
	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> This is priorty 1(most important) test case. </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 1, groups = { "payout", "transfer", "regression",
			"sanity" }, description = "Verify quick transfer via Paytm")
	private void verifyQuickTransferPayTm() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD).navigateToPayoutPage()
				.switchAccount("Rohit Automation").navigateToTransfers()
				.quickTransfer(Constant.PAYTM_TRANSFER_ID,TransferMode.PAYTM);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> This is priorty 1(most important) test case. </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 1, groups = { "payout", "transfer", "regression",
			"sanity" }, description = "Verify quick transfer via Amazon Pay")
	private void verifyQuickTransferAmazon() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD).navigateToPayoutPage()
				.switchAccount("Rohit Automation").navigateToTransfers()
				.quickTransfer(Constant.AMAZON_TRANSFER_ID,TransferMode.AMAZONE);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> This is priorty 2(important) test case. </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 2, groups = { "payout", "transfer", "regression",
			"sanity" }, description = "Verify after transfer via bank transfer details are valid")
	private void verifyBankTransferDetail() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD).navigateToPayoutPage()
				.switchAccount("Rohit Automation").navigateToTransfers()
				.quickTransfer(Constant.BANK_TRANSFER_ID_DETAILS,TransferMode.BANK).verifyTransferDetails(Constant.BANK_TRANSFER_ID_DETAILS,TransferMode.BANK);;
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("<p> This is priorty 2(important) test case. </p>" + DESC)
	@Link(name = testCaseName, url = testCaseURL)
	@Story("Merchant Dashboard Revamp")
	@Test(enabled = true, priority = 2, groups = { "payout", "transfer", "regression",
			"sanity" }, description = "Verify after transfer via UPI transfer details are valid")
	private void verifyUPITransferDetail() {
		login = new LoginPage();
		login.login(Constant.PAYOUT_GAMMA_USER_ID, Constant.PAYOUT_GAMMA_PASSWORD).navigateToPayoutPage()
				.switchAccount("Rohit Automation").navigateToTransfers()
				.quickTransfer(Constant.UPI_TRANSFER_ID_DETAILS,TransferMode.VPA).verifyTransferDetails(Constant.UPI_TRANSFER_ID_DETAILS,TransferMode.VPA);;
	}
	
	
	/*
	 * @Severity(SeverityLevel.CRITICAL)
	 * 
	 * @Description("<p> Verify User is able to transfer via Bank </p>" +
	 * DESC_PART_1 + NODE_TEST_ENVIRONMENT + DESC_PART_2)
	 * 
	 * @Story("Merchant Dashboard Revamp")
	 * 
	 * @Link(name = testCaseName, url = testCaseURL)
	 * 
	 * @Test(enabled = true, priority = 0, groups = { "smoke" }, description =
	 * "Verify transfer via Bank") private void verifyTransferBank() { LoginPage
	 * login = new LoginPage(); login.login("cfmain@cashfree.com",
	 * "cashfree.123").navigateToPayoutPage().navigateToTransfers()
	 * .quickTransferBank("026291800001191_2226983");
	 * 
	 * }
	 */
}
