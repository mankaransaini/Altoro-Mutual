package com.altoro.utils;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {

    private ExtentTestManager() {
        // Utility class
    }

    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    public static void startTest(String testName) {

        ExtentTest test =
                ExtentReportManager
                        .getReportInstance()
                        .createTest(testName);

        extentTest.set(test);
    }

    public static ExtentTest getTest() {

        ExtentTest test = extentTest.get();

        if (test == null) {
            throw new IllegalStateException(
                    "ExtentTest has not been initialized");
        }

        return test;
    }

    public static void removeTest() {

        extentTest.remove();
    }
}