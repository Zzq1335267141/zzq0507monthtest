package com.bw.text3.model;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/12
 * Time: 19:04
 */
public class ModelImpl implements Model {
    @Override
    public void getRequestData(String uri, String phone, String pwd, CallBack callBack) {
        new MyTask(uri,phone,pwd,callBack).execute(uri);
    }
    class MyTask extends AsyncTask<String,Void,String>{
        private String uri;
        private String phone;
        private String pwd;
        private CallBack callBack;
        public MyTask(String uri, String phone, String pwd, CallBack callBack) {
            this.uri = uri;
            this.phone = phone;
            this.pwd = pwd;
            this.callBack = callBack;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(uri);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                String params = "phone="+phone+"&pwd="+pwd;
                OutputStream outputStream = urlConnection.getOutputStream();
                outputStream.write(params.getBytes());
                outputStream.flush();
                outputStream.close();
                Log.i("TAG",urlConnection.getResponseCode()+"");
                if(urlConnection.getResponseCode()==200){
                    String s = ToString(urlConnection.getInputStream());
                    Log.i("TAG",s);
                    return s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            callBack.setRequestData(s);
        }
    }
    public String ToString(InputStream inputStream) throws Exception {
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
