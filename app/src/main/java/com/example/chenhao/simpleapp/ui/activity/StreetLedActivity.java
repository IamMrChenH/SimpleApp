package com.example.chenhao.simpleapp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;
import com.example.chenhao.simpleapp.utils.Utils;

import java.util.Date;

public class StreetLedActivity extends SuperBaseActivity implements Runnable {
    private TextView mTitleText;
    private ImageView mLed;
    private Switch mAutowitch1, mOpenwitch2;

    private static boolean mAutoMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_led);
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
//                autoLed();

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


    public void autoLed() {
        if (!mAutoMode) return;

        Date date = new Date(System.currentTimeMillis());
        int hours = date.getHours();
        Log.e("233", "autoLed: " + hours);
        try {
            if (hours > 19 || hours < 7) {
                mLed.setImageResource(R.mipmap.roadlamp_on);
                Animation animation = AnimationUtils.loadAnimation(StreetLedActivity.this, R.anim.street_led_anim);
                mLed.startAnimation(animation);
                mOpenwitch2.setChecked(true);

            } else {
                mLed.setImageResource(R.mipmap.roadlamp_off);
                mLed.clearAnimation();
                mOpenwitch2.setChecked(false);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            mBaseHandler.postDelayed(this, 1 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getToolbarTitle() {
        return "路灯控制";
    }

    @Override
    public void run() {
        autoLed();
        mBaseHandler.postDelayed(this, 5 * 1000);
    }
}
