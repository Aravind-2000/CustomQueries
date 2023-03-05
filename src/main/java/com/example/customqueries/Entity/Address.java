package com.example.customqueries.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "employee_address_details")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String houseNumber;
    private String addressLine1;
    private String addressLine2;
    private String district;
    private String city;
    private String state;
    private String pinCode;
    private String country;

    private String employeeDasId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeDasId", referencedColumnName = "dasId", insertable = false, updatable = false)
    private Employee employee;


    @JsonIgnore
    private Integer isValid;
}
