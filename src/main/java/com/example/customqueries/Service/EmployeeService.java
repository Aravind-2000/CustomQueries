package com.example.customqueries.Service;

import com.example.customqueries.DAO.EmployeeDao;
import com.example.customqueries.Entity.Employee;
import com.example.customqueries.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDao employeeDao;

    public ResponseEntity<?> dasNameEmail() {
        try {
            return new ResponseEntity<>(employeeDao.getDasNameMail(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.getAllValidEmployees();
            if (employees.isEmpty()) {
                return new ResponseEntity<>("No Data found", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getEmployee(String das) {
        try {
            Employee employee = employeeRepository.getEmployee(das);
            if (employee != null) {
                return new ResponseEntity<>(employeeRepository.getEmployee(das), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Data found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> addEmployees(Employee employee) {
        try {
            if (employee != null) {
                employee.setIsValid(1);
                employeeRepository.save(employee);
                return new ResponseEntity<>("DATA ADDED SUCCESSFULLY", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("DATA NOT FOUND", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateEmployee(Employee employee) {
        try {
            if (employee.getId() == null || employee.getDasId() == null || employee.getEmployeeName() == null ||
                    employee.getEmployeeEmail() == null || employee.getEmployeePhoneNumber() == null
                    || employee.getBankId() == null) {
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
            }

            else {
                employeeRepository.updateEmployee(employee.getId(), employee.getDasId(), employee.getEmployeeName(),
                        employee.getEmployeeEmail(),
                        employee.getEmployeePhoneNumber(), employee.getBankId());
                return new ResponseEntity<>("DATA UPDATED SUCCESSFULLY", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteEmployee(String id) {
        try {
            if (id == null) {
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
            } else {
                employeeRepository.softDeleteEmployee(id);
                return new ResponseEntity<>("DATA DELETED SUCCESSFULLY", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
