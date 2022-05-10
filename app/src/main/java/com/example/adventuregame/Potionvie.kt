package com.example.adventuregame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

class Potionvie(var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView) {
    val r = RectF(x1, y1, x2, y2)
    private val random = Random()
    private val PotionPaint = Paint()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))
    var dx = 1
    var dy = 1
    var PotionvieScreen = true

    fun draw(canvas: Canvas,red : Int,green : Int, blue : Int) {
        if(PotionvieScreen){
            PotionPaint.color = Color.rgb(red,green,blue)
            canvas.drawOval(r, PotionPaint)
        }}

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }

    fun gauche() {
        dx = -1
        /*view.balle.x1 += 10f
        view.balle.x2 += 10f*/
        r.offset(10.0F*dx,0.0F*dy)
    }

    fun deplacementpotiondevie(){
        if (view.player.x2 >= view.screenWidth / 2f ) {
        view.potionvie[view.nombregamelancee].gauche()
        view.potionvie[view.nombregamelancee].x1 -= 10f
        view.potionvie[view.nombregamelancee].x2 -= 10f
    }}

    fun regenerevie(){
        if(view.potionvie[view.nombregamelancee].r.left == view.player.r.right){ /*Si joueur touche une potion de vie*/
            view.potionvie[view.nombregamelancee].PotionvieScreen = false
            if (view.player.life != view.facteurdiminutionbarredevie){
                view.player.life += 1 /*Augmentation de la vie du joueur*/
                view.barrevie.x2 += 50f /view.facteurdiminutionbarredevie
                view.barrevie.setRect()
                view.barrevie.draw(view.canvas, 67, 163, 62) }
        }
    }

    fun estilparti(){/*Redessinee de la potion de vie lorsqu'elle sort de l'écran*/
        if (view.potionvie[view.nombregamelancee].x2 == 0f) {
            view.potionvie[view.nombregamelancee].PotionvieScreen = true
            view.potionvie.set(
                0,
                Potionvie(
                    20000f,
                    (view.screenHeight / 2f) + 330f,
                    20030f,
                    view.screenHeight / 2f + 360f,
                    view
                )
            )
        }
    }
}