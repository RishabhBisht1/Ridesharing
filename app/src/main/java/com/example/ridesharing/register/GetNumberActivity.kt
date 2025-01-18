package com.example.ridesharing.register

import android.content.Intent
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

class GetNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_number)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //get details from previous activity
        val usermail = intent.getStringExtra("usermail")
        val firstname = intent.getStringExtra("firstname")
        val lastname = intent.getStringExtra("lastname")
        Log.d("GetNumberActivity", "Usermail received: $usermail")


        val numbertxt = findViewById<EditText>(R.id.numbertxt)



        //continue button
        val numbtn = findViewById<Button>(R.id.phonebtn)
        numbtn.setOnClickListener {
            val usernumber = numbertxt.text.toString()


            //send details to next activity
            val intent = Intent(this,passwordActivity::class.java)
            intent.putExtra("usermail", usermail)
            intent.putExtra("firstname",firstname)
            intent.putExtra("lastname",lastname)
            intent.putExtra("usernumber",usernumber)
            startActivity(intent)
            finish()
        }

        //backbtn
        val backbtn = findViewById<ImageButton>(R.id.backbtn)
        backbtn.setOnClickListener{
            finish()
        }

    }
}