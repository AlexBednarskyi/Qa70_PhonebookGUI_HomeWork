package com.phonebook.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager();

    protected Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected SoftAssert softAssert;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void startTest(Method method) {
        logger.info("Start test: {}", method.getName());
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void stopTest(ITestResult result) {
        String methodName = result.getMethod().getMethodName();

        if (result.isSuccess()) {
            logger.info("PASSED: {}", methodName);
        } else {
            String screenshot = "screen-" + System.currentTimeMillis() + ".png";
            logger.error("FAILED: {} Screenshot: {}", methodName, screenshot);
            app.getBase().takeScreenshot(screenshot);
        }

        logger.info("Stop test");
        logger.info("************************************");
    }
}
