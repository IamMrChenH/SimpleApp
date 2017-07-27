package com.example.chenhao.simpleapp.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.chenhao.simpleapp.R;
import com.example.chenhao.simpleapp.app.MyApp;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by lenovo on 2017/5/31.
 * 简单工具类 集成一些工具类
 */
public class Utils {

    /**
     * The M toast.
     */
    static Toast mToast;

    /**
     * Show toast.
     *
     * @param msg the msg
     */
    public static void showToast(String msg) {
        try {
            if (mToast != null)
                mToast.cancel();
            mToast = Toast.makeText(MyApp.mApp, msg, Toast.LENGTH_SHORT);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add map.
     *
     * @param o the o
     * @return the map
     */
    public static Map<String, Object> add(Object o) {
        Map<String, Object> map = new HashMap<>();
        map.put("text", o);
        return map;
    }

    /**
     * Add map.
     *
     * @param text  the text
     * @param lv    the lv
     * @param money the money
     * @return the map
     */
    public static Map<String, Object> add(Object text, Object lv, Object money) {
        Map<String, Object> map = new HashMap<>();
        map.put("text", text);
        map.put("lv", lv);
        map.put("money", money);
        return map;
    }

    /**
     * Show notiftion.
     *
     * @param context the context
     * @param title   the title
     * @param msg     the msg
     * @param id      the id
     */
    public static void showNotiftion(Context context, String title, String msg, int id) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(msg);

        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        long[] longs = {0, 100, 0, 300};
        builder.setVibrate(longs);
        manager.notify(id, builder.build());


    }

    /**
     * Clear notiftion.
     *
     * @param context the context
     * @param id      the id
     */
    public static void clearNotiftion(Context context, int id) {
        try {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
