package com.distributedlife.mahjong.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.distributedlife.mahjong.ui.R;
import com.distributedlife.mahjong.ui.clickHandlers.ShowTilesForSelection;
import com.distributedlife.mahjong.ui.clickHandlers.ShowWindsForSelection;
import com.distributedlife.mahjong.ui.resource.TileResourceMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectHand extends Activity {
    Map<Integer, Integer> tiles = new HashMap<Integer, Integer>();
    Integer ownWind = 0;
    Map<Integer, String> resourceToTileMap = TileResourceMap.getByResource();

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

        findViewById(R.id.tileOwnWind).setOnClickListener(new ShowWindsForSelection(R.id.tileOwnWind, this));

        findViewById(R.id.find_hands).setOnClickListener(new RevealPotentialHands());
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        int tileId = data.getIntExtra("tile", -1);

        if (requestCode != R.id.tileOwnWind) {
            tiles.put(requestCode, tileId);
        } else {
            ownWind = tileId;
        }

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

            if (ownWind != 0) {
                intent.putExtra("own-wind", resourceToTileMap.get(ownWind));
            }

            startActivity(intent);
        }
    }
}
