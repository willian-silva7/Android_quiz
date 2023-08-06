package com.example.android_quiz

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionAdapter(private val mListOfOptions: List<String>): RecyclerView.Adapter<QuestionAdapter.ViewHolder>()  {

    private val TAG: String = "CHECK_RESPONSE"
    private lateinit var questionsId: String
    private var verifyQuestion: Boolean= false

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = mListOfOptions[position]

        viewHolder.itemView.setOnClickListener() {
            val correctAnswer: Boolean = sendAnswer(questionsId, viewHolder.textView.text as String)
            val context = viewHolder.itemView.context
            val intent = Intent(context, AnswerActivity::class.java)
            intent.putExtra("correct_answer", correctAnswer)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = mListOfOptions.size

    fun setQuestionId(id: String) {
        questionsId = id
    }

    private fun sendAnswer(questionId: String, userResponse: String): Boolean {
        val jsonResponse = JSONObject()
        jsonResponse.put("answer", userResponse)
        val retrofit = RetrofitClient.getClient()

        val quizApiService = retrofit.create(QuizApiService::class.java)

        val call = quizApiService.verifyAnswerQuestion(questionId, jsonResponse)
        call.enqueue(object : Callback<ResultClass> {
            override fun onResponse(call: Call<ResultClass>, response: Response<ResultClass>) {
                if (response.isSuccessful) {
                    val serviceResponse = response.body()
                    Log.i(TAG, "serviceResponse: $serviceResponse")

                    if (serviceResponse?.result == true){
                        Log.i(TAG, "Acertou $serviceResponse")
                        verifyQuestion = true
                    } else {
                        Log.i(TAG, "Errou $serviceResponse")
                        verifyQuestion = false
                    }
                }
            }

            override fun onFailure(call: Call<ResultClass>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
                verifyQuestion = false
            }

        })

        return verifyQuestion
    }
}