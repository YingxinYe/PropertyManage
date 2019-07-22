package com.example.propertymanagement.view.adpaters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.pojo.Icon;

import java.util.ArrayList;

public class IconListAdapter extends RecyclerView.Adapter<IconListAdapter.MyViewHolder> {

    ArrayList<Icon> mlist;
    Context context;
    LayoutInflater inflater;
    setOnClickListener myListener;

    public IconListAdapter(Context context, ArrayList<Icon> list){
        mlist=list;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.grid_home_icon_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(mlist.get(i).getName());
        myViewHolder.imageView.setImageResource(mlist.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface setOnClickListener{
        void onClickListener(View v, int position);
    }

    public void initOnClickListener(setOnClickListener myListener){
        this.myListener=myListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.icon_image);
            textView=itemView.findViewById(R.id.icon_name);
            imageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myListener.onClickListener(v,getAdapterPosition());
        }
    }
}
