package com.demowebshop.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Encapsulates thread-safe initialization of WebDriver instances avoiding
 * Singleton bottlenecks
 * in highly parallel execution scenarios.
 */
public class WebDriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void initializeDriver(String browser) {
        WebDriver driver;
        logger.info("Initializing driver architecture for geometric target: {}", browser);
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                ChromeOptions options = new ChromeOptions();
                // options.addArguments("--headless"); // Enable if headless required
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                break;
        }
        driverThreadLocal.set(driver);
        logger.info("Driver topology bound to active execution thread.");
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
            logger.info("Driver resource unlinked and terminated.");
        }
    }
}
