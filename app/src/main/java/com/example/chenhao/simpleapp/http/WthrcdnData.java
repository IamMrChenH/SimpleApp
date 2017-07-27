package com.example.chenhao.simpleapp.http;

/**
 * Created by IamMr on 2017/7/24.
 * 存放天气信息的一个简单实体类
 */
public class WthrcdnData {
    /**
     * The constant fengli.
     */
    public static String fengli = "";
    /**
     * The constant fengxiang.
     */
    public static String fengxiang = "";
    /**
     * The constant type.
     */
    public static String type = "";

    /**
     * The constant wendu.
     */
    public static String wendu = "";
    /**
     * The constant ganmao.
     */
    public static String ganmao = "";

    /**
     * Show data string.
     *
     * @return the string
     */
    public static  String showData() {
        return "天气" + type
                + "\t温度" + wendu
                + "\n风向" + fengxiang
                + "\t风力" + fengli
                + "\n感冒指数" + ganmao;
    }
}
