package com.diomun.learn.javademo.api;

import com.diomun.learn.javademo.model.Music.Song;
import com.diomun.learn.javademo.model.User.ReqKey;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/9
 * @desc 使用 Retrofit 的网络请求工具
 */
public interface HttpService {
    /**
     * 获取数据
     */
    @GET("{category}")
    Call<Song> getMusicData(
            @Path("category") String tPath, // 有一些api不同分类路径下有相同的路径名，可在代码中动态设置路径
            @Query("keyword") String keyword,
            @Query("page") String page,
            @Query("pagesize") String pageSize);

    @POST
    @FormUrlEncoded
    Call<ReqKey> getToken(
            @Field("userid") String userId,
            @Field("token") String token);

}
