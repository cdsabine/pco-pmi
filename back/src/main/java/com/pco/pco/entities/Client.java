package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client extends AppUser{
    private int repeatedTransactions;
    private double totalValueAchieved;

    @JsonIgnoreProperties("clientOrderList")
    @ManyToOne()
    @JoinColumn(name = "coCode")
    private ClientOrder clientOrder;

    public Client(){}
    public Client(String appUsername, String emailAddress, String address, String country, int repeatedTransactions, double totalValueAchieved) {
        super(appUsername, emailAddress, address, country);
        this.repeatedTransactions = repeatedTransactions;
        this.totalValueAchieved = totalValueAchieved;
    }

    public int getRepeatedTransactions() {
        return repeatedTransactions;
    }

    public void setRepeatedTransactions(int repeatedTransactions) {
        this.repeatedTransactions = repeatedTransactions;
    }
    public double getTotalValueAchieved() {
        return totalValueAchieved;
    }

    public void setTotalValueAchieved(double totalValueAchieved) {
        this.totalValueAchieved = totalValueAchieved;
    }

    public ClientOrder getClientOrder() { return clientOrder; }
    public void setClientorder(ClientOrder clientorder) {
        this.clientOrder = clientorder;
    }

}
