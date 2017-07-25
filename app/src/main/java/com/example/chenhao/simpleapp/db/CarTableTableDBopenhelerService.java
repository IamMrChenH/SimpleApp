package com.example.chenhao.simpleapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.chenhao.simpleapp.bean.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhao on 17/7/15.
 */

public class CarTableTableDBopenhelerService {

    private static CarTableTableDBopenhelerService instance = null;
    private DBopenHeler mHeler = null;


    private CarTableTableDBopenhelerService(Context context) {
        mHeler = DBopenHeler.getInstance(context);
//        mHeler.getWritableDatabase().close();

    }

    public static CarTableTableDBopenhelerService getInstance(Context c) {
        if (instance == null) {
            synchronized (CarTableTableDBopenhelerService.class) {
                if (instance == null) {
                    instance = new CarTableTableDBopenhelerService(c);
                }
            }
        }
        return instance;
    }

    /*****************************插入 inser***************************************/
    public boolean insert(Car bean) {
        try {
            SQLiteDatabase db = mHeler.getWritableDatabase();
            db.execSQL("insert into car" +
                            "(userId," +
                            "carId," +
                            "carName," +
                            "balance)" +
                            "values(?,?,?,?)",
                    new Object[]{bean.getUserId(), bean.getCarId(),
                            bean.getCarName(), bean.getBalance()});
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
     * @return
     */
    public List<Car> findAllCar() {
        List<Car> cars = new ArrayList<>();
        SQLiteDatabase db = mHeler.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from car",
                new String[]{});
        while (cursor.moveToNext()) {
            Car car = getCursorContent(cursor);
            if (car != null) {
                cars.add(car);
                Log.e("db", car.toString());
            }
        }
        cursor.close();
        db.close();

        return cars;
    }

    public Car findCar(int id) {
        Car car = null;
        SQLiteDatabase db = mHeler.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from car where id=?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            car = getCursorContent(cursor);
        }
        cursor.close();
        db.close();

        return car;
    }

    //OK
    public Car getCursorContent(Cursor cursor) {
        int id = -1;
        try {
            id = cursor.getInt(cursor.getColumnIndex("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int userId = -1;
        try {
            userId = cursor.getInt(cursor.getColumnIndex("userId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int carId = -1;
        try {
            carId = cursor.getInt(cursor.getColumnIndex("carId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String carName = null;
        try {
            carName = cursor.getString(cursor.getColumnIndex("carName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        double balance = -1;
        try {
            balance = cursor.getDouble(cursor.getColumnIndex("balance"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Car(id, userId, carId, carName, balance);
    }

    /****************更新 -- 改  update********************************/
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