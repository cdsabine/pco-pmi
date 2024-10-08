package com.pco.pco.entities.productchildren;

import com.pco.pco.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "headwear")
public class Headwear extends Product {
    private boolean waterproof;
    private boolean visor;
    private boolean thermal;
    public Headwear(){}
    public Headwear(String SKU, String title, double price, boolean activeProduct, String colour, String size, int quantity, String prodCondition, String vendorCode, boolean waterproof, boolean visor, boolean thermal){
        super(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);
        this.waterproof = waterproof;
        this.visor = visor;
        this.thermal = thermal;
    }
    public boolean getThermal() {
        return thermal;
    }
    public void setThermal(boolean thermal) { this.thermal=thermal; }
    public boolean getWaterproof() {
        return waterproof;
    }
    public void setWaterproof(boolean waterproof) {
        this.waterproof = waterproof;
    }
    public boolean getVisor() {
        return visor;
    }
    public void setVisor(boolean visor) { this.visor = visor; }
}
