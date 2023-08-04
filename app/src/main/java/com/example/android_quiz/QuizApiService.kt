package com.example.android_quiz


import retrofit2.Call
import retrofit2.http.GET

interface QuizApiService {
    @GET("question")
    fun getRandomQuestions(): Call<Questions>
}