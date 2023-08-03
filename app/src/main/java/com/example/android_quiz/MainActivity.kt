package com.example.android_quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android_quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


    //        setContentView(homeBinding2!!.root)
    }

    override fun onResume() {
        super.onResume()

        homeBinding.btnName.setOnClickListener(){
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }
}