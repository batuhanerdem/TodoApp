package com.batuhan.todoapp.db

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.batuhan.todoapp.model.Todo


object DataBase {

    private lateinit var sqlDatabase: SQLiteDatabase
    private var setup = false

    internal fun setupSQL(context: Context) {
        if (!setup) {
            sqlDatabase = context.openOrCreateDatabase("todos", Context.MODE_PRIVATE, null)
            sqlDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS todos " +
                        "(uid INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, checked INTEGER, text TEXT)"
            )
            setup = true
        }
    }

    fun addToDataBase(todo: Todo) {

        val myString =
            "INSERT INTO todos(title,text,checked) VALUES ('${
                todo.title.replace(
                    "'",
                    "''"
                )
            }'," +
                    " '${todo.text.replace("'", "''")}'," +
                    "${todo.isDone.convertToInt()})"
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
        val myString = "DELETE FROM todos WHERE uid = ${id}"
        sqlDatabase.execSQL(myString)
    }

    fun deleteCheckeds() {
        val myString = "DELETE FROM todos WHERE checked = 1"
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
                cursor.getInt(cursor.getColumnIndex("checked")).convertToBoolean(),
                cursor.getInt(cursor.getColumnIndex("uid"))
            )
            todoList.add(newTodo)

        }
        cursor.close()
        return todoList
    }

    private fun Boolean.convertToInt(): Int {
        if (this) {
            return 1
        } else {
            return 0
        }
    }

    private fun Int.convertToBoolean(): Boolean {
        return this == 1
    }

    fun checkedChange(id: Int, value: Boolean) {
        val myString = "UPDATE todos SET checked = ${value.convertToInt()} WHERE uid = ${id}"
        sqlDatabase.execSQL(myString)
    }
}