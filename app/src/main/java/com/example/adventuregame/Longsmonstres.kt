package com.example.adventuregame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import java.util.*
import kotlin.concurrent.schedule

class Longsmonstres(x1: Float,y1: Float,x2: Float,y2: Float,view: DrawingView, numero : Int):Monstres(x1,y1,x2,y2,view,numero) {
    private val LongsmonstresPaint = Paint()
    var LongsmonstresOnScreen = true
    lateinit var drawingView: DrawingView


    override fun draw(canvas: Canvas, red: Int, green: Int, blue: Int) {
        super.draw(canvas, red, green, blue)
        LongsmonstresPaint.color = Color.MAGENTA
        canvas.drawRect(r, LongsmonstresPaint)
    }

    override fun attack() {
        /*Si perso touche le monstre*/
        if ((view.lesmonstres[view.nombregamelancee].r.left == view.player.r.right &&
                    view.lesmonstres[view.nombregamelancee].r.top < view.player.r.bottom) &&
            view.lesmonstres[view.nombregamelancee].MonstresOnScreen
        ) { /*Si perso touche monstre*/
        Timer("SettingUp", false).schedule(500) {
            view.player.life -= 2
            view.barrevie.x2 -= (50f / view.facteurdiminutionbarredevie) * 2
            view.barrevie.setRect()
            view.barrevie.draw(view.canvas, 0, 255, 14)
        }
        }
    }

/*else {Timer("SettingUp", false).schedule(500) {
                    barrevie.x2 -= 50f / facteurdiminutionbarredevie
                    barrevie.setRect()
                    barrevie.draw(canvas, 0,255,14)}}*/
}
