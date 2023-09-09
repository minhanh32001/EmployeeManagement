package JVDC.EmployeeManagement.Controllers;

import JVDC.EmployeeManagement.Repository.AccountRepository;
import JVDC.EmployeeManagement.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import JVDC.EmployeeManagement.Model.Account;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;

@Controller
public class AccountController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LoginService loginService;
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/authen")
    public String authen(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) throws FileNotFoundException {
        if (loginService.loginCheck(username, password)) {
            return "redirect:Employee";
        }
        else {
            redirectAttributes.addFlashAttribute("message", "ユーザー名またはパスワードが違います。");
            return "redirect:login";
        }
    }
    @GetMapping("/register")
    public String newEmployeeForm(){
        return "register";
    }
    @PostMapping("/register")
    public String newEmployee(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes redirectAttributes){
        Account checkExist = accountRepository.findByName(username);
        if (checkExist== null){
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(passwordEncoder.encode(password));
            account.setRole("user");
            accountRepository.insert(account);
            redirectAttributes.addFlashAttribute("message", "アカウント設定が成功しました、ロギングしてください。");
            return "redirect:login";
        }
        else {
            model.addAttribute("message", "アカウントが存在します！");
            return "register";
        }

    }

}