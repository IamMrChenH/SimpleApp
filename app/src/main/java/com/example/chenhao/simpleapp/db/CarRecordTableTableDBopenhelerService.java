package com.example.chenhao.simpleapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.chenhao.simpleapp.bean.CarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhao on 17/7/15.
 * 这个是一个 专门用于操作小车操作记录表（car_record）的操作服务类
 */
public class CarRecordTableTableDBopenhelerService {

    private static CarRecordTableTableDBopenhelerService instance = null;
    private DBopenHeler mHeler = null;


    private CarRecordTableTableDBopenhelerService(Context context) {
        mHeler = DBopenHeler.getInstance(context);
//        mHeler.getWritableDatabase().close();

    }

    /**
     * Gets instance.
     *
     * @param c the c
     * @return the instance
     */
    public static CarRecordTableTableDBopenhelerService getInstance(Context c) {
        if (instance == null) {
            synchronized (CarRecordTableTableDBopenhelerService.class) {
                if (instance == null) {
                    instance = new CarRecordTableTableDBopenhelerService(c);
                }
            }
        }
        return instance;
    }

    /*****************************插入 inser @param bean the bean
     * @return the boolean
     */
    public boolean insert(CarRecord bean) {
        try {
            SQLiteDatabase db = mHeler.getWritableDatabase();
            db.execSQL("insert into car_record" +
                            "(carId," +
                            "money," +
                            "opType," +
                            "userId," +
                            "opTime)" +
                            "values(?,?,?,?,?)",
                    new Object[]{bean.getCarId(), bean.getMoney(),
                            bean.getOpType(), bean.getUserId(),
                            bean.getOpTime()});
            Log.e("db", "insert: 插入数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("db", "insert: 插入数据失败！");
            return false;
        }
        return true;
    }

/****************************************查询 find*************************************/

//    find（多条件查询）select * from person where id=? and A=? and B=?" new String[] { String.valueOf(Id) ,A,B});

    /**
     * 查询所有小车记录
     *
     * @return list
     */
    public List<CarRecord> findAllCar() {
        List<CarRecord> cars = new ArrayList<>();
        SQLiteDatabase db = mHeler.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from car_record",
                new String[]{});
        while (cursor.moveToNext()) {
            CarRecord car = getCursorContent(cursor);
            if (car != null) {
                cars.add(car);
                Log.e("db", car.toString());
            }
        }
        cursor.close();
        db.close();
        return cars;
    }

    /**
     * Find car record list.
     *
     * @param id the id
     * @return the list
     */
    public List<CarRecord> findCarRecord(int id) {
        List<CarRecord> cars = new ArrayList<>();
        SQLiteDatabase db = mHeler.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from car_record where carId=?",
                new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            CarRecord car = getCursorContent(cursor);
            if (car != null) {
                cars.add(car);
                Log.e("db", car.toString());
            }
        }
        cursor.close();
        db.close();

        return cars;
    }

    /**
     * Gets cursor content.
     *
     * @param cursor the cursor
     * @return the cursor content
     */
//OK
    public CarRecord getCursorContent(Cursor cursor) {
        int id = -1;
        try {
            id = cursor.getInt(cursor.getColumnIndex("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int carId = -1;
        try {
            carId = cursor.getInt(cursor.getColumnIndex("carId"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        double money = -1;
        try {
            money = cursor.getDouble(cursor.getColumnIndex("money"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String opType = null;
        try {
            opType = cursor.getString(cursor.getColumnIndex("opType"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int userId = -1;
        try {
            userId = cursor.getInt(cursor.getColumnIndex("userId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String opTime = null;
        try {
            opTime = cursor.getString(cursor.getColumnIndex("opTime"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CarRecord(id, carId, money, opType, userId, opTime);
    }

    /****************更新 -- 改  update @param id the id
     * @param balance the balance
     * @return the boolean
     */
//    update（更新）： db.execSQL("update  person set A=? , B=? where id=? ", new Object[] {A,B,id});
    public boolean updateBalance(int id, double balance) {
        SQLiteDatabase db = mHeler.getWritableDatabase();
        try {
            db.execSQL("update  car set balance=? where id=? ", new Object[]{balance, id});
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

}