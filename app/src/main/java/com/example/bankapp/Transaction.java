package com.example.bankapp;

import java.util.Date;

public class Transaction{
    Integer icon;
    String label;
    String price;
    String date;
    String numCompte;
    String numRef;
    double solde;


    public Transaction() {

    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public String getNumRef() {
        return numRef;
    }

    public void setNumRef(String numRef) {
        this.numRef = numRef;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Transaction(Integer icon, String label, String price, String date,String numCompte, String numRef, double solde) {
        this.icon = icon;
        this.label = label;
        this.price = price;
        this.date = date;
        this.numCompte = numCompte;
        this.numRef = numRef;
        this.solde = solde;
    }


}
