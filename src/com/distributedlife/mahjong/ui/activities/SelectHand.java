package com.distributedlife.mahjong.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.distributedlife.mahjong.ui.R;
import com.distributedlife.mahjong.ui.clickHandlers.ShowTilesForSelection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectHand extends Activity {
    Map<Integer, Integer> tiles = new HashMap<Integer, Integer>();
    Map<Integer, String> resourceToTileMap = buildDrawableToTileNameMap();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.tile1).setOnClickListener(new ShowTilesForSelection(R.id.tile1, this));
        findViewById(R.id.tile2).setOnClickListener(new ShowTilesForSelection(R.id.tile2, this));
        findViewById(R.id.tile3).setOnClickListener(new ShowTilesForSelection(R.id.tile3, this));
        findViewById(R.id.tile4).setOnClickListener(new ShowTilesForSelection(R.id.tile4, this));
        findViewById(R.id.tile5).setOnClickListener(new ShowTilesForSelection(R.id.tile5, this));
        findViewById(R.id.tile6).setOnClickListener(new ShowTilesForSelection(R.id.tile6, this));
        findViewById(R.id.tile7).setOnClickListener(new ShowTilesForSelection(R.id.tile7, this));
        findViewById(R.id.tile8).setOnClickListener(new ShowTilesForSelection(R.id.tile8, this));
        findViewById(R.id.tile9).setOnClickListener(new ShowTilesForSelection(R.id.tile9, this));
        findViewById(R.id.tile10).setOnClickListener(new ShowTilesForSelection(R.id.tile10, this));
        findViewById(R.id.tile11).setOnClickListener(new ShowTilesForSelection(R.id.tile11, this));
        findViewById(R.id.tile12).setOnClickListener(new ShowTilesForSelection(R.id.tile12, this));
        findViewById(R.id.tile13).setOnClickListener(new ShowTilesForSelection(R.id.tile13, this));
        findViewById(R.id.tile14).setOnClickListener(new ShowTilesForSelection(R.id.tile14, this));

        //TODO: own wind

        findViewById(R.id.find_hands).setOnClickListener(new RevealPotentialHands());
    }

    private Map<Integer, String> buildDrawableToTileNameMap() {
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

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        int tileId = data.getIntExtra("tile", -1);

        tiles.put(requestCode, tileId);

        ((ImageButton) findViewById(requestCode)).setImageResource(tileId);
    }

    private class RevealPotentialHands implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            ArrayList<String> tilesInHand = new ArrayList<String>();

            for (Integer slot : tiles.keySet()) {
                tilesInHand.add(resourceToTileMap.get(tiles.get(slot)));
            }

            Intent intent = new Intent(view.getContext(), ShowPotentialHands.class);
            intent.putStringArrayListExtra("tiles-in-hand", tilesInHand);
            startActivity(intent);
        }
    }
}
