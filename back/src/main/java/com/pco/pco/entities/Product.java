package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    @Id
    private String SKU;
    private String title;
    private double price;
    private boolean activeProduct;
    private String colour;
    private String size;
    private String prodCondition;
    private String vendorCode;

    @JsonIgnoreProperties("productList")
    @ManyToOne()
    @JoinColumn(name = "brandCode")
    private Brand brand;
    @Transient
    private String brandName;
    @JsonIgnoreProperties("productList")
    @ManyToOne()
    @JoinColumn(name = "teamCode")
    private Team team;
    @Transient
    private String teamName;
    @JsonIgnoreProperties("productList")
    @ManyToOne()
    @JoinColumn(name = "coCode")
    private ClientOrder clientOrder;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private List<Box> boxes;

    public Product(){}
    public Product(String SKU, String title, double price, boolean activeProduct, String colour, String size, String prodCondition, String vendorCode){
        this.SKU = SKU;
        this.title = title;
        this.price = price;
        this.activeProduct = activeProduct;
        this.colour = colour;
        this.size = size;
        this.prodCondition = prodCondition;
        this.vendorCode = vendorCode;
    }

    public String getSKU() {
        return SKU;
    }
    public void setSKU(String SKU) {
        this.SKU = SKU;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String Title) {
        this.title = title;
    }
    public String getProdCondition() {
        return prodCondition;
    }
    public void setProdCondition(String prodCondition) {
        this.prodCondition = prodCondition;
    }
    public String getVendorCode() {
        return vendorCode;
    }
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean getActiveProduct() {
        return activeProduct;
    }
    public void setActiveProduct(boolean activeProduct) {
        this.activeProduct = activeProduct;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public ClientOrder getClientOrder() {
        return clientOrder;
    }
    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public List<Box> getBoxes() { return boxes; }
    public void setBox(Box box ) { this.boxes.add(box); }

}
