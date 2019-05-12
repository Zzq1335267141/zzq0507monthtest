package com.bw.test2.presenter;

import com.bw.test2.MainActivity;
import com.bw.test2.model.Model;
import com.bw.test2.model.ModelImpl;
import com.bw.test2.view.AView;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/12
 * Time: 14:08
 */
public class PresenterImpl implements Presenter{
    private Model model;
    private AView aView;

    public PresenterImpl(AView aView) {
        model = new ModelImpl();
        this.aView = aView;
    }

    @Override
    public void onTach(AView aView) {
        this.aView = aView;
    }

    @Override
    public void startRequest(String uri, String phone, String pwd) {
        model.requestData(uri, phone, pwd, new Model.CallBack() {
            @Override
            public void setData(String info) {
                aView.getResponse(info);
            }
        });
    }
    //防止
    @Override
    public void deTach() {
        if(model!=null){
            model=null;
        }
        if(aView!=null){
            aView=null;
        }
    }
}
