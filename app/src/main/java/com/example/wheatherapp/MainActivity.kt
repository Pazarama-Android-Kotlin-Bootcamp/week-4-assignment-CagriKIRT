package com.example.wheatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wheatherapp.fragments.LoginFragment
import com.example.wheatherapp.fragments.TestFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, TestFragment)
            .commit()*/
    }
}