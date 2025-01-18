package com.example.ridesharing.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ridesharing.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class passwordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usermail = intent.getStringExtra("usermail")
        val firstname = intent.getStringExtra("firstname")
        val lastname = intent.getStringExtra("lastname")
        val usernum = intent.getStringExtra("usernumber")
        Log.d("passwordActivity", "Usermail received: $usermail")

        val passtxt = findViewById<EditText>(R.id.passwordtxt)

        //set logic for register button to firebase
        val registerbtn = findViewById<Button>(R.id.registerbtn)

        val auth = FirebaseAuth.getInstance()

        registerbtn.setOnClickListener {

            val userpass = passtxt.text.toString()


            if (usermail != null) {
                if (usermail.isNotEmpty() && userpass.isNotEmpty()) {
                    auth.createUserWithEmailAndPassword(usermail, userpass)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Registration successful
                                //get user id of user
                                val userId = auth.currentUser?.uid

                                // Create a user object to store in the database
                                val user = hashMapOf(
                                    "firstname" to firstname,
                                    "lastname" to lastname,
                                    "phonenumber" to usernum
                                )

                                // Store user data in Firebase Realtime Database
                                val database = Firebase.database
                                val userRef = database.reference.child("users").child(userId!!)
                                userRef.setValue(user)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this, "User Registered and Data Saved", Toast.LENGTH_SHORT).show()
                                            //clear previous activity stacks
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                            startActivity(intent)
                                            finish()
                                        } else {
                                            Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                                        }
                                    }


                            } else {
                                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please fill in usermail", Toast.LENGTH_SHORT).show()
            }
        }


        //backbtn
        val backbtn = findViewById<ImageButton>(R.id.backbtn)
        backbtn.setOnClickListener{
            finish()
        }

    }
}