package com.example.adventuregame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
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
    val parois = Parois(0f, 0f, 0f, 0f, this)
    val personnage = Personnage(0f,0f,0f,0f,this)

    init {
        backgroundPaint.color = Color.WHITE
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

        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w.toFloat()
        screenHeight = h.toFloat()

/*Dessin du sol */
        parois.x1 = (0f)
        parois.y1 = (screenHeight/1f - 75f) /*x1*/
        parois.x2= (screenWidth/1f)  /*y1*/
        parois.y2 = (screenHeight/1f-50f)
        parois.setRect()
/*Dessin du personnage*/
        personnage.x1 = (50f)
        personnage.y1 = (screenHeight/1f - 275f)
        personnage.x2 = (150f)
        personnage.y2 = (screenHeight/1f - 75f)
        personnage.setRect()

    }

    fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(
                0f, 0f, canvas.width.toFloat(),
                canvas.height.toFloat(), backgroundPaint
            )
            parois.draw(canvas)
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
