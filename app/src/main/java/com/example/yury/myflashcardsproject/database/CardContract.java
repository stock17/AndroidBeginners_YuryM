package com.example.yury.myflashcardsproject.database;

import android.content.ContentResolver;
import android.provider.BaseColumns;

import com.example.yury.myflashcardsproject.MainActivity;

public final class CardContract {

    private CardContract() {};

    static final String AUTHORITY = "com.example.yury.myflashcardsproject";
    static final String PATH = "cards";

    public static final class CardEntry implements BaseColumns {

        public static final String TABLE_NAME = "cards";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER = "answer";


        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH;

    }




}
