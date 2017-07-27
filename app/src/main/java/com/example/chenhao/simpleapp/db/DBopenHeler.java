package com.example.chenhao.simpleapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenhao on 17/7/15.
 * 数据库类 包含5张表 主要使用的是car、car_record 和app_user三张表
 */
public class DBopenHeler extends SQLiteOpenHelper {
    private static final String TAG = "DBopenHeler";
    private static DBopenHeler instance = null;


    private DBopenHeler(Context context) {
        super(context, TAG, null, 1);
    }

    /**
     * Gets instance.
     *
     * @param c the c
     * @return the instance
     */
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
                "role int ," +
                "carId text)");

//   insert（插入）：db.execSQL("insert into person (A,B)  values(?,?)",new Object[]{values1,values2});
        db.execSQL("create table car(id integer PRIMARY KEY AUTOINCREMENT," +
                " userId  int," +
                " carId   int," +
                " carName text," +
                " balance double)");

        db.execSQL("insert into car(userId,carId,carName,balance)" +
                "            values(1,1,'1号车',100)");
        db.execSQL("insert into car(userId,carId,carName,balance)" +
                "            values(2,2,'2号车',100)");
        db.execSQL("insert into car(userId,carId,carName,balance)" +
                "            values(3,3,'3号车',100)");
        db.execSQL("insert into car(userId,carId,carName,balance)" +
                "            values(4,4,'4号车',100)");
        db.execSQL("create table car_record(id integer PRIMARY KEY AUTOINCREMENT," +
                " carId  int," +
                "money double," +
                "opType text," +
                "userId int," +
                "opTime text)");
        db.execSQL("insert into car_record(carId,money,opType,userId,opTime)" +
                "values(1,10,'充值',1,'2017-7-24 05:30')");

//        编号，车编号，金额，类型，操作者，操作时间
//        1,      1     10     充值   1       2017-7-24 05:30


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
