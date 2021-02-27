package com.diomun.learn.javademo.ui.activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.service.BackService;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/21
 */
public class MainActivity extends BaseActivity {
    public static final int CMD_STOP_SERVICE = 0;

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

    Intent intent2backServ;
    ServiceConnection servConn;

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        intent2backServ = new Intent(mContext, BackService.class);

        servConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: ");
                BackService.MyBinder myBinder = (BackService.MyBinder) service;
                myBinder.showTip();
            }

            /**
             * Android系统在同service的连接意外丢失时调用这个．比如当service崩溃了或被强杀了．
             * 当客户端解除绑定时，这个方法不会被调用．
             */
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: ");
            }
        };
    }


    @OnClick({
            R.id.btn_toListView,
            R.id.btn_database,
            R.id.btn_startBackService,
            R.id.btn_stopBackService,
            R.id.btn_viewBackService,
            R.id.btn_unBindBackService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_toListView:
                Toast.makeText(mContext, "點擊btn_toListView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_database:
                Toast.makeText(mContext, "数据库管理页", Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent(this, DBManagerActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_startBackService:
                Toast.makeText(mContext, "开启服务", Toast.LENGTH_SHORT).show();
                startService(intent2backServ);
                // bindService(intent2backServ, servConn, BIND_AUTO_CREATE);
                break;
            case R.id.btn_stopBackService:
                Toast.makeText(mContext, "停止服务", Toast.LENGTH_SHORT).show();
                // 发送停止服务广播
                Intent it2stopService = new Intent();
                it2stopService.setAction("AAAAA");
                it2stopService.putExtra("cmd", CMD_STOP_SERVICE);
                sendBroadcast(it2stopService);
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
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

}

