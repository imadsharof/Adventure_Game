package com.example.adventuregame

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById<DrawingView>(R.id.vMain)
    }
    /*override fun onPause() {
        super.onPause()
        drawingView.pause()
        }

    override fun onResume() {
        super.onResume()
        drawingView.resume()
        }*/

}