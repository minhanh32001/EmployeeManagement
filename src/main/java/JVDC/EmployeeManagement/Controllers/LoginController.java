package JVDC.EmployeeManagement.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/authen")
    public ModelAndView authenticate(@RequestParam String username, @RequestParam String password) {
        ModelAndView modelAndView = new ModelAndView();

        if (username.equals("user") && password.equals("123")) {
            modelAndView.setViewName("redirect:/Employee");
        } else {
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }
}