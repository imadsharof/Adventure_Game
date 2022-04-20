package com.example.adventuregame

import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

class Personnage(x1: Float, y1: Float, x2: Float, y2: Float) {

    val r = RectF(x1, y1, x2, y2)
    val paint = Paint()
    var dx = 1
    var dy = 0
    var random = Random()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))

    fun draw (canvas: Canvas) { /* Dessin du personnage représenté par un rectangle*/
        paint.color = color
        canvas.drawRect(r,paint)
    }

    fun saute() {
        dy = 2
        r.offset(3.0F*dx, 3.0F*dy)
    }

    fun gauche() {
        dx = - dx
        r.offset(3.0F*dx, 3.0F*dy)
    }
}