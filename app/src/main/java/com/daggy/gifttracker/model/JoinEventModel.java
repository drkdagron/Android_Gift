package com.daggy.gifttracker.model;

/**
 * Created by drkdagron on 2017-01-19.
 */

public class JoinEventModel {
    public String Code;
    public String Pin;

    public JoinEventModel(String i, String p)
    {
        this.Code = i;
        this.Pin = p;
    }
}
