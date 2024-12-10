package com.employee.amaris.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Employee {
    @JsonProperty("id")
    String id;
    @JsonProperty("employee_name")
    String name;
    @JsonProperty("employee_salary")
    float salary;
    @JsonProperty("employee_age")
    int age;
    @JsonProperty("profile_image")
    String image;
    float annualSalary;


    public float getSalary() {
        return this.salary;
    }

    public void setAnnualSalary(float salary) {
        this.salary = salary;
    }
}