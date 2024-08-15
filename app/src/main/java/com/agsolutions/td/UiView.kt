package com.agsolutions.td

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class UiView(context: Context, attributes: AttributeSet) : View(context, attributes) {

    companion object {
        var xRecycler = 0f
        var yRecycler = 0f
        var xRecyclerTower = 0f
        var yRecyclerTower = 0f
        var xRecyclerBag = 0f
        var yRecyclerBag = 0f
        var xTalents = 0f
        var yTalents = 0f
        var xLevelCount = (Resources.getSystem().displayMetrics.widthPixels / 2).toFloat()
        var yLevelCount = (Resources.getSystem().displayMetrics.heightPixels / 2).toFloat()
    }

    var firstBootInit = true
    var paintText = Paint ()
    var paintBlocked = Paint ()
    var paintBlocked2 = Paint ()
    var paintBlockedTower = Paint ()
    var paintBlockedTower2 = Paint ()
    var swipeDown: Bitmap? = null
    var fingerClick: Bitmap? = null
    var fingerClickDragDrop: Bitmap? = null
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

    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        if (firstBootInit) firstBootInit()

        canvas.translate(GameView.focusCanvasX * (GameView.focusVar) * -1, GameView.focusCanvasY * (GameView.focusVar) * -1)

        paintText.textSize = 48f * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)

        if (GameActivity.companionList.focusTalentWindow) {

        } else if (GameActivity.companionList.focusMainWindow) {

            if (GameActivity.companionList.dragStatusDrag) {
                var xLvl = (Resources.getSystem().getDisplayMetrics().widthPixels / 2).toFloat()
                var yLvl = (Resources.getSystem().getDisplayMetrics().heightPixels / 2).toFloat()
                if (GameActivity.companionList.dragStatusCantBuild){
                    paintText.textSize = 80f * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)
                    paintText.color = Color.parseColor("#EB1900")
                    var xLevelCountC = xLvl - (paintText.measureText("Can´t Build") / 2)
                    var yLevelCountC =
                        ((380 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)))
                    canvas.drawText("Can´t Build", xLevelCountC, yLevelCountC, paintText)
                }else {
                    paintText.textSize = 80f * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)
                    paintText.color = Color.parseColor("#009E11")
                    var xLevelCountA = xLvl - (paintText.measureText("Build Okay") / 2)
                    var yLevelCountA =
                        ((380 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)))
                    canvas.drawText("Build Okay ", xLevelCountA, yLevelCountA, paintText)
                }
                GameView.dragRectList.forEach {
                    var height = 0 // GameView.towerBase!!.height
                    var rectPlace = Rect(((it.left - height) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((it.top - height) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((it.right - height) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((it.bottom - height)* ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt())
                    canvas.drawRect(rectPlace, paintBlocked)
                    canvas.drawRect(rectPlace, paintBlocked2)
                }
                GameActivity.companionList.towerList.forEach(){
                    var rectPlace = Rect(((it.towerRange.x -100) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((it.towerRange.y - 100) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((it.towerRange.x + 100) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((it.towerRange.y + 100)* ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt())
                    if (it.canBuildEach){
                        paintBlockedTower.color = Color.parseColor("#8086BD09") // green
                        paintBlockedTower.style = Paint.Style.FILL
                        paintBlockedTower2.color = Color.BLACK
                        paintBlockedTower2.style = Paint.Style.STROKE
                    } else if (it.canBuild) {
                        paintBlockedTower.color = Color.parseColor("#80FFEB42") // yellow
                        paintBlockedTower.style = Paint.Style.FILL
                        paintBlockedTower2.color = Color.BLACK
                        paintBlockedTower2.style = Paint.Style.STROKE
                    } else {
                        paintBlockedTower.color = Color.parseColor("#80AA2E25") // red
                        paintBlockedTower.style = Paint.Style.FILL
                        paintBlockedTower2.color = Color.BLACK
                        paintBlockedTower2.style = Paint.Style.STROKE
                    }
                    canvas.drawRect(rectPlace, paintBlockedTower)
                    canvas.drawRect(rectPlace, paintBlockedTower2)
                }
            }

            if (GameActivity.companionList.towerList.isEmpty() && xRecyclerTower != 0f && GameActivity.companionList.hintsBool) {
                if (GameActivity.companionList.buildListBag.isEmpty()) {
                    var rect =
                        Rect(xRecyclerTower.toInt(), yRecyclerTower.toInt(), (xRecyclerTower + (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yRecyclerTower + (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                    canvas.drawBitmap(swipeDown!!, null, rect, null)
                } else {
                    var rect =
                        Rect(xRecyclerTower.toInt(), yRecyclerTower.toInt(), (xRecyclerTower + (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yRecyclerTower + (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                    canvas.drawBitmap(fingerClickDragDrop!!, null, rect, null)
                }
            }

            if (GameActivity.companionList.towerClick && GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.size - GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElementCount < 1 && GameActivity.companionList.itemList.isNotEmpty() && GameActivity.companionList.hintsBool) {
                var rect =
                    Rect(xRecycler.toInt(), yRecycler.toInt(), (xRecycler + (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yRecycler + (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(swipeDown!!, null, rect, null)
            }
            if (GameActivity.companionList.towerClick && GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].talentPoints > 1 && GameActivity.companionList.hintsBool) {
                var rect =
                    Rect((xTalents).toInt(), (yTalents - (15 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xTalents + (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yTalents + (135 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(fingerClick!!, null, rect, null)
            }
            if (GameActivity.companionList.towerClick && GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.size - GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElementCount >= 1 && GameActivity.companionList.hintsBool && GameActivity.companionList.tutorialFirstUseItemBool) {
                var rect =
                    Rect((xRecyclerBag + (20 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yRecyclerBag - (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xRecyclerBag + (320 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yRecyclerBag + (50 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(swipeUp!!, null, rect, null)
            }

            if (GameActivity.companionList.hintsBool && GameActivity.companionList.level != 0 && GameActivity.companionList.spawnBool && GameActivity.companionList.mapMode == 1){
                var spawnRect =
                    Rect(((950 - 225) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((1200 - 40) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((950 + 75) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((1200 + 110) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt())
                canvas.drawBitmap(fingerClickToToggle2!!, null, spawnRect, null)
            }

            if (GameActivity.companionList.enemyList.isEmpty() && GameActivity.companionList.level != 0 && GameActivity.companionList.mapMode == 1 && !GameActivity.companionList.autoSpawn && GameActivity.companionList.hintsBool){
                var spawnRect =
                    Rect(((950 - 225) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((1200 - 40) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((950 + 75) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), ((1200 + 110) * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt())
                canvas.drawBitmap(fingerClickToSpawn!!, null, spawnRect, null)
            }

            if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 1) {

            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 60) {
                var rect =
                    Rect((xLevelCount - (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (400 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xLevelCount + (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(one!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 120) {
                var rect =
                    Rect((xLevelCount - (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (400 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xLevelCount + (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(two!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 180) {
                var rect =
                    Rect((xLevelCount - (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (400 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xLevelCount + (150 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(three!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 300) {
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 340) {
                var rect1 =
                    Rect((xLevelCount - (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (600 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xLevelCount + (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(beat!!, null, rect1, null)
                var rect2 =
                    Rect((xLevelCount - (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (400 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xLevelCount + (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(the!!, null, rect2, null)
                var rect3 =
                    Rect((xLevelCount - (450 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (200 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xLevelCount + (450 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount + (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(highscore!!, null, rect3, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 380) {
                var rect1 =
                    Rect((xLevelCount - (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (600 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xLevelCount + (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(beat!!, null, rect1, null)
                var rect2 =
                    Rect((xLevelCount - (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (400 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xLevelCount + (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(the!!, null, rect2, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            } else if (GameActivity.companionList.level == 0 && GameActivity.companionList.levelCount >= GameActivity.companionList.levelCountPlace - 420) {
                var rect =
                    Rect((xLevelCount - (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (600 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (xLevelCount + (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt(), (yLevelCount - (300 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toInt())
                canvas.drawBitmap(beat!!, null, rect, null)
                paintText.color = Color.parseColor("#FFD700")
                var xLevelCountA = xLevelCount - (paintText.measureText("Global") / 2)
                var yLevelCountA =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (100 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Global ", xLevelCountA, yLevelCountA, paintText)
                var xLevelCountB =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.highscore.toString()) / 2)
                var yLevelCountB =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (175 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.highscore.toString(), xLevelCountB, yLevelCountB, paintText)
                paintText.color = Color.parseColor("#C0C0C0")
                var xLevelCountC = xLevelCount - (paintText.measureText("Personal ") / 2)
                var yLevelCountC =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (250 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText("Personal", xLevelCountC, yLevelCountC, paintText)
                var xLevelCountD =
                    xLevelCount - (paintText.measureText(GameActivity.companionList.personalHighscore.toString()) / 2)
                var yLevelCountD =
                    (yLevelCount - (paintText.descent() + paintText.ascent()) / 2) + (325 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))
                canvas.drawText(GameActivity.companionList.personalHighscore.toString(), xLevelCountD, yLevelCountD, paintText)
            }
        }
    }

    fun firstBootInit() {
        firstBootInit = false

                    paintBlocked.color = Color.parseColor("#80AA2E25")
                    paintBlocked.style = Paint.Style.FILL
                    paintBlocked2.color = Color.BLACK
                    paintBlocked2.style = Paint.Style.STROKE
                    paintText.color = Color.BLUE
                    paintText.style = Paint.Style.FILL_AND_STROKE
                    paintText.isAntiAlias = true
                    paintText.textSize =
                        48f * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)
                    paintText.setShadowLayer(10f, 0f, 0f, Color.WHITE)

                    swipeDown =
                        BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipedowntouse)
                    swipeUp =
                        BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipeuptodel)
                    fingerClick =
                        BitmapFactory.decodeResource(context.resources, R.drawable.fingerclick)
                    fingerClickDragDrop =
                        BitmapFactory.decodeResource(context.resources, R.drawable.fingerdragdrop)
                    one = BitmapFactory.decodeResource(context.resources, R.drawable.one)
                    two = BitmapFactory.decodeResource(context.resources, R.drawable.two)
                    three = BitmapFactory.decodeResource(context.resources, R.drawable.three)
                    beat = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan1)
                    the = BitmapFactory.decodeResource(context.resources, R.drawable.startslogan2)
                    highscore =
                        BitmapFactory.decodeResource(context.resources, R.drawable.startslogan3)

                    fingerClickToSpawn =
                        BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktospawn)
                    fingerClickToToggle =
                        BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle)
                    fingerClickToToggle2 =
                        BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle2)
                    fingerClickToToggle3 =
                        BitmapFactory.decodeResource(context.resources, R.drawable.fingerclicktotoggle3)

    }
}

/*
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

 */

class UiViewTalentWindow(context: Context, attributes: AttributeSet) : View(context, attributes) {
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
        paintText.textSize = 48f * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)
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

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (GameActivity.companionList.focusTalentFragment && GameActivity.companionList.hintsBool && GameActivity.companionList.showHelpTalent) {
            var rect =
                Rect((talentX).toInt(), (talentY + (15 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentX + (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentY + (165 * (GameActivity.companionList.scaleScreen / 10))).toInt())
            canvas.drawBitmap(fingerClick!!, null, rect, null)
        }
    }
}

class UiViewStartItems(context: Context, attributes: AttributeSet) : View(context, attributes) {
    companion object {
        var talentRecyclerX = 0f
        var talentRecyclerY = 0f
        var talentRecyclerElementX = 0f
        var talentRecyclerElementY = 0f
        var talentRecyclerPickX = 0f
        var talentRecyclerPickY = 0f
    }


    var paintText = Paint ()
    var swipeDown: Bitmap? = null
    var fingerClick: Bitmap? = null
    var fingerClickItem: Bitmap? = null
    var fingerClickElement: Bitmap? = null
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
        paintText.textSize = 48f * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor)
        paintText.setShadowLayer(10f, 0f, 0f, Color.WHITE)

        swipeDown = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipedowntouse)
        swipeUp = BitmapFactory.decodeResource(context.resources, R.drawable.fingerswipeuptodel)
        fingerClick = BitmapFactory.decodeResource(context.resources, R.drawable.fingerclick)
        fingerClickItem = BitmapFactory.decodeResource(context.resources, R.drawable.fingerpickitem)
        fingerClickElement = BitmapFactory.decodeResource(context.resources, R.drawable.fingerpickelement)
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

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (GameActivity.companionList.hintsBool && talentRecyclerX == 0f && talentRecyclerElementX == 0f) {
            var rect =
                Rect((talentRecyclerPickX).toInt(), (talentRecyclerPickY + (15 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentRecyclerPickX + (150 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentRecyclerPickY + (165 * (GameActivity.companionList.scaleScreen / 10))).toInt())
            canvas.drawBitmap(fingerClick!!, null, rect, null)
        }
        if (GameActivity.companionList.hintsBool && talentRecyclerX != 0f) {
            var rect =
                Rect((talentRecyclerX).toInt(), (talentRecyclerY + (15 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentRecyclerX + (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentRecyclerY + (165 * (GameActivity.companionList.scaleScreen / 10))).toInt())
            canvas.drawBitmap(fingerClickItem!!, null, rect, null)
        }
        if (GameActivity.companionList.hintsBool && talentRecyclerElementX != 0f) {
            var rect =
                Rect((talentRecyclerElementX).toInt(), (talentRecyclerElementY + (15 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentRecyclerElementX + (300 * (GameActivity.companionList.scaleScreen / 10))).toInt(), (talentRecyclerElementY + (165 * (GameActivity.companionList.scaleScreen / 10))).toInt())
            canvas.drawBitmap(fingerClickElement!!, null, rect, null)
        }
    }
}