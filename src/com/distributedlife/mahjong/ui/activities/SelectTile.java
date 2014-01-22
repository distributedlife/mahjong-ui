package com.distributedlife.mahjong.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.distributedlife.mahjong.ui.R;

public class SelectTile  extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_tile);

        findViewById(R.id.spot1).setOnClickListener(new ReturnToPrior(R.drawable.spot1));
        findViewById(R.id.spot2).setOnClickListener(new ReturnToPrior(R.drawable.spot2));
        findViewById(R.id.spot3).setOnClickListener(new ReturnToPrior(R.drawable.spot3));
        findViewById(R.id.spot4).setOnClickListener(new ReturnToPrior(R.drawable.spot4));
        findViewById(R.id.spot5).setOnClickListener(new ReturnToPrior(R.drawable.spot5));
        findViewById(R.id.spot6).setOnClickListener(new ReturnToPrior(R.drawable.spot6));
        findViewById(R.id.spot7).setOnClickListener(new ReturnToPrior(R.drawable.spot7));
        findViewById(R.id.spot8).setOnClickListener(new ReturnToPrior(R.drawable.spot8));
        findViewById(R.id.spot9).setOnClickListener(new ReturnToPrior(R.drawable.spot9));

        findViewById(R.id.crack1).setOnClickListener(new ReturnToPrior(R.drawable.crack1));
        findViewById(R.id.crack2).setOnClickListener(new ReturnToPrior(R.drawable.crack2));
        findViewById(R.id.crack3).setOnClickListener(new ReturnToPrior(R.drawable.crack3));
        findViewById(R.id.crack4).setOnClickListener(new ReturnToPrior(R.drawable.crack4));
        findViewById(R.id.crack5).setOnClickListener(new ReturnToPrior(R.drawable.crack5));
        findViewById(R.id.crack6).setOnClickListener(new ReturnToPrior(R.drawable.crack6));
        findViewById(R.id.crack7).setOnClickListener(new ReturnToPrior(R.drawable.crack7));
        findViewById(R.id.crack8).setOnClickListener(new ReturnToPrior(R.drawable.crack8));
        findViewById(R.id.crack9).setOnClickListener(new ReturnToPrior(R.drawable.crack9));

        findViewById(R.id.bamboo1).setOnClickListener(new ReturnToPrior(R.drawable.bamboo1));
        findViewById(R.id.bamboo2).setOnClickListener(new ReturnToPrior(R.drawable.bamboo2));
        findViewById(R.id.bamboo3).setOnClickListener(new ReturnToPrior(R.drawable.bamboo3));
        findViewById(R.id.bamboo4).setOnClickListener(new ReturnToPrior(R.drawable.bamboo4));
        findViewById(R.id.bamboo5).setOnClickListener(new ReturnToPrior(R.drawable.bamboo5));
        findViewById(R.id.bamboo6).setOnClickListener(new ReturnToPrior(R.drawable.bamboo6));
        findViewById(R.id.bamboo7).setOnClickListener(new ReturnToPrior(R.drawable.bamboo7));
        findViewById(R.id.bamboo8).setOnClickListener(new ReturnToPrior(R.drawable.bamboo8));
        findViewById(R.id.bamboo9).setOnClickListener(new ReturnToPrior(R.drawable.bamboo9));

        findViewById(R.id.red).setOnClickListener(new ReturnToPrior(R.drawable.red));
        findViewById(R.id.white).setOnClickListener(new ReturnToPrior(R.drawable.white));
        findViewById(R.id.green).setOnClickListener(new ReturnToPrior(R.drawable.green));

        findViewById(R.id.east).setOnClickListener(new ReturnToPrior(R.drawable.east));
        findViewById(R.id.south).setOnClickListener(new ReturnToPrior(R.drawable.south));
        findViewById(R.id.west).setOnClickListener(new ReturnToPrior(R.drawable.west));
        findViewById(R.id.north).setOnClickListener(new ReturnToPrior(R.drawable.north));
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
