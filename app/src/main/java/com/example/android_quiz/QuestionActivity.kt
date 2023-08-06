package com.example.android_quiz

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_quiz.databinding.QuestionActivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionActivity: AppCompatActivity() {

    private lateinit var questionBinding: QuestionActivityBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuestionAdapter

    private var dataList = ArrayList<String>()
    private val TAG: String = "CHECK_RESPONSE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionBinding = DataBindingUtil.setContentView(this, R.layout.question_activity)

        val retrofit = RetrofitClient.getClient()
        val quizApiService = retrofit.create(QuizApiService::class.java)

        val call = quizApiService.getRandomQuestions()
        call.enqueue(object: Callback<QuestionClass> {
            override fun onResponse(call: Call<QuestionClass>, response: Response<QuestionClass>) {
                if (response.isSuccessful) {
                    val question = response.body()
                    if (question != null) {
                        adapter.setQuestionId(question.id)
                        questionBinding.textView.text = question.statement
                        showQuestion(question.options)
                    }
                    Log.i(TAG, "onSuccess: $question")

                } else {
                    // To treat everyone different 200 status HTTP
                }
            }

            override fun onFailure(call: Call<QuestionClass>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }

        })

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = QuestionAdapter(dataList)
        recyclerView.adapter = adapter
    }

    override fun onBackPressed() {
    }

    private fun showQuestion(question: List<String>?) {
        if (question != null) {
            for(element in question) {
                dataList.add(element)
            }
        }
        // Notify adapter changes
        adapter.notifyDataSetChanged()
    }
}