package ru.studyguk.testingsystem.presentation.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import data.models.Test
import ru.studyguk.testingsystem.R

class TestAdapter(val list: ArrayList<Test>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<TestAdapter.MyTest>() {
    class MyTest(item: View) : RecyclerView.ViewHolder(item) {
        val textView: TextView = item.findViewById(R.id.textViewTestItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTest {
        val myItem = LayoutInflater.from(parent.context).inflate(R.layout.item_tests, parent, false)
        return MyTest(myItem)
    }

    override fun onBindViewHolder(holder: MyTest, position: Int) {
        val text = list[position].name
        holder.textView.text = Html.fromHtml(text)
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(list[position])
        }
    }

    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClickListener(test: Test)
    }
}