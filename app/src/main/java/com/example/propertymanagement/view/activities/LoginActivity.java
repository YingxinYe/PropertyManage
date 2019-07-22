package com.example.propertymanagement.view.activities;

import android.content.Intent;
import android.se.omapi.Session;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.helpers.SessionManager;
import com.example.propertymanagement.model.pojo.User;
import com.example.propertymanagement.model.presenter_impl.LoginPresenterImpl;
import com.example.propertymanagement.view.view_interface.LoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    EditText editTextEmail, editTextPassword;
    Button btnSignin, btnSigninwithGoogle;
    TextView textViewForgotPassword;

    SessionManager sessionManager;
    private LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        sessionManager = new SessionManager();

        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        textViewForgotPassword = findViewById(R.id.forgot_password);

        Intent intent = getIntent();
        editTextEmail.setText(intent.getStringExtra("Email"));
        editTextPassword.setText(intent.getStringExtra("Password"));

        btnSignin = findViewById(R.id.button_signin);
        btnSigninwithGoogle = findViewById(R.id.button_signinwithgoogle);

        btnSignin.setOnClickListener(this);
        btnSigninwithGoogle.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_signin:
                loginPresenter = new LoginPresenterImpl(this);
                loginPresenter.getResponseFromApi(this, editTextEmail.getText().toString(), editTextPassword.getText().toString());
                break;
            case R.id.button_signinwithgoogle:

                break;
            case R.id.forgot_password:
                Intent i=new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
        }

    }

    @Override
    public void loginSuccess(String message, User user) {
        Log.i("winnie","Login Success"+user.getUserid()+","+user.getUsertype());
        sessionManager.saveUser(user);
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginFail(String message) {
        Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
        editTextEmail.setText("");
        editTextPassword.setText("");
    }
}
