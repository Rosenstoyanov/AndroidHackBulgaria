package com.example.rosen.expencelist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rosen on 03.12.14.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "expenseList.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EXPENSE = "expense";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_LABEL = "label";
    public enum COLUMN{
        COLUMN_ID, COLUMN_LABEL, COLUMN_PRICE
    }

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_EXPENSE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_LABEL
            + " text not null, " + COLUMN_PRICE
            + " text not null);";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP DATABASE IF EXIST " + DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }
}
