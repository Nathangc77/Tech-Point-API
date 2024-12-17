package com.moreira.techpoint.repositories;

import com.moreira.techpoint.entities.Employee;
import com.moreira.techpoint.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_employee.employeeCode AS username, tb_employee.password, tb_role.id AS roleId, tb_role.authority
            FROM tb_employee
            INNER JOIN tb_employee_role ON tb_employee.id = tb_employee_role.employee_id
            INNER JOIN tb_role ON tb_employee_role.role_id = tb_role.id
            WHERE tb_employee.employee_code = :employeeCode
            """)
    List<UserDetailsProjection> searchEmployeeAndRolesByEmployeeCode(String employeeCode);
}