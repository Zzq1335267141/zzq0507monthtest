package com.bw.zzq0507monthtest.bean;

/**
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/7
 * Time: 10:33
 */
public class DataBean {
    private String id;
    private String imageUrl;
    private String name;

    public DataBean(String id, String imageUrl, String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
