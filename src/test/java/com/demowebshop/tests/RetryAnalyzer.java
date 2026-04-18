package com.demowebshop.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Implements macro-level retry thresholds to seamlessly recover from transient
 * element locability faults.
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(RetryAnalyzer.class);
    private int counter = 0;
    private static final int MAX_RETRY_LIMIT = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < MAX_RETRY_LIMIT) {
            logger.warn("Automated Test Retry Triggered! Attempt {} of {}", counter + 1, MAX_RETRY_LIMIT);
            counter++;
            return true;
        }
        return false;
    }
}
