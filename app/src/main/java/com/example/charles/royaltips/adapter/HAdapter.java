package com.example.charles.royaltips.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.charles.royaltips.model.Popular;

import java.util.List;

/**
 * Created by charles on 2017. 10. 23..
 * Horizontal Adapter
 */

public class HAdapter extends RecyclerView.Adapter<HAdapter.CustomViewHolder> {

    private Context context;
    private List<Popular>  popularList;

    public HAdapter(Context context, List<Popular> popularList) {
        this.context = context;
        this.popularList = popularList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a number variable
        // for any view that will be set as you render a row
        ImageView popularImage;
        TextView nameTextView;
        TextView countOne;
        TextView loremOne;
        TextView countTwo;
        TextView loremTwo;
        LinearLayout linearLayout;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public CustomViewHolder(View itemView) {
            super(itemView);


        }

        @Override
        public void onClick(View v) {

        }
    }

}
