package com.altoro.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverFactory {

    private DriverFactory() {
        // Utility class
    }

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void initializeDriver(String browser) {

        WebDriver webDriver;

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);

            webDriver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            webDriver = new FirefoxDriver();

            webDriver.manage()
                    .timeouts()
                    .implicitlyWait(java.time.Duration.ofSeconds(5));

        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);
        }

        webDriver.manage().window().maximize();

        driver.set(webDriver);
    }

    public static WebDriver getDriver() {

        WebDriver webDriver = driver.get();

        if (webDriver == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialized");
        }

        return webDriver;
    }

    public static void quitDriver() {

        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}