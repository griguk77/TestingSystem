package ru.studyguk.testingsystem.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import data.Result
import ru.studyguk.testingsystem.R

class ResultAdapter(val list: ArrayList<Result>) : RecyclerView.Adapter<ResultAdapter.MyResult>() {
    class MyResult(item: View) : RecyclerView.ViewHolder(item) {
        val tvUser: TextView = item.findViewById(R.id.textViewUserResult)
        val tvResult: TextView = item.findViewById(R.id.textViewResResult)
        val tvDate: TextView = item.findViewById(R.id.textViewDateResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyResult {
        val myItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_results, parent, false)
        return ResultAdapter.MyResult(myItem)
    }

    override fun onBindViewHolder(holder: MyResult, position: Int) {
        holder.tvUser.text = list[position].user
        holder.tvResult.text = list[position].points.toString()
        holder.tvDate.text = list[position].date
    }

    override fun getItemCount() = list.size
}