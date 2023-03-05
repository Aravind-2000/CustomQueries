package com.example.customqueries.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity(name = "employee_details")
@Getter
@Setter
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;


    @Column(unique = true)
    private String dasId;

    private String employeeName;

    @Column(unique = true)
    private String employeeEmail;

    private String employeePhoneNumber;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> employeeAddresses = new ArrayList<>();

    @JsonIgnore
    private Integer bankId;
    @JoinColumn(name = "bankId", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Bank employeeBankDetails;

    @JsonIgnore
    private Integer isValid;

}
