package com.example.charles.royaltips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.charles.royaltips.model.Home;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestHome();

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


}
