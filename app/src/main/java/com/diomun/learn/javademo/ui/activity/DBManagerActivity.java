package com.diomun.learn.javademo.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.database.UserDBHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/2/18
 * @desc
 */
public class DBManagerActivity extends BaseActivity {
    @BindView(R.id.btn_createDB)
    Button btnCreateDB;
    @BindView(R.id.btn_dataList)
    Button btnDataList;

    @Override
    public int initLayout() {
        return R.layout.activity_dbmanager;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        UserDBHelper dbHelper = new UserDBHelper(this, "userInfo.db",null, 1);
    }

    @OnClick({R.id.btn_createDB, R.id.btn_dataList})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_createDB:
                Toast.makeText(mContext, "创建", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dataList:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}
