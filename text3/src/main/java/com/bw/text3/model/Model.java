package com.bw.text3.model;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/12
 * Time: 19:00
 */
public interface Model {
    void getRequestData(String uri,String phone,String pwd,CallBack callBack);
    public interface CallBack{
        void setRequestData(String info);
    }
}
