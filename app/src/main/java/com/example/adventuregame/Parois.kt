package com.example.adventuregame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
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
        r.offset(5.0F*dx, 5.0F*dy)

    }

    /*fun updatePositions(elapsedTimeMS: Double) {
        val interval = elapsedTimeMS / 1000.0
        Personnage.update(interval)
        Parois.update(interval)
        Petitsmonstres.update(interval)

    }*/





}