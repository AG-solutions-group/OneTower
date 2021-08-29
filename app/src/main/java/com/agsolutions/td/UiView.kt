package com.agsolutions.td

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.agsolutions.td.Companion.Companion.dragStatusCantBuild
import com.agsolutions.td.Companion.Companion.focusMainWindow
import com.agsolutions.td.Companion.Companion.hintsBool
import com.agsolutions.td.Companion.Companion.scaleScreen
import com.agsolutions.td.Companion.Companion.showHelpTalent
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
        paintText.textSize = 48f * (com.agsolutions.td.Companion.scaleScreen / 10)
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

        paintText.textSize = 48f * (com.agsolutions.td.Companion.scaleScreen / 10)

        if (com.agsolutions.td.Companion.focusTalentWindow) {

        } else if (focusMainWindow) {

            if (com.agsolutions.td.Companion.globalItemListBag.isEmpty() && com.agsolutions.td.Companion.itemList.isNotEmpty() && com.agsolutions.td.Companion.hintsBool) {
                var rect =
                    Rect(xRecycler.toInt(), yRecycler.toInt(), (xRecycler + (300 * (scaleScreen / 10))).toInt(), (yRecycler + (150 * (scaleScreen / 10))).toInt())
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
            if (com.agsolutions.td.Companion.globalItemListBag.isNotEmpty() && com.agsolutions.td.Companion.hintsBool && com.agsolutions.td.Companion.tutorialFirstUseItemBool) {
                var rect =
                    Rect((xRecyclerBag + (20 * (scaleScreen / 10))).toInt(), (yRecyclerBag - (100 * (scaleScreen / 10))).toInt(), (xRecyclerBag + (320 * (scaleScreen / 10))).toInt(), (yRecyclerBag + (50 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(swipeUp!!, null, rect, null)
            }

            if (!com.agsolutions.td.Companion.autoSpawn && com.agsolutions.td.Companion.autoSpawnCount > 180 && com.agsolutions.td.Companion.hintsBool && com.agsolutions.td.Companion.enemyList.isEmpty()) {
                com.agsolutions.td.Companion.touchCountCounter++
                var spawnRect =
                    Rect(((950 - 225) * (scaleScreen / 10)).toInt(), ((1200 - 40) * (scaleScreen / 10)).toInt(), ((950 + 75) * (scaleScreen / 10)).toInt(), ((1200 + 110) * (scaleScreen / 10)).toInt())
                if (com.agsolutions.td.Companion.touchCountCounter > 160) com.agsolutions.td.Companion.touchCountCounter =
                    -10
                else if (com.agsolutions.td.Companion.touchCountCounter > 102) {
                    canvas.drawBitmap(fingerClickToToggle!!, null, spawnRect, null)
                } else if (com.agsolutions.td.Companion.touchCountCounter > 100) {
                    canvas.drawBitmap(fingerClickToToggle3!!, null, spawnRect, null)
                } else if (com.agsolutions.td.Companion.touchCountCounter > 85) {
                    canvas.drawBitmap(fingerClickToToggle2!!, null, spawnRect, null)
                } else if (com.agsolutions.td.Companion.touchCountCounter > 75) {
                } else if (com.agsolutions.td.Companion.touchCountCounter > 0) {
                    canvas.drawBitmap(fingerClickToSpawn!!, null, spawnRect, null)
                }
            }

            if (com.agsolutions.td.Companion.level == 0 && com.agsolutions.td.Companion.levelCount >= com.agsolutions.td.Companion.levelCountPlace - 1) {

            } else if (com.agsolutions.td.Companion.level == 0 && com.agsolutions.td.Companion.levelCount >= com.agsolutions.td.Companion.levelCountPlace - 60) {
                var rect =
                    Rect((xLevelCount - (150 * (scaleScreen / 10))).toInt(), (yLevelCount - (400 * (scaleScreen / 10))).toInt(), (xLevelCount + (150 * (scaleScreen / 10))).toInt(), (yLevelCount - (100 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(one!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (com.agsolutions.td.Companion.level == 0 && com.agsolutions.td.Companion.levelCount >= com.agsolutions.td.Companion.levelCountPlace - 120) {
                var rect =
                    Rect((xLevelCount - (150 * (scaleScreen / 10))).toInt(), (yLevelCount - (400 * (scaleScreen / 10))).toInt(), (xLevelCount + (150 * (scaleScreen / 10))).toInt(), (yLevelCount - (100 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(two!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (com.agsolutions.td.Companion.level == 0 && com.agsolutions.td.Companion.levelCount >= com.agsolutions.td.Companion.levelCountPlace - 180) {
                var rect =
                    Rect((xLevelCount - (150 * (scaleScreen / 10))).toInt(), (yLevelCount - (400 * (scaleScreen / 10))).toInt(), (xLevelCount + (150 * (scaleScreen / 10))).toInt(), (yLevelCount - (100 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(three!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (com.agsolutions.td.Companion.level == 0 && com.agsolutions.td.Companion.levelCount >= com.agsolutions.td.Companion.levelCountPlace - 300) {
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (com.agsolutions.td.Companion.level == 0 && com.agsolutions.td.Companion.levelCount >= com.agsolutions.td.Companion.levelCountPlace - 340) {
                var rect1 =
                    Rect((xLevelCount - (300 * (scaleScreen / 10))).toInt(), (yLevelCount - (600 * (scaleScreen / 10))).toInt(), (xLevelCount + (300 * (scaleScreen / 10))).toInt(), (yLevelCount - (300 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(beat!!, null, rect1, null)
                var rect2 =
                    Rect((xLevelCount - (300 * (scaleScreen / 10))).toInt(), (yLevelCount - (400 * (scaleScreen / 10))).toInt(), (xLevelCount + (300 * (scaleScreen / 10))).toInt(), (yLevelCount - (100 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(the!!, null, rect2, null)
                var rect3 =
                    Rect((xLevelCount - (450 * (scaleScreen / 10))).toInt(), (yLevelCount - (200 * (scaleScreen / 10))).toInt(), (xLevelCount + (450 * (scaleScreen / 10))).toInt(), (yLevelCount + (100 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(highscore!!, null, rect3, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (com.agsolutions.td.Companion.level == 0 && com.agsolutions.td.Companion.levelCount >= com.agsolutions.td.Companion.levelCountPlace - 380) {
                var rect1 =
                    Rect((xLevelCount - (300 * (scaleScreen / 10))).toInt(), (yLevelCount - (600 * (scaleScreen / 10))).toInt(), (xLevelCount + (300 * (scaleScreen / 10))).toInt(), (yLevelCount - (300 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(beat!!, null, rect1, null)
                var rect2 =
                    Rect((xLevelCount - (300 * (scaleScreen / 10))).toInt(), (yLevelCount - (400 * (scaleScreen / 10))).toInt(), (xLevelCount + (300 * (scaleScreen / 10))).toInt(), (yLevelCount - (100 * (scaleScreen / 10))).toInt())
                canvas.drawBitmap(the!!, null, rect2, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (com.agsolutions.td.Companion.level == 0 && com.agsolutions.td.Companion.levelCount >= com.agsolutions.td.Companion.levelCountPlace - 420) {
                var rect =
                    Rect((xLevelCount - (300 * (scaleScreen / 10))).toInt(), (yLevelCount - (600 * (scaleScreen / 10))).toInt(), (xLevelCount + (300 * (com.agsolutions.td.Companion.scaleScreen / 10))).toInt(), (yLevelCount - (300 * (com.agsolutions.td.Companion.scaleScreen / 10))).toInt())
                canvas.drawBitmap(beat!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * (scaleScreen / 10))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * (scaleScreen / 10))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(com.agsolutions.td.Companion.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * (scaleScreen / 10))
                canvas.drawText(com.agsolutions.td.Companion.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            }
            if (com.agsolutions.td.Companion.dragStatusDrag) {
                var xLvl = (Resources.getSystem().getDisplayMetrics().widthPixels / 2).toFloat()
                var yLvl = (Resources.getSystem().getDisplayMetrics().heightPixels / 2).toFloat()
                    if (dragStatusCantBuild){
                    paintText.textSize = 72f * (com.agsolutions.td.Companion.scaleScreen / 10)
                    paintText.color = Color.parseColor("#EB1900")
                    var xLevelCountC = xLvl - (paintText.measureText("Can´t Build") / 2)
                    var yLevelCountC =
                        (yLvl - (500 * (scaleScreen / 10)))
                    canvas.drawText("Can´t Build", xLevelCountC, yLevelCountC, paintText)
                }else {
                    paintText.textSize = 72f * (com.agsolutions.td.Companion.scaleScreen / 10)
                    paintText.color = Color.parseColor("#009E11")
                    var xLevelCountA = xLvl - (paintText.measureText("Build Okay") / 2)
                    var yLevelCountA =
                        (yLvl - (500 * (scaleScreen / 10)))
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
        paintText.textSize = 48f * (com.agsolutions.td.Companion.scaleScreen / 10)
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

        if (com.agsolutions.td.Companion.focusTalentWindow && hintsBool && showHelpTalent) {
            var rect =
                Rect((talentX).toInt(), (talentY + (15 * (scaleScreen / 10))).toInt(), (talentX + (150 * (scaleScreen / 10))).toInt(), (talentY + (165 * (scaleScreen / 10))).toInt())
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
        paintText.textSize = 48f * (com.agsolutions.td.Companion.scaleScreen / 10)
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

        if (com.agsolutions.td.Companion.focusEarthFragment && com.agsolutions.td.Companion.hintsBool && showHelpTalent) {
            var rect =
                Rect((talentEarthX).toInt(), (talentEarthY + (15 * (scaleScreen / 10))).toInt(), (talentEarthX + (150 * (scaleScreen / 10))).toInt(), (talentEarthY + (165 * (scaleScreen / 10))).toInt())
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
        paintText.textSize = 48f * (com.agsolutions.td.Companion.scaleScreen / 10)
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

        if (com.agsolutions.td.Companion.hintsBool) {
            var rect =
                Rect((talentRecyclerX).toInt(), (talentRecyclerY + (15 * (scaleScreen / 10))).toInt(), (talentRecyclerX + (150 * (scaleScreen / 10))).toInt(), (talentRecyclerY + (165 * (scaleScreen / 10))).toInt())
            canvas.drawBitmap(fingerClick!!, null, rect, null)
        }
    }
}