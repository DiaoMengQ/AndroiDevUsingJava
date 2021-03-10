/**
  * Copyright 2021 bejson.com 
  */
package com.diomun.learn.javademo.model.Music;

import com.google.gson.annotations.SerializedName;

/**
 * API提供：https://www.cnblogs.com/daxiangxm/archive/2019/10/25/kugou_music_api.html
 * Auto-generated: 2021-03-10 18:54:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Song {

    @SerializedName("status")
    private int status;
    @SerializedName("error")
    private String error;
    @SerializedName("data")
    private Data data;
    @SerializedName("errcode")
    private int errcode;
    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setError(String error) {
         this.error = error;
     }
     public String getError() {
         return error;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setErrcode(int errcode) {
         this.errcode = errcode;
     }
     public int getErrcode() {
         return errcode;
     }

}