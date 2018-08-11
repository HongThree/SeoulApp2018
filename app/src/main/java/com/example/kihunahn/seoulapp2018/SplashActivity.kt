package com.example.kihunahn.seoulapp2018

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)


        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}
