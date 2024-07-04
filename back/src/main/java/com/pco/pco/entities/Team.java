package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamCode;

    private String teamName;
    private String nationality;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"teams","vendors"})
    @JoinTable(name = "teamvendormapping", joinColumns = @JoinColumn(name = "teamCode"), inverseJoinColumns = @JoinColumn(name = "userCode"))
    private List<Vendor> vendors;

    @JsonIgnoreProperties("SKU")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<Product> productList;

    public Long getTeamCode() {
        return teamCode;
    }
    public void setTeamCode(Long teamCode) {
        this.teamCode = teamCode;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getNationality(){ return nationality; }
    public void setNationality(String nationality){ this.nationality = nationality; }

    public List<Product> getProductList() { return productList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }

    public List<Vendor> getVendors() { return vendors; }
    public void setVendors(Vendor vendors) { this.vendors.add(vendors); }
}
