package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientorder")
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coCode;
    private LocalDate dateOfOrder;
    private boolean shipped;
    private double totalOrderValue;
    @JsonIgnoreProperties("products")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "clientOrder")
    private List<Product> productList;
    @Transient
    private List<String> productSKUList;
    @JsonIgnoreProperties("clientOrderList")
    @ManyToOne()
    @JoinColumn(name = "userCode")
    private Client client;
    @Transient
    private int clientNumber;

    public ClientOrder(){}
    public ClientOrder(int clientNumber, List<String> productSKUList, LocalDate currentDate){
        this.clientNumber = clientNumber;
        this.productSKUList = productSKUList;
        this.dateOfOrder = currentDate;

        this.totalOrderValue = 0;
        this.productList = new ArrayList<>();
    }


    public Long getClientOrderCode() {
        return coCode;
    }
    public void setClientOrderCode(Long coCode) {
        this.coCode = coCode;
    }
    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }
    public void setDateOfOrder(LocalDate dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }
    public boolean getShipped(){ return shipped; }
    public void setShipped(boolean shipped){ this.shipped = shipped; }
    public double getTotalOrderValue(){ return totalOrderValue; }
    public void setTotalOrderValue(double totalOrderValue){ this.totalOrderValue = totalOrderValue; }
    public List<Product> getProductList() { return productList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }
    public void addToProductList(Product product) { this.productList.add(product); }
    public Client getClient(){ return client; }
    public void setClient(Client client){ this.client = client; }
    public List<String> getProductSKUList() { return productSKUList; }
    public void setProductSKUList(List<String> productSKUList) { this.productSKUList = productSKUList; }
    public int getClientNumber(){ return this.clientNumber; }
    public void setClientNumber(int clientNumber){ this.clientNumber = clientNumber;}

}
