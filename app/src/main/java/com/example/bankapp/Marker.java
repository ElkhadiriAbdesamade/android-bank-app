package com.example.bankapp;

public class Marker {

    private double lati,longt;
    private String agence_name,phone_number;

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    public String getAgence_name() {
        return agence_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAgence_name(String agence_name) {
        this.agence_name = agence_name;
    }

    public Marker(double lati, double longt, String agence_name,String phone_number) {
        this.lati = lati;
        this.longt = longt;
        this.agence_name = agence_name;
        this.phone_number = phone_number;
    }

    public Marker(double lati, double longt) {
        this.lati = lati;
        this.longt = longt;
    }
}
