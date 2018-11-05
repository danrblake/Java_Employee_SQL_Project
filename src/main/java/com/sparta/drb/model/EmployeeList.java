package com.sparta.drb.model;

import java.util.HashMap;
import java.util.Map;

public class EmployeeList {
    private Map<Integer, Employee> employeeList = new HashMap<>();

    public void addToList(Integer id, Employee newEmployee){
        employeeList.put(id,newEmployee);
    }
}
