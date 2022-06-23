package com.example.carrentalowner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carrentalowner.data.local.ClientPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        ClientPreferences(this).clearSharedPref()
    }
}