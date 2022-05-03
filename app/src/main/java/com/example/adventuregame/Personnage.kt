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

class Personnage(var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView) {

    val r = RectF(x1, y1, x2, y2)
    val PersonnagePaint = Paint()
    var dx = 1
    var dy = 0
    var random = Random()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))
    lateinit var drawingView: DrawingView
    lateinit var monstres : Monstres
    var dead = false
    var life = 100




    fun draw(canvas: Canvas) { /* Dessin du personnage représenté par un rectangle*/
        PersonnagePaint.color = Color.BLUE
        canvas.drawRect(r, PersonnagePaint)
    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }

    fun saute() {
        dy = -2
        r.offset(0F*dx, 100.0F*dy)
        Timer("SettingUp", false).schedule(300) {
            r.offset(0F*dx, -100.0F*dy)
        }

    }

    fun droite() {
        dx = 1
        r.offset(15.0F*dx,0.0F*dy)
    }

    fun gauche() {
        dx = -1
        r.offset(15.0F*dx, 0.0F*dy)
    }

    fun inter(m : Monstres){
        var a = m.r.intersect(r)
        a = false
        if(m.r.intersect(r)){
            life -= 20 }
    }



}

