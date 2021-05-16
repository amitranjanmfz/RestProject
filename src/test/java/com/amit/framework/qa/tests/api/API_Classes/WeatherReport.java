package com.amit.framework.qa.tests.api.API_Classes;

import com.amit.framework.qa.helpers.services.UtilityFunctions;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;


import static io.restassured.RestAssured.given;

public class WeatherReport {

    @Test
    public void testWeather() throws JSONException {

        Response response = UtilityFunctions.doGetRequest("http://api.vidible.tv/swagger/v2/api-docs?group=o2");
        System.out.println(response.asString());
        JSONObject jsonResponse = new JSONObject(response.body().asString());
        System.out.println(jsonResponse.length());
        String sw = jsonResponse.getString("swagger");
        System.out.println(sw);
        JSONObject license = jsonResponse.getJSONObject("info").getJSONObject("license");
        System.out.println("licenseName : " + license.getString("name"));
        JSONArray tags = jsonResponse.getJSONArray("tags");
        System.out.println(tags.length());
        System.out.println(tags);
        for (int i = 0; i < tags.length(); i++) {
            System.out.println(tags.getJSONObject(i).getString("name"));
        }

    }

        @Test
        public void testLocation() throws JSONException, IOException {
        String url="https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4&fields=name,rating,formatted_phone_number";
        String keyToken="AIzaSyCzViMhr7A0nVUzM6qHKjj4VyCPv2ikuPo";
        Response response=UtilityFunctions.doRequestWithKey("GET",url,"",keyToken);
        System.out.println(response.asString());
        JSONObject jsonResponse = new JSONObject(response.body().asString());
        JSONObject jsonResponse2 = new JSONObject(jsonResponse.get("result").toString());
        System.out.println(jsonResponse2);
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(jsonResponse2.toString(), Map.class);
        System.out.println("----------------MAP---------------------");
        System.out.println(map);
        System.out.println("----------------MAP END---------------------");
        System.out.println("Rating : "+map.get("rating"));
     }

    @Test
    public void postCustomer() throws JSONException {

         RequestSpecification request = RestAssured.given();
         JSONObject requestParams = new JSONObject();
         requestParams.put("FirstName", "Amit");
         requestParams.put("LastName", "Ranjan");
         requestParams.put("UserName", "amit1290");
         requestParams.put("Password", "password1");
         requestParams.put("Email",  "amit1290@gmail.com");
         Response resp=UtilityFunctions.doRequestWithoutParam("POST","http://restapi.demoqa.com/customer/register",requestParams.toString());
         System.out.println(resp.asString());

     }

    @Test
    public void fetchComments() throws JSONException {


        Response response=UtilityFunctions.doRequestWithKey("GET","https://jsonplaceholder.typicode.com/comments",""," ");

        Response response2 = given()
                .when()
                .queryParam("postId", "1")
                .get("https://jsonplaceholder.typicode.com/comments")
                .then().contentType("application/json")
                .log().all()
                .extract()
                .response();


    }



    @Test
    public void getLocation() throws JSONException {

        String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json";
        String keyToken="AIzaSyCzViMhr7A0nVUzM6qHKjj4VyCPv2ikuPo";
        String location="-33.8670522,151.1957362";
        String radius="10";
        Response response=UtilityFunctions.doRequestWithKeyAndParams("GET",url,"",keyToken,location,radius);
        System.out.println(response.asString());
        JSONObject resp1=new JSONObject(response.body().asString());
        JSONArray resp2=resp1.getJSONArray("results");
        System.out.println(resp2.length());
        for(int i=0;i<resp2.length();i++){
            System.out.println("json :"+i);
            System.out.println(resp2.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat"));
        }



    }


    @Test
    public void postRequest_weather(){

        String url="https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4&fields=name,rating,formatted_phone_number";
        String keyToken="AIzaSyCzViMhr7A0nVUzM6qHKjj4VyCPv2ikuPo";
        String body="{"+
                "\"location\": {"+
                "\"lat\": -33.8669710,"+
                "\"lng\": 151.1958750"+
                "},"+
                "\"accuracy\": 50,"+
                "\"name\": \"Google Shoes!\","+
                "\"phone_number\": \"(02) 9374 4000\","+
                "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
                "\"types\": [\"shoe_store\"],"+
                "\"website\": \"http://www.google.com.au/\","+
                "\"language\": \"en-AU\""+
                "}";

        Response response=UtilityFunctions.doRequest_Post("POST",url,body,keyToken);
        System.out.println(response.asString());

    }





}



