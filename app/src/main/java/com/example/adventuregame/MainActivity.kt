package com.example.adventuregame

import android.graphics.Point
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
@Suppress("DEPRECATION") /*Permet d'ajouter du son*/


class MainActivity() : AppCompatActivity(), View.OnTouchListener{

    lateinit var drawingView: DrawingView
    lateinit var start : ImageView
    lateinit var jump : ImageView
    lateinit var attack: ImageView
    lateinit var scoretext: TextView
    lateinit var vietext : TextView
    lateinit var personnage : Personnage
    lateinit var mapview: Mapview
    /*lateinit var pause: Button*/
    lateinit var balle : Balle


    var mapavance = true
    var balleavance = true
    var a : Long = 15
    var b = a
    var startpressed = true
    private var soundPool: SoundPool? = null
    private val soundId = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        val soundjump = soundPool!!.load(baseContext, R.raw.cartoonslip, 1)
        val soundattack = soundPool!!.load(baseContext,R.raw.pistol_fire3,1)



        fun playSoundjump() {
                soundPool?.play(soundjump, 1F, 1F, 0, 0, 1F)
        }

        fun playSoundattack(){
            soundPool?.play(soundattack, 1F, 1F, 0, 0, 1F)
        }

        drawingView = findViewById<DrawingView>(R.id.vMain)

        start = findViewById(R.id.start)
        jump = findViewById(R.id.jump)
        attack = findViewById(R.id.attack)
        scoretext = findViewById(R.id.score)
        vietext = findViewById(R.id.vie)
        start.setOnTouchListener(this)


        Thread{
            while(startpressed){
                start.rotationY +=1
                Thread.sleep(15)
            }
        }.start()
        var lastClickTime = 0L


        jump.setOnClickListener {
            playSoundjump()
            if (SystemClock.elapsedRealtime() - lastClickTime < 300) { /*Permet de ne pas cliquer plusieurs fois sur le bouton JUMP*/
                return@setOnClickListener
            }
            lastClickTime = SystemClock.elapsedRealtime()
            drawingView.player.jump()

        }
        attack.setOnClickListener {
            playSoundattack()
            drawingView.player.attack()
        }

    }

    override fun onTouch(v : View, e : MotionEvent) : Boolean {
        val action = e.action
        val touchPoint = Point(e.x.toInt(), e.y.toInt())

        when(v.id) {
            start.id -> {
                startpressed = false
                if (action == MotionEvent.ACTION_DOWN) {
                    Thread {

                        start.visibility = View.INVISIBLE
                        while (mapavance) {
                            runOnUiThread(
                                Runnable {
                                    vietext.setText("Life : ${drawingView.player.life}")
                                })
                            drawingView.deplacementcontinue()
                            drawingView.accelerelejeu()
                            Thread.sleep(b)

                        }
                    }.start()
                }
            }


        }
        return true
        }

    override fun onPause() {
        super.onPause()
        drawingView.pause()
    }

    override fun onResume() {
        super.onResume()
        drawingView.resume()
    }



}