package com.example.adventuregame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import java.util.*

class Balle (x1: Float, y1: Float, val diametre: Float) {
    val r = RectF(x1, y1, x1 + diametre, y1 + diametre)
    val random = Random()
    val paint = Paint()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))
    var dx = 1

    fun draw (canvas: Canvas) {
        paint.color = color
        canvas.drawOval(r, paint)
    }

    fun move() {
        r.offset(5.0F*dx, 0F)
    }
}
