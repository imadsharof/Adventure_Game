package com.example.adventuregame

import android.graphics.Point
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.schedule


class MainActivity() : AppCompatActivity(), View.OnTouchListener{

    lateinit var drawingView: DrawingView
    lateinit var start : Button
    lateinit var jump : Button
    lateinit var personnage: Personnage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById<DrawingView>(R.id.vMain)
        start = findViewById(R.id.start)
        jump = findViewById(R.id.jump)
        start.setOnTouchListener(this)
        var lastClickTime = 0L

        jump.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTime < 1000) { /*Permet de ne pas cliquer plusieurs fois sur le bouton JUMP*/
                return@setOnClickListener
            }
            lastClickTime = SystemClock.elapsedRealtime()
            for (m in drawingView.personnage) {
                m.saute()
            }
        }
        Thread.sleep(1000)

    }


    override fun onTouch(v : View, e : MotionEvent) : Boolean {
        val action = e.action
        val touchPoint = Point(e.x.toInt(), e.y.toInt())
        var stop = true

        if(action ==MotionEvent.ACTION_DOWN ) {
            Thread {
                while (stop) {
                    drawingView.deplacementcontinue()
                    Thread.sleep(15)
                    }
            }.start()
            }
        return true
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