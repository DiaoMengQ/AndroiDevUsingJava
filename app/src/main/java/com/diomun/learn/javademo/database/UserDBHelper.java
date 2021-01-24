package com.diomun.learn.javademo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diomun.learn.javademo.database.manager.GlobeManager;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/23
 */
public class UserDBHelper extends SQLiteOpenHelper {
    public UserDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GlobeManager.UserInfo.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
