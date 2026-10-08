package com.altoro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    // Locator
    private By signInLink = By.id("LoginLink");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Action
    public LoginPage clickSignIn() {
        driver.findElement(signInLink).click();
        return new LoginPage(driver);
    }
}