package com.example.propertymanagement.view.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.presenter_impl.SignupPresenterImpl;
import com.example.propertymanagement.view.view_interface.SignupView;

import java.security.Key;
import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, SignupView{

    TabLayout tabLayout;
    ArrayList<String> tabNameList = new ArrayList<>();

    EditText editTextLandlordEmail, editTextEmail, editTextPassword, editTextConfirmPassword;
    TextInputLayout textInputLayoutLandlordEmail, textInputLayoutEmail, textInputLayoutPassword, textInputLayoutConfirmPassword;
    Button btnSignup;
    TextView textViewLogin;

    private SignupPresenterImpl signupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();


    }

    private void init() {

        editTextLandlordEmail = findViewById(R.id.edit_text_landlord_email);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextConfirmPassword = findViewById(R.id.edit_text_confirm_password);

        textInputLayoutLandlordEmail = findViewById(R.id.input_layout_landlord_email);
        textInputLayoutEmail = findViewById(R.id.input_layout_email);
        textInputLayoutPassword = findViewById(R.id.input_layout_password);
        textInputLayoutConfirmPassword = findViewById(R.id.input_layout_confirm_password);

        btnSignup = findViewById(R.id.button_signup);
        textViewLogin = findViewById(R.id.text_view_login);

        tabSetup();

        btnSignup.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);

        editTextLandlordEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateLandlordEmail();
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateLandlordEmail();
            }
        });



        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateEmail();
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEmail();
            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePassword();

            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePassword();
            }
        });

        editTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateConfirmPassword();
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateConfirmPassword();
            }
        });

    }

    private boolean validateLandlordEmail(){
        String landlordEmail = editTextLandlordEmail.getText().toString().trim();
        if(editTextLandlordEmail.isFocusable()){
            if(landlordEmail.isEmpty()){
                textInputLayoutLandlordEmail.setError("landlord email cannot be empty");

                return false;
            }else if(!Patterns.EMAIL_ADDRESS.matcher(landlordEmail).matches()){
                textInputLayoutLandlordEmail.setError("Please input correct landlord email address");
                return false;
            }else{
                textInputLayoutLandlordEmail.setError(null);
                textInputLayoutLandlordEmail.setErrorEnabled(false);
                return true;
            }
        }else{
            return true;
        }

    }

    private boolean validateEmail(){
        String email = editTextEmail.getText().toString().trim();
        if(email.isEmpty()){
            textInputLayoutEmail.setError("email cannot be empty");

            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textInputLayoutEmail.setError("Please input correct email address");
            return false;
        }else{
            textInputLayoutEmail.setError(null);
            textInputLayoutEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){
        String password = editTextPassword.getText().toString().trim();
        if(password.isEmpty()){
            textInputLayoutPassword.setError("password cannot be empty");
            return false;
        }else if(password.length() <10){
            textInputLayoutPassword.setError("password length must be at lease 10 digits long");
            return false;
        }else if(!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{10,}$")){
            textInputLayoutPassword.setError("password should contain at least one upper case letter, one lower case letter and one number");
            return false;
        }else{
            textInputLayoutPassword.setError(null);
            textInputLayoutPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateConfirmPassword(){
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        if(!confirmPassword.equals(editTextPassword.getText().toString().trim())){
            textInputLayoutConfirmPassword.setError("the confirmed password doesn't equal to password");
            return false;
        }else{
            textInputLayoutConfirmPassword.setError(null);
            textInputLayoutConfirmPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void tabSetup() {
        tabLayout = findViewById(R.id.signup_type_tabs);

        tabNameList.add("Landlord");
        tabNameList.add("Property-Manager");
        tabNameList.add("Tenant");
        tabNameList.add("Vendor");

        for(int i=0; i<tabNameList.size(); i++){
            tabLayout.addTab(tabLayout.newTab().setText(tabNameList.get(i)));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                editTextEmail.setText("");
                editTextEmail.setError(null);

                editTextLandlordEmail.setText("");
                editTextLandlordEmail.setError(null);

                editTextPassword.setText("");
                editTextPassword.setError(null);

                editTextConfirmPassword.setText("");
                editTextConfirmPassword.setError(null);

                if(tab.getPosition() == 2){
                    editTextLandlordEmail.setBackground(ContextCompat.getDrawable(SignupActivity.this, R.drawable.edit_text_border));
                    editTextLandlordEmail.setClickable(true);
                    editTextLandlordEmail.setFocusable(true);
                    editTextLandlordEmail.setFocusableInTouchMode(true);
                }else{
                    editTextLandlordEmail.setBackground(ContextCompat.getDrawable(SignupActivity.this, R.drawable.edit_text_border_disable));
                    editTextLandlordEmail.setClickable(false);
                    editTextLandlordEmail.setFocusable(false);
                    editTextLandlordEmail.setFocusableInTouchMode(false);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_signup:
                if(validateEmail() && validatePassword() && validateEmail() && validateLandlordEmail()){

                    signupPresenter = new SignupPresenterImpl(this);
                    signupPresenter.getResponseFromApi(this, editTextEmail.getText().toString(), editTextLandlordEmail.getText().toString(), editTextConfirmPassword.getText().toString(), tabNameList.get(tabLayout.getSelectedTabPosition()).toLowerCase());


                }

                break;
            case R.id.text_view_login:
                startActivity(new Intent(this, LoginActivity.class));
        }

    }

    @Override
    public void signupSuccess(String message) {
        Toast.makeText(this, "Sign Up Success!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void signupFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        editTextLandlordEmail.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        editTextConfirmPassword.setText("");
    }

}
