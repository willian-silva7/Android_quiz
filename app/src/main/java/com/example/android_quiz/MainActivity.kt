package com.example.android_quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android_quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        deleteDataquiz()

    //        setContentView(homeBinding2!!.root)
    }

    private fun deleteDataquiz() {
        var sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        sharedPreferences = getSharedPreferences("points", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        sharedPreferences = getSharedPreferences("index", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }

    override fun onResume() {
        super.onResume()

        homeBinding.btnName.setOnClickListener(){
            if(homeBinding.edtName.text.isNotEmpty()) {
                saveUsername(homeBinding.edtName.text.toString())
                val intent = Intent(this, QuestionActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Username Field is Required", Toast.LENGTH_SHORT).show()
            }

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