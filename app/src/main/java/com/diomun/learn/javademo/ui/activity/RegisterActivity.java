package com.diomun.learn.javademo.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.diomun.learn.javademo.R;
import com.diomun.learn.javademo.base.BaseActivity;
import com.diomun.learn.javademo.ui.customview.IconView;
import com.diomun.learn.javademo.ui.customview.RoundImageView;
import com.diomun.learn.javademo.util.Img2bitmap;
import com.diomun.learn.javademo.util.fileUtil;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/23
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.ic_back)
    IconView icBack;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.iv_headImg)
    RoundImageView ivHeadImg;
    @BindView(R.id.layout_head)
    ConstraintLayout layoutHead;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd_valid)
    EditText etPwdValid;

    @Override
    public int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    /**
     * 获取处理后返回系统文件的链接
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                // TODO: 保存文件失败，源文件路径找不到文件
                Uri resultUri = result.getUri();
                Log.d(TAG, "onActivityResult: 源文件位置 " + resultUri);
                Uri extenalUri = Uri.fromFile(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
                Log.d(TAG, "onActivityResult: 图片保存位置 " + extenalUri);
                try {
                    fileUtil.copyFile(resultUri.toString(), extenalUri.toString());
                    Toast.makeText(mContext, "成功保存图片", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.d(TAG, "onActivityResult: 保存图片失败");
                    e.printStackTrace();
                }

                // 图片转bitmap
                Bitmap imgBitmap = Img2bitmap.decodeUri(mContext, resultUri, 300, 300);

                // 获取处理后照片的链接
                ivHeadImg.setImageBitmap(imgBitmap);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @OnClick({R.id.iv_headImg, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_headImg:
                // 调用选图裁切页
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .setFixAspectRatio(true)
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .start(mContext);
                break;
            case R.id.btn_register:
                Toast.makeText(mContext, "注册", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}
