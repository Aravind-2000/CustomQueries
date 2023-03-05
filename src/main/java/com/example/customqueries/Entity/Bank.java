package com.example.customqueries.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;


@Entity(name = "employee_bank_details")
@Getter
@Setter
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String accountHolderName;
    @Column(unique = true)
    private String accountNumber;
    private String ifscCode;
    private String bankBranch;

    private String bankName;

    private String dasId;

    @JsonIgnore
    private Integer isValid;
}
