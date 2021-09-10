package com.example.kanbanboard.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.kanbanboard.model.DbTaskModel
class DbHelper(context: Context) : SQLiteOpenHelper(context,DBNAME,null,DBVERSION) {


    override fun onCreate(Db: SQLiteDatabase?) {
        val tasksTable = "CREATE TABLE ${DbSchema.TABLE_TASKS} (" +
                "${DbSchema.TASK_ID} INTEGER PRIMARY KEY," +
                "${DbSchema.TASK_TITLE} TEXT," +
                "${DbSchema.TASK_DESC} TEXT," +
                "${DbSchema.TASK_STATS} TEXT," +
                "${DbSchema.TASK_TYPE} TEXT," +
                "${DbSchema.TASK_DATE} TEXT," +
                "${DbSchema.USER_NAME} TEXT " +
                ")"

        Db?.execSQL(tasksTable)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS " + DbSchema.TASK_TYPE + ";")
        onCreate(p0)
    }
    @SuppressLint("Range", "Recycle")
    fun readTasksData (){
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} ", arrayOf())
        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val desc = cursor.getString(cursor.getColumnIndex("description"))
            val status = cursor.getString(cursor.getColumnIndex("stats"))
            val taskType = cursor.getString(cursor.getColumnIndex("task_type"))
            val taskDate = cursor.getString(cursor.getColumnIndex("task_date"))
            val userName = cursor.getString(cursor.getColumnIndex("user_name"))
            Log.v("Hi from Tasks Table", "$id - $title -$desc -$status-$taskType-$taskDate-$userName")
        }
    }//done & check

    fun filterTaskByUserName(name : String) {
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} WHERE ${DbSchema.USER_NAME} = ?", arrayOf(name))
        readDataCursor(cursor)
    }//Refactor


    @SuppressLint("Range")
    fun getAllTasksDataSpinner(status : String):MutableList<DbTaskModel>{
        val tasksList : ArrayList<DbTaskModel> = ArrayList()
        val cursor : Cursor? = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} WHERE ${DbSchema.TASK_STATS} = ?", arrayOf(status))
        var idTask : Int?
        var titleTask : String?
        var descTask:String?
        var statsTask:String?
        var typeTask:String?
        var dateTask:String?
        var userNAME : String?
        if (cursor != null) {
            while (cursor.moveToNext()){
                idTask = cursor.getInt(cursor.getColumnIndex("id"))
                titleTask = cursor.getString(cursor.getColumnIndex("title"))
                descTask = cursor.getString(cursor.getColumnIndex("description"))
                statsTask = cursor.getString(cursor.getColumnIndex("stats"))
                typeTask = cursor.getString(cursor.getColumnIndex("task_type"))
                dateTask = cursor.getString(cursor.getColumnIndex("task_date"))
                userNAME = cursor.getString(cursor.getColumnIndex("user_name"))
                val std = DbTaskModel(
                    idTask = idTask,
                    titleTask = titleTask,
                    descTask = descTask,
                    statsTask = statsTask,
                    typeTask = typeTask,
                    dateTask = dateTask,
                    userName = userNAME
                )
                tasksList.add(std)
                Log.i("TASKS", "${std.idTask} + ${std.titleTask} + ${std.descTask} + ${std.statsTask}+ ${std.typeTask}+ ${std.dateTask}+ ${std.userName}")
            }
        }
        return tasksList
    }

    @SuppressLint("Range")
    fun filterTaskByStatsChart(taskStatus: String, typeOfFilter: String, filterColumn:String) :MutableList<Int>{
        val list : MutableList<Int> = ArrayList()
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} WHERE $typeOfFilter = ?", arrayOf(taskStatus))
        var index = 0
        while (cursor.moveToNext()){
            cursor.getInt(cursor.getColumnIndex(filterColumn))
            index +=1
        }
        list.add(index)
        readDataCursor(cursor)
        return list
    } //Done

    private fun readDataCursor(cursor: Cursor) {
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val desc = cursor.getString(2)
            val status = cursor.getString(3)
            val taskType = cursor.getString(4)
            val taskDate = cursor.getInt(5)
            val userName = cursor.getString(6)
            Log.v("Hi Join", "$id - $title -$desc -$status -$taskType - $taskDate -$userName")
        }
    } //Done

    @SuppressLint("Range", "Recycle")
    fun getAllTasksData():MutableList<DbTaskModel>{
        val tasksList : ArrayList<DbTaskModel> = ArrayList()
        val cursor : Cursor? = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS}",null)
        var idTask : Int?
        var titleTask : String?
        var descTask:String?
        var statsTask:String?
        var typeTask:String?
        var dateTask:String?
        var userNAME : String?
        if (cursor != null) {
            while (cursor.moveToNext()){
                idTask = cursor.getInt(cursor.getColumnIndex("id"))
                titleTask = cursor.getString(cursor.getColumnIndex("title"))
                descTask = cursor.getString(cursor.getColumnIndex("description"))
                statsTask = cursor.getString(cursor.getColumnIndex("stats"))
                typeTask = cursor.getString(cursor.getColumnIndex("task_type"))
                dateTask = cursor.getString(cursor.getColumnIndex("task_date"))
                userNAME = cursor.getString(cursor.getColumnIndex("user_name"))
                val std = DbTaskModel(
                    idTask = idTask,
                    titleTask = titleTask,
                    descTask = descTask,
                    statsTask = statsTask,
                    typeTask = typeTask,
                    dateTask = dateTask,
                    userName = userNAME
                )
                tasksList.add(std)
                Log.i("TASKS", "${std.idTask} + ${std.titleTask} + ${std.descTask} + ${std.statsTask}+ ${std.typeTask}+ ${std.dateTask}+ ${std.userName}")
            }
        }
        return tasksList
    }

    fun addTask(title:String, desc:String, status:String, taskType:String, taskDate:String,userName:String){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.apply {
            put(DbSchema.TASK_TITLE,title)
            put(DbSchema.TASK_DESC,desc)
            put(DbSchema.TASK_STATS,status)
            put(DbSchema.TASK_TYPE,taskType)
            put(DbSchema.TASK_DATE,taskDate)
            put(DbSchema.USER_NAME,userName)
            db.insert(DbSchema.TABLE_TASKS,null,cv)
        }
    }
    companion object{
        private const val DBNAME = "TasksManagerDb"
        private const val DBVERSION = 6
    }

}
