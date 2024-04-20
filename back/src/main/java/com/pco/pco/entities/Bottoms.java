package com.pco.pco.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bottoms")
public class Bottoms extends Product{
    private boolean chamois;
    private boolean thermal;
    private boolean aero;

    public boolean getChamois() {
        return chamois;
    }
    public void setChamois(Boolean chamois) {
        this.chamois = chamois;
    }
    public boolean getThermal() {
        return thermal;
    }
    public void setThermal(boolean thermal) { this.thermal=thermal; }
    public boolean getAero() {
        return aero;
    }
    public void setAero(boolean aero) {
        this.aero = aero;
    }
}
