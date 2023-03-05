package com.example.customqueries.Controller;

import com.example.customqueries.Entity.Employee;
import com.example.customqueries.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getallemployees")
    public ResponseEntity<?> getAll(){
        return employeeService.getAllEmployees();
    }
    
    @GetMapping("/getemployee/{das}")
    public ResponseEntity<?> get(@PathVariable String das){
        return employeeService.getEmployee(das);
    }

    @GetMapping("/three")
    public ResponseEntity<?> three(){
        return employeeService.dasNameEmail();
    }

    @PostMapping("/addemployee")
    public ResponseEntity<?> add(@RequestBody Employee e){
        return employeeService.addEmployees(e);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<?> update(@RequestBody Employee e){
        return employeeService.updateEmployee(e);
    }

    @PutMapping("deleteEmployee/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        return employeeService.deleteEmployee(id);
    }

}
