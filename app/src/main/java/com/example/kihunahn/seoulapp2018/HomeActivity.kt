package com.example.kihunahn.seoulapp2018

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kihunahn.seoulapp2018.R.id.btn_goMain
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_goMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            finish()
        }
    }
}
