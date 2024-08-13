package com.pco.pco.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client extends AppUser{
    private int repeatedTransactions;
    private double totalValueAchieved;
    @JsonIgnoreProperties("coCode")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<ClientOrder> clientOrderList;

    public Client(){}
    public Client(String appUsername, String emailAddress, String address, String country, int repeatedTransactions, double totalValueAchieved) {
        super(appUsername, emailAddress, address, country);
        this.repeatedTransactions = repeatedTransactions;
        this.totalValueAchieved = totalValueAchieved;
        this.clientOrderList = new ArrayList<>();
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

    public List<ClientOrder> getClientOrderList() { return clientOrderList; }
    public void addToClientOrderList(ClientOrder clientorder) {
        this.clientOrderList.add(clientorder);
    }

}
