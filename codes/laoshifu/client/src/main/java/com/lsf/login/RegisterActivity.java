package com.lsf.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import cn.smssdk.utils.SMSLog;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText phoneNum, verifyCode;
    private Button getVerifyCodeBtn, confirmBtn;
    private static String APPKEY = "f6ca1b0b0270";
    private static String APPSECRET = "4c2f5b2b09ff8a2206348c2ac1f2ed6d";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phoneNum = (EditText)findViewById(R.id.user_phone);
        verifyCode = (EditText)findViewById(R.id.verifyCode);

        getVerifyCodeBtn = (Button)findViewById(R.id.getVerifyCode);
        confirmBtn = (Button)findViewById(R.id.confirm);

        getVerifyCodeBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        SMSSDK.initSDK(this, APPKEY, APPSECRET, true);

        EventHandler eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data){
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);

    }

    Handler mHandler = new Handler(){

        public void handleMessage(Message msg){
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;

            if(result == SMSSDK.RESULT_COMPLETE){
                System.out.println("------result" + event);
                if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                    Toast.makeText(getApplicationContext(),"提交验证码成功",Toast.LENGTH_SHORT).show();
                }else if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    Toast.makeText(getApplicationContext(),"验证码已经发送",Toast.LENGTH_SHORT).show();
                }

            }else{
                try{
                    ((Throwable)data).printStackTrace();
                    Throwable throwable = (Throwable)data;
                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    if(!TextUtils.isEmpty(des)){
                        Toast.makeText(getApplicationContext(),des,Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (Exception e){
                    SMSLog.getInstance().w(e);
                }

            }

        }
    };

    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(phoneNum.getText().toString()))
        {
            Toast.makeText(this,"电话号码不能为空",Toast.LENGTH_SHORT).show();
        }
        switch (v.getId()){
            case R.id.getVerifyCode:
                SMSSDK.getVerificationCode("86",phoneNum.getText().toString());
                new CountDownTimer(10000,1000){
                    public void onTick(long millisUntilFinished){
                        getVerifyCodeBtn.setText(millisUntilFinished/1000 + "s后重新发送");
                        getVerifyCodeBtn.setEnabled(false);

                    }
                    public void onFinish(){
                        getVerifyCodeBtn.setText("重新获取验证码");
                        getVerifyCodeBtn.setEnabled(true);
                    }
                }.start();
                break;
            case R.id.confirm:
                if(TextUtils.isEmpty(verifyCode.getText().toString())){
                    Toast.makeText(this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                }
                SMSSDK.submitVerificationCode("86",phoneNum.getText().toString(),verifyCode.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);

        MenuItem seachItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(seachItem);

        //ToDo: Configure the search info and add any event listeners...


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_favorite:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
