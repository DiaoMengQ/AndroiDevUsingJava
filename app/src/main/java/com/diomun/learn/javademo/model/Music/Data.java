/**
  * Copyright 2021 bejson.com 
  */
package com.diomun.learn.javademo.model.Music;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Auto-generated: 2021-03-10 18:54:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    @SerializedName("aggregation")
    private List<Aggregation> aggregation;
    @SerializedName("tab")
    private String tab;
    @SerializedName("info")
    private List<Info> info;
    @SerializedName("correctiontype")
    private int correctiontype;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("allowerr")
    private int allowerr;
    @SerializedName("total")
    private int total;
    @SerializedName("istag")
    private int istag;
    @SerializedName("istagresult")
    private int istagresult;
    @SerializedName("forcecorrection")
    private int forcecorrection;
    @SerializedName("correctiontip")
    private String correctiontip;

    public void setAggregation(List<Aggregation> aggregation) {
         this.aggregation = aggregation;
     }
     public List<Aggregation> getAggregation() {
         return aggregation;
     }

    public void setTab(String tab) {
         this.tab = tab;
     }
     public String getTab() {
         return tab;
     }

    public void setInfo(List<Info> info) {
         this.info = info;
     }
     public List<Info> getInfo() {
         return info;
     }

    public void setCorrectiontype(int correctiontype) {
         this.correctiontype = correctiontype;
     }
     public int getCorrectiontype() {
         return correctiontype;
     }

    public void setTimestamp(long timestamp) {
         this.timestamp = timestamp;
     }
     public long getTimestamp() {
         return timestamp;
     }

    public void setAllowerr(int allowerr) {
         this.allowerr = allowerr;
     }
     public int getAllowerr() {
         return allowerr;
     }

    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    public void setIstag(int istag) {
         this.istag = istag;
     }
     public int getIstag() {
         return istag;
     }

    public void setIstagresult(int istagresult) {
         this.istagresult = istagresult;
     }
     public int getIstagresult() {
         return istagresult;
     }

    public void setForcecorrection(int forcecorrection) {
         this.forcecorrection = forcecorrection;
     }
     public int getForcecorrection() {
         return forcecorrection;
     }

    public void setCorrectiontip(String correctiontip) {
         this.correctiontip = correctiontip;
     }
     public String getCorrectiontip() {
         return correctiontip;
     }

}