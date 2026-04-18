package com.demowebshop.tests;

import com.demowebshop.config.EnvConfig;
import com.demowebshop.core.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Superclass mapping TestNG execution phases with execution telemetry.
 */
public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("local") String xmlBrowserConfig) {
        // Fall back to properties if XML parameter does not structurally exist
        String browser = xmlBrowserConfig.equals("local") ? EnvConfig.getInstance().getBrowser() : xmlBrowserConfig;
        
        logger.info("Bootstrapping test environment context on geometric variant: {}", browser);
        WebDriverFactory.initializeDriver(browser);
        driver = WebDriverFactory.getDriver();
        driver.get(EnvConfig.getInstance().getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        logger.info("Tearing down test environment and unlinking WebDriver references.");
        WebDriverFactory.quitDriver();
    }
}
