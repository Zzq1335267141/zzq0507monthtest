package com.bw.zzq0507monthtest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Activty抽积类
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/7
 * Time: 8:59
 */
public abstract class BaseActivity extends AppCompatActivity {
    //视图
    public abstract int initLayout();
    //资源id
    public abstract void findViewById();
    //数据
    public abstract void initData();
    //监听
    public abstract void initListener();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        findViewById();
        initData();
        initListener();
    }
}
