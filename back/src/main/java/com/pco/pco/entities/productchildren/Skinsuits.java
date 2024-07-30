package com.pco.pco.entities.productchildren;

import com.pco.pco.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "skinsuit")
public class Skinsuits extends Product {
    private String sleeves;
    private boolean thermal;
    private boolean aero;
    private boolean timetrial;
    public Skinsuits(){}
    public Skinsuits(String SKU, String title, double price, boolean activeProduct, String colour, String size, int quantity, String prodCondition, String vendorCode, String sleeves, boolean thermal, boolean aero, boolean timetrial){
        super(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);
        this.sleeves = sleeves;
        this.thermal = thermal;
        this.aero = aero;
        this.timetrial = timetrial;
    }
}
