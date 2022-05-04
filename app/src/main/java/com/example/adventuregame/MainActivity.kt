package com.example.adventuregame

import android.graphics.Point
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay
import java.util.*


class MainActivity() : AppCompatActivity(), View.OnTouchListener{

    lateinit var drawingView: DrawingView
    lateinit var droite : Button
    lateinit var gauche : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById<DrawingView>(R.id.vMain)
        droite = findViewById(R.id.droite)
        gauche = findViewById(R.id.gauche)
        droite.setOnTouchListener(this)
        gauche.setOnTouchListener(this)



        /*droite.setOnClickListener {
            drawingView.personnage.saute()
        }*/

        /*droite.setOnLongClickListener(OnLongClickListener {
            drawingView.personnage.droite()
            drawingView.personnage.x1 += 10f
            drawingView.personnage.x2 += 10f
            drawingView.personnage.setRect()
            for (m in drawingView.lesmonstres){ /*Si perso touche monstre*/
                m.setRect()
                if((m.r.left == personnage.r.right  && m.r.top < personnage.r.top) ){drawingView.life-= 1 }
                else if(m.r.top == personnage.r.bottom){drawingView.life -= 1}
                if (drawingView.life==0){personnage.dead = true}
            }
            true
        })*/
    }

    override fun onTouch(v : View, e : MotionEvent) : Boolean {
        val action = e.action
        val touchPoint = Point(e.x.toInt(), e.y.toInt())

        when(v.id){
            droite.id -> {if(e.action ==MotionEvent.ACTION_DOWN ) {
                resteappuie()
            }
            else if(e.action ==MotionEvent.ACTION_UP ) { Thread.interrupted() == true }}
        gauche.id ->{}}
        return true
        }



    fun resteappuie() {
        var isPressed = true
        Thread {
            while (isPressed) {
                if (drawingView.personnage.x2 > drawingView.screenWidth / 2){val isPressed = false}
                drawingView.personnage.droite()
                drawingView.personnage.x1 += 10f
                drawingView.personnage.x2 += 10f
                drawingView.personnage.setRect()
                for (m in drawingView.lesmonstres){ /*Si perso touche monstre*/
                    m.setRect()
                    if((m.r.left == drawingView.personnage.r.right  && m.r.top < drawingView.personnage.r.top) ){drawingView.life -= 1 }
                    else if(m.r.top == drawingView.personnage.r.bottom){drawingView.life -= 1}
                    if (drawingView.life==0){drawingView.personnage.dead = true}
                }
                Thread.sleep(100)
                if (!Thread.interrupted()){var isPressed = false}
                    }

                }.start()
            }


   fun appuieplus() {
        var isPressed = false
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