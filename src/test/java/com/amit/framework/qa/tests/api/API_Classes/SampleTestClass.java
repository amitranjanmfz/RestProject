package com.amit.framework.qa.tests.api.API_Classes;

import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class SampleTestClass {

    @Test(dataProvider = "getData",dataProviderClass = JsonDataProvider.class)
    public void testMethod(TestData data) throws FileNotFoundException {
        System.out.println(data);
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/test/resources/"+data.getJsonFile()));
            System.out.println(obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
