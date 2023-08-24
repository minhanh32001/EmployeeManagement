package JVDC.EmployeeManagement.Controllers;

import JVDC.EmployeeManagement.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import JVDC.EmployeeManagement.Model.Account;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/authen")
    public String authen(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        Account account = accountRepository.findByName(username);
        if (account != null && passwordEncoder.matches(password, account.getPassword()))
            return "redirect:Employee";
        else {
            redirectAttributes.addFlashAttribute("message", "エラーがある");
            return "redirect:login";
        }

    }
}