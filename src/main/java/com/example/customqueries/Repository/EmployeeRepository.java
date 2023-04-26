package com.example.customqueries.Repository;

import com.example.customqueries.Entity.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query(value = "select * from employee_details where is_valid = 1", nativeQuery = true)
    List<Employee> getAllValidEmployees();

    @Query(value = "SELECT * FROM employee_details WHERE das_id = :das", nativeQuery = true)
    Employee getEmployee(String das);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employee_details SET das_id = :dasId, employee_name = :name, employee_email = :email, " +
            "employee_phone_number = :phoneNumber , bank_id = :bankId WHERE id = :id", nativeQuery = true)
    void updateEmployee(int id, String dasId, String name, String email, String phoneNumber, int bankId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE employee_details SET is_valid = 0 where das_id = :id", nativeQuery = true)
    void softDeleteEmployee(String id);
}
