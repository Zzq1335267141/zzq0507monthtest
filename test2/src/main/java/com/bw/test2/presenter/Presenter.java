package com.bw.test2.presenter;

import com.bw.test2.view.AView;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/12
 * Time: 14:05
 */
public interface Presenter {
    //交互
    void onTach(AView aView);
    //参数
    void startRequest(String uri,String phone,String pwd);
    //防止
    void deTach();
}
