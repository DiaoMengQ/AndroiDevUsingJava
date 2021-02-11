package com.diomun.learn.javademo.ui.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.diomun.learn.javademo.R;

import java.util.Objects;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/30
 * @desc 自定义带底框线的EditText
 */
public class LineEditText extends AppCompatEditText implements TextWatcher, View.OnFocusChangeListener {
    private final String TAG = "LineEditText";
    private Paint mPaint;
    private int color;
    public static final int STATUS_FOCUSED = 1;
    public static final int STATUS_UNFOCUSED = 2;
    public static final int STATUS_ERROR = 3;
    private int status = 2;
    private Drawable del_btn;
    private Drawable del_btn_down;
    private int focusedDrawableId = R.drawable.user_select;
    private int unfocusedDrawableId = R.drawable.user; // 默认的
    private int errorDrawableId = R.drawable.user_error;
    Drawable left = null;
    private Context mContext;
    /**
     * 是否获取焦点，默认没有焦点
     */
    private boolean hasFocus = false;
    /**
     * 手指抬起时的X坐标
     */
    private int clickUpx = 0;

    public LineEditText(@NonNull Context context) {
        // super(context);
        // mContext = context;
        // init();
        this(context, null);
    }

    public LineEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public LineEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.lineEdittext, defStyleAttr, 0);

        focusedDrawableId = a.getResourceId(
                R.styleable.lineEdittext_drawableFocus, R.drawable.user_select);
        unfocusedDrawableId = a.getResourceId(
                R.styleable.lineEdittext_drawableUnFocus, R.drawable.user);
        errorDrawableId = a.getResourceId(
                R.styleable.lineEdittext_drawableError, R.drawable.user_error);
        a.recycle();
        init();
    }


    /**
     * 输入文字前
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // 对是否显示右侧“清空文本”图标进行判断
        if (TextUtils.isEmpty(s)) {
            // 如果为空，则不显示删除图标
            setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
        } else {
            // 如果非空，则要显示删除图标
            setCompoundDrawablesWithIntrinsicBounds(left, null, del_btn, null);
        }
    }

    /**
     * 正在输入文字
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // 对是否显示右侧“清空文本”图标进行判断
        if (hasFocus) {
            if (TextUtils.isEmpty(s)) {
                // 如果为空，则不显示删除图标
                setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
            } else {
                // 如果非空，则要显示删除图标
                setCompoundDrawablesWithIntrinsicBounds(left, null, del_btn, null);
            }
        }
    }

    /**
     * 输入文字后
     */
    @Override
    public void afterTextChanged(Editable s) {
        postInvalidate();
    }

    /**
     * 文本框焦点改变时
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // 同步获取焦点状态
        try {
            this.hasFocus = hasFocus;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);
        int x = this.getScrollX();
        int w = this.getMeasuredWidth();
        // 处理输入长度超过输入框的情况，使文本框向外延伸
        canvas.drawLine(0, this.getHeight() - 2, this.getWidth(),
                this.getHeight() - 2, mPaint);
    }

    private void init() {
        // 画笔
        mPaint = new Paint();
        mPaint.setStrokeWidth(3.0f);
        color = Color.parseColor("#bfbfbf");
        // 初始状态：无焦点
        setStatus(status);
        del_btn = mContext.getResources().getDrawable(R.drawable.del_btn_bg);
        del_btn_down = mContext.getResources().getDrawable(R.drawable.del_btn_bg_down);
        addListeners();
        setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
    }

    /**
     * 发生点击事件时
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 处理点击“清空按钮”时的事件，提高容错
        // 1. 判断是否有文字
        if (!TextUtils.isEmpty(Objects.requireNonNull(getText()).toString())) {
            // 2. 判断点击位置是否在“清空”按钮生效范围
            clickUpx = (int) event.getX();
            // Log.i(TAG, " width: " + getWidth() + " x：" + clickUpx + " paddingRight: " + getCompoundPaddingRight());
            // 当 点击的坐标 到 当前输入框右侧的距离 小于等于 getCompoundPaddingRight() 的距离时，则认为是点击了删除图标
            if ((getWidth() - clickUpx) <= getCompoundPaddingRight()) {
                // 3. 手指点击抬起后才触发“清空”
                if (del_btn != null && event.getAction() == MotionEvent.ACTION_UP) {
                    setText("");
                    setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                } else if (del_btn != null && event.getAction() == MotionEvent.ACTION_DOWN) {
                    setCompoundDrawablesWithIntrinsicBounds(left, null, del_btn_down, null);
                }
            }
        }

        return super.onTouchEvent(event);
    }

    // 输入框状态
    public void setStatus(int status) {
        this.status = status;
        Log.d(TAG, "setStatus: " + status);
        if (status == STATUS_ERROR) {
            try {
                left = getResources().getDrawable(errorDrawableId);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
            setColor(R.color.error);
        } else if (status == STATUS_FOCUSED) {
            try {
                left = getResources().getDrawable(focusedDrawableId);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
            setColor(R.color.colorAccent);
        } else {
            try {
                left = getResources().getDrawable(unfocusedDrawableId);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
            setColor(R.color.unFocus);
        }
        if (left != null) {
            // left.setBounds(0, 0, 30, 40);
            // this.setCompoundDrawables(left, null, null, null);
            setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
        }
        postInvalidate();
    }

    public void setLeftDrawable(int focusedDrawableId, int unfocusedDrawableId,
                                int errorDrawableId) {
        this.focusedDrawableId = focusedDrawableId;
        this.unfocusedDrawableId = unfocusedDrawableId;
        this.errorDrawableId = errorDrawableId;
        setStatus(status);
    }

    private void addListeners() {
        try {
            setOnFocusChangeListener(this);
            addTextChangedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        this.hasFocus = focused;
        if (focused) {
            setStatus(STATUS_FOCUSED);
        } else {
            setStatus(STATUS_UNFOCUSED);
            setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public void setColor(int color) {
        this.color = color;
        this.setTextColor(color);
        invalidate();
    }

}
