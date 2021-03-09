package com.diomun.learn.javademo.ui.activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.service.BackService;
import com.diomun.learn.javademo.util.HttpService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/21
 */
public class MainActivity extends BaseActivity implements Callback<String> {
    public static final int CMD_STOP_SERVICE = 0;
    @BindView(R.id.btn_dataRequest)
    Button btnDataRequest;
    @BindView(R.id.tv_test)
    TextView tvTest;
    private Intent intent2backServ;
    private ServiceConnection servConn;

    @BindView(R.id.btn_toListView)
    Button btnToListView;
    @BindView(R.id.btn_database)
    Button btnDatabase;
    @BindView(R.id.btn_startBackService)
    Button btnStartBackService;
    @BindView(R.id.btn_stopBackService)
    Button btnStopBackService;
    @BindView(R.id.btn_viewBackService)
    Button btnViewBackService;
    @BindView(R.id.btn_unBindBackService)
    Button btnUnBindBackService;
    @BindView(R.id.btn_toRecycleView)
    Button btnToRecycleView;

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        intent2backServ = new Intent(mContext, BackService.class);

        servConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: 服务已绑定");
                BackService.MyBinder myBinder = (BackService.MyBinder) service;
                myBinder.showTip();
            }

            /**
             * Android系统在同service的连接意外丢失时调用这个．比如当service崩溃了或被强杀了．
             * 当客户端解除绑定时，这个方法不会被调用．
             */
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: 服务绑定丢失");
            }
        };
    }

    @OnClick({
            R.id.btn_toListView,
            R.id.btn_database,
            R.id.btn_startBackService,
            R.id.btn_stopBackService,
            R.id.btn_viewBackService,
            R.id.btn_unBindBackService,
            R.id.btn_toRecycleView,
            R.id.btn_dataRequest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_toRecycleView:
                Toast.makeText(mContext, "btn_toRecycleView", Toast.LENGTH_SHORT).show();
                Intent intent2Recycler = new Intent(this, RecyclerViewActivity.class);
                startActivity(intent2Recycler);
                break;
            case R.id.btn_toListView:
                Toast.makeText(mContext, "點擊btn_toListView", Toast.LENGTH_SHORT).show();
                Intent intent2ListView = new Intent(this, ListViewActivity.class);
                startActivity(intent2ListView);
                break;
            case R.id.btn_database:
                Toast.makeText(mContext, "数据库管理页", Toast.LENGTH_SHORT).show();
                Intent intent2DBManager = new Intent(this, DBManagerActivity.class);
                startActivity(intent2DBManager);
                break;
            case R.id.btn_startBackService:
                Toast.makeText(mContext, "开启服务", Toast.LENGTH_SHORT).show();
                startService(intent2backServ);
                // bindService(intent2backServ, servConn, BIND_AUTO_CREATE);
                break;
            case R.id.btn_stopBackService:
                Toast.makeText(mContext, "停止服务", Toast.LENGTH_SHORT).show();
                // 发送停止服务广播
                Intent intent2stopServ = new Intent();
                intent2stopServ.setAction(getString(R.string.action_stopBackService));
                intent2stopServ.putExtra("cmd", CMD_STOP_SERVICE);
                sendBroadcast(intent2stopServ);
                break;
            case R.id.btn_unBindBackService:
                Toast.makeText(mContext, "解绑服务", Toast.LENGTH_SHORT).show();
                unbindService(servConn);
                break;
            case R.id.btn_viewBackService:
                Toast.makeText(mContext, "查看服务状态", Toast.LENGTH_SHORT).show();
                ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningServiceInfo> list = Objects.requireNonNull(am).getRunningServices(30);

                for (ActivityManager.RunningServiceInfo info : list) {
                    // Log.d(TAG, "backSerivce: " + info);
                    if ("Scheduled-task".equals(info.service.getClassName())) {
                        Log.d(TAG, "backService: Scheduled-task running......");
                    }
                }
                break;
            case R.id.btn_dataRequest:
                Toast.makeText(mContext, "请求数据", Toast.LENGTH_SHORT).show();

                Retrofit retrofit = new Retrofit.Builder()
                        // .baseUrl("http://songsearch.kugou.com/song_search_v2?keyword=%E7%A7%8B%E6%84%8F%E6%B5%93&page=1&pagesize=10")
                        .baseUrl("https://baidu.com")
                        .addConverterFactory(
                                new Converter.Factory() {
                                    @Override
                                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                                        return (Converter<ResponseBody, String>) ResponseBody::string;
                                    }
                                }
                        ).build();
                HttpService httpService = retrofit.create(HttpService.class);
                retrofit2.Call<String> dataStrCall = httpService.getDataStr();
                dataStrCall.enqueue(this);

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    @Override
    public void handlerMsg(Message msg) {
        if (msg.what == 0) {
            Log.d(TAG, "handlerMsg: 更新视图");
            tvTest.setText(msg.getData().getString(getString(R.string.bundleDataKey_httpTest)));
        } else {
            throw new IllegalStateException("Unexpected value: " + msg.what);
        }
    }

    /**
     * 网络请求成功
     *
     * @param call
     * @param response 响应
     */
    @Override
    public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
        Intent intent2viewTest = new Intent(mContext, ViewTestActivity.class);

        if (response.code() == 200) {
            String dataStr = response.body();
            Log.d(TAG, "onResponse: " + dataStr);

            Bundle bundle = new Bundle();
            bundle.putString(getString(R.string.bundleDataKey_httpTest), dataStr);

            intent2viewTest.putExtra(getString(R.string.bundleKey_httpTest), bundle);
            // startActivity(intent2viewTest);

            // handle 通知当前 activity 主线程更新视图
            Message msg = new Message();
            msg.what = 0;
            msg.setData(bundle);
            mHandler.sendMessage(msg);
        }
    }


    /**
     * 网络请求失败
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(retrofit2.Call<String> call, Throwable t) {
        Toast.makeText(mContext, "网络请求出错" + call.request().url(), Toast.LENGTH_SHORT).show();
        t.printStackTrace();
    }
}

