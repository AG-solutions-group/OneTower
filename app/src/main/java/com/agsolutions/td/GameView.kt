package com.agsolutions.td

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.agsolutions.td.Companion.Companion.allShieldsBool
import com.agsolutions.td.Companion.Companion.autoSpawn
import com.agsolutions.td.Companion.Companion.build
import com.agsolutions.td.Companion.Companion.day
import com.agsolutions.td.Companion.Companion.dmgDisplayList
import com.agsolutions.td.Companion.Companion.enemyClick
import com.agsolutions.td.Companion.Companion.enemyCount
import com.agsolutions.td.Companion.Companion.enemyList
import com.agsolutions.td.Companion.Companion.enemySelectedBool
import com.agsolutions.td.Companion.Companion.enemySpawned
import com.agsolutions.td.Companion.Companion.iceShardSpeed
import com.agsolutions.td.Companion.Companion.level
import com.agsolutions.td.Companion.Companion.levelCountSecondBool
import com.agsolutions.td.Companion.Companion.levelList
import com.agsolutions.td.Companion.Companion.levelStatus
import com.agsolutions.td.Companion.Companion.lvlArmor
import com.agsolutions.td.Companion.Companion.lvlEvade
import com.agsolutions.td.Companion.Companion.lvlHp
import com.agsolutions.td.Companion.Companion.lvlHpReg
import com.agsolutions.td.Companion.Companion.lvlMagicArmor
import com.agsolutions.td.Companion.Companion.lvlSpd
import com.agsolutions.td.Companion.Companion.lvlXp
import com.agsolutions.td.Companion.Companion.manaShieldBool
import com.agsolutions.td.Companion.Companion.mapMode
import com.agsolutions.td.Companion.Companion.mapPick
import com.agsolutions.td.Companion.Companion.midnightMadnessExtraSpawn
import com.agsolutions.td.Companion.Companion.poisonCloud
import com.agsolutions.td.Companion.Companion.readLockEnemy
import com.agsolutions.td.Companion.Companion.refresh
import com.agsolutions.td.Companion.Companion.rotateTornado
import com.agsolutions.td.Companion.Companion.rotationBulletX
import com.agsolutions.td.Companion.Companion.rotationBulletY
import com.agsolutions.td.Companion.Companion.rotationEnemyX
import com.agsolutions.td.Companion.Companion.rotationEnemyY
import com.agsolutions.td.Companion.Companion.rotationTowerX
import com.agsolutions.td.Companion.Companion.rotationTowerY
import com.agsolutions.td.Companion.Companion.scaleScreen
import com.agsolutions.td.Companion.Companion.shieldBool
import com.agsolutions.td.Companion.Companion.shieldBrakerItem
import com.agsolutions.td.Companion.Companion.shootList
import com.agsolutions.td.Companion.Companion.shootListIce
import com.agsolutions.td.Companion.Companion.shootListMine
import com.agsolutions.td.Companion.Companion.shootListPoison
import com.agsolutions.td.Companion.Companion.shootListTornado
import com.agsolutions.td.Companion.Companion.spawnDoubleClick
import com.agsolutions.td.Companion.Companion.spawnDoubleClickCounter
import com.agsolutions.td.Companion.Companion.spawnEnemy
import com.agsolutions.td.Companion.Companion.startScreenTimerTower
import com.agsolutions.td.Companion.Companion.startScreenTowerBool
import com.agsolutions.td.Companion.Companion.tankBool
import com.agsolutions.td.Companion.Companion.timer
import com.agsolutions.td.Companion.Companion.timerEliteMob
import com.agsolutions.td.Companion.Companion.towerClick
import com.agsolutions.td.Companion.Companion.towerClickBool
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Companion.Companion.towerStatsClick
import com.agsolutions.td.Companion.Companion.wizardMagicArmorSmasher
import com.agsolutions.td.Companion.Companion.wizardMine
import com.agsolutions.td.Companion.Companion.writeLockDisplay
import com.agsolutions.td.Companion.Companion.writeLockEnemy
import com.agsolutions.td.Companion.Companion.writeLockIce
import com.agsolutions.td.Companion.Companion.writeLockMine
import com.agsolutions.td.Companion.Companion.writeLockPoison
import com.agsolutions.td.Companion.Companion.writeLockShot
import com.agsolutions.td.Companion.Companion.writeLockTornado
import com.agsolutions.td.Companion.Companion.writeLockTower
import com.agsolutions.td.Main.MainActivity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.math.atan2
import kotlin.random.Random


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
    var backgroundStartScreen: Bitmap? = null
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
        backgroundStartScreen = BitmapFactory.decodeResource(context.resources, R.drawable.backgroundstartscreen)
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
            readLockEnemy.lock()
            try {

                val x = event!!.x
                val y = event.y

                if (tower(x, y)) {

                } else if ((x > 875 * (scaleScreen / 10) && x < 1025 * (scaleScreen / 10)) && (y > 1145 * (scaleScreen / 10) && y < 1275 * (scaleScreen / 10)) && spawnDoubleClickCounter in 1..20 && mapMode == 1) {
                        spawnDoubleClick = true
                        if (autoSpawn) {
                            autoSpawn = false
                            spawnEnemy = false
                        } else if (!autoSpawn) {
                            autoSpawn = true
                            spawnEnemy = false
                        }
                } else if ((x > 875 * (scaleScreen / 10) && x < 1025 * (scaleScreen / 10)) && (y > 1145 * (scaleScreen / 10) && y < 1275 * (scaleScreen / 10)) && mapMode == 1) {
                        spawnDoubleClickCounter = 1
                }else if (enemy(x, y)){
                }else {
                    towerClick = false
                    enemyClick = false
                    build = true
                    com.agsolutions.td.Companion.buildClickBool = true
                    towerStatsClick = true
                }

                return super.onTouchEvent(event)
            } finally {
                readLockEnemy.unlock()
            }
        } else return super.onTouchEvent(event)
    }

    fun enemy (x: Float, y: Float) : Boolean{

        var bool = false

        var enemyListIterator = enemyList.listIterator()
        while (enemyListIterator.hasNext()) {
            var it = enemyListIterator.next()
            it.selected = false
        }
        var enemyListIteratorZ = enemyList.listIterator()
        while (enemyListIteratorZ.hasNext()) {
            var it = enemyListIteratorZ.next()
            val distanceX = x - (it.circle!!.x * (scaleScreen / 10))
            val distanceY = y - (it.circle!!.y  * (scaleScreen / 10))

            val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

            val sumOfRadius = (1 + it.circle!!.r)

            if (squaredDistance <= sumOfRadius * sumOfRadius) {
                it.selected = true
                build = true
                com.agsolutions.td.Companion.buildClickBool = true
                towerClick = false
                enemyClick = true
                enemySelectedBool = true
                bool = true
                break
            }
        }
        return bool
    }

    fun tower (x: Float, y: Float) : Boolean{

        var bool = false

        var towerListIterator = towerList.listIterator()
        while (towerListIterator.hasNext()) {
            var it = towerListIterator.next()
            it.selected = false
        }

        var towerListIteratorZ = towerList.listIterator()
        while (towerListIteratorZ.hasNext()) {
            var it = towerListIteratorZ.next()
            val distanceX = x - (it.towerRange.x * (scaleScreen / 10))
            val distanceY = y - (it.towerRange.y * (scaleScreen / 10))

            val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

            val sumOfRadius = (1 + 80)

            if (squaredDistance <= sumOfRadius * sumOfRadius) {
                it.selected = true
                towerClick = true
                towerClickID = towerList.indexOf(it)
                build = false
                enemyClick = false
                towerClickBool = true
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

            // start screen -------------------------------------------------------------------------------------------------------------
            // start screen -------------------------------------------------------------------------------------------------------------
            // start screen -------------------------------------------------------------------------------------------------------------
            // start screen -------------------------------------------------------------------------------------------------------------

            if (MainActivity.startScreenBool) {

                var width = resources.displayMetrics.widthPixels
                var height = resources.displayMetrics.heightPixels

                if (startScreenTowerBool) {
                    startScreenTowerBool = false
                    towerList.add(Tower(10f, 0f, 0f, 45f, 1f, 2f))
                }

                if (MainActivity.firstDisplayScale != 1) {

                    canvas.scale(
                        (scaleScreen / 10), (scaleScreen / 10)
                    )

                    rectBackgroundStartScreen = Rect(0, 0, 1200, 2160)
                    canvas.drawBitmap(backgroundStartScreen!!, null, rectBackgroundStartScreen, null)

                    //draw tower

                        var towerListIterator = towerList.listIterator()
                        while (towerListIterator.hasNext()) {
                            var it = towerListIterator.next()

                            it.firstLastRandom = 2
                            it.towerRange = TowerRadius(175f, ((2160 / 2)).toFloat(), 300f)
                            it.towerR = 300f

                            if (it.towerFallingCount > 0) {
                                it.towerFallingCount += 8
                                var rectBaseS =
                                    Rect((100).toInt(), it.towerFallingCount, (300).toInt(), it.towerFallingCount + 200)
                                canvas.drawBitmap(towerFalling!!, null, rectBaseS, null)
                                if (it.towerFallingCount > (((2000 / 2)).toInt())) it.towerFallingCount =
                                    0
                            } else {
                                var rectBaseX =
                                    Rect(100.toInt(), ((((2160 / 2)) - 75).toInt()), (250).toInt(), ((((2160 / 2)) + 75).toInt()))
                                canvas.drawBitmap(towerBase!!, null, rectBaseX, null)
                                if (it.towerPrimaryElement == "wind") {
                                }else {
                                    canvas.save()
                                    canvas.rotate(getAngle(it), (175f), (2160 / 2f))
                                    var rectTowerX =
                                        Rect((85).toInt(), (((2160 / 2) - 75).toInt()), (385).toInt(), (((2160 / 2) + 75).toInt()))
                                    canvas.drawBitmap(towerGunPurple!!, null, rectTowerX, null)
                                    canvas.restore()
                                }
                            }
                        }

                    // draw enemy

                    enemyCount++
                    if (enemyCount > (10..100).random()) {
                        enemyCount = 0
                        val color =
                            Color.argb(255, Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255))
                        var x: Enemy =
                            Enemy(30f, 30f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f, color)

                        when ((0..1).random()) {
                            0 -> {
                                x.circle!!.x = 405f
                                x.circle!!.y = 0f
                                x.passed = 4.toByte()
                            }
                            1 -> {
                                x.circle!!.x = 405f
                                x.circle!!.y = 2160f
                                x.passed = 2.toByte()
                            }
                        }
                        var enemyListIterator = enemyList.listIterator()
                        enemyListIterator.add(x)
                        // enemy out of screen

                    }

                    var enemyListIterator = enemyList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var it = enemyListIterator.next()
                        if (it.circle!!.y > (2160).toFloat() || it.circle!!.y < 0f || it.dead == 7) {
                            towerList[0].crossesAllList.remove(it)
                            towerList[0].crossesNoneList.remove(it)
                            enemyListIterator.remove()
                        } else {
                            it.update()
                            it.draw(canvas)
                        }
                    }

                    // crosses

                    var enemyListIteratorX = enemyList.listIterator()
                    while (enemyListIteratorX.hasNext()) {
                        var it = enemyListIteratorX.next()

                        val distanceX = (towerList[0].towerRange.x) - (it.circle!!.x)
                        val distanceY = (towerList[0].towerRange.y) - (it.circle!!.y)
                        val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)
                        val sumOfRadius = towerList[0].towerR + ((it.circle!!.r) - 20)
                        if (squaredDistance <= sumOfRadius * sumOfRadius) {

                            if (towerList[0].crossesAllList.contains(it)) {
                            } else {
                                //if (firstLastRandom == 1) crossesAllList.add(0, it)     // last
                                //else crossesAllList.add(crossesAllList.size, it)      // first
                                towerList[0].crossesAllList.add(0, it)
                                if (towerList[0].crossesNoneList.contains(it)) towerList[0].crossesNoneList.remove(it)
                            }
                        } else {
                            if (towerList[0].crossesAllList.contains(it)) towerList[0].crossesAllList.remove(it)
                            if (towerList[0].crossesNoneList.contains(it)) {
                            } else towerList[0].crossesNoneList.add(0, it)
                        }
                    }

                    // draw bullet

                    var shootListIteratorZ = shootList.listIterator()
                    while (shootListIteratorZ.hasNext()) {
                        var bullet = shootListIteratorZ.next()
                        var enemyListIteratorZ = enemyList.listIterator()
                        while (enemyListIteratorZ.hasNext()) {
                            var it = enemyListIteratorZ.next()

                            val distanceX = (bullet.bullet.x) - (it.circle!!.x)
                            val distanceY = (bullet.bullet.y) - (it.circle!!.y)

                            val squaredDistance =
                                (distanceX * distanceX) + (distanceY * distanceY)

                            val sumOfRadius = bullet.bullet.r + ((it.circle!!.r) - (20.0))

                            if (squaredDistance <= sumOfRadius * sumOfRadius) {
                                bullet.broken = 1
                                towerList[0].randomEnemyForShotBool
                                it.dead = 7

                            }
                        }
                    }

                    var shootListIterator = shootList.listIterator()
                    while (shootListIterator.hasNext()) {
                        var bullet = shootListIterator.next()
                        if (bullet.broken == 1) {
                            shootListIterator.remove()
                        } else {
                            bullet.update()
                            if (bullet.bullet.x > ((175 + 60))) {
                                canvas.save()
                                canvas.rotate(getAngleBullet(bullet), bullet.bullet.x, bullet.bullet.y)
                                var baseShootBullet =
                                    Rect((bullet.bullet.x - (bullet.bullet.r * 4)).toInt(), (bullet.bullet.y - (bullet.bullet.r * 4)).toInt(), (bullet.bullet.x + (bullet.bullet.r * 4)).toInt(), (bullet.bullet.y + (bullet.bullet.r * 4)).toInt())
                                canvas.drawBitmap(shootBulletPic!!, null, baseShootBullet, null)
                                canvas.restore()
                            }
                        }
                    }

                    startScreenTimerTower++
                    if (com.agsolutions.td.Companion.startScreenTimerTower >= 30f && towerList[0].crossesAllList.isNotEmpty()) {
                        var shootListIteratorX = shootList.listIterator()
                        var x = Shoot()
                        x.bullet.x = towerList[0].towerRange.x
                        x.bullet.y = towerList[0].towerRange.y
                        x.towerId = 0
                        shootListIteratorX.add(x)   // add new shoot
                        com.agsolutions.td.Companion.startScreenTimerTower = 0
                    }

                } else {
                    rectBackgroundStartScreen = Rect(0, 0, width, height)
                    canvas.drawBitmap(backgroundStartScreen!!, null, rectBackgroundStartScreen, null)
                }


                // ------------------------------------------------------------------------------------------------------------------------------------------------
                // ------------------------------------------------------------------------------------------------------------------------------------------------
                // ------------------------------------------------------------------------------------------------------------------------------------------------
                //  MAIN ACTIVITY ---------------------------------------------------------------------------------------------------------------------------------

            } else {

                //scale canvas for different devices
                canvas.scale(
                    (scaleScreen / 10), (scaleScreen / 10)
                )

                //draw background
                if (mapPick == 0 || mapPick == 1) {
                    if (mapMode != 2) {
                        if (day) canvas.drawBitmap(backgroundMap1DayBmp!!, null, rectBackground, null)
                        else canvas.drawBitmap(backgroundMap1NightBmp!!, null, rectBackground, null)
                    } else {
                        if (day) canvas.drawBitmap(backgroundMap1Mode2DayBmp!!, null, rectBackground, null)
                        else canvas.drawBitmap(backgroundMap1Mode2NightBmp!!, null, rectBackground, null)
                    }
                } else if (mapPick == 2) {
                    if (day) canvas.drawBitmap(backgroundDayMap2!!, null, rectBackground, null)
                    else canvas.drawBitmap(backgroundNightMap2!!, null, rectBackground, null)
                }

                // draw text
                if (mapMode != 2) {
                    if (autoSpawn) canvas.drawText("ON", 910f, 1225f, paintText)
                    else canvas.drawText("OFF", 910f, 1225f, paintText)
                }

                //draw tower

                if (towerList.size > 0) {
                    writeLockTower.lock()
                    try {
                        var towerListIterator = towerList.listIterator()
                        while (towerListIterator.hasNext()) {
                            var it = towerListIterator.next()
                            if (it.towerFallingCount > 0) {
                                if (refresh) it.towerFallingCount += 8
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
                            if (towerClick && it.selected) canvas.drawCircle(it.towerRange.x, it.towerRange.y, it.towerR, paintRange)
                            if (towerClick && it.selected && it.itemListBag.contains(Items.eutils)) canvas.drawCircle(it.towerRange.x, it.towerRange.y, 150f, paintRange)
                        }
                } finally {
                    writeLockTower.unlock()
                }
                }



                    //draw poison talent
                    if (poisonCloud > 0 && shootListPoison.isNotEmpty()) {
                        writeLockPoison.lock()
                        try {
                            var shootListPoisonIterator = shootListPoison.listIterator()
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
                            writeLockPoison.unlock()
                        }
                    }

                    //draw tornado talent
                    if (shootListTornado.size > 0) {
                        writeLockTornado.lock()
                        try {
                            var shootListTornadoIterator = shootListTornado.listIterator()
                            while (shootListTornadoIterator.hasNext()) {
                                var it = shootListTornadoIterator.next()
                                if (it.broken == 1) {
                                    shootListTornadoIterator.remove()
                                } else {
                                    it.update()
                                    rotateTornado += 5
                                    if (rotateTornado >= 360) rotateTornado = 0f
                                    canvas.save()
                                    canvas.rotate(rotateTornado, it.tornadoRadius.x, it.tornadoRadius.y)
                                    var baseShootTornado =
                                        Rect((it.tornadoRadius.x - (it.tornadoRadius.r)).toInt(), (it.tornadoRadius.y - (it.tornadoRadius.r)).toInt(), (it.tornadoRadius.x + (it.tornadoRadius.r)).toInt(), (it.tornadoRadius.y + (it.tornadoRadius.r)).toInt())
                                    if (day) canvas.drawBitmap(shootMultiPicDay!!, null, baseShootTornado, null)
                                    else canvas.drawBitmap(shootMultiPic!!, null, baseShootTornado, null)
                                    canvas.restore()
                                }
                            }
                        } finally {
                            writeLockTornado.unlock()
                        }
                    }

                    //draw mine talent
                    if (wizardMine) {
                        writeLockMine.lock()
                        try {
                            var shootListMineIterator = shootListMine.listIterator()
                            while (shootListMineIterator.hasNext()) {
                                var it = shootListMineIterator.next()
                                if (it.broken) shootListMineIterator.remove()
                                else {
                                    if (refresh) it.update()
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
                            writeLockMine.unlock()
                        }
                    }

                        //draw enemy

                            if (level > 0) level()
                            if (enemyList.size > 0) {

                                writeLockEnemy.lock()
                                try {
                                    var enemyListIterator = enemyList.listIterator()
                                    while (enemyListIterator.hasNext()) {
                                        var it = enemyListIterator.next()
                                        // enemy out of screen
                                        if (refresh && it.dead != 1) {
                                            it.update()
                                        }
                                        // draw
                                        it.draw(canvas)

                                        canvas.drawRect((it.circle!!.x - 37).toFloat(), (it.circle!!.y - 42).toFloat(), (it.circle!!.x + 37).toFloat(), (it.circle!!.y - 18).toFloat(), paintHpBarBack)
                                        if (day) canvas.drawRect((it.circle!!.x - 35).toFloat(), (it.circle!!.y - 40).toFloat(), (it.circle!!.x.toFloat() - it.enemyRight + 35.0F), (it.circle!!.y - 20).toFloat(), paintHpBar)
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
                                        if (it.itemLassoAlreadyAffected > 0) canvas.drawLine(towerList[it.itemLassoAlreadyAffectedTowerId].towerRange.x, towerList[it.itemLassoAlreadyAffectedTowerId].towerRange.y, it.circle!!.x, it.circle!!.y, paintLasso)
                                        if (it.darkTalentLaserAlreadyAffected > 0) canvas.drawLine(towerList[it.darkTalentLaserTowerId].towerRange.x, towerList[it.darkTalentLaserTowerId].towerRange.y, it.circle!!.x, it.circle!!.y, paintLaser)

                                        if (dmgDisplayList.isNotEmpty()) {
                                            //  coroutineScope {
                                            //      launch {
                                            writeLockDisplay.lock()
                                            try {
                                                var dmgDisplayListIterator =
                                                    dmgDisplayList.listIterator()
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
                                                writeLockDisplay.unlock()
                                            }
                                        }

                                    }
                                } finally {
                                    writeLockEnemy.unlock()
                                }
                            }

                        // dmg display
                        writeLockDisplay.lock()
                        try {
                            if (dmgDisplayList.isNotEmpty()) {
                                var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                while (dmgDisplayListIterator.hasNext()) {
                                    var display = dmgDisplayListIterator.next()
                                    if (display.displayDmgDelete) dmgDisplayListIterator.remove()
                                }
                            }
                        } finally {
                            writeLockDisplay.unlock()
                        }


                        //draw shot
                        if (shootList.size > 0) {
                            writeLockShot.lock()
                            try {
                                var shootListIteratorZ = shootList.listIterator()
                                while (shootListIteratorZ.hasNext()) {
                                    var itZ = shootListIteratorZ.next()
                                    if (itZ.broken == 1) {
                                        shootListIteratorZ.remove()
                                    }
                                }
                                var shootListIterator = shootList.listIterator()
                                while (shootListIterator.hasNext()) {
                                    var it = shootListIterator.next()
                                        if (refresh) {
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
                                        } else if (towerList[it.towerId].towerPrimaryElement == "moon") {
                                            if (!crossTowerBullet(it)) {
                                                com.agsolutions.td.Companion.rotateShotBounce += 30
                                                if (com.agsolutions.td.Companion.rotateShotBounce >= 360) com.agsolutions.td.Companion.rotateShotBounce = 0f
                                                canvas.save()
                                                canvas.rotate(com.agsolutions.td.Companion.rotateShotBounce, it.bullet.x, it.bullet.y)
                                                var baseShootBounce =
                                                    Rect((it.bullet.x - (it.bullet.r * 4)).toInt(), (it.bullet.y - (it.bullet.r * 4)).toInt(), (it.bullet.x + (it.bullet.r * 4)).toInt(), (it.bullet.y + (it.bullet.r * 4)).toInt())
                                                canvas.drawBitmap(shootBouncePic!!, null, baseShootBounce, null)
                                                canvas.restore()
                                            }
                                        } else if (it.multiShotBullet && towerList[it.towerId].towerPrimaryElement == "wind") {
                                            com.agsolutions.td.Companion.rotateShotMulti += 5
                                            if (com.agsolutions.td.Companion.rotateShotMulti >= 360) com.agsolutions.td.Companion.rotateShotMulti = 0f
                                            canvas.save()
                                            canvas.rotate(com.agsolutions.td.Companion.rotateShotMulti, it.bullet.x, it.bullet.y)
                                            var baseShootMulti =
                                                Rect((it.bullet.x - (it.bullet.r * 3)).toInt(), (it.bullet.y - (it.bullet.r * 3)).toInt(), (it.bullet.x + (it.bullet.r * 3)).toInt(), (it.bullet.y + (it.bullet.r * 3)).toInt())
                                            if (day) canvas.drawBitmap(shootMultiPicDay!!, null, baseShootMulti, null)
                                            else canvas.drawBitmap(shootMultiPic!!, null, baseShootMulti, null)
                                            canvas.restore()
                                        } else if (towerList[it.towerId].towerPrimaryElement == "earth") {
                                            if (!crossTowerBullet(it)) {
                                                canvas.save()
                                                canvas.rotate(getAngleBullet(it), it.bullet.x, it.bullet.y)
                                                var baseShootSplash =
                                                    Rect((it.bullet.x - (it.bullet.r * 8)).toInt(), (it.bullet.y - (it.bullet.r * 8)).toInt(), (it.bullet.x + (it.bullet.r * 8)).toInt(), (it.bullet.y + (it.bullet.r * 8)).toInt())
                                                canvas.drawBitmap(shootSplashPic!!, null, baseShootSplash, null)
                                                canvas.restore()
                                            }
                                        } else if (towerList[it.towerId].towerPrimaryElement == "butterfly") {
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
                                writeLockShot.unlock()
                            }
                        }




                    //draw ice talent
                    if (iceShardSpeed > 0) {
                        writeLockIce.lock()
                        try {
                            var shootListIceIterator = shootListIce.listIterator()
                            while (shootListIceIterator.hasNext()) {
                                var it = shootListIceIterator.next()
                                if (refresh) it.update()
                                it.draw(canvas)
                            }
                        } finally {
                            writeLockIce.unlock()
                        }
                    }
                    refresh = false
                }
    }

    fun crossTowerBullet (it: Shoot): Boolean {
        var check = false
        writeLockShot.lock()
        try {
            /*

                if (it.bullet!!.x < towerRange.x + 80 && it.bullet!!.x > towerRange.x - 80) {
                        if (it.bullet!!.y < towerRange.y + 80 && it.bullet!!.y > towerRange.y - 80) check = true
                    }
                if (it.bullet!!.y < towerRange.y + 80 && it.bullet!!.y > towerRange.y - 80) {
                    if (it.bullet!!.x < towerRange.x + 80 && it.bullet!!.x > towerRange.x - 80)  check = true
                }

 */
                val distanceX = (towerList[it.towerId].towerRange.x) - it.bullet!!.x
                val distanceY = (towerList[it.towerId].towerRange.y) - it.bullet!!.y

                val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                val sumOfRadius = 95

                if (squaredDistance <= sumOfRadius * sumOfRadius) {
                    check = true
                }


            return check
        } finally {
            writeLockShot.unlock()
                }
    }

    fun getAngle(it:Tower): Float {
        if (MainActivity.startScreenBool) {
            var angle =
                Math.toDegrees(atan2((rotationTowerY - (2160 /2)).toDouble(), (rotationTowerX - 175f).toDouble()))
                    .toFloat()
            if (angle < 0) {
                angle += 360f
            }
            return angle
        } else {
            var angle =
                Math.toDegrees(atan2((rotationTowerY - it.towerRange.y).toDouble(), (rotationTowerX - it.towerRange.x).toDouble()))
                    .toFloat()
            if (angle < 0) {
                angle += 360f
            }
            return angle
        }
    }

    fun getAngleBullet(bullet: Shoot): Float {
        if (MainActivity.startScreenBool) {
            var angle =
                Math.toDegrees(atan2((rotationEnemyY - towerList[bullet.towerId].towerRange.y).toDouble(), (rotationEnemyX - towerList[bullet.towerId].towerRange.x).toDouble()))
                    .toFloat()
            if (angle < 0) {
                angle += 360f
            }
            return angle
        } else {

            var angle = 0f
            if (bullet.alreadyBounced > 0) angle =
                (Math.toDegrees(atan2((rotationEnemyY - (rotationBulletY)).toDouble(), (rotationEnemyX - (rotationBulletX)).toDouble()))
                    .toFloat())
            else angle =
                Math.toDegrees(atan2((rotationEnemyY - towerList[bullet.towerId].towerRange.y).toDouble(), (rotationEnemyX - towerList[bullet.towerId].towerRange.x).toDouble()))
                    .toFloat()
            if (angle < 0) {
                angle += 360f
            }
            return angle
        }
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

        if (Utils.divisible(level, 50)) challenge(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
        else if (Utils.divisible(level, 10)) boss(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
        else {
            when (levelStatus) {
                "undef" ->
                    if (levelList.contains("tank") && tankBool) {
                        tank(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                        tankBool = false
                    } else {
                        when (levelList.random()) {
                            // 0 -> base (hp, armor, magicArmor, evade, hpReg, xp, speed)
                            "armor" -> armor(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "normal" -> normal(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "speed" -> speed(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "mass" -> mass(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "regeneration" -> reg(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "evade" -> evade(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "magic armor" -> magicArmor(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "immune" -> immune(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "shortcut" -> shortcut(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "split" -> split(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "shield" -> shield(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "manaShield" -> manaShield(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                        }
                    }
                "armor", "normal", "speed", "mass", "regeneration", "evade", "magic armor", "immune", "shortcut", "split", "shield", "manaShield" ->
                    if (levelList.contains("healer") && levelStatus != "boss" && levelStatus != "mass" && levelStatus != "challenge") {
                        when ((0..5).random()) {
                            0 -> {
                                healer(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            }
                            in 1..5 -> {
                                when (levelStatus) {
                                    "armor" -> armor(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "normal" -> normal(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "speed" -> speed(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "mass" -> mass(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "regeneration" -> reg(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "evade" -> evade(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "magic armor" -> magicArmor(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "immune" -> immune(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "shortcut" -> shortcut(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "split" -> split(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "shield" -> shield(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                    "manaShield" -> manaShield(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                                }
                            }
                        }
                    } else {
                        when (levelStatus) {
                            "armor" -> armor(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "normal" -> normal(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "speed" -> speed(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "mass" -> mass(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "regeneration" -> reg(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "evade" -> evade(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "magic armor" -> magicArmor(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "immune" -> immune(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "shortcut" -> shortcut(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "split" -> split(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "shield" -> shield(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                            "manaShield" -> manaShield(lvlHp, lvlArmor, lvlMagicArmor, lvlEvade, lvlHpReg, lvlXp, lvlSpd)
                        }
                    }
            }
        }
    }

    private fun armor(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
            if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
                var eliteMob: Int
                if (timerEliteMob == 0) {
                    eliteMob = 1
                } else {
                    eliteMob = 2
                }
                val enemyListIterator = enemyList.listIterator()
                var manaShield = 0f
                var shield = 0f
                if (!allShieldsBool) {
                    allShieldsBool = true
                    if (levelList.contains("manaShield") && levelList.contains("shield")) {
                        when ((0..2).random()) {
                            0 -> manaShieldBool = true
                            1 -> shieldBool = true
                        }
                    } else if (levelList.contains("manaShield")) {
                        when ((0..2).random()) {
                            0 -> manaShieldBool = true
                        }
                    } else if (levelList.contains("shield")) {
                        when ((0..2).random()) {
                            0 -> shieldBool = true
                        }
                    }
                }
                if (manaShieldBool) manaShield = (hp / 10f) * shieldBrakerItem
                if (shieldBool) shield = (hp / 10f) * shieldBrakerItem

                var x: Enemy =
                    Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, (armor * 10f), magicArmor * 0.5f,
                        evade * 0.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#566573"))
                if (level > 50) x =
                    Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, (armor * 15f), magicArmor * 0.5f,
                        evade * 0.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#566573"))
                if (eliteMob == 2) x.eliteMob = true
                x.name = "armor"
                x.baseSpeed = x.speed
                enemyListIterator.add(x)
                enemySpawned += 1 * eliteMob
                when ((0..5).random()) {
                    0 -> timerEliteMob = 1
                    in 1..5 -> timerEliteMob = 0
                }
                timer = if (eliteMob == 2 || timerEliteMob == 1) 0
                else 25
                levelStatus = "armor"
                levelCountSecondBool = true

            }
        } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun magicArmor(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                if (levelList.contains("manaShield") && levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                        1 -> shieldBool = true
                    }
                } else if (levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                    }
                } else if (levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> shieldBool = true
                    }
                }
            }
            if (manaShieldBool) manaShield = (hp / 10f)* shieldBrakerItem
            if (shieldBool) shield = (hp / 10f)* shieldBrakerItem
            var x: Enemy =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor * 0.5f, (magicArmor * 10f) * wizardMagicArmorSmasher, evade * 0.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#af7ac5"))
            if (level > 50) x =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor * 0.5f, (magicArmor * 15f) * wizardMagicArmorSmasher, evade * 0.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#af7ac5"))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "magicArmor"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "magic armor"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun normal(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                if (levelList.contains("manaShield") && levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                        1 -> shieldBool = true
                    }
                } else if (levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                    }
                } else if (levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> shieldBool = true
                    }
                }
            }
            if (manaShieldBool) manaShield = (hp / 10f)* shieldBrakerItem
            if (shieldBool) shield = (hp / 10f)* shieldBrakerItem
            val x: Enemy =
                Enemy(hp * eliteMob, hp * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#6FBCAF"))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "normal"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "normal"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun shortcut(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                if (levelList.contains("manaShield") && levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                        1 -> shieldBool = true
                    }
                } else if (levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                    }
                } else if (levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> shieldBool = true
                    }
                }
            }
            if (manaShieldBool) manaShield = (hp / 10f)* shieldBrakerItem
            if (shieldBool) shield = (hp / 10f)* shieldBrakerItem
            val x: Enemy =
                Enemy(hp * eliteMob, hp * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed * 1.2f, resources.getColor(R.color.shortcut))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "shortcut"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "shortcut"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun speed(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                if (levelList.contains("manaShield") && levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                        1 -> shieldBool = true
                    }
                } else if (levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                    }
                } else if (levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> shieldBool = true
                    }
                }
            }
            if (manaShieldBool) manaShield = (hp / 10f)* shieldBrakerItem
            if (shieldBool) shield = (hp / 10f)* shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.fast))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "speed"
            x.baseSpeed = x.speed
            x.extraSpeed = 3.0f
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "speed"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun mass(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 20 && enemySpawned < 16 + (midnightMadnessExtraSpawn *2) ) {
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                if (levelList.contains("manaShield") && levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                        1 -> shieldBool = true
                    }
                } else if (levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                    }
                } else if (levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> shieldBool = true
                    }
                }
            }
            if (manaShieldBool) manaShield = (hp / 10f)* shieldBrakerItem
            if (shieldBool) shield = (hp / 10f)* shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.7f, hp * 0.7f, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp, speed, Color.parseColor("#f64545"))
            x.name = "mass"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1
            timer = 0
            levelStatus = "mass"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun reg(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                if (levelList.contains("manaShield") && levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                        1 -> shieldBool = true
                    }
                } else if (levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                    }
                } else if (levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> shieldBool = true
                    }
                }
            }
            if (manaShieldBool) manaShield = (hp / 10f)* shieldBrakerItem
            if (shieldBool) shield = (hp / 10f)* shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * (hp / 500), xp * eliteMob, speed, Color.parseColor("#fff116"))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "regeneration"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "regeneration"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun evade(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                if (levelList.contains("manaShield") && levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                        1 -> shieldBool = true
                    }
                } else if (levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                    }
                } else if (levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> shieldBool = true
                    }
                }
            }
            if (manaShieldBool) manaShield = (hp / 10f)* shieldBrakerItem
            if (shieldBool) shield = (hp / 10f)* shieldBrakerItem
            var x: Enemy =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade * 5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#d5dbdb"))
            if (level > 50) x =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade * 7.5f, hpReg * 0, xp * eliteMob, speed, Color.parseColor("#d5dbdb"))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "evade"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "evade"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun boss(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 20 && enemySpawned < 1) {
            val enemyListIterator = enemyList.listIterator()
            val x: Enemy =
                Enemy(hp * 6.0f, hp * 6.0f, 0f, 0f, 0f, 0f, armor, magicArmor, evade, hpReg * (hp / 1000), xp * 6f, speed, resources.getColor(R.color.boss))
            x.name = "boss"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1
            timer = 0
            levelStatus = "boss"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun challenge(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 20 && enemySpawned < 1) {
            val enemyListIterator = enemyList.listIterator()
            val x: Enemy =
                Enemy(hp * 15.0f, hp * 15.0f, 0f, 0f, 0f, 0f, armor * 4, magicArmor * 4, evade * 0, hpReg * 0, xp * 0, speed, resources.getColor(R.color.challenge))
            x.name = "challenge"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1
            timer = 0
            levelStatus = "challenge"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun immune(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {

        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                if (levelList.contains("manaShield") && levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                        1 -> shieldBool = true
                    }
                } else if (levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                    }
                } else if (levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> shieldBool = true
                    }
                }
            }
            if (manaShieldBool) manaShield = (hp / 10f)* shieldBrakerItem
            if (shieldBool) shield = (hp / 10f)* shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.7f * eliteMob, hp * 0.7f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.immune))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "immune"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "immune"
            levelCountSecondBool = true

        }
        } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun split(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                if (levelList.contains("manaShield") && levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                        1 -> shieldBool = true
                    }
                } else if (levelList.contains("manaShield")) {
                    when ((0..2).random()) {
                        0 -> manaShieldBool = true
                    }
                } else if (levelList.contains("shield")) {
                    when ((0..2).random()) {
                        0 -> shieldBool = true
                    }
                }
            }
            if (manaShieldBool) manaShield = (hp / 10f)* shieldBrakerItem
            if (shieldBool) shield = (hp / 10f)* shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.5f * eliteMob, hp * 0.5f * eliteMob, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.split))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "split"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "split"
            levelCountSecondBool = true

        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun shield(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                shieldBool = true
            }
            if (shieldBool) shield = (hp * 0.4f * eliteMob)* shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.1f, hp * 0.1f, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.Shield))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "shield"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "shield"
            levelCountSecondBool = true
        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun manaShield(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8 + midnightMadnessExtraSpawn) {
            var eliteMob: Int
            if (timerEliteMob == 0) {
                eliteMob = 1
            } else {
                eliteMob = 2
            }
            val enemyListIterator = enemyList.listIterator()
            var manaShield = 0f
            var shield = 0f
            if (!allShieldsBool) {
                allShieldsBool = true
                manaShieldBool = true
            }
            if (manaShieldBool) manaShield = (hp * 0.4f * eliteMob)* shieldBrakerItem
            val x: Enemy =
                Enemy(hp * 0.1f, hp * 0.1f, manaShield, manaShield, shield, shield, armor, magicArmor, evade, hpReg * 0, xp * eliteMob, speed, resources.getColor(R.color.ManaShield))
            if (eliteMob == 2) x.eliteMob = true
            x.name = "manaShield"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1 * eliteMob
            when ((0..5).random()) {
                0 -> timerEliteMob = 1
                in 1..5 -> timerEliteMob = 0
            }
            timer = if (eliteMob == 2 || timerEliteMob == 1) 0
            else 25
            levelStatus = "manaShield"
            levelCountSecondBool = true
        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun healer(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
        if (timer >= 50 && enemySpawned < 8) {
            val enemyListIterator = enemyList.listIterator()
            val x: Enemy =
                Enemy(hp * 1.0f, hp * 1.0f, 0f, 0f, 0f, 0f, armor * 0, magicArmor * 0, evade, hpReg * 0, xp * 1.5f, speed, resources.getColor(R.color.healer))
            x.name = "healer"
            x.baseSpeed = x.speed
            enemyListIterator.add(x)
            enemySpawned += 1
            timer = 25
        }
            } finally {
                writeLockEnemy.unlock()
        }
    }

    private fun tank(hp: Float, armor: Float, magicArmor: Float, evade: Float, hpReg: Float, xp: Float, speed: Float) {
        writeLockEnemy.lock()
        try {
            if (timer >= 50) {
                val enemyListIterator = enemyList.listIterator()
                val x: Enemy =
                    Enemy(hp * 2f, hp * 2f, 0f, 0f, 0f, 0f, armor * 3, magicArmor * 3, evade * 0, hpReg * 0, xp * 1.5f, speed, resources.getColor(R.color.tank))
                x.name = "tank"
                x.baseSpeed = x.speed
                enemyListIterator.add(x)
                enemySpawned += 1
                timer = 25
            }
        } finally {
            writeLockEnemy.unlock()
        }
    }
}

// TODO Item insert : Gameactivty - Items fragment - itemAdapter - ItemBagAdapter - Itemupgradefragment - Gameactivity Load Game

/*
      if (day) paint.color = (Color.parseColor("#ffdab9"))
      else paint.color = (Color.parseColor("#FFBF86"))
          paint2.color = (Color.parseColor("#B29881"))
          paint3.color = (Color.parseColor("#591eb6"))
          canvas.drawRect(160f, 970f, 990f, 1050f, paint)
          canvas.drawRect(910f, 970f, 990f, 1210f, paint)
          canvas.drawCircle(950f, 1200f, 70f, paint)
          canvas.drawCircle(950f, 1200f, 30f, paint2)
          canvas.drawRect(160f, 470f, 240f, 1050f, paint)
          canvas.drawRect(385f, 470f, 415f, 1050f, paint)
          canvas.drawRect(160f, 470f, 840f, 550f, paint)
          canvas.drawRect(760f, 470f, 840f, 1200f, paint)
          canvas.drawCircle(800f, 1200f, 70f, paint)
          canvas.drawCircle(800f, 1200f, 30f, paint2)

          var skelFront1: Bitmap? = null
    var skelFront2: Bitmap? = null
    var skelFront3: Bitmap? = null
    var skelBack1: Bitmap? = null
    var skelBack2: Bitmap? = null
    var skelBack3: Bitmap? = null
    var skelLeft1: Bitmap? = null
    var skelLeft2: Bitmap? = null
    var skelLeft3: Bitmap? = null
    var skelRight1: Bitmap? = null
    var skelRight2: Bitmap? = null
    var skelRight3: Bitmap? = null


          skelFront1 = BitmapFactory.decodeResource(context.resources, R.drawable.skelfrontone)
        skelFront2 = BitmapFactory.decodeResource(context.resources, R.drawable.skelfronttwo)
        skelFront3 = BitmapFactory.decodeResource(context.resources, R.drawable.skelfrontthree)
        skelBack1 = BitmapFactory.decodeResource(context.resources, R.drawable.skelbackone)
        skelBack2 = BitmapFactory.decodeResource(context.resources, R.drawable.skelbacktwo)
        skelBack3 = BitmapFactory.decodeResource(context.resources, R.drawable.skelbackthree)
        skelLeft1 = BitmapFactory.decodeResource(context.resources, R.drawable.skelleftone)
        skelLeft2 = BitmapFactory.decodeResource(context.resources, R.drawable.skellefttwo)
        skelLeft3 = BitmapFactory.decodeResource(context.resources, R.drawable.skelleftthree)
        skelRight1 = BitmapFactory.decodeResource(context.resources, R.drawable.skelrightone)
        skelRight2 = BitmapFactory.decodeResource(context.resources, R.drawable.skelrighttwo)
        skelRight3 = BitmapFactory.decodeResource(context.resources, R.drawable.skelrightthree)

*/

/*
                        rectSkeleton.set((it.circle.x - 24).toInt(), (it.circle.y - 32).toInt(), (it.circle.x + 24).toInt(), (it.circle.y + 32).toInt())
                        skeletonCount +=1
                        paintSkel.color = it.color
                        when (it.passed) {
                            0.toByte(), 2.toByte() -> {
                                when (skeletonCount){
                                    in 1..60 -> canvas.drawBitmap(skelBack1!!, null, rectSkeleton, paintSkel)
                                    in 61..120 -> canvas.drawBitmap(skelBack2!!, null, rectSkeleton, paintSkel)
                                    in 121..180 -> canvas.drawBitmap(skelBack3!!, null, rectSkeleton, paintSkel)
                                    in 181..240 -> canvas.drawBitmap(skelBack2!!, null, rectSkeleton, paintSkel)
                                }
                            }
                            1.toByte() -> {
                                when (skeletonCount){
                                    in 1..60 -> canvas.drawBitmap(skelLeft1!!, null, rectSkeleton, paintSkel)
                                    in 61..120 -> canvas.drawBitmap(skelLeft2!!, null, rectSkeleton, paintSkel)
                                    in 121..180 -> canvas.drawBitmap(skelLeft3!!, null, rectSkeleton, paintSkel)
                                    in 181..240 -> canvas.drawBitmap(skelLeft2!!, null, rectSkeleton, paintSkel)
                                }
                            }
                            3.toByte() -> {
                                when (skeletonCount){
                                    in 1..60 -> canvas.drawBitmap(skelRight1!!, null, rectSkeleton, paintSkel)
                                    in 61..120 -> canvas.drawBitmap(skelRight2!!, null, rectSkeleton, paintSkel)
                                    in 121..180 -> canvas.drawBitmap(skelRight3!!, null, rectSkeleton, paintSkel)
                                    in 181..240 -> canvas.drawBitmap(skelRight2!!, null, rectSkeleton, paintSkel)
                                }
                            }
                            4.toByte() -> {
                                when (skeletonCount){
                                    in 1..60 -> canvas.drawBitmap(skelFront1!!, null, rectSkeleton, paintSkel)
                                    in 61..120 -> canvas.drawBitmap(skelFront2!!, null, rectSkeleton, paintSkel)
                                    in 121..180 -> canvas.drawBitmap(skelFront3!!, null, rectSkeleton, paintSkel)
                                    in 181..240 -> canvas.drawBitmap(skelFront2!!, null, rectSkeleton, paintSkel)
                                }
                            }
                        }
                        if (skeletonCount >= 240) skeletonCount = 0

                         */