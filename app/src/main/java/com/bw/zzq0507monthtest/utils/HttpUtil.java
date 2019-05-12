package com.bw.zzq0507monthtest.utils;

import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 工具类
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/7
 * Time: 9:53
 */
public class HttpUtil {
    //饿汉式
    static HttpUtil httpUtil = new HttpUtil();
    private CallBack callback;

    public static HttpUtil getInstance(){
        return httpUtil;
    }
    //1.get请求
    public String getUrl(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //设置请求方式
        urlConnection.setRequestMethod("GET");
        //设置请求时间
        urlConnection.setReadTimeout(5000);
        //设置超时连击
        urlConnection.setConnectTimeout(5000);
        //读写
        if(urlConnection.getResponseCode()==200){
            InputStream inputStream = urlConnection.getInputStream();
            //得到实例
            ToString toString = ToString.getInstance();
            String styeam = toString.Styeam(inputStream);
            return styeam;
        }
        return null;
    }
    //2.异步请求
    public void getAsyncTask(String path){
        AsyncTask<String,Void,String> asyncTask = new AsyncTask<String, Void, String>() {
            String url = "";
            @Override
            protected String doInBackground(String... strings) {
                try {
                    url = getUrl(strings[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return url;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                callback.getData(s);
            }
        }.execute(path);
    }
    //接口回调
    public void getcallback(CallBack callBack){
        this.callback = callBack;
    }
}
