package com.example.chenhao.simpleapp.base;

import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by chenhao on 17/7/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Handler mBaseHandler = new Handler();

    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }


    public void showMsgDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setTitle("温馨提示")
                .setCancelable(false)
                .setPositiveButton("确定", null)
                .show();

    }

}
