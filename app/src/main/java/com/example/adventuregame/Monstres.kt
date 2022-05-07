package com.example.adventuregame

import android.graphics.*
import android.view.MotionEvent
import androidx.core.graphics.createBitmap
import java.util.*
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.os.Bundle
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.createBitmap
import java.util.Timer
import kotlin.concurrent.schedule

open class Monstres(var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView){
    val r = RectF(x1, y1, x2, y2)
    val MonstresPaint = Paint()
    var dx = 1
    var random = Random()
    var color = Color.argb(
        255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256)
    )
    var MonstresOnScreen = true


    /* Dessin du monstre représenté par un rectangle*/

    open fun draw(canvas: Canvas,red : Int,green : Int, blue : Int) {
        MonstresPaint.color = Color.rgb(red,green,blue)
        canvas.drawRect(r, MonstresPaint)
    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }

    fun gauche() {
        dx = -1
        r.offset(10.0F*dx, 0.0F)
    }

}

