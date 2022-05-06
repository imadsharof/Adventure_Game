package com.example.adventuregame

import android.graphics.*
import android.view.View
import java.util.*

class Balle (var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView) {

    val r = RectF(x1, y1, x2, y2)
    val random = Random()
    val BallePaint = Paint()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))
    var dx = 1
    var dy = 1
    var BalleOnScreen = false

    /* La balle est représentée par un oval */

    fun draw(canvas: Canvas,red : Int,green : Int, blue : Int) {
        if(BalleOnScreen){
        BallePaint.color = Color.rgb(red,green,blue)
        canvas.drawOval(r, BallePaint)
        }
    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }


    fun droite() {
        dx = 1
        view.balle.x1 += 10f
        view.balle.x2 += 10f
        r.offset(10.0F*dx,0.0F*dy)
    }

    fun afficheballe(){
        BalleOnScreen = true
    }

    fun supprimeballe(){
        BalleOnScreen = false
    }
}

