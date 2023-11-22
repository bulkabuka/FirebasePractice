package org.leftbrained.firebasepractice

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val auth = Firebase.auth
        val user = auth.currentUser
        if (user != null) {
            Log.d(TAG, "User is signed in.")
        } else {
            Log.d(TAG, "User is signed out.")
        }

    }

    fun loginUser(view: View) {
        val auth = Firebase.auth
        val email = findViewById<TextInputEditText>(R.id.email).text.toString()
        val password = findViewById<TextInputEditText>(R.id.password).text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
    }

    fun registerUser(view: View) {
        val auth = Firebase.auth
        val email = findViewById<TextInputEditText>(R.id.email).text.toString()
        val password = findViewById<TextInputEditText>(R.id.password).text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                }
            }
    }
}