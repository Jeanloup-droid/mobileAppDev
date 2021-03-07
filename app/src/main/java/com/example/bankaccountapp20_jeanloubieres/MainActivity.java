package com.example.bankaccountapp20_jeanloubieres;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        EditText mIdInput = (EditText) findViewById(R.id.activity_main_id_input);
        mLoginButton = (Button) findViewById(R.id.activity_main_login_btn);

        mLoginButton.setEnabled(false);

        mIdInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Checking the user input
                mLoginButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The button is clicked
                String userId = mIdInput.getText().toString();
                Intent accountActivity = new Intent(MainActivity.this, AccountActivity.class);
                accountActivity.putExtra("edittext", userId);
                startActivity(accountActivity);
            }
        });
    }
}