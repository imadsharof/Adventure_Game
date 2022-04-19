package com.example.adventuregame

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.*
import android.widget.Toast
import java.util.*

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback,Runnable  {

    lateinit var Lamap: Array<Map>
    var drawing: Boolean = true
    lateinit var canvas: Canvas
    lateinit var thread: Thread
    val backgroundPaint = Paint()


}