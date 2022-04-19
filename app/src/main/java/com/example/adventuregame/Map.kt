package com.example.adventuregame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Map(x1: Float, y1: Float, x2: Float, y2: Float){
    val r = RectF(x1, y1, x2, y2)
    val paint = Paint()

    fun draw(canvas: Canvas) {
        paint.color = Color.YELLOW
        canvas.drawRect(r, paint)
    }
}