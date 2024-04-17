package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryCode;

    private String countryName;

    @JsonIgnoreProperties("appuser")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<AppUser> appUserList;

    public Country(){}
    public Country(String countryName){
        this.countryName = countryName;
    }

    public Long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName(){ return countryName; }
    public void setCountryName(String countryName){ this.countryName = countryName; }

    public List<AppUser> getAppUserList() { return appUserList; }
    public void setappUserList(List<AppUser> appUserList) { this.appUserList = appUserList; }

}
