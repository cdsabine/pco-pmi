package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamCode;

    private String teamName;
    private String nationality;

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
}
