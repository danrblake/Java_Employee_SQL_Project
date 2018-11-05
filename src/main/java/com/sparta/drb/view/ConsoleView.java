package com.sparta.drb.view;

import com.sparta.drb.model.Employee;

import java.util.Map;

public class ConsoleView {

    public void displayEmployeeList(Map employeeList){
        for ( Object employee: employeeList.values()) {
            System.out.println(employee.toString());
        }
    }

    public void displayEmployeeInformation(Employee employee){
        System.out.println(employee.toString());
    }
}
