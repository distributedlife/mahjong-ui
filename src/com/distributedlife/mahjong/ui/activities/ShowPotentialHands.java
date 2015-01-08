package com.distributedlife.mahjong.ui.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.distributedlife.mahjong.matching.matcher.Match;
import com.distributedlife.mahjong.reference.adapter.ArrayOfTilesToBitFieldConverter;
import com.distributedlife.mahjong.ui.R;
import com.distributedlife.mahjong.ui.clickHandlers.ShowHandLarge;
import com.distributedlife.mahjong.ui.db.HandReference;
import com.distributedlife.mahjong.ui.resource.TileResourceMap;

import java.util.ArrayList;
import java.util.List;

public class ShowPotentialHands extends ListActivity {
    private String ownWind = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new MobileArrayAdapter(this, getMatchingHandNames(), this));
    }


    private List<Match> getMatchingHandNames() {
        ArrayList<Match> matches = new ArrayList<Match>();

//        MahJongHandMatcher mahJongHandMatcher = new MahJongHandMatcher(
//                new MatchingHandSorter(),
//                new MatchingHandFilter(new HandReference())
//        );

        List<String> tilesInHand = getIntent().getStringArrayListExtra("tiles-in-hand");
        List<Long> handParts = ArrayOfTilesToBitFieldConverter.convertToBitField(tilesInHand);

        if (!getIntent().hasExtra("own-wind")) {
            HandReference handReference = new HandReference(this);
            String sql = String.format(
                    "SELECT name, p1 & %d as v1, p2 & %d as v2, p3 & %d as v3, p4 & %d as v4, p1, p2, p3, p4 " +
                    "FROM hands " +
                    "WHERE p1 & %d OR p2 & %d OR p3 & %d OR p4 & %d " +
                    "ORDER BY v1, v2, v3, v4",
                    5,
                    handParts.get(1),
                    handParts.get(2),
                    handParts.get(3),
                    5,
                    handParts.get(1),
                    handParts.get(2),
                    handParts.get(3)
            );
            Cursor cursor = handReference.getReadableDatabase().rawQuery(sql, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                matches.add(convert(cursor));

                cursor.moveToNext();
            }
            cursor.close();

//            return mahJongHandMatcher.getMatches(
//                    handParts.get(0),
//                    handParts.get(1),
//                    handParts.get(2),
//                    handParts.get(3)
//            );
        } else {
            ownWind = getIntent().getStringExtra("own-wind");
//            return mahJongHandMatcher.getMatchesWithOwnWind(
//                    handParts.get(0),
//                    handParts.get(1),
//                    handParts.get(2),
//                    handParts.get(3),
//                    TileSet.Winds.valueOf(ownWind)
//            );
        }

        return matches;
    }

    private Match convert(Cursor cursor) {
        String name = cursor.getString(0);
        Long v1 = cursor.getLong(1);
        Long v2 = cursor.getLong(2);
        Long v3 = cursor.getLong(3);
        Long v4 = cursor.getLong(4);
        Long p1 = cursor.getLong(5);
        Long p2 = cursor.getLong(6);
        Long p3 = cursor.getLong(7);
        Long p4 = cursor.getLong(8);

        List<String> required = ArrayOfTilesToBitFieldConverter.convertFromBitField(p1, p2, p3, p4);
        List<String> has = ArrayOfTilesToBitFieldConverter.convertFromBitField(v1, v2, v3, v4);

        return new Match(
                name,
                has.size(),
                has,
                required
        );
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
