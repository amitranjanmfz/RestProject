package com.amit.framework.qa.tests.api.API_Classes;

public class Student {
    public String name;
    public int age;
    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String toJSON() {
        return String.format("{\"name\":\"%s\", \"age\":%d}", name, age);
    }
}
