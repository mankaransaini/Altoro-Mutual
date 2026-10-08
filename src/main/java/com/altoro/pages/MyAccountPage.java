package com.altoro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class MyAccountPage {

    private WebDriver driver;

    private By accountDropdown = By.id("listAccounts");
    private By goButton = By.id("btnGetAccount");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectAccount(String accountName) {
        Select account = new Select(driver.findElement(accountDropdown));
        account.selectByVisibleText(accountName);
    }

    public AccountHistoryPage clickGo() {
        driver.findElement(goButton).click();
        return new AccountHistoryPage(driver);
    }
}