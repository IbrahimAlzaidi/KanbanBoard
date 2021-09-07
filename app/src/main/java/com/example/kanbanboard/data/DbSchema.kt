package com.example.kanbanboard.data

object DbSchema {
    const val TABLE_TASKS = "TASKS"
    const val TASK_ID = "id"
    const val TASK_TITLE = "title"
    const val TASK_DESC = "description"
    const val TASK_STATS = "stats"

    const val TABLE_USERS ="USERS"
    const val USER_ID = "id"
    const val USER_NAME = "name"
    const val USER_TASK_ID = "task_id"

}