package com.example.charles.royaltips.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charles.royaltips.R;

/**
 * Created by charles on 2017. 10. 23..
 * Page1 Fragment
 */

public class Page1Fragment extends Fragment {

    RecyclerView hRecyclerView, vRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page1, container, false);
    }
}
