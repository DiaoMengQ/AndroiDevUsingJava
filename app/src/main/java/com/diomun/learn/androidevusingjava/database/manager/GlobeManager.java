package com.diomun.learn.androidevusingjava.database.manager;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/23
 */
public final class GlobeManager {
    public GlobeManager() {
    }

    public static abstract class UserInfo {
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String USERPASSWORD = "password";
        public static final String TABLENAME = "usertable";
        public static final String DATABASENAME = "userdatabase";
        public static final String PHONENUMBER = "phone";

        public static final String CREATE_TABLE = "create table " + TABLENAME + "("
                + ID + " integer primary key autoincrement,"
                + USERNAME + " text,"
                + PHONENUMBER + " text,"
                + USERPASSWORD + " text)";
    }
}
