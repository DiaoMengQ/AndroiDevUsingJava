package com.diomun.learn.androidevusingjava.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diomun.learn.androidevusingjava.BuildConfig;
import com.diomun.learn.androidevusingjava.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DiaoMengQ
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @BindView(R.id.tv_hello)
    TextView tvHello;
    @BindView(R.id.btn_toListView)
    Button btnToListView;
    private Button toLvBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (BuildConfig.DEBUG) {
            Log.d("MainActivity", "testlog");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @OnClick({R.id.btn_toListView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_toListView:
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}
