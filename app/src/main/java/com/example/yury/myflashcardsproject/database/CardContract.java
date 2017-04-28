package com.example.yury.myflashcardsproject.database;

import android.provider.BaseColumns;

import com.example.yury.myflashcardsproject.MainActivity;

public final class CardContract {

    private CardContract() {};

    public static final class CardEntry implements BaseColumns {

        public static final String TABLE_NAME = "cards";
        public static final int DATABASE_VERSION = 1;

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER = "answer";
    }




}
