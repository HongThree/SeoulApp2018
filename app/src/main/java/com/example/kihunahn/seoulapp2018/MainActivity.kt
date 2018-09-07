package com.example.kihunahn.seoulapp2018

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var authStateListener : FirebaseAuth.AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 로그인 세션을 체크하는 부분
        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            var user = firebaseAuth.currentUser
            if(user != null){
                showProgress(false)

                startActivity(Intent(this, HomeActivity::class.java))
            }
            else{
                Log.d("s","login error")
            }
        }
        // 구글 로그인 옵션
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        // 구글 로그인 클래스를 만듦
        var googleSignInClient = GoogleSignIn.getClient(this, gso)
        login_button.setSize(1)
        login_button.setOnClickListener {
            var signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, 1)
            showProgress(true)
        }
    }



    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener!!)
    }

    override fun onPause() {
        super.onPause()
        FirebaseAuth.getInstance().removeAuthStateListener(authStateListener!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // 구글 로그인에 성공했을 때 넘어 오는 토큰 값을 가지고 있는 Task
            var task = GoogleSignIn.getSignedInAccountFromIntent(data)
            // 캐스팅
            var account = task.getResult(ApiException::class.java)
            // 구글 로그인에 성공했다는 인증서
            var credential = GoogleAuthProvider.getCredential(account.idToken, null)
            // 파이어베이스에 구글 사용자 등록
            FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful)
                            Toast.makeText(this, "구글 아이디 연동이 성공하였습니다.", Toast.LENGTH_LONG).show()
                        else
                            Toast.makeText(this, "구글 아이디 연동이 실패하였습니다.", Toast.LENGTH_LONG).show()
                    }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

            loginProgress.setVisibility(if (show) View.VISIBLE else View.GONE)
            loginProgress.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    loginProgress.setVisibility(if (show) View.VISIBLE else View.GONE)
                }
            })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            loginProgress.setVisibility(if (show) View.VISIBLE else View.GONE)
        }
    }

}