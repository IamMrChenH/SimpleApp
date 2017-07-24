package com.example.chenhao.simpleapp.base;

import android.os.Handler;
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


}
