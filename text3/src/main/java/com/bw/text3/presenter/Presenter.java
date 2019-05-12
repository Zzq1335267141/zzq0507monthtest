package com.bw.text3.presenter;

import com.bw.text3.view.IView;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/12
 * Time: 19:34
 */
public interface Presenter {
    void onTach(IView iView);
    void getrequest(String uri,String phone,String pwd);
    void deTach();
}
