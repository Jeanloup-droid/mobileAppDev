package com.example.bankaccountapp20_jeanloubieres;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("/accounts")
    Call<List<Accounts>> getAccountsJson();
}
