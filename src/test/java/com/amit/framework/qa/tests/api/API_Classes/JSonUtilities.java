package com.amit.framework.qa.tests.api.API_Classes;

import com.amit.framework.qa.helpers.services.UtilityFunctions;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;


public class JSonUtilities {

    public static String recurseKeys(JSONObject jObj, String findKey) throws JSONException {
        String finalValue = "";
        if (jObj == null) {
            return "";
        }

        Iterator<String> keyItr = jObj.keys();
        Map<String, String> map = new HashMap<>();

        while (keyItr.hasNext()) {
            String key = keyItr.next();
            map.put(key, jObj.getString(key));
        }
        for (Map.Entry<String, String> e : (map).entrySet()) {
            String key = e.getKey();
            if (key.equalsIgnoreCase(findKey)) {
                return jObj.getString(key);
            }
        }

        // key is not found
        return finalValue;

    }

    @Test
    public static void getValues(String key) throws JSONException {
        Response response = UtilityFunctions.doGetRequest("http://localhost:3000/users");
        JSONArray arr = new JSONArray(response.asString());
        List<String> lst=new ArrayList<>();
        for(int i=0;i<arr.length();i++){
            lst.add(recurseKeys(arr.getJSONObject(i),key));
        }
        lst.stream().forEach(System.out::println);
    }

    /* get value for a json object */
    @Test
    public static void getValuesForAKey() throws JSONException {
        getValues("firstName");
    }


    @Test
    public static void compareJsonObjects() throws JSONException, IOException {
        Response response1 = UtilityFunctions.doGetRequest("http://localhost:3000/users");
        JSONArray arr=new JSONArray(response1.asString());
        List<JSONObject> lst=new ArrayList<>();
        for(int i=0;i<arr.length();i++){
            lst.add(arr.getJSONObject(i));
        }
        List<JSONObject> lstSorted1=sortJsonArrayByKey("firstName",false);
        lstSorted1.stream().forEach(System.out::println);

        System.out.println("---------------------");

        Response response2 = UtilityFunctions.doGetRequest("http://localhost:3000/users");
        JSONArray arr2=new JSONArray(response2.asString());
        List<JSONObject> lst2=new ArrayList<>();
        for(int i=0;i<arr2.length();i++){
            lst2.add(arr2.getJSONObject(i));
        }
        List<JSONObject> lstSorted2=sortJsonArrayByKey("firstName",false);
        lstSorted2.stream().forEach(System.out::println);

        ObjectMapper mapper = new ObjectMapper();

        for(int j=0;j<lstSorted1.size();j++) {
            Assert.assertEquals(mapper.readTree(lstSorted1.get(j).toString()), mapper.readTree(lstSorted2.get(j).toString()));
        }

    }

    @Test
    public static List<JSONObject> sortJsonArrayByKey(String key,Boolean reverse) throws JSONException {
        Response response = UtilityFunctions.doGetRequest("http://localhost:3000/users");
        JSONArray arr=new JSONArray(response.asString());
        List<JSONObject> lst=new ArrayList<>();
        for(int i=0;i<arr.length();i++){
            lst.add(arr.getJSONObject(i));
        }
        if(reverse==true)
            Collections.sort(lst,new MyComparator(key).reversed());
        else
            Collections.sort(lst,new MyComparator(key));
        return lst;

    }

    /* sort all json objects by a key */
    @Test
    public static void sortJsonArrayByKeyFunc() throws JSONException {
        List<JSONObject> lst2=sortJsonArrayByKey("firstName",true);
        lst2.stream().forEach(System.out::println);
    }


    //replace json value
    @Test
    public static void changeJsonObject() throws JSONException {
        Response response = UtilityFunctions.doGetRequest("http://localhost:3000/users");
        JSONArray arr=new JSONArray(response.asString());
        JSONObject obj=arr.getJSONObject(0);
        obj.put("age","69");
//        System.out.println(obj);
        arr.put(0,obj);
//        System.out.println(arr);

        // replacing user with id 1
        response=UtilityFunctions.doRequestWithoutParam("PUT","http://localhost:3000/users/1",obj.toString());

    }




}
