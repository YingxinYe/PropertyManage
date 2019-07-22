package com.example.propertymanagement.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.helpers.ToolbarManager;
import com.example.propertymanagement.model.pojo.Icon;
import com.example.propertymanagement.view.adpaters.IconListAdapter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IconListAdapter.setOnClickListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    IconListAdapter adapter;
    ArrayList<Icon> mlist = new ArrayList<>();

    ToolbarManager toolbarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initArray();
        recyclerView = findViewById(R.id.icon_recycler_view);
        layoutManager = new GridLayoutManager(MainActivity.this, 3);
        adapter = new IconListAdapter(this, mlist);
        adapter.initOnClickListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        toolbarManager = new ToolbarManager(this);
        toolbarManager.setUpToolBar();
    }

    @Override
    public void onClickListener(View v, int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, PropertyActivity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, TenantActivity.class));
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
        }
    }

    private void initArray() {
        Icon i_alert = new Icon(R.drawable.ic_notifications_black_24dp, "Alert");
        mlist.add(i_alert);
        Icon i_property = new Icon(R.drawable.ic_home_black_24dp, "Properties");
        mlist.add(i_property);
        Icon i_tenants = new Icon(R.drawable.ic_person_black_24dp, "Tenants");
        mlist.add(i_tenants);
        Icon i_transaction = new Icon(R.drawable.ic_credit_card_black_24dp, "Transactions");
        mlist.add(i_transaction);
        Icon i_collect_rent = new Icon(R.drawable.ic_money_bill_black_24dp, "Collect Rent");
        mlist.add(i_collect_rent);
        Icon i_to_do_list = new Icon(R.drawable.ic_to_do_list_check_black_24dp, "To-Do List");
        mlist.add(i_to_do_list);
        Icon i_Trips = new Icon(R.drawable.ic_directions_car_black_24dp, "Trips");
        mlist.add(i_Trips);
        Icon i_Documents = new Icon(R.drawable.ic_document_black_24dp, "Documents");
        mlist.add(i_Documents);
        Icon i_Vendors = new Icon(R.drawable.ic_vendors_black_24dp, "Vendors");
        mlist.add(i_Vendors);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return toolbarManager.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toolbarManager.onOptionsItemSelected(item);
    }
}

