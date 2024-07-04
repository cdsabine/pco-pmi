package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientorder")
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coCode;

    private String dateOfOrder;
    private boolean shipped;

    @JsonIgnoreProperties("SKU")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientOrder")
    private List<Product> productList;

    @JsonIgnoreProperties("userCode")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientOrder")
    private List<Client> clientOrderList;
    public Long getClientOrderCode() {
        return coCode;
    }
    public void setClientOrderCode(Long coCode) {
        this.coCode = coCode;
    }
    public String getDateOfOrder() {
        return dateOfOrder;
    }
    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }
    public boolean getShipped(){ return shipped; }
    public void setShipped(boolean shipped){ this.shipped = shipped; }

    public List<Product> getProductList() { return productList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }

}
