package com.amit.framework.qa.tests.api.API_Classes;

import com.amit.framework.qa.helpers.services.UtilityFunctions;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JsonToJava_MultipleJsons {

    @Test(timeOut = 100)
    public void test1() throws MalformedURLException, InterruptedException {
        Thread.sleep(2000);
    }

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
//also check other ways to write value other than to file
        for(int i=0;i<lst.size();i++) {
            ObjectMapper objMap = new ObjectMapper();
            objMap.writeValue(new File("C:\\Users\\amiranja3\\Desktop\\CucumberFramework\\RestAssuredProject\\customerInfo"+i+".json"), lst.get(i));
        }
        UtilityFunctions.con.close();
    }

}
