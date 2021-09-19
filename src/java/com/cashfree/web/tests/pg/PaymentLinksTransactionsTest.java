package com.cashfree.web.tests.pg;

import com.cashfree.web.core.BaseWebdriver;
import com.cashfree.web.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static com.cashfree.web.core.Constant.PG_LOGIN_EMAIL;
import static com.cashfree.web.core.Constant.PG_LOGIN_PWD;

@Epic("PG Dashboard")
@Feature("Create Payment Link")
public class PaymentLinksTransactionsTest extends BaseWebdriver {

    private final String QA_OWNER = "Manideep Ellur";
    private final String DESC_PART_2 = "<p>Please reach out to <a href=\"mailto:qa-tech@cashfree.com\" target=\"_blank\" title=\"QA-Owner="
            + QA_OWNER + "\">QA-Tech</a>, in case of an issue.</p>";

    @Severity(SeverityLevel.CRITICAL)
    @Description("<p> Verify if user is able to create a payment link and complete a transaction successfully</p>" + DESC_PART_2)
    @Story("Merchant Dashboard Revamp")
    @Test(enabled = true, priority = 0, groups = { "sanity" }, description = "Verify creation of payments links and create a transaction")
    private void verifyPaymentsLinkCreation() throws InterruptedException {
        LoginPage login = new LoginPage();
        login.login(PG_LOGIN_EMAIL, PG_LOGIN_PWD).navigateToPgPage().navigateToPaymentLinks()
                .verifyPaymentLinksCreation().verifyPaymentLink();
    }

}
