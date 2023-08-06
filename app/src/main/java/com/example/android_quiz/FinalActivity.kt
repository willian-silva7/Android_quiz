package com.example.android_quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android_quiz.databinding.FinalActivityBinding

class FinalActivity : AppCompatActivity() {

    private lateinit var finalActivityBinding: FinalActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        finalActivityBinding = DataBindingUtil.setContentView(this, R.layout.final_activity)

        val username = recoverName()
        val points = recoverPoints()

        when(points) {
            0 -> {
                finalActivityBinding.txtName.text = "Hey $username, \nYou didn't do well this time, \ntry again..."
                finalActivityBinding.rightQuestions.text = "$points correct questions"
            }
            10 -> {
                finalActivityBinding.txtName.text = "Congratulations $username, \nYou are awesome!!!"
                finalActivityBinding.rightQuestions.text = "$points correct questions"
            }
            6..9 -> {
                finalActivityBinding.txtName.text = "Great!!! \nYou almost got there \n$username,"
                finalActivityBinding.rightQuestions.text = "$points correct questions"
            }
            else -> {
                finalActivityBinding.txtName.text = "You did well $username"
                finalActivityBinding.rightQuestions.text = "$points correct questions"
            }
        }

        var sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        sharedPreferences = getSharedPreferences("points", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        sharedPreferences = getSharedPreferences("index", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }

    private fun recoverPoints(): Any {
        val sharedPreferences = getSharedPreferences("points", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("points", 0)
    }

    override fun onBackPressed() {
    }

    override fun onResume() {
        super.onResume()

        finalActivityBinding.btnName.setOnClickListener(){
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }

        finalActivityBinding.btnRename.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun recoverName(): String {
        val sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE)
        return sharedPreferences.getString("name", "player") ?: "player"
    }
}