package com.example.adventuregame

import android.graphics.*
import android.view.View
import java.util.*

class Balle (var view: DrawingView, var x1: Float, var y1: Float, var diametre: Float) {
    var r = RectF(x1 , y1, x1 + diametre, y1 + diametre)
    val random = Random()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))
    var dx = 1
    var coordoneeballe = PointF()
    var balleVitesse = 10f
    var balleOnScreen = true
    var balleRayon = 10f
    val ballePaint = Paint()

    init {
        ballePaint.color = Color.RED
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(coordoneeballe.x, coordoneeballe.y, balleRayon,
            ballePaint)
    }


    fun bouge(lesmonstres: Array<Monstres>,lesBalles: ArrayList<Balle>) {
        r.offset(15.0F * dx, 0F)

        for (b in lesBalles) {
            if (RectF.intersects(lesmonstres[i].r, b.r)) {
                life -= 1
                break
            }
        }
    }



}
