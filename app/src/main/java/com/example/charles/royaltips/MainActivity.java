package com.example.charles.royaltips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.charles.royaltips.model.Arena;
import com.example.charles.royaltips.model.Card;
import com.example.charles.royaltips.model.Chest;
import com.example.charles.royaltips.model.Home;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    GridLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestHome();

        requestCards();
        requestChests();
        requestArenas();

        initLayout();
    }

    void initLayout() {
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    /**
     * Base route
     */
    void requestHome() {
        Call<Home> call = ApiRequest.instance().getHome();
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {

                Log.d("#@@#","onResponse");

            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {

                Log.d("#@@#","onFailure");
            }
        });
    }


    /**
     * Card List
     */
    void requestCards() {
        Call<List<Card>> call = ApiRequest.instance().getCardList();
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {

                Log.d("#@@#","onResponse");

            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {

                Log.d("#@@#","onFailure");
            }
        });
    }

    /**
     * Chest List
     */
    void requestChests() {
        Call<List<Chest>> call = ApiRequest.instance().getChests();
        call.enqueue(new Callback<List<Chest>>() {
            @Override
            public void onResponse(Call<List<Chest>> call, Response<List<Chest>> response) {

                Log.d("#@@#","onResponse");

            }

            @Override
            public void onFailure(Call<List<Chest>> call, Throwable t) {

                Log.d("#@@#","onFailure");
            }
        });
    }

    /**
     * Arena List
     */
    void requestArenas() {
        Call<List<Arena>> call = ApiRequest.instance().getArenaList();
        call.enqueue(new Callback<List<Arena>>() {
            @Override
            public void onResponse(Call<List<Arena>> call, Response<List<Arena>> response) {

                Log.d("#@@#","onResponse");

            }

            @Override
            public void onFailure(Call<List<Arena>> call, Throwable t) {

                Log.d("#@@#","onFailure");
            }
        });
    }
}
