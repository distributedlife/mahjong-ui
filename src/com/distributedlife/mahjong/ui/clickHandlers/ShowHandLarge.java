package com.distributedlife.mahjong.ui.clickHandlers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.distributedlife.mahjong.matching.matcher.Match;
import com.distributedlife.mahjong.ui.activities.SelectTile;
import com.distributedlife.mahjong.ui.activities.ShowPotentialHands;
import com.distributedlife.mahjong.ui.activities.ShowSelectedHand;

import java.util.ArrayList;

public class ShowHandLarge implements View.OnClickListener {
    private final Match match;
    private final Activity owner;
    private String ownWind;

    public ShowHandLarge(Match match, Activity owner, String ownWind) {
        this.match = match;
        this.owner = owner;
        this.ownWind = ownWind;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ShowSelectedHand.class);

        intent.putExtra("match-name", match.getName());
        intent.putExtra("match-count", match.getCount());
        intent.putStringArrayListExtra("match-required-tiles", (ArrayList<String>) match.getRequiredTiles());
        intent.putStringArrayListExtra("match-matching-tiles", new ArrayList<String>(match.getMatchingTiles()));
        intent.putExtra("own-wind", ownWind);

        owner.startActivity(intent);
    }
}
