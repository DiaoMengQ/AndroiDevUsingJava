package com.diomun.learn.javademo.model;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/11
 * @desc
 */
public class TestData {
    private String title = "";
    private String detail = "";
    private String img = "";

    public TestData(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public TestData(String title, String detail, String img) {
        this.title = title;
        this.detail = detail;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
