package com.bw.test1.presenter;

import com.bw.test1.LoginActivity;
import com.bw.test1.model.IModel;
import com.bw.test1.model.LoginImpl;
import com.bw.test1.model.RegisterImpl;
import com.bw.test1.view.IView;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/11
 * Time: 9:12
 */
public class LoginPresenter implements IPresenter {
    private IModel iModel;
    private IView iView;

    public LoginPresenter(IView iView) {
        iModel = new LoginImpl();
        this.iView = iView;
    }


    @Override
    public void onAttch(IView iView) {
        this.iView = iView;
    }

    @Override
    public void startRequest(String userName, String pwd) {
        iModel.requestData(userName, pwd, new IModel.Callback() {
            @Override
            public void setData(String info) {
                iView.getResponse(info);
            }
        });
    }
    //防止
    @Override
    public void onDeattch() {
        if(iModel !=null){
            iModel=null;
        }
        if(iView!=null){
            iView= null;
        }
    }
}
