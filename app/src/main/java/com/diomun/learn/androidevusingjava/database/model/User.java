package com.diomun.learn.androidevusingjava.database.model;

import androidx.annotation.NonNull;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/23
 */
public class User {
    private String mName;
    private String mPassword;
    private String mPhone;

    public User(String name, String password) {
        mName = name;
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" + getName() + " " + getPassword() + " " + getPhone() + "}";
    }



}
