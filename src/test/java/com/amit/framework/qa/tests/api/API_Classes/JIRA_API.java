package com.amit.framework.qa.tests.api.API_Classes;

import com.amit.framework.qa.helpers.services.UtilityFunctions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JIRA_API {


    @Test
    public void JiraAPICreateIssue() throws JSONException {
        //Creating Issue/Defect
        String sessionKey=UtilityFunctions.getSessionKey();

        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + sessionKey).
                body("{" +
                        "\"fields\": {" +
                        "\"project\":{" +
                        "\"key\": \"RES\"" +
                        "}," +
                        "\"summary\": \"Issue 8 for adding comment\"," +
                        "\"description\": \"Creating my second bug\"," +
                        "\"issuetype\": {" +
                        "\"name\": \"Bug\"" +
                        "}" +
                        "}}").when().
                post("/rest/api/2/issue").then().statusCode(201).extract().response();

        JSONObject resp3=new JSONObject(res.body().asString());
        String ID=resp3.get("id").toString(); //gives IssueID
        System.out.println(ID);

    }

    @Test
    public void Jira_APICreateComment() throws JSONException {
        //Creating Issue/Defect
        String sessionKey=UtilityFunctions.getSessionKey();

        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" +sessionKey).
                body("{ \"body\": \"Inserting comment from the automation code\"," +
                        "\"visibility\": {" +
                        "\"type\": \"role\"," +
                        "\"value\": \"Administrators\" }" +
                        "}").when().
                post("/rest/api/2/issue/10 100/comment/").then().statusCode(201).extract().response();

        JSONObject resp3=new JSONObject(res.body().asString());
        String ID=resp3.get("id").toString();// gives comment ID
        System.out.println(ID);
    }
    @Test
    public void JiraAPIUpdate() throws JSONException {
        //Creating Issue/Defect

        RestAssured.baseURI= "http://localhost:8080";
        Response res=given().header("Content-Type", "application/json").
                header("Cookie","JSESSIONID="+UtilityFunctions.getSessionKey()).
                pathParams("commentid","10100").
                body("{ \"body\": \"Updating comment from the automation code\","+
                        "\"visibility\": {"+
                        "\"type\": \"role\","+
                        "\"value\": \"Administrators\" }"+
                        "}").when().
                put("/rest/api/2/issue/10100/comment/{commentid}").then().statusCode(200).extract().response();
        //issue ID will be mostly different from commentID
    }



}



