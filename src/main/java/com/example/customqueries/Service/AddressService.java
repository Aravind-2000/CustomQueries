package com.example.customqueries.Service;

import com.example.customqueries.Entity.Address;
import com.example.customqueries.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public ResponseEntity<?> getAllAddress(){
        try{
            List<Address> addresses = addressRepository.validAddress();
            if(addresses.isEmpty()){
                return new ResponseEntity<>("DATA NOT FOUND", HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(addresses, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>("SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAddressesByDas(String das){

        try{
            List<Address> addresses = addressRepository.getAddressByEmployeeDasId(das);
            if(addresses.isEmpty()){
                return new ResponseEntity<>("DATA NOT FOUND", HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(addresses, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>("SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<?> addAddress(Address address){
        try{
            if(address != null){
                address.setIsValid(1);
                addressRepository.save(address);
                return new ResponseEntity<>("DATA ADDED SUCCESSFULLY", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("DATA NOT FOUND",HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateAddress(Address address){
        try{
            if(address.getId() == null || address.getHouseNumber() == null || address.getAddressLine1() == null  ||
                    address.getAddressLine2() == null || address.getDistrict() == null || address.getCity() == null ||
                    address.getState() == null || address.getPinCode() == null || address.getCountry() == null || address.getEmployeeDasId() == null)
            {
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
            }

            else{
                addressRepository.updateAddress(address.getHouseNumber(), address.getAddressLine1(), address.getAddressLine2(), address.getDistrict(),
                        address.getCity(), address.getState(),address.getPinCode(), address.getCountry(),address.getEmployeeDasId(), address.getId());
                return new ResponseEntity<>("DATA UPDATED SUCCESSFULLY", HttpStatus.OK);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>("SERVER ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> softDeleteAddress(String das, Integer id){
        try{
            Address address = addressRepository.getAddressByEmployeeDasIdAndId(das, id);
            if(address != null){
                addressRepository.softDeleteAddress(das, id);
                return new ResponseEntity<>("RECORD DELETED SUCCESSFULLY", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("DATA NOT FOUND", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>("SERVER ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
