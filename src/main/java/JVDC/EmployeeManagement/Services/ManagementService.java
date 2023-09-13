package JVDC.EmployeeManagement.Services;

import JVDC.EmployeeManagement.Model.Employee;
import JVDC.EmployeeManagement.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ManagementService {
    @Autowired
    EmployeeRepository employeeRepository;

    public ManagementService() {
    }
    List<Employee> employees = new ArrayList<>();
    /**
     * displayEmployees:
     * This function will return a Page of Employee
     *
     * @param employee_name <String> employee's name that searching
     * @param pageable pageable, an interface from Spring boot
     *
     * @return employeePage
     */
    public Page<Employee> displayEmployees(String employee_name, Pageable pageable) {
        employees = employeeRepository.find(employee_name);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Employee> list;

        if (employees.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, employees.size());
            list = employees.subList(startItem, toIndex);
        }
        Page<Employee> employeePage
                = new PageImpl<Employee>(list, PageRequest.of(currentPage, pageSize), employees.size());
        return employeePage;
    }

    /**
     * getPageNumbers:
     *
     * @param employeePage input a Page
     * @return a list that display all the page that exist
     */
    public List<Integer> getPageNumbers(Page employeePage){
    int totalPages = employeePage.getTotalPages();
        if (totalPages > 0) {
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            return pageNumbers;
    }
        else return null;
    }

    /**
     * getEmployee:
     * @param id Id of employee that user want to see detail
     * @return return Employee meet the id
     */
    public Employee getEmployee(int id) {
        return employeeRepository.findById(id);
    }

    /**
     * add: adding a new employee to database
     * @param employee_name name of new employee
     * @param email email of new employee
     * @param phone_number phone number of new employee
     */
    public void add(String employee_name, String email, String phone_number){
    Employee employee = new Employee(employee_name, email, phone_number);
    employeeRepository.insert(employee); // call insert function from employeeRepository, which performs SQL syntax.
    }

    /**
     * modify: set new information for employee, and store it to database
     * @param id employee's id tend to be modified
     * @param employee_name new employee name
     * @param email new employee email
     * @param phone_number new employee number
     */
    public void modify(int id, String employee_name, String email, String phone_number){
        Employee employee = employeeRepository.findById(id);
        employee.setEmployee_name(employee_name);
        employee.setPhone_number(phone_number);
        employee.setEmail(email);
        employeeRepository.update(employee); // call insert function from employeeRepository, which performs SQL syntax to update object.
    }

    /**
     * modifyConfirm: return an Employee object that stored employee new information, to parse to html, lets user confirm information
     * @param id employee id
     * @param employee_name employee new name
     * @param email employee new email
     * @param phone_number employee new phone number
     * @return an Employee object
     */
    public Employee modifyConfirm(int id, String employee_name, String email, String phone_number){
        Employee employee = employeeRepository.findById(id);
        employee.setEmployee_name(employee_name);
        employee.setEmail(email);
        employee.setPhone_number(phone_number);
        return employee;
    }
}
