package com.bw.test1.model;

import android.os.AsyncTask;

import com.bw.test1.LoginActivity;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/11
 * Time: 8:53
 */
public class LoginImpl implements IModel{
    public static final String REQUEST_URL = "http://172.17.8.100/small/user/v1/login";



    @Override
    public void requestData(String username, String pwd, Callback callback) {
        new MyTask(username,pwd,callback).execute(REQUEST_URL);
    }
    class MyTask extends AsyncTask<String,Void,String> {
        private String mUserName;
        private String mPwd;
        private Callback callBack;
        public MyTask(String username, String pwd, Callback callback) {
            this.mUserName =username;
            this.mPwd=pwd;
            this.callBack=callback;
        }

        //异常的分类
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(REQUEST_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                OutputStream outputStream = urlConnection.getOutputStream();
                String params = "phone="+mUserName+"&pwd="+mPwd;
                outputStream.write(params.getBytes());
                outputStream.flush();
                outputStream.close();
                if(urlConnection.getResponseCode()==200){
                    String str = getStr(urlConnection.getInputStream());
                    return str;
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            callBack.setData(s);
        }
    }
    public String getStr(InputStream inputStream) throws Exception {
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len=inputStream.read(bytes))!=-1){
            String s = new String(bytes, 0, len);
            buffer.append(s);
        }
        return buffer.toString();


    }
}
