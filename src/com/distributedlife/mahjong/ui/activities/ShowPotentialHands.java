package com.distributedlife.mahjong.ui.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.distributedlife.mahjong.matching.filter.MatchingHandFilter;
import com.distributedlife.mahjong.matching.hand.HandLibrary;
import com.distributedlife.mahjong.matching.matcher.MahJongHandMatcher;
import com.distributedlife.mahjong.matching.matcher.Match;
import com.distributedlife.mahjong.matching.sorter.MatchingHandSorter;
import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.ui.R;
import com.distributedlife.mahjong.ui.clickHandlers.ShowHandLarge;
import com.distributedlife.mahjong.ui.resource.TileResourceMap;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowPotentialHands extends ListActivity {

    private String ownWind = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new MobileArrayAdapter(this, getMatchingHandNames(), this));
    }


    private List<Match> getMatchingHandNames() {
        MahJongHandMatcher mahJongHandMatcher = new MahJongHandMatcher(
                new MatchingHandSorter(),
                new MatchingHandFilter(HandLibrary.loadFromJson(getHandLibraryAsJson()))
        );

        ArrayList<String> tilesInHand = getIntent().getStringArrayListExtra("tiles-in-hand");

        if (!getIntent().hasExtra("own-wind")) {
            return mahJongHandMatcher.getMatches(tilesInHand);
        } else {
            ownWind = getIntent().getStringExtra("own-wind");
            return mahJongHandMatcher.getMatchesWithOwnWind(tilesInHand, TileSet.Winds.valueOf(ownWind));
        }
    }

    private JSONObject getHandLibraryAsJson() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(IOUtils.toString(ShowPotentialHands.class.getResourceAsStream("/all-hands.json")));
        } catch (JSONException e) {
            throw new RuntimeException("Hand Library could not be parsed. Please reinstall.");
        } catch (IOException e) {
            throw new RuntimeException("Hand Library could not found. Please reinstall.");
        }
        return jsonObject;
    }

    public class MobileArrayAdapter extends ArrayAdapter<Match> {
        private final Context context;
        private final List<Match> matches;
        private Activity owner;

        public MobileArrayAdapter(Context context, List<Match> matches, Activity owner) {
            super(context, R.layout.potential_hands_list, matches);
            this.context = context;
            this.matches = matches;
            this.owner = owner;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.potential_hands_list, parent, false);

            Match match = matches.get(position);
            rowView.setOnClickListener(new ShowHandLarge(match, owner, ownWind));


            assert rowView != null;
            ((TextView) rowView.findViewById(R.id.hand_name)).setText(match.getName());

            float percent = ((float) match.getCount() / 14.0f) * 100.0f;
            ((TextView) rowView.findViewById(R.id.hand_chance)).setText(String.format("%.1f%%", percent));


            List<ImageView> tiles = new ArrayList<ImageView>();
            tiles.add((ImageView) rowView.findViewById(R.id.tile1));
            tiles.add((ImageView) rowView.findViewById(R.id.tile2));
            tiles.add((ImageView) rowView.findViewById(R.id.tile3));
            tiles.add((ImageView) rowView.findViewById(R.id.tile4));
            tiles.add((ImageView) rowView.findViewById(R.id.tile5));
            tiles.add((ImageView) rowView.findViewById(R.id.tile6));
            tiles.add((ImageView) rowView.findViewById(R.id.tile7));
            tiles.add((ImageView) rowView.findViewById(R.id.tile8));
            tiles.add((ImageView) rowView.findViewById(R.id.tile9));
            tiles.add((ImageView) rowView.findViewById(R.id.tile10));
            tiles.add((ImageView) rowView.findViewById(R.id.tile11));
            tiles.add((ImageView) rowView.findViewById(R.id.tile12));
            tiles.add((ImageView) rowView.findViewById(R.id.tile13));
            tiles.add((ImageView) rowView.findViewById(R.id.tile14));


            List<String> tilesToHighlight = new ArrayList<String>(match.getMatchingTiles());
            for (int i = 0; i < 14; i++) {
                String tile = match.getRequiredTiles().get(i);

                ImageView tileImage = tiles.get(i);
                if (tile.equals("OwnWind")) {
                    tileImage.setImageResource(TileResourceMap.getByTile().get(ownWind));
                } else {
                    tileImage.setImageResource(TileResourceMap.getByTile().get(tile));
                }

                if (tilesToHighlight.contains(tile)) {
                    tilesToHighlight.remove(tile);

                    tileImage.setBackgroundColor(getResources().getColor(R.color.have));
                }
            }

            return rowView;
        }
    }
}
