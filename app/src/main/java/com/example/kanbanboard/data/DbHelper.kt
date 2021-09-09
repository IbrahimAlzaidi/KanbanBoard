package com.example.kanbanboard.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.kanbanboard.model.DbTaskModel
import com.example.kanbanboard.model.DbUserModel
import kotlin.math.min

class DbHelper(context: Context) : SQLiteOpenHelper(context,DBNAME,null,DBVERSION) {


    override fun onCreate(Db: SQLiteDatabase?) {

        val tasksTable = "CREATE TABLE ${DbSchema.TABLE_TASKS} (" +
                "${DbSchema.TASK_ID} INTEGER PRIMARY KEY," +
                "${DbSchema.TASK_TITLE} TEXT," +
                "${DbSchema.TASK_DESC} TEXT," +
                "${DbSchema.TASK_STATS} TEXT," +
                "${DbSchema.TASK_TYPE} TEXT," +
                "${DbSchema.TASK_DATE} DATE " +
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
        p0?.execSQL("DROP TABLE IF EXISTS " + DbSchema.TASK_TYPE)
        p0?.execSQL("DROP TABLE IF EXISTS " + DbSchema.TABLE_USERS)
        onCreate(p0)

    }

    fun readAllData() {
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} LEFT JOIN ${DbSchema.TABLE_USERS} ON ${DbSchema.TABLE_TASKS+"."+DbSchema.TASK_ID} = ${DbSchema.TABLE_USERS+"."+DbSchema.USER_TASK_ID}", arrayOf<String>())!!
        readDataCursor(cursor)
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
    fun readUserData (){
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_USERS} ", arrayOf<String>())
        while (cursor.moveToNext()){
            val id = cursor.getInt(0)
            val userName = cursor.getString(1)
            val desc = cursor.getString(2)
            Log.v("Hi from Tasks Table", "$id - $userName -$desc ")
        }
    }
    fun userFilter (name : String){
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_USERS} WHERE ${DbSchema.USER_NAME} = ?", arrayOf(name))
        while (cursor.moveToNext()){
            val id = cursor.getInt(0)
            val userName = cursor.getString(1)
            Log.v("Hi from Tasks Table", "$id - $userName")
        }
    }

    fun filterTaskByUserName(name : String) {
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} LEFT JOIN ${DbSchema.TABLE_USERS} ON ${DbSchema.TABLE_TASKS+"."+DbSchema.TASK_ID} = ${DbSchema.TABLE_USERS+"."+DbSchema.USER_TASK_ID} where ${DbSchema.TABLE_USERS+"."+DbSchema.USER_NAME} = ? ", arrayOf(name))!!
        readDataCursor(cursor)
    }

    fun filterTaskByStats(stats : String) {
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} LEFT JOIN ${DbSchema.TABLE_USERS} ON ${DbSchema.TABLE_TASKS+"."+DbSchema.TASK_ID} = ${DbSchema.TABLE_USERS+"."+DbSchema.USER_TASK_ID} where ${DbSchema.TABLE_TASKS+"."+DbSchema.TASK_STATS} = ? ", arrayOf(stats))!!
        readDataCursor(cursor)
    }
    @SuppressLint("Range")
    fun filterTaskByStatsChart(taskType : String) :MutableList<Int>{
        val list : MutableList<Int> = ArrayList()
        val cursor = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS} LEFT JOIN ${DbSchema.TABLE_USERS} ON ${DbSchema.TABLE_TASKS+"."+DbSchema.TASK_ID} = ${DbSchema.TABLE_USERS+"."+DbSchema.USER_TASK_ID} where ${DbSchema.TABLE_TASKS+"."+DbSchema.TASK_TYPE} = ? ", arrayOf(taskType))!!
        var index = 0
        while (cursor.moveToNext()){
            cursor.getInt(cursor.getColumnIndex("task_type"))
            index +=1
        }
        list.add(index)
        readDataCursor(cursor)
        return list
    }


    private fun readDataCursor(cursor: Cursor) {
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val desc = cursor.getString(2)
            val stat = cursor.getString(3)
            val taskType = cursor.getString(4)
            val taskDate = cursor.getInt(5)
            val userid = cursor.getInt(6)
            val userName = cursor.getString(7)
            val taskId = cursor.getInt(8)
            Log.v("Hi Join", "$id - $title -$desc -$stat -$taskType - $taskDate - $userid - $userName - $taskId")
        }
    }

    @SuppressLint("Range")
    fun getAllTasksData():MutableList<DbTaskModel>{
        val tasksList : ArrayList<DbTaskModel> = ArrayList()
        val cursor : Cursor? = readableDatabase.rawQuery("SELECT * FROM ${DbSchema.TABLE_TASKS}",null)
        var idTask : Int ?
        var titleTask : String?
        var descTask:String?
        var statsTask:String?
        var typeTask:String?
        var dateTask:Int?

        if (cursor != null) {
            while (cursor.moveToNext()){
                idTask = cursor.getInt(cursor.getColumnIndex("id"))
                titleTask = cursor.getString(cursor.getColumnIndex("title"))
                descTask = cursor.getString(cursor.getColumnIndex("description"))
                statsTask = cursor.getString(cursor.getColumnIndex("stats"))
                typeTask = cursor.getString(cursor.getColumnIndex("task_type"))
                dateTask = cursor.getInt(cursor.getColumnIndex("task_date"))

                val std = DbTaskModel(
                    idTask = idTask,
                    titleTask = titleTask,
                    descTask = descTask,
                    statsTask = statsTask,
                    typeTask = typeTask,
                    dateTask = dateTask
                )
                tasksList.add(std)
                Log.i("TASKS", "${std.idTask} + ${std.titleTask} + ${std.descTask} + ${std.statsTask}+ ${std.typeTask}+ ${std.dateTask}")
            }
        }
        return tasksList
    }

    @SuppressLint("Range")
    fun getAllUserData():MutableList<DbUserModel>{
        val tasksList : ArrayList<DbUserModel> = ArrayList()
        val selectQuery = "SELECT * FROM ${DbSchema.TABLE_USERS}"
        val dp = this.readableDatabase
        val cursor : Cursor?
        try {
            cursor = dp.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            dp.execSQL(selectQuery)
            return ArrayList()
        }
        var idUser : Int ?
        var nameUser : String?
        var taskIdUser:Int?

        while (cursor.moveToNext()){
            idUser = cursor.getInt(cursor.getColumnIndex("id"))
            nameUser = cursor.getString(cursor.getColumnIndex("title"))
            taskIdUser = cursor.getInt(cursor.getColumnIndex("description"))

            val std = DbUserModel(
                userId = idUser,
                userName = nameUser,
                userTaskId = taskIdUser)
            tasksList.add(std)
            Log.i("AABB", "${std.userId} + ${std.userName} + ${std.userTaskId}")
        }
        return tasksList
    }

    fun addTask(title:String, desc:String, status:String, taskType:String, taskDate:Int){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.apply {
            put(DbSchema.TASK_TITLE,title)
            put(DbSchema.TASK_DESC,desc)
            put(DbSchema.TASK_STATS,status)
            put(DbSchema.TASK_TYPE,taskType)
            put(DbSchema.TASK_DATE,taskDate)
            db.insert(DbSchema.TABLE_TASKS,null,cv)
        }

    }
    fun addUser(userName:String,taskUserId:Int){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.apply {
            put(DbSchema.USER_NAME,userName)
            put(DbSchema.USER_TASK_ID,taskUserId)
        }
        db.insert(DbSchema.TABLE_USERS,null,cv)
    }


    companion object{
        private const val DBNAME = "TasksManagerDb"
        private val DBVERSION = 4
    }

}
