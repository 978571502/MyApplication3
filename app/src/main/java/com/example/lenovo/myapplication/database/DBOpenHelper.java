package com.example.lenovo.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2019/4/27.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    final String Create_Table_SQL="create table tb_dict (_id integer primary key autoincrement,word,translate)";

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Table_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("delete from tb_dict");
        sqLiteDatabase.execSQL("update sqlite_sequence SET seq = 0 where name ='tb_dict'");
    }

    //单词的添加方法
    public void writeData(SQLiteDatabase sqLiteDatabase,String word,String translate){
        ContentValues values = new ContentValues();
        values.put("word",word);
        values.put("translate",translate);
        sqLiteDatabase.insert("tb_dict",null,values);//保存功能
    }

    //单词的删除方法
    //判断是否是合法用户

}
