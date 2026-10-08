package com.altoro.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altoro.base.BaseTest;
import com.altoro.base.TestDataProvider;
import com.altoro.pages.AccountHistoryPage;
import com.altoro.pages.HomePage;
import com.altoro.pages.MyAccountPage;

public class AccountTest extends BaseTest {
    @Test(
        dataProvider = "accountData",
        dataProviderClass = TestDataProvider.class
    )
    public void verifyAccountDetails(
            String testCase,
            String username,
            String password,
            String account,
            String expectedElectricBill) {
        System.out.println("Executing: " + testCase);

        HomePage homePage = new HomePage(driver);

        MyAccountPage myAccountPage =
                homePage.clickSignIn()
                        .login(username, password);

        myAccountPage.selectAccount(account);

        AccountHistoryPage accountHistoryPage =
                myAccountPage.clickGo();

        String availableBalance =
                accountHistoryPage.getAvailableBalance();

        Assert.assertFalse(
                availableBalance.isBlank(),
                "Available Balance should not be empty"
        );

        String actualElectricBill =
                accountHistoryPage.getElectricBillAmount();

        Assert.assertEquals(
                actualElectricBill,
                expectedElectricBill,
                "Electric Bill amount is incorrect"
        );
    }
}