package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandCode;
    private String brandName;
    private String nationality;

    @JsonIgnoreProperties("SKU")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private List<Product> productList;

    public Long getBrandCode() {
        return brandCode;
    }
    public void setBrandCode(Long brandCode) {
        this.brandCode = brandCode;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getNationality(){ return nationality; }
    public void setNationality(String nationality){ this.nationality = nationality; }

    public List<Product> getProductList() { return productList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }
}
