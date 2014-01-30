package com.distributedlife.mahjong.ui.resource;

import com.distributedlife.mahjong.ui.R;

import java.util.HashMap;
import java.util.Map;

public class TileResourceMap {
    public static Map<Integer, String> getByResource() {
        Map<Integer, String> map = new HashMap<Integer, String>();

        map.put(R.drawable.bamboo1, "1 Bamboo");
        map.put(R.drawable.bamboo2, "2 Bamboo");
        map.put(R.drawable.bamboo3, "3 Bamboo");
        map.put(R.drawable.bamboo4, "4 Bamboo");
        map.put(R.drawable.bamboo5, "5 Bamboo");
        map.put(R.drawable.bamboo6, "6 Bamboo");
        map.put(R.drawable.bamboo7, "7 Bamboo");
        map.put(R.drawable.bamboo8, "8 Bamboo");
        map.put(R.drawable.bamboo9, "9 Bamboo");

        map.put(R.drawable.crack1, "1 Crack");
        map.put(R.drawable.crack2, "2 Crack");
        map.put(R.drawable.crack3, "3 Crack");
        map.put(R.drawable.crack4, "4 Crack");
        map.put(R.drawable.crack5, "5 Crack");
        map.put(R.drawable.crack6, "6 Crack");
        map.put(R.drawable.crack7, "7 Crack");
        map.put(R.drawable.crack8, "8 Crack");
        map.put(R.drawable.crack9, "9 Crack");

        map.put(R.drawable.spot1, "1 Spot");
        map.put(R.drawable.spot2, "2 Spot");
        map.put(R.drawable.spot3, "3 Spot");
        map.put(R.drawable.spot4, "4 Spot");
        map.put(R.drawable.spot5, "5 Spot");
        map.put(R.drawable.spot6, "6 Spot");
        map.put(R.drawable.spot7, "7 Spot");
        map.put(R.drawable.spot8, "8 Spot");
        map.put(R.drawable.spot9, "9 Spot");

        map.put(R.drawable.red, "Red");
        map.put(R.drawable.white, "White");
        map.put(R.drawable.green, "Green");
        map.put(R.drawable.east, "East");
        map.put(R.drawable.south, "South");
        map.put(R.drawable.west, "West");
        map.put(R.drawable.north, "North");

        return map;
    }

    public static Map<String, Integer> getByTile() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("1 Bamboo", R.drawable.bamboo1);
        map.put("2 Bamboo", R.drawable.bamboo2);
        map.put("3 Bamboo", R.drawable.bamboo3);
        map.put("4 Bamboo", R.drawable.bamboo4);
        map.put("5 Bamboo", R.drawable.bamboo5);
        map.put("6 Bamboo", R.drawable.bamboo6);
        map.put("7 Bamboo", R.drawable.bamboo7);
        map.put("8 Bamboo", R.drawable.bamboo8);
        map.put("9 Bamboo", R.drawable.bamboo9);
        map.put("1 Crack", R.drawable.crack1);
        map.put("2 Crack", R.drawable.crack2);
        map.put("3 Crack", R.drawable.crack3);
        map.put("4 Crack", R.drawable.crack4);
        map.put("5 Crack", R.drawable.crack5);
        map.put("6 Crack", R.drawable.crack6);
        map.put("7 Crack", R.drawable.crack7);
        map.put("8 Crack", R.drawable.crack8);
        map.put("9 Crack", R.drawable.crack9);
        map.put("1 Spot", R.drawable.spot1);
        map.put("2 Spot", R.drawable.spot2);
        map.put("3 Spot", R.drawable.spot3);
        map.put("4 Spot", R.drawable.spot4);
        map.put("5 Spot", R.drawable.spot5);
        map.put("6 Spot", R.drawable.spot6);
        map.put("7 Spot", R.drawable.spot7);
        map.put("8 Spot", R.drawable.spot8);
        map.put("9 Spot", R.drawable.spot9);
        map.put("Red", R.drawable.red);
        map.put("White", R.drawable.white);
        map.put("Green", R.drawable.green);
        map.put("East", R.drawable.east);
        map.put("South", R.drawable.south);
        map.put("West", R.drawable.west);
        map.put("North", R.drawable.north);

        return map;
    }
}
