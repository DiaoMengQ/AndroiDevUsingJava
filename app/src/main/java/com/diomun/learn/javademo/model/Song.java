package com.diomun.learn.javademo.model;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/6
 * @desc
 */
public class Song {
    private String sName = "未知曲名";
    private String sSinger = "佚名";
    private String sUrl;
    private String sAlbumUrl;

    public Song() {
    }


    public Song(String sUrl) {
        this.sUrl = sUrl;
        this.sName = "未知曲名";
        this.sSinger = "佚名";
    }

    public Song(String sName, String sSinger, String sUrl) {
        this.sName = sName;
        this.sUrl = sUrl;
        this.sSinger = sSinger;
    }

    public Song(String sName, String sSinger, String sUrl, String sAlbumUrl) {
        this.sName = sName;
        this.sUrl = sUrl;
        this.sAlbumUrl = sAlbumUrl;
        this.sSinger = sSinger;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    public String getsAlbumUrl() {
        return sAlbumUrl;
    }

    public void setsAlbumUrl(String sAlbumUrl) {
        this.sAlbumUrl = sAlbumUrl;
    }

    public String getsSinger() {
        return sSinger;
    }

    public void setsSinger(String sSinger) {
        this.sSinger = sSinger;
    }

}
