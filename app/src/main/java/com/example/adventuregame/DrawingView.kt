package com.example.adventuregame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceView
import androidx.core.graphics.createBitmap

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), Runnable {

    lateinit var canvas: Canvas
    val backgroundPaint = Paint()
    lateinit var thread: Thread
    var drawing: Boolean = true
    lateinit var lesParois: Array<Parois>

    var parois = Parois(0f, 0f, 20f, 20f)

    init {
        backgroundPaint.color = Color.WHITE
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val canvasH = (h - 50).toFloat()
        val canvasW = (w - 25).toFloat()

    }


    override fun run() {
        parois.draw(canvas)

    }
}

  /*  override fun surfaceChanged(
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

