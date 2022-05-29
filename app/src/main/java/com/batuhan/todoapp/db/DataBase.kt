package com.batuhan.todoapp.db

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.batuhan.todoapp.model.List
import com.batuhan.todoapp.model.Todo

object DataBase {

    private lateinit var sqlDatabase: SQLiteDatabase
    private var setup = false

    internal fun setupSQL(context: Context) {
        if (!setup) {
            sqlDatabase = context.openOrCreateDatabase("todos", Context.MODE_PRIVATE, null)
            val myString = "CREATE TABLE IF NOT EXISTS todos " +
                    "(uid INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, " +
                    "checked INTEGER, text TEXT, listNumber INTEGER)"
            val myString2 = "CREATE TABLE IF NOT EXISTS lists " +
                    "(listId INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)"
            sqlDatabase.execSQL(myString)
            sqlDatabase.execSQL(myString2)
            setup = true
        }
    }

    fun addToDataBase(todo: Todo) {
        val myString =
            "INSERT INTO todos(title,text,checked,listNumber) VALUES ('${
                todo.title.replace("'", "''")
            }'," +
                    " '${todo.text.replace("'", "''")}'," +
                    "${todo.isDone.convertToInt()}," +
                    "${todo.listNumber})"
        sqlDatabase.execSQL(myString)
    }

    fun addToListDatabase(name: String) {
        val myString = "INSERT INTO lists(name) VALUES('${name.replace("'", "''")}')"
        sqlDatabase.execSQL(myString)
    }

    fun editDataBase(id: Int, title: String, text: String) {
        val myString =
            "UPDATE todos SET title = '" + title.replace("'", "''") + "' WHERE uid = " + id
        val myString2 =
            "UPDATE todos SET text = '" + text.replace("'", "''") + "' WHERE uid = " + id

        sqlDatabase.execSQL(myString)
        sqlDatabase.execSQL(myString2)
    }

    fun deleteFromDataBase(id: Int) {
        val myString = "DELETE FROM todos WHERE uid = $id"
        sqlDatabase.execSQL(myString)
    }

    fun deleteFromListDatabase(id: Int) {
        val myString = "DELETE FROM lists WHERE listId = $id"
        sqlDatabase.execSQL(myString)
    }

    fun deleteCheckeds() {
        val myString = "DELETE FROM todos WHERE checked = 1"
        sqlDatabase.execSQL(myString)
    }
    fun deleteTodoFromList(todoId: Int){
        val myString = "UPDATE todos SET listNumber = '0' WHERE uid = $todoId"
        sqlDatabase.execSQL(myString)
    }
    fun setId(todoId: Int, listNumber: Int) {
        val myString = "UPDATE todos SET listNumber = '$listNumber' WHERE uid = $todoId"
        sqlDatabase.execSQL(myString)
    }

    @SuppressLint("Range")
    fun getFromDB(): ArrayList<Todo> {
        val todoList = arrayListOf<Todo>()
        val myString = "SELECT * FROM todos"
        val cursor = sqlDatabase.rawQuery(myString, null)
        while (cursor.moveToNext()) {
            val newTodo = Todo(
                cursor.getString(cursor.getColumnIndex("title")).replace("''", "'"),
                cursor.getString(cursor.getColumnIndex("text")),
                cursor.getInt(cursor.getColumnIndex("listNumber")),
                cursor.getInt(cursor.getColumnIndex("checked")).convertToBoolean(),
                cursor.getInt(cursor.getColumnIndex("uid"))
            )
            todoList.add(newTodo)
        }
        cursor.close()
        return todoList
    }

    @SuppressLint("Range")
    fun getFromListDB(): ArrayList<List> {
        val listList = arrayListOf<List>()
        val myString = "SELECT * FROM lists"
        val cursor = sqlDatabase.rawQuery(myString, null)
        while (cursor.moveToNext()) {
            val newList = List(
                cursor.getInt(cursor.getColumnIndex("listId")),
                cursor.getString(cursor.getColumnIndex("name"))
            )
            listList.add(newList)
        }
        cursor.close()
        return listList
    }

    @SuppressLint("Range")
    fun addToShowingList(listNumber: Int) {
        SharedDB.showingList.clear()
        val myString = "SELECT * FROM todos WHERE listNumber = $listNumber"
        val cursor = sqlDatabase.rawQuery(myString, null)
        while (cursor.moveToNext()) {
            val newTodo = Todo(
                cursor.getString(cursor.getColumnIndex("title")).replace("''", "'"),
                cursor.getString(cursor.getColumnIndex("text")),
                cursor.getInt(cursor.getColumnIndex("listNumber")),
                cursor.getInt(cursor.getColumnIndex("checked")).convertToBoolean(),
                cursor.getInt(cursor.getColumnIndex("uid"))
            )
            SharedDB.showingList.add(newTodo)
        }
        cursor.close()
    }

    private fun Boolean.convertToInt(): Int {
        return if (this) {
            1
        } else {
            0
        }
    }

    private fun Int.convertToBoolean(): Boolean {
        return this == 1
    }

    fun checkedChange(id: Int, value: Boolean) {
        val myString = "UPDATE todos SET checked = ${value.convertToInt()} WHERE uid = $id"
        sqlDatabase.execSQL(myString)
    }
    fun updateList(list: ArrayList<List>){
        list.clear()
        list.addAll(getFromListDB())
    }
}