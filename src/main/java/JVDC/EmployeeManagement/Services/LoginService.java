package JVDC.EmployeeManagement.Services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class LoginService {
    /**
     * Read a file from project folder, and compare it to login inout value.
     * @param username
     * @param password
     * @return if username and password match the username and password stored in the file, return true.
     * @throws FileNotFoundException
     */
    public Boolean loginCheck(String username, String password) throws FileNotFoundException {
        File loginInfo = new File("src/main/resources/static/login_info.txt");
        Scanner readLoginInfo = new Scanner(loginInfo);
        String[] data = {readLoginInfo.nextLine(), readLoginInfo.nextLine()}; // an array to store information in the file
        readLoginInfo.close(); // close scanner
        return (username.matches(data[0]) && password.matches(data[1]));
    }
}