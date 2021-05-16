package com.amit.framework.qa.tests.api.API_Classes;


import org.testng.annotations.Test;

import java.io.*;
import java.sql.*;

public class ReadFromJson {

   @Test
   public void readFromJson() throws ClassNotFoundException, SQLException, IOException {
       /*ObjectMapper objMap=new ObjectMapper();
       CustomerDetails cd=objMap.readValue(new File("C:\\Users\\amiranja3\\Desktop\\CucumberFramework\\RestAssuredProject\\customerInfo.json"),CustomerDetails.class);
      *//* String a=objMap.readValue(new File("C:\\Users\\amiranja3\\Desktop\\CucumberFramework\\RestAssuredProject\\customerInfo.json"),CustomerDetails.class).getClass().getName();
       System.out.println(a);*//*
       System.out.println(cd.getLocation());*/
     /*  HashMap obj=new HashMap();
       obj.put("A",new Integer(1));
       System.out.println(obj);
*/
  /*   StringBuffer s1=new StringBuffer("hello");
     s1.deleteCharAt(1);

       System.out.println(s1);*/

     /*  BitSet obj1 = new BitSet(5);
       BitSet obj2 = new BitSet(10);
       for (int i = 0; i < 5; i++)
           obj1.set(i);
       for (int i = 3; i < 13; i++)
           obj2.set(i);
       obj1.and(obj2);
       System.out.println(obj1);*/
    /* int i;
       for ( i = 0; i < 100; i++)
           if(i==33)
               continue;
       System.out.println(i);*/
   String test1=new String("Test");
   String test2="Test";
   String test3=test1;
       System.out.println(test1.equals(test2));
       System.out.println(test1==test2);
       System.out.println(test1.equals(test3));
   }




}


