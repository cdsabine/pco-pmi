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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"boxes","products"})
    @JoinTable(name = "boxproductmapping", joinColumns = @JoinColumn(name = "boxNumber"), inverseJoinColumns = @JoinColumn(name = "SKU"))
    private List<Product> products;

    public Box(){}
    public Box(String boxNumber, int quantityActive, int quantityDraft, int quantityTotal, boolean allActive) {
        this.boxNumber = boxNumber;
        this.quantityActive = quantityActive;
        this.quantityDraft = quantityDraft;
        this.quantityTotal = quantityTotal;
        this.allActive = allActive;
    }

    public String getBoxNumber() { return boxNumber; }
    public void setBoxNumber(String boxNumber) { this.boxNumber = boxNumber; }

    public int getQuantityActive() { return quantityActive; }
    public void setQuantityActive(int quantityActive) { this.quantityActive = quantityActive; }

    public int getQuantityDraft() { return quantityDraft; }
    public void setQuantityDraft(int quantityDraft) { this.quantityDraft = quantityDraft; }

    public int getQuantityTotal() { return quantityTotal; }
    public void setQuantityTotal(int quantityTotal) { this.quantityTotal = quantityTotal; }

    public boolean isAllActive() { return allActive; }
    public void setAllActive(boolean allActive) { this.allActive = allActive; }
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products ) { this.products = products; }

}
