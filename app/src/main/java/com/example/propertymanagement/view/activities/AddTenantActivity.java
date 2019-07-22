package com.example.propertymanagement.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.propertymanagement.R;

import com.example.propertymanagement.model.presenter_impl.AddTenantPresenterImp;
import com.example.propertymanagement.presenter.presenter_interface.AddTenantPresenter;
import com.example.propertymanagement.view.view_interface.AddTenantView;

public class AddTenantActivity extends AppCompatActivity implements View.OnClickListener, AddTenantView {
    private Toolbar toolbar;
    private EditText editTextName, editTextEmail, editTextAddress, editTextMobile, editTextPropertyId,
            editTextLandlordId;
    private Button buttonAdd;

    private AddTenantPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("hi","you entered the create part");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant);

        presenter = new AddTenantPresenterImp(this);

        setUpToolBar();
        init();
    }

    private void init() {

        Log.d("hi","you entered the init part");

        editTextName = findViewById(R.id.edit_name);
        editTextEmail = findViewById(R.id.edit_email);
        editTextAddress = findViewById(R.id.edit_address);
        editTextMobile = findViewById(R.id.edit_mobile);
        editTextLandlordId = findViewById(R.id.edit_landlordid);
        editTextPropertyId = findViewById(R.id.edit_propertyid);

        buttonAdd = findViewById(R.id.button_add_tenant);
        buttonAdd.setOnClickListener(this);


    }

    private void setUpToolBar() {
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Tenants");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Log.d("hi","you entered the onclick part");

        presenter.addTenant(editTextName.getText().toString(),
              editTextEmail.getText().toString(),
              editTextAddress.getText().toString(),
              editTextMobile.getText().toString(),
              editTextPropertyId.getText().toString(),
              editTextLandlordId.getText().toString());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }

//    private void addTenant(String name,String email,String address,String mobile,String propertyId,String landlordId){
//
//        Log.d("hi","you entered the addtenant part");
//
//
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseBody> call = apiInterface.addTenant(name,email,address,mobile,propertyId,landlordId);
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.d("hi","you entere the response part");
//
//                try {
//                    Log.d("hi","response body is:"+response.body());
//                    Log.d("hi","response is:"+response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                Log.d("hi","t is:"+t.getMessage());
//
//            }
//        });
//    }
}
