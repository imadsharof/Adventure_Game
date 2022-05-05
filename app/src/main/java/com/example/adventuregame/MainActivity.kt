package com.example.adventuregame

import android.graphics.Point
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.schedule


class MainActivity() : AppCompatActivity(), View.OnTouchListener{

    lateinit var drawingView: DrawingView
    lateinit var start : ImageButton
    lateinit var jump : Button
    lateinit var personnage: Personnage
    lateinit var attack: Button
    lateinit var balle : Balle
    var balleavance = true
    var mapavance = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById<DrawingView>(R.id.vMain)
        start = findViewById(R.id.start)
        jump = findViewById(R.id.jump)
        attack = findViewById(R.id.attack)
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
        attack.setOnClickListener {
            Thread{
                while(balleavance){
                    drawingView.balle.afficheballe()
                    drawingView.balle.droite()
                    if(drawingView.balle.r.intersect(drawingView.monstres.r)){
                        balleavance = false
                        drawingView.balle.BalleOnScreen = false
                    }
                    Thread.sleep(15) } }.start()
        }

    }



    override fun onTouch(v : View, e : MotionEvent) : Boolean {
        val action = e.action
        val touchPoint = Point(e.x.toInt(), e.y.toInt())

        when(v.id) {
            start.id -> {
                if (action == MotionEvent.ACTION_DOWN) {
                    Thread {
                        start.visibility = View.INVISIBLE
                        while (mapavance) {
                            drawingView.deplacementcontinue()
                            Thread.sleep(15)
                        }
                    }.start()
                }
            }


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