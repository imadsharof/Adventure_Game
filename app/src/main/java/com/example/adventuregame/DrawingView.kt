package com.example.adventuregame

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.*
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.random.Random

open class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback,Runnable {

    lateinit var canvas: Canvas
    val backgroundPaint = Paint()
    var drawing = false
    lateinit var thread: Thread
    lateinit var mainActivity: MainActivity
    lateinit var monstres: Monstres
    var screenWidth = 0f
    var screenHeight = 0f
    var gameover = false
    val activity = context as FragmentActivity
    var nombregamelancee = 0
    var clickabled = true


    var parois = arrayOf(
        Parois(0f, 0f, 0f, 0f, this), /*Sol*/
        Parois(0f, 0f, 0f, 0f, this), /*Terre*/
        Parois(0f, 0f, 0f, 0f, this),/*Nuage1*/
        Parois(0f, 0f, 0f, 0f, this),/*Nuage2*/
        Parois(0f, 0f, 0f, 0f, this),/*Nuage3*/
        Parois(0f, 0f, 0f, 0f, this),/*ScreenOutdroite*/
        Parois(0f, 0f, 0f, 0f, this))/*ScreenOutGauche*/
    var personnage = arrayOf(Personnage(0f,0f,0f,0f,this,0), /*Dessin du perso principal*/
                            Personnage(0f,0f,0f,0f,this,0)) /*Dessin barre de vie*/
    var recompense = Récompense(0f, 0f, 0f, 0f, this)

    var mapview = Mapview(0f,0f,0f,0f,this)

    var balle = Balle(0f,0f,0f,0f,this)

    var lesmonstres = ArrayList<Monstres>()

    val sol = parois[0]
    val terre = parois[1]
    val nuage1 = parois[2]
    val nuage2 = parois[3]
    val nuage3 = parois[4]
    val screenoutdroite = parois[5]
    val screenoutgauche = parois[6]

    val player = personnage[0]
    var barrevie = personnage[1]

    var life = 3
    var random = java.util.Random()
    var score = 0


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

