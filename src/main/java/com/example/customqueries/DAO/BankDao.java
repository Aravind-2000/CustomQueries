package com.example.customqueries.DAO;

import com.example.customqueries.Entity.Bank;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class BankDao {

//    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Bank> getAllBankRecords(){
        String query = "SELECT * FROM employee_bank_details WHERE is_valid = "+1+" LIMIT 1000";
        return jdbcTemplate.query(
                query, rs -> {
                    List<Bank> bankRecords = new ArrayList<>();
                    while (rs.next()){
                        Bank bank = new Bank();
                        bank.setId(rs.getInt("id"));
                        bank.setAccountHolderName(rs.getString("account_holder_name"));
                        bank.setBankBranch(rs.getString("bank_branch"));
                        bank.setBankName(rs.getString("bank_name"));
                        bank.setIsValid(rs.getInt("is_valid"));
                        bank.setAccountNumber(rs.getString("account_number"));
                        bank.setIfscCode(rs.getString("ifsc_code"));
                        bank.setDasId(rs.getString("das_id"));
                        bankRecords.add(bank);
                    }
                    return bankRecords;
                }
        );
    }


    public Bank getBankRecord(String das){
        String query = "SELECT * FROM employee_bank_details WHERE das_id = '"+das+"' LIMIT 1000";
        Bank bank = new Bank();
        return jdbcTemplate.query(
                query, rs -> {
                    while (rs.next()){
                        bank.setId(rs.getInt("id"));
                        bank.setAccountNumber(rs.getString("account_number"));
                        bank.setAccountHolderName(rs.getString("account_holder_name"));
                        bank.setIfscCode(rs.getString("ifsc_code"));
                        bank.setDasId(rs.getString("das_id"));
                        bank.setBankName(rs.getString("bank_name"));
                        bank.setBankBranch(rs.getString("bank_branch"));
                        bank.setIsValid(rs.getInt("is_valid"));
                    }
                    return bank;
                }
        );
    }

    public void addBankRecord(@NotNull Bank newBank){
        String query =  "INSERT into employee_bank_details (account_number, account_holder_name, ifsc_code, bank_branch, bank_name, is_valid) VALUES" +
                "( '"+newBank.getAccountNumber()+"' , '"+newBank.getAccountHolderName()+"' , '"+newBank.getIfscCode()+"' , '"+newBank.getBankBranch()+"' , '"+newBank.getBankName()+"' , " +1+ ")";
        jdbcTemplate.update(query);
    }

    public void updateBank(@NotNull Bank bank){
        String query = "UPDATE employee_bank_details SET account_number = '"+bank.getAccountNumber()+"' , account_holder_name = '"+bank.getAccountHolderName()+"' , "+
                "ifsc_code = '"+bank.getIfscCode()+"' , bank_branch = '"+bank.getBankBranch()+"' , bank_name = '"+bank.getBankName()+"' WHERE id ="+bank.getId();
        jdbcTemplate.update(query);
    }

    public void softDeleteBank(String id){
        String query = "UPDATE employee_bank_details SET is_valid = "+0 +" WHERE das_id = '"+id+"'";
        jdbcTemplate.update(query);
    }

}
