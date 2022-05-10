package com.example.adventuregame

import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

class Petitsmonstres(x1: Float,y1: Float,x2: Float,y2: Float,view: DrawingView,numero : Int):Monstres(x1,y1,x2,y2,view,numero) {
    private val PetitsmonstresPaint = Paint()
    var PetitsmonstresOnScreen = true
    lateinit var drawingView: DrawingView


    override fun draw(canvas: Canvas, red: Int, green: Int, blue: Int) {
        super.draw(canvas, red, green, blue)
        PetitsmonstresPaint.color = Color.BLACK
        canvas.drawRect(r, PetitsmonstresPaint)
    }

    override fun attack() {
        /*Si perso touche le monstre*/
        if ((view.lesmonstres[view.nombregamelancee].r.left == view.player.r.right &&
                    view.lesmonstres[view.nombregamelancee].r.top < view.player.r.bottom) &&
            view.lesmonstres[view.nombregamelancee].MonstresOnScreen
        ) { /*Si perso touche monstre*/
            view.player.life -= 4
            view.barrevie.x2 -= (50f / view.facteurdiminutionbarredevie)*4
            view.barrevie.setRect()
            view.barrevie.draw(view.canvas, 0, 255, 14)

        }
    }
}
