package com.example.tomas.vakantieapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tomas on 24-6-2017.
 */

public class VakantieItem implements Serializable {


    public String name;                     // Naam, bijv herfstvakantie
    public boolean compulsorydates;         // Verplicht of door school te bepalen
    public ArrayList<Tijdvak> tijdvak;     // Tijden voor de verschillende delen van NL

    VakantieItem() {this.tijdvak = new ArrayList<>(); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompulsorydates() {
        return compulsorydates;
    }

    public void setCompulsorydates(boolean compulsorydates) {
        this.compulsorydates = compulsorydates;
    }

    public ArrayList<Tijdvak> getTijdvak() {
        return tijdvak;
    }

    public void setTijdvak(ArrayList<Tijdvak> tijdvak) {
        this.tijdvak = tijdvak;
    }

    @Override
    public String toString() {
        return "VakantieItem{" +
                "name='" + name + '\'' +
                ", compulsorydates=" + compulsorydates +
                ", tijdvak=" + tijdvak +
                '}';
    }
}

