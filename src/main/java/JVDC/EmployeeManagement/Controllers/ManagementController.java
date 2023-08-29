package JVDC.EmployeeManagement.Controllers;


import JVDC.EmployeeManagement.Model.Employee;
import JVDC.EmployeeManagement.Repository.EmployeeRepository;
import JVDC.EmployeeManagement.Services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping(path = "/Employee")
public class ManagementController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ManagementService managementService;

    @GetMapping("")
    public String showEmployeeListPage(Model model,
                                       @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Employee> employeePage = managementService.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("employees",employeePage );

        int totalPages = employeePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "employee";
    }

    @GetMapping("/search")
    public String searchEmployee(@RequestParam String employee_name, Model model){
        List<Employee> employees = employeeRepository.search(employee_name);
        model.addAttribute("employees", employees);
        return "employee_search";
    }
    @GetMapping("/{id}") // get employee by id
    public String showEmployee(@PathVariable int id, Model model){
        Employee employee = employeeRepository.findById(id);
        model.addAttribute("employee", employee);
        return "employee_detail";
    };

    // should use role to protect these APIs
    @GetMapping("/create")
    public String newEmployeeForm(){
        return "employee_add";
    }
    @PostMapping("/create")
    public String newEmployee(@RequestParam String employee_name, @RequestParam String email, @RequestParam String phone_number){
        if (!employee_name.isEmpty()){
            Employee employee = new Employee(employee_name, email, phone_number);
            employeeRepository.insert(employee);
        }
        return "redirect:/Employee";
    }

    @PostMapping("/modify/{id}")
    public String modifyEmployee(@PathVariable int id, @RequestParam String employee_name, @RequestParam String email, @RequestParam String phone_number){
        Employee employee = employeeRepository.findById(id);
        employee.setEmployee_name(employee_name);
        employee.setEmail(email);
        employee.setPhone_number(phone_number);
        employeeRepository.update(employee);
        return "redirect:/Employee";
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
