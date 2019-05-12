package com.bw.test1.model;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/10
 * Time: 15:31
 */
public interface IModel {
    //请求数据接口，并返回数据，接口回调方法
    void requestData(String username,String pwd,Callback callback);
    //这个接口是用来保存数据一个连接的桥梁
    interface Callback{
        void setData(String info);
    }
}
