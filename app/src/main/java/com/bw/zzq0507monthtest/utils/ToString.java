package com.bw.zzq0507monthtest.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 读写
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/7
 * Time: 9:59
 */
public class ToString {
    static ToString toString = new ToString();
    public static ToString getInstance(){
        return toString;
    }
    public String Styeam(InputStream inputStream) throws Exception {
        //设置长度
        int len = -1;
        //设置读取速率
        byte[] bytes = new byte[1024];
        //存入
        StringBuffer buffer = new StringBuffer();
        //循环读写
        while ((len=inputStream.read(bytes))!=-1){
            String s = new String(bytes, 0, len);
            buffer.append(s);
        }
        return buffer.toString();
    }
}
