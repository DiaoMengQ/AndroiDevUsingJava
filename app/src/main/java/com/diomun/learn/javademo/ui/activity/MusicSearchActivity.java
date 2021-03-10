package com.diomun.learn.javademo.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.adapter.SongRecyclerAdapater;
import com.diomun.learn.javademo.api.HttpService;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.model.Music.Data;
import com.diomun.learn.javademo.model.Music.Info;
import com.diomun.learn.javademo.model.Music.Song;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/10
 * @desc
 */
public class MusicSearchActivity extends BaseActivity implements SongRecyclerAdapater.OnChildClickListener {
    private SongRecyclerAdapater songRecyclerAdapater;
    private List<Info> songInfoList;

    @BindView(R.id.ed_musicSearch)
    TextView edMusicSearch;
    @BindView(R.id.btn_musicSearch)
    Button btnMusicSearch;
    @BindView(R.id.ll_musicSearch)
    LinearLayout llMusicSearch;
    @BindView(R.id.rv_searchList)
    RecyclerView rvSearchList;

    @Override
    public int initLayout() {
        return R.layout.activity_musicsearch;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.ed_musicSearch, R.id.btn_musicSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_musicSearch:
                Log.d(TAG, "onViewClicked: 文本框");
                break;
            case R.id.btn_musicSearch:
                Log.d(TAG, "onViewClicked: 音乐搜索按钮");
                musicSearch();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    /**
     * 音乐请求相关方法
     */
    private void musicSearch() {
        Toast.makeText(mContext, "请求数据", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mobilecdn.kugou.com/") // 注意根目录最后不带‘/’
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpService httpService = retrofit.create(HttpService.class);
        Call<Song> dataCall = httpService.getMusicData("/api/v3/search/song", "秋意浓", "1", "10");

        dataCall.enqueue(new Callback<Song>() {
            @Override
            public void onResponse(Call<Song> call, Response<Song> response) {
                if (response.code() == 200) {
                    Song songRes = response.body();
                    Data songData = songRes.getData();
                    songInfoList = songData.getInfo();

                    Log.d(TAG, "onResponse: " + songInfoList.size());

                    String data2show = "";
                    data2show = songInfoList.get(0).getFilename();

                    Bundle bundle = new Bundle();
                    bundle.putString(getString(R.string.bundleDataKey_httpTest), data2show);

                    // handle 通知当前 activity 主线程更新视图
                    Message msg = new Message();
                    msg.what = 0;
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Song> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void handlerMsg(Message msg) {
        if (msg.what == 0) {
            Log.d(TAG, "handlerMsg: 更新视图消息");
            // tvTest.setText(msg.getData().getString(getString(R.string.bundleDataKey_httpTest)));
            songRecyclerAdapater = new SongRecyclerAdapater(this, songInfoList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rvSearchList.setLayoutManager(linearLayoutManager);
            rvSearchList.setAdapter(songRecyclerAdapater);
            songRecyclerAdapater.setOnChildClickListener(this);
        } else {
            throw new IllegalStateException("Unexpected value: " + msg.what);
        }
    }

    @Override
    public void onChildClick(RecyclerView parent, View view, int position, Info data) {
        Log.d(TAG, "onChildClick: 点击事件");
        Toast.makeText(mContext, data.getInfoStr(), Toast.LENGTH_SHORT).show();
    }
}
