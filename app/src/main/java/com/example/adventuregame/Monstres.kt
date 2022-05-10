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

open class Monstres(var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView,var numero : Int) {
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

    open fun draw(canvas: Canvas, red: Int, green: Int, blue: Int) {
        MonstresPaint.color = Color.rgb(red, green, blue)
        canvas.drawRect(r, MonstresPaint)
    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }

    fun gauche() {
        dx = -1
        r.offset(10.0F * dx, 0.0F)
    }

    fun deplacementmonstres() {
        if (view.player.x2 >= view.screenWidth / 2f) {
            view.lesmonstres[view.nombregamelancee].gauche()
            view.lesmonstres[view.nombregamelancee].x1 -= 10f/*Déplacement monstres*/
            view.lesmonstres[view.nombregamelancee].x2 -= 10f
        }
    }

    open fun attack() {
        /*Si perso touche le monstre*/
        if ((view.lesmonstres[view.nombregamelancee].r.left == view.player.r.right &&
                    view.lesmonstres[view.nombregamelancee].r.top < view.player.r.bottom) &&
            view.lesmonstres[view.nombregamelancee].MonstresOnScreen
        ) { /*Si perso touche monstre*/
            view.player.life -= 1
            view.barrevie.x2 -= 50f / view.facteurdiminutionbarredevie
            view.barrevie.setRect()
            view.barrevie.draw(view.canvas, 0, 255, 14)

        }
    }

    fun estilparti() { /*Vérifie si le monstre est bien sorti de l'écran afin d'ajouter un nouveau monstre random*/
        if (view.lesmonstres[view.nombregamelancee].x2 == 0f) { /*Redessine un monstre random lorsqu'un monstre sort de l'écran*/
            val grandsmonstres = Grandsmonstres(
                view.screenWidth,
                view.screenHeight / 2f + 275f,
                view.screenWidth + 100f,
                view.screenHeight / 2f + 375f,
                view,
                2
            )
            val longsmonstres = Longsmonstres(
                view.screenWidth,
                view.screenHeight / 2f + 20f,
                view.screenWidth + 50f,
                view.screenHeight / 2f + 375f,
                view,
                1
            )
            val petitsmonstres = Petitsmonstres(
                view.screenWidth,
                view.screenHeight / 2f + 300f,
                view.screenWidth + 50f,
                view.screenHeight / 2f + 375f,
                view,
                0
            )
            val listedemonstre = listOf(grandsmonstres, longsmonstres, petitsmonstres)
            view.lesmonstres[view.nombregamelancee].MonstresOnScreen = true
            view.lesmonstres.set(0, listedemonstre.random())
        }

    }
}
