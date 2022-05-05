package com.example.adventuregame

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.view.ViewDebug
import androidx.annotation.ColorInt
import java.time.Clock.offset

class Parois(var x1: Float, var y1: Float, var x2: Float, var y2: Float, var view: DrawingView) {
    val r = RectF(x1,y1,x2,y2)
    val paroisPaint = Paint()
    var dx = -1
    var dy = 0

    fun draw(canvas: Canvas,red : Int,green : Int, blue : Int) {
        paroisPaint.color = Color.rgb(red,green,blue)
        canvas.drawRect(r, paroisPaint)
    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }

    fun deplacementmap(){
        r.offset(20.0f*dx,20.0f*dy)
    }


}