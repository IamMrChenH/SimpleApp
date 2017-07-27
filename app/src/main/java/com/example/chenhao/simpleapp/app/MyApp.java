package com.example.chenhao.simpleapp.app;

import android.app.Application;

/**
 * Created by lenovo on 2017/5/31.
 */
public class MyApp extends Application {

    /**
     *  IP地址
     */
    public static String IP = "10.20.0.25";
    /**
     * 端口号
     */
    public static int Port = 8080;
    /**
     * 实际地址
     */
    public static String IP_Address = "http://" + IP + ":" + Port + "/action/";
    /**
     * The constant UpdateTime.
     */
    public static int UpdateTime = 1 * 1000;

    /**
     * The constant mApp.
     */
    public static MyApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
