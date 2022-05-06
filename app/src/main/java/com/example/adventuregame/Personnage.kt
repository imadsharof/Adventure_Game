package com.example.adventuregame

import android.graphics.*
import android.view.MotionEvent
import androidx.core.graphics.createBitmap
import java.util.*
import android.content.Context
import android.graphics.Bitmap.createBitmap
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
import java.util.Timer
import kotlin.concurrent.schedule

class Personnage(var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView) {

    val r = RectF(x1, y1, x2, y2)
    val PersonnagePaint = Paint()
    var dx = 1
    var dy = 0
    var random = Random()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))
    var dead = false
    var life = 100
    val image = intArrayOf(R.drawable.img)

    lateinit var drawingView: DrawingView
    lateinit var monstres : Monstres


    /* Dessin du personnage représenté par un rectangle */

    fun draw(canvas: Canvas,red : Int,green : Int, blue : Int) {
        PersonnagePaint.color = Color.rgb(red,green,blue)
        canvas.drawRect(r, PersonnagePaint)
    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }

    /* le personnage saute et peut sauter à nouveau lorsqu'il touche le sol */

    fun saute() {
        dy = -2
        r.offset(0F*dx, 100.0F*dy)
        Timer("SettingUp", false).schedule(300) {r.offset(0F*dx, -100.0F*dy) }

    }

    fun droite() {
        dx = 1
        r.offset(10.0F*dx,0.0F*dy)
    }


    fun inter(m : Monstres){
        while(!m.r.intersect(r)){
            dead = false
        }
    }



}


