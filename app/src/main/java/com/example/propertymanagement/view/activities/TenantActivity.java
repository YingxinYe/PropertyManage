package com.example.propertymanagement.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



import com.example.propertymanagement.R;


public class TenantActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button buttonAdd,buttonContact,buttonApplicant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);
        setUpToolBar();
        init();
    }

    private void setUpToolBar() {
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tenants");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void init() {
        buttonAdd =findViewById(R.id.tenant_button_add);
        buttonContact =findViewById(R.id.tenant_button_contact);
        buttonApplicant = findViewById(R.id.tenant_button_applicants);

        buttonAdd.setOnClickListener(this);
        buttonContact.setOnClickListener(this);
        buttonApplicant.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tenant_button_add:
                startActivity(new Intent(TenantActivity.this, AddTenantActivity.class));
                break;
            case R.id.tenant_button_contact:
                startActivity(new Intent(TenantActivity.this, ContactTenantsAvtivity.class));
                break;
            case R.id.tenant_button_applicants:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tenant_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.item_search:
                break;
        }
        return true;
    }
}
