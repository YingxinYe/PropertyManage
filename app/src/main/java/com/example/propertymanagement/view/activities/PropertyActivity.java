package com.example.propertymanagement.view.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.helpers.SessionManager;
import com.example.propertymanagement.model.helpers.ToolbarManager;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.model.pojo.PropertyResponse;
import com.example.propertymanagement.model.presenter_impl.GetPropertyListPresenterImpl;
import com.example.propertymanagement.view.fragments.PropertyAddFragment;
import com.example.propertymanagement.view.fragments.PropertyEmptyFragment;
import com.example.propertymanagement.view.fragments.PropertyListFragment;
import com.example.propertymanagement.view.view_interface.GetPropertyListView;

import java.util.ArrayList;


public class PropertyActivity extends AppCompatActivity implements GetPropertyListView {

    FragmentManager fragmentManager;
    int listSize;
    GetPropertyListPresenterImpl myImpl;
    ToolbarManager toolbarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        fragmentManager = getSupportFragmentManager();
        myImpl=new GetPropertyListPresenterImpl(this);
        myImpl.getPropertyListFromApi(this);
        
        toolbarManager = new ToolbarManager(this);
        toolbarManager.setUpToolBar();
        toolbarManager.setUpToolBarTitle();
    }

    @Override
    public void onSuccessView(String message, ArrayList<Property> mlist,PropertyResponse myresponse) {
        listSize = mlist.size();
        if (listSize == 0) {
            fragmentManager.beginTransaction().add(R.id.property_fragment_container, new PropertyEmptyFragment()).commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("PROPERTY", myresponse);
            PropertyListFragment propertyListFragment = new PropertyListFragment();
            propertyListFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.property_fragment_container, propertyListFragment).commit();
        }
    }

    @Override
    public void onFailView(String message) {
        Log.i("winnie","Get List fail");
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return toolbarManager.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_logout:
                SessionManager.clearLogin();
                break;

        }return true;
    }
}
