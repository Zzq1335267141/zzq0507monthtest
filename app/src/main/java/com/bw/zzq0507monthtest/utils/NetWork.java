package com.bw.zzq0507monthtest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断网络
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/7
 * Time: 10:09
 */
public class NetWork {
    public static boolean getNetWork(Context context){
        //得到系统服务
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //得到网络
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //判断网络
        if(networkInfo!=null && networkInfo.isConnected()){
            return true;
        }else{
            return false;
        }
    }
}
