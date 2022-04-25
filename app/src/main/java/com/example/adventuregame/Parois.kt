package com.example.adventuregame

import android.graphics.*
import java.time.Clock.offset

class Parois(x1: Float, y1: Float, x2: Float, y2: Float) {
    val r = RectF(x1, y1, x2, y2)
    val paint = Paint()
    var dx = -1
    val dy = 0
    val paroiVitesse = 10

    fun draw(canvas: Canvas) {
        paint.color = Color.BLACK
        canvas.drawRect(r, paint)
    }

    fun update(interval: Double) {
        var longueur = (interval * paroiVitesse).toFloat()
        r.offset(longueur, 0f)
    }



    /*fun updatePositions(elapsedTimeMS: Double) {
        val interval = elapsedTimeMS / 1000.0
        Personnage.update(interval)
        Parois.update(interval)
        Petitsmonstres.update(interval)

    }*/





}