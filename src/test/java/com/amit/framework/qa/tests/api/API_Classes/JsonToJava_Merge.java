package com.amit.framework.qa.tests.api.API_Classes;


import com.amit.framework.qa.helpers.services.UtilityFunctions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JsonToJava_Merge {

    @Test
    public void readFromDB() throws ClassNotFoundException, SQLException, IOException {

        ResultSet rs=UtilityFunctions.readFromDB("Select * from CustomerInfo where location='Asia';");
        List<CustomerDetails> lst=new ArrayList<CustomerDetails>();
        while (rs.next()){
            CustomerDetails cd=new CustomerDetails();
            cd.setCourseName(rs.getString(1));
            cd.setPurchasedDate(rs.getString(2));
            cd.setAmount(rs.getInt(3));
            cd.setLocation(rs.getString(4));
            lst.add(cd);
        }

        //cretae json array object from simple.json not org.json
        JSONArray jArray=new JSONArray();

        for(int i=0;i<lst.size();i++) {
            //to map object with json (in jackson package)
            ObjectMapper objMap = new ObjectMapper();
            objMap.writeValue(new File("C:\\Users\\amiranja3\\Desktop\\CucumberFramework\\RestAssuredProject\\customerInfo"+i+".json"), lst.get(i));
            //convert java object to json string (Gson package)
            Gson gs=new Gson();
            String jsonString= gs.toJson(lst.get(i));
            jArray.add(jsonString);
        }

        //json simple jsonObject not org.json
        JSONObject jObj=new JSONObject();
        jObj.put("data",jArray);
        String str1=jObj.toJSONString().replace("\\","");
        String finalString=str1.replace("\"{","{").replace("}\"","}");
        System.out.println(finalString);
        try(FileWriter file=new FileWriter("C:\\Users\\amiranja3\\Desktop\\CucumberFramework\\RestAssuredProject\\customerInfoMerged.json")){
            file.write(finalString);
            System.out.println("Successfully copied to file...");
        }
        UtilityFunctions.con.close();
    }

}
