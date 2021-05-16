package com.amit.framework.qa.tests.api.API_Classes;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class JsonAssertion {

    @Test
    public void lineantAssertion() throws IOException {

        String actual = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"age\": 34,\n" +
                "        \"skills\": [\"Java\", \"C++\", \"Python\"]\n" +
                "    }\n" +
                "}";
        String expected = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"age\": 34,\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"skills\": [\"Java\", \"C++\", \"Python\"] \n" +
                "    } \n" +
                "}";

        try {
            // JSONAssert.assertEquals("{name:\"John\"}", actual, JSONCompareMode.STRICT);
            JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
        } catch (Exception ae) {
            ae.printStackTrace();
        }
    }
    @Test
    public void strictAssertion() throws IOException {

        String actual = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"age\": 34,\n" +
                "        \"skills\": [\"Java\", \"C++\", \"Python\"]\n" +
                "    }\n" +
                "}";
        String expected = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"age\": 34,\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"skills\": [\"Java\", \"C++\", \"Python\"] \n" +
                "    } \n" +
                "}";

        try {
            // JSONAssert.assertEquals("{name:\"John\"}", actual, JSONCompareMode.STRICT);
            JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
        } catch (Exception ae) {
            ae.printStackTrace();
        }
    }
    @Test
    public void complexLineantAssertion() throws IOException {

        String actual = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"age\": 34,\n" +
                "        \"skills\": [\"Java\", \"C++\", \"Python\"],\n" +
                "        \"qualifications\": {\"degree\":\"Bachelors\", \"Type\":\"Full Time\"}\n" +
                "    }\n" +
                "}";
        String expected = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"skills\": [\"C++\",\"Java\", \"Python\"],\n" +
                "        \"age\": 34,\n" +
                "        \"qualifications\": {\"Type\":\"Full Time\",\"degree\":\"Bachelors\"}\n" +
                "    }\n" +
                "}";

        try {
            // JSONAssert.assertEquals("{name:\"John\"}", actual, JSONCompareMode.STRICT);
            JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT_ORDER);
        } catch (Exception ae) {
            ae.printStackTrace();
        }
    }
    @Test
    public void complexStricAssertion() throws IOException {

        String actual = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"age\": 34,\n" +
                "        \"skills\": [\"Java\", \"C++\", \"Python\"]\n" +
                "    }\n" +
                "}";
        String expected = "{\n" +
                "    \"employee\":\n" +
                "    {\n" +
                "        \"id\": \"1212\",\n" +
                "        \"age\": 34,\n" +
                "        \"fullName\": \"John Miles\",\n" +
                "        \"skills\": [\"Java\", \"C++\", \"Python\"] \n" +
                "    } \n" +
                "}";

        try {
            // JSONAssert.assertEquals("{name:\"John\"}", actual, JSONCompareMode.STRICT);
            JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
        } catch (Exception ae) {
            ae.printStackTrace();
        }
    }


    }
