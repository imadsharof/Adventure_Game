package com.example.adventuregame

import android.app.Activity
import android.graphics.Color
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.adventuregame.R


class MainActivity() : AppCompatActivity() {

    lateinit var drawingView: DrawingView
    lateinit var droite : Button
    lateinit var personnage: Personnage



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById<DrawingView>(R.id.vMain)
        droite = findViewById(R.id.droite)


        droite.setOnClickListener {
            drawingView.personnage.saute()
        }
    }
    override fun onPause() {
        super.onPause()
        drawingView.pause()
    }

    override fun onResume() {
        super.onResume()
        drawingView.resume()
    }



}