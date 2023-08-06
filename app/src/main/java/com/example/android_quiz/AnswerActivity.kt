package com.example.android_quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android_quiz.databinding.AnswerActivityBinding

class AnswerActivity : AppCompatActivity() {

    private lateinit var answerPageBinding: AnswerActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        answerPageBinding = DataBindingUtil.setContentView(this, R.layout.answer_activity)

        val answerCorrect = intent.getBooleanExtra("correct_answer", false)

        if (answerCorrect) {
            answerPageBinding.txtName.text = "Correct question!!!"
            increaseValue()
        }else {
            answerPageBinding.txtName.text = "Incorrect question..."
        }

        increaseQuestionIndex()
    }

    private fun increaseQuestionIndex() {
        val sharedPreferences = getSharedPreferences("index", Context.MODE_PRIVATE)
        var index = sharedPreferences.getInt("index", 0)

        index += 1

        val editor = sharedPreferences.edit()
        editor.putInt("index", index)
        editor.apply()
    }

    private fun increaseValue() {
        val sharedPreferences = getSharedPreferences("points", Context.MODE_PRIVATE)
        var points = sharedPreferences.getInt("points", 0)

        points += 1

        val editor = sharedPreferences.edit()
        editor.putInt("points", points)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()

        answerPageBinding.btnName.setOnClickListener{
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }

        val indexQuestion = loadIndex()
        Toast.makeText(this, "$indexQuestion", Toast.LENGTH_SHORT).show()

        if (indexQuestion == 10){
            val intent = Intent(this, FinalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadIndex(): Int {
        val sharedPreferences = getSharedPreferences("index", Context.MODE_PRIVATE)
        var index = sharedPreferences.getInt("index", 0)

        return index
    }


}