package com.example.musicstore.repository.models;

import java.util.Calendar;
import java.util.Date;

public class CreditCard {
    private String number;
    private String holder;
    private String expirationDate;

    public CreditCard(String number, String holder, String expirationDate, int codeCSV) {
        this.number = number;
        this.holder = holder;
        this.expirationDate = expirationDate;
        this.codeCSV = codeCSV;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCodeCSV() {
        return codeCSV;
    }

    public void setCodeCSV(int codeCSV) {
        this.codeCSV = codeCSV;
    }

    private int codeCSV;


    public Boolean isValid(){
        Boolean b = true;

        if(this.number != null && this.number.isEmpty()){
            return false;
        }
        //validate the length of digits in number

        if(this.number.length()!=16) return false;

        if(this.holder != null && this.holder.isEmpty())
            return false;

        // validate expiration date
        if(this.expirationDate != null && this.expirationDate.isEmpty())
            return false;

        try{
            String[] sp = this.expirationDate.split("/");
            int month = Integer.parseInt(sp[0]);
            int year = Integer.parseInt(sp[1]);
            if(month <= 0 && month > 12) return false;

//            Date c = Calendar.getInstance().getTime();
            String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            int currentYearNumber = Integer.parseInt(currentYear.substring(2,4));

            if(year < currentYearNumber) return false;

        }catch (Exception e){
            return false;
        }

        //validate the length of digits in number
        int csvCount =0;
        int cCodeCSV = this.codeCSV;
        for (; cCodeCSV != 0; cCodeCSV /= 10, ++csvCount) {
        }
        if(csvCount < 3) return false;


        return b;
    }


}
