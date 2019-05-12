package com.bw.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.test1.presenter.RegistPresenter;
import com.bw.test1.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {

    private EditText et1;
    private EditText et2;
    private Button btn;
    private RegistPresenter registPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.m_et1);
        et2 = (EditText) findViewById(R.id.m_et2);
        btn = (Button) findViewById(R.id.m_btn);
        btn.setOnClickListener(this);
        registPresenter = new RegistPresenter(this);
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
                    registPresenter.startRequest(e1,e2);

                }
        }
    }

    @Override
    public void getResponse(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();


    }
}
