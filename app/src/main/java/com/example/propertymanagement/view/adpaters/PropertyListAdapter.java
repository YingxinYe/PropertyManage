package com.example.propertymanagement.view.adpaters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.pojo.Property;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.MyViewHolder> {

    ArrayList<Property> mlist;
    Context context;
    LayoutInflater inflater;
    setOnClickListener myOnClickListner;
    ArrayList<Boolean> openArray;
    private int opened = -1;


    public PropertyListAdapter(Context context, ArrayList<Property> list) {
        this.context = context;
        mlist = list;
        openArray = new ArrayList<>(mlist.size());
        for(int aa=0;aa<mlist.size();aa++){
            openArray.add(aa,false);
        }
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.row_property_list, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bindView(i, mlist.get(i));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void initOnClickListener(setOnClickListener myonClick) {
        myOnClickListner = myonClick;
    }

    public void setData(ArrayList<Property> mlist, int position) {
        this.mlist = mlist;
        openArray.remove(position);
        notifyDataSetChanged();
        notifyItemChanged(position);
        //-----> will call bindView
//        Log.i("winnie2", "setData was called. Size is "+openArray.size());
//        for(int i=0;i<openArray.size();i++){
//            Log.i("winnie2", "openArray["+i+"] is "+openArray.get(i));
//        }
    }

    public interface setOnClickListener {
        void onClickListener(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button view, delete;
        TextView address, text_view_status, text_view_price, text_view_mortgage;
        RelativeLayout hideLayout;

        public void bindView(int i, Property property) {
            String address_info = property.getPropertyaddress() + ","
                    + property.getPropertycity() + ","
                    + property.getPropertystate() + ","
                    + property.getPropertycountry();
            address.setText((i + 1) + ". " + address_info);

            text_view_status.setText("Status: " + property.getPropertystatus());
            text_view_price.setText("Purchase Price: $" + property.getPropertypurchaseprice());
            text_view_mortgage.setText("Mortgage detail: " + property.getPropertymortageinfo());

            if (openArray.size() > 0) {
                if (openArray.get(i)) {
                    view.setText("Hide Detail");
                    hideLayout.setVisibility(View.VISIBLE);
                } else {
                    view.setText("View Detail");
                    hideLayout.setVisibility(View.GONE);
                }
            }


//            if (i == opened){
//                view.setText("Hide Detail");
//                hideLayout.setVisibility(View.VISIBLE);
//            } else{
//                view.setText("View Detail");
//                hideLayout.setVisibility(View.GONE);
//            }


        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.property_list_address);
            view = itemView.findViewById(R.id.property_list_view_button);
            delete = itemView.findViewById(R.id.property_list_delete_button);

            hideLayout = itemView.findViewById(R.id.hide_layout);
            text_view_status = itemView.findViewById(R.id.property_list_status);
            text_view_price = itemView.findViewById(R.id.property_list_price);
            text_view_mortgage = itemView.findViewById(R.id.property_list_mortgage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (openArray.get(getAdapterPosition()) == true) {
                        openArray.set(getAdapterPosition(), false); //已经打开，就关闭
                        notifyItemChanged(getAdapterPosition());
                    } else {   //已经关闭，就打开
                        openArray.set(getAdapterPosition(), true);
                        notifyItemChanged(getAdapterPosition());

                    }

//                    if (opened == getAdapterPosition()) {
//                        //当点击的item已经被展开了, 就关闭.
//                        opened = -1;
//                        notifyItemChanged(getAdapterPosition());
//                    } else {
//                        int oldOpened = opened;
//                        opened = getAdapterPosition();
//                        //notifyItemChanged(oldOpened);
//                        notifyItemChanged(opened);
//                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnClickListner.onClickListener(v, getAdapterPosition());
                }
            });
        }


    }
}
