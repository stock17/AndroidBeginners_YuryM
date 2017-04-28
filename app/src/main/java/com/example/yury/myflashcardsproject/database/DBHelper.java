package com.example.yury.myflashcardsproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.yury.myflashcardsproject.database.CardContract.CardEntry;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "cards";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String execString = "CREATE TABLE " + CardEntry.TABLE_NAME + " (" +
                CardEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CardEntry.COLUMN_QUESTION + " TEXT, " +
                CardEntry.COLUMN_ANSWER + " TEXT)";

        db.execSQL(execString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CardEntry.TABLE_NAME);
        onCreate(db);
    }
}
