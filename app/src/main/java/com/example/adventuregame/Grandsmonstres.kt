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

class Grandsmonstres(x1: Float,y1: Float,x2: Float,y2: Float,view: DrawingView, numero : Int):Monstres(x1,y1,x2,y2,view,numero) {
    private val GrandsmonstresPaint = Paint()
    var GrandsmonstresOnScreen = true
    lateinit var drawingView: DrawingView


    override fun draw(canvas: Canvas, red: Int, green: Int, blue: Int) {
        super.draw(canvas, red, green, blue)
        GrandsmonstresPaint.color = Color.RED
        canvas.drawRect(r, GrandsmonstresPaint)
    }

    override fun attack() {
        /*Si perso touche le monstre*/
        if ((view.lesmonstres[view.nombregamelancee].r.left == view.player.r.right &&
                    view.lesmonstres[view.nombregamelancee].r.top < view.player.r.bottom) &&
            view.lesmonstres[view.nombregamelancee].MonstresOnScreen
        ) { /*Si perso touche monstre*/
            view.player.life -= 2
            view.barrevie.x2 -= (50f / view.facteurdiminutionbarredevie)*2
            view.barrevie.setRect()
            view.barrevie.draw(view.canvas, 0, 255, 14)

        }
    }
}

