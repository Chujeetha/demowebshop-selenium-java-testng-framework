package com.demowebshop.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static final ExtentReports extentReports = createInstance();
    private static ThreadLocal<ExtentTest> testMap = new ThreadLocal<>();

    private static ExtentReports createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("Automation Execution Telemetry");
        sparkReporter.config().setReportName("Demo Webshop Assessment");
        sparkReporter.config().setTheme(Theme.DARK);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Framework Paradigm", "Page Object Model + WebDriverManager");
        extent.setSystemInfo("Telemetry Engine", "ExtentReports 5");
        return extent;
    }

    public static ExtentReports getInstance() {
        return extentReports;
    }

    public static ExtentTest getTest() {
        return testMap.get();
    }

    public static void setTest(ExtentTest test) {
        testMap.set(test);
    }
}
