package com.example.android_quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_quiz.databinding.QuestionActivityBinding

class QuestionActivity: AppCompatActivity() {

    private lateinit var questionBinding: QuestionActivityBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionBinding = DataBindingUtil.setContentView(this, R.layout.question_activity)

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_foreground, "Item $i"))
        }

        adapter = QuestionAdapter(data)
        recyclerView.adapter = adapter
    }
}