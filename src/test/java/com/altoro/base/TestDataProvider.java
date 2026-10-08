package com.altoro.base;

import org.testng.annotations.DataProvider;

import com.altoro.utils.ExcelReader;

public class TestDataProvider {

    @DataProvider(name = "accountData")
    public static Object[][] accountData() {

        return ExcelReader.getTestData(
                "TestData.xlsx",
                "Sheet1"
        );
    }
}