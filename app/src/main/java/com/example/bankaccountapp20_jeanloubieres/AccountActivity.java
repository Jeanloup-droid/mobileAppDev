package com.example.bankaccountapp20_jeanloubieres;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountActivity extends AppCompatActivity {

    private Button mLogoutButton;
    ArrayList<Accounts> accounts = new ArrayList<>();
    private AccountsAdapter accountsAdapter;
    private RecyclerView accounts_recyclerview;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Affichage Id récupéré
        Intent intent = getIntent();
        if (intent != null) {
            // Le code pour récupérer les extras ira ici
            if (intent.hasExtra("edittext")) { // vérifie qu'une valeur est associée à la clé “edittext”
                userId = intent.getStringExtra("edittext").toString(); // on récupère la valeur associée à la clé
            }
            TextView textView = (TextView) findViewById(R.id.activity_account_welcomeUserId);
            textView.setText("Welcome back user " + userId + " !");
        }

        //Ancien
        Button mLogoutButton = (Button) findViewById(R.id.activity_second_logout_btn);
        Button mRefreshButton = (Button) findViewById(R.id.activity_second_refresh_btn);
        mLogoutButton.setEnabled(true);
        mRefreshButton.setEnabled(true);
        //Ancien

        accounts_recyclerview = (RecyclerView)findViewById(R.id.accounts_recyclerview);
        accounts_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        //test
        getAccountsResponse();

        /*
        if (isOnline()){
            Toast.makeText(AccountActivity.this,"Co", Toast.LENGTH_SHORT).show();
            getAccountsResponse();
        }
        else {
            Toast.makeText(AccountActivity.this,"No co", Toast.LENGTH_SHORT).show();
            getAccountsResponseWithoutConnexion();
        }
         */

        //Ancien
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The button is clicked
                Intent mainActivity = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The button is clicked
                /*
                Intent accountActivity = new Intent(AccountActivity.this, AccountActivity.class);
                startActivity(accountActivity);
                */
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        //Ancien
    }

    private void getAccountsResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://60102f166c21e10017050128.mockapi.io/labbbank/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<List<Accounts>> call = requestInterface.getAccountsJson();

        call.enqueue(new Callback<List<Accounts>>() {
            @Override
            public void onResponse(Call<List<Accounts>> call, Response<List<Accounts>> response) {
                accounts = new ArrayList<>(response.body());
                saveData();

                if(Integer.parseInt(userId) == 0 || accounts.size() - 1 < Integer.parseInt(userId)){
                    Intent mainActivity = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(mainActivity);
                    Toast.makeText(AccountActivity.this,"Id doesn't exist", Toast.LENGTH_SHORT).show();
                }
                else{
                    ArrayList<Accounts> accountsR = new ArrayList<>();
                    accountsR.add(accounts.get(Integer.parseInt(userId) - 1));
                    accountsAdapter = new AccountsAdapter(AccountActivity.this, accountsR);
                    accounts_recyclerview.setAdapter(accountsAdapter);
                    Toast.makeText(AccountActivity.this,"Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Accounts>> call, Throwable t) {
                loadData();

                if(Integer.parseInt(userId) == 0 || accounts.size() - 1 < Integer.parseInt(userId)){
                    Intent mainActivity = new Intent(AccountActivity.this, MainActivity.class);
                    startActivity(mainActivity);
                    Toast.makeText(AccountActivity.this,"Id doesn't exist", Toast.LENGTH_SHORT).show();
                }
                else{
                    ArrayList<Accounts> accountsR = new ArrayList<>();
                    accountsR.add(accounts.get(Integer.parseInt(userId) - 1));
                    accountsAdapter = new AccountsAdapter(AccountActivity.this, accountsR);
                    accounts_recyclerview.setAdapter(accountsAdapter);
                    Toast.makeText(AccountActivity.this,"No connexion", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    // method to load arraylist from shared prefs
    private void loadData() {
        // initializing our shared prefs with name as  shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("accounts", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<Accounts>>() {}.getType();

        // in below line we are getting data from gson and saving it to our array list
        accounts = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (accounts == null) {
            // if the array list is empty creating a new array list.
            accounts = new ArrayList<>();
        }
    }

    // method for saving the data in array list.
    private void saveData() {
        // creating a variable for storing data in shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for editor to store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(accounts);

        // below line is to save data in shared prefs in the form of string.
        editor.putString("accounts", json);

        // below line is to apply changes and save data in shared prefs.
        editor.apply();
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}