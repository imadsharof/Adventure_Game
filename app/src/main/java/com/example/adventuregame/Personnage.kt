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

    fun jump(){
        view.player.saute()
        view.barrevie.saute()
        //if(!drawingView.player.r.intersect(drawingView.lesmonstres[drawingView.nombregamelancee].r)){
        if(view.player.r.bottom<view.lesmonstres[view.nombregamelancee].r.top
            &&view.lesmonstres[view.nombregamelancee].x1 >( view.screenWidth/2f)
            &&view.lesmonstres[view.nombregamelancee].x1 <( view.screenWidth)
            &&view.lesmonstres[view.nombregamelancee].MonstresOnScreen){
            view.score(50)
            (activity as MainActivity).runOnUiThread(
                Runnable {
                    (activity as MainActivity).scoretext.setText("Score : ${view.score}")

                })
        }
    }

    fun droite() {
        dx = 1
        r.offset(10.0F*dx,0.0F*dy)
    }

    fun attack(){
        (activity as MainActivity).attack.isClickable = false
        if(!view.balle.BalleOnScreen){/*Redessine la Balle*/
            view.mapview.drawballe(view.balle)
            view.balle.draw(view.canvas,255,164,0)
        }
        view.balle.afficheballe()
        (activity as MainActivity).balleavance = true
        Thread{
            (activity as MainActivity).attack.isClickable = false
            while((activity as MainActivity).balleavance) {
                view.balle.afficheballe()
                view.balle.droite()
                if (view.balle.r.intersect(view.lesmonstres[view.nombregamelancee].r)
                    &&( view.lesmonstres[view.nombregamelancee].numero == 1 || view.lesmonstres[view.nombregamelancee].numero == 0 )
                    && view.lesmonstres[view.nombregamelancee].MonstresOnScreen
                ) {
                    view.score(100)
                    (activity as MainActivity).runOnUiThread(
                        Runnable {
                            (activity as MainActivity).scoretext.setText(
                                "Score : ${view.score}"
                            )

                        })
                    (activity as MainActivity).balleavance = false
                    view.balle.supprimeballe()
                    view.lesmonstres[view.nombregamelancee].MonstresOnScreen =
                        false
                    (activity as MainActivity).attack.isClickable = true
                }

                else if (view.balle.r.intersect(view.lesmonstres[view.nombregamelancee].r)
                    && view.lesmonstres[view.nombregamelancee].numero == 2){
                    (activity as MainActivity).balleavance = false
                    view.balle.supprimeballe()
                    (activity as MainActivity).attack.isClickable = true
                }
                else if (view.balle.r.left ==view.screenoutdroite.r.left ){
                    (activity as MainActivity). balleavance = false
                    view.balle.supprimeballe()
                    (activity as MainActivity).attack.isClickable = true
                }
                Thread.sleep(15)
            }}.start()

    }

    fun animationdebutjeu() {
        if (view.player.x2 < view.screenWidth / 2) {
            view.player.droite()
            view.barrevie.droite()
            view.player.x1 += 10f
            view.barrevie.x1 += 10f
            view.player.x2 += 10f
            view.barrevie.x2 += 10f
        }
    }

    fun estilmort(){
        if (view.player.life <= 0 ) {
            dead = true
        }}


}

