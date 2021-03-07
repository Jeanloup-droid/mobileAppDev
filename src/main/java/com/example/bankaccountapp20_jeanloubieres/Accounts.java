package com.example.bankaccountapp20_jeanloubieres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Accounts {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("iban")
    @Expose
    private String iban;
    @SerializedName("currency")
    @Expose
    private String currency;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }
    public String getAmount(){
        return amount;
    }

    public void setAmount(String amount){
        this.amount = amount;
    }
    public String getIban(){
        return iban;
    }

    public void setIban(String iban){
        this.iban = iban;
    }
    public String getCurrency(){
        return currency;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }
}
