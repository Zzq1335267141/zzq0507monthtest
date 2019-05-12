package com.bw.test2.model;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/12
 * Time: 13:54
 */
public interface Model {
    void requestData(String uri,String phone,String pwd,CallBack callBack);
    public interface CallBack{
        void setData(String info);
    }
}
