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
    LoginService loginService;
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/authen")
    public String authen(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) throws FileNotFoundException {
        if (loginService.loginCheck(username, password)) { // use loginCheck to check login information, if username and password match, login successful.
            return "redirect:Employee";// and redirect to home page
        }
        else { //if not match, login false, display a fail message on login page.
            redirectAttributes.addFlashAttribute("message", "ユーザー名またはパスワードが違います。");
            return "redirect:login";
        }
    }
}