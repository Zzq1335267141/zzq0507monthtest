package com.bw.test1.presenter;

import com.bw.test1.view.IView;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/10
 * Time: 17:16
 */
public interface IPresenter {
    //传入V层对象
    void onAttch(IView iView);
    void startRequest(String userName,String pwd);
    void onDeattch();
}
