package com.example.customqueries.Service;

import com.example.customqueries.DAO.BankDao;
import com.example.customqueries.Entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BankService {

    @Autowired
    private BankDao bankDao;

    public ResponseEntity<?> getAllBankRecords(){
        List<Bank> banks = bankDao.getAllBankRecords();
        try{
            if(banks.isEmpty()){
                return new ResponseEntity<>("NO DATA FOUND", HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(banks, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>("SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> addBankRecord(Bank bank){
        try{
            if(bank != null){
                bankDao.addBankRecord(bank);
                return new ResponseEntity<>("NEW RECORD ADDED", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("NO DATA FOUND", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){

            return new ResponseEntity<>("SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateBankRecord(Bank bank){
        try{
            if(bank.getId() == null || bank.getBankBranch() == null || bank.getBankName() == null || bank.getIfscCode() == null || bank.getAccountNumber()
            == null || bank.getAccountHolderName() == null){
                return new ResponseEntity<>("SOME DATA MISSING", HttpStatus.BAD_REQUEST);
            }
            else{
                bankDao.updateBank(bank);
                return new ResponseEntity<>("RECORD UPDATED SUCCESSFULLY" , HttpStatus.OK);
            }
        }
        catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> softDeleteBank(String das){
        Bank bank = bankDao.getBankRecord(das);
        try{
            if(bank != null){
                bankDao.softDeleteBank(das);
                return new ResponseEntity<>("RECORD DELETED SUCCUSSFULLY", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("NO DATA FOUND", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            System.out.print(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
