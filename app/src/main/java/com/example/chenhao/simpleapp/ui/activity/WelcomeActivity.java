package com.example.chenhao.simpleapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.BaseActivity;

/**
 * The type Welcome activity.
 * 欢迎界面
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mBaseHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        }, 600);

    }
}
