package com.externalization;

import com.externalization.bo.TestcaseBO;
import com.externalization.fileReaders.excel.TestCaseFileReader;
import com.externalization.fileReaders.properties.PropertyReader;
import com.externalization.testng.RuntimeTestNG;

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
