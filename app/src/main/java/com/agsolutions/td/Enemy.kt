package com.agsolutions.td

import android.graphics.*
import com.agsolutions.td.GameActivity.Companion.companionList
import java.io.Serializable


class Enemy(var hp: Float, var maxHp: Float, var manaShield: Float, var manaShieldMax: Float, var shield: Float, var shieldMax: Float, var armor: Float, var magicArmor: Float, var evade: Float, var hpReg: Float, var xpEnemy: Float, var speed: Float, var color: Int) :
    Serializable {

    var circle: TowerRadius? = null
    var circleXMovement = "xnull"
    var circleYMovement = "ynull"
    var point = 0
    var introBool = true
    var debuffExtraMgcDmg = false

    var reachedPortal = false
    var status = -1
    var dead = false
    var preDead = false
    var flag = 0
    var squaredDistance = 0f
    var selected = false
    var eliteMob = false
    var elementalMob = false
    var name = "normal"
    var pickEnemyID = 0
    var extraSpeed = 0f
    var baseSpeed = 0f
    var enemyPathBool = 0
    var killerId = -1
    var dmgTakenDebuff = 1f
    var xpDropDebuff = 1f
    var goldDropDebuff = 1f
    var overallXp = 0f
    var xpDrop = 0f
    var overallGold = 0f
    var antihealDebuff = 0
    var antihealDebuffActive = 0f
    var antihealDebuffTowerId = 0
    var hpRegDebuff = 0f
    var invu = false
    var invuTime = 0

    var random1 = 0
    var random2 = 0
    var random3 = 0
    var random4 = 0
    var random5 = 0
    var random6 = 0
    var random7 = 0
    var random8 = 0
    var random9 = 0
    var random10 = 0
    var random11 = 0
    var random12 = 0
    var randomAngle = 0f

    // talent fire
    var fireDebuff: Int = 0
    var fireDebuffDR = 1f
    var fireDebuffTowerId = 0

    // talent ice
    var iceDebuff: Int = 0
    var iceDebuffTowerId = 0
    var iceDebuffExtra = 0
    var iceDebuffExtraTowerId = 0
    var iceAlreadyAffected = 0
    var iceSlowEachSpeedReduce = 0f
    var iceSlowExtraSpeedReduce = 0f
    var iceExtraAlreadyAffected = 0
    var iceAuraAlreadyAffected = 0
    var iceAuraSpeedReduce = 0f
    var iceNovaSpeedReduce = 0f
    var iceNovaAlreadyAffected = false
    var iceNovaAlreadyAffectedCounter = 0
    var iceNovaDR = 1f
    var iceDebuffExtraDR = 1f
    var iceUltimateDR = 1f

    // talent wind
    var pushBackDR = 1f
    var winddebuffincreased = 1f

    // talent poison
    var poisonDebuff: Int = 0
    var poisonDebuffTowerId: Int = 0
    var poisonStack = 0.0f
    var entangled = false
    var entangleOnHit = 0
    var entangleOnHitTowerId = 0
    var entangleSpeedReduce = 0f
    var poisonTalentPestAlreadyAffected = 0
    var poisonTalentPestDamage = 0
    var poisonTalentPestImmune = false
    var entangleDR = 1f

    // talent earth
    var throwBoulderHit = 0
    var throwBoulderSpeedReduce = 0.0f
    var throwBoulderHitAlreadyEffected = false
    var throwBoulderDR = 1f

    // talent dark
    var darkSlowAlreadyAffected = false
    var darkDebuff = false
    var darkDebuffTowerId = 0
    var feared = false
    var fearOnHit = 0
    var fearSpeedReduce = 0f
    var fearDR = 1f
    var fearTowerId = 0
    var darkTalentLaserAlreadyAffected = 0

    var instaKilled = 1
    var instakillStacks = 0
    var darkTalentLaserTowerId =0
    var darkMoreDmgDebuff = 0
    var darkMoreDmgDebuffComplete = 0f
    var darkMoreDmgDebuffStacks = 0
    var darkMoreDmgDebuffTowerId = 0
    var darkSlowSpeedReduce = 0f

    // talent moon
    var talentMoonlightAlreadyAffected = 0f
    var talentMoonlightDraw = 0

    // talent wizard
    var wizardBombActive = 0
    var mineAlreadyAffected = false
    var mineSpeedReduce = 0f
    var mineAlreadyAffectedCounter = 0f
    var mineDR = 1f
    var wizardMissedLightningActiveHit = 0
    var wizardBombTowerId = 0

    // talent butterfly
    var markOfTheButterflyCounter = 0
    var markOfTheButterflySlowActive = 0
    var markOfTheButterflySlow = 0
    var markOfTheButterflyStun = 0
    var markOfTheButterflySlowAlreadyAffected = false
    var markOfTheButterflyStunAlreadyAffected = false
    var markOfTheButterflySlowReduce = 0f
    var markOfTheButterflySlowStunDR = 1f
    var butterflyDebuffEnemyDmg = 0
    var butterflyDebuffEnemyGoldXp = 0
    var butterflyDebuffEnemyDmgAlreadyEffected = false
    var butterflyDebuffEnemyGoldXpAlreadyEffected = false
    var butterflyDebuffEnemyDmgTowerId = 0
    var butterflyDebuffEnemyGoldXpTowerId = 0
    var markOfTheButterflySlowActiveTowerId = 0
    var markOfTheButterflyTowerId = 0
    var markOfTheButterflyTowerCounter = mutableListOf<Int>()

    var itemFrostAlreadyAffected = 0
    var itemFrostDR = 1f
    var itemFrostSpeedReduce = 0.0f
    var itemLassoAlreadyAffected = 0
    var itemLassoAlreadyAffectedTowerId = 0
    var itemLassoSpeedReduce = 0.0f
    var hit = false
    var explosionCounter = 0


    var towerDmgReceived = 0
    var burnDmgReceived = 0
    var poisonDmgReceived = 0
    var pestDmgReceived = 0

    var enemyRight = 0.0F
    var enemyRightManaShield = 0f
    var enemyRightShield = 0f
    var paint: Painter
    var paintSelected: Painter
    var paintBlack: Painter
    var paint2: Painter
    var paint3: Painter
    var paintFire: Painter
    var paintIce: Painter
    var paintPoison: Painter
    var paintDark: Painter
    var paintMoon: Painter
    var paintPoisonDark: Painter
    var paintButterfly: Painter
    var paintBomb: Painter
    var paintLightning: Painter
    var paintBoulder: Painter
    var path = mutableListOf<Triple<Int, Int, Int>>()


    init {
        circle = TowerRadius(950.0f, 1199.0f, 30.0f)

        paint = Painter()
        paintSelected = Painter()
        paintBlack = Painter()
        paint2 = Painter()
        paint3 = Painter()
        paintFire = Painter()
        paintIce = Painter()
        paintPoison = Painter()
        paintDark = Painter()
        paintMoon = Painter()
        paintPoisonDark = Painter()
        paintButterfly = Painter()
        paintBomb = Painter()
        paintLightning = Painter()
        paintBoulder = Painter()
        paint.isAntiAlias
        paint.isFilterBitmap
        paint.color = color
        paint2.color = Color.BLACK
        paint3.color = Color.GREEN
        paintFire.color = Color.parseColor("#F6546A")
        paintIce.color = Color.parseColor("#1429cc")
        paintPoison.color = Color.parseColor("#ADEAAD")
        paintPoisonDark.color = Color.parseColor("#3C8227")
        paintDark.color = Color.parseColor("#591eb6")
        paintMoon.color = Color.parseColor("#0C1593")
        paintBlack.color = Color.BLACK
        paintButterfly.color = Color.parseColor("#FF8100")
        paintBomb.color = Color.parseColor("#FFEC9E")
        paintLightning.color = Color.parseColor("#B7A971")
        paintBoulder.color = Color.parseColor("#55253A")
        paintSelected.color = Color.RED
    }

    fun draw(canvas: Canvas) {
        if (enemyPathBool == 0) randomizer ()
        paintShader ()
        when (name) {
            "boss" -> {
                if (selected && companionList.enemyClick) canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y).toFloat(), (((circle!!.r - 8) * 1.5) + 4).toFloat(), paintSelected)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), (((circle!!.r - 8) * 1.5) + 2).toFloat(), paintBlack)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), ((circle!!.r - 8) * 1.5).toFloat(), paint)
            }
            "splitter" -> {
                if (selected && companionList.enemyClick) canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y).toFloat(), (((circle!!.r - 8) * 0.5) + 4).toFloat(), paintSelected)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), (((circle!!.r - 8) * 0.5) + 2).toFloat(), paintBlack)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), ((circle!!.r - 8) * 0.5).toFloat(), paint)
            }
            "healer" -> {
                if (selected && companionList.enemyClick) canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y).toFloat(), (((circle!!.r - 8) * 0.9) + 4).toFloat(), paintSelected)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), (((circle!!.r - 8) * 0.9) + 2).toFloat(), paintBlack)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), ((circle!!.r - 8) * 0.9).toFloat(), paint)
            }
            "tank" -> {
                if (selected && companionList.enemyClick) canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y).toFloat(), (((circle!!.r - 8) * 1.1) + 4).toFloat(), paintSelected)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), (((circle!!.r - 8) * 1.1) + 2).toFloat(), paintBlack)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), ((circle!!.r - 8) * 1.1).toFloat(), paint)
            }
            "challenge" -> {
                if (selected && companionList.enemyClick) canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y).toFloat(), (((circle!!.r - 8) * 2.0) + 4).toFloat(), paintSelected)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), (((circle!!.r - 8) * 2.0) + 2).toFloat(), paintBlack)
                canvas.drawCircle(circle!!.x.toFloat(), circle!!.y.toFloat(), ((circle!!.r - 8) * 2.0).toFloat(), paint)
            }
            else -> {
                    if (selected && companionList.enemyClick) drawPolygonSelected(canvas, circle!!.x, circle!!.y, circle!!.r, paintSelected)
                    drawPolygonOutline(canvas, circle!!.x, circle!!.y, circle!!.r, paintBlack)
                    drawPolygonBase(canvas, circle!!.x, circle!!.y, circle!!.r, paint)
            }
        }
    }

    fun randomizer (){
        random1 = (10..25).random()
        random2 = (10..25).random()
        random3 = (10..25).random()
        random4 = (10..25).random()
        random5 = (10..25).random()
        random6 = (10..25).random()
        random7 = (10..25).random()
        random8 = (10..25).random()
        random9 = (10..25).random()
        random10 = (10..25).random()
        random11 = (0..45).random()
        random12 = (10..25).random()
        enemyPathBool = (1..3).random()
        randomAngle = (0..360).random().toFloat()

    }

    private fun drawPolygonBase(mCanvas: Canvas, x: Float, y: Float, r: Float, paint: Paint) {
        if (eliteMob) {
            val a: Float = (Math.PI.toFloat() * 2f) / 6f
            mCanvas.save()

            val path = Path()
            path.moveTo(x + (r -3).toFloat(), y + 0f)
            var i = 1
            while (i < 6) {
                path.lineTo(x + (r -3) * Math.cos((a * i).toDouble())
                    .toFloat(), y +(r - 3) * Math.sin((a * i).toDouble())
                    .toFloat())
                i++
            }
            path.close()
            mCanvas.drawPath(path, paint)
            mCanvas.restore()

        } else if (elementalMob) {
            val a: Float = (Math.PI.toFloat() * 2f) / 12f
            mCanvas.save()

            val path = Path()
            path.moveTo(x + (r -3).toFloat(), y + 0f)
            var i = 1
            while (i < 12) {
                path.lineTo(x + (r -3) * Math.cos((a * i).toDouble())
                    .toFloat(), y +(r - 3) * Math.sin((a * i).toDouble())
                    .toFloat())
                i++
            }
            path.close()
            mCanvas.drawPath(path, paint)
            mCanvas.restore()

        } else {
            when (enemyPathBool) {
                1 -> {
                    val path = Path()
                    var b = x + random1
                    var c = y + random8
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - random2, y + random3)
                    path.lineTo(x - random4, y - random5)
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()

                }
                2 -> {
                    val path = Path()
                    var b = x + random1
                    var c = y + random8
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - random2, y + random3)
                    path.lineTo(x - random4, y - random5)
                    path.lineTo(x + random6, y - random7)
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
                3 -> {
                    val path = Path()
                    var b = x + random1
                    var c = y + random8
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x, y + random11)
                    path.lineTo(x - random2, y + random3)
                    path.lineTo(x - random4, y - random5)
                    path.lineTo(x + random6, y - random7)
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
            }
        }
    }
    private fun drawPolygonOutline(mCanvas: Canvas, x: Float, y: Float,  r: Float, paint: Paint) {
        if (eliteMob) {
            val a: Float = Math.PI.toFloat() * 2 / 6
            mCanvas.save()
            mCanvas.translate(x, y)
            val path = Path()
            path.moveTo((r -2).toFloat(), 0f)
            var i = 1
            while (i < 6) {
                path.lineTo((r -2) * Math.cos((a * i).toDouble())
                    .toFloat(), (r -2) * Math.sin((a * i).toDouble())
                    .toFloat())
                i++
            }
            path.close()
            mCanvas.drawPath(path, paint)
            mCanvas.restore()
        } else if (elementalMob) {
            val a: Float = Math.PI.toFloat() * 2 / 12
            mCanvas.save()
            mCanvas.translate(x, y)
            val path = Path()
            path.moveTo((r -2).toFloat(), 0f)
            var i = 1
            while (i < 12) {
                path.lineTo((r -2) * Math.cos((a * i).toDouble())
                    .toFloat(), (r -2) * Math.sin((a * i).toDouble())
                    .toFloat())
                i++
            }
            path.close()
            mCanvas.drawPath(path, paint)
            mCanvas.restore()
        } else {
            when (enemyPathBool) {
                1 -> {

                    val path = Path()
                    var b = x + (random1 + 1)
                    var c = y + (random8 + 1)
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - (random2 + 1), y + (random3 + 1))
                    path.lineTo(x - (random4 + 1), y - (random5 + 1))
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
                2 -> {
                    val path = Path()
                    var b = x + (random1 + 1)
                    var c = y + (random8 + 1)
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - (random2 + 1), y + (random3 + 1))
                    path.lineTo(x - (random4 + 1), y - (random5 + 1))
                    path.lineTo(x + (random6 + 1), y - (random7 + 1))
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
                3 -> {
                    val path = Path()
                    var b = x + (random1 + 1)
                    var c = y + (random8 + 1)
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x, y + (random11 + 1))
                    path.lineTo(x - (random2 + 1), y + (random3 + 1))
                    path.lineTo(x - (random4 + 1), y - (random5 + 1))
                    path.lineTo(x + (random6 + 1), y - (random7 + 1))
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
            }
        }
    }
    private fun drawPolygonSelected(mCanvas: Canvas, x: Float, y: Float,  r: Float, paint: Paint) {
        if (eliteMob) {
            val a: Float = Math.PI.toFloat() * 2 / 6
            mCanvas.save()
            mCanvas.translate(x, y)
            val path = Path()
            path.moveTo((r + 0).toFloat(), 0f)
            var i = 1
            while (i < 6) {
                path.lineTo((r + 0) * Math.cos((a * i).toDouble())
                    .toFloat(), (r + 0) * Math.sin((a * i).toDouble())
                    .toFloat())
                i++
            }
            path.close()
            mCanvas.drawPath(path, paint)
            mCanvas.restore()
        } else if (elementalMob) {
            val a: Float = Math.PI.toFloat() * 2 / 12
            mCanvas.save()
            mCanvas.translate(x, y)
            val path = Path()
            path.moveTo((r + 0).toFloat(), 0f)
            var i = 1
            while (i < 12) {
                path.lineTo((r + 0) * Math.cos((a * i).toDouble())
                    .toFloat(), (r + 0) * Math.sin((a * i).toDouble())
                    .toFloat())
                i++
            }
            path.close()
            mCanvas.drawPath(path, paint)
            mCanvas.restore()
        } else {
            when (enemyPathBool) {
                1 -> {

                    val path = Path()
                    var b = x + (random1 + 3)
                    var c = y + (random8 + 3)
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - (random2 + 3), y + (random3 + 3))
                    path.lineTo(x - (random4 + 3), y - (random5 + 3))
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
                2 -> {
                    val path = Path()
                    var b = x + (random1 + 3)
                    var c = y + (random8 + 3)
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - (random2 + 3), y + (random3 + 3))
                    path.lineTo(x - (random4 + 3), y - (random5 + 3))
                    path.lineTo(x + (random6 + 3), y - (random7 + 3))
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
                3 -> {
                    val path = Path()
                    var b = x + (random1 + 3)
                    var c = y + (random8 + 3)
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x, y + (random11 + 3))
                    path.lineTo(x - (random2 + 3), y + (random3 + 3))
                    path.lineTo(x - (random4 + 3), y - (random5 + 3))
                    path.lineTo(x + (random6 + 3), y - (random7 + 3))
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
            }
        }
    }

    fun paintShader () {
        if (!dead) {
            var gradient: LinearGradient? = null
            var gradient2: RadialGradient? = null
            if (invu){
                gradient2 = RadialGradient(circle!!.x , circle!!.y, circle!!.r, Color.YELLOW, Color.WHITE, Shader.TileMode.MIRROR)
            }
            else if (companionList.day) {
                when (circleXMovement){
                    "xplus" -> gradient =
                        LinearGradient(circle!!.x - circle!!.r, 0f, circle!!.x + circle!!.r, 0f, Color.LTGRAY, color, Shader.TileMode.CLAMP)
                    "xminus" -> gradient =
                        LinearGradient(circle!!.x - circle!!.r, 0f, circle!!.x + circle!!.r, 0f, color, Color.LTGRAY, Shader.TileMode.CLAMP)
                    "xnull" -> {
                        when (circleYMovement) {
                            "yplus" -> gradient =
                                LinearGradient(0f, circle!!.y - circle!!.r, 0f, circle!!.y + circle!!.r, color, Color.LTGRAY, Shader.TileMode.CLAMP)
                            "yminus" -> gradient =
                                LinearGradient(0f, circle!!.y - circle!!.r, 0f, circle!!.y + circle!!.r, Color.LTGRAY, color, Shader.TileMode.CLAMP)
                            "ynull" -> gradient =
                                LinearGradient(0f, circle!!.y - circle!!.r, 0f, circle!!.y + circle!!.r, Color.LTGRAY, color, Shader.TileMode.CLAMP)
                        }
                    }
                }
            } else {
                when (circleXMovement){
                    "xplus" -> gradient =
                        LinearGradient(circle!!.x - circle!!.r, 0f, circle!!.x + circle!!.r, 0f, Color.DKGRAY, color, Shader.TileMode.CLAMP)
                    "xminus" -> gradient =
                        LinearGradient(circle!!.x - circle!!.r, 0f, circle!!.x + circle!!.r, 0f, color, Color.DKGRAY, Shader.TileMode.CLAMP)
                    "xnull" -> {
                        when (circleYMovement) {
                            "yplus" -> gradient =
                                LinearGradient(0f, circle!!.y - circle!!.r, 0f, circle!!.y + circle!!.r, color, Color.DKGRAY, Shader.TileMode.CLAMP)
                            "yminus" -> gradient =
                                LinearGradient(0f, circle!!.y - circle!!.r, 0f, circle!!.y + circle!!.r, Color.DKGRAY, color, Shader.TileMode.CLAMP)
                            "ynull" -> gradient =
                                LinearGradient(0f, circle!!.y - circle!!.r, 0f, circle!!.y + circle!!.r, Color.DKGRAY, color, Shader.TileMode.CLAMP)
                        }
                    }
                }
            }
            var gradientMatrix = Matrix()
            if (eliteMob || elementalMob) gradientMatrix!!.preRotate((0f), circle!!.x, circle!!.y)
            else gradientMatrix!!.preRotate((360 - randomAngle), circle!!.x, circle!!.y)
            if (invu){
                gradient2!!.setLocalMatrix(gradientMatrix)
                paint.shader = gradient2!!
            }else {
                gradient!!.setLocalMatrix(gradientMatrix)
                paint.shader = gradient!!
            }
        }
    }

    fun drawBurn(canvas: Canvas) {
        canvas.drawCircle((circle!!.x - 20).toFloat(), (circle!!.y + 35).toFloat(), 9.toFloat(), paintFire)
    }

    fun drawIceSlow(canvas: Canvas) {
        canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y + 35).toFloat(), 9.toFloat(), paintIce)
    }

    fun drawPoisonDebuff(canvas: Canvas) {
        canvas.drawCircle((circle!!.x + 20).toFloat(), (circle!!.y + 35).toFloat(), 9.toFloat(), paintPoison)
    }

    fun drawPoisonTalentPest(canvas: Canvas) {
        canvas.drawCircle((circle!!.x + 20).toFloat(), (circle!!.y + 55).toFloat(), 9.toFloat(), paintPoisonDark)
    }

    fun draw5(canvas: Canvas) {
        canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y + 55).toFloat(), 9.toFloat(), paintDark)
    }

    fun drawEntangle(canvas: Canvas) {
        canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y).toFloat(), 9.toFloat(), paintPoison)
    }

    fun drawFear(canvas: Canvas) {
        canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y).toFloat(), 9.toFloat(), paintDark)
    }

    fun drawBoulder(canvas: Canvas) {
        canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y).toFloat(), 9.toFloat(), paintBoulder)
    }


    fun drawButterfly(canvas: Canvas) {
        paintButterfly.color = companionList.towerList[markOfTheButterflyTowerId].markofTheBitterflyColor
        if (markOfTheButterflyCounter == 1) canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y - 50).toFloat(), 9.toFloat(), paintButterfly)
        else if (markOfTheButterflyCounter == 2) canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y - 50).toFloat(), 15.toFloat(), paintButterfly)
        else if (markOfTheButterflyCounter >= 3) canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y - 50).toFloat(), 21.toFloat(), paintButterfly)
    }

    fun drawMoonlight(canvas: Canvas) {
        canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y - 50).toFloat(), 9.toFloat(), paintMoon)
    }

    fun drawBomb(canvas: Canvas) {
        canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y - 50).toFloat(), 9.toFloat(), paintBomb)
    }
    fun drawLightning(canvas: Canvas) {
        canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y - 50).toFloat(), 15.toFloat(), paintLightning)
    }
    fun drawMine(canvas: Canvas) {
        canvas.drawCircle((circle!!.x - 20).toFloat(), (circle!!.y + 55).toFloat(), 9.toFloat(), paintBomb)
    }
    fun drawButterflySlow(canvas: Canvas) {
        canvas.drawCircle((circle!!.x).toFloat(), (circle!!.y + 55).toFloat(), 9.toFloat(), paintBomb)
    }


    fun update() {

        if (introBool) {
            introBool = false

            path.add(Triple(950, 1000, 1))
            path.add(Triple(400, 1000, 2))
            if (name == "shortcut") path.add(Triple(400, 500, 3))
            else if (GameActivity.companionList.levelList.contains("shortcut")) {
                when ((0..3).random()) {
                    0 -> path.add(Triple(400, 500, 3))
                    in 1..3 -> {
                        path.add(Triple(200, 1000, 2))
                        path.add(Triple(200, 500, 3))
                        path.add(Triple(400, 500, 4))
                    }
                }
            } else {
                path.add(Triple(200, 1000, 2))
                path.add(Triple(200, 500, 3))
                path.add(Triple(400, 500, 4))
            }
            path.add(Triple(800, 500, 4))
            if (companionList.mapMode == 2) path.add(Triple(800, 1000, 5))
            else path.add(Triple(800, 1200, 5))
        }

        if (point >= path.size) {
            reachedPortal = true
        }else {
            if (circle!!.x.toInt() == path[point].first) {
                circleXMovement = "xnull"
            }else if (circle!!.x.toInt() < path[point].first) {
                circleXMovement = "xplus"
                if (circle!!.x + (speed) > path[point].first) circle!!.x = path[point].first.toFloat()
                else circle!!.x += (speed)
            } else {
                circleXMovement = "xminus"
                if (circle!!.x - (speed) < path[point].first) circle!!.x = path[point].first.toFloat()
                else circle!!.x -= (speed)
            }

            if (circle!!.y.toInt() == path[point].second) {
                circleYMovement = "ynull"
            }else if (circle!!.y.toInt() < path[point].second) {
                circleYMovement = "yplus"
                if (circle!!.y + (speed) > path[point].second) circle!!.y = path[point].second.toFloat()
                else circle!!.y += (speed)
            } else {
                circleYMovement = "yminus"
                if (circle!!.y - (speed) < path[point].second) circle!!.y = path[point].second.toFloat()
                else circle!!.y -= (speed)
            }
            if (circle!!.x.toInt() == path[point].first && circle!!.y.toInt() == path[point].second){
                if (circle!!.x == 800f && circle!!.y == 1000f) point = 1
                else point++
            }
        }

        enemyRight = if (hp > 0){
            (((maxHp * 70.0) - (hp * 70.0)) / maxHp).toFloat()
        }
        else {
            (((maxHp * 70.0) - (1 * 70.0)) / maxHp).toFloat()
        }
        enemyRightManaShield =
            (((manaShieldMax * 70.0) - (manaShield * 70.0)) / manaShieldMax).toFloat()
        enemyRightShield = (((shieldMax * 70.0) - (shield * 70.0)) / shieldMax).toFloat()

    }
}
