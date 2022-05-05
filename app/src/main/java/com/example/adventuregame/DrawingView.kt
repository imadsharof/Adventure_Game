package com.example.adventuregame

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback,Runnable {

    lateinit var canvas: Canvas
    val backgroundPaint = Paint()
    var drawing = false
    lateinit var thread: Thread
    var screenWidth = 0f
    var screenHeight = 0f
    var gameover = false
    val activity = context as FragmentActivity


    val parois = arrayOf(
        Parois(0f, 0f, 0f, 0f, this), /*Sol*/
        Parois(0f, 0f, 0f, 0f, this), /*Terre*/
        Parois(0f, 0f, 0f, 0f, this),/*Nuage1*/
        Parois(0f, 0f, 0f, 0f, this),/*Nuage2*/
        Parois(0f, 0f, 0f, 0f, this))/*Nuage3*/

    val personnage = Personnage(0f,0f,0f,0f,this)
    val recompense = Récompense(0f, 0f, 0f, 0f, this)
    var lesmonstres = ArrayList<Monstres>()
    val sol = parois[0]
    val terre = parois[1]
    val nuage1 = parois[2]
    val nuage2 = parois[3]
    val nuage3 = parois[4]
    var life = 3


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


        /* Les valeurs ci-dessous ont été trouvé par essais-erreurs */

/*Dessin du sol : (épaisseur sol = 25f)*/
        sol.x1 = (0f)
        sol.y1 = (screenHeight/2f + 375f) /*x1*/
        sol.x2= (screenWidth/1f)  /*y1*/
        sol.y2 = (screenHeight/2f+ 400f) /*parois.y2 = parois.y1 + ( 25f )*/
        sol.setRect()
/*Dessin de la Terre*/

        terre.x1 = 0f
        terre.y1 = screenHeight/2f+ 400f
        terre.x2 = screenWidth/1f
        terre.y2 = screenHeight/1f
        terre.setRect()

/* Dessin du personnage : (base personnage = 50f, hauteur = 50f)  */

        personnage.x1 = 50f
        personnage.y1 = screenHeight/2f + 325f /*personnage.y1 = personnage.y2 - 100f*/
        personnage.x2 = 100f /*longueur perso = x2 - x1 = 50 f*/
        personnage.y2 = screenHeight/2f + 375f /*personnage.y2 = sol.y1*/
        personnage.setRect()

        /*Dessin monstre*/

        var a = 1900f
        val b = screenHeight/2f + 275f
        for (i in 0..20){ /*Ajout de 20 monstres*/
        lesmonstres.add(Monstres(a,b,a+100f,b + 100f,this))
        lesmonstres[i].setRect()
        a+= 1000f}

        /* Dessin récompense finale du jeu */

        recompense.x1 = 3000f
        recompense.y1 = screenHeight/2f + 350f
        recompense.x2 = 3550f                       /* recompense à la fin du jeu */
        recompense.y2 = screenHeight/2f + 400f
        recompense.setRect()

/*Dessin des Nuages*/
        /*Nuage 1*/
        nuage1.x1 = 100f
        nuage1.y1 = 50f
        nuage1.x2 = 800f
        nuage1.y2 = 100f
        nuage1.setRect()

        /*Nuage 2*/
        nuage2.x1 = 1000f
        nuage2.y1 = 50f
        nuage2.x2 = 1700f
        nuage2.y2 = 100f
        nuage2.setRect()

        /*Nuage 3*/

        nuage3.x1 = 1900f
        nuage3.y1 = 50f
        nuage3.x2 = 2600f
        nuage3.y2 = 100f
        nuage3.setRect()


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
            recompense.draw(canvas)
            /*if (!personnage.dead){}*/ personnage.draw(canvas)
            for (m in lesmonstres){m.draw(canvas)}
            holder.unlockCanvasAndPost(canvas)
        }
    }

    fun deplacementdroite(){
        if (personnage.x2 < screenWidth / 2) {
            personnage.droite()
            personnage.x1 += 10f
            personnage.x2 += 10f
            personnage.setRect()
            for (m in lesmonstres) { /*Si perso touche monstre*/
                m.setRect()
                if ((m.r.left == personnage.r.right && m.r.top < personnage.r.top)) {
                    life -= 1
                } else if (m.r.top == personnage.r.bottom) {
                    life -= 1
                }
                if (life == 0) {
                    personnage.dead = true
                }
            }
        }

        /*Déplacement des nuages et maps*/
        else if (personnage.x2 >= screenWidth / 2f ) {
            nuage1.x1 -= 20f
            nuage1.x2 -= 20f
            nuage2.x1 -= 20f
            nuage2.x2 -= 20f
            nuage3.x1 -= 20f
            nuage3.x2 -= 20f

            nuage1.deplacementmap()
            nuage2.deplacementmap()
            nuage3.deplacementmap()
            /*Déplacement monstres*/
            for (m in lesmonstres) { /*Si perso touche monstre*/
                m.gauche()
                m.x1 -= 10f
                m.x2 -= 10f
                m.setRect()
                if ((m.r.left == personnage.r.right && m.r.top < personnage.r.top)) {
                    life -= 1
                } else if (m.r.top == personnage.r.bottom) {
                    life -= 1
                }
                if (life == 0) {
                    personnage.dead = true
                }
                /*personnage.dead = true*/
            }

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
        }
    }


    override fun run() {
        while (drawing) {
            draw()
            if (personnage.dead) {
                /*gameover()*/
                drawing = false
            }
        }

    }

    /*fun gameover() {
        drawing = false
        showGameOverDialog(R.string.lose)
        gameover = true
    }*/

    fun showGameOverDialog(messageId: Int) {
        class GameResult: DialogFragment() {
            override fun onCreateDialog(bundle: Bundle?): Dialog {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle(resources.getString(messageId))
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
