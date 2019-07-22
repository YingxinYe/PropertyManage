package com.example.propertymanagement.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.Tenant;
import com.example.propertymanagement.model.pojo.TenantList;
import com.example.propertymanagement.view.adpaters.TenantDetailAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactTenantsAvtivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TenantDetailAdapter adapter;
    private ArrayList<Tenant> arrayList= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_tenants_avtivity);

        setUpToolBar();
        getData("32");



    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.contact_tenant_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new TenantDetailAdapter(this,arrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setUpToolBar() {
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tenant Details");
    }

    public void getData(String landlordId){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getTenantDetail(landlordId);
        Call<TenantList> call =  apiInterface.getTenantDetail(landlordId);
        call.enqueue(new Callback<TenantList>() {
            @Override
            public void onResponse(Call<TenantList> call, Response<TenantList> response) {
                final ArrayList<Tenant> list = response.body().getArrayList();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        arrayList = list;
                        setUpRecyclerView();

                    }
                });
            }

            @Override
            public void onFailure(Call<TenantList> call, Throwable t) {
               Log.d("hi","throwable is:"+t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tenant_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.item_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
