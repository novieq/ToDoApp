package com.example.novieq.todoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.novieq.todoapp.data.ToDoItem;

/**
 * Created by sayghosh on 9/24/15.
 */
public class ToDoDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES = "Create table todos(_id INTEGER PRIMARY KEY AUTOINCREMENT,todo TEXT NOT NULL,priority TEXT NOT NULL,date TEXT NOT NULL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS todos";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoItems.db";

    public ToDoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
