package com.example.charles.royaltips.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.charles.royaltips.R;

/**
 * Created by charles on 2017. 10. 20..
 * View Holder
 */

public class RTViewHolder extends RecyclerView.ViewHolder {

    private WebImageView imageView;

    public RTViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.content_main_item, parent, false));
        imageView = itemView.findViewById(R.id.image);
    }

}
