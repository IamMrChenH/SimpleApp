package com.example.chenhao.simpleapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.BaseActivity;
import com.example.chenhao.simpleapp.utils.Utils;

public class StreetLedActivity extends BaseActivity {
    private TextView mTitleText;
    private ImageView mLed;
    private Switch mAutowitch1, mOpenwitch2;

    private static boolean mAutoMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_led);
        initToolbar();
        initViews();


    }

    private void initViews() {
        mTitleText = findView(R.id.mTitle);
        mTitleText.setText("路灯控制");

        mLed = (ImageView) findViewById(R.id.ratingBar);
        mAutowitch1 = (Switch) findViewById(R.id.switch1);
        mOpenwitch2 = (Switch) findViewById(R.id.switch2);

        mAutowitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String statusStr = "当前为手动模式";
                if (isChecked)
                    statusStr = "当前为自动模式";
                Utils.showToast(statusStr);
                mAutoMode = isChecked;

            }
        });

        mOpenwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mAutoMode) {
                    Utils.showToast("必须先将路灯模式设置为手动");
                    mOpenwitch2.setChecked(!isChecked);
                    return;
                }

                if (isChecked) {
                    mLed.setImageResource(R.mipmap.roadlamp_on);
                    Animation animation = AnimationUtils.loadAnimation(StreetLedActivity.this, R.anim.street_led_anim);
                    mLed.startAnimation(animation);
                } else {
                    mLed.setImageResource(R.mipmap.roadlamp_off);
                    mLed.clearAnimation();

                }


            }
        });
    }


    private void initToolbar() {
        Toolbar mToolbar = findView(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
