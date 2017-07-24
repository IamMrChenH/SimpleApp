package com.example.chenhao.simpleapp.app;

import android.app.Application;

/**
 * Created by lenovo on 2017/5/31.
 */

public class MyApp extends Application {

    public static String IP = "10.20.0.25";
    public static int Port = 8080;
    public static String IP_Address = "http://" + IP + ":" + Port + "/action/";
    public static int UpdateTime = 1 * 1000;

    public static MyApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
