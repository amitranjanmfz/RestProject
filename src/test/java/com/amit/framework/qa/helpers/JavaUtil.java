package com.amit.framework.qa.helpers;

import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JavaUtil {

   /* Method to get all the public variables in a class*/

    public static List<String> getPublicFieldVariables(Class cls){

        List<String> lst=new ArrayList<>();
        for(Field field:cls.getDeclaredFields()){
            if(Modifier.isPublic(field.getModifiers())){
                try{
                    lst.add((String) cls.getField(field.getName()).toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return lst;
    }

    public static String getDateFromYearsAgo(int years){

        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.YEAR,years* -1);
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cal.getTime());
    }

    public String getPreviousBusinessDays(int days){

        LocalDate date=LocalDate.now();
        int count=days;
        for(int i=0;i<count;i++){

            date=date.minusDays(1);
            if(isWeekend(date.getDayOfWeek())){
                date=date.minusDays(1);
                days++;
            }

        }
        return getDateFromDaysAgo(days);
    }
    private static boolean isWeekend(DayOfWeek day){
        return day.toString().equals("SUNDAY")||day.toString().equals("SATURDAY");
    }
    private static String getDateFromDaysAgo(int days){
        return getDateFromDaysAgo(days,"yyyy-MM-dd");
    }
    private static String getDateFromDaysAgo(int days,String format){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(format);
        return LocalDate.now().minusDays(days).format(formatter);
    }


    public static String getFileType(String documentType){

        String[] tokens=documentType.split("/");
        return tokens[1];
    }
    public static BigDecimal calculatePercentage(int part,int total){

        BigDecimal totalBg=new BigDecimal(total);
        return new BigDecimal(part).divide(totalBg,2,RoundingMode.HALF_UP).multiply(new BigDecimal(100))
                .setScale(2,BigDecimal.ROUND_HALF_UP);


    }

    public static Short getCurrentYearAsShort(){
        return Short.valueOf(getDateAsString("yyyy"));
    }

    public static String getDateAsString(String format){
        return getDateAsString(format,new Date());
    }

    public static String getDateAsString(String format,Date date){
        return new SimpleDateFormat(format).format(date);
    }





    @Test
    public void test1(){

       /* List<String> lst1=getPublicFieldVariables(DateUtil.class);
        System.out.println(lst1);*/
     /*  String cal1=getDateFromYearsAgo(5);
       System.out.println(cal1);*/

//        System.out.println(getPreviousBusinessDays(2));
        System.out.println(calculatePercentage(1,3));
    }



}
