package com.pco.pco.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tops")
public class Tops extends Product{
    private String sleeves;
    private boolean thermal;
    private boolean aero;
    public Tops(){}
    public Tops(String SKU, String title, double price, boolean activeProduct, String colour, String size, int quantity, String prodCondition, String vendorCode, String sleeves, boolean thermal, boolean aero){
        super(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);
        this.sleeves = sleeves;
        this.thermal = thermal;
        this.aero = aero;
    }

    public String getSleeves() {
        return sleeves;
    }
    public void setSleeves(String sleeves) {
        this.sleeves = sleeves;
    }
    public boolean getThermal() {
        return thermal;
    }
    public void setThermal(boolean thermal) { this.thermal=thermal; }
    public boolean getAero() {
        return aero;
    }
    public void setAero(boolean aero) {
        this.aero = aero;
    }
}
