package com.example.kanbanboard.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kanbanboard.R
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.databinding.ItemTaskBinding
import com.example.kanbanboard.model.DbTaskModel
import org.w3c.dom.Text

class TaskAdapter(private val list:MutableList <DbTaskModel>):RecyclerView.Adapter<TaskAdapter.TaskViewHolder>()
{
val dbHelper:DbHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentPosition = list[position]
        holder.binding.apply {
            textTaskTitle.text = currentPosition.titleTask
            textTaskDescription.text = currentPosition.descTask
            taskStatus.text = currentPosition.statsTask
            userText.text = currentPosition.userName.subSequence(0,2)
        }
    }

    override fun getItemCount() = list.size
    class TaskViewHolder(viewItem : View):RecyclerView.ViewHolder(viewItem) {
        val binding = ItemTaskBinding.bind(viewItem)

    }
}