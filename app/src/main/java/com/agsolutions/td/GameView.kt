package com.agsolutions.td

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.agsolutions.td.GameActivity.Companion.companionList
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
        var paintIceWizard = Paint ()
        var paintPoisonUlti = Paint ()
        var paintMine = Paint ()

        var towerBase: Bitmap? = null
        var dragRect : Rect? = null
        var clipRect : Rect? = null
        var dragRectList = mutableListOf<Rect>()
        var dragRectListTower = mutableListOf<DragRectListData>()

        var scaleFactor = 1f
        var focusCanvasX = 0f
        var focusCanvasY = 0f
        var focusVar = 0f
    }

    private var thread: GameThread
    val enemy = Enemy(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0)
    var firstBoot = true

    var MIN_ZOOM = 1f
    var MAX_ZOOM = 3f


    var detector = ScaleGestureDetector(getContext(), ScaleListener())
    var lastTouchX = 0f
    var lastTouchY = 0f
    var pressDuration = 0

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

    var rectForAll = Rect(0, 0, 0, 0)
    var paintRange = Paint()
    var paintLaser = Paint ()
    var paintHpBar = Paint()
    var paintHpBarNight = Paint()
    var paintHpBarBack = Paint()
    var paintLasso = Paint()
    var paintManaShield = Paint()
    var paintShield = Paint()
    var paintText = Paint ()
    var paintDisrupt = Paint ()

    var towerGunBasic: Bitmap? = null
    var towerGunBlue: Bitmap? = null
    var towerGunOrange: Bitmap? = null
    var towerGunPurple: Bitmap? = null
    var goldDrop: Bitmap? = null
    var upDrop: Bitmap? = null
    var ipDrop: Bitmap? = null

    var elementButterfly : Bitmap? = null
    var elementDark : Bitmap? = null
    var elementEarth : Bitmap? = null
    var elementFire : Bitmap? = null
    var elementIce : Bitmap? = null
    var elementMoon : Bitmap? = null
    var elementPoison : Bitmap? = null
    var elementUtils : Bitmap? = null
    var elementWind : Bitmap? = null
    var elementWizard : Bitmap? = null

    var icon: Bitmap? = null


    //initialize items----------------------------------------------------------------------------

    init {
        dragRect = Rect()
        clipRect = Rect()

        paintIceWizard.isAntiAlias
        paintIceWizard.isFilterBitmap
        paintIceWizard.color = Color.WHITE
        paintMine.isAntiAlias
        paintMine.isFilterBitmap
        paintMine.color = Color.parseColor("#653F05")
        paintPoisonUlti.isAntiAlias
        paintPoisonUlti.isFilterBitmap
        paintPoisonUlti.color = Color.GREEN

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
        paintDisrupt.color = Color.parseColor("#500E28")
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

         elementButterfly = BitmapFactory.decodeResource(context.resources, R.drawable.talentsbutterfly)
         elementDark = BitmapFactory.decodeResource(context.resources, R.drawable.talentsdark)
         elementEarth = BitmapFactory.decodeResource(context.resources, R.drawable.talentsearth)
         elementFire = BitmapFactory.decodeResource(context.resources, R.drawable.talentsfire)
         elementIce = BitmapFactory.decodeResource(context.resources, R.drawable.talentsice)
         elementMoon = BitmapFactory.decodeResource(context.resources, R.drawable.moon)
         elementPoison = BitmapFactory.decodeResource(context.resources, R.drawable.talentspoison)
         elementUtils = BitmapFactory.decodeResource(context.resources, R.drawable.talentsutils)
         elementWind = BitmapFactory.decodeResource(context.resources, R.drawable.talentswind)
         elementWizard = BitmapFactory.decodeResource(context.resources, R.drawable.talentswizard)

        goldDrop = BitmapFactory.decodeResource(context.resources, R.drawable.goldicon)
        upDrop = BitmapFactory.decodeResource(context.resources, R.drawable.upgradepointsicon)
        ipDrop = BitmapFactory.decodeResource(context.resources, R.drawable.itempointsicon)
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
        icon = goldDrop

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

    private inner class ScaleListener : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = Math.max(MIN_ZOOM, Math.min(scaleFactor, MAX_ZOOM))
            focusCanvasX = detector.focusX
            focusCanvasY = detector.focusY
            Log.d("blablax", focusCanvasX.toString())
            Log.d("blablay", focusCanvasY.toString())
            invalidate()
            return true
        }
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


                var scaler = ((companionList.scaleScreen / 10) * GameView.scaleFactor)
                val x = event!!.x
                val y = event.y

        if (event!!.action == MotionEvent.ACTION_MOVE) {
            pressDuration++

            if (pressDuration > 15){

                var dx = (x - lastTouchX)
                var dy = (y - lastTouchY)

                focusCanvasX -= dx
                focusCanvasY -= dy

                lastTouchX = x;
                lastTouchY = y;

                invalidate();
            }
        }
        else if (event!!.action == MotionEvent.ACTION_UP) {
            pressDuration = 0

        }else if (event!!.action == MotionEvent.ACTION_DOWN) {
            lastTouchX = x
            lastTouchY = y

        }

                if (tower(x, y, scaler)) {
                    return super.onTouchEvent(event)
                } else if ((!companionList.autoSpawn && companionList.enemyList.isEmpty() && x > ((875 * scaler) - (GameView.clipRect!!.left * scaler)) && x < ((1025 * scaler) - (GameView.clipRect!!.left * scaler)) && y > ((1145 * scaler) - (GameView.clipRect!!.top * scaler)) && y < ((1275 * scaler) - (GameView.clipRect!!.top * scaler))) && companionList.mapMode == 1) {
                    companionList.towerClick = false
                    companionList.build = true
                    companionList.buildClickBool = true
                    companionList.spawnEnemy = true
                    return super.onTouchEvent(event)
                } else if ((x > ((875 * scaler) - (GameView.clipRect!!.left * scaler)) && x < ((1025 * scaler) - (GameView.clipRect!!.left * scaler)) && y > ((1145 * scaler) - (GameView.clipRect!!.top * scaler)) && y < ((1275 * scaler) - (GameView.clipRect!!.top * scaler))) && companionList.mapMode == 1)  {
                    companionList.towerClick = false
                    companionList.build = true
                    companionList.buildClickBool = true
                    companionList.autoSpawnClick = true
                        if (companionList.autoSpawn) {
                            companionList.autoSpawn = false
                            companionList.spawnEnemy = false
                        } else if (!companionList.autoSpawn) {
                            companionList.autoSpawn = true
                            companionList.spawnEnemy = false
                        }
                    return super.onTouchEvent(event)
                }else if (enemy(x, y, scaler)){
                    return super.onTouchEvent(event)
                }else {
                    companionList.towerClick = false
                    companionList.enemyClick = false
                    companionList.build = true
                    companionList.buildClickBool = true
                    detector.onTouchEvent(event)
                    return true
                }

      //  return super.onTouchEvent(event)
      //  return true
    }

    fun enemy (x: Float, y: Float, scaler: Float) : Boolean{
        var bool = false
        companionList.readLockEnemy.lock()
        try {

        var enemyListIterator = companionList.enemyList.listIterator()
        while (enemyListIterator.hasNext()) {
            var it = enemyListIterator.next()
            it.selected = false
        }
        var enemyListIteratorZ = companionList.enemyList.listIterator()
        while (enemyListIteratorZ.hasNext()) {
            var it = enemyListIteratorZ.next()
            val distanceX = (x + (GameView.clipRect!!.left * scaler)) - (it.circle!!.x * scaler)
            val distanceY = (y + (GameView.clipRect!!.top * scaler)) - (it.circle!!.y * scaler)

            val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

            val sumOfRadius = (1 + it.circle!!.r * scaler)

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
             } finally {
                companionList.readLockEnemy.unlock()
            }
        return bool
    }

    fun tower (x: Float, y: Float, scaler: Float) : Boolean{
        var bool = false

        companionList.readLockTower.lock()
        try {

        var towerListIterator = companionList.towerList.listIterator()
        while (towerListIterator.hasNext()) {
            var it = towerListIterator.next()
            it.selected = false
        }

        var towerListIteratorZ = companionList.towerList.listIterator()
        while (towerListIteratorZ.hasNext()) {
            var it = towerListIteratorZ.next()
            val distanceX = (x + (GameView.clipRect!!.left * scaler)) - (it.towerRange.x * scaler)
            val distanceY = (y + (GameView.clipRect!!.top * scaler)) - (it.towerRange.y * scaler)

            val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

            val sumOfRadius = (1 + 80 * scaler)

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
            } finally {
                        companionList.readLockTower.unlock()
                }
        return bool
    }



    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------

    override fun draw(canvas: Canvas) {
        super.draw(canvas)


        // ------------------------------------------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------------------------------------------
        //  MAIN ACTIVITY ---------------------------------------------------------------------------------------------------------------------------------

        //scale canvas for different devices

        focusVar = scaleFactor - 1
        canvas.translate(focusCanvasX * (focusVar) * -1, focusCanvasY * (focusVar) * -1)
        canvas.scale(
            ((companionList.scaleScreen / 10) * scaleFactor), ((companionList.scaleScreen / 10) * scaleFactor)
        )

        GameView.clipRect = canvas.clipBounds

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
        if (companionList.mapMode == 1) {
            if (companionList.autoSpawn) canvas.drawText("ON", 910f, 1225f, paintText)
            else if (companionList.enemyList.isEmpty()) canvas.drawText("NXT", 910f, 1225f, paintText)
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
                        if (!GameActivity.paused) it.towerFallingCount += (8 * companionList.gameSpeedAdjuster).toInt()
                        var rect =
                            Rect((it.towerRange.x - 80).toInt(), it.towerFallingCount, it.towerRange.x.toInt() + 80, it.towerFallingCount + 160)
                        canvas.drawBitmap(towerFalling!!, null, rect, null)
                        if (it.towerFallingCount > it.towerRange.y.toInt() - 80) it.towerFallingCount =
                            0
                    } else {
                        var rect =
                            Rect((it.towerRange.x - 80).toInt(), (it.towerRange.y - 80).toInt(), (it.towerRange.x + 80).toInt(), (it.towerRange.y + 80).toInt())
                        canvas.drawBitmap(towerBase!!, null, rect, null)

                        if (companionList.towerClick && it.selected) canvas.drawCircle(it.towerRange.x, it.towerRange.y, it.towerR, paintRange)
                        if (companionList.towerClick && it.selected && it.itemListBag.contains(GameActivity.companionList.eutils)) canvas.drawCircle(it.towerRange.x, it.towerRange.y, 150f, paintRange)
                        var rect2 =
                            Rect((it.towerRange.x - 60).toInt(), (it.towerRange.y + 30).toInt(), it.towerRange.x.toInt() - 30, it.towerRange.y.toInt() + 60)
                        if (it.talentPoints > 0) canvas.drawBitmap(talentP!!, null, rect2, null)
                        var textsize = paintTowerDmgDone.measureText(it.towerLevel.toString())
                        canvas.drawText(it.towerLevel.toString(), (it.towerRange.x + 55 - (textsize / 2)), (it.towerRange.y + 60), paintTowerDmgDone)
                        if (it.bagSizeElementCount > 0) {
                            var yBool = 0
                            var bitmap = elementButterfly
                            for (item in it.itemListBag) {
                                var elementIsThere = false
                                when (item) {
                                    companionList.eearth -> {
                                        bitmap = elementEarth
                                        elementIsThere = true
                                    }
                                    companionList.ebutterfly -> {
                                        bitmap = elementButterfly
                                        elementIsThere = true
                                    }
                                    companionList.ewind -> {
                                        bitmap = elementWind
                                        elementIsThere = true
                                    }
                                    companionList.emoon -> {
                                        bitmap = elementMoon
                                        elementIsThere = true
                                    }
                                    companionList.epoison -> {
                                        bitmap = elementPoison
                                        elementIsThere = true
                                    }
                                    companionList.eice -> {
                                        bitmap = elementIce
                                        elementIsThere = true
                                    }
                                    companionList.efire -> {
                                        bitmap = elementFire
                                        elementIsThere = true
                                    }
                                    companionList.edark -> {
                                        bitmap = elementDark
                                        elementIsThere = true
                                    }
                                    companionList.eutils -> {
                                        bitmap = elementUtils
                                        elementIsThere = true
                                    }
                                    companionList.ewizard -> {
                                        bitmap = elementWizard
                                        elementIsThere = true
                                    }
                                }
                                if (elementIsThere) {
                                    var rect =
                                        Rect((it.towerRange.x + 35).toInt(), (it.towerRange.y + yBool).toInt(), it.towerRange.x.toInt() + 75, it.towerRange.y.toInt() + yBool + 40)
                                    canvas.drawBitmap(bitmap!!, null, rect, null)
                                    yBool -= 35
                                }
                            }
                        }

                        canvas.save()
                        canvas.rotate(getAngle(it), it.towerRange.x, it.towerRange.y)
                        var rect3 =
                            Rect((it.towerRange.x - 84).toInt(), (it.towerRange.y - 64).toInt(), it.towerRange.x.toInt() + 172, it.towerRange.y.toInt() + 64)
                        when (it.towerLevel) {
                            in 1..4 -> canvas.drawBitmap(towerGunBasic!!, null, rect3, null)
                            in 5..9 -> canvas.drawBitmap(towerGunBlue!!, null, rect3, null)
                            in 10..14 -> canvas.drawBitmap(towerGunOrange!!, null, rect3, null)
                            in 15..999 -> canvas.drawBitmap(towerGunPurple!!, null, rect3, null)
                        }
                        canvas.restore()
                    }
                    if (it.disrupted) canvas.drawCircle(it.towerRange.x, it.towerRange.y, 30f, paintDisrupt)
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
                    var rect =
                            Rect((it.poisonCloud.x - (it.poisonCloud.r * 1.5)).toInt(), (it.poisonCloud.y - (it.poisonCloud.r * 1.5)).toInt(), (it.poisonCloud.x + (it.poisonCloud.r * 1.5)).toInt(), (it.poisonCloud.y + (it.poisonCloud.r * 1.5)).toInt())
                        it.poisonPicCounter++
                        if (it.poisonPicCounter >= 5) {
                            it.poisonPicCounter = 0
                            it.poisonNextPic++
                            if (it.poisonNextPic >= 2) it.poisonNextPic = 0
                        }
                        canvas.drawBitmap(shootPoisonArray!![it.poisonNextPic], null, rect, null)

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
                        companionList.rotateTornado++
                        if (companionList.rotateTornado >= 360) companionList.rotateTornado = 0f
                        canvas.save()
                        canvas.rotate(companionList.rotateTornado, it.tornadoRadius.x, it.tornadoRadius.y)
                    var rect =
                            Rect((it.tornadoRadius.x - (it.tornadoRadius.r)).toInt(), (it.tornadoRadius.y - (it.tornadoRadius.r)).toInt(), (it.tornadoRadius.x + (it.tornadoRadius.r)).toInt(), (it.tornadoRadius.y + (it.tornadoRadius.r)).toInt())
                        if (companionList.day) canvas.drawBitmap(shootMultiPicDay!!, null, rectForAll, null)
                        else canvas.drawBitmap(shootMultiPic!!, null, rect, null)
                        canvas.restore()
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
                    var rect =
                            Rect((it.mineRadius.x - (it.mineRadius.r * 1.25)).toInt(), (it.mineRadius.y - (it.mineRadius.r * 1.25)).toInt(), (it.mineRadius.x + (it.mineRadius.r * 1.25)).toInt(), (it.mineRadius.y + (it.mineRadius.r * 1.25)).toInt())
                        it.minePicCounter++
                        if (it.minePicCounter >= 5) {
                            it.minePicCounter = 0
                            it.mineNextPic++
                            if (it.mineNextPic >= 5) it.mineNextPic = 0
                        }
                        canvas.drawBitmap(mineArray!![it.mineNextPic], null, rect, null)

                }
            } finally {
                companionList.writeLockMine.unlock()
            }
        }

        //draw enemy

        if (companionList.enemyList.size > 0) {
            companionList.writeLockEnemy.lock()
            try {
                    var enemyListIterator = companionList.enemyList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var it = enemyListIterator.next()
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
                            it.explosionCounter++
                            when (it.explosionCounter) {
                                in 1..4 -> {
                                    var rect =
                                        Rect((it.circle!!.x - 12).toInt(), (it.circle!!.y - 15).toInt(), (it.circle!!.x + 12).toInt(), (it.circle!!.y + 14).toInt())
                                    canvas.drawBitmap(explosion!!, null, rect, null)
                                }
                                in 5..10 -> {
                                    var rect =
                                        Rect((it.circle!!.x - 12).toInt(), (it.circle!!.y - 15).toInt(), (it.circle!!.x + 12).toInt(), (it.circle!!.y + 14).toInt())
                                    canvas.drawBitmap(explosion2!!, null, rect, null)
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
                                        if (it.circleYMovement == "yminus" || it.circleYMovement == "yplus") canvas.drawText(display.dmgReceived.toString(), ((it.circle!!.x + display.dmgCountPosition)), (it.circle!!.y - (it.circle!!.r / 2) + display.positionY), display.paint)
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

        if (companionList.dmgDisplayDropList.isNotEmpty()) {
            companionList.writeLockDisplayDrop.lock()
            try {
                var dmgDisplayListIterator = companionList.dmgDisplayDropList.listIterator()
                while (dmgDisplayListIterator.hasNext()) {
                    var display = dmgDisplayListIterator.next()

                    //        for (display in dmgDisplayList) {
                    //            Log.d("thread", Thread.currentThread().name)
                    display.dmgCount++
                    if (display.dmgCountPosition > 1) display.dmgCountPosition += 2
                    else display.dmgCountPosition -= 2
                    icon =
                        if (display.icon == "gold") goldDrop else if (display.icon == "ip") ipDrop else upDrop
                    var rect =
                        Rect((display.indexx - 25), (display.indexy - 75 - display.dmgCountPosition), (display.indexx + 25), (display.indexy - 25 - display.dmgCountPosition))
                    canvas.drawBitmap(icon!!, null, rect, null)
                    if (display.dmgCount > 25) {
                        //   display.burnDmgDelete = fireDmgDisplay.indexOf(display)
                        display.displayDmgDelete = true
                    }
                }
            } finally {
                companionList.writeLockDisplayDrop.unlock()
            }
        }



                        //draw shot
                        if (companionList.shootList.size > 0) {
                            companionList.writeLockShot.lock()
                            companionList.writeLockEnemy.lock()
                            try {
                                if (companionList.shootList.size > 0) {
                                var shootListIterator = companionList.shootList.listIterator()
                                while (shootListIterator.hasNext()) {
                                    var it = shootListIterator.next()
                                    if (it.sniper) {
                                        if (!crossTowerBullet(it)) {
                                            canvas.save()
                                            canvas.rotate(getAngleBullet(it), it.bullet.x, it.bullet.y)
                                            var rect =
                                                Rect((it.bullet.x - (it.bullet.r * 4)).toInt(), (it.bullet.y - (it.bullet.r * 4)).toInt(), (it.bullet.x + (it.bullet.r * 4)).toInt(), (it.bullet.y + (it.bullet.r * 4)).toInt())
                                            canvas.drawBitmap(shootBulletPic!!, null, rect, null)
                                            canvas.restore()
                                        }
                                    } else if (it.chainLightning) {
                                        canvas.save()
                                        canvas.rotate(getAngleBullet(it) - 75, it.bullet.x, it.bullet.y)
                                        var rect =
                                            Rect((it.bullet.x - (it.bullet.r * 3)).toInt(), (it.bullet.y - (it.bullet.r * 3)).toInt(), (it.bullet.x + (it.bullet.r * 3)).toInt(), (it.bullet.y + (it.bullet.r * 3)).toInt())
                                        canvas.drawBitmap(shootChainLightningPic!!, null, rect, null)
                                        canvas.restore()
                                    } else if (companionList.towerList[it.towerId].towerPrimaryElement == "moon") {
                                        if (!crossTowerBullet(it)) {
                                            companionList.rotateShotBounce += 30
                                            if (companionList.rotateShotBounce >= 360) companionList.rotateShotBounce =
                                                0f
                                            canvas.save()
                                            canvas.rotate(companionList.rotateShotBounce, it.bullet.x, it.bullet.y)
                                            var rect =
                                                Rect((it.bullet.x - (it.bullet.r * 4)).toInt(), (it.bullet.y - (it.bullet.r * 4)).toInt(), (it.bullet.x + (it.bullet.r * 4)).toInt(), (it.bullet.y + (it.bullet.r * 4)).toInt())
                                            canvas.drawBitmap(shootBouncePic!!, null, rect, null)
                                            canvas.restore()
                                        }
                                    } else if (it.multiShotBullet && companionList.towerList[it.towerId].towerPrimaryElement == "wind") {
                                        companionList.rotateShotMulti += 5
                                        if (companionList.rotateShotMulti >= 360) companionList.rotateShotMulti =
                                            0f
                                        canvas.save()
                                        canvas.rotate(companionList.rotateShotMulti, it.bullet.x, it.bullet.y)
                                        var rect =
                                            Rect((it.bullet.x - (it.bullet.r * 3)).toInt(), (it.bullet.y - (it.bullet.r * 3)).toInt(), (it.bullet.x + (it.bullet.r * 3)).toInt(), (it.bullet.y + (it.bullet.r * 3)).toInt())
                                        if (companionList.day) canvas.drawBitmap(shootMultiPicDay!!, null, rect, null)
                                        else canvas.drawBitmap(shootMultiPic!!, null, rect, null)
                                        canvas.restore()
                                    } else if (companionList.towerList[it.towerId].towerPrimaryElement == "earth") {
                                        if (!crossTowerBullet(it)) {
                                            canvas.save()
                                            canvas.rotate(getAngleBullet(it), it.bullet.x, it.bullet.y)
                                            var rect =
                                                Rect((it.bullet.x - (it.bullet.r * 8)).toInt(), (it.bullet.y - (it.bullet.r * 8)).toInt(), (it.bullet.x + (it.bullet.r * 8)).toInt(), (it.bullet.y + (it.bullet.r * 8)).toInt())
                                            canvas.drawBitmap(shootSplashPic!!, null, rect, null)
                                            canvas.restore()
                                        }
                                    } else if (companionList.towerList[it.towerId].towerPrimaryElement == "butterfly") {
                                        if (!crossTowerBullet(it)) {
                                            canvas.save()
                                            canvas.rotate(getAngleBullet(it) - 90, it.bullet.x, it.bullet.y)
                                            var rect =
                                                Rect((it.bullet.x - (it.bullet.r * 7)).toInt(), (it.bullet.y - (it.bullet.r * 7)).toInt(), (it.bullet.x + (it.bullet.r * 7)).toInt(), (it.bullet.y + (it.bullet.r * 7)).toInt())
                                            it.butterflyPicCounter ++
                                            if (it.butterflyPicCounter > 2) {
                                                it.butterflyPicCounter = 0
                                                it.butterflyNextPic ++
                                                if (it.butterflyNextPic >= 4) it.butterflyNextPic =
                                                    0
                                            }
                                            canvas.drawBitmap(shootButterflyArray!![it.butterflyNextPic], null, rect, null)
                                            canvas.restore()
                                        }
                                    } else {
                                        if (!crossTowerBullet(it)) {
                                            canvas.save()
                                            canvas.rotate(getAngleBullet(it), it.bullet.x, it.bullet.y)
                                            var rect =
                                                Rect((it.bullet.x - (it.bullet.r * 4)).toInt(), (it.bullet.y - (it.bullet.r * 4)).toInt(), (it.bullet.x + (it.bullet.r * 4)).toInt(), (it.bullet.y + (it.bullet.r * 4)).toInt())
                                            canvas.drawBitmap(shootBulletPic!!, null, rect, null)
                                            canvas.restore()
                                        }
                                    }
                                }
                                }
                            } finally {
                                companionList.writeLockShot.unlock()
                                companionList.writeLockEnemy.unlock()
                            }
                        }




                    //draw ice talent

                        if (companionList.towerList.isNotEmpty()) {
                            companionList.writeLockTower.lock()
                            try {
                                var towerListIterator = companionList.towerList.listIterator()
                                while (towerListIterator.hasNext()) {
                                    var it = towerListIterator.next()
                                    if (it.iceShard > 0) {
                                        var shootListIceIterator = it.shootListIce.listIterator()
                                        while (shootListIceIterator.hasNext()) {
                                            var shard = shootListIceIterator.next()
                                            shard.draw(canvas)
                                        }
                                    }
                                }
                            } finally {
                                companionList.writeLockTower.unlock()
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

}



// TODO Item insert : Gameactivty - Items fragment - itemAdapter - ItemBagAdapter - Itemupgradefragment - Gameactivity Load Game