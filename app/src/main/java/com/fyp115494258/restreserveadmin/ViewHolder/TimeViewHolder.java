package com.fyp115494258.restreserveadmin.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyp115494258.restreserveadmin.Interface.ItemClickListener;
import com.fyp115494258.restreserveadmin.R;

public class TimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public Button btnTime;


    private ItemClickListener itemClickListener;

    public TimeViewHolder(@NonNull View itemView) {
        super(itemView);

        btnTime=(Button)itemView.findViewById(R.id.btnTimeSlot);


        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }





}
