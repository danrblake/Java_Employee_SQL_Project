package com.sparta.drb.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.lang.Integer.parseInt;
public class Employee {
    private String id;
    private String title;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private LocalDate dob;
    private LocalDate hireDate;
    private int salary;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleInitial() {
        return middleInitial;
    }
    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public LocalDate getHireDate() {
        return hireDate;
    }
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salaryCon(salary);
    }
    public Employee(String id, String title, String firstName, String middleInitial, String lastName, String gender, String email, LocalDate dob, LocalDate hireDate, String salary){
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.hireDate = hireDate;
        this.salary = salaryCon(salary);
    }

    public int salaryCon(String salary){
        return parseInt(salary);
    }

    @Override
    public String toString(){
        return "ID: " + id + " Title: " + title + " First Name: " + firstName + " " + middleInitial + " Last Name: "
                + lastName + " Gender: " + gender + " Email: " + " Date of Birth: " + dob
                + " Hire Date: " + hireDate.toString() + "  Salary: " + salary;
    }

}
