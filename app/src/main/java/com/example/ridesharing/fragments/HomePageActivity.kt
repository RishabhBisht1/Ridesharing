package com.example.ridesharing.fragments

import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ridesharing.R
import androidx.fragment.app.commit
import androidx.fragment.app.replace


class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val searchbtn = findViewById<ImageButton>(R.id.searchbtn)
        searchbtn.setOnClickListener {
            supportFragmentManager.commit {
                replace<SearchFragment>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
                addToBackStack("name") // Name can be null
            }
        }

        val uploadbtn = findViewById<ImageButton>(R.id.uploadbtn)
        uploadbtn.setOnClickListener {
            supportFragmentManager.commit {
                replace<UploadFragment>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
                addToBackStack("name") // Name can be null
            }
        }

        val profilebtn = findViewById<ImageButton>(R.id.profilebtn)
        profilebtn.setOnClickListener {
            supportFragmentManager.commit {
                replace<ProfileFragment>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
                addToBackStack("name") // Name can be null
            }
        }
    }
}