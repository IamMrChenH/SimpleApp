package com.example.chenhao.simpleapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenhao on 17/7/15.
 */

public class DBopenHeler extends SQLiteOpenHelper {
    private static final String TAG = "DBopenHeler";
    private static DBopenHeler instance = null;


    private DBopenHeler(Context context) {
        super(context, TAG, null, 1);
    }

    public static DBopenHeler getInstance(Context c) {
        if (instance == null) {
            synchronized (DBopenHeler.class) {
                if (instance == null) {
                    instance = new DBopenHeler(c);
                }
            }
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person (id integer PRIMARY KEY AUTOINCREMENT,content text,time long)");
        db.execSQL("create table addRecord (id integer PRIMARY KEY AUTOINCREMENT,content text,time long)");
        /**
         * role 管理员 一般用户 其他
         *    分别为 0  1  2 来表示权限
         */
        db.execSQL("create table app_user (id integer PRIMARY KEY AUTOINCREMENT," +
                "user_name text," +
                "password text," +
                "name text," +
                "email text," +
                "phone text," +
                "regist_time text," +
                "role int )");
//   insert（插入）：db.execSQL("insert into person (A,B)  values(?,?)",new Object[]{values1,values2});

//        db.execSQL("insert into app_user (insert into app_user(user_name,password,name,email,phone,regist_time,role)values('admin','admin','管理员','admin@xx.com','1008611','2017-7-17-21:55',0)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
