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
    var personnage = arrayOf(Personnage(0f,0f,0f,0f,this,0,context), /*Dessin du perso principal*/
                            Personnage(0f,0f,0f,0f,this,0,context)) /*Dessin barre de vie*/

    var mapview = Mapview(0f,0f,0f,0f,this,context)

    var balle = Balle(0f,0f,0f,0f,this)

    var lesmonstres = ArrayList<Monstres>()
    var potionvie = ArrayList<Potionvie>()

    val sol = parois[0]
    val terre = parois[1]
    val nuage1 = parois[2]
    val nuage2 = parois[3]
    val nuage3 = parois[4]
    val screenoutdroite = parois[5]
    val screenoutgauche = parois[6]

    val player = personnage[0]
    var barrevie = personnage[1]


    var random = java.util.Random()
    var score = 0
    val facteurdiminutionbarredevie = 10



    init {
        backgroundPaint.color = Color.rgb(153,204,255)
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

    fun draw() { /*Dessinr le jeu complet*/

        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(
                0f, 0f, canvas.width.toFloat(),
                canvas.height.toFloat(), backgroundPaint
            )
            sol.draw(canvas,0,102,0)
            terre.draw(canvas,103,41,11)
            nuage1.draw(canvas,255,255,255)
            nuage2.draw(canvas,255,255,255)
            nuage3.draw(canvas,255,255,255)
            screenoutgauche.draw(canvas,255,255,255)
            screenoutdroite.draw(canvas,255,255,255)
            player.draw(canvas,0,14,255)
            barrevie.draw(canvas,0,255,14)
            if(lesmonstres[nombregamelancee].MonstresOnScreen){lesmonstres[nombregamelancee].draw(canvas,255,0,0)}
            if(balle.BalleOnScreen){balle.draw(canvas,255,164,0)}
            potionvie[nombregamelancee].draw(canvas,0,255,14)
            holder.unlockCanvasAndPost(canvas)
        }
    }



    fun deplacementcontinue() {

        player.animationdebutjeu() /*Animation déplacement du personnage début du jeu*/
        nuage1.animationdesnuages() /*Animation des nuages*/
        sol.deplacementsol() /*Déplacement du sol à l'infini*/
        potionvie[nombregamelancee].deplacementpotiondevie() /*Déplacement de la potion de vie*/
        lesmonstres[nombregamelancee].deplacementmonstres() /*Déplacement des monstres*/

            /*Reconait quel monstre le joueur a touché et attaque différemment selon le monstre touché*/
        if (lesmonstres[nombregamelancee].numero == 0) {
            lesmonstres[nombregamelancee].attack()
            }
        else if (lesmonstres[nombregamelancee].numero == 1) {
            lesmonstres[nombregamelancee].attack()
            }
        else if (lesmonstres[nombregamelancee].numero == 2) {
            lesmonstres[nombregamelancee].attack()
            }

        potionvie[nombregamelancee].regenerevie() /*Si joueur touche potion, il gagne de la vie*/
        player.estilmort() /*Vérifie si le joueur est mort*/
        lesmonstres[nombregamelancee].estilparti()/*Vérifie si le monstre est bien sorti de l'écran afin d'ajouter un nouveau monstre random*/
        potionvie[nombregamelancee].estilparti() /*Redessinee de la potion de vie lorsqu'elle sort de l'écran*/

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

    fun accelerelejeu(){ /*Crééons de la complexité au jeu :
    plus le joueur avance, plus la vitesse du jeu augmente*/
        if(sol.x1 == -5000f  ||
            sol.x1 == -10000f||
            sol.x1 == -15000f||
            sol.x1 == -20000f||
            sol.x1 == -30000f||
            sol.x1 == -35000f||
            sol.x1 == -40000f||
            sol.x1 == -50000f||
            sol.x1 == -60000f ){(activity as MainActivity).b -= 1}
    }


    fun score(a : Int) {
        score += a /*Le score augmente d'une valeur qu'on définit*/
        if(a ==0){score = 0} //Réinitialise le score à 0
    }

    private fun gameover() {/*La partie est perdue, interruptiond des threads*/
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

    fun newGame() : Boolean { /*Relance une nouvelle partie*/
        nombregamelancee +=1
        mapview.dessindelamap()
        drawing = true
        player.dead = false
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
