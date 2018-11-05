package com.sparta.drb;

import com.sparta.drb.DataStorage.EmployeeDAO;
import com.sparta.drb.model.Employee;
import com.sparta.drb.model.EmployeeList;
//import control.FileManager;
import control.FileManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit test for simple Starter.
 */
public class EmployeeTester
{
    private static Map testEmployeeList;
    private static String path = "resources/EmployeeRecords.csv";
    private static EmployeeDAO employeeDAO = new EmployeeDAO();

    @BeforeClass
    public static void setUpTests(){
        FileManager fileManager = new FileManager();
        fileManager.setFile(path);
        Map employeeList = new HashMap<>();



    }
    @Before
    public void setUp(){

    }

    @Test
    public void testFileConversion(){
        assertEquals(testEmployeeList.size(), 9_943);
    }

    @Test
    public void testEmployeeFound(){
        assertTrue(testEmployeeList.containsKey("207808"));
    }

    @Test
    public void testEmployeeNotFound(){
        assertFalse(testEmployeeList.containsKey("324574"));
    }

    @Test
    public void databaseSize(){
        int counter = 0;
        Map<String, Employee> allEmployees = employeeDAO.getAllEmployees();
        for ( Object employee: allEmployees.values()) {
            counter++;
        }
        assertEquals(counter, 9943);
    }
    @Test
    public void isEmployeeThere(){
        String correctId = "537774";
        String incorrectID = "124345";
        assertNotNull(employeeDAO.getEmployee(correctId));
        assertNull(employeeDAO.getEmployee(incorrectID));
    }

    @Test
    public void testUpdate(){
        String id = "537774";
        employeeDAO.updateEmployeeInfo(id,"Blake");
        assertEquals(employeeDAO.getEmployee(id).getLastName(), "Blake" );
    }
}
