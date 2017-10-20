package com.example.charles.royaltips;

import com.example.charles.royaltips.model.Arena;
import com.example.charles.royaltips.model.Card;
import com.example.charles.royaltips.model.Chest;
import com.example.charles.royaltips.model.Home;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by charles on 2017. 10. 20..
 * api.cr-api.com
 */

public class ApiRequest {

    private static final String CR_API = "http://api.cr-api.com";
    private static final String CLASH_XYZ = "http://www.clashapi.xyz/";

    private static final String IMG_ARENA = "images/arenas/";
    private static final String IMG_CARDS = "images/cards/";
    private static final String IMG_CHESTS = "images/chests/";
    private static final String IMG_LEAGUES = "images/leagues/";

    private static ApiCallInterface apiCallInterface;

    public static ApiCallInterface instance() {

        if (apiCallInterface == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CLASH_XYZ)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiCallInterface = retrofit.create(ApiCallInterface.class);
        }

        return apiCallInterface;
    }


    public interface ApiCallInterface {

        @GET(CLASH_XYZ) Call<Home> getHome();

        @GET(CLASH_XYZ + "api/chests")  Call<List<Chest>>   getChests();
        @GET(CLASH_XYZ + "api/cards")   Call<List<Card>>    getCardList();
        @GET(CLASH_XYZ + "api/arenas")  Call<List<Arena>>   getArenaList();
    }

    public static String getCardUrl(String name) {
        return String.format("%s%s%s.png", CLASH_XYZ, IMG_CARDS, name);
    }

    public static String getChestUrl(String name) {
        return String.format("%s%s%s.png", CLASH_XYZ, IMG_CHESTS, name);
    }

    public static String getArenaUrl(String name) {
        return String.format("%s%s%s.png", CLASH_XYZ, IMG_ARENA, name);
    }

    public static String getLeagueUrl(String name) {
        return String.format("%s%s%s.png", CLASH_XYZ, IMG_LEAGUES, name);
    }

}
