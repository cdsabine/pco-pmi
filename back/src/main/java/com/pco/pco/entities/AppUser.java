package com.pco.pco.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "appuser")
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCode;
    private String appUsername;
    private String emailAddress;
    private String appAddress;
    @Transient
    private String userType;

    @Transient
    private String countryName;
    @JsonIgnoreProperties("appUserList")
    @ManyToOne()
    @JoinColumn(name = "countryCode")
    private Country country;

    public AppUser(){}
    public AppUser(String appUsername, String emailAddress, String address, String country) {
        //this.userCode = userCode;
        this.appUsername = appUsername;
        this.emailAddress = emailAddress;
        this.appAddress = address;
        this.countryName = country;
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
        return appAddress;
    }

    public void setAddress(String address) {
        this.appAddress = address;
    }

    public Country getCountry(){
        return country;
    }

    public void setCountry(Country country){
        this.country = country;
    }

    public String getCountryName(){ return countryName; }
    public void setCountryName(String countryName){ this.countryName = countryName; }
    public String getUserType() {return userType; }
    public void setUserType(String userType) {this.userType = userType; }
}
