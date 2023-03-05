package com.example.customqueries.Controller;


import com.example.customqueries.Entity.Bank;
import com.example.customqueries.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;


    @GetMapping("/getallbanks")
    public ResponseEntity<?> getAll(){
        return bankService.getAllBankRecords();
    }

    @PostMapping("/addbank")
    public ResponseEntity<?> save(@RequestBody Bank bank){
        return bankService.addBankRecord(bank);
    }

    @PutMapping("/updatebank")
    public ResponseEntity<?> update(@RequestBody Bank bank){
        return bankService.updateBankRecord(bank);
    }

    @PutMapping("/softdeletebank/{das}")
    public ResponseEntity<?> softDelete(@PathVariable String das){
        return bankService.softDeleteBank(das);
    }


}
