package com.altoro.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.altoro.base.BaseTest;
import com.altoro.utils.ExtentReportManager;
import com.altoro.utils.ExtentTestManager;
import com.altoro.utils.ScreenshotUtil;

public class ExtentTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTestManager.startTest(
                result.getMethod().getMethodName()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTestManager
                .getTest()
                .pass("Test passed successfully");

        ExtentTestManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager
                .getTest()
                .fail(result.getThrowable());

        Object instance = result.getInstance();

        if (instance instanceof BaseTest) {

            BaseTest baseTest = (BaseTest) instance;

            try {

                String screenshotPath =
                        ScreenshotUtil.captureScreenshot(
                                baseTest.getDriver(),
                                result.getMethod()
                                        .getMethodName()
                        );

                ExtentTestManager
                        .getTest()
                        .addScreenCaptureFromPath(
                                screenshotPath
                        );

            } catch (Exception e) {

                ExtentTestManager
                        .getTest()
                        .warning(
                                "Screenshot capture failed: "
                                + e.getMessage()
                        );
            }
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTestManager
                .getTest()
                .skip("Test skipped");

        ExtentTestManager.removeTest();
    }

    @Override
    public void onFinish(
            org.testng.ITestContext context) {

        ExtentReportManager
                .getReportInstance()
                .flush();
    }
}