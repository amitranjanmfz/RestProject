package com.amit.framework.qa.tests.api.API_Classes;

import com.amit.framework.qa.helpers.services.UtilityFunctions;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Twitter_API {


    @Test
    public void testCustomJson() throws JSONException {

        Response response = UtilityFunctions.doGetRequest("http://localhost:3000/users");
        System.out.println(response.asString());
        JSONArray arr=new JSONArray(response.asString());
        System.out.println(arr.length());
        JSONArray merchants=arr.getJSONObject(0).getJSONArray("merchants");
        System.out.println(merchants + " \n "+merchants.length());


}



    @Test
    public void testCustomJson2() throws JSONException {

        String payload="{\n" +
                " \"users\":[\n" +
                "   {\n" +
                "     \"id\":\"1\",\n" +
                "     \"firstName\":\"Amit\",\n" +
                "     \"lastName\":\"Amit@gmail.com\",\n" +
                "     \"age\":\"24\",\n" +
                "     \"company\":\"wipro\",\n" +
                "     \"merchants\":[\n" +
                "      {\n" +
                "        \"merchantID\":\"1\",\n" +
                "        \"merchantName\":\"merchant1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"merchantID\":\"2\",\n" +
                "        \"merchantName\":\"merchant2\"\n" +
                "      }\n" +
                "\n" +
                "     ]\n" +
                "   },\n" +
                "   {\n" +
                "     \"id\":\"12\",\n" +
                "     \"firstName\":\"Ranjan\",\n" +
                "     \"lastName\":\"Ranjan@gmail.com\",\n" +
                "     \"age\":\"25\",\n" +
                "     \"company\":\"infy\"\n" +
                "   },\n" +
                "   {\n" +
                "     \"id\":\"14\",\n" +
                "     \"firstName\":\"Ajay\",\n" +
                "     \"lastName\":\"Ajay@gmail.com\",\n" +
                "     \"age\":\"35\",\n" +
                "     \"company\":\"cisco\"\n" +
                "   }\n" +
                "\n" +
                " ]\n" +
                "\n" +
                "}\n";

        String payload1="{\n" +
                "    \"id\": \"15\",\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"john@gmail.com\",\n" +
                "    \"age\": \"68\",\n" +
                "    \"company\": \"bosch\",\n" +
                "    \"merchants\": [\n" +
                "      {\n" +
                "        \"merchantID\": \"1\",\n" +
                "        \"merchantName\": \"merchant1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"merchantID\": \"2\",\n" +
                "        \"merchantName\": \"merchant2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

        System.out.println(payload.toString());
        Response response = UtilityFunctions.doRequestWithoutParam("POST","http://localhost:3000/users/",payload1);





    }


    @Test
    public void testCustomJson3() throws JSONException {
        String payload="{\n" +
                "    \"id\": \"15\",\n" +
                "    \"firstName\": \"Amit\",\n" +
                "    \"lastName\": \"john@gmail.com\",\n" +
                "    \"age\": \"68\",\n" +
                "    \"company\": \"bosch\",\n" +
                "    \"merchants\": [\n" +
                "      {\n" +
                "        \"merchantID\": \"4\",\n" +
                "        \"merchantName\": \"merchant1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"merchantID\": \"5\",\n" +
                "        \"merchantName\": \"merchant2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }";
        Response response = UtilityFunctions.doGetRequest("http://localhost:3000/users");
        System.out.println(response.asString());
        /*JSONArray arr=new JSONArray(response.asString());
        System.out.println(arr.length());
        JSONObject js1=arr.getJSONObject(0).put("age","68");
        arr.put(0,js1);*/

        response=UtilityFunctions.doRequestWithoutParam("PUT","http://localhost:3000/users/1",payload);
    }
}



