package com.example.chenhao.simpleapp.base;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.chenhao.simpleapp.R;

/**
 * Created by IamMr on 2017/7/17.
 */
public abstract class SuperBaseActivity extends BaseActivity {
    /**
     * The M toolbar.
     */
    protected Toolbar mToolbar;

    @Override
    protected void onStart() {
        super.onStart();
        try {
            initToolbar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets toolbar title.
     *
     * @return the toolbar title
     */
    public abstract String getToolbarTitle();


    /**
     * Init toolbar.
     */
    protected void initToolbar() {
        ((TextView) findView(R.id.mTitle)).setText(getToolbarTitle());
        mToolbar = findView(R.id.mToolbar);
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


    @Override
    protected void onPause() {
        super.onPause();
        try {
            mBaseHandler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
        }
    }


    /**
     * Add animation.
     *
     * @param view the view
     */
    public void addAnimation(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1200);
        alphaAnimation.setFillAfter(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -50, 0);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(alphaAnimation);
        set.addAnimation(translateAnimation);

        view.setAnimation(set);
    }

    /**
     * Split and string string.
     *
     * @param strings the strings
     * @return the string
     */
    public String splitAndString(String[] strings) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            buffer.append(strings[i]).append(",");
        }

        return buffer.toString();
    }



}
