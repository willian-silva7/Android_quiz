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
        homeBinding.txtName.text = recoverName()

        var sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        sharedPreferences = getSharedPreferences("points", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        sharedPreferences = getSharedPreferences("index", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    //        setContentView(homeBinding2!!.root)
    }

    override fun onResume() {
        super.onResume()

        homeBinding.btnName.setOnClickListener(){
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }



    }

    private fun saveUsername(name: String) {
        val sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.apply()
    }

    private fun recoverName(): String {
        val sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "player") ?: "player"
        return name
    }
}