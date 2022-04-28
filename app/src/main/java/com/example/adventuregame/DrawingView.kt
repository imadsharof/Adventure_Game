package com.example.adventuregame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.createBitmap

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr),Runnable {

    lateinit var canvas: Canvas
    lateinit var M : MainActivity
    val backgroundPaint = Paint()
    var screenWidth = 0f
    var screenHeight = 0f
    var drawing = false
    lateinit var thread: Thread
    val parois = arrayOf(
        Parois(0f, 0f, 0f, 0f, this), /*Sol*/
        Parois(0f, 0f, 0f, 0f, this), /*Terre*/
        Parois(0f, 0f, 0f, 0f, this),/*Nuage1*/
        Parois(0f, 0f, 0f, 0f, this),/*Nuage2*/
        Parois(0f, 0f, 0f, 0f, this)) /*Nuage3*/
    val personnage = Personnage(0f,0f,0f,0f,this)
    val sol = parois[0]
    val terre = parois[1]
    val nuage1 = parois[2]
    val nuage2 = parois[3]
    val nuage3 = parois[4]


    init {
        backgroundPaint.color = Color.rgb(0,255,249)
        /*couleur ciel*/
    }

    fun pause() {
        drawing = false
        thread.join()
    }

    fun resume() {
        drawing = true
        thread = Thread(this)
        thread.start()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        /*la méthode onSizeChanged, qui a pour mission de réajuster les
         dimensions des graphiques en réaction aux manipulations faites sur l'application*/

        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w.toFloat()
        screenHeight = h.toFloat()

/*Dessin du sol : (épaisseur sol = 25f)*/
        sol.x1 = (0f)
        sol.y1 = (screenHeight/2f + 375f) /*x1*/
        sol.x2= (screenWidth/1f)  /*y1*/
        sol.y2 = (screenHeight/2f+ 400f) /*parois.y2 = parois.y1 + ( 25f )*/
        sol.setRect()
/*Dessin de la Terre*/

        terre.x1 = 0f
        terre.y1 = screenHeight/2f+ 400f
        terre.x2 = screenWidth/1f
        terre.y2 = screenHeight/1f
        terre.setRect()

/*Dessin du personnage : (base personnage = 10f, hauteur = 100f)  */

        personnage.x1 = 50f
        personnage.y1 = screenHeight/2f + 325f /*personnage.y1 = personnage.y2 - 100f*/
        personnage.x2 = 100f /*longueur perso = x2 - x1 = 100 f*/
        personnage.y2 = screenHeight/2f + 375f /*personnage.y2 = sol.y1*/
        personnage.setRect()
        /*Dessin rectangle moitié bas de l'écran en dessous du sol*/
/*Dessin des Nuages*/
        /*Nuage 1*/
        nuage1.x1 = 100f
        nuage1.y1 = 50f
        nuage1.x2 = 800f
        nuage1.y2 = 100f
        nuage1.setRect()

        /*Nuage 2*/
        nuage2.x1 = 1000f
        nuage2.y1 = 50f
        nuage2.x2 = 1700f
        nuage2.y2 = 100f
        nuage2.setRect()

        /*Nuage 3*/

        nuage3.x1 = 1900f
        nuage3.y1 = 50f
        nuage3.x2 = 2600f
        nuage3.y2 = 100f
        nuage3.setRect()

    }

    fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(
                0f, 0f, canvas.width.toFloat(),
                canvas.height.toFloat(), backgroundPaint
            )
            sol.draw(canvas,0,255,14)
            terre.draw(canvas,103,41,11)
            nuage1.draw(canvas,255,255,255)
            nuage2.draw(canvas,255,255,255)
            nuage3.draw(canvas,255,255,255)
            personnage.draw(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }


    override fun run() {
        while (drawing){
            draw()
        }
    }

    fun surfaceChanged(holder: SurfaceHolder, format: Int,
                                width: Int, height: Int) {}

    fun surfaceCreated(holder: SurfaceHolder) {}

    fun surfaceDestroyed(holder: SurfaceHolder) {}
}
