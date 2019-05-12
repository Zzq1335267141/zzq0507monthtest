package com.bw.zzq0507monthtest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bw.zzq0507monthtest.adpter.LvAdpter;
import com.bw.zzq0507monthtest.base.BaseActivity;
import com.bw.zzq0507monthtest.bean.DataBean;
import com.bw.zzq0507monthtest.utils.CallBack;
import com.bw.zzq0507monthtest.utils.HttpUtil;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现bn 侧拉 多条目
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/7
 * Time: 8:40
 */
public class MainActivity extends BaseActivity {

    private Banner bn;
    private PullToRefreshListView ptrsv;
    private String path = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private String d_path = "http://172.17.8.100/movieApi/movie/v1/findHotMovieList?";
    private List<String> strings = new ArrayList<>();
    int page = 1;
    private List<DataBean> beanList = new ArrayList<>();
    private List<DataBean> adpterList = new ArrayList<>();
    private LvAdpter lvAdpter;

    //视图
    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }
    //资源id
    @Override
    public void findViewById() {
        bn = (Banner) findViewById(R.id.m_bn);
        ptrsv = (PullToRefreshListView) findViewById(R.id.m_ptrlv);
    }
    //数据
    @Override
    public void initData() {
        setBn();
        setpull();
    }
    //监听
    @Override
    public void initListener() {

    }
    //bn设置
    public void setBn(){
        HttpUtil httpUtil = HttpUtil.getInstance();
        httpUtil.getAsyncTask(path);
        httpUtil.getcallback(new CallBack() {
            @Override
            public void getData(String json) {
                //原生解析
                try {
                    //进入
                    JSONObject jsonObject = new JSONObject(json);
                    //进入第一层
                    JSONArray result = jsonObject.getJSONArray("result");
                    //进入集合
                    for(int i=0;i<result.length();i++){
                        String imageUrl = result.getJSONObject(i).getString("imageUrl");
                        strings.add(imageUrl);
                    }
                    bn.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load(path).into(imageView);
                        }
                    });
                    bn.setImages(strings);
                    bn.isAutoPlay(true);
                    bn.setDelayTime(3000);
                    bn.start();
                    setDataForm();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //上拉下拉
    public void setpull(){
        //设置模式
        ptrsv.setMode(PullToRefreshBase.Mode.BOTH);
        //下拉
        ILoadingLayout start = ptrsv.getLoadingLayoutProxy(true, false);
        start.setPullLabel("下拉刷新");
        start.setReleaseLabel("正在刷新");
        start.setRefreshingLabel("松开刷新");
        ILoadingLayout end = ptrsv.getLoadingLayoutProxy(true, false);
        end.setPullLabel("上拉加载");
        end.setReleaseLabel("正在加载");
        end.setRefreshingLabel("松开加载");
        //监听
        ptrsv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page=1;
                setDataForm();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page++;
                setDataForm();
            }
        });
        //为lv设置适配
        lvAdpter = new LvAdpter(adpterList, MainActivity.this);
        ptrsv.setAdapter(lvAdpter);
    }
    //数据
    public void setDataForm(){
        HttpUtil httpUtil = HttpUtil.getInstance();
        String params = d_path +"page="+page+"&count=5";
        httpUtil.getAsyncTask(params);
        httpUtil.getcallback(new CallBack() {
            @Override
            public void getData(String json) {
                if(page==1){
                    beanList.clear();
                }
                Log.i("TSA",json);
                //原生解析
                try {
                    //进入第一层
                    JSONObject jsonObject = new JSONObject(json);
                    //进入集合
                    JSONArray result = jsonObject.getJSONArray("result");
                    for(int i=0;i<result.length();i++){
                        String id = result.getJSONObject(i).getString("id");
                        String imageUrl = result.getJSONObject(i).getString("imageUrl");
                        String name = result.getJSONObject(i).getString("name");
                        DataBean dataBean = new DataBean(id, imageUrl, name);
                        beanList.add(dataBean);
                    }
                    Log.i("TSA",beanList.size()+"");
                    if(page==1){
                        adpterList.clear();
                    }
                    adpterList.addAll(beanList);
                    //刷新适配器
                    lvAdpter.notifyDataSetChanged();
                    //停止刷新
                    ptrsv.onRefreshComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //跳转
    public void tern(View view) {
        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        startActivity(intent);
    }

}
