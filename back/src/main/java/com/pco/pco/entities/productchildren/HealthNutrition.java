package com.pco.pco.entities.productchildren;

import com.pco.pco.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "healthnutrition")
public class HealthNutrition extends Product {
    private LocalDate expirationDate;
    private String flavour;
    public HealthNutrition(){}
    public HealthNutrition(String SKU, String title, double price, boolean activeProduct, String colour, String size, int quantity, String prodCondition, String vendorCode, LocalDate expirationDate, String flavour){
        super(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);
        this.expirationDate = expirationDate;
        this.flavour = flavour;
    }
    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate=expirationDate; }
    public String getFlavour() {
        return flavour;
    }
    public void setFlavour(String flavour) { this.flavour = flavour; }
}
