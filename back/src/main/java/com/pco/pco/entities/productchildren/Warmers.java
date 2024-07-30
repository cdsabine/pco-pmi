package com.pco.pco.entities.productchildren;

import com.pco.pco.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "warmer")
public class Warmers extends Product {
    private boolean waterproof;
    private String bodypart;
    private boolean thermal;
    public Warmers(){}
    public Warmers(String SKU, String title, double price, boolean activeProduct, String colour, String size, int quantity, String prodCondition, String vendorCode, boolean waterproof, String bodypart, boolean thermal){
        super(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);
        this.waterproof = waterproof;
        this.thermal = thermal;
        this.bodypart = bodypart;
    }
}
