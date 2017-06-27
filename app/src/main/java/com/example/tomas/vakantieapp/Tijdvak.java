package com.example.tomas.vakantieapp;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tomas on 24-6-2017.
 */


public class Tijdvak implements Serializable {


    public String region;
    public Date startdate;
    public Date enddate;


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }



}