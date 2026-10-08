package com.altoro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By usernameField = By.id("uid");
    private By passwordField = By.id("passw");
    private By loginButton = By.name("btnSubmit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public MyAccountPage clickLogin() {
        driver.findElement(loginButton).click();
        return new MyAccountPage(driver);
    }

    public MyAccountPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }
}