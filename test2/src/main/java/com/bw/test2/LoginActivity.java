package com.bw.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.test2.presenter.PresenterImpl;
import com.bw.test2.view.AView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AView {
    private EditText et1;
    private EditText et2;
    private Button btn;
    private PresenterImpl impl;
    String url = "http://172.17.8.100/small/user/v1/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        impl = new PresenterImpl(this);
    }
    public void findViewById(){
        et1 = (EditText) findViewById(R.id.l_et1);
        et2 = (EditText) findViewById(R.id.l_et2);
        btn = (Button) findViewById(R.id.l_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.l_btn:
                String e1 = et1.getText().toString();
                String e2 = et2.getText().toString();
                if(TextUtils.isEmpty(e1)&&TextUtils.isEmpty(e2)){
                    Toast.makeText(this, "参数有误", Toast.LENGTH_SHORT).show();
                }else{
                    impl.startRequest(url,e1,e2);
                }
                break;
        }
    }

    @Override
    public void getResponse(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
