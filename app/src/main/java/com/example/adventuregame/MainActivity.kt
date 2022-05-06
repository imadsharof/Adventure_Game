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
    var a : Long = 15

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
            if(drawingView.balle.BalleOnScreen == false){/*Redessine la Balle*/
                drawingView.balle.x1 =drawingView.screenout.x1 / 2f
                drawingView.balle.y1 = drawingView.screenout.y2/2f + 340f
                drawingView.balle.x2 =drawingView.screenout.x1/ 2f+30f
                drawingView.balle.y2 = drawingView.screenout.y2/2f + 360f
                drawingView.balle.setRect()
                drawingView.balle.draw(drawingView.canvas,255,164,0)
            }
            drawingView.balle.afficheballe()
            balleavance = true
             Thread{
                 attack.isClickable = false
                while(balleavance){
                    drawingView.balle.afficheballe()
                    drawingView.balle.droite()
                    if(drawingView.balle.r.intersect(drawingView.lesmonstres[0].r) && drawingView.lesmonstres[0].MonstresOnScreen){
                        balleavance = false
                        drawingView.balle.supprimeballe()
                        drawingView.lesmonstres[0].MonstresOnScreen = false
                        attack.isClickable = true
                    }
                    else if (drawingView.balle.r.left ==drawingView.screenout.r.left ){
                        balleavance = false
                        drawingView.balle.supprimeballe()
                        attack.isClickable = true
                    }
                    Thread.sleep(15)
                } }.start()
        }

    }



    override fun onTouch(v : View, e : MotionEvent) : Boolean {
        val action = e.action
        val touchPoint = Point(e.x.toInt(), e.y.toInt())
        a = 15

        when(v.id) {
            start.id -> {
                if (action == MotionEvent.ACTION_DOWN) {
                    Thread {
                        start.visibility = View.INVISIBLE
                        while (mapavance) {
                            drawingView.deplacementcontinue()
                            Thread.sleep(a)
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