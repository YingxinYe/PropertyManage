package com.example.propertymanagement.view.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.MsgResponse;
import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.model.pojo.PropertyResponse;
import com.example.propertymanagement.model.presenter_impl.RemovePropertyPresenterImpl;
import com.example.propertymanagement.view.adpaters.PropertyListAdapter;
import com.example.propertymanagement.view.view_interface.GetPropertyListView;
import com.example.propertymanagement.view.view_interface.RemovePropertyListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PropertyListFragment extends Fragment implements PropertyListAdapter.setOnClickListener, View.OnClickListener, RemovePropertyListView {

    PropertyResponse myresponse;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PropertyListAdapter adapter;
    ArrayList<Property> mlist = new ArrayList<>();
    Button add_btn;
    RemovePropertyPresenterImpl myImpl;

    public PropertyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property_list, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        add_btn = view.findViewById(R.id.property_list_add_property_button);
        add_btn.setOnClickListener(this);
        Bundle bundle = getArguments();
        myresponse = (PropertyResponse) bundle.getSerializable("PROPERTY");
        mlist = myresponse.getMlist();
        recyclerView = view.findViewById(R.id.property_list_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new PropertyListAdapter(getContext(), mlist);
        adapter.initOnClickListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        myImpl = new RemovePropertyPresenterImpl(this);
    }

    @Override
    public void onClickListener(View v, final int position) {  //connect adapter, remove property
        final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage("Do you want to delete this property record?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                myImpl.removePropertyPresent(getContext(), mlist, position);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.property_list_add_property_button:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.property_fragment_container, new PropertyAddFragment()).commit();
                break;
        }
    }

    @Override
    public void onSuccessRemovePropertyListView(String message, int position) {
        mlist.remove(position);
        adapter.setData(mlist,position);
        if (mlist.size() == 0) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.property_fragment_container, new PropertyEmptyFragment()).commit();
        }
    }

    @Override
    public void onFailRemovePropertyListView(String message) {
        Log.i("winnie", "Get List fail");
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
