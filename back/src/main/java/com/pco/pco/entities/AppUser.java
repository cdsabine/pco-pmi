package com.pco.pco.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "appuser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCode;
    private String appUsername;
    private String emailAddress;
    private String address;
    @JsonIgnoreProperties("appUserList")
    @ManyToOne()
    @JoinColumn(name = "countryCode")
    private Country country;

    public AppUser(){}
    public AppUser(Long userCode, String appUsername, String emailAddress, String address, Country country) {
        this.userCode = userCode;
        this.appUsername = appUsername;
        this.emailAddress = emailAddress;
        this.address = address;
        this.country = country;
    }

    public Long getUserCode() {
        return userCode;
    }

    public void setCode(Long userCode) {
        this.userCode = userCode;
    }

    public String getAppUsername() {
        return appUsername;
    }

    public void setName(String name) {
        this.appUsername = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Country getCountry(){
        return country;
    }

    public void setCountry(Country country){
        this.country = country;
    }
}
