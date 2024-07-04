package com.pco.pco.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee extends AppUser{
    public Employee(){}
    public Employee(String appUsername, String emailAddress, String address, String country) {
        super(appUsername, emailAddress, address, country);
    }
}
