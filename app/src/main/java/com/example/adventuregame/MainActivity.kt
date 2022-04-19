package com.example.adventuregame

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var drawingview: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingview = findViewById(R.id.surfaceView)
    }
}