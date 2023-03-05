package com.example.customqueries.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public LinkedHashMap<String , Set<String>> getDasNameMail(){
        String query = "SELECT das_id,employee_name,employee_email from employee_details";
        return jdbcTemplate.query(
                query, rs -> {
                    LinkedHashMap<String , Set<String>> data = new LinkedHashMap<>();
                    while(rs.next()){
                        Set<String> set = new LinkedHashSet<>();
                        set.add(rs.getString("employee_name"));
                        set.add(rs.getString("employee_email"));
                        data.put(rs.getString("das_id"),set);
                    }
                    return data;
                }
        );
    }


}
