package com.example.chenhao.simpleapp.http;

/**
 * Created by IamMr on 2017/7/24.
 */

public class WthrcdnData {
    public static String fengli = "";
    public static String fengxiang = "";
    public static String type = "";

    public static String wendu = "";
    public static String ganmao = "";

    public static  String showData() {
        return "天气" + type
                + "\t温度" + wendu
                + "\n风向" + fengxiang
                + "\t风力" + fengli
                + "\n感冒指数" + ganmao;
    }
}
