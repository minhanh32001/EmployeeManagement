package JVDC.EmployeeManagement.Repository;

import JVDC.EmployeeManagement.Model.Account;
import JVDC.EmployeeManagement.Model.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountRepository {

    @Select("select * FROM Account WHERE username = #{username}")
    public Account findByName(String username);

    @Insert("INSERT INTO Account (username, password, role) VALUES (#{username}, #{password}, #{role})")
    public boolean insert(Account account);
}
