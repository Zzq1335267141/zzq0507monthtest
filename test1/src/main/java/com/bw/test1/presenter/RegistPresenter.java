package com.bw.test1.presenter;

import com.bw.test1.MainActivity;
import com.bw.test1.model.IModel;
import com.bw.test1.model.LoginImpl;
import com.bw.test1.model.RegisterImpl;
import com.bw.test1.view.IView;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/10
 * Time: 17:19
 */
public class RegistPresenter implements IPresenter {
    private IModel iModel;
    private IView iView;

    public RegistPresenter(IView iView) {
        iModel = new RegisterImpl();
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
