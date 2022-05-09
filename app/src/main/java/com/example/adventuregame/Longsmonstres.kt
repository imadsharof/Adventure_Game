package com.example.adventuregame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Longsmonstres(x1: Float,y1: Float,x2: Float,y2: Float,view: DrawingView):Monstres(x1,y1,x2,y2,view) {
    private val LongsmonstresPaint = Paint()
    var LongsmonstresOnScreen = true
    lateinit var drawingView: DrawingView

    override fun draw(canvas: Canvas, red: Int, green: Int, blue: Int) {
        super.draw(canvas, red, green, blue)
        LongsmonstresPaint.color = Color.MAGENTA
        canvas.drawRect(r, LongsmonstresPaint)
    }
}