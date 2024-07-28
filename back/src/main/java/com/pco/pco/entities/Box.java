package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "box")
public class Box {
    @Id
    private String boxNumber;
    private int quantityActive;
    private int quantityDraft;
    private int quantityTotal;
    private boolean allActive;

    @JsonIgnoreProperties({"products","box"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "box")
    private List<Product> productList;

    @JsonIgnoreProperties({"box","products","productLocationChanges"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "box")
    private List<productLocationChanges> productLocationChanges;

    /*
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"boxes","products"})
    @JoinTable(name = "boxproductmapping", joinColumns = @JoinColumn(name = "boxNumber"), inverseJoinColumns = @JoinColumn(name = "SKU"))
    private List<Product> products;
     */
    public Box(){}
    public Box(String boxNumber, int quantityActive, int quantityDraft, int quantityTotal, boolean allActive) {
        this.boxNumber = boxNumber;
        this.quantityActive = quantityActive;
        this.quantityDraft = quantityDraft;
        this.quantityTotal = quantityTotal;
        this.allActive = allActive;
        this.productList = new ArrayList<>();
        this.productLocationChanges = new ArrayList<>();
    }

    public String getBoxNumber() { return boxNumber; }
    public void setBoxNumber(String boxNumber) { this.boxNumber = boxNumber; }

    public int getQuantityActive() { return quantityActive; }
    public void setQuantityActive(int quantityActive) { this.quantityActive = quantityActive; }
    public void addToQuantityActive() { this.quantityActive++; }
    public void removeFromQuantityActive() { this.quantityActive--; }

    public int getQuantityDraft() { return quantityDraft; }
    public void setQuantityDraft(int quantityDraft) { this.quantityDraft = quantityDraft; }
    public void addToQuantityDraft() { this.quantityDraft++; }
    public void removeFromQuantityDraft() { this.quantityDraft--; }

    public int getQuantityTotal() { return quantityTotal; }
    public void setQuantityTotal(int quantityTotal) { this.quantityTotal = quantityTotal; }
    public void addToQuantityTotal() { this.quantityTotal++; }
    public void removeFromQuantityTotal() { this.quantityTotal--; }

    public boolean isAllActive() { return allActive; }
    public void setAllActive(boolean allActive) { this.allActive = allActive; }
    public List<Product> getProducts() { return productList; }
    public void setProducts(Product products ) { this.productList.add(products); }
    public List<productLocationChanges> getProductLocationChanges() { return productLocationChanges; }
    public void setProductLocationChanges(productLocationChanges productLocationChanges ) { this.productLocationChanges.add(productLocationChanges); }

}
