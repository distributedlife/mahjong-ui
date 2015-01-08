package com.distributedlife.mahjong.ui.db;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class HandReference extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "mahjong.db";
    private static final int DATABASE_VERSION = 1;

    public HandReference(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}