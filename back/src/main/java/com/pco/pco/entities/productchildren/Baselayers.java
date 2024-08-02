package com.pco.pco.entities.productchildren;

import com.pco.pco.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "baselayer")
public class Baselayers extends Product {
    private String sleeves;
    private boolean thermal;
    private boolean merino;
    public Baselayers(){}
    public Baselayers(String SKU, String title, double price, boolean activeProduct, String colour, String size, int quantity, String prodCondition, String vendorCode, String sleeves, boolean thermal, boolean merino){
        super(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);
        this.sleeves = sleeves;
        this.thermal = thermal;
        this.merino = merino;
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
    public boolean getMerino() {
        return merino;
    }
    public void setMerino(boolean merino) {
        this.merino = merino;
    }
}
