package com.diomun.learn.javademo.util;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/9
 * @desc 使用 Retrofit 的网络请求工具
 */
public interface HttpService {
    /**
     * @return
     */
    @GET("/") // 网址底下的子目录
    Call<String> getDataStr();
}
