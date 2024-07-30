package com.pco.pco.entities.productchildren;

import com.pco.pco.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "components")
public class Components extends Product {
    public Components(){}
    public Components(String SKU, String title, double price, boolean activeProduct, String colour, String size, int quantity, String prodCondition, String vendorCode){
        super(SKU,title,price,activeProduct,colour,size, quantity, prodCondition, vendorCode);
    }
}
