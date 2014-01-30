package com.distributedlife.mahjong.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.distributedlife.mahjong.ui.R;
import com.distributedlife.mahjong.ui.resource.TileResourceMap;

import java.util.ArrayList;

public class ShowSelectedHand extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_selected_hand);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(
                this,
                getIntent().getStringArrayListExtra("match-required-tiles"),
                getIntent().getStringArrayListExtra("match-matching-tiles"),
                getIntent().getStringExtra("own-wind"))
        );

        ((TextView) findViewById(R.id.hand_name)).setText(getIntent().getStringExtra("match-name"));
        ((TextView) findViewById(R.id.hand_chance)).setText(getIntent().getStringExtra("match-chance"));
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<String> requiredTiles;
        private ArrayList<String> tilesToHighlight;
        private String ownWind;

        public ImageAdapter(Context c, ArrayList<String> requiredTiles, ArrayList<String> tilesToHighlight, String ownWind) {
            context = c;
            this.requiredTiles = requiredTiles;
            this.tilesToHighlight = tilesToHighlight;
            this.ownWind = ownWind;
        }

        @Override
        public int getCount() {
            return requiredTiles.size();
        }

        public Object getItem(int position) {
            return requiredTiles.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setPadding(5, 5, 5, 5);

            if (requiredTiles.get(position).equals("OwnWind")) {
                imageView.setImageResource(TileResourceMap.getByTile().get(ownWind));
            } else {
                imageView.setImageResource(TileResourceMap.getByTile().get(requiredTiles.get(position)));
            }

            if (tilesToHighlight.contains(requiredTiles.get(position))) {
                tilesToHighlight.remove(requiredTiles.get(position));

                imageView.setBackgroundColor(getResources().getColor(R.color.have));
            }

            return imageView;
        }
    }
}
