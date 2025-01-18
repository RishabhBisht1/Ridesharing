package com.example.ridesharing.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ridesharing.R

class EmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_email)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailtxt = findViewById<EditText>(R.id.emailtxt)


        //set onclicklistner for button
        val continuemailbtn = findViewById<Button>(R.id.continuemailbtn)
        continuemailbtn.setOnClickListener{
            //Get email entered by user
            val usermail = emailtxt.text.toString()

            //pass the mail to next activity not directly to password activity
            val intent = Intent(this, GetNameActivity::class.java)
            intent.putExtra("usermail", usermail)
            startActivity(intent)
        }

        val backbtn = findViewById<ImageButton>(R.id.backbtn)
        backbtn.setOnClickListener {
            finish()
        }
    }
}