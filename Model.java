package com.example.bet;

public class Model
{
    String title,amt;

    public Model(){}

    public Model(String title, String amt) {
        this.title = title;
        this.amt = amt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
