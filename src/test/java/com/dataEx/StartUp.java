package com.dataEx;

import com.dataEx.bo.TestcaseBO;
import com.dataEx.fileReaders.excel.TestCaseFileReader;
import com.dataEx.fileReaders.properties.PropertyReader;
import com.dataEx.testng.RuntimeTestNG;

import java.util.List;

public class StartUp {

    public static void main(String[] args) {

        // read properties file
        PropertyReader propertyReader = new PropertyReader();

        // read testcase file
        TestCaseFileReader testCaseFileReader = new TestCaseFileReader();
        List<TestcaseBO> testcaseList = testCaseFileReader.read();

        // Start testNG execution
        RuntimeTestNG testNG = new RuntimeTestNG();
        testNG.create(testcaseList).run();
    }
}
