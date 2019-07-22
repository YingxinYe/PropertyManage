package com.example.propertymanagement.view.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.helpers.SessionManager;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.MsgResponse;
import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.model.pojo.PropertyResponse;
import com.example.propertymanagement.model.pojo.User;
import com.example.propertymanagement.model.presenter_impl.AddPropertyPresenterImpl;
import com.example.propertymanagement.model.presenter_impl.GetPropertyListPresenterImpl;
import com.example.propertymanagement.model.presenter_impl.PropertyCheckPresenterImpl;
import com.example.propertymanagement.view.view_interface.AddPropertyView;
import com.example.propertymanagement.view.view_interface.GetPropertyListView;
import com.example.propertymanagement.view.view_interface.PropertyValidationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PropertyAddFragment extends Fragment implements View.OnClickListener, AddPropertyView, GetPropertyListView {

    EditText address, city, state, zip, country, price, mortgage;
    TextInputLayout address_box, city_box, state_box, zip_box, country_box, price_box;
    Button add_btn, view_list_btn;
    RadioButton yes_mortgage, no_mortgage;
    Spinner status_spinner;
    ArrayAdapter<String> spinnerAdapter;
    ArrayList<String> spinnerData;
    int listSize;

    String propertyaddress, propertycity, propertystate, propertyzip, propertycountry, propertystatus, propertypurchaseprice, propertymortageinfo;
    String latitude = "12.4565656";
    String longitude = "3.5656565";

    AddPropertyPresenterImpl presenter;
    GetPropertyListPresenterImpl listImpl;

    public PropertyAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property_add, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        address = view.findViewById(R.id.property_add_address);
        city = view.findViewById(R.id.property_add_city);
        state = view.findViewById(R.id.property_add_state);
        zip = view.findViewById(R.id.property_add_zip);
        country = view.findViewById(R.id.property_add_country);
        price = view.findViewById(R.id.property_add_purchase_price);

        address_box = view.findViewById(R.id.property_add_address_box);
        city_box = view.findViewById(R.id.property_add_city_box);
        state_box = view.findViewById(R.id.property_add_state_box);
        zip_box = view.findViewById(R.id.property_add_zip_box);
        country_box = view.findViewById(R.id.property_add_country_box);
        price_box = view.findViewById(R.id.property_add_purchase_price_box);

        status_spinner = view.findViewById(R.id.property_status_spinner);
        spinnerData = new ArrayList<>();
        spinnerData.add("Rent");
        spinnerData.add("Mortgage");
        spinnerData.add("Sell");
        spinnerAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, spinnerData);
        status_spinner.setAdapter(spinnerAdapter);


        yes_mortgage = view.findViewById(R.id.yes_mortgage_button);
        no_mortgage = view.findViewById(R.id.no_mortgage_button);
        mortgage = view.findViewById(R.id.property_add_mortgage_info);

        add_btn = view.findViewById(R.id.property_add_button);
        add_btn.setOnClickListener(this);

        view_list_btn = view.findViewById(R.id.property_view_list);
        view_list_btn.setOnClickListener(this);

        presenter=new AddPropertyPresenterImpl(this);
        listImpl=new GetPropertyListPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.property_view_list:  //get list
                listImpl.getPropertyListFromApi(getContext());
                break;
            case R.id.property_add_button:
                propertystatus = status_spinner.getSelectedItem().toString();

                boolean EditTextValid=false;
                boolean MortgageValid=false;
                if(AllEditTextValid()){
                    EditTextValid=true;
                }
                if(checkMortgageinfo(v)){
                    MortgageValid=true;
                }
                if ( EditTextValid && MortgageValid ) {
                    User myUser=SessionManager.getUser();
                    presenter.AddPropertyConnect(getContext(),propertyaddress, propertycity, propertystate, propertycountry, propertystatus,
                            propertypurchaseprice, propertymortageinfo, myUser.getUserid(),myUser.getUsertype(), latitude, longitude);
                }
                break;
        }
    }

    @Override
    public void onSuccessView(String message, ArrayList<Property> mlist, PropertyResponse myresponse) {

        listSize = mlist.size();
        if (listSize == 0) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.property_fragment_container, new PropertyEmptyFragment()).commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("PROPERTY", myresponse);
            PropertyListFragment propertyListFragment = new PropertyListFragment();
            propertyListFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.property_fragment_container, propertyListFragment).commit();
        }
    }

    @Override
    public void onFailView(String message) {
        Log.i("winnie","Get List fail");
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessAddView(String message) {
        Log.i("winnie", "add property success");
        showDialog(message);
    }

    @Override
    public void onFailAddView(String message) {
        Log.i("winnie", "add property fail " + message);
        Toast.makeText(getContext(),"message",Toast.LENGTH_SHORT).show();

    }

    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);

        builder.setPositiveButton("Go to List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.property_fragment_container,new PropertyListFragment()).commit();
                listImpl.getPropertyListFromApi(getContext());
            }
        });
        builder.setNegativeButton("Continue Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                address.setText("");
                city.setText("");
                state.setText("");
                zip.setText("");
                country.setText("");
                price.setText("");
                mortgage.setText("");
                mortgage.setEnabled(true);
            }
        });
        builder.show();
    }

    private boolean AllEditTextValid() {

        boolean addressValid = true;
        boolean cityValid = true;
        boolean stateValid = true;
        boolean zipValid = true;
        boolean countryValid = true;
        boolean priceValid = true;
        //without zip

        if (!isAddressValid()) {
            addressValid = false;
        }
        if (!isCityValid()) {
            cityValid = false;
        }
        if (!isStateValid()) {
            stateValid = false;
        }
        if (!isZipValid()) {
            zipValid = false;
        }
        if (!isCountryValid()) {
            countryValid = false;
        }
        if (!isPriceValid()) {
            priceValid = false;
        }
        if (addressValid && cityValid && stateValid && zipValid && countryValid && priceValid) return true;
        else return false;
    }


    private boolean isAddressValid() {
        propertyaddress = address.getText().toString();
        if (TextUtils.isEmpty(propertyaddress)) {
            address_box.setError("Address can not be empty");
            return false;
        } else {
            address_box.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isCityValid() {
        propertycity = city.getText().toString();
        if (TextUtils.isEmpty(propertycity)) {
            city_box.setError("City can not be empty");
            return false;
        } else {
            city_box.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isStateValid() {
        propertystate = state.getText().toString();
        if (TextUtils.isEmpty(propertystate)) {
            state_box.setError("State can not be empty");
            return false;
        } else {
            state_box.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isZipValid() {
        propertyzip = zip.getText().toString();
        if (TextUtils.isEmpty(propertyzip)) {
            zip_box.setError("Zip can not be empty");
            return false;
        } else if (propertyzip.length() != 5) {
            zip_box.setError("Zip code have to be in 5 digit");
            return false;
        } else {
            zip_box.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isCountryValid() {
        propertycountry = country.getText().toString();
        if (TextUtils.isEmpty(propertycountry)) {
            country_box.setError("Country can not be empty");
            return false;
        } else {
            country_box.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isPriceValid() {
        propertypurchaseprice = price.getText().toString();
        if (TextUtils.isEmpty(propertypurchaseprice)) {
            price_box.setError("Price can not be empty");
            return false;
        } else {
            price_box.setErrorEnabled(false);
            return true;
        }

    }

    private boolean checkMortgageinfo(View v) {
        if (yes_mortgage.isChecked()) {  //checked yes
            if (TextUtils.isEmpty(mortgage.getText().toString())) {
                yes_mortgage.setError(null);
                no_mortgage.setError(null);
                mortgage.setError("Please enter your mortgage information");
                return false;
            } else {
                yes_mortgage.setError(null);
                no_mortgage.setError(null);
                mortgage.setError(null);
                propertymortageinfo = mortgage.getText().toString();
                return true;
            }
        } else if (no_mortgage.isChecked()) { //checked no
            mortgage.setText("");
            mortgage.setEnabled(false);
            propertymortageinfo = "no";

            yes_mortgage.setError(null);
            no_mortgage.setError(null);
            mortgage.setError(null);
            return true;
        } else {
            yes_mortgage.setError("Please select either one");
            no_mortgage.setError("Please select either one");
            return false;
        }
    }
}