        mapview.dessindelamap()

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
            screenoutgauche.draw(canvas,255,255,255)
            screenoutdroite.draw(canvas,255,255,255)
            recompense.draw(canvas)
            player.draw(canvas,0,14,255)
            barrevie.draw(canvas,67,163,62)
            if(lesmonstres[nombregamelancee].MonstresOnScreen){lesmonstres[nombregamelancee].draw(canvas,255,0,0)}
            if(balle.BalleOnScreen){balle.draw(canvas,255,164,0)}
            holder.unlockCanvasAndPost(canvas)
        }
    }



    fun deplacementcontinue(){

        /*Déplacement du personnage début du jeu*/
        if (player.x2 < screenWidth / 2) {
            player.droite()
            barrevie.droite()
            player.x1 += 10f
            barrevie.x1 += 10f
            player.x2 += 10f
            barrevie.x2 += 10f

        }

        /*Déplacement des nuages et maps*/
        else if (player.x2 >= screenWidth / 2f ) {
            nuage1.x1 -= 20f
            nuage1.x2 -= 20f
            nuage2.x1 -= 20f
            nuage2.x2 -= 20f
            nuage3.x1 -= 20f
            nuage3.x2 -= 20f
            sol.x1 -= 10f
            sol.x2 -= 10f
            sol.deplacementmap()
            nuage1.deplacementmap()
            nuage2.deplacementmap()
            nuage3.deplacementmap()
            /*Déplacement monstres*/
            lesmonstres[nombregamelancee]
            lesmonstres[nombregamelancee].gauche()
            lesmonstres[nombregamelancee].x1 -= 10f
            lesmonstres[nombregamelancee].x2 -= 10f

            if ((lesmonstres[nombregamelancee].r.left == player.r.right && lesmonstres[nombregamelancee].r.top < player.r.bottom) && lesmonstres[nombregamelancee].MonstresOnScreen) { /*Si perso touche monstre*/
                player.life -= 1
                if (player.y2 == screenHeight / 2f + 375f) {
                    barrevie.x2 -= 50f / 3
                    barrevie.setRect()
                    barrevie.draw(canvas, 67, 163, 62)
                }
                else {Timer("SettingUp", false).schedule(500) {
                    barrevie.x2 -= 50f / 3
                    barrevie.setRect()
                    barrevie.draw(canvas, 67, 163, 62)}}

            }



            /* else if (lesmonstres[nombregamelancee].r.top == player.r.bottom && lesmonstres[nombregamelancee].MonstresOnScreen) {
                    player.life -= 1
                    barrevie.x2 -= 50f/3
                    barrevie.setRect()
                    barrevie.draw(canvas,67,163,62)
                }*/
                if (player.life == 0) {
                    player.dead = true
                }}



            /*Demander AIDE assistant pour réduire code*/

            if (nuage3.x2 == 1700f) {
                nuage1.x1 = 1900f
                nuage1.y1 = 50f
                nuage1.x2 = 2600f
                nuage1.y2 = 100f
                nuage1.setRect()
                nuage1.draw(canvas, 255, 255, 255)

            } else if (nuage3.x2 == 800f) {
                nuage2.x1 = 1900f
                nuage2.y1 = 50f
                nuage2.x2 = 2600f
                nuage2.y2 = 100f
                nuage2.setRect()
                nuage2.draw(canvas, 255, 255, 255)
            } else if (nuage1.x1 == 100f) {
                nuage3.x1 = 1900f
                nuage3.y1 = 50f
                nuage3.x2 = 2600f
                nuage3.y2 = 100f
                nuage3.setRect()
            }

            if(lesmonstres[nombregamelancee].x2 == 0f ) {
                val grandsmonstres =Grandsmonstres(screenWidth ,screenHeight/2f + 275f,screenWidth+100f,screenHeight/2f + 375f,this)
                val longsmonstres = Longsmonstres(screenWidth,screenHeight/2f +20f,screenWidth+50f,screenHeight/2f + 375f,this)
                val petitsmonstres = Petitsmonstres(screenWidth,screenHeight/2f+300f,screenWidth+50f,screenHeight/2f + 375f,this)
                val listedemonstre = listOf(
                    grandsmonstres,
                    longsmonstres,
                petitsmonstres)
                lesmonstres[nombregamelancee].MonstresOnScreen = true
                /*lesmonstres.remove(lesmonstres[nombregamelancee])
                lesmonstres.add(listedemonstre.random())*/
                lesmonstres.set(0,listedemonstre.random())

            }

        }



    override fun run() {
        while (drawing) {
            draw()
            if (player.dead) {
                gameover()
                drawing = false
            }
        }

    }

    fun score(a : Int) {
        score += a
        /*if(balle.BalleOnScreen && balle.r.intersect(lesmonstres[nombregamelancee].r )){
             score +=100// 1 monstre tué donne 100 points
        }
        else if(!player.r.intersect(lesmonstres[nombregamelancee].r) &&
                    player.r.bottom < lesmonstres[nombregamelancee].r.top)
                    {
            score += 50 // 1 monstre esquivé donne 75 points
        }*/
    }

    private fun gameover() {
        drawing = false
        showGameOverDialog(R.string.lose)
        gameover = true
    }

    private fun showGameOverDialog(messageId: Int) {
        class GameResult: DialogFragment() {
            override fun onCreateDialog(bundle: Bundle?): Dialog {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle(resources.getString(messageId))
                builder.setMessage(
                    resources.getString(R.string.results_format, score)
                )
                builder.setPositiveButton(R.string.reset_game,
                    DialogInterface.OnClickListener { _, _->newGame() }
                )
                return builder.create()
            }
        }

        activity.runOnUiThread(
            Runnable {
                val ft = activity.supportFragmentManager.beginTransaction()
                val prev =
                    activity.supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                val gameResult = GameResult()
                gameResult.isCancelable = false
                gameResult.show(ft,"dialog")
            }
        )
    }

    fun newGame() : Boolean {
        nombregamelancee +=1
        mapview.dessindelamap()
        drawing = true
        score = 0

        if (gameover) {
            gameover = false
            thread = Thread(this)
            thread.start()


        }
        return true
    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int,
                                width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = Thread(this)
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.join()
    }
}
