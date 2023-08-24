package JVDC.EmployeeManagement.Controllers;

import JVDC.EmployeeManagement.Model.Account;
import JVDC.EmployeeManagement.Model.Employee;
import JVDC.EmployeeManagement.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "")
public class AccountController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("/register")
    public String newEmployeeForm(){
        return "register";
    }
    @PostMapping("/register")
    public String newEmployee(@RequestParam String username, @RequestParam String password,Model model, RedirectAttributes redirectAttributes){
        Account checkExist = accountRepository.findByName(username);
        if (checkExist== null && !username.isEmpty() && !password.isEmpty()){
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(passwordEncoder.encode(password));
            account.setRole("user");
            accountRepository.insert(account);
            redirectAttributes.addFlashAttribute("message", "アカウント設定が成功しました、ロギングしてください。");
            return "redirect:login";
        }
        else {
            model.addAttribute("message", "エラーがあります！");
            return "register";
        }

    }

}
