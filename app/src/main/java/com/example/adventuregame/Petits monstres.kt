package com.example.adventuregame

import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*
class Petitsmonstres(x: Float, y: Float, x: Float, y: Float) {
    val paint = Paint()
    val speed = 0.3f
    val size(0.5f, 0.5f)
    val attackRange = 0.15f
    val attackCooldown = 2f
    val damage = 2f
    val life = 10
    val detectRange = 2.5f
    }

    fun draw (canvas: Canvas) { /* Dessin du personnage représenté par un rectangle*/
        paint.color = color
        canvas.drawRect(r,paint)
    }

    fun attack() {
        if personnage in detectRange






}