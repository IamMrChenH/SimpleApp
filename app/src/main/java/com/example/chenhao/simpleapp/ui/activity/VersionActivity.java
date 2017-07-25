package com.example.chenhao.simpleapp.ui.activity;

import android.os.Bundle;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.base.SuperBaseActivity;

public class VersionActivity extends SuperBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

    }

    @Override
    public String getToolbarTitle() {
        return "版本号V1.0";
    }
}
