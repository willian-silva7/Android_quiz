package com.example.android_quiz


import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface QuizApiService {
    @GET("question")
    fun getRandomQuestions(): Call<Questions>

    @POST("answer")
    fun verifyAnswerQuestion(@Query("questionId") questionId: String, @Body answer: JSONObject): Call<Result>

}