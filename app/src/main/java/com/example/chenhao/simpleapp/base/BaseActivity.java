package com.example.chenhao.simpleapp.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by chenhao on 17/7/13.
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * The M base handler.  通用的Handle
     */
    public Handler mBaseHandler = new Handler();

    /**
     * Find view t.
     * 不用强转View 的获取方法
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }


    /**
     * Show msg dialog.  通用的消息对话框
     *
     * @param msg the msg
     */
    public void showMsgDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setTitle("温馨提示")
                .setCancelable(false)
                .setPositiveButton("确定", null)
                .show();

    }

    /**
     *  不保存状态
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }
}
