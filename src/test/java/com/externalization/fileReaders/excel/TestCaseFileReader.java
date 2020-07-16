package com.externalization.fileReaders.excel;

import com.externalization.bo.TestcaseBO;
import com.externalization.fileReaders.properties.PropertyReader;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCaseFileReader {

    // testcase file path
    private String testcaseFilePath = "src/test/resources/testCases.xlsx";


    public List<TestcaseBO> read() {

        // to store list of testcases to be executed
        List<TestcaseBO> testcaseList = new ArrayList<>();
        String sheetName = "FourthDraft";

        // get suite name
        String suiteName = PropertyReader.getProperty("suiteToRun").toLowerCase();
        System.out.println("\nInitiating Execution for " + suiteName.toUpperCase() + " Suite\n");

        // creating workbook instance
        XSSFWorkbook workbook = null;

        // initialize workbook
        try {
            workbook = new XSSFWorkbook(testcaseFilePath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // get testcase sheet
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // find total number of rows
        int totalRows = sheet.getPhysicalNumberOfRows();

        // iterating on each row
        for (int i = 1; i < totalRows; i++) {

            // creating map to store header and value of each row
            Map<String, String> testcaseMap = new HashMap<>();

            // get column count in row 1
            int colNum = sheet.getRow(1).getLastCellNum();

            // iterating on each column
            for (int j = 0; j < colNum; j++) {

                String key = sheet.getRow(1).getCell(j).getStringCellValue().toLowerCase();
                String value = null;

                // fetch value from the cell. If NPE, then store as blank.
                try {
                    CellType cellType = sheet.getRow(i + 1).getCell(j).getCellType();

                    switch (cellType) {
                        case STRING:
                            value = sheet.getRow(i + 1).getCell(j).getStringCellValue();
                            break;
                        case NUMERIC:
                            value = String.valueOf(sheet.getRow(i + 1).getCell(j).getNumericCellValue());
                            break;
                        default:
                            value = "";
                            break;
                    }
                } catch (NullPointerException npe) {
                    value = "";
                }

                // add value to the map
                testcaseMap.put(key, value);
            }

            // check if testcase execution flag for given suite
            if (testcaseMap.get(suiteName).equalsIgnoreCase("Y")) {

                // set BO values
                TestcaseBO testcaseBO = new TestcaseBO();
                testcaseBO.setTestcaseName(testcaseMap.get("testcase_name"));
                testcaseBO.setPageName(testcaseMap.get("page_name"));

                // add testcase to the list for execution
                testcaseList.add(testcaseBO);
            }
        }

        // close workbook
        try {
            workbook.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return testcaseList;
    }

    /**
     * Unit test to verify excel reader
     */
    @Test
    public void unitTest() {
        new PropertyReader();

        TestCaseFileReader testCaseFileReader = new TestCaseFileReader();
        testCaseFileReader.read();
    }
}
