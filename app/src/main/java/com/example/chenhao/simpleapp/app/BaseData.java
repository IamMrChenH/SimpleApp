package com.example.chenhao.simpleapp.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.chenhao.simpleapp.base.UserInfoBean;
import com.example.chenhao.simpleapp.http.WthrcdnData;
import com.example.chenhao.simpleapp.user.bean.UserBean;
import com.example.chenhao.simpleapp.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

import static com.example.chenhao.simpleapp.app.MyApp.UpdateTime;

/**
 * The type Base data.
 */
public class BaseData {
    /**
     * The constant mUserInfoBean.
     */
    public static UserInfoBean mUserInfoBean = null;

    /**
     * The constant mName.
     */
    public static String mName = "默认";
    /**
     * The M sense name.
     */
    public static String[] mSenseName = {"空气温度", "空气湿度", "pm2.5", "CO2", "光照", "道路状态", "ETC账户余额"};
    /**
     * The M sense data.
     */
    public static float[] mSenseData = {0, 0, 0, 0, 0, 0, 0};

    /**
     * The M sense min data.
     */
    public static int[] mSenseMinData = {10, 50, 500, 100, 0, 1, 10};
    /**
     * The M sense max data.
     */
    public static int[] mSenseMaxData = {40, 150, 5000, 600, 100, 5, 5000};


    private static String[] mTrafficNames = {};
    /**
     * The M traffic data.
     */
    public static int[] mTrafficData = {40, 100, 500, 60};
    /**
     * The constant mTrafficMinData.
     */
    public static int mTrafficMinData = 10;
    /**
     * The constant mTrafficMaxData.
     */
    public static int mTrafficMaxData = 90;
    /**
     * The constant isTrafficRun.
     */
    public static boolean isTrafficRun = false;


    /**
     * The M rorund data.
     */
    public static int[] mRorundData = {1, 2, 3, 4, 5};
    /**
     * The constant mUserBean.
     */
    public static UserBean mUserBean = new UserBean();
    /**
     * The M car money.
     */
    public static int[] mCarMoney = {100, 200, 300, 400};
    /**
     * The constant mCarMinMoney.
     */
    public static int mCarMinMoney = 0;
    /**
     * The constant mCarMaxMoney.
     */
    public static int mCarMaxMoney = 5 * 1000;


    /**
     * Start data.
     *
     * @param context the context
     */
    public static void startData(final Activity context) {
        getWthrcdnData(context);
//        mSenseData[6] = new Random().nextInt(1000);

        SharedPreferences data = context.getSharedPreferences("data", Context.MODE_PRIVATE);

        mSenseData[6] = data.getFloat("etc_Balance", 0);

        for (int i = 0; i < mSenseMinData.length; i++) {
            mSenseMinData[i] = data.getInt("etc_Balance_min_" + i, 10);
            mSenseMaxData[i] = data.getInt("etc_Balance_max_" + i, 5000);
        }


        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {

                        mRorundData[0] = new Random().nextInt(5) + 1;
                        mRorundData[1] = new Random().nextInt(5) + 1;
                        mRorundData[2] = new Random().nextInt(5) + 1;
                        mRorundData[3] = new Random().nextInt(5) + 1;
                        mRorundData[4] = new Random().nextInt(5) + 1;


                        mSenseData[0] = new Random().nextInt(1000);
                        mSenseData[1] = new Random().nextInt(1000);
                        mSenseData[2] = new Random().nextInt(1000);
                        mSenseData[3] = new Random().nextInt(1000);
                        mSenseData[4] = new Random().nextInt(1000);
                        mSenseData[5] = new Random().nextInt(5) + 1;


                        mTrafficData[0] = new Random().nextInt(150);
                        mTrafficData[1] = new Random().nextInt(150);
                        mTrafficData[2] = new Random().nextInt(150);
                        mTrafficData[3] = new Random().nextInt(150);


                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isTrafficRun)
                                    for (int i = 0; i < mTrafficData.length; i++) {
                                        if (mTrafficData[i] < mTrafficMinData) {
                                            Utils.showToast((i + 1) + "号小车速度过慢！");
                                            Utils.showNotiftion(context, "小车提示", (i + 1) + "号小车速度过慢！", (56456 + i));


                                        } else if (mTrafficData[i] > mTrafficMaxData) {
                                            Utils.showToast((i + 1) + "号小车速度过快！已发送停止请求！");
                                            Utils.showNotiftion(context, "小车提示", (i + 1) + "号小车速度过快！已发送停止请求！", (56456 + i));

                                        }

                                    }
                            }
                        });


                        mUserBean.setId(new Random().nextInt(5));
                        mUserBean.setMoney(mCarMoney[0]);
                        mUserBean.setName("user");
                        mUserBean.setSpeed(new Random().nextInt(500));
//                        mUserBean.setStatus(new Random().nextInt(500));

                        if (mSenseData[2] > 400) {
                            mUserBean.setStatus(1);
                        } else if (mSenseData[2] > 500) {
                            mUserBean.setStatus(2);
                        } else if (mSenseData[2] > 600) {
                            mUserBean.setStatus(3);
                        } else {
                            mUserBean.setStatus(0);
                        }
                        sleep(UpdateTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();

    }

    /**
     * Gets wthrcdn data.
     *
     * @param context the context
     */
    public static void getWthrcdnData(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://wthrcdn.etouch.cn/weather_mini?city=%E7%A6%8F%E5%B7%9E",
                new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("233", "onResponse: " + jsonObject.toString());
                String temp = jsonObject.toString();
                try {
                    JSONObject object = new JSONObject(temp);
                    object = new JSONObject(object.getString("data"));
                    JSONArray forecast = object.getJSONArray("forecast");
                    JSONObject jsonObject1 = forecast.getJSONObject(0);
                    WthrcdnData.fengli = jsonObject1.getString("fengli");
                    WthrcdnData.fengxiang = jsonObject1.getString("fengxiang");
                    WthrcdnData.type = jsonObject1.getString("type");
                    WthrcdnData.wendu = object.getString("wendu");
                    WthrcdnData.ganmao = object.getString("ganmao");

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(request);
    }

// forecast
//                    "{\"date\":\"24日星期一\",\"high\":\"高温 38℃\",\"fengli\":\"4-5级\",\"low\":\"低温 27℃\",\"fengxiang\":\"南风\",\"type\":\"多云\"}"+
//                            ",\"ganmao\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\",\"wendu\":\"32\"},\"status\":1000,\"desc\":\"OK\"}";

}
