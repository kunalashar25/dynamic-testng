package com.dataEx.bo;

public class TestcaseBO {

    private String testcaseName;
    private String pageName;

    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @Override
    public String toString() {
        return "TestcaseBO{" +
                "testcaseName='" + testcaseName + '\'' +
                ", pageName='" + pageName + '\'' +
                '}';
    }
}
