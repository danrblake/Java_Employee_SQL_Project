package com.sparta.drb.DataStorage;
import com.sparta.drb.model.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class EmployeeDAO {
    private final String PATH = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER = "system";
    private final String PASS = "Acad3my1";
    private String query;

    public void createDatabase() {
        query = "CREATE TABLE hashEmployees ( employee_id varchar(50) NOT NULL, title varchar(10) NOT NULL, first_name varchar(60) NOT NULL" +
                ", middle_initial char(5), last_name varchar(60) NOT NULL, gender char(1) NOT NULL, email varchar(100) NOT NULL, date_of_birth date NOT NULL, hire_date date NOT NULL, salary Number(10) NOT NULL, PRIMARY KEY (employee_id))";

        try (Connection connection = DriverManager.getConnection(PATH, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Employee> getAllEmployees() {
        query = "SELECT * from hashEmployees";
        Map<String, Employee> employeeMap = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(PATH, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Employee newEmployee = new Employee(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7),(set.getDate(8).toLocalDate()), (set.getDate(9).toLocalDate()), set.getString(10));
                employeeMap.put(set.getString(1), newEmployee);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } return employeeMap;
    }

    public Employee getEmployee(String id) {
        query = "Select * from hashEmployees e WHERE e.employee_id = ?";
        Employee newEmployee = null;
        try (Connection connection = DriverManager.getConnection(PATH, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                newEmployee = new Employee(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7),(set.getDate(8).toLocalDate()), (set.getDate(9).toLocalDate()), set.getString(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } return newEmployee;
    }

    public void insertEmployee(Employee employee) {
        query = "INSERT INTO hashEmployees(employee_id,title, first_name,middle_initial, last_name, gender, email, date_of_birth, hire_date, salary) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(PATH, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employee.getId());
            statement.setString(2, employee.getTitle());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getMiddleInitial());
            statement.setString(5, employee.getLastName());
            statement.setString(6, employee.getGender());
            statement.setString(7, employee.getEmail());
            statement.setDate(8, Date.valueOf(employee.getDob()));
            statement.setDate(9, Date.valueOf(employee.getHireDate()));
            statement.setInt(10, employee.getSalary());
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertEmployees(Collection<Employee> employees) {
    int count = 0;
    Connection connection = null;
    try{
        connection = DriverManager.getConnection(PATH, USER, PASS);
    } catch (SQLException e){
        e.printStackTrace();
    }
        try {
            int rowsAdded = 0;
            for (Employee employee : employees) {
                if(count == 280) {
                    connection.close();
                    count = 0;

                    try {
                        connection = DriverManager.getConnection(PATH, USER, PASS);
                    } catch(SQLException e){
                        e.printStackTrace();
                    }
                }

                count++;
                query = "INSERT INTO hashEmployees VALUES (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, employee.getId());
                statement.setString(2, employee.getTitle());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, employee.getMiddleInitial());
                statement.setString(5, employee.getLastName());
                statement.setString(6, employee.getGender());
                statement.setString(7, employee.getEmail());
                statement.setDate(8, Date.valueOf(employee.getDob()));
                statement.setDate(9, Date.valueOf(employee.getHireDate()));
                statement.setInt(10, employee.getSalary());
                try {
                    statement.executeUpdate();
                } catch(SQLException e){
                }
                rowsAdded++;
            }
            System.out.println(rowsAdded+ "rows created in table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeInfo(String id, String info){
        query = "UPDATE hashEmployees e SET e.last_name = ? WHERE e.employee_id = ?";
            try (Connection connection = DriverManager.getConnection(PATH, USER, PASS)) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, info);
                statement.setString(2, id);
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Employee not found");
        }
    }

    public void deleteEmployee(String id) {
        query = "DELETE from hashEmployees WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(PATH, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
