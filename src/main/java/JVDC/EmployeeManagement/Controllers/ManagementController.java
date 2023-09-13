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
@Controller
@RequestMapping(path = "/Employee")
public class ManagementController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ManagementService managementService;

    @GetMapping("")
    public String showEmployeeListPage(Model model,
                                       @RequestParam(name = "employee_name", defaultValue = "") String employee_name,
                                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                                       @RequestParam(name="size", defaultValue = "5") Integer size) {
        Page<Employee> employeePage = managementService.displayEmployees(employee_name, PageRequest.of(page - 1, size));
        List<Integer> pageNumbers = managementService.getPageNumbers(employeePage);
        if(employeePage.isEmpty()){
            model.addAttribute("message", "データなし");// if there is no data, return a error message
        }
        model.addAttribute("employees", employeePage); // parse Page to html to display all the Emloyee
        model.addAttribute("pageNumbers", pageNumbers); // parse list page number
        return "employee";
    }

    @GetMapping("/{id}") // show employee detail
    public String showEmployee(@PathVariable int id, Model model) {
        Employee employee = managementService.getEmployee(id); // call to function that performs SQL to get employee information
        model.addAttribute("employee", employee);
        return "employee_detail";
    }

    @GetMapping("/create") // get employee create form
    public String newEmployeeForm() {
        return "employee_add";
    }
    @PostMapping("/create_confirm") //confirm the creation
    public String modifyConfirm(@RequestParam String employee_name,
                                @RequestParam String email,
                                @RequestParam String phone_number,
                                Model model) {
        Employee employee = new Employee(employee_name, email, phone_number);
        // a new obj Employee created here to store new employee information, to parse view again, lets user confirm the information
        model.addAttribute("employee", employee);
        return "create_confirm";
    }
    @PostMapping("/create")
    public String createEmployee(@RequestParam String employee_name,
                                 @RequestParam String email,
                                 @RequestParam String phone_number,
                                 Model model) {
        managementService.add(employee_name, email, phone_number); // call to add function, which will store this employee to database
        model.addAttribute("message", "登録が完了しました。");
        return "complete";
    }

    @PostMapping("/modify/{id}")
    public String modifyEmployee(@PathVariable int id,
                                 @RequestParam String employee_name,
                                 @RequestParam String email,
                                 @RequestParam String phone_number,
                                 Model model) {
        managementService.modify(id, employee_name, email, phone_number);
        String message = "更新が完了しました。";
        model.addAttribute("message", message);
        return "complete";
    }

    @PostMapping("/modify_confirm/{id}")
    public String modifyConfirm(@RequestParam String employee_name,
                          @RequestParam String email,
                          @RequestParam String phone_number,
                          @PathVariable int id, Model model){
        Employee employee = managementService.modifyConfirm(id, employee_name, email, phone_number);
        // a new obj Employee created here to store employee's information, to parse view again, lets user confirm the information
        model.addAttribute("employee", employee);
        return "modify_confirm";
}
    @GetMapping("/delete_confirm/{id}")
    public String deleteConfirm( @PathVariable int id, Model model){
        Employee employee = employeeRepository.findById(id);
        //employee was made to stored information of the employee was chosen to delete, to parse view again, lets user confirm the information
        model.addAttribute("employee",employee);
        return "delete_confirm";

    }
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id, Model model){
        employeeRepository.deleteById(id); // call to delete function, which performs SQL to delete by id
        model.addAttribute("message", "削除が完了しました。");
        return "complete";
    }

}
