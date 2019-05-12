package com.bw.test2.model;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/12
 * Time: 13:57
 */
public class ModelImpl implements Model {
    @Override
    public void requestData(String uri, String phone, String pwd, CallBack callBack) {
        new Mytask(uri,phone,pwd,callBack).execute(uri);
    }
    class Mytask extends AsyncTask<String,Void,String>{
        private String uri;
        private String phone;
        private String pwd;
        private CallBack callBack;
        public Mytask(String uri, String phone, String pwd, CallBack callBack) {
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
                OutputStream outputStream = urlConnection.getOutputStream();
                String params = "phone="+phone+"&pwd="+pwd;
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
