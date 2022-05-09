package com.example.adventuregame

class Mapview(var x1: Float, var y1: Float, var x2: Float, var y2: Float,var view: DrawingView) {

    fun drawsol(sol: Parois) {
        sol.x1 = 0f
        sol.y1 = view.screenHeight / 2f + 375f
        sol.x2 = (1000000f)
        sol.y2 = (view.screenHeight / 2f + 400f)
        sol.setRect()
    }

    fun drawterre(terre:Parois){
        terre.x1 = 0f
        terre.y1 = view.screenHeight/2f+ 400f
        terre.x2 = view.screenWidth/1f
        terre.y2 = view.screenHeight/1f
        terre.setRect()
    }

    fun drawplayer(player : Personnage){
        player.x1 = 50f
        player.y1 = view.screenHeight/2f + 325f /*personnage.y1 = personnage.y2 - 100f*/
        player.x2 = 100f /*longueur perso = x2 - x1 = 50 f*/
        player.y2 = view.screenHeight/2f + 375f /*personnage.y2 = sol.y1*/
        player.life = 3
        player.setRect()
    }

    fun drawbarrevie(barrevie : Personnage){
        barrevie.x1 = 50f
        barrevie.y1 = view.screenHeight/2f + 300f
        barrevie.x2 = 100f
        barrevie.y2 = view.screenHeight/2f + 310f
        barrevie.setRect()
    }

    fun drawballe(balle: Balle){
        balle.x1 =view.screenWidth / 2f
        balle.y1 = view.screenHeight/2f + 340f
        balle.x2 =view.screenWidth / 2f+30f
        balle.y2 = view.screenHeight/2f + 360f
        balle.setRect()
    }

    fun drawrecompense(recompense : Récompense){
        recompense.x1 = 2000f
        recompense.y1 = view.screenHeight/2f + 350f
        recompense.x2 = 2050f                       /* recompense à la fin du jeu */
        recompense.y2 = view.screenHeight/2f + 400f
        recompense.setRect()
    }

    fun drawnuage1(nuage1: Parois){
        nuage1.x1 = 100f
        nuage1.y1 = 50f
        nuage1.x2 = 800f
        nuage1.y2 = 100f
        nuage1.setRect()
    }

    fun drawnuage2(nuage2 : Parois){
        nuage2.x1 = 1000f
        nuage2.y1 = 50f
        nuage2.x2 = 1700f
        nuage2.y2 = 100f
        nuage2.setRect()
    }

    fun drawnuage3(nuage3 : Parois){
        nuage3.x1 = 1900f
        nuage3.y1 = 50f
        nuage3.x2 = 2600f
        nuage3.y2 = 100f
        nuage3.setRect()
    }
    fun drawscreenout(screenout : Parois){
        screenout.x1 = view.screenWidth
        screenout.y1 = 0f
        screenout.x2 = view.screenWidth + 20f
        screenout.y2 =  view.screenHeight
        screenout.setRect()
    }

    fun drawmonstres(){
        val listemonstre = listOf(Grandsmonstres(2000f,view.screenHeight/2f + 275f,2100f,view.screenHeight/2f + 375f,view),
            Longsmonstres(2000f,view.screenHeight/2f + 210f,2050f,view.screenHeight/2f + 375f,view),
            Petitsmonstres(2000f,view.screenHeight/2f+300f,2050f,view.screenHeight/2f + 375f,view))
        val monstrerandom = listemonstre.random()

        view.lesmonstres.add(monstrerandom)
        view.lesmonstres[view.nombregamelancee].setRect()
    }

    fun resetgame(){
        view.lesmonstres = ArrayList<Monstres>()
        view.parois = arrayOf(
            Parois(0f, 0f, 0f, 0f, view), /*Sol*/
            Parois(0f, 0f, 0f, 0f, view), /*Terre*/
            Parois(0f, 0f, 0f, 0f, view),/*Nuage1*/
            Parois(0f, 0f, 0f, 0f, view),/*Nuage2*/
            Parois(0f, 0f, 0f, 0f, view),/*Nuage3*/
            Parois(0f, 0f, 0f, 0f, view))/*ScreenOut*/
        view.personnage = arrayOf(Personnage(0f,0f,0f,0f,view,0), /*Dessin du perso principal*/
            Personnage(0f,0f,0f,0f,view,0)) /*Dessin barre de vie*/
        view.recompense = Récompense(0f, 0f, 0f, 0f, view)
    }

}