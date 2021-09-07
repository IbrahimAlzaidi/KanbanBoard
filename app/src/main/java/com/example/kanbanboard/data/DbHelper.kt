package com.example.kanbanboard.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context) : SQLiteOpenHelper(context,DBNAME,null,DBVERSION) {


    override fun onCreate(Db: SQLiteDatabase?) {

        val tasksTable = "CREATE TABLE ${DbSchema.TABLE_TASKS} (" +
                "${DbSchema.TASK_ID} INTEGER PRIMARY KEY," +
                "${DbSchema.TASK_TITLE} TEXT," +
                "${DbSchema.TASK_DESC} TEXT," +
                "${DbSchema.TASK_STATS} TEXT" +
                ")"

        val usersTable = "CREATE TABLE ${DbSchema.TABLE_USERS} (" +
                "${DbSchema.USER_ID} INTEGER PRIMARY KEY," +
                "${DbSchema.USER_NAME} TEXT," +
                "${DbSchema.USER_TASK_ID} INTEGER" +
                ")"

        Db?.execSQL(tasksTable)
        Db?.execSQL(usersTable)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun readData() {
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} LEFT JOIN ${DbSchema.TABLE_USERS} ON ${DbSchema.TABLE_TASKS+"."+DbSchema.TASK_ID} = ${DbSchema.TABLE_USERS+"."+DbSchema.USER_TASK_ID}", arrayOf<String>())!!
        while (cursor.moveToNext()){
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val desc = cursor.getString(2)
            val stat = cursor.getString(3)
            val userid = cursor.getInt(4)
            val userName = cursor.getString(5)
            val taskId = cursor.getInt(6)
            Log.v("Hi Join", "$id - $title -$desc -$stat -$userid - $userName - $taskId" )
        }
    }
    fun readTasksData (){
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} ", arrayOf<String>())
        while (cursor.moveToNext()){
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val desc = cursor.getString(2)
            val stat = cursor.getString(3)
            Log.v("Hi from Tasks Table", "$id - $title -$desc -$stat")
        }
    }

    companion object{
        private const val DBNAME = "TasksManagerDb"
        private const val DBVERSION = 1
    }

}