package com.example.adventuregame

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.os.SystemClock
import androidx.fragment.app.FragmentActivity
import java.util.*
import kotlin.concurrent.schedule



class Personnage(var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView,var life : Int,context: Context){

    val r = RectF(x1, y1, x2, y2)
    private val PersonnagePaint = Paint()
    var dx = 1
    var dy = 0
    private var random = Random()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))
    lateinit var monstres : Monstres
    var dead = false
    lateinit var mainActivity: MainActivity
    var saute = false
    val activity = context as FragmentActivity



    /* Dessin du personnage représenté par un rectangle*/
    fun draw(canvas: Canvas,red : Int,green : Int, blue : Int) {
        PersonnagePaint.color = Color.rgb(red,green,blue)
        canvas.drawRect(r, PersonnagePaint)

    }

    fun setRect() {
        r.set(x1, y1, x2, y2)
    }


    /* le personnage se déplace vers le haut et passé un certain délai il redescend. Ce délai est
    * choisi de manière à rendre le déplacement fluide */

    fun saute() {
        dy = -2
        r.offset(0F*dx, 100.0F*dy)
        view.player.y1 -= 200f
        view.player.y2 -=200f
        view.barrevie.y1 -= 200f
        view.barrevie.y2 -= 200f
        Timer("SettingUp", false).schedule(310) {
            saute = true
            r.offset(0F*dx, -100.0F*dy)
            view.player.y1 += 200f
            view.player.y2 +=200f
            view.barrevie.y1 += 200f
            view.barrevie.y2 += 200f
        }

        saute = false
    }

    fun droite() {
        dx = 1
        r.offset(10.0F*dx,0.0F*dy)
    }

    /*fun attack(){
        (activity as MainActivity).attack.isClickable = false
        if(!drawingView.balle.BalleOnScreen){/*Redessine la Balle*/
            drawingView.mapview.drawballe(drawingView.balle)
            drawingView.balle.draw(drawingView.canvas,255,164,0)
        }
        drawingView.balle.afficheballe()
        balleavance = true
        Thread{
            attack.isClickable = false
            while(balleavance) {
                drawingView.balle.afficheballe()
                drawingView.balle.droite()
                if (drawingView.balle.r.intersect(drawingView.lesmonstres[drawingView.nombregamelancee].r)
                    &&( drawingView.lesmonstres[drawingView.nombregamelancee].numero == 1 || drawingView.lesmonstres[drawingView.nombregamelancee].numero == 0 )
                    && drawingView.lesmonstres[drawingView.nombregamelancee].MonstresOnScreen
                ) {
                    drawingView.score(100)
                    runOnUiThread(
                        Runnable {
                            scoretext.setText(
                                "Score : ${drawingView.score}"
                            )

                        })
                    balleavance = false
                    drawingView.balle.supprimeballe()
                    drawingView.lesmonstres[drawingView.nombregamelancee].MonstresOnScreen =
                        false
                    attack.isClickable = true
                }

                else if (drawingView.balle.r.intersect(drawingView.lesmonstres[drawingView.nombregamelancee].r)
                    && drawingView.lesmonstres[drawingView.nombregamelancee].numero == 2){
                    balleavance = false
                    drawingView.balle.supprimeballe()
                    attack.isClickable = true
                }
                else if (drawingView.balle.r.left ==drawingView.screenoutdroite.r.left ){
                    balleavance = false
                    drawingView.balle.supprimeballe()
                    attack.isClickable = true
                }
                Thread.sleep(15)
            }}.start()

    }*/


}

