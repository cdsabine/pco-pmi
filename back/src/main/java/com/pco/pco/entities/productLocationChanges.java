package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "productlocationchanges")
public class productLocationChanges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String boxMovementCode;
    private String oldBoxNumber;
    private String dateOfMovement;

    @JsonIgnoreProperties("productLocationChanges")
    @ManyToOne()
    @JoinColumn(name = "boxNumber")
    private Box box;

    @JsonIgnoreProperties("productLocationChanges")
    @ManyToOne()
    @JoinColumn(name = "SKU")
    private Product product;

    public productLocationChanges(){}
    public productLocationChanges(String oldBoxNumber, String dateOfMovement){
        this.oldBoxNumber = oldBoxNumber;
        this.dateOfMovement = dateOfMovement;
    }

    public String getBoxMovementCode() { return boxMovementCode; }
    public void setBoxMovementCode(String boxMovementCode) { this.boxMovementCode = boxMovementCode; }

    public String getOldBoxNumber() { return oldBoxNumber; }
    public void setOldBoxNumber(String oldBoxNumber) { this.oldBoxNumber = oldBoxNumber; }

    public String getDateOfMovement() { return dateOfMovement; }
    public void setDateOfMovement(String dateOfMovement) { this.dateOfMovement = dateOfMovement; }

    public Box getBox() { return box; }
    public void setBox(Box box) { this.box = box; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

}
