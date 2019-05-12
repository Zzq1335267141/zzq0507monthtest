package com.bw.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.test2.model.Model;
import com.bw.test2.model.ModelImpl;
import com.bw.test2.presenter.Presenter;
import com.bw.test2.presenter.PresenterImpl;
import com.bw.test2.view.AView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AView {
    String url = "http://172.17.8.100/small/user/v1/register";
    private EditText et1;
    private EditText et2;
    private Button btn;
    private PresenterImpl impl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        impl = new PresenterImpl(this);
    }
    public void findViewById(){
        et1 = (EditText) findViewById(R.id.m_et1);
        et2 = (EditText) findViewById(R.id.m_et2);
        btn = (Button) findViewById(R.id.m_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m_btn:

                String e1 = et1.getText().toString();
                String e2 = et2.getText().toString();
                Log.i("TAG",e1+" "+e2);
                if(TextUtils.isEmpty(e1)|| TextUtils.isEmpty(e2)){
                    Toast.makeText(this, "参数有误", Toast.LENGTH_SHORT).show();
                }else{
                    impl.startRequest(url,e1,e2);
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
        }
    }
    @Override
    public void getResponse(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();


    }


}
