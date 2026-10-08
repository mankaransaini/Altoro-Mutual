package com.altoro.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelReader {

    private ExcelReader() {
        // Utility class
    }

    public static Object[][] getTestData(String fileName, String sheetName) {

        List<Object[]> data = new ArrayList<>();

        String resourcePath = "testdata/" + fileName;

        try (InputStream inputStream = ExcelReader.class
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "Excel file not found: " + resourcePath);
            }

            try (Workbook workbook = new XSSFWorkbook(inputStream)) {

                Sheet sheet = workbook.getSheet(sheetName);

                if (sheet == null) {
                    throw new RuntimeException(
                            "Sheet not found: " + sheetName);
                }

                DataFormatter formatter = new DataFormatter();

                for (int rowIndex = 1;
                     rowIndex <= sheet.getLastRowNum();
                     rowIndex++) {

                    Row row = sheet.getRow(rowIndex);

                    if (row == null) {
                        continue;
                    }

                    Object[] rowData = new Object[5];

                    for (int columnIndex = 0;
                         columnIndex < 5;
                         columnIndex++) {

                        rowData[columnIndex] =
                                formatter.formatCellValue(
                                        row.getCell(columnIndex));
                    }

                    data.add(rowData);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to read Excel test data", e);
        }

        return data.toArray(new Object[0][]);
    }
}