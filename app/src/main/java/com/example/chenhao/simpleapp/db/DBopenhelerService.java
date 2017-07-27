package com.example.chenhao.simpleapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhao on 17/7/15.
 *
 * 这个是一个 专门用于操作用户表（addRecord）的操作服务类 但是后面基本没有使用到了
 * 换成了car_record这张表 替代了
 */
public class DBopenhelerService {

    private static DBopenhelerService instance = null;
    private DBopenHeler mHeler = null;


    private DBopenhelerService(Context context) {
        mHeler = DBopenHeler.getInstance(context);
        mHeler.getWritableDatabase().close();

    }

    /**
     * Gets instance.
     *
     * @param c the c
     * @return the instance
     */
    public static DBopenhelerService getInstance(Context c) {
        if (instance == null) {
            synchronized (DBopenhelerService.class) {
                if (instance == null) {
                    instance = new DBopenhelerService(c);
                }
            }
        }
        return instance;
    }
//    insert（插入）：db.execSQL("insert into person (A,B)  values(?,?)",new Object[]{values1,values2});
//    delete(删除) ：db.execSQL("delete from person where id=? ", new Object[] { id });
//    update（更新）： db.execSQL("update  person set A=? , B=? where id=? ", new Object[] {A,B,id});
//    find(使用id来查找数据) db.rawQuery("select * from person where id=?",new String[] { String.valueOf(Id) });
//    find（多条件查询）select * from person where id=? and A=? and B=?" new String[] { String.valueOf(Id) ,A,B});
//    findAll(获取表中所有数据) db.rawQuery("select * from person",new String[] {  });
//    Sql的语句中，and是&  or 是|  与或 的表示形式。


    /**
     * Insert boolean.
     *
     * @param content the content
     * @return the boolean
     */
    public boolean insert(String content) {
        try {
            SQLiteDatabase db = mHeler.getWritableDatabase();
            db.execSQL("insert into person (content,time)  values(?,?)", new Object[]{content, System.currentTimeMillis()});
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("db", "insert: 插入数据失败！");
            return false;
        }
        Log.e("db", "insert: 插入数据成功！");
        return true;
    }

    /**
     * Find.
     */
    public void find() {
        SQLiteDatabase db = mHeler.getWritableDatabase();

    }

    /**
     * Insert record boolean.
     *
     * @param content the content
     * @return the boolean
     */
    public boolean insertRecord(String content) {
        try {
            SQLiteDatabase db = mHeler.getWritableDatabase();
            db.execSQL("insert into addRecord (content)  values(?)", new Object[]{content});
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("db", "insertRecord: 插入数据失败！");
            return false;
        }
        Log.e("db", "insertRecord: 插入数据成功！");
        return true;
    }


    /**
     * Find record list.
     *
     * @return the list
     */
    public List<String> findRecord() {
        List<String> mRecord = new ArrayList<>();
        try {
            SQLiteDatabase db = mHeler.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from addRecord", new String[]{});
            while (cursor.moveToNext()) {
                String content = cursor.getString(cursor.getColumnIndex("content"));
                if (content != null) {
                    Log.e("db", "findRecord: " + content);
                    mRecord.add(content);
                }

            }
            cursor.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("db", "insertRecord: 查询失败！");
        }
        Log.e("db", "insertRecord: 查询成功！");
        return mRecord;
    }

}
