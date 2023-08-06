package com.example.android_quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android_quiz.databinding.ActivityMainBinding
import com.example.android_quiz.databinding.FinalActivityBinding

class FinalActivity : AppCompatActivity() {

    private lateinit var homeBinding: FinalActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        homeBinding = DataBindingUtil.setContentView(this, R.layout.final_activity)

        val username = recoverName()
        val points = recoverPoints()

        when(points) {
            0 -> {
                homeBinding.txtName.text = "$username You didn't do well this time, try again"
                homeBinding.rightQuestions.text = "$points correct questions"
            }
            10 -> {
                homeBinding.txtName.text = "Congratulations $username, You're awesome!!!"
                homeBinding.rightQuestions.text = "$points correct questions"
            }
            else -> {
                homeBinding.txtName.text = "You did well $username"
                homeBinding.rightQuestions.text = "$points correct questions"
            }
        }
//        homeBinding.txtName.text = ""

        var sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        sharedPreferences = getSharedPreferences("points", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        sharedPreferences = getSharedPreferences("index", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    //        setContentView(homeBinding2!!.root)
    }

    private fun recoverPoints(): Any {
        val sharedPreferences = getSharedPreferences("points", Context.MODE_PRIVATE)
        val points = sharedPreferences.getInt("points", 0)
        return points
    }

    override fun onResume() {
        super.onResume()

        homeBinding.btnName.setOnClickListener(){
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun recoverName(): String {
        val sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "player") ?: "player"
        return name
    }
}