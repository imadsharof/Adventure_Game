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
        /*Thread{runOnUiThread(
            Runnable {
                vietext.setText("Vie : ${drawingView.life}")
            })

        }.start()*/

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
            drawingView.player.saute()
            drawingView.barrevie.saute()
            //if(!drawingView.player.r.intersect(drawingView.lesmonstres[drawingView.nombregamelancee].r)){
            if(drawingView.player.r.bottom<drawingView.lesmonstres[drawingView.nombregamelancee].r.top
                &&drawingView.lesmonstres[drawingView.nombregamelancee].x1 >( drawingView.screenWidth/2f)
                &&drawingView.lesmonstres[drawingView.nombregamelancee].x1 <( drawingView.screenWidth)
                &&drawingView.lesmonstres[drawingView.nombregamelancee].MonstresOnScreen){
                drawingView.score(50)
                runOnUiThread(
                    Runnable {
                        scoretext.setText("Score : ${drawingView.score}")

                    })
            }



           // }

        }

        attack.setOnClickListener {
            playSoundattack()
            attack.isClickable = false
            if(!drawingView.balle.BalleOnScreen){/*Redessine la Balle*/
                drawingView.mapview.drawballe(drawingView.balle)
                drawingView.balle.draw(drawingView.canvas,255,164,0)
            }
            drawingView.balle.afficheballe()
            balleavance = true
             Thread{
                 attack.isClickable = false
                while(balleavance){
                    drawingView.balle.afficheballe()
                    drawingView.balle.droite()
                    if(drawingView.balle.r.intersect(drawingView.lesmonstres[drawingView.nombregamelancee].r)
                        && drawingView.lesmonstres[drawingView.nombregamelancee].numero == 1
                        && drawingView.lesmonstres[drawingView.nombregamelancee].MonstresOnScreen){
                        drawingView.score(100)
                        runOnUiThread(
                            Runnable {
                                scoretext.setText("Score : ${drawingView.score}"
                                )

                            })
                        balleavance = false
                        drawingView.balle.supprimeballe()
                        drawingView.lesmonstres[drawingView.nombregamelancee].MonstresOnScreen = false
                        attack.isClickable = true
                    }
                    else if (drawingView.balle.r.left ==drawingView.screenoutdroite.r.left ){
                        balleavance = false
                        drawingView.balle.supprimeballe()
                        attack.isClickable = true
                    }
                    Thread.sleep(15)
                }}.start()

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
                                    vietext.setText("Vie : ${drawingView.player.life}")
                                })
                            drawingView.deplacementcontinue()

                            if(drawingView.sol.x1 == -5000f  ||
                                drawingView.sol.x1 == -10000f||
                                drawingView.sol.x1 == -15000f||
                                drawingView.sol.x1 == -20000f||
                                drawingView.sol.x1 == -30000f||
                                drawingView.sol.x1 == -35000f||
                                drawingView.sol.x1 == -40000f||
                                drawingView.sol.x1 == -50000f||
                                drawingView.sol.x1 == -60000f ){b -= 1}
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