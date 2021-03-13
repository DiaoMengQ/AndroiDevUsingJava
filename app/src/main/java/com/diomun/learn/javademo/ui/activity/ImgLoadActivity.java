package com.diomun.learn.javademo.ui.activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;

import butterknife.BindView;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/3/13
 * @desc 图片加载页
 */
public class ImgLoadActivity extends BaseActivity {
    @BindView(R.id.iv_netLoad)
    AppCompatImageView ivNetLoad;

    private String imgUrl = "https://c-ssl.duitang.com/uploads/item/201708/04/20170804085229_Z4Y2a.jpeg";

    @Override
    public int initLayout() {
        return R.layout.activity_imgload;
    }

    @Override
    public void initView() {
        initLoading();
    }

    @Override
    public void initData() {
        new MyAsyncTask().execute(imgUrl);
    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
        /**
         * 第一个执行的方法
         * 执行时机：在执行实际的后台操作前，被UI 线程调用
         * 作用：可以在该方法中做一些准备工作，如在界面上显示一个进度条，或者一些控件的实例化，这个方法可以不用实现。
         *
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: 准备工作，等待动画");
            showLoading();
        }

        /**
         * 执行时机：在onPreExecute 方法执行后马上执行，该方法运行在后台线程中
         * 作用：主要负责执行那些很耗时的后台处理工作。可以调用 publishProgress方法来更新实时的任务进度。该方法是抽象方法，子类必须实现。
         *
         * @param strings
         * @return
         * @see android.os.AsyncTask#doInBackground(Object[])
         */
        @Override
        protected Bitmap doInBackground(String... strings) {
            return null;
        }

        /**
         * 执行时机：这个函数在doInBackground调用publishProgress时被调用后，UI 线程将调用这个方法.虽然此方法只有一个参数,但此参数是一个数组，可以用values[i]来调用
         * 作用：在界面上展示任务的进展情况，例如通过一个进度条进行展示。此实例中，该方法会被执行100次
         *
         * @see android.os.AsyncTask#onProgressUpdate(Object[])
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 执行时机：在doInBackground 执行完成后，将被UI 线程调用
         * 作用：后台的计算结果将通过该方法传递到UI 线程，并且在界面上展示给用户
         * result:上面doInBackground执行后的返回值，所以这里是"执行完毕"
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

        }
    }
}
