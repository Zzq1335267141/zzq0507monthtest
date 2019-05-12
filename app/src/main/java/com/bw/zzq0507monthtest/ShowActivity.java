package com.bw.zzq0507monthtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.BaseAdapter;

import com.bw.zzq0507monthtest.base.BaseActivity;
/**
 * 网页
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/7
 * Time: 11：15
 */
public class ShowActivity extends BaseActivity {

    private WebView wv;
    private String path = "https://baike.baidu.com/item/%E7%94%B5%E5%BD%B1/31689";
    //视图
    @Override
    public int initLayout() {
        return R.layout.activity_show;
    }
    //资源id
    @Override
    public void findViewById() {
        wv = (WebView) findViewById(R.id.s_wv);
    }
    //数据
    @Override
    public void initData() {
        wv.loadUrl(path);
    }
    //监听
    @Override
    public void initListener() {

    }
    public void back(View view) {
        Intent intent = new Intent(ShowActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
