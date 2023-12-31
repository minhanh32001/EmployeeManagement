package JVDC.EmployeeManagement.Repository;

import JVDC.EmployeeManagement.Model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeRepository {
    @Select("Select * from employees;")
    public List<Employee> findAll();

    @Select(" Select * from employees WHERE employee_name like CONCAT('%', #{employee_name}, '%') OR email like CONCAT('%', #{employee_name}, '%')")
    public List<Employee> find(String employee_name);

    @Select("SELECT * FROM employees WHERE id = #{id}")
    public Employee findById(int id);
    @Select("SELECT * FROM employees WHERE email = #{email}")
    public Employee findByEmail(String email);

    @Delete("DELETE FROM employees WHERE id = #{id}")
    public int deleteById(int id);

    @Insert("INSERT INTO employees ( employee_name, email, phone_number) VALUES (#{employee_name}, #{email}, #{phone_number})")
    public boolean insert(Employee employee);

    @Update("Update employees set employee_name=#{employee_name}, " +
            " email=#{email}, phone_number=#{phone_number} where id=#{id}")
    public int update(Employee employee);
}
