package com.example.adventuregame

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import java.util.*

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr) {

    lateinit var canvas: Canvas
    val backgroundPaint = Paint()
    lateinit var thread: Thread
    var drawing: Boolean = true
    lateinit var lesParois: Array<Parois>

    init {
        backgroundPaint.color = Color.WHITE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val canvasH = (h - 50).toFloat()
        val canvasW = (w - 25).toFloat()
        lesParois = arrayOf(
            Parois(5f, 5f, 25f, canvasH)
        )

        fun draw() {
            if (holder.surface.isValid) {
                canvas = holder.lockCanvas()
                canvas.drawRect(
                    0F, 0F, canvas.getWidth() * 1F,
                    canvas.getHeight() * 1F, backgroundPaint
                )
                for (p in lesParois) {
                    p.draw(canvas)
                }

            }
        }
    }
}
    /*fun pause() {
        drawing = false
        thread.join()
    }

    fun resume() {
        drawing = true
        thread = Thread(this)
        thread.start()
    }

    fun draw() {

    }

}

    override fun surfaceChanged(
        holder: SurfaceHolder, format: Int,
        width: Int, height: Int) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = Thread(this)
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.join()
    }*/

