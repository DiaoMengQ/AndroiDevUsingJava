package com.diomun.learn.javademo.ui.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/30
 * @desc 为所有 iconfont 设定已 setTypeface 的自定义view
 */
public class IconView extends AppCompatTextView {
    public IconView(Context context) {
        super(context);
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"iconfont.ttf");
        setTypeface(typeface);
    }
}
