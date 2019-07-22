package com.example.propertymanagement.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.presenter_impl.ForgotPasswordPresenterImpl;
import com.example.propertymanagement.view.view_interface.ForgotPasswordView;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener, ForgotPasswordView {

    EditText editTextEmail;
    TextView textViewPassword;
    Button btnFindPassword, btnReturnToLogin;

    private ForgotPasswordPresenterImpl forgotPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        init();
    }

    private void init() {
        editTextEmail = findViewById(R.id.edit_text_email);
        textViewPassword = findViewById(R.id.text_view_password);

        btnFindPassword = findViewById(R.id.button_findpassword);
        btnFindPassword.setOnClickListener(this);
        btnReturnToLogin = findViewById(R.id.button_returntologin);
        btnReturnToLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_findpassword:
                forgotPasswordPresenter = new ForgotPasswordPresenterImpl(this);
                forgotPasswordPresenter.getResponseFromApi(this, editTextEmail.getText().toString());
                break;
            case R.id.button_returntologin:
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                if(!textViewPassword.getText().toString().isEmpty()){
                    intent.putExtra("Email", editTextEmail.getText().toString());
                    intent.putExtra("Password", textViewPassword.getText().toString());
                }

                startActivity(intent);
                break;
        }

    }

    @Override
    public void getPasswordSuccess(String msg, String email, String password) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        editTextEmail.setText(email);
        textViewPassword.setText(password);
    }

    @Override
    public void getPasswordFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        editTextEmail.setText("");
        textViewPassword.setText("");
    }
}
