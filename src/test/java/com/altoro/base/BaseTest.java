package com.altoro.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.altoro.utils.ConfigReader;
import com.altoro.utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {

        String url = ConfigReader.get("url");

        // Create a browser instance for the current TestNG thread
        DriverFactory.initializeDriver(browser);

        // Get the thread-specific driver
        driver = DriverFactory.getDriver();

        // Open application
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {

        // Close the current thread's browser
        DriverFactory.quitDriver();
    }

    // Provides controlled access to the driver for listeners/utilities
    public WebDriver getDriver() {
        return driver;
    }
}