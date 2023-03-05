package com.example.customqueries.Repository;

import com.example.customqueries.Entity.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Query(value = "SELECT * FROM employee_address_details WHERE is_valid = 1", nativeQuery = true)
    List<Address> validAddress();

    @Query(value = "SELECT * FROM employee_address_details WHERE employee_das_id = :das AND is_valid = 1", nativeQuery = true)
    List<Address> getAddressByEmployeeDasId(String das);

    @Query(value = "SELECT * FROM employee_address_details WHERE employee_das_id = :das AND id = :id", nativeQuery = true)
    Address getAddressByEmployeeDasIdAndId(String das, int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employee_address_details SET house_number = :no , address_line1 = :line1, address_line2 = :line2, " +
            " district = :dis, city = :city, state = :state, pin_code = :pin, country = :country, employee_das_id = :das " +
            "WHERE id = :id", nativeQuery = true)
    void updateAddress(String no, String line1, String line2, String dis, String city, String state, String pin, String country, String das, int id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE employee_address_details SET is_valid = 0 WHERE employee_das_id = :das AND id = :id", nativeQuery = true)
    void softDeleteAddress(String das, int id);

}
