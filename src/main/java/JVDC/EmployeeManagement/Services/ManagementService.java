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

@Service
public class ManagementService {
    @Autowired
    EmployeeRepository employeeRepository;

    public ManagementService() {
    }

    List<Employee> employees = new ArrayList<>();
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
}
