package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "productlocationchanges")
public class productLocationChanges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boxMovementCode;
    private String oldBoxNumber;
    private LocalDate dateOfMovement;

    @JsonIgnoreProperties({"box","products","productLocationChanges"})
    @ManyToOne()
    @JoinColumn(name = "boxNumber")
    private Box box;

    @JsonIgnoreProperties({"box","products","productLocationChanges"})
    @ManyToOne()
    @JoinColumn(name = "SKU")
    private Product product;

    public productLocationChanges(){}
    public productLocationChanges(String oldBoxNumber, LocalDate dateOfMovement){
        this.oldBoxNumber = oldBoxNumber;
        this.dateOfMovement = dateOfMovement;
    }

    public long getBoxMovementCode() { return boxMovementCode; }
    public void setBoxMovementCode(long boxMovementCode) { this.boxMovementCode = boxMovementCode; }

    public String getOldBoxNumber() { return oldBoxNumber; }
    public void setOldBoxNumber(String oldBoxNumber) { this.oldBoxNumber = oldBoxNumber; }

    public LocalDate getDateOfMovement() { return dateOfMovement; }
    public void setDateOfMovement(LocalDate dateOfMovement) { this.dateOfMovement = dateOfMovement; }

    public Box getBox() { return box; }
    public void setBox(Box box) { this.box = box; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

}
