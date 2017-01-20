package com.daggy.gifttracker.model;

/**
 * Created by drkdagron on 2017-01-19.
 */

public class CreateEventModel {
    public String Name;
    public String Pin;

    public CreateEventModel(String n, String p)
    {
        this.Name = n;
        this.Pin = p;
    }
}
