package com.example.frippy_v_fin.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.example.frippy_v_fin.R


class Spalsh : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_spalsh)
        val logo = findViewById<ImageView>(R.id.logo)
        val backround = findViewById<ImageView>(R.id.backround)
        val lottie = findViewById<LottieAnimationView>(R.id.lottie)
        backround.animate().translationY(5000F).setDuration(1000).startDelay = 4000
        logo.animate().translationY(-2000F).setDuration(1000).startDelay =4000
        lottie.animate().translationY(5000F).setDuration(1000).startDelay = 4000

        var handler: Handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 4500)
    }
}