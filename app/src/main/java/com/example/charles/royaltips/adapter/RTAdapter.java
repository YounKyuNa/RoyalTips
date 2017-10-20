package com.example.charles.royaltips.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.charles.royaltips.view.RTViewHolder;

/**
 * Created by charles on 2017. 10. 20..
 * Adapter
 */

public class RTAdapter extends RecyclerView.Adapter<RTViewHolder> {

    private String[] mDataset;

    public RTAdapter(String[] mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public RTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RTViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
