package com.example.adventuregame

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.adventuregame.R


class MainActivity() : AppCompatActivity() {

    lateinit var drawingView: DrawingView
    lateinit var droite : Button
    lateinit var jump : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById<DrawingView>(R.id.vMain)
        droite = findViewById(R.id.droite)
        jump = findViewById(R.id.jump)

        jump.setOnClickListener {
            drawingView.personnage.sauter()

        }


        droite.setOnClickListener {

            /*Déplacement des nuages*/

            if(drawingView.personnage.x2>=drawingView.screenWidth/2f) {
                drawingView.nuage1.x1 -= 20f
                drawingView.nuage1.x2 -= 20f
                drawingView.nuage2.x1 -= 20f
                drawingView.nuage2.x2 -= 20f
                drawingView.nuage3.x1 -= 20f
                drawingView.nuage3.x2 -= 20f

                drawingView.nuage1.deplacementmap()
                drawingView.nuage2.deplacementmap()
                drawingView.nuage3.deplacementmap()

                /*Demander AIDE assistant pour réduire code*/

                if (drawingView.nuage3.x2 == 1700f) {
                    drawingView.nuage1.x1 = 1900f
                    drawingView.nuage1.y1 = 50f
                    drawingView.nuage1.x2 = 2600f
                    drawingView.nuage1.y2 = 100f
                    drawingView.nuage1.setRect()
                    drawingView.nuage1.draw(drawingView.canvas, 255, 255, 255)

                } else if (drawingView.nuage3.x2 == 800f) {
                    drawingView.nuage2.x1 = 1900f
                    drawingView.nuage2.y1 = 50f
                    drawingView.nuage2.x2 = 2600f
                    drawingView.nuage2.y2 = 100f
                    drawingView.nuage2.setRect()
                    drawingView.nuage2.draw(drawingView.canvas, 255, 255, 255)
                } else if (drawingView.nuage1.x1 == 100f) {
                    drawingView.nuage3.x1 = 1900f
                    drawingView.nuage3.y1 = 50f
                    drawingView.nuage3.x2 = 2600f
                    drawingView.nuage3.y2 = 100f
                    drawingView.nuage3.setRect()
                }
            }
            /*Déplacement du personnage*/

            if(drawingView.personnage.x2 < drawingView.screenWidth/2){
                drawingView.personnage.x1 += 10f
                drawingView.personnage.x2 += 10f
                drawingView.personnage.droite()}
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