package com.wallethub.web.tests.wallethub;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wallethub.web.core.BaseWebdriver;
import com.wallethub.web.core.Configuration;
import com.wallethub.web.pages.LandingPage;
import com.wallethub.web.pages.LoginPage;
import com.wallethub.web.pages.ReviewDetailPage.ReviewOptions;
import com.wallethub.web.utils.RandomData;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class WalletHubTest extends BaseWebdriver {

	private final String QA_OWNER = "Rohit";
	private final String DESC = "<p>Please reach out to <a href=\"mailto:rohit639u@gmail.com\" target=\"_blank\" title=\"QA-Owner="
			+ QA_OWNER + "\">QA-Tech</a>, in case of an issue with.</p>";;

	@Severity(SeverityLevel.BLOCKER)
	@Description("<p> This is priorty 0(most important) test case. </p>" + DESC)
	@Link(name = "Home", url = "https://wallethub.com/")
	@Story("Demo for QA postion")
	@Test(enabled = true, priority = 0, groups = { "sanity" }, description = "Rating validation")
	private void verifyReview() {

		String desc = RandomData.randomString(200);

		LandingPage home = new LandingPage();
		Assert.assertTrue(
				home.reviewStar().completeReview(ReviewOptions.Health_Insurancel, desc)
						.login(Configuration.getUsername(), Configuration.getUserpassword()).verifyDescription(desc),
				"Failed to verify review provided.");

	}

}
