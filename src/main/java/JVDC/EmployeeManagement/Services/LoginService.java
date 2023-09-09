package JVDC.EmployeeManagement.Services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class LoginService {
    public Boolean loginCheck(String username, String password) throws FileNotFoundException {
        File loginInfo = new File("src/main/resources/static/login_info.txt");
        Scanner readLoginInfo = new Scanner(loginInfo);
        String[] data = {readLoginInfo.nextLine(), readLoginInfo.nextLine()};
        readLoginInfo.close();
        return (username.matches(data[0]) && password.matches(data[1]));
    }
}