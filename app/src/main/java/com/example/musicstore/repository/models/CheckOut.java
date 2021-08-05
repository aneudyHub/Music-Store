package com.example.musicstore.repository.models;

import com.example.musicstore.repository.database.Database;

import java.util.Date;

public class CheckOut {
    private int id;
    private Date date;
    private Music music;
    private Double amount;
    private CreditCard creditCard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Boolean processCheckOut(){
        Boolean b = true;

        if(!this.creditCard.isValid())
            return false;

        if(this.amount <= 0){
            return false;
        }

        if(this.music == null){
            return false;
        }

        //save to temporary database to show your music
        Database.myMusic.add(this.music);

        return b;
    }
}
