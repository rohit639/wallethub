package com.wallethub.web.core.listeners;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.wallethub.web.core.Configuration;
import com.wallethub.web.core.LoggerClass;

/**
 * <p>
 * Used interface IRetryAnalyzer to retry failed test cases
 * </p>
 * 
 * @author Rohit P Kumar
 */
public class RetryFailTest implements IRetryAnalyzer {

	public static final Logger logger = LoggerClass.createLogger();

	private int retryCount = 0;

	private boolean retryFailed = Configuration.isRetryFailedEnabled();
	private int maxRetryCount = Configuration.getMaxRetryCount();
	public static boolean retryCase = false;

	@Override
	public boolean retry(ITestResult result) {
		if (retryFailed) {
			if (retryCount < maxRetryCount) {
				retryCase = true;
				int coutNo = retryCount + 1;
				logger.debug("retrying failed test case\"" + result.getName() + "\" for the : " + coutNo);
				retryCount++;
				return true;
			} else {
				retryCase = false;
				return false;
			}
		}

		return false;
	}
}