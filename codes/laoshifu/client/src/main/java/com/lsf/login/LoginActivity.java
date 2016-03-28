package com.lsf.login;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
//import com.aus.model.Account;
import com.lsf.util.HttpCallbackListener;
import com.lsf.util.HttpCallbackListenerImpl;
import com.lsf.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements HttpCallbackListener{


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Log.d("LoginActivity", "the return message from server" + msg.obj);

        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText editTextPhone = (EditText)findViewById(R.id.user_phone);
        EditText editTextPassword = (EditText)findViewById(R.id.user_password);
        final String userPhone = editTextPhone.getText().toString();
        final String userPwd= editTextPassword.getText().toString();


        TextView registerLink = (TextView)findViewById(R.id.registerLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button loginBtn = (Button) findViewById(R.id.button_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Account account = new Account();
                //account.setAccountNo("123");
                //account.setPassword("abc");
               // String accountInfo = JSON.toJSONString(account);
                String accountInfo ="accountNo=123";
                HttpUtils.sendPostRequest("https://145q8w2034.iok.la:27044/webServer/accountController?login",accountInfo,LoginActivity.this);
            }
        });
    }

    @Override
    public void onFinish(String response) {
        Message msg = Message.obtain();
        msg.obj = response;
        handler.sendMessage(msg);
    }

    @Override
    public void onError(Exception e) {
        Message msg = Message.obtain();
        Log.e("LoginActivity", e.toString(), e);
        msg.obj = e.toString();
        handler.sendMessage(msg);
    }

    /*class myHandler extends  Handler{
        @Override
        public void handleMessage(Message msg){
            System.out.println("*************"+ msg.obj);
            Toast.makeText(LoginActivity.this, "已发送request"+msg.obj,Toast.LENGTH_SHORT);
        }
    }

   private class WorkThread implements Runnable{
        Handler mHandler;
        String result;
        public WorkThread(Handler handler){
            super();
            this.mHandler= handler;
        }

        @Override
        public void run() {
            Looper.prepare();
            try {
                List params = new ArrayList();
                params.add(new BasicNameValuePair("accountNo", "123"));
                params.add(new BasicNameValuePair("password", "123"));
                result = HttpUtils.sendPostRequest("https://145q8w2034.iok.la:27044/webServer/accountController?login", "POST", params);
            }catch(Exception e){
                result = e.toString();
            }
            Message msg = Message.obtain();
            msg.obj = result;
            mHandler.sendMessage(msg);
            Looper.loop();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
