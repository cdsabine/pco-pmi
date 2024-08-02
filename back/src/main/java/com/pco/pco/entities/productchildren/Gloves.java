package com.pco.pco.entities.productchildren;

import com.pco.pco.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "gloves")
public class Gloves extends Product {
    private boolean waterproof;
    private boolean thermal;
    private boolean neoprene;
    public Gloves(){}
    public Gloves(String SKU, String title, double price, boolean activeProduct, String colour, String size, int quantity, String prodCondition, String vendorCode, boolean waterproof, boolean thermal, boolean neoprene){
        super(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);
        this.waterproof = waterproof;
        this.thermal = thermal;
        this.neoprene = neoprene;
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
    public boolean getNeoprene() { return neoprene; }
    public void setNeoprene(boolean neoprene) { this.neoprene = neoprene; }
}
