package com.amit.framework.qa.helpers.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

import static io.restassured.RestAssured.given;

public class UtilityFunctions {

    public static Connection con=null;

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }

    public static Response doRequestWithKey(String method,String url,String body,String key){

        Response response=given()
                .log().all()
                .contentType(ContentType.JSON)
                .param("key",key)
//                .proxy(prop.getProperty("proxy.url"))
                .body(body)
                .when()
                .request(method,url)
                .then()
                .log().all()
                .extract().response();

        return response;
    }
    public static Response doRequestWithoutParam(String method,String url,String body){

        Response response=given()
                .log().all()
                .contentType(ContentType.JSON)
//              .proxy(prop.getProperty("proxy.url"))
                .body(body)
                .when()
                .request(method,url)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    public static Response doRequestWithKeyAndParams(String method,String url,String body,String key,String location,String radius){

        Response response=given()
                .log().all()
                .contentType(ContentType.JSON)
                .param("key",key)
                .param("location",location)
                .param("radius",radius)
//                .proxy(prop.getProperty("proxy.url"))
                .body(body)
                .when()
                .request(method,url)
                .then()
                .log().all()
                .extract().response();

        return response;
    }


    public static Response doRequest_Post(String method,String url,String body,String key){

        Response response=given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("key",key)  // can send only query parameter when sending post request
                .body(body)
                .when()
                .request(method,url)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

    //to read json string from a json file by giving folder path
    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static JsonPath rawToJson(Response r){
        String response=r.asString();
        JsonPath x=new JsonPath(response);
        return  x;
    }

    public static String getSessionKey() throws JSONException {

        Response res=given().header("Content-Type","application/json").
        body("{ \"username\": \"aamitranjan007\", \"password\": \"admin\" }").
        when().
        post("http://localhost:8080/rest/auth/1/session").
        then().log().all()
        .extract().response();

        JSONObject resp2=new JSONObject(res.body().asString());
        String sessionID=resp2.getJSONObject("session").getString("value");
        return sessionID;
    }


    public static ResultSet readFromDB(String query) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","root");
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        return rs;
    }

}
