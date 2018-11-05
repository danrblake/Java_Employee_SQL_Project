package control;

import com.sparta.drb.model.Employee;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class FileManager {
    private String file;
    String line;
    private String csvSplit = ",";


    public void setFile(String filePath) {
        this.file = filePath;
    }

    public Map convertFile() {
        Map<String, Employee> employeeList = new HashMap<>();
        try {
            Reader br = new BufferedReader(new FileReader(file));
            ((BufferedReader) br).readLine();
            while (((line = ((BufferedReader) br).readLine()) != null)) {
                String[] employeeDetails = line.split(csvSplit);
                if (employeeDetails[0] != null) {
                    Employee newEmployee = new Employee(employeeDetails[0], employeeDetails[1], employeeDetails[2], employeeDetails[3], employeeDetails[4], employeeDetails[5], employeeDetails[6], toDate(employeeDetails[7]), toDate(employeeDetails[8]), employeeDetails[9]);
                    employeeList.put(employeeDetails[0], newEmployee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public Map convertFileWithStream() {
        Map<String, Employee> employeeList = new HashMap<>();
        try (Reader br = new BufferedReader(new FileReader(file))) {
            ((BufferedReader) br).lines().skip(1).map(line -> line.split(csvSplit)).forEach(employee -> {
                Employee newEmployee = new Employee(employee[0], employee[1], employee[2], employee[3], employee[4], employee[5], employee[6], toDate(employee[7]), toDate(employee[8]), employee[9]);
                employeeList.put(employee[0], newEmployee);
            });
//            sendBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    private LocalDate toDate(String dateToChange) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(dateToChange, formatter);
    }

//    private void addToStoreThreaded(Collection<Employee> records){
//        Runnable runnable = () -> {
//            EmployeeDAO dao = new EmployeeDAO();
//            dao.writeEmployeeRecords(records);
//        }
//    }

//    private void createRecords(String[] fields) {
//        Employee employee = createEmployeeRecord(fields);
//        String key = employee.getId();
//        if (!employeeMap.containsKey(key)) {
//            employeeMap.put(employee.getId(), employee);
//            if (employeeMap.size == 10000) {
//                sendBatch();
//            }
//        } else {
//            setDuplicates(employee);
//        }
//    }
//    private void sendBatch() {
//        Collection<Employee> batch = new HashSet<>(employeeMap.values());
//        addToDataStoreThreaded(batch);
//        employeeMap.clear();
//    }
}

