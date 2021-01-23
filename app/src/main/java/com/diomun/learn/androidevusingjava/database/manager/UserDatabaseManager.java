package com.diomun.learn.androidevusingjava.database.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diomun.learn.androidevusingjava.database.UserDBHelper;
import com.diomun.learn.androidevusingjava.database.model.User;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/23
 */
public class UserDatabaseManager {

    private Context mContext;
    private static UserDatabaseManager mManager;
    private UserDBHelper mDatabase;
    private static SQLiteDatabase mSQLiteDatabase;

    private UserDatabaseManager(Context context) {
        mContext = context;
        mDatabase = new UserDBHelper(mContext, GlobeManager.UserInfo.DATABASENAME, null, 1);
        mSQLiteDatabase = mDatabase.getWritableDatabase();
    }

    public static UserDatabaseManager newInstance(Context context) {
        if (mManager == null) {
            mManager = new UserDatabaseManager(context);
        }
        return mManager;
    }

    public static void addUser(User user) {

        ContentValues values = new ContentValues();

        values.put(GlobeManager.UserInfo.USERNAME, user.getName());
        values.put(GlobeManager.UserInfo.USERPASSWORD, user.getPassword());
        values.put(GlobeManager.UserInfo.PHONENUMBER, user.getPhone());
        mSQLiteDatabase.insert(GlobeManager.UserInfo.TABLENAME, null, values);
    }

    public static void updateUserPassword(User user) {

        ContentValues values = new ContentValues();
        values.put(GlobeManager.UserInfo.USERPASSWORD, user.getPassword());
        mSQLiteDatabase.update(GlobeManager.UserInfo.TABLENAME, values, GlobeManager.UserInfo.USERNAME + " = ?", new String[]{user.getName()});
    }

    public static void updateUserPhone(User user) {
        ContentValues values = new ContentValues();
        values.put(GlobeManager.UserInfo.PHONENUMBER, user.getPhone());
        mSQLiteDatabase.update(GlobeManager.UserInfo.TABLENAME, values, GlobeManager.UserInfo.USERNAME + " = ?", new String[]{user.getName()});
    }

    public static void deleteUser(User user) {

        mSQLiteDatabase.delete(GlobeManager.UserInfo.TABLENAME, GlobeManager.UserInfo.USERNAME + " = ?", new String[]{user.getName()});

    }

    public static User queryUsername(String username) {

        Cursor cursor = mSQLiteDatabase.query(GlobeManager.UserInfo.TABLENAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.USERNAME));
                if (name.equals(username)) {
                    String password = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.USERPASSWORD));
                    String phone = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.PHONENUMBER));
                    User user = new User(name, password);
                    user.setPhone(phone);
                    return user;
                }
            } while (cursor.moveToNext());
        }
        return null;
    }

    public static User queryByPhone(String phone) {

        Cursor cursor = mSQLiteDatabase.query(GlobeManager.UserInfo.TABLENAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String temp = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.PHONENUMBER));

                if (temp.equals(phone)) {
                    String name = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.USERNAME));
                    String password = cursor.getString(cursor.getColumnIndex(GlobeManager.UserInfo.USERPASSWORD));

                    User user = new User(name, password);
                    return user;
                }
            } while (cursor.moveToNext());


        }
        return null;
    }
}
