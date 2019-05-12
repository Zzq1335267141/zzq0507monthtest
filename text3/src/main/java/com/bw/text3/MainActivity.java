package com.bw.text3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.text3.presenter.PresenterImpl;
import com.bw.text3.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView {
    String url = "http://172.17.8.100/small/user/v1/register";
    String url1 = "http://172.17.8.100/small/user/v1/login";
    private EditText et1;
    private EditText et2;
    private Button btn;
    private PresenterImpl presenter;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewBy();
        presenter = new PresenterImpl(this);
    }
    public void findViewBy(){
        et1 = (EditText) findViewById(R.id.m_et1);
        et2 = (EditText) findViewById(R.id.m_et2);
        btn = (Button) findViewById(R.id.m_btn);
        btn1 = (Button) findViewById(R.id.m_btn1);
        btn1.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.m_btn:
                String e1 = et1.getText().toString();
                String e2 = et2.getText().toString();
            if(TextUtils.isEmpty(e1)||TextUtils.isEmpty(e2)){
                Toast.makeText(this, "参数错误", Toast.LENGTH_SHORT).show();
            }else{
                presenter.getrequest(url,e1,e2);
            }
            break;
            case R.id.m_btn1:
                String ee1 = et1.getText().toString();
                String ee2 = et2.getText().toString();
                if(TextUtils.isEmpty(ee1)||TextUtils.isEmpty(ee2)){
                    Toast.makeText(this, "参数错误", Toast.LENGTH_SHORT).show();
                }else{
                    presenter.getrequest(url1,ee1,ee2);
                    
                }
                break;
        }
    }
    //解析
    @Override
    public void setResponseData(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
