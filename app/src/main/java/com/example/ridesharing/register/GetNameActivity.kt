package com.example.ridesharing.register

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ridesharing.R

class GetNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_name)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usermail = intent.getStringExtra("usermail")
        Log.d("GetNameActivity", "Usermail received: $usermail")

        val firstnametxt = findViewById<EditText>(R.id.firstnametxt)
        val lastnametxt = findViewById<EditText>(R.id.lastnametxt)



        //continue btn
        val namebtn = findViewById<Button>(R.id.namebtn)
        namebtn.setOnClickListener {
            val firstname = firstnametxt.text.toString()
            val lastname = lastnametxt.text.toString()


            //send values to next activity
            val intent = Intent(this,GetNumberActivity::class.java)
            intent.putExtra("usermail", usermail)
            intent.putExtra("firstname",firstname)
            intent.putExtra("lastname",lastname)
            startActivity(intent)
        }


        val backbtn = findViewById<ImageButton>(R.id.backbtn)
        backbtn.setOnClickListener {
            finish()
        }

    }
}