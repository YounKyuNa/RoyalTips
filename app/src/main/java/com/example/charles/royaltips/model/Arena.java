package com.example.charles.royaltips.model;

import java.util.List;

/**
 * Created by charles on 2017. 10. 20..
 * 아레나
 */

public class Arena {
    public String idName;
    public int number;
    public String name;
    public int victoryGold;
    public int minTrophies;
    public int order;

    public List<String> leagues;
    public List<String> cardUnlocks;
    public List<String> chests;

    public Clan clan;

}
