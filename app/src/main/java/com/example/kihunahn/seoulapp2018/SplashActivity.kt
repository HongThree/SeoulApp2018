package com.example.kihunahn.seoulapp2018

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            // 뒤로가기 했을경우 안나오도록 없애주기 >> finish!!
            finish()
        }, 2000)

//
//        try {
//            Thread.sleep(300)
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
//
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
    }

}
