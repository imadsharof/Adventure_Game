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

class Récompense(var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView) {

    val r = RectF(x1, y1, x2, y2)
    private val RecompensePaint = Paint()
    var random = Random()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))
    lateinit var drawingView: DrawingView

     fun draw(canvas: Canvas) { /* Dessin du livre représenté par un rectangle*/
        RecompensePaint.color = Color.MAGENTA
        canvas.drawRect(r, RecompensePaint)
    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }
}