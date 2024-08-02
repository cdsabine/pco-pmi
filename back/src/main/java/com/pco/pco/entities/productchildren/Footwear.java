package com.pco.pco.entities.productchildren;

import com.pco.pco.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "footwear")
public class Footwear extends Product {
    private String discipline;
    public Footwear(){}
    public Footwear(String SKU, String title, double price, boolean activeProduct, String colour, String size, int quantity, String prodCondition, String vendorCode, String discipline){
        super(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);
        this.discipline = discipline;
    }
    public String getDiscipline() {
        return discipline;
    }
    public void setDiscipline(String discipline) { this.discipline = discipline; }
}
