package JVDC.EmployeeManagement.Model;

public class Employee {
    private int serial;
    private int id;
    private String employee_name;
    private String email;
    private String phone_number;

    public Employee() {
    }

    public Employee(String employee_name, String email, String phone_number) {
        this.employee_name = employee_name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}