package com.altoro.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest {

    @Test
    public void openAltoroWebsite() {

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().window().maximize();

            driver.get("https://demo.testfire.net");

            System.out.println("Page Title: " + driver.getTitle());

            Assert.assertTrue(
                driver.getTitle().contains("Altoro"),
                "Altoro Mutual application did not load"
            );

        } finally {
            driver.quit();
        }
    }
}