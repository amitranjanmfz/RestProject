package com.amit.framework.qa.tests.api.API_Classes;

public class TestData {
    private String testCase;
    private String username;
    private String password;
    private boolean rememberMe;
    private String jsonFile;

    public String getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(String jsonFile) {
        this.jsonFile = jsonFile;
    }


    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "{" +
                "testCase='" + testCase + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe + '\'' +
                ", jsonFile=" + jsonFile +
                '}';
    }
}