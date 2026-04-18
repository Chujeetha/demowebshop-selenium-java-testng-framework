package com.demowebshop.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.core.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Object[] params = result.getParameters();
        
        // Dynamically append the product data provider attribute if structurally present (Index 2)
        if (params != null && params.length >= 3) {
            testName = testName + "-" + params[2].toString();
        }
        
        ExtentTest test = extent.createTest(testName, result.getMethod().getDescription());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "State validation resolved successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String base64Screenshot = "";
        WebDriver driver = WebDriverFactory.getDriver();
        if (driver != null) {
            base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        }

        // Full trace view debugging
        StringWriter sw = new StringWriter();
        result.getThrowable().printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString();

        ExtentManager.getTest().log(Status.FAIL, "Execution anomaly detected.");
        ExtentManager.getTest().log(Status.FAIL, stackTrace);

        // Attach UI browser screenshot specifically
        if (!base64Screenshot.isEmpty()) {
            ExtentManager.getTest().addScreenCaptureFromBase64String(base64Screenshot, "Browser UI Failure Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, "Execution skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
