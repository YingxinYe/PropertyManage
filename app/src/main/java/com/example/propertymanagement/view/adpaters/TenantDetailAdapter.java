package com.example.propertymanagement.view.adpaters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.bumptech.glide.request.transition.Transition;
import com.example.propertymanagement.R;
import com.example.propertymanagement.model.pojo.Tenant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TenantDetailAdapter extends RecyclerView.Adapter<TenantDetailAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Tenant> arrayList= new ArrayList<>();
    private ArrayList<Tenant> exampleList;

    public TenantDetailAdapter(Context context, ArrayList<Tenant> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        exampleList = new ArrayList<>(arrayList);
    }

//    public void setData(ArrayList<Tenant> arrayList){
//        this.arrayList = arrayList;
//        notifyDataSetChanged();
//    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_tenant_detail_recycler_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
         Tenant tenant = arrayList.get(i);

         myViewHolder.textViewId.setText("id is:"+tenant.getId());
         myViewHolder.textViewname.setText("name is:"+tenant.getTenantname());
         myViewHolder.textViewEmail.setText("email is:"+tenant.getTenantemail());
         myViewHolder.textViewaddress.setText("address is:"+tenant.getTenantaddress());
         myViewHolder.textViewMobile.setText("mobile is:"+tenant.getTenantmobile());
         myViewHolder.textViewPropertyId.setText("Preperty id is:"+tenant.getPropertyid());
         myViewHolder.textViewLandlordId.setText("landlord id is:"+tenant.getLandlordid());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId,textViewname,textViewEmail,textViewaddress,textViewMobile,textViewPropertyId,textViewLandlordId;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.text_id);
            textViewname = itemView.findViewById(R.id.text_tenantname);
            textViewEmail = itemView.findViewById(R.id.text_tenantemail);
            textViewaddress = itemView.findViewById(R.id.text_tenantaddress);
            textViewMobile = itemView.findViewById(R.id.text_tenantmobile);
            textViewPropertyId = itemView.findViewById(R.id.text_propertyid);
            textViewLandlordId = itemView.findViewById(R.id.text_landlordid);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Tenant> list = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                list.addAll(exampleList);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Tenant item:exampleList){
                    if (item.getTenantname().toLowerCase().contains(filterPattern)||item.getTenantemail().contains(filterPattern)){
                        list.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = list;

            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((Collection<? extends Tenant>) results.values);
            notifyDataSetChanged();
        }
    };
}
