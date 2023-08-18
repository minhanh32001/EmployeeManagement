package JVDC.EmployeeManagement.Controllers;


import JVDC.EmployeeManagement.Model.Employee;
import JVDC.EmployeeManagement.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/Employee")
public class ManagementController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("")
    public String showEmployeeList(Model model) {
        List<Employee> employees = employeeRepository.findAll();

        model.addAttribute("employees", employees);

        return "employee";
    }
    @GetMapping("/{id}") // get employee by id
    public String showEmployee(@PathVariable int id, Model model){

        Employee employee = employeeRepository.findById(id);
        model.addAttribute("employee", employee);
        return "employee_detail";
    };

    // should use role to protect these APIs
    @GetMapping("/create") // add new employee, should place a form here
    public String newEmployeeForm(){
        return "employee_add";
    }
    @PostMapping("/create") // add new employee, should place a form here
    public String newEmployee(@RequestParam String employee_name, @RequestParam String email, @RequestParam String phone_number){
        Employee employee = new Employee(5, employee_name, email, phone_number);
        employeeRepository.insert(employee);
        return "redirect:/Employee";
    }

    @PostMapping("/modify/{id}") // need another API to get modify form, modify @Update to meet conditions
    public ModelAndView modifyEmployee(@PathVariable int id, @RequestParam String employee_name, @RequestParam String email, @RequestParam String phone_number){
        Employee employee = employeeRepository.findById(id);
        employee.setEmployee_name(employee_name);
        employee.setEmail(email);
        employee.setPhone_number(phone_number);
        employeeRepository.update(employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/Employee");
        return modelAndView;
    }

    @GetMapping("/modify/{id}")
    public String modifyEmployeeForm(@PathVariable int id, Model model){
        Employee employee = employeeRepository.findById(id);
        model.addAttribute("employee", employee);
        return "employee_modify";

    }
//    @DeleteMapping("/delete/{id}") // return a message or something here
//    public int deleteEmployee(@PathVariable int id){
//        return employeeRepository.deleteById(id);
//        return "redirect:/Employee";
//    }
    @GetMapping("/delete/{id}") // return a message or something here
    public String deleteEmployee(@PathVariable int id){
        employeeRepository.deleteById(id);
        return "redirect:/Employee";
    }
}
