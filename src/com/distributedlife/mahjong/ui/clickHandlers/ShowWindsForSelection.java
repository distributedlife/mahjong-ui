package com.distributedlife.mahjong.ui.clickHandlers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.distributedlife.mahjong.ui.activities.SelectWind;

public class ShowWindsForSelection implements View.OnClickListener {
    private int id;
    private Activity owner;

    public ShowWindsForSelection(int id, Activity owner) {
        this.id = id;
        this.owner = owner;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), SelectWind.class);
        owner.startActivityForResult(intent, id);
    }
}
