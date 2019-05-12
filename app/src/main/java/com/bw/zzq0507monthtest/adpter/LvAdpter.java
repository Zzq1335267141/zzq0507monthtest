package com.bw.zzq0507monthtest.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.zzq0507monthtest.R;
import com.bw.zzq0507monthtest.bean.DataBean;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 适配器
 * Created by Android Studio.
 * User: 张泽强
 * Date: 2019/5/7
 * Time: 10:45
 */
public class LvAdpter extends BaseAdapter {
    private List<DataBean> list;
    private Context context;

    public LvAdpter(List<DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case 0:
                ViewHolder1 viewHolder1;
                if(convertView==null){
                    viewHolder1 = new ViewHolder1();
                    convertView=View.inflate(context, R.layout.lv_left,null);
                    viewHolder1.img= convertView.findViewById(R.id.l_img);
                    viewHolder1.tv1= convertView.findViewById(R.id.l_tv1);
                    viewHolder1.tv2= convertView.findViewById(R.id.l_tv2);
                    convertView.setTag(viewHolder1);
                }else{
                    viewHolder1 = (ViewHolder1) convertView.getTag();
                }
                Glide.with(context).load(list.get(position).getImageUrl()).into(viewHolder1.img);
                viewHolder1.tv1.setText("电影名:"+list.get(position).getName());
                viewHolder1.tv2.setText("商品id:"+list.get(position).getId());
                break;
            case 1:
                ViewHolder2 viewHolder2;
                if(convertView==null){
                    viewHolder2 = new ViewHolder2();
                    convertView=View.inflate(context, R.layout.lv_right,null);
                    viewHolder2.img= convertView.findViewById(R.id.r_img);
                    viewHolder2.tv1= convertView.findViewById(R.id.r_tv1);
                    viewHolder2.tv2= convertView.findViewById(R.id.r_tv2);
                    convertView.setTag(viewHolder2);
                }else{
                    viewHolder2 = (ViewHolder2) convertView.getTag();
                }
                Glide.with(context).load(list.get(position).getImageUrl()).into(viewHolder2.img);
                viewHolder2.tv1.setText("电影名:"+list.get(position).getName());
                viewHolder2.tv2.setText("商品id:"+list.get(position).getId());
                break;
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    private static class ViewHolder1 {
        ImageView img;
        TextView tv1;
        TextView tv2;
    }
    private static class ViewHolder2 {
        ImageView img;
        TextView tv1;
        TextView tv2;
    }
}
