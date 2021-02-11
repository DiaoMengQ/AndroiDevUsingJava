package com.diomun.learn.javademo.ui.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diomun.learn.javademo.R;

/**
 * Date:15/9/29
 * Time:15:22
 * Small improvements each day~
 */
public class CustomView extends LinearLayout {

    private int color1;
    private int color2;
    private int color3;
    private int color4;
    private int color5;

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        //  context, attrs, defStyleAttr
        this(context, attrs, 0);
    }

    @SuppressLint("SetTextI18n")
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //  attrs, defStyleAttr, defStyleRes
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CustomView, defStyleAttr, 0);

        color1 = a.getColor(R.styleable.CustomView_custom_color1,0xffE64A19);
        color2 = a.getColor(R.styleable.CustomView_custom_color2,0xffE64A19);
        color3 = a.getColor(R.styleable.CustomView_custom_color3,0xffE64A19);
        color4 = a.getColor(R.styleable.CustomView_custom_color4,0xffE64A19);
        color5 = a.getColor(R.styleable.CustomView_custom_color5,0xffE64A19);


        TextView textView1 = new TextView(context);
        textView1.setText("color1 "+ Integer.toHexString(color1));
        textView1.setTextColor(color1);
        addView(textView1);

        TextView textView2 = new TextView(context);
        textView2.setText("color2 "+ Integer.toHexString(color2));
        textView2.setTextColor(color2);
        addView(textView2);
        TextView textView3 = new TextView(context);
        textView3.setText("color3 "+ Integer.toHexString(color3));
        textView3.setTextColor(color3);
        addView(textView3);

        TextView textView4 = new TextView(context);
        textView4.setText("color4 "+ Integer.toHexString(color4));
        textView4.setTextColor(color4);
        addView(textView4);

        TextView textView5 = new TextView(context);
        textView5.setText("color5 "+ Integer.toHexString(color5));
        textView5.setTextColor(color5);
        addView(textView5);

        a.recycle();
    }
}
