package com.pco.pco.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tops")
public class Tops extends Product{
    private String sleeves;
    private boolean thermal;
    private boolean aero;

    public String getSleeves() {
        return sleeves;
    }
    public void setSleeves(String sleeves) {
        this.sleeves = sleeves;
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
