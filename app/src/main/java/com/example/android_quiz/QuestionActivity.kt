package com.example.android_quiz

import android.content.Context
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

    private val BASE_URL = "?questionId={id}"
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
                    val question = response.body() // Questão aleatória obtida da API em formato JSON
                    // Agora você pode trabalhar com a pergunta recebida
                    if (question != null) {
                        adapter.setQuestionId(question.id)
                        showQuestion(question.options)
                    }
                    Log.i(TAG, "onSuccess: $question")

                } else {
                    // Tratar falha na resposta da API (código HTTP diferente de 200)
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

    private fun showQuestion(question: List<String>?) {
        // Verifica se a pergunta não é nula antes de adicionar à lista
        if (question != null) {
            for(element in question) {
                dataList.add(element)
            }
        }
        // Notifica o adapter sobre a mudança na lista
        adapter.notifyDataSetChanged()
    }
}