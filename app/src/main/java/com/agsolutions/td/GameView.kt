package com.agsolutions.td

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.Main.MainActivity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.math.atan2


class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes),
    SurfaceHolder.Callback {

    companion object {

        var paintTowerDmgDone = Paint ()
        var paintBurnDmgDone = Paint ()
        var paintPoisonDmgDone = Paint ()
        var paintPestDmgDone = Paint ()
        var paintIceDmgDone = Paint ()
        var paintEarthDmgDone = Paint ()
        var paintWizardDmgDone = Paint ()
        var paintMoonDmgDone = Paint ()
        var paintBombDmgDone = Paint ()

        var towerBase: Bitmap? = null
        var dragRect = Rect(0, 0, 0, 0)
        var dragRectList = mutableListOf<Rect>(
            Rect(0, 0, 1600, 300),
            Rect(0, 1300, 1600, 1600),

            Rect(200, 950, 950, 1100),
            Rect(200, 475, 850, 625),
            Rect(900, 950, 1000, 1250),
            Rect(375, 475, 525, 850),
            Rect(200, 475, 350, 1100),
            Rect(750, 475, 875, 1250)
        )
    }

    private var thread: GameThread
    val enemy = Enemy(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0)
    var firstBoot = true


    var rectBackground = Rect(0, 0, 1200, 1800)
    var rectBackgroundStartScreen = Rect(0, 0, 1200, 1800)
    var creepRect = Rect(0, 0, 0, 0)
    var towerFalling: Bitmap? = null
    var towerTowerArray: ArrayList<Bitmap>? = null
    var backgroundMap1DayBmp: Bitmap? = null
    var backgroundMap1NightBmp: Bitmap? = null
    var backgroundMap1Mode2DayBmp: Bitmap? = null
    var backgroundMap1Mode2NightBmp: Bitmap? = null
    var backgroundDayMap2: Bitmap? = null
    var backgroundNightMap2: Bitmap? = null

    var explosion: Bitmap? = null
    var explosion2: Bitmap? = null
    var shootBulletPic: Bitmap? = null
    var shootSplashPic: Bitmap? = null
    var shootBouncePic: Bitmap? = null
    var shootMultiPic: Bitmap? = null
    var shootMultiPicDay: Bitmap? = null
    var shootChainLightningPic: Bitmap? = null
    var shootButterflyArray: ArrayList<Bitmap>? = null
    var shootPoisonArray: ArrayList<Bitmap>? = null
    var mineArray: ArrayList<Bitmap>? = null
    var touchArray: ArrayList<Bitmap>? = null
    var fingerClick: Bitmap? = null
    var creepOutline: Bitmap? = null
    var talentP: Bitmap? = null

    var rectExplosion = Rect(0, 0, 24, 29)
    var paintRange = Paint()
    var paintLaser = Paint ()
    var paintHpBar = Paint()
    var paintHpBarNight = Paint()
    var paintHpBarBack = Paint()
    var paintLasso = Paint()
    var paintManaShield = Paint()
    var paintShield = Paint()
    var paintText = Paint ()

    var towerGunBasic: Bitmap? = null
    var towerGunBlue: Bitmap? = null
    var towerGunOrange: Bitmap? = null
    var towerGunPurple: Bitmap? = null



    //initialize items----------------------------------------------------------------------------

    init {
        paintHpBarBack.color = Color.BLACK
        paintHpBar.color = Color.parseColor("#00B300")
        paintHpBarNight.color = Color.parseColor("#005900")
        paintLasso.color = Color.YELLOW
        paintRange.color = Color.YELLOW
        paintRange.style = Paint.Style.STROKE
        paintManaShield.color = resources.getColor(R.color.ManaShield)
        paintShield.color = resources.getColor(R.color.Shield)
        paintLaser.color = Color.YELLOW
        paintLaser.style = Paint.Style.STROKE
        paintLaser.strokeWidth = 4f
        paintText.color = Color.RED
        paintText.style = Paint.Style.FILL_AND_STROKE
        paintText.isAntiAlias = true
        paintText.textSize = 40f
        paintText.setShadowLayer(10f,10f, 10f, Color.BLACK)
        paintTowerDmgDone.color = Color.WHITE
        paintTowerDmgDone.style = Paint.Style.FILL_AND_STROKE
        paintTowerDmgDone.isAntiAlias = true
        paintTowerDmgDone.textSize = 30f
        paintTowerDmgDone.letterSpacing = -0.15f
        paintTowerDmgDone.setShadowLayer(5f,0f, 0f, Color.BLACK)
        paintBurnDmgDone.color = Color.parseColor("#F6546A")
        paintBurnDmgDone.style = Paint.Style.FILL_AND_STROKE
        paintBurnDmgDone.isAntiAlias = true
        paintBurnDmgDone.textSize = 30f
        paintBurnDmgDone.letterSpacing = -0.15f
        paintBurnDmgDone.setShadowLayer(5f,0f, 0f, Color.BLACK)
        paintPoisonDmgDone.color = Color.parseColor("#ADEAAD")
        paintPoisonDmgDone.style = Paint.Style.FILL_AND_STROKE
        paintPoisonDmgDone.isAntiAlias = true
        paintPoisonDmgDone.textSize = 30f
        paintPoisonDmgDone.letterSpacing = -0.15f
        paintPoisonDmgDone.setShadowLayer(5f,0f, 0f, Color.BLACK)
        paintPestDmgDone.color = Color.parseColor("#3C8227")
        paintPestDmgDone.style = Paint.Style.FILL_AND_STROKE
        paintPestDmgDone.isAntiAlias = true
        paintPestDmgDone.textSize = 30f
        paintPestDmgDone.letterSpacing = -0.15f
        paintPestDmgDone.setShadowLayer(5f,0f, 0f, Color.BLACK)
        paintIceDmgDone.color = Color.parseColor("#7FB3FF")
        paintIceDmgDone.style = Paint.Style.FILL_AND_STROKE
        paintIceDmgDone.isAntiAlias = true
        paintIceDmgDone.textSize = 30f
        paintIceDmgDone.letterSpacing = -0.15f
        paintIceDmgDone.setShadowLayer(5f,0f, 0f, Color.BLACK)
        paintEarthDmgDone.color = Color.parseColor("#55253A")
        paintEarthDmgDone.style = Paint.Style.FILL_AND_STROKE
        paintEarthDmgDone.isAntiAlias = true
        paintEarthDmgDone.textSize = 30f
        paintEarthDmgDone.letterSpacing = -0.15f
        paintEarthDmgDone.setShadowLayer(1f,0f, 0f, Color.BLACK)
        paintEarthDmgDone.setShadowLayer(5f,0f, 0f, Color.WHITE)
        paintWizardDmgDone.color = Color.parseColor("#FFEC9E")
        paintWizardDmgDone.style = Paint.Style.FILL_AND_STROKE
        paintWizardDmgDone.isAntiAlias = true
        paintWizardDmgDone.textSize = 30f
        paintWizardDmgDone.letterSpacing = -0.15f
        paintWizardDmgDone.setShadowLayer(5f,0f, 0f, Color.BLACK)
        paintMoonDmgDone.color = Color.parseColor("#0C1593")
        paintMoonDmgDone.style = Paint.Style.FILL_AND_STROKE
        paintMoonDmgDone.isAntiAlias = true
        paintMoonDmgDone.textSize = 30f
        paintMoonDmgDone.letterSpacing = -0.15f
        paintMoonDmgDone.setShadowLayer(1f,0f, 0f, Color.BLACK)
        paintMoonDmgDone.setShadowLayer(5f,0f, 0f, Color.WHITE)
        paintBombDmgDone.color = Color.BLACK
        paintBombDmgDone.style = Paint.Style.FILL_AND_STROKE
        paintBombDmgDone.isAntiAlias = true
        paintBombDmgDone.textSize = 30f
        paintBombDmgDone.letterSpacing = -0.15f
        paintBombDmgDone.setShadowLayer(5f,0f, 0f, Color.WHITE)

        talentP = BitmapFactory.decodeResource(context.resources, R.drawable.talenticondraw)

        towerBase = BitmapFactory.decodeResource(context.resources, R.drawable.towerbasesmall)
        towerFalling = BitmapFactory.decodeResource(context.resources, R.drawable.towerfalling)
        towerGunBasic = BitmapFactory.decodeResource(context.resources, R.drawable.towergunbasic)
        towerGunBlue = BitmapFactory.decodeResource(context.resources, R.drawable.towergunblue)
        towerGunOrange = BitmapFactory.decodeResource(context.resources, R.drawable.towergunorange)
        towerGunPurple = BitmapFactory.decodeResource(context.resources, R.drawable.towergunbig)
            backgroundMap1DayBmp = BitmapFactory.decodeResource(context.resources, R.drawable.map1blur)
            backgroundMap1NightBmp = BitmapFactory.decodeResource(context.resources, R.drawable.map1blurnight)
            backgroundMap1Mode2DayBmp = BitmapFactory.decodeResource(context.resources, R.drawable.map2blur)
            backgroundMap1Mode2NightBmp = BitmapFactory.decodeResource(context.resources, R.drawable.map2blurnight)
        explosion = BitmapFactory.decodeResource(context.resources, R.drawable.explosion)
        explosion2 = BitmapFactory.decodeResource(context.resources, R.drawable.explosiontwo)

        shootBulletPic = BitmapFactory.decodeResource(context.resources, R.drawable.bulletbig)
        shootSplashPic = BitmapFactory.decodeResource(context.resources, R.drawable.rock)
        shootBouncePic = BitmapFactory.decodeResource(context.resources, R.drawable.glaive)
        shootMultiPic = BitmapFactory.decodeResource(context.resources, R.drawable.windmulti)
        shootMultiPicDay = BitmapFactory.decodeResource(context.resources, R.drawable.windmultiday)
        shootChainLightningPic = BitmapFactory.decodeResource(context.resources, R.drawable.lightning)
        val shootButterflyPic1 = BitmapFactory.decodeResource(context.resources, R.drawable.butterfly11)
        val shootButterflyPic2 = BitmapFactory.decodeResource(context.resources, R.drawable.butterfly22)
        val shootButterflyPic3 = BitmapFactory.decodeResource(context.resources, R.drawable.butterfly33)
        shootButterflyArray = arrayListOf<Bitmap>(shootButterflyPic1, shootButterflyPic2, shootButterflyPic3, shootButterflyPic2)
        val shootPoisonPic1 = BitmapFactory.decodeResource(context.resources, R.drawable.poisoncloud1)
        val shootPoisonPic2 = BitmapFactory.decodeResource(context.resources, R.drawable.poisoncloud2)
        shootPoisonArray = arrayListOf<Bitmap>(shootPoisonPic1, shootPoisonPic2)
        val minePic1 = BitmapFactory.decodeResource(context.resources, R.drawable.mine1)
        val minePic2 = BitmapFactory.decodeResource(context.resources, R.drawable.mine2)
        mineArray = arrayListOf<Bitmap>(minePic2, minePic2, minePic1, minePic2, minePic2)
        val touchPic1 = BitmapFactory.decodeResource(context.resources, R.drawable.touch1)
        val touchPic2 = BitmapFactory.decodeResource(context.resources, R.drawable.touch2)
        val touchPic3 = BitmapFactory.decodeResource(context.resources, R.drawable.touch3)
        touchArray = arrayListOf<Bitmap>(touchPic1, touchPic2, touchPic3, touchPic3)

        fingerClick = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclick)

        creepOutline = BitmapFactory.decodeResource(context.resources, R.drawable.creepoutline2)

        // add callback
        holder.addCallback(this)

        // instantiate the game thread
        thread = GameThread(holder, this)

    }

    //surface holder things-----------------------------------------------------------------------

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {

        if (firstBoot) {
            GameThread.running = true
            firstBoot = false
            thread.start()
        } else if (!GameThread.running) {
            GameThread.running = true
        } else if (thread.state == Thread.State.TERMINATED) {
            thread.start()
            GameThread.running = true
        }
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
        thread.interrupt()
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        thread.interrupt()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (!MainActivity.startScreenBool) {
            companionList.readLockEnemy.lock()
            try {

                val x = event!!.x
                val y = event.y

                if (tower(x, y)) {

                } else if ((x > 875 * (companionList.scaleScreen / 10) && x < 1025 * (companionList.scaleScreen / 10)) && (y > 1145 * (companionList.scaleScreen / 10) && y < 1275 * (companionList.scaleScreen / 10)) && companionList.spawnDoubleClickCounter in 1..20 && companionList.mapMode == 1) {
                    companionList.spawnDoubleClick = true
                    companionList.towerClick = false
                    companionList.build = true
                    companionList.buildClickBool = true
                        if (companionList.autoSpawn) {
                            companionList.autoSpawn = false
                            companionList.spawnEnemy = false
                        } else if (!companionList.autoSpawn) {
                            companionList.autoSpawn = true
                            companionList.spawnEnemy = false
                        }
                } else if ((x > 875 * (companionList.scaleScreen / 10) && x < 1025 * (companionList.scaleScreen / 10)) && (y > 1145 * (companionList.scaleScreen / 10) && y < 1275 * (companionList.scaleScreen / 10)) && companionList.mapMode == 1) {
                    companionList.spawnDoubleClickCounter = 1
                    companionList.towerClick = false
                    companionList.build = true
                    companionList.buildClickBool = true
                }else if (enemy(x, y)){
                }else {
                    companionList.towerClick = false
                    companionList.enemyClick = false
                    companionList.build = true
                    companionList.buildClickBool = true
                }

                return super.onTouchEvent(event)
            } finally {
                companionList.readLockEnemy.unlock()
            }
        } else return super.onTouchEvent(event)
    }

    fun enemy (x: Float, y: Float) : Boolean{

        var bool = false

        var enemyListIterator = companionList.enemyList.listIterator()
        while (enemyListIterator.hasNext()) {
            var it = enemyListIterator.next()
            it.selected = false
        }
        var enemyListIteratorZ = companionList.enemyList.listIterator()
        while (enemyListIteratorZ.hasNext()) {
            var it = enemyListIteratorZ.next()
            val distanceX = x - (it.circle!!.x * (companionList.scaleScreen / 10))
            val distanceY = y - (it.circle!!.y  * (companionList.scaleScreen / 10))

            val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

            val sumOfRadius = (1 + it.circle!!.r)

            if (squaredDistance <= sumOfRadius * sumOfRadius) {
                it.selected = true
                companionList.build = true
                companionList.towerClick = false
                companionList.enemyClick = true
                companionList.enemySelectedBool = true
                bool = true
                break
            }
        }
        return bool
    }

    fun tower (x: Float, y: Float) : Boolean{

        var bool = false

        var towerListIterator = companionList.towerList.listIterator()
        while (towerListIterator.hasNext()) {
            var it = towerListIterator.next()
            it.selected = false
        }

        var towerListIteratorZ = companionList.towerList.listIterator()
        while (towerListIteratorZ.hasNext()) {
            var it = towerListIteratorZ.next()
            val distanceX = x - (it.towerRange.x * (companionList.scaleScreen / 10))
            val distanceY = y - (it.towerRange.y * (companionList.scaleScreen / 10))

            val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

            val sumOfRadius = (1 + 80)

            if (squaredDistance <= sumOfRadius * sumOfRadius) {
                it.selected = true
                companionList.towerClick = true
                companionList.towerClickID = companionList.towerList.indexOf(it)
                companionList.build = false
                companionList.enemyClick = false
                companionList.towerClickBool = true
                bool = true
                break
            }
        }
        return bool
    }



    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------


    @InternalCoroutinesApi
    override fun draw(canvas: Canvas) {
        super.draw(canvas)


                // ------------------------------------------------------------------------------------------------------------------------------------------------
                // ------------------------------------------------------------------------------------------------------------------------------------------------
                // ------------------------------------------------------------------------------------------------------------------------------------------------
                //  MAIN ACTIVITY ---------------------------------------------------------------------------------------------------------------------------------

                //scale canvas for different devices
                canvas.scale(
                    (companionList.scaleScreen / 10), (companionList.scaleScreen / 10)
                )

                //draw background
                if (companionList.mapPick == 0 || companionList.mapPick == 1) {
                    if (companionList.mapMode != 2) {
                        if (companionList.day) canvas.drawBitmap(backgroundMap1DayBmp!!, null, rectBackground, null)
                        else canvas.drawBitmap(backgroundMap1NightBmp!!, null, rectBackground, null)
                    } else {
                        if (companionList.day) canvas.drawBitmap(backgroundMap1Mode2DayBmp!!, null, rectBackground, null)
                        else canvas.drawBitmap(backgroundMap1Mode2NightBmp!!, null, rectBackground, null)
                    }
                } else if (companionList.mapPick == 2) {
                    if (companionList.day) canvas.drawBitmap(backgroundDayMap2!!, null, rectBackground, null)
                    else canvas.drawBitmap(backgroundNightMap2!!, null, rectBackground, null)
                }

                // draw text
                if (companionList.mapMode != 2) {
                    if (companionList.autoSpawn) canvas.drawText("ON", 910f, 1225f, paintText)
                    else canvas.drawText("OFF", 910f, 1225f, paintText)
                }

                //draw tower

                if (companionList.towerList.size > 0) {
                    companionList.writeLockTower.lock()
                    try {
                        var towerListIterator = companionList.towerList.listIterator()
                        while (towerListIterator.hasNext()) {
                            var it = towerListIterator.next()
                            if (it.towerFallingCount > 0) {
                                if (companionList.refresh) it.towerFallingCount += 8
                                var rectBaseS =
                                    Rect((it.towerRange.x - 80).toInt(), it.towerFallingCount, it.towerRange.x.toInt() + 80, it.towerFallingCount + 160)
                                canvas.drawBitmap(towerFalling!!, null, rectBaseS, null)
                                if (it.towerFallingCount > it.towerRange.y.toInt() - 80) it.towerFallingCount = 0
                            } else {
                                var rectBase =
                                    Rect((it.towerRange.x - 80).toInt(), (it.towerRange.y - 80).toInt(), (it.towerRange.x + 80).toInt(), (it.towerRange.y + 80).toInt())
                                canvas.drawBitmap(towerBase!!, null, rectBase, null)
                                    canvas.save()
                                    canvas.rotate(getAngle(it), it.towerRange.x, it.towerRange.y)
                                    var rectTower =
                                        Rect((it.towerRange.x - 84).toInt(), (it.towerRange.y - 64).toInt(), it.towerRange.x.toInt() + 172, it.towerRange.y.toInt() + 64)
                                    when (it.towerLevel ){
                                        in 1..4 -> canvas.drawBitmap(towerGunBasic!!, null, rectTower, null)
                                        in 5..9 -> canvas.drawBitmap(towerGunBlue!!, null, rectTower, null)
                                        in 10..14 -> canvas.drawBitmap(towerGunOrange!!, null, rectTower, null)
                                        in 15..999 -> canvas.drawBitmap(towerGunPurple!!, null, rectTower, null)
                                    }
                                    canvas.restore()
                            }
                            if (companionList.towerClick && it.selected) canvas.drawCircle(it.towerRange.x, it.towerRange.y, it.towerR, paintRange)
                            if (companionList.towerClick && it.selected && it.itemListBag.contains(GameActivity.companionList.eutils)) canvas.drawCircle(it.towerRange.x, it.towerRange.y, 150f, paintRange)
                            var rectTalent = Rect((it.towerRange.x - 60).toInt(), (it.towerRange.y + 30).toInt(), it.towerRange.x.toInt() - 30, it.towerRange.y.toInt() + 60)
                            if (it.talentPoints > 0) canvas.drawBitmap(talentP!!, null, rectTalent, null)
                        }
                } finally {
                        companionList.writeLockTower.unlock()
                }
                }



                    //draw poison talent
                    if (companionList.poisonCloud > 0 && companionList.shootListPoison.isNotEmpty()) {
                        companionList.writeLockPoison.lock()
                        try {
                            var shootListPoisonIterator = companionList.shootListPoison.listIterator()
                            while (shootListPoisonIterator.hasNext()) {
                                var it = shootListPoisonIterator.next()
                                if (it.broken == 1) {
                                    shootListPoisonIterator.remove()
                                } else {
                                    it.update()
                                    var baseShootPoison =
                                        Rect((it.poisonCloud.x - (it.poisonCloud.r * 1.5)).toInt(), (it.poisonCloud.y - (it.poisonCloud.r * 1.5)).toInt(), (it.poisonCloud.x + (it.poisonCloud.r * 1.5)).toInt(), (it.poisonCloud.y + (it.poisonCloud.r * 1.5)).toInt())
                                    it.poisonPicCounter++
                                    if (it.poisonPicCounter >= 5) {
                                        it.poisonPicCounter = 0
                                        it.poisonNextPic++
                                        if (it.poisonNextPic >= 2) it.poisonNextPic = 0
                                    }
                                    canvas.drawBitmap(shootPoisonArray!![it.poisonNextPic], null, baseShootPoison, null)
                                }
                            }
                        } finally {
                            companionList.writeLockPoison.unlock()
                        }
                    }

                    //draw tornado talent
                    if (companionList.shootListTornado.size > 0) {
                        companionList.writeLockTornado.lock()
                        try {
                            var shootListTornadoIterator = companionList.shootListTornado.listIterator()
                            while (shootListTornadoIterator.hasNext()) {
                                var it = shootListTornadoIterator.next()
                                if (it.broken == 1) {
                                    shootListTornadoIterator.remove()
                                } else {
                                    it.update()
                                    companionList.rotateTornado += 5
                                    if (companionList.rotateTornado >= 360) companionList.rotateTornado = 0f
                                    canvas.save()
                                    canvas.rotate(companionList.rotateTornado, it.tornadoRadius.x, it.tornadoRadius.y)
                                    var baseShootTornado =
                                        Rect((it.tornadoRadius.x - (it.tornadoRadius.r)).toInt(), (it.tornadoRadius.y - (it.tornadoRadius.r)).toInt(), (it.tornadoRadius.x + (it.tornadoRadius.r)).toInt(), (it.tornadoRadius.y + (it.tornadoRadius.r)).toInt())
                                    if (companionList.day) canvas.drawBitmap(shootMultiPicDay!!, null, baseShootTornado, null)
                                    else canvas.drawBitmap(shootMultiPic!!, null, baseShootTornado, null)
                                    canvas.restore()
                                }
                            }
                        } finally {
                            companionList.writeLockTornado.unlock()
                        }
                    }

                    //draw mine talent
                    if (companionList.wizardMine) {
                        companionList.writeLockMine.lock()
                        try {
                            var shootListMineIterator = companionList.shootListMine.listIterator()
                            while (shootListMineIterator.hasNext()) {
                                var it = shootListMineIterator.next()
                                if (it.broken) shootListMineIterator.remove()
                                else {
                                    if (companionList.refresh) it.update()
                                    var baseMine =
                                        Rect((it.mineRadius.x - (it.mineRadius.r * 1.25)).toInt(), (it.mineRadius.y - (it.mineRadius.r * 1.25)).toInt(), (it.mineRadius.x + (it.mineRadius.r * 1.25)).toInt(), (it.mineRadius.y + (it.mineRadius.r * 1.25)).toInt())
                                    it.minePicCounter++
                                    if (it.minePicCounter >= 5) {
                                        it.minePicCounter = 0
                                        it.mineNextPic++
                                        if (it.mineNextPic >= 5) it.mineNextPic = 0
                                    }
                                    canvas.drawBitmap(mineArray!![it.mineNextPic], null, baseMine, null)
                                }
                            }
                        } finally {
                            companionList.writeLockMine.unlock()
                        }
                    }

                        //draw enemy

                            if (companionList.level > 0) level()
                            if (companionList.enemyList.size > 0) {

                                companionList.writeLockEnemy.lock()
                                try {
                                    var enemyListIterator = companionList.enemyList.listIterator()
                                    while (enemyListIterator.hasNext()) {
                                        var it = enemyListIterator.next()
                                        // enemy out of screen
                                        if (companionList.refresh && it.dead != 1) {
                                            it.update()
                                        }
                                        // draw
                                        it.draw(canvas)

                                        canvas.drawRect((it.circle!!.x - 37).toFloat(), (it.circle!!.y - 42).toFloat(), (it.circle!!.x + 37).toFloat(), (it.circle!!.y - 18).toFloat(), paintHpBarBack)
                                        if (companionList.day) canvas.drawRect((it.circle!!.x - 35).toFloat(), (it.circle!!.y - 40).toFloat(), (it.circle!!.x.toFloat() - it.enemyRight + 35.0F), (it.circle!!.y - 20).toFloat(), paintHpBar)
                                        else canvas.drawRect((it.circle!!.x - 35).toFloat(), (it.circle!!.y - 40).toFloat(), (it.circle!!.x.toFloat() - it.enemyRight + 35.0F), (it.circle!!.y - 20).toFloat(), paintHpBarNight)

                                        if (it.manaShield > 0) {
                                            canvas.drawRect((it.circle!!.x - 37).toFloat(), (it.circle!!.y - 54).toFloat(), (it.circle!!.x + 37).toFloat(), (it.circle!!.y - 44).toFloat(), paintHpBarBack)
                                            canvas.drawRect((it.circle!!.x - 35).toFloat(), (it.circle!!.y - 52).toFloat(), (it.circle!!.x.toFloat() - it.enemyRightManaShield + 35.0F), (it.circle!!.y - 42).toFloat(), paintManaShield)
                                        }
                                        if (it.shield > 0) {
                                            canvas.drawRect((it.circle!!.x - 37).toFloat(), (it.circle!!.y - 54).toFloat(), (it.circle!!.x + 37).toFloat(), (it.circle!!.y - 44).toFloat(), paintHpBarBack)
                                            canvas.drawRect((it.circle!!.x - 35).toFloat(), (it.circle!!.y - 52).toFloat(), (it.circle!!.x.toFloat() - it.enemyRightShield + 35.0F), (it.circle!!.y - 42).toFloat(), paintShield)
                                        }

                                        if (it.hit) {
                                            it.explosionCounter ++
                                            when (it.explosionCounter) {
                                                in 1..4 -> {
                                                    var rectExplosion = Rect((it.circle!!.x - 12).toInt(), (it.circle!!.y - 15).toInt(), (it.circle!!.x + 12).toInt(), (it.circle!!.y + 14).toInt())
                                                    canvas.drawBitmap(explosion!!, null, rectExplosion, null)
                                                }
                                                in 5..10 -> {
                                                    var rectExplosion = Rect((it.circle!!.x - 12).toInt(), (it.circle!!.y - 15).toInt(), (it.circle!!.x + 12).toInt(), (it.circle!!.y + 14).toInt())
                                                    canvas.drawBitmap(explosion2!!, null, rectExplosion, null)
                                                }
                                                in 11..999 -> {
                                                    it.explosionCounter = 0
                                                    it.hit = false
                                                }
                                            }
                                        }

                                        if (it.fireDebuff > 0) it.drawBurn(canvas)
                                        if (it.poisonDebuff > 0) it.drawPoisonDebuff(canvas)
                                        if (it.poisonTalentPestDamage > 0) it.drawPoisonTalentPest(canvas)
                                        if (it.iceDebuff > 0 || it.itemFrostAlreadyAffected > 0 || it.iceNovaAlreadyAffected) it.drawIceSlow(canvas)
                                        if (it.entangled) it.drawEntangle(canvas)
                                        if (it.feared) it.drawFear(canvas)
                                        if (it.darkSlowAlreadyAffected) it.draw5(canvas)
                                        if (it.talentMoonlightDraw > 0) it.drawMoonlight(canvas)
                                        if (it.markOfTheButterflyCounter > 0) it.drawButterfly(canvas)
                                        if (it.wizardBombActive > 0) it.drawBomb(canvas)
                                        if (it.markOfTheButterflySlow > 0) it.drawButterflySlow(canvas)
                                        if (it.wizardMissedLightningActiveHit >= 1) it.drawLightning(canvas)
                                        if (it.mineAlreadyAffected) it.drawMine(canvas)
                                        if (it.throwBoulderHitAlreadyEffected) it.drawBoulder(canvas)
                                        if (it.itemLassoAlreadyAffected > 0) canvas.drawLine(companionList.towerList[it.itemLassoAlreadyAffectedTowerId].towerRange.x, companionList.towerList[it.itemLassoAlreadyAffectedTowerId].towerRange.y, it.circle!!.x, it.circle!!.y, paintLasso)
                                        if (it.darkTalentLaserAlreadyAffected > 0) canvas.drawLine(companionList.towerList[it.darkTalentLaserTowerId].towerRange.x, companionList.towerList[it.darkTalentLaserTowerId].towerRange.y, it.circle!!.x, it.circle!!.y, paintLaser)

                                        if (companionList.dmgDisplayList.isNotEmpty()) {
                                            //  coroutineScope {
                                            //      launch {
                                            companionList.writeLockDisplay.lock()
                                            try {
                                                var dmgDisplayListIterator =
                                                    companionList.dmgDisplayList.listIterator()
                                                while (dmgDisplayListIterator.hasNext()) {
                                                    var display = dmgDisplayListIterator.next()

                                                    //        for (display in dmgDisplayList) {
                                                    //            Log.d("thread", Thread.currentThread().name)
                                                    if (display.indexx == it) {
                                                        display.dmgCount++
                                                        if (display.dmgCountPosition > 1) display.dmgCountPosition += 2
                                                        else display.dmgCountPosition -= 2
                                                        if (it.passed == 0.toByte() || it.passed == 2.toByte() || it.passed == 4.toByte()) canvas.drawText(display.dmgReceived.toString(), ((it.circle!!.x + display.dmgCountPosition)), (it.circle!!.y - (it.circle!!.r / 2) + display.positionY), display.paint)
                                                        else canvas.drawText(display.dmgReceived.toString(), ((it.circle!!.x - (it.circle!!.r / 2)) + display.positionX), (it.circle!!.y - display.dmgCountPosition), display.paint)
                                                        if (display.dmgCount > 25) {
                                                            //   display.burnDmgDelete = fireDmgDisplay.indexOf(display)
                                                            display.displayDmgDelete = true
                                                        }
                                                    }
                                                }
                                            } finally {
                                                companionList.writeLockDisplay.unlock()
                                            }
                                        }

                                    }
                                } finally {
                                    companionList.writeLockEnemy.unlock()
                                }
                            }

                        // dmg display
                companionList.writeLockDisplay.lock()
                        try {
                            if (companionList.dmgDisplayList.isNotEmpty()) {
                                var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                                while (dmgDisplayListIterator.hasNext()) {
                                    var display = dmgDisplayListIterator.next()
                                    if (display.displayDmgDelete) dmgDisplayListIterator.remove()
                                }
                            }
                        } finally {
                            companionList.writeLockDisplay.unlock()
                        }


                        //draw shot
                        if (companionList.shootList.size > 0) {
                            companionList.writeLockShot.lock()
                            try {
                                var shootListIteratorZ = companionList.shootList.listIterator()
                                while (shootListIteratorZ.hasNext()) {
                                    var itZ = shootListIteratorZ.next()
                                    if (itZ.broken == 1) {
                                        shootListIteratorZ.remove()
                                    }
                                }
                                var shootListIterator = companionList.shootList.listIterator()
                                while (shootListIterator.hasNext()) {
                                    var it = shootListIterator.next()
                                        if (companionList.refresh) {
                                            it.update()
                                        }
                                        if (it.sniper) {
                                            if (!crossTowerBullet(it)) {
                                                canvas.save()
                                                canvas.rotate(getAngleBullet(it), it.bullet.x, it.bullet.y)
                                                var baseShootBullet =
                                                    Rect((it.bullet.x - (it.bullet.r * 4)).toInt(), (it.bullet.y - (it.bullet.r * 4)).toInt(), (it.bullet.x + (it.bullet.r * 4)).toInt(), (it.bullet.y + (it.bullet.r * 4)).toInt())
                                                canvas.drawBitmap(shootBulletPic!!, null, baseShootBullet, null)
                                                canvas.restore()
                                            }
                                        } else if (it.chainLightning) {
                                            canvas.save()
                                            canvas.rotate(getAngleBullet(it) - 75, it.bullet.x, it.bullet.y)
                                            var baseShootChain =
                                                Rect((it.bullet.x - (it.bullet.r * 3)).toInt(), (it.bullet.y - (it.bullet.r * 3)).toInt(), (it.bullet.x + (it.bullet.r * 3)).toInt(), (it.bullet.y + (it.bullet.r * 3)).toInt())
                                            canvas.drawBitmap(shootChainLightningPic!!, null, baseShootChain, null)
                                            canvas.restore()
                                        } else if (companionList.towerList[it.towerId].towerPrimaryElement == "moon") {
                                            if (!crossTowerBullet(it)) {
                                                companionList.rotateShotBounce += 30
                                                if (companionList.rotateShotBounce >= 360) companionList.rotateShotBounce = 0f
                                                canvas.save()
                                                canvas.rotate(companionList.rotateShotBounce, it.bullet.x, it.bullet.y)
                                                var baseShootBounce =
                                                    Rect((it.bullet.x - (it.bullet.r * 4)).toInt(), (it.bullet.y - (it.bullet.r * 4)).toInt(), (it.bullet.x + (it.bullet.r * 4)).toInt(), (it.bullet.y + (it.bullet.r * 4)).toInt())
                                                canvas.drawBitmap(shootBouncePic!!, null, baseShootBounce, null)
                                                canvas.restore()
                                            }
                                        } else if (it.multiShotBullet && companionList.towerList[it.towerId].towerPrimaryElement == "wind") {
                                            companionList.rotateShotMulti += 5
                                            if (companionList.rotateShotMulti >= 360) companionList.rotateShotMulti = 0f
                                            canvas.save()
                                            canvas.rotate(companionList.rotateShotMulti, it.bullet.x, it.bullet.y)
                                            var baseShootMulti =
                                                Rect((it.bullet.x - (it.bullet.r * 3)).toInt(), (it.bullet.y - (it.bullet.r * 3)).toInt(), (it.bullet.x + (it.bullet.r * 3)).toInt(), (it.bullet.y + (it.bullet.r * 3)).toInt())
                                            if (companionList.day) canvas.drawBitmap(shootMultiPicDay!!, null, baseShootMulti, null)
                                            else canvas.drawBitmap(shootMultiPic!!, null, baseShootMulti, null)
                                            canvas.restore()
                                        } else if (companionList.towerList[it.towerId].towerPrimaryElement == "earth") {
                                            if (!crossTowerBullet(it)) {
                                                canvas.save()
                                                canvas.rotate(getAngleBullet(it), it.bullet.x, it.bullet.y)
                                                var baseShootSplash =
                                                    Rect((it.bullet.x - (it.bullet.r * 8)).toInt(), (it.bullet.y - (it.bullet.r * 8)).toInt(), (it.bullet.x + (it.bullet.r * 8)).toInt(), (it.bullet.y + (it.bullet.r * 8)).toInt())
                                                canvas.drawBitmap(shootSplashPic!!, null, baseShootSplash, null)
                                                canvas.restore()
                                            }
                                        } else if (companionList.towerList[it.towerId].towerPrimaryElement == "butterfly") {
                                            if (!crossTowerBullet(it)) {
                                                canvas.save()
                                                canvas.rotate(getAngleBullet(it) - 90, it.bullet.x, it.bullet.y)
                                                var baseShootButterfly =
                                                    Rect((it.bullet.x - (it.bullet.r * 7)).toInt(), (it.bullet.y - (it.bullet.r * 7)).toInt(), (it.bullet.x + (it.bullet.r * 7)).toInt(), (it.bullet.y + (it.bullet.r * 7)).toInt())
                                                it.butterflyPicCounter++
                                                if (it.butterflyPicCounter > 2) {
                                                    it.butterflyPicCounter = 0
                                                    it.butterflyNextPic++
                                                    if (it.butterflyNextPic >= 4) it.butterflyNextPic =
                                                        0
                                                }
                                                canvas.drawBitmap(shootButterflyArray!![it.butterflyNextPic], null, baseShootButterfly, null)
                                                canvas.restore()
                                            }
                                        } else {
                                            if (!crossTowerBullet(it)) {
                                                canvas.save()
                                                canvas.rotate(getAngleBullet(it), it.bullet.x, it.bullet.y)
                                                var baseShootBullet =
                                                    Rect((it.bullet.x - (it.bullet.r * 4)).toInt(), (it.bullet.y - (it.bullet.r * 4)).toInt(), (it.bullet.x + (it.bullet.r * 4)).toInt(), (it.bullet.y + (it.bullet.r * 4)).toInt())
                                                canvas.drawBitmap(shootBulletPic!!, null, baseShootBullet, null)
                                                canvas.restore()
                                            }
                                        }
                                }
                            } finally {
                                companionList.writeLockShot.unlock()
                            }
                        }




                    //draw ice talent
                    if (companionList.iceShardSpeed > 0) {
                        companionList.writeLockIce.lock()
                        try {
                            var shootListIceIterator = companionList.shootListIce.listIterator()
                            while (shootListIceIterator.hasNext()) {
                                var it = shootListIceIterator.next()
                                if (companionList.refresh) it.update()
                                it.draw(canvas)
                            }
                        } finally {
                            companionList.writeLockIce.unlock()
                        }
                    }
                companionList.refresh = false
                }

    fun crossTowerBullet (it: Shoot): Boolean {
        var check = false
            /*

                if (it.bullet!!.x < towerRange.x + 80 && it.bullet!!.x > towerRange.x - 80) {
                        if (it.bullet!!.y < towerRange.y + 80 && it.bullet!!.y > towerRange.y - 80) check = true
                    }
                if (it.bullet!!.y < towerRange.y + 80 && it.bullet!!.y > towerRange.y - 80) {
                    if (it.bullet!!.x < towerRange.x + 80 && it.bullet!!.x > towerRange.x - 80)  check = true
                }

 */
                val distanceX = (companionList.towerList[it.towerId].towerRange.x) - it.bullet!!.x
                val distanceY = (companionList.towerList[it.towerId].towerRange.y) - it.bullet!!.y

                val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                val sumOfRadius = 95

                if (squaredDistance <= sumOfRadius * sumOfRadius) {
                    check = true
                }


            return check
    }

    fun getAngle(it:Tower): Float {

            var angle =
                Math.toDegrees(atan2((companionList.rotationTowerY - it.towerRange.y).toDouble(), (companionList.rotationTowerX - it.towerRange.x).toDouble()))
                    .toFloat()
            if (angle < 0) {
                angle += 360f
            }
            return angle
    }

    fun getAngleBullet(bullet: Shoot): Float {

                    var angle = 0f
            if (bullet.alreadyBounced > 0) angle =
                (Math.toDegrees(atan2((companionList.rotationEnemyY - (companionList.rotationBulletY)).toDouble(), (companionList.rotationEnemyX - (companionList.rotationBulletX)).toDouble()))
                    .toFloat())
            else angle =
                Math.toDegrees(atan2((companionList.rotationEnemyY - companionList.towerList[bullet.towerId].towerRange.y).toDouble(), (companionList.rotationEnemyX - companionList.towerList[bullet.towerId].towerRange.x).toDouble()))
                    .toFloat()
            if (angle < 0) {
                angle += 360f
            }
            return angle
    }


    private fun level() {

        // Normal orange:  #f39c12
        // Armor grey:  #566573
        // Magic Armor purple:  #af7ac5
        // Immune dark green: #117a65
        // Speed blue:  #284ae2
        // Evade light grey:  #d5dbdb
        // Mass red: #f64545
        // Hp-reg yellow:  #fff116
        // Boss white: #ffffff
        // Shortcut brown: #4C4137
        // ManaShield blue:
        // Shield brown:

        if (Utils.divisible(companionList.level, 50)) challenge(companionList.lvlHp, companionList.lvlArmor, companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
        else if (Utils.divisible(companionList.level, 10)) boss(companionList.lvlHp, companionList.lvlArmor, companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
        else {
            when (companionList.levelStatus) {
                "undef" ->
                    if (companionList.levelList.contains("tank") && companionList.tankBool) {
                        tank(companionList.lvlHp, companionList.lvlArmor, companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                        companionList.tankBool = false
                    } else {
                        when (companionList.levelList.random()) {
                            // 0 -> base (hp, armor, magicArmor, evade, hpReg, xp, speed)
                            "armor" -> armor(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "normal" -> normal(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "speed" -> speed(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "mass" -> mass(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "regeneration" -> reg(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "evade" -> evade(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "magic armor" -> magicArmor(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "immune" -> immune(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "shortcut" -> shortcut(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "split" -> split(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "shield" -> shield(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "manaShield" -> manaShield(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                        }
                    }
                "armor", "normal", "speed", "mass", "regeneration", "evade", "magic armor", "immune", "shortcut", "split", "shield", "manaShield" ->
                    if (companionList.levelList.contains("healer") && companionList.levelStatus != "boss" && companionList.levelStatus != "mass" && companionList.levelStatus != "challenge") {
                        when ((0..5).random()) {
                            0 -> {
                                healer(companionList.lvlHp, companionList.lvlArmor, companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            }
                            in 1..5 -> {
                                when (companionList.levelStatus) {
                                    "armor" -> armor(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "normal" -> normal(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "speed" -> speed(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "mass" -> mass(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "regeneration" -> reg(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "evade" -> evade(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "magic armor" -> magicArmor(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "immune" -> immune(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "shortcut" -> shortcut(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "split" -> split(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "shield" -> shield(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                    "manaShield" -> manaShield(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                                }
                            }
                        }
                    } else {
                        when (companionList.levelStatus) {
                            "armor" -> armor(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "normal" -> normal(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "speed" -> speed(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "mass" -> mass(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "regeneration" -> reg(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "evade" -> evade(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "magic armor" -> magicArmor(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "immune" -> immune(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "shortcut" -> shortcut(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "split" -> split(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "shield" -> shield(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                            "manaShield" -> manaShield(companionList.lvlHp, companionList.lvlArmor,companionList.lvlMagicArmor, companionList.lvlEvade, companionList.lvlHpReg, companionList.lvlXp, companionList.lvlSpd)
                        }
                    }
            }
        }
    }

    private fun armor(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
            if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
                var eliteMob = 1
                if (companionList.timerEliteMob == 0) {
                    eliteMob = 1
                } else {
                    when((0..1).random()){
                        0 -> eliteMob = 2
                        1 -> eliteMob = 3
                    }
                }
                val enemyListIterator = companionList.enemyList.listIterator()
                var manaShield = 0f
                var shield = 0f
                if (!companionList.allShieldsBool) {
                    companionList.allShieldsBool = true
                    if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                        when ((0..2).random()) {
                            0 -> companionList.manaShieldBool = true
                            1 -> companionList.shieldBool = true
                        }
                    } else if (companionList.levelList.contains("manaShield")) {
                        when ((0..2).random()) {
                            0 -> companionList.manaShieldBool = true
                        }
                    } else if (companionList.levelList.contains("shield")) {
                        when ((0..2).random()) {
                            0 -> companionList.shieldBool = true
                        }
                    }
                }
                if (companionList.manaShieldBool) manaShield = (hp / 10f) * companionList.shieldBrakerItem
                if (companionList.shieldBool) shield = (hp / 10f) * companionList.shieldBrakerItem

                var x: Enemy =
                    Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, (armor * 10f), magicArmor * 0.5f,
                        evade * 0.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#566573"))
                if (companionList.level > 50) x =
                    Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, (armor * 15f), magicArmor * 0.5f,
                        evade * 0.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#566573"))
                if (eliteMob == 2) x.eliteMob = true
                if (eliteMob == 3) x.elementalMob = true
                if (eliteMob == 3) eliteMob = 2
                x.name = "armor"
                x.baseSpeed = x.speed
                enemyListIterator.add(x)
                companionList.enemySpawned += 1 * eliteMob
                when ((0..5).random()) {
                    0 -> companionList.timerEliteMob = 1
                    in 1..5 -> companionList.timerEliteMob = 0
                }
                companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
                else 25
                companionList.levelStatus = "armor"
                companionList.levelCountSecondBool = true

            }
        } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun magicArmor(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                        1 -> companionList.shieldBool = true
                    }
                } else if (companionList.levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                    }
                } else if (companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.shieldBool = true
                    }
                }
            }
            if (companionList.manaShieldBool) manaShield = (hp / 10f)* companionList.shieldBrakerItem
            if (companionList.shieldBool) shield = (hp / 10f)* companionList.shieldBrakerItem
            var x: Enemy =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor * 0.5f, (magicArmor * 10f) * companionList.wizardMagicArmorSmasher, evade * 0.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#af7ac5"))
            if (companionList.level > 50) x =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor * 0.5f, (magicArmor * 15f) * companionList.wizardMagicArmorSmasher, evade * 0.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#af7ac5"))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "magicArmor"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "magic armor"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun normal(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                        1 -> companionList.shieldBool = true
                    }
                } else if (companionList.levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                    }
                } else if (companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.shieldBool = true
                    }
                }
            }
            if (companionList.manaShieldBool) manaShield = (hp / 10f)* companionList.shieldBrakerItem
            if (companionList.shieldBool) shield = (hp / 10f)* companionList.shieldBrakerItem
            val x: Enemy =
                Enemy(hp * eliteMob, hp * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#6FBCAF"))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "normal"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "normal"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun shortcut(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                        1 -> companionList.shieldBool = true
                    }
                } else if (companionList.levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                    }
                } else if (companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.shieldBool = true
                    }
                }
            }
            if (companionList.manaShieldBool) manaShield = (hp / 10f)* companionList.shieldBrakerItem
            if (companionList.shieldBool) shield = (hp / 10f)* companionList.shieldBrakerItem
            val x: Enemy =
                Enemy(hp * eliteMob, hp * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed * 1.2f, resources.getColor(R.color.shortcut))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "shortcut"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "shortcut"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun speed(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                        1 -> companionList.shieldBool = true
                    }
                } else if (companionList.levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                    }
                } else if (companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.shieldBool = true
                    }
                }
            }
            if (companionList.manaShieldBool) manaShield = (hp / 10f)* companionList.shieldBrakerItem
            if (companionList.shieldBool) shield = (hp / 10f)* companionList.shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.fast))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "speed"
            x.baseSpeed = x.speed
            x.extraSpeed = 3.0f
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "speed"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun mass(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 20 && companionList.enemySpawned < 16 + (companionList.midnightMadnessExtraSpawn *2) ) {
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                        1 -> companionList.shieldBool = true
                    }
                } else if (companionList.levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                    }
                } else if (companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.shieldBool = true
                    }
                }
            }
            if (companionList.manaShieldBool) manaShield = (hp / 10f)* companionList.shieldBrakerItem
            if (companionList.shieldBool) shield = (hp / 10f)* companionList.shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.7f, hp * 0.7f, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp, speed, Color.parseColor("#f64545"))
            x.name = "mass"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1
            companionList.timer = 0
            companionList.levelStatus = "mass"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun reg(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                        1 -> companionList.shieldBool = true
                    }
                } else if (companionList.levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                    }
                } else if (companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.shieldBool = true
                    }
                }
            }
            if (companionList.manaShieldBool) manaShield = (hp / 10f)* companionList.shieldBrakerItem
            if (companionList.shieldBool) shield = (hp / 10f)* companionList.shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * (hp / 500), xp * eliteMob, speed, Color.parseColor("#fff116"))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "regeneration"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "regeneration"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun evade(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                        1 -> companionList.shieldBool = true
                    }
                } else if (companionList.levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                    }
                } else if (companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.shieldBool = true
                    }
                }
            }
            if (companionList.manaShieldBool) manaShield = (hp / 10f)* companionList.shieldBrakerItem
            if (companionList.shieldBool) shield = (hp / 10f)* companionList.shieldBrakerItem
            var x: Enemy =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade * 5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#d5dbdb"))
            if (companionList.level > 50) x =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade * 7.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#d5dbdb"))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "evade"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "evade"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun boss(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 20 && companionList.enemySpawned < 1) {
            val enemyListIterator = companionList.enemyList.listIterator()
            val x: Enemy =
                Enemy(hp * 6.0f, hp * 6.0f, 0f, 0f, 0f, 0f, armor, magicArmor, evade, hpReg * (hp / 1000), xp * 6f, speed, resources.getColor(R.color.boss))
            x.name = "boss"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1
            companionList.timer = 0
            companionList.levelStatus = "boss"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun challenge(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 20 && companionList.enemySpawned < 1) {
            val enemyListIterator = companionList.enemyList.listIterator()
            val x: Enemy =
                Enemy(hp * 15.0f, hp * 15.0f, 0f, 0f, 0f, 0f, armor * 4, magicArmor * 4, evade * 0, hpReg * 0, xp * 0, speed, resources.getColor(R.color.challenge))
            x.name = "challenge"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1
            companionList.timer = 0
            companionList.levelStatus = "challenge"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun immune(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {

        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                        1 -> companionList.shieldBool = true
                    }
                } else if (companionList.levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                    }
                } else if (companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.shieldBool = true
                    }
                }
            }
            if (companionList.manaShieldBool) manaShield = (hp / 10f)* companionList.shieldBrakerItem
            if (companionList.shieldBool) shield = (hp / 10f)* companionList.shieldBrakerItem
            val x: Enemy =
                Enemy((hp * 0.7f * eliteMob), (hp * 0.7f * eliteMob), manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.immune))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "immune"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "immune"
            companionList.levelCountSecondBool = true

        }
        } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun split(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                if (companionList.levelList.contains("manaShield") && companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                        1 -> companionList.shieldBool = true
                    }
                } else if (companionList.levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> companionList.manaShieldBool = true
                    }
                } else if (companionList.levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> companionList.shieldBool = true
                    }
                }
            }
            if (companionList.manaShieldBool) manaShield = (hp / 10f)* companionList.shieldBrakerItem
            if (companionList.shieldBool) shield = (hp / 10f)* companionList.shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.5f * eliteMob, hp * 0.5f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.split))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "split"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "split"
            companionList.levelCountSecondBool = true

        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun shield(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                companionList.shieldBool = true
            }
            if (companionList.shieldBool) shield = (hp * 0.4f * eliteMob)* companionList.shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.1f, hp * 0.1f, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.Shield))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "shield"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "shield"
            companionList.levelCountSecondBool = true
        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun manaShield(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8 + companionList.midnightMadnessExtraSpawn) {
            var eliteMob = 1
            if (companionList.timerEliteMob == 0) {
                eliteMob = 1
            } else {
                when((0..3).random()){
                    0,1,2 -> eliteMob = 2
                    3 -> eliteMob = 3
                }
            }
            val enemyListIterator = companionList.enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!companionList.allShieldsBool) {
                companionList.allShieldsBool = true
                companionList.manaShieldBool = true
            }
            if (companionList.manaShieldBool) manaShield = (hp * 0.4f * eliteMob)* companionList.shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.1f, hp * 0.1f, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.ManaShield))
            if (eliteMob == 2) x.eliteMob = true
            if (eliteMob == 3) x.elementalMob = true
            if (eliteMob == 3) eliteMob = 2
            x.name = "manaShield"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> companionList.timerEliteMob = 1
                in 1..5 -> companionList.timerEliteMob = 0
            }
            companionList.timer = if (eliteMob == 2 || companionList.timerEliteMob == 1) 0
            else 25
            companionList.levelStatus = "manaShield"
            companionList.levelCountSecondBool = true
        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun healer(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
        if (companionList.timer >= 50 && companionList.enemySpawned < 8) {
            val enemyListIterator = companionList.enemyList.listIterator()
            val x: Enemy =
                Enemy(hp * 1.0f, hp * 1.0f, 0f, 0f, 0f, 0f, armor * 0, magicArmor * 0, evade, hpReg * 0, xp * 1.5f, speed, resources.getColor(R.color.healer))
            x.name = "healer"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            companionList.enemySpawned += 1
            companionList.timer = 25
        }
            } finally {
            companionList.writeLockEnemy.unlock()
        }
    }

    private fun tank(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        companionList.writeLockEnemy.lock()
        try {
            if (companionList.timer >= 50) {
                val enemyListIterator = companionList.enemyList.listIterator()
                val x: Enemy =
                    Enemy(hp * 2f, hp * 2f, 0f, 0f, 0f, 0f, armor * 3, magicArmor * 3, evade * 0, hpReg * 0, xp * 1.5f, speed, resources.getColor(R.color.tank))
                x.name = "tank"
                x.baseSpeed = x.speed
                enemyListIterator.add(x)
                companionList.enemySpawned += 1
                companionList.timer = 25
            }
        } finally {
            companionList.writeLockEnemy.unlock()
        }
    }
}

// TODO Item insert : Gameactivty - Items fragment - itemAdapter - ItemBagAdapter - Itemupgradefragment - Gameactivity Load Game