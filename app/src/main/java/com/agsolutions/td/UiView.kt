package com.agsolutions.td

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlinx.coroutines.InternalCoroutinesApi


class UiView(context: Context, attributes: AttributeSet) : View(context, attributes) {

    companion object {
        var xRecycler = 0f
        var yRecycler = 0f
        var xRecyclerBag = 0f
        var yRecyclerBag = 0f
        var xTalents = 0f
        var yTalents = 0f
        var xLevelCount = 0f
        var yLevelCount = 0f
    }

    var paintText = Paint ()
    var swipeDown: Bitmap? = null
    var fingerClick: Bitmap? = null
    var swipeUp: Bitmap? = null
    var one: Bitmap? = null
    var two: Bitmap? = null
    var three: Bitmap? = null
    var beat: Bitmap? = null
    var the: Bitmap? = null
    var highscore: Bitmap? = null
    var fingerClickToSpawn: Bitmap? = null
    var fingerClickToToggle: Bitmap? = null
    var fingerClickToToggle2: Bitmap? = null
    var fingerClickToToggle3: Bitmap? = null

    //initialize items----------------------------------------------------------------------------

    init {
        paintText.color = Color.BLUE
        paintText.style = Paint.Style.FILL_AND_STROKE
        paintText.isAntiAlias = true
        paintText.textSize = 48f * (GameActivity.companionList.scaleScreen / 10)
        paintText.setShadowLayer(10f, 0f, 0f, Color.WHITE)

        swipeDown = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipedowntouse)
        swipeUp = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipeuptodel)
        fingerClick = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclick)
        one = BitmapFactory.decodeResource(context.resources, R.drawable.one)
        two = BitmapFactory.decodeResource(context.resources, R.drawable.two)
        three = BitmapFactory.decodeResource(context.resources, R.drawable.three)
        beat = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan1)
        the = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan2)
        highscore = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan3)

        fingerClickToSpawn = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktospawn)
        fingerClickToToggle = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle)
        fingerClickToToggle2 = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle2)
        fingerClickToToggle3 = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle3)

    }

    // on Touch------------------------------------------------------

    override fun onTouchEvent(event: MotionEvent?): Boolean {

       // var x = event!!.x
       // var y = event!!.y

        return super.onTouchEvent(event)
    }


    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------

    @InternalCoroutinesApi
    override fun draw(canvas: Canvas) {

        super.draw(canvas)

        paintText.textSize = 48f * (GameActivity.companionList.scaleScreen / 10)

        if (GameActivity.companionList.focusTalentWindow) {

        } else if (GameActivity.companionList.focusMainWindow) {

            if (GameActivity.companionList.towerClick && GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.isEmpty() && GameActivity.companionList.itemList.isNotEmpty() && GameActivity.companionList.hintsBool) {
                var rect =
                    Rect(xRecycler.toInt(), yRecycler.toInt(), (xRecycler + (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yRecycler + (150 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(swipeDown!!, null, rect, null)
            }
            // TODO
            /*
            if (talentPoints > 1 && com.agsolutions.td.Companion.hintsBool) {
                var rect =
                    Rect((xTalents).toInt(), (yTalents - (15 * (scaleScreen / 10))).toInt(), (xTalents + (150 * (scaleScreen / 10))).toInt(), (yTalents + (135 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(fingerClick!!, null, rect, null)
            }

             */
            if (GameActivity.companionList.towerClick && GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.isNotEmpty() && GameActivity.companionList.hintsBool && GameActivity.companionList.tutorialFirstUseItemBool) {
                var rect =
                    Rect((xRecyclerBag + (20 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yRecyclerBag - (100 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xRecyclerBag + (320 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yRecyclerBag + (50 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(swipeUp!!, null, rect, null)
            }

            if (!GameActivity.companionList.autoSpawn && GameActivity.companionList.autoSpawnCount > 180 && GameActivity.companionList.hintsBool && GameActivity.companionList.enemyList.isEmpty()) {
                GameActivity.companionList.touchCountCounter++
                var spawnRect =
                    Rect(((950 - 225) * (GameActivity.companionList.scaleScreen / 10)).toInt(), ((1200 - 40) * (GameActivity.companionList.scaleScreen / 10)).toInt(), ((950 + 75) * (GameActivity.companionList.scaleScreen / 10)).toInt(), ((1200 + 110) * (GameActivity.companionList.scaleScreen / 10)).toInt())
                if (GameActivity.companionList.touchCountCounter > 160) GameActivity.companionList.touchCountCounter =
                    -10
                else if (GameActivity.companionList.touchCountCounter > 102) {
                    canvas.drawBitmap(fingerClickToToggle!!, null, spawnRect, null)
                } else if (GameActivity.companionList.touchCountCounter > 100) {
                    canvas.drawBitmap(fingerClickToToggle3!!, null, spawnRect, null)
                } else if (GameActivity.companionList.touchCountCounter > 85) {
                    canvas.drawBitmap(fingerClickToToggle2!!, null, spawnRect, null)
                } else if (GameActivity.companionList.touchCountCounter > 75) {
                } else if (GameActivity.companionList.touchCountCounter > 0) {
                    canvas.drawBitmap(fingerClickToSpawn!!, null, spawnRect, null)
                }
            }

            if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 1) {

            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 60) {
                var rect =
                    Rect((xLevelCount - (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (400 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xLevelCount + (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (100 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(one!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 120) {
                var rect =
                    Rect((xLevelCount - (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (400 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xLevelCount + (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (100 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(two!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 180) {
                var rect =
                    Rect((xLevelCount - (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (400 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xLevelCount + (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (100 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(three!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 300) {
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 340) {
                var rect1 =
                    Rect((xLevelCount - (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (600 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xLevelCount + (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (300 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(beat!!, null, rect1, null)
                var rect2 =
                    Rect((xLevelCount - (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (400 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xLevelCount + (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (100 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(the!!, null, rect2, null)
                var rect3 =
                    Rect((xLevelCount - (450 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (200 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xLevelCount + (450 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount + (100 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(highscore!!, null, rect3, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 380) {
                var rect1 =
                    Rect((xLevelCount - (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (600 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xLevelCount + (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (300 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(beat!!, null, rect1, null)
                var rect2 =
                    Rect((xLevelCount - (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (400 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xLevelCount + (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (100 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(the!!, null, rect2, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 420) {
                var rect =
                    Rect((xLevelCount - (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (600 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (xLevelCount + (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (yLevelCount - (300 * (GameActivity.companionList.scaleScreen / 10))).toInt())
                canvas.drawBitmap(beat!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (GameActivity.companionList.scaleScreen / 10))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            }
            if (GameActivity.companionList.dragStatusDrag) {
                var xLvl = (Resources.getSystem().getDisplayMetrics().widthPixels / 2).toFloat()
                var yLvl = (Resources.getSystem().getDisplayMetrics().heightPixels / 2).toFloat()
                    if (GameActivity.companionList.dragStatusCantBuild){
                    paintText.textSize = 72f * (GameActivity.companionList.scaleScreen / 10)
                    paintText.color = Color.parseColor("#EB1900")
                    var xLevelCountC = xLvl - (paintText.measureText("Can´t Build") / 2)
                    var yLevelCountC =
                        (yLvl - (500 * (GameActivity.companionList.scaleScreen / 10)))
                    canvas.drawText("Can´t Build", xLevelCountC, yLevelCountC, paintText)
                }else {
                    paintText.textSize = 72f * (GameActivity.companionList.scaleScreen / 10)
                    paintText.color = Color.parseColor("#009E11")
                    var xLevelCountA = xLvl - (paintText.measureText("Build Okay") / 2)
                    var yLevelCountA =
                        (yLvl - (500 * (GameActivity.companionList.scaleScreen / 10)))
                    canvas.drawText("Build Okay ", xLevelCountA, yLevelCountA, paintText)
                }
            }
        }
    }
}

class UiViewTalent(context: Context, attributes: AttributeSet) : View(context, attributes) {
    companion object {
        var talentX = 0f
        var talentY = 0f
    }


    var paintText = Paint ()
    var swipeDown: Bitmap? = null
    var fingerClick: Bitmap? = null
    var swipeUp: Bitmap? = null
    var one: Bitmap? = null
    var two: Bitmap? = null
    var three: Bitmap? = null
    var beat: Bitmap? = null
    var the: Bitmap? = null
    var highscore: Bitmap? = null
    var fingerClickToSpawn: Bitmap? = null
    var fingerClickToToggle: Bitmap? = null
    var fingerClickToToggle2: Bitmap? = null
    var fingerClickToToggle3: Bitmap? = null

    //initialize items----------------------------------------------------------------------------

    init {
        paintText.color = Color.BLUE
        paintText.style = Paint.Style.FILL_AND_STROKE
        paintText.isAntiAlias = true
        paintText.textSize = 48f * (GameActivity.companionList.scaleScreen / 10)
        paintText.setShadowLayer(10f, 0f, 0f, Color.WHITE)

        swipeDown = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipedowntouse)
        swipeUp = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipeuptodel)
        fingerClick = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclick)
        one = BitmapFactory.decodeResource(context.resources, R.drawable.one)
        two = BitmapFactory.decodeResource(context.resources, R.drawable.two)
        three = BitmapFactory.decodeResource(context.resources, R.drawable.three)
        beat = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan1)
        the = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan2)
        highscore = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan3)

        fingerClickToSpawn = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktospawn)
        fingerClickToToggle = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle)
        fingerClickToToggle2 = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle2)
        fingerClickToToggle3 = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle3)

    }

    @InternalCoroutinesApi
    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (GameActivity.companionList.focusTalentWindow && GameActivity.companionList.hintsBool && GameActivity.companionList.showHelpTalent) {
            var rect =
                Rect((talentX).toInt(), (talentY + (15 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentX + (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentY + (165 * (GameActivity.companionList.scaleScreen / 10))).toInt())
            canvas.drawBitmap(fingerClick!!, null, rect, null)
        }
    }
}

class UiViewTalentEarth(context: Context, attributes: AttributeSet) : View(context, attributes) {
    companion object {
        var talentEarthX = 0f
        var talentEarthY = 0f
    }


    var paintText = Paint ()
    var swipeDown: Bitmap? = null
    var fingerClick: Bitmap? = null
    var swipeUp: Bitmap? = null
    var one: Bitmap? = null
    var two: Bitmap? = null
    var three: Bitmap? = null
    var beat: Bitmap? = null
    var the: Bitmap? = null
    var highscore: Bitmap? = null
    var fingerClickToSpawn: Bitmap? = null
    var fingerClickToToggle: Bitmap? = null
    var fingerClickToToggle2: Bitmap? = null
    var fingerClickToToggle3: Bitmap? = null

    //initialize items----------------------------------------------------------------------------

    init {
        paintText.color = Color.BLUE
        paintText.style = Paint.Style.FILL_AND_STROKE
        paintText.isAntiAlias = true
        paintText.textSize = 48f * (GameActivity.companionList.scaleScreen / 10)
        paintText.setShadowLayer(10f, 0f, 0f, Color.WHITE)

        swipeDown = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipedowntouse)
        swipeUp = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipeuptodel)
        fingerClick = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclick)
        one = BitmapFactory.decodeResource(context.resources, R.drawable.one)
        two = BitmapFactory.decodeResource(context.resources, R.drawable.two)
        three = BitmapFactory.decodeResource(context.resources, R.drawable.three)
        beat = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan1)
        the = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan2)
        highscore = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan3)

        fingerClickToSpawn = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktospawn)
        fingerClickToToggle = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle)
        fingerClickToToggle2 = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle2)
        fingerClickToToggle3 = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle3)

    }

    @InternalCoroutinesApi
    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (GameActivity.companionList.focusEarthFragment && GameActivity.companionList.hintsBool && GameActivity.companionList.showHelpTalent) {
            var rect =
                Rect((talentEarthX).toInt(), (talentEarthY + (15 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentEarthX + (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentEarthY + (165 * (GameActivity.companionList.scaleScreen / 10))).toInt())
            canvas.drawBitmap(fingerClick!!, null, rect, null)
        }
    }
}

class UiViewStartItems(context: Context, attributes: AttributeSet) : View(context, attributes) {
    companion object {
        var talentRecyclerX = 0f
        var talentRecyclerY = 0f
    }


    var paintText = Paint ()
    var swipeDown: Bitmap? = null
    var fingerClick: Bitmap? = null
    var swipeUp: Bitmap? = null
    var one: Bitmap? = null
    var two: Bitmap? = null
    var three: Bitmap? = null
    var beat: Bitmap? = null
    var the: Bitmap? = null
    var highscore: Bitmap? = null
    var fingerClickToSpawn: Bitmap? = null
    var fingerClickToToggle: Bitmap? = null
    var fingerClickToToggle2: Bitmap? = null
    var fingerClickToToggle3: Bitmap? = null

    //initialize items----------------------------------------------------------------------------

    init {
        paintText.color = Color.BLUE
        paintText.style = Paint.Style.FILL_AND_STROKE
        paintText.isAntiAlias = true
        paintText.textSize = 48f * (GameActivity.companionList.scaleScreen / 10)
        paintText.setShadowLayer(10f, 0f, 0f, Color.WHITE)

        swipeDown = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipedowntouse)
        swipeUp = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipeuptodel)
        fingerClick = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclick)
        one = BitmapFactory.decodeResource(context.resources, R.drawable.one)
        two = BitmapFactory.decodeResource(context.resources, R.drawable.two)
        three = BitmapFactory.decodeResource(context.resources, R.drawable.three)
        beat = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan1)
        the = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan2)
        highscore = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan3)

        fingerClickToSpawn = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktospawn)
        fingerClickToToggle = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle)
        fingerClickToToggle2 = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle2)
        fingerClickToToggle3 = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle3)

    }

    @InternalCoroutinesApi
    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (GameActivity.companionList.hintsBool) {
            var rect =
                Rect((talentRecyclerX).toInt(), (talentRecyclerY + (15 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentRecyclerX + (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentRecyclerY + (165 * (GameActivity.companionList.scaleScreen / 10))).toInt())
            canvas.drawBitmap(fingerClick!!, null, rect, null)
        }
    }
}