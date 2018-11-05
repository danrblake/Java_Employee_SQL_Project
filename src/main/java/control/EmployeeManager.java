package control;

import com.sparta.drb.DataStorage.EmployeeDAO;
import com.sparta.drb.model.Employee;
import com.sparta.drb.view.ConsoleView;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EmployeeManager {
    private ConsoleView consoleView = new ConsoleView();
    private Map employeeList;

    public void run(){
        addToEmployeeList();
        database();
//        printList(employeeList);
//        getEmployee("811306");
    }

    public void database(){
        EmployeeDAO employeeDAO = new EmployeeDAO();
//        employeeDAO.getAllEmployees();
        employeeDAO.createDatabase();
        employeeDAO.insertEmployees(createEmployeeHashSet(employeeList));

    }
    private void getEmployee(String id){
        consoleView.displayEmployeeInformation((Employee)employeeList.get(id));
    }
    private void addToEmployeeList(){
        String file = "resources/EmployeeRecords.csv";
        FileManager fileManager = new FileManager();
        fileManager.setFile(file);
        employeeList = new HashMap<String, Employee>();
        employeeList = fileManager.convertFileWithStream();
    }

    private void printList(Map employeeList){
        consoleView.displayEmployeeList(employeeList);
    }
    public Collection createEmployeeHashSet(Map employeeList){
        Collection<Employee> employees = new HashSet<>(employeeList.values());
        return employees;
    }

}
