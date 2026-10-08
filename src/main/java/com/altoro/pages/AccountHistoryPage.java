package com.altoro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountHistoryPage {

    private WebDriver driver;

    // Available Balance
    private By availableBalance = By.xpath(
            "//td[normalize-space()='Available balance']/following-sibling::td[1]"
    );

    // Electric Bill amount inside Debits table
    private By electricBillAmount = By.xpath(
            "//div[@id='debits']//tr[td[normalize-space()='Electric Bill']]/td[last()]"
    );

    public AccountHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAvailableBalance() {
        return driver.findElement(availableBalance)
                .getText()
                .trim();
    }

    public String getElectricBillAmount() {
        return driver.findElement(electricBillAmount)
                .getText()
                .trim();
    }
}