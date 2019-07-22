package com.example.propertymanagement.view.activities;

import android.content.Intent;
import android.se.omapi.Session;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.helpers.SessionManager;

public class LoginChoiceActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin, btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);

        init();
    }

    private void init() {

        btnLogin = findViewById(R.id.button_login);
        btnSignup = findViewById(R.id.button_signup);

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.button_signup:
                startActivity(new Intent(this, SignupActivity.class));
                break;
        }
    }
}
