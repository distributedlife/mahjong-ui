package com.distributedlife.mahjong.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.distributedlife.mahjong.ui.R;

public class SelectWind extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_tile);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return tiles.length;
        }

        public Object getItem(int position) {
            return tiles[position];
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

            imageView.setImageResource(tiles[position]);
            imageView.setOnClickListener(new ReturnToPrior(tiles[position]));
            return imageView;
        }

        private Integer[] tiles = {
                R.drawable.east, R.drawable.south, R.drawable.west, R.drawable.north
        };
    }

    class ReturnToPrior implements View.OnClickListener {
        private int id;

        public ReturnToPrior(int id) {
            this.id = id;
        }

        @Override
        public void onClick(View view) {
            Intent data = new Intent();
            data.putExtra("tile", id);

            setResult(RESULT_OK, data);

            finish();
        }
    }
}
