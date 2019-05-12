package com.bw.text3.presenter;

import com.bw.text3.MainActivity;
import com.bw.text3.model.Model;
import com.bw.text3.model.ModelImpl;
import com.bw.text3.view.IView;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/12
 * Time: 19:36
 */
public class PresenterImpl implements Presenter {
    private IView iView;
    private Model model;

    public PresenterImpl(IView iView) {
        model = new ModelImpl();
        this.iView = iView;
    }

    @Override
    public void onTach(IView iView) {
        this.iView = iView;
    }

    @Override
    public void getrequest(String uri, String phone, String pwd) {
        model.getRequestData(uri, phone, pwd, new Model.CallBack() {
            @Override
            public void setRequestData(String info) {
                iView.setResponseData(info);
            }
        });
    }

    @Override
    public void deTach() {
        if(model!=null){
            model=null;
        }
        if(iView!=null){
            iView = null;
        }
    }
}
