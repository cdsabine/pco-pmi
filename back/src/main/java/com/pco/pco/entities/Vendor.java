package com.pco.pco.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendor")
public class Vendor extends AppUser{
    private String vendorCode;
    private int shipments;
    private double totalPayedTo;
    private double totalValueAchieved;

    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinTable(name = "teamvendormapping", joinColumns = @JoinColumn(name = "userCode"), inverseJoinColumns = @JoinColumn(name = "teamCode"))
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "vendors")
    private List<Team> teams;

    public Vendor(){}
    public Vendor(String appUsername, String emailAddress, String address, String country, String vendorCode) {
        super(appUsername, emailAddress, address, country);
        this.teams = new ArrayList<>();
        this.vendorCode = vendorCode;
        this.shipments = 0;
        this.totalPayedTo = 0;
        this.totalValueAchieved = 0;
    }
    public String getVendorCode() { return vendorCode; }
    public void setVendorCode(String vendorCode) { this.vendorCode = vendorCode; }

    public int getShipments() { return shipments; }
    public void setShipments(int shipments) { this.shipments = shipments; }

    public void addOneShipment(){
        this.shipments = shipments++;
    }

    public double getTotalPayedTo() {
        return totalPayedTo;
    }

    public void setTotalPayedTo(double totalPayedTo) {
        this.totalPayedTo = totalPayedTo;
    }

    public void addQunatityToTotalPayed(double quantPayed){
        this.totalPayedTo = totalPayedTo+quantPayed;
    }
    public double getTotalValueAchieved() {
        return totalValueAchieved;
    }

    public void setTotalValueAchieved(double totalValueAchieved) {
        this.totalValueAchieved = totalValueAchieved;
    }

    public void addQunatityToTotalValue(double quantValue){
        this.totalValueAchieved = totalValueAchieved+quantValue;
    }

    public List<Team> getTeams() { return teams; }
    public void setTeams(Team teams ) { this.teams.add(teams); }
}
