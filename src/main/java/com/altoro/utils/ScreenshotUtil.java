package com.altoro.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
        // Utility class
    }

    public static String captureScreenshot(
            WebDriver driver,
            String testName) {

        try {

            Path screenshotDirectory =
                    Paths.get(
                            System.getProperty("user.dir"),
                            "reports",
                            "screenshots"
                    );

            Files.createDirectories(screenshotDirectory);

            String fileName =
                    testName + "_" + System.currentTimeMillis() + ".png";

            Path screenshotPath =
                    screenshotDirectory.resolve(fileName);

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            Files.copy(
                    source.toPath(),
                    screenshotPath
            );

            return screenshotPath.toAbsolutePath().toString();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to capture screenshot", e);
        }
    }
}