package com.example.novieq.todoapp.data;

/**
 * Created by sayghosh on 9/24/15.
 */
public class ToDoItem {
    public int _id;
    public String text;
    public String priority;
    public String dateofcompletion;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDateofcompletion() {
        return dateofcompletion;
    }

    public void setDateofcompletion(String dateofcompletion) {
        this.dateofcompletion = dateofcompletion;
    }
}
