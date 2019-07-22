package com.example.propertymanagement.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.propertymanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PropertyEmptyFragment extends Fragment {

    Button add_property_btn;

    public PropertyEmptyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_property_empty, container, false);
        add_property_btn=view.findViewById(R.id.empty_property_button);
        add_property_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.property_fragment_container,new PropertyAddFragment()).commit();
            }
        });
        return view;
    }

}
