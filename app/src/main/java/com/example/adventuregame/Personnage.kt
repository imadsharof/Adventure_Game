package com.example.adventuregame

import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

class Personnage(var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView) {

    val r = RectF(x1, y1, x2, y2)
    val PersonnagePaint = Paint()
    var dx = 1
    var dy = 0
    var random = Random()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))

    fun draw(canvas: Canvas) { /* Dessin du personnage représenté par un rectangle*/
        PersonnagePaint.color = Color.BLUE
        canvas.drawRect(r, PersonnagePaint)
    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }

    fun sauter() {
            dy = -2
            r.offset(0F*dx, 3.0F*dy)
     }

    fun droite(){
        dx = dx
        r.offset(10.0F*dx,10.0F*dy)
    }

    fun gauche() {
        dx = - dx
        r.offset(3.0F*dx, 3.0F*dy)
    }
}