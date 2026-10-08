package com.altoro.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altoro.base.BaseTest;
import com.altoro.pages.HomePage;
import com.altoro.pages.MyAccountPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {
        HomePage homePage = new HomePage(driver);

        MyAccountPage myAccountPage =
                homePage.clickSignIn()
                        .login("jsmith", "demo1234");

        Assert.assertTrue(
                driver.getPageSource().contains("John Smith"),
                "Login was not successful"
        );
    }
}