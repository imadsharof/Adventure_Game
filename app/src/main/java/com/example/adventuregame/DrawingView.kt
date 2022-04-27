package com.example.adventuregame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.graphics.createBitmap

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr),Runnable {

    lateinit var canvas: Canvas
    val backgroundPaint = Paint()
    var screenWidth = 0f
    var screenHeight = 0f
    var drawing = false
    lateinit var thread: Thread
    val parois = arrayOf(Parois(0f, 0f, 0f, 0f, this),Parois(0f, 0f, 0f, 0f, this),Parois(0f, 0f, 0f, 0f, this))
    val personnage = Personnage(0f,0f,0f,0f,this)


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

/*Dessin du sol : (épaisseur sol = 25f*/
        parois[0].x1 = (0f)
        parois[0].y1 = (screenHeight/2f + 175f) /*x1*/
        parois[0].x2= (screenWidth/1f)  /*y1*/
        parois[0].y2 = (screenHeight/2f+ 200f) /*parois.y2 = parois.y1 + ( 25f )*/
        parois[0].setRect()
/*Dessin de la Terre*/

        parois[1].x1 = (0f)
        parois[1].y1 = (screenHeight/2f+ 200f)
        parois[1].x2 = (screenWidth/1f)
        parois[1].y2 = (screenHeight/1f)
        parois[1].setRect()

/*Dessin du personnage : (longueur personnage = 100f, largeur = 200f)  */
        personnage.x1 = (50f)
        personnage.y1 = (screenHeight/2f - 25f) /*personnage.y1 = personnage.y2 - 200f*/
        personnage.x2 = (150f) /*longueur perso = x2 - x1 = 100 f*/
        personnage.y2 = (screenHeight/2f + 175f) /*personnage.y2 = parois.y1*/
        personnage.setRect()
        /*Dessin rectangle moitié bas de l'écran en dessous du sol*/


    }

    fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(
                0f, 0f, canvas.width.toFloat(),
                canvas.height.toFloat(), backgroundPaint
            )
            parois[0].draw(canvas,0,255,14)
            parois[1].draw(canvas,103,41,11)
            personnage.draw(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_MOVE-> { /*
            ACTION _DOWN =Action lorsque le toucher commence : au moment ou on appuie
            ACTION_UP = Action lorsque le toucher se termine : au moment où on lève le doigt
            ACTION_MOVE = Action lorsque le toucher bouge*/
                personnage.x1 = ( 50f +25f) /*Personnage se déplace de 25 unités*/
                personnage.droite()

            }
        }
        return true
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
