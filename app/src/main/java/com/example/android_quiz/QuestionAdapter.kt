package com.example.android_quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter(private val mList: List<ItemsViewModel>): RecyclerView.Adapter<QuestionAdapter.ViewHolder>()  {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        viewHolder.imageView.setImageResource(itemsViewModel.image)

        viewHolder.textView.text = itemsViewModel.text
    }

    override fun getItemCount() = mList.size
}