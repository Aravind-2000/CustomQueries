package com.example.customqueries.Controller;


import com.example.customqueries.Entity.Address;
import com.example.customqueries.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return addressService.getAllAddress();
    }

    @GetMapping("/getbydas/{das}")
    public ResponseEntity<?> getAllByDas(@PathVariable String das){
        return addressService.getAddressesByDas(das);
    }

    @PostMapping("/addaddress")
    public ResponseEntity<?> save(@RequestBody Address address){
        return addressService.addAddress(address);
    }

    @PutMapping("/updateaddress")
    public ResponseEntity<?> update(@RequestBody Address address){
        return addressService.updateAddress(address);
    }

    @PutMapping("/softdelete/{das}/{id}")
    public ResponseEntity<?> softDelete(@PathVariable String das, @PathVariable int id){
        return addressService.softDeleteAddress(das, id);
    }

}
