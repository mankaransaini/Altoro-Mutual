package com.altoro.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReportManager {

    private static ExtentReports extentReports;

    private ExtentReportManager() {
        // Utility class
    }

    public static synchronized ExtentReports getReportInstance() {

        if (extentReports == null) {

            Path reportDirectory = Paths.get(
                    System.getProperty("user.dir"),
                    "reports"
            );

            try {
                Files.createDirectories(reportDirectory);
            } catch (Exception e) {
                throw new RuntimeException(
                        "Unable to create reports directory", e);
            }

            Path reportFile =
                    reportDirectory.resolve("AutomationReport.html");

            System.out.println(
                    "Extent Report Location: "
                    + reportFile.toAbsolutePath()
            );

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(
                            reportFile.toString()
                    );

            sparkReporter.config().setDocumentTitle(
                    "Altoro Automation Report"
            );

            sparkReporter.config().setReportName(
                    "Altoro Selenium Test Execution"
            );

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo(
                    "Application",
                    "Altoro Mutual"
            );

            extentReports.setSystemInfo(
                    "Environment",
                    "Test"
            );

            extentReports.setSystemInfo(
                    "OS",
                    System.getProperty("os.name")
            );

            extentReports.setSystemInfo(
                    "Java",
                    System.getProperty("java.version")
            );
        }

        return extentReports;
    }
}