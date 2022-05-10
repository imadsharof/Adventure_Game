package com.example.adventuregame

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.view.ViewDebug
import androidx.annotation.ColorInt
import java.lang.reflect.Array.set
import java.time.Clock.offset

class Parois(var x1: Float, var y1: Float, var x2: Float, var y2: Float, var view: DrawingView) {
    val r = RectF(x1,y1,x2,y2)
    private val paroisPaint = Paint()
    var dx = -1

    fun draw(canvas: Canvas,red : Int,green : Int, blue : Int) {
        paroisPaint.color = Color.rgb(red,green,blue)
        canvas.drawRect(r, paroisPaint)
    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }

    /* L'environnement se déplace pour donner l'impression que le personnage se déplace */

    fun deplacementmap(){
        r.offset(20.0f*dx,0.0f)
    }

    fun animationdesnuages(){
        if (view.player.x2 >= view.screenWidth / 2f ){
        /*Déplacements des nuages*/
            view.nuage1.x1 -= 20f
            view.nuage1.x2 -= 20f
            view.nuage2.x1 -= 20f
            view.nuage2.x2 -= 20f
            view.nuage3.x1 -= 20f
            view.nuage3.x2 -= 20f
            view.nuage1.deplacementmap()
            view.nuage2.deplacementmap()
            view.nuage3.deplacementmap()
            /*Répétation des nuages à l'infini*/
            if (view.nuage3.x2 == 1700f) {
                view.nuage1.x1 = 1900f
                view.nuage1.y1 = 50f
                view.nuage1.x2 = 2600f
                view.nuage1.y2 = 100f
                view.nuage1.setRect()
                view.nuage1.draw(view.canvas, 255, 255, 255)

            } else if (view.nuage3.x2 == 800f) {
                view.nuage2.x1 = 1900f
                view.nuage2.y1 = 50f
                view.nuage2.x2 = 2600f
                view.nuage2.y2 = 100f
                view.nuage2.setRect()
                view.nuage2.draw(view.canvas, 255, 255, 255)
            } else if (view.nuage1.x1 == 100f) {
                view.nuage3.x1 = 1900f
                view.nuage3.y1 = 50f
                view.nuage3.x2 = 2600f
                view.nuage3.y2 = 100f
                view.nuage3.setRect()
            }
    }}

    fun deplacementsol(){
        if (view.player.x2 >= view.screenWidth / 2f ) {
        view.sol.x1 -= 10f
        view.sol.x2 -= 10f
        view.sol.deplacementmap()
    }}

}