package JVDC.EmployeeManagement.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/authen")
    public String authen(@RequestParam String username, @RequestParam String password, Model model) {
        if (username.equals("user") && password.equals("123")) {
            return "redirect:/Employee";
        } else {
            model.addAttribute("err", "エラーがある");
            return "login";
        }
    }
}