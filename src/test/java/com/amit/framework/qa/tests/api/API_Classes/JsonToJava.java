package com.amit.framework.qa.tests.api.API_Classes;


import com.amit.framework.qa.helpers.services.UtilityFunctions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class JsonToJava {

   @Test
   public void readFromDB() throws ClassNotFoundException, SQLException, IOException {

       ResultSet rs=UtilityFunctions.readFromDB("Select * from CustomerInfo where location='Asia' LIMIT 1;");
       CustomerDetails cd=new CustomerDetails();;
       while (rs.next()){
           cd.setCourseName(rs.getString(1));
           cd.setPurchasedDate(rs.getString(2));
           cd.setAmount(rs.getInt(3));
           cd.setLocation(rs.getString(4));
           System.out.print(cd.getCourseName()+" ");
           System.out.print(cd.getPurchasedDate()+" ");
           System.out.print(cd.getAmount()+" ");
           System.out.print(cd.getLocation()+" ");

       }
//also check other ways to write value other than to file
       ObjectMapper objMap=new ObjectMapper();
       objMap.writeValue(new File("C:\\Users\\amiranja3\\Desktop\\CucumberFramework\\RestAssuredProject\\customerInfo.json"),cd);
       UtilityFunctions.con.close();
   }

}
