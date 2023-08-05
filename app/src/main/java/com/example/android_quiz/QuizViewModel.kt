package com.example.android_quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizViewModel: ViewModel() {
    private val _numberQuestions = MutableLiveData<QuestionClass>()
    val numberQuestions: LiveData<QuestionClass> get() = _numberQuestions
    private val TAG: String = "CHECK_RESPONSE"

    fun loadQuestions() {
        val retrofit = RetrofitClient.getClient()
        val quizApiService = retrofit.create(QuizApiService::class.java)

        val call = quizApiService.getRandomQuestions()
        call.enqueue(object: Callback<QuestionClass> {
            override fun onResponse(call: Call<QuestionClass>, response: Response<QuestionClass>) {
                if (response.isSuccessful) {
                    val question = response.body() // Questão aleatória obtida da API em formato JSON
                    _numberQuestions.postValue(question)

                } else {
                    // Tratar falha na resposta da API (código HTTP diferente de 200)
                }
            }

            override fun onFailure(call: Call<QuestionClass>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }

        })
    }
}