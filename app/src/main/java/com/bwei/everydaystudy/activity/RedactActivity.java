package com.bwei.everydaystudy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.everydaystudy.R;
import com.bwei.everydaystudy.interfaces.OnItemSelectedListener;
import com.bwei.everydaystudy.utils.DialogUtils;
import com.tencent.open.utils.HttpUtils;


import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static com.bwei.everydaystudy.R.id.imageView;


public class RedactActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView changePic_tv;
    private int width;
    private OnItemSelectedListener onIllegalListener;
    private File file;

    private static final int OPEN_CAMERA = 100;
    private static final int OPEN_GALLERY = 101;
    private static final int CROP = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact);
        //初始化控件
        initView();

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        // 屏幕宽度（像素）
        width = metric.widthPixels;
    }

    private void initView() {
        //跟换头像点击事件
        changePic_tv = (TextView) findViewById(R.id.changePic_tv);

        changePic_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.changePic_tv:
                showDiaLog();
                break;
        }
    }

    /**
     * 弹出框
     */
    private void showDiaLog() {

        onIllegalListener = new OnItemSelectedListener() {
            @Override
            public void getSelectedItem(String content) {
                if (content.equals("相册")){
                    // 打开相册
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, OPEN_GALLERY);

                }else if (content.equals("拍照")){
                    file = new File(Environment.getExternalStorageDirectory(),
                            System.currentTimeMillis() + ".png");
                    // 隐式意图打开系统界面 --要求回传
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 存到什么位置
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, OPEN_CAMERA);
                }
            }
        };
        DialogUtils.showItemSelectDialog(RedactActivity.this,width,onIllegalListener,"相册","拍照");
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_CAMERA) {
            // 图片怎么取出
            // imageView.setImageURI(Uri.fromFile(file));
            crop(Uri.fromFile(file));
        } else if (requestCode == OPEN_GALLERY) {
            // 相册应用通过putData设置的图片的uri，所以我们这样拿
            Uri uri = data.getData();
            // imageView.setImageURI(uri);
            crop(uri);
        } else {
            if (requestCode == CROP) {
                // 直接拿到一张图片
                final Bitmap bitmap = data.getParcelableExtra("data");
                File picFile = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".png");
                // 把bitmap放置到文件中
                // format 格式
                // quality 质量
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(
                            picFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 上传服务器
//                HttpUtils httpUtils = new HttpUtils();
                // 必须post get 1k
                // 请求方式 请求地址 请求参数 回调
                RequestParams params = new RequestParams("http://169.254.239.3:8080/imageupload/servlet/UploadServlet");
                params.addBodyParameter("files", picFile);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(RedactActivity.this, s, Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Toast.makeText(RedactActivity.this, "上传失败", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
              /*  httpUtils
                        .send(HttpMethod.POST,
                                "http://169.254.161.66:8080/imageupload/servlet/UploadServlet",
                                params, new RequestCallBack<String>() {

                                    @Override
                                    public void onFailure(HttpException arg0,
                                                          String arg1) {
                                        Toast.makeText(MainActivity.this, "上传失败", 0)
                                                .show();

                                    }

                                    @Override
                                    public void onSuccess(ResponseInfo<String> arg0) {
                                        Toast.makeText(MainActivity.this, "上传成功", 0)
                                                .show();

                                        imageView.setImageBitmap(bitmap);

                                    }
                                });*/
//			imageView.setImageBitmap(bitmap);
            }
        }
    }

    // 手机自带裁剪功能--调用系统裁剪的意图
    public void crop(Uri uri) {
        // 定义图片裁剪意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置是否裁剪
        intent.putExtra("crop", "true");
        // 裁剪框的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 设置输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 设置图片格式
        intent.putExtra("outputFormat", "JPEG");
        // 是否返回数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP);
    }

}
