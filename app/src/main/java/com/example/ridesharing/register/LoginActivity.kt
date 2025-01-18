package com.example.ridesharing.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ridesharing.fragments.HomePageActivity
import com.example.ridesharing.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val auth = FirebaseAuth.getInstance()

        val loginbtn = findViewById<Button>(R.id.loginbtn)
        loginbtn.setOnClickListener {
            val usermail = (findViewById<EditText>(R.id.emailtxt)).text.toString()
            val pass = (findViewById<EditText>(R.id.passtxt)).text.toString()

            if(usermail.isNotEmpty() && pass.isNotEmpty()){
                auth.signInWithEmailAndPassword(usermail,pass)
                    .addOnCompleteListener{ task->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, HomePageActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }



        //backbtn
        val backbtn = findViewById<ImageButton>(R.id.backbtn)
        backbtn.setOnClickListener{
            finish()
        }



    }
}