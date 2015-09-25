package com.example.novieq.todoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.novieq.todoapp.data.ToDoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sayghosh on 9/24/15.
 */
public class ToDoDbClient {
    private SQLiteDatabase sqlDb;
    private ToDoDbHelper mdbHelper;

    public ToDoDbClient(Context context) {
        mdbHelper = new ToDoDbHelper(context);
        sqlDb = mdbHelper.getWritableDatabase();

    }

    public void insertToDoItem(ToDoItem toDoItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("text",toDoItem.getText());
        contentValues.put("priority",toDoItem.getPriority());
        contentValues.put("dateofcompletion",toDoItem.getDateofcompletion());
        sqlDb.insert(ToDoDbHelper.DATABASE_NAME,null,contentValues);
    }

    public void updateToDoItem(ToDoItem toDoItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("text",toDoItem.getText());
        contentValues.put("priority",toDoItem.getPriority());
        contentValues.put("dateofcompletion",toDoItem.getDateofcompletion());
        sqlDb.update(ToDoDbHelper.DATABASE_NAME, contentValues, "_id=" + toDoItem.getId(), null);
    }

    public List<ToDoItem> getToDo() {
        List<ToDoItem> todoList = new ArrayList<ToDoItem>();
        String[] tableCol = new String[]{"_id","text","priority","dateofcompletion"};
        Cursor cursor = sqlDb.query(ToDoDbHelper.DATABASE_NAME,tableCol,null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setId(cursor.getInt(0));
            toDoItem.setText(cursor.getString(1));
            toDoItem.setPriority(cursor.getString(2));
            toDoItem.setDateofcompletion(cursor.getString(3));
            todoList.add(toDoItem);
            cursor.moveToNext();

        }
        return todoList;
    }
}
