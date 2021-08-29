package com.agsolutions.td

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.agsolutions.td.Companion.Companion.advancedList1
import com.agsolutions.td.Companion.Companion.advancedList2
import com.agsolutions.td.Companion.Companion.advancedList3
import com.agsolutions.td.Companion.Companion.advancedList4
import com.agsolutions.td.Companion.Companion.enemyList
import com.agsolutions.td.Companion.Companion.gameSpeedAdjuster
import com.agsolutions.td.Companion.Companion.randomEnemyForSniper
import com.agsolutions.td.Companion.Companion.rotationEnemyX
import com.agsolutions.td.Companion.Companion.rotationEnemyY
import com.agsolutions.td.Companion.Companion.rotationTowerX
import com.agsolutions.td.Companion.Companion.rotationTowerY
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Main.MainActivity

class Shoot () {

    var bulletSpeed: Float = 10.0F
    var paint: Paint
    var bullet = TowerRadius(600.0f, 750.0f, 5.0f)
    var broken = 0
    var bounceLeft = 0
    var alreadyBounced = 0
    var alreadyBouncedReset = false
    var id = -1
    var sniper = false
    var butterflyUltimate = false
    var chainLightning = false
    var color = Color.YELLOW
    var multiShotBullet = false
    var butterflyPicCounter = 0
    var butterflyNextPic = 0
    var firstEnemyMulti: Enemy? = null
    var firstEnemyMultiBool = false
    var towerId = 0


    init {
        paint = Paint()
        paint.isAntiAlias
        paint.isFilterBitmap
        paint.color = color

    }

    fun draw(canvas: Canvas) {

    //    canvas.drawCircle(bullet.x.toFloat(), bullet.y.toFloat(), bullet.r.toFloat(), paint)


    }

    fun update() {

          com.agsolutions.td.Companion.readLockEnemy.lock()
          try {


          if (MainActivity.startScreenBool) {

          }
          else if (sniper && gameSpeedAdjuster == 2f ) bulletSpeed = 10f
          else if (sniper && gameSpeedAdjuster == 1.5f) bulletSpeed = 15f
          else if (sniper && gameSpeedAdjuster == 1f) bulletSpeed = 20f
          else if (towerList[towerId].towerPrimaryElement == "earth")  bulletSpeed = 6f
          else if (towerList[towerId].towerPrimaryElement == "butterfly")  bulletSpeed = 8f


            //   var crossesAllListIteratorX = crossesAllList.listIterator()
            //   while (crossesAllListIteratorX.hasNext()) {
            //       var enemy = crossesAllListIteratorX.next()

            if (sniper) {
                if (towerList[towerId].crossesNoneList.contains(randomEnemyForSniper)) movement(randomEnemyForSniper)
                else if (towerList[towerId].crossesNoneList.isNotEmpty()) {
                    randomEnemyForSniper = towerList[towerId].crossesNoneList.random()
                    movement(randomEnemyForSniper)
                }  // random
                else {
                    bullet.x = towerList[towerId].towerRange.x
                    bullet.y = towerList[towerId].towerRange.y
                    broken = 1
                }
            } else if (towerList[towerId].towerPrimaryElement == "wind" && multiShotBullet) {
                if (firstEnemyMultiBool && enemyList!!.contains(firstEnemyMulti)){
                    movementMulti(firstEnemyMulti!!)
                }
                else {
                    if (id >= towerList[towerId].crossesAllList.size) {
                        bullet.x = towerList[towerId].towerRange.x
                        bullet.y = towerList[towerId].towerRange.y
                        broken = 1
                    } else {
                        if (towerList[towerId].crossesAllList.isNotEmpty()) {
                            firstEnemyMulti = (towerList[towerId].crossesAllList[id])
                            movementMulti(firstEnemyMulti!!)
                            firstEnemyMultiBool = true
                        } else {
                            bullet.x = towerList[towerId].towerRange.x
                            bullet.y = towerList[towerId].towerRange.y
                            broken = 1
                        }
                    }
                }
            } else if (alreadyBounced > 0 && towerList[towerId].towerPrimaryElement == "moon") {
                if (id >= 0 && id < enemyList.size && alreadyBouncedReset) {
                    movement(enemyList[id])
                } else {
                    var squaredDistancePlaceholder = 185f * 185f
                    var squareDistanceList = mutableListOf<Enemy>()
                    var enemyListIterator = enemyList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var itX = enemyListIterator.next()

                        val distanceX = (bullet.x) - itX.circle!!.x
                        val distanceY = (bullet.y) - itX.circle!!.y

                        val squaredDistance =
                            (distanceX * distanceX) + (distanceY * distanceY)

                        val sumOfRadius = (bullet.r + 150) + (itX.circle!!.r)
                        val sumOfRadius2 = bullet.r + (itX.circle!!.r - 10.0)

                        if (squaredDistance <= sumOfRadius * sumOfRadius && squaredDistance >= sumOfRadius2 * sumOfRadius2) {

                            if (squaredDistance < squaredDistancePlaceholder) {
                                squareDistanceList.add(0, itX)
                                squaredDistancePlaceholder = squaredDistance
                            }
                        }
                    }

                    if (squareDistanceList.isNotEmpty()) {
                        if (squareDistanceList[0].circle!!.x > bullet.x){
                            bullet.x += 10
                        }
                        else if (squareDistanceList[0].circle!!.x < bullet.x){
                            bullet.x -= 10
                        }
                        if (squareDistanceList[0].circle!!.y > bullet.y){
                            bullet.y += 10
                        }
                        else if (squareDistanceList[0].circle!!.y < bullet.y){
                            bullet.y -= 10
                        }

                        movement(squareDistanceList[0])
                        id = enemyList.indexOf(squareDistanceList[0])
                        alreadyBouncedReset = true
                        squareDistanceList.removeAll(squareDistanceList)
                    } else broken = 1
                }
            } else {
                if (towerList[towerId].crossesAllList.isNotEmpty()) {
                    var tank = false
                    var crossesAllListIterator = towerList[towerId].crossesAllList.listIterator()
                    while (crossesAllListIterator.hasNext()) {
                        var it = crossesAllListIterator.next()
                        if (it.name == "tank") {
                            tank = true
                            movement(it)
                        }
                    }
                    if (tank == true) {
                    } else {
                            if (towerList[towerId].firstLastRandom == 3) {
                                if (towerList[towerId].randomEnemyForShotBool) {
                                    var use: Enemy? = null
                                    var place = 0f
                                    var place2 = 2000f

                                    var crossesAllListIteratorZ = towerList[towerId].crossesAllList.listIterator()
                                    while (crossesAllListIteratorZ.hasNext()) {
                                        var it = crossesAllListIteratorZ.next()
                                        when (it.passed) {
                                             4.toByte() -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID = enemyList.indexOf(it)
                                                advancedList4.add(x)
                                            }
                                            3.toByte() -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID = enemyList.indexOf(it)
                                                advancedList3.add(x)
                                            }
                                            2.toByte() -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID = enemyList.indexOf(it)
                                                advancedList2.add(x)
                                            }
                                            1.toByte() -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID = enemyList.indexOf(it)
                                                advancedList1.add(x)
                                            }
                                        }
                                    }
                                    if (advancedList4.isNotEmpty()) {
                                        for (it in advancedList4) {
                                            if (it.circle!!.y > place) {
                                                place = it.circle!!.y
                                                use = enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (advancedList3.isNotEmpty()) {
                                        for (it in advancedList3) {
                                            if (it.circle!!.x > place) {
                                                place = it.circle!!.x
                                                use = enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (advancedList2.isNotEmpty()) {
                                        for (it in advancedList2) {
                                            if (it.circle!!.y < place2) {
                                                place2 = it.circle!!.y
                                                use = enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (advancedList1.isNotEmpty()) {
                                        for (it in advancedList1) {
                                            if (it.circle!!.x < place2) {
                                                place2 = it.circle!!.x
                                                use = enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else {
                                    }
                                    if (use != null) {
                                        towerList[towerId].randomEnemyForShot = use!!
                                        movement(towerList[towerId].randomEnemyForShot)
                                        towerList[towerId].randomEnemyForShotBool = false
                                        advancedList4.removeAll(advancedList4)
                                        advancedList3.removeAll(advancedList3)
                                        advancedList2.removeAll(advancedList2)
                                        advancedList1.removeAll(advancedList1)
                                    } else {
                                    }
                                } else movement(towerList[towerId].randomEnemyForShot)
                            } else if (towerList[towerId].firstLastRandom == 1) {
                                if (towerList[towerId].randomEnemyForShotBool){
                                for (it in enemyList) {
                                    when {
                                        it.passed == 1.toByte() && towerList[towerId].crossesAllList.contains(it) -> {
                                            towerList[towerId].randomEnemyForShot = it
                                            movement(towerList[towerId].randomEnemyForShot)
                                            towerList[towerId].randomEnemyForShotBool = false
                                            break
                                        }
                                        it.passed == 2.toByte() && towerList[towerId].crossesAllList.contains(it) -> {
                                            towerList[towerId].randomEnemyForShot = it
                                            movement(towerList[towerId].randomEnemyForShot)
                                            towerList[towerId].randomEnemyForShotBool = false
                                            break
                                        }
                                        it.passed == 3.toByte() && towerList[towerId].crossesAllList.contains(it) -> {
                                            towerList[towerId].randomEnemyForShot = it
                                            movement(towerList[towerId].randomEnemyForShot)
                                            towerList[towerId].randomEnemyForShotBool = false
                                            break
                                        }
                                        it.passed == 4.toByte() && towerList[towerId].crossesAllList.contains(it) -> {
                                            towerList[towerId].randomEnemyForShot = it
                                            movement(towerList[towerId].randomEnemyForShot)
                                            towerList[towerId].randomEnemyForShotBool = false
                                            break
                                        }
                                    }
                                }
                                } else movement(towerList[towerId].randomEnemyForShot)
                            } else if (towerList[towerId].firstLastRandom == 0) {
                            if (towerList[towerId].randomEnemyForShotSticky) {
                                var use: Enemy? = null
                                var place = 0f
                                var place2 = 2000f

                                var crossesAllListIteratorZ = towerList[towerId].crossesAllList.listIterator()
                                while (crossesAllListIteratorZ.hasNext()) {
                                    var it = crossesAllListIteratorZ.next()
                                    when (it.passed) {
                                        4.toByte() -> {
                                            var x =
                                                Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                            x.circle!!.x = it.circle!!.x
                                            x.circle!!.y = it.circle!!.y
                                            x.pickEnemyID = enemyList.indexOf(it)
                                            advancedList4.add(x)
                                        }
                                        3.toByte() -> {
                                            var x =
                                                Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                            x.circle!!.x = it.circle!!.x
                                            x.circle!!.y = it.circle!!.y
                                            x.pickEnemyID = enemyList.indexOf(it)
                                            advancedList3.add(x)
                                        }
                                        2.toByte() -> {
                                            var x =
                                                Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                            x.circle!!.x = it.circle!!.x
                                            x.circle!!.y = it.circle!!.y
                                            x.pickEnemyID = enemyList.indexOf(it)
                                            advancedList2.add(x)
                                        }
                                        1.toByte() -> {
                                            var x =
                                                Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                            x.circle!!.x = it.circle!!.x
                                            x.circle!!.y = it.circle!!.y
                                            x.pickEnemyID = enemyList.indexOf(it)
                                            advancedList1.add(x)
                                        }
                                    }
                                }
                                if (advancedList4.isNotEmpty()) {
                                    for (it in advancedList4) {
                                        if (it.circle!!.y > place) {
                                            place = it.circle!!.y
                                            use = enemyList[it.pickEnemyID]
                                        }
                                    }
                                } else if (advancedList3.isNotEmpty()) {
                                    for (it in advancedList3) {
                                        if (it.circle!!.x > place) {
                                            place = it.circle!!.x
                                            use = enemyList[it.pickEnemyID]
                                        }
                                    }
                                } else if (advancedList2.isNotEmpty()) {
                                    for (it in advancedList2) {
                                        if (it.circle!!.y < place2) {
                                            place2 = it.circle!!.y
                                            use = enemyList[it.pickEnemyID]
                                        }
                                    }
                                } else if (advancedList1.isNotEmpty()) {
                                    for (it in advancedList1) {
                                        if (it.circle!!.x < place2) {
                                            place2 = it.circle!!.x
                                            use = enemyList[it.pickEnemyID]
                                        }
                                    }
                                } else {
                                }
                                if (use != null) {
                                    towerList[towerId].randomEnemyForShot = use!!
                                    movement(towerList[towerId].randomEnemyForShot)
                                    towerList[towerId].randomEnemyForShotSticky = false
                                    advancedList4.removeAll(advancedList4)
                                    advancedList3.removeAll(advancedList3)
                                    advancedList2.removeAll(advancedList2)
                                    advancedList1.removeAll(advancedList1)
                                } else {
                                }
                            } else movement(towerList[towerId].randomEnemyForShot)
                        } else {
                                if (towerList[towerId].randomEnemyForShotBool) {
                                    towerList[towerId].randomEnemyForShot = towerList[towerId].crossesAllList.random()
                                    movement(towerList[towerId].randomEnemyForShot)
                                    towerList[towerId].randomEnemyForShotBool = false
                                }  // random
                                else movement(towerList[towerId].randomEnemyForShot)
                            }
                        }
                } else {
                    if (enemyList.contains(towerList[towerId].randomEnemyForShot)) {
                        movement(towerList[towerId].randomEnemyForShot)
                    } else {
                        towerList[towerId].randomEnemyForShotBool = true
                        if (MainActivity.startScreenBool){
                            bullet.x = 200.0f
                            bullet.y = 750.0f
                        } else {
                            bullet.x = towerList[towerId].towerRange.x
                            bullet.y = towerList[towerId].towerRange.y
                        }
                            broken = 1
                    }
                }
            }
        } finally {
            com.agsolutions.td.Companion.readLockEnemy.unlock()
        }
    }


        fun movementMulti(enemy: Enemy) {

                    fun xSpeed(): Int {
                        var speed = 0
                        when (enemy.passed) {
                            0.toByte() -> speed = 0
                            1.toByte() -> speed = (enemy.speed.toInt()) * (-1)
                            2.toByte() -> speed = 0
                            3.toByte() -> speed = (enemy.speed.toInt())
                            4.toByte() -> speed = 0
                        }
                        return speed
                    }

                    fun ySpeed(): Int {
                        var speed = 0
                        when (enemy.passed) {
                            0.toByte() -> speed = (enemy.speed.toInt()) * (-1)
                            1.toByte() -> speed = 0
                            2.toByte() -> speed = (enemy.speed.toInt()) * (-1)
                            3.toByte() -> speed = 0
                            4.toByte() -> speed = (enemy.speed.toInt())
                        }
                        return speed
                    }

                    val nx =
                        if (bullet.x > (enemy.circle!!.x + xSpeed())) ((bullet.x - (enemy.circle!!.x + xSpeed().toFloat())) / (bulletSpeed * Companion.gameSpeedAdjuster))
                        else (((enemy.circle!!.x + xSpeed().toFloat()) - bullet.x) / (bulletSpeed * Companion.gameSpeedAdjuster))
                    val ny =
                        if (bullet.y > (enemy.circle!!.y + xSpeed())) ((bullet.y - (enemy.circle!!.y + ySpeed().toFloat())) / (bulletSpeed * Companion.gameSpeedAdjuster))
                        else (((enemy.circle!!.y + ySpeed().toFloat()) - bullet.y) / (bulletSpeed * Companion.gameSpeedAdjuster))

                    val n =
                        if (nx > ny) (bulletSpeed * Companion.gameSpeedAdjuster) / nx
                        else (bulletSpeed * Companion.gameSpeedAdjuster) / ny


                    if (enemyList.contains(enemy)) {
                        rotationEnemyX = enemy.circle!!.x
                        rotationEnemyY = enemy.circle!!.y

                        if (bullet.x > (enemy.circle!!.x + xSpeed())) {
                            bullet.x -= (nx * n)
                        } else {
                            bullet.x += (nx * n)
                        }

                        if (bullet.y > (enemy.circle!!.y + ySpeed())) {
                            bullet.y -= (ny * n)
                        } else {
                            bullet.y += (ny * n)
                        }

                       // if (!crossesAllList.contains(enemy)) broken = 1
                        //  break

                        //  } else if (enemy.status != it.id && enemy == crossesAllList.lastOrNull()) {

                        //      it.broken = 1
                   // } else if (enemy.status != id) {
                    } else {
                        bullet.x = towerList[towerId].towerRange.x
                        bullet.y = towerList[towerId].towerRange.y
                        broken = 1
                    }
        }


        fun movement(enemy: Enemy) {

                fun xSpeed(): Int {
                    var speed = 0
                    when (enemy.passed) {
                        0.toByte() -> speed = 0
                        1.toByte() -> speed = (enemy.speed.toInt()) * (-1)
                        2.toByte() -> speed = 0
                        3.toByte() -> speed = (enemy.speed.toInt())
                        4.toByte() -> speed = 0
                    }
                    return speed
                }

                fun ySpeed(): Int {
                    var speed = 0
                    when (enemy.passed) {
                        0.toByte() -> speed = (enemy.speed.toInt()) * (-1)
                        1.toByte() -> speed = 0
                        2.toByte() -> speed = (enemy.speed.toInt()) * (-1)
                        3.toByte() -> speed = 0
                        4.toByte() -> speed = (enemy.speed.toInt())
                    }
                    return speed
                }

                var nx =
                    if (bullet.x > (enemy.circle!!.x + xSpeed())) ((bullet.x - (enemy.circle!!.x + xSpeed().toFloat())) / (bulletSpeed * Companion.gameSpeedAdjuster))
                    else (((enemy.circle!!.x + xSpeed().toFloat()) - bullet.x) / (bulletSpeed * Companion.gameSpeedAdjuster))
                var ny =
                    if (bullet.y > (enemy.circle!!.y + xSpeed())) ((bullet.y - (enemy.circle!!.y + ySpeed().toFloat())) / (bulletSpeed * Companion.gameSpeedAdjuster))
                    else (((enemy.circle!!.y + ySpeed().toFloat()) - bullet.y) / (bulletSpeed * Companion.gameSpeedAdjuster))

                var n =
                    if (nx > ny) (bulletSpeed * Companion.gameSpeedAdjuster) / nx
                    else (bulletSpeed * Companion.gameSpeedAdjuster) / ny

                if (sniper) {
                    if (towerList[towerId].crossesNoneList.contains(enemy)) {
                        rotationEnemyX = enemy.circle!!.x
                        rotationEnemyY = enemy.circle!!.y

                        if (bullet.x > (enemy.circle!!.x + xSpeed())) {
                            bullet.x -= (nx * n)
                        } else {
                            bullet.x += (nx * n)
                        }

                        if (bullet.y > (enemy.circle!!.y + ySpeed())) {
                            bullet.y -= (ny * n)
                        } else {
                            bullet.y += (ny * n)
                        }
                        //    break

                    } else {
                        bullet.x = towerList[towerId].towerRange.x
                        bullet.y = towerList[towerId].towerRange.y
                        broken = 1
                    }
                } else if (alreadyBounced > 0 && towerList[towerId].towerPrimaryElement == "moon") {
                    rotationEnemyX = enemy.circle!!.x
                    rotationEnemyY = enemy.circle!!.y
                    if (enemyList.contains(enemy)) {
                        if (bullet.x > (enemy.circle!!.x + xSpeed())) {
                            bullet.x -= (nx * n)
                        } else {
                            bullet.x += (nx * n)
                        }

                        if (bullet.y > (enemy.circle!!.y + ySpeed())) {
                            bullet.y -= (ny * n)
                        } else {
                            bullet.y += (ny * n)
                        }
                        //    break

                    } else {
                        bullet.x = towerList[towerId].towerRange.x
                        bullet.y = towerList[towerId].towerRange.y
                        broken = 1
                    }

                } else {
                    if (enemyList.contains(enemy)) {
                        rotationTowerX = enemy.circle!!.x
                        rotationTowerY = enemy.circle!!.y
                        rotationEnemyX = enemy.circle!!.x
                        rotationEnemyY = enemy.circle!!.y
                        if (bullet.x > (enemy.circle!!.x + xSpeed())) {
                            bullet.x -= nx * n
                        } else {
                            bullet.x += nx * n
                        }

                        if (bullet.y > (enemy.circle!!.y + ySpeed())) {
                            bullet.y -= ny * n
                        } else {
                            bullet.y += ny * n
                        }
                        //    break
                    } else {
                        towerList[towerId].randomEnemyForShotBool = true
                        if (MainActivity.startScreenBool){
                            bullet.x = 200.0f
                            bullet.y = 750.0f
                        } else {
                            bullet.x = towerList[towerId].towerRange.x
                            bullet.y = towerList[towerId].towerRange.y
                        }
                        broken = 1
                    }
                }
        }
}


/*

var mx =
                    if (bullet.x > (enemy!!.circle.x + xSpeed())) (bulletSpeed * (-1))
                    else bulletSpeed
                var my =
                    if (bullet.y > (enemy!!.circle.y + ySpeed())) (bulletSpeed * (-1))
                    else bulletSpeed




                     var x =
                        if (bullet.x > (enemy!!.circle.x + xSpeed())) (ceil(((bullet.x.toDouble() - (enemy!!.circle.x + xSpeed()).toDouble()) ))).toInt()
                        else (ceil((((enemy!!.circle.x+ xSpeed()).toDouble() - bullet.x.toDouble())))).toInt()

                    var ny =
                        if (bullet.y > (enemy!!.circle.y+ ySpeed())) (ceil(((bullet.y.toDouble() - (enemy!!.circle.y + ySpeed()).toDouble()) ))).toInt()
                        else (ceil((((enemy!!.circle.y + ySpeed()).toDouble() - bullet.y.toDouble()) ))).toInt()


                        if (bullet.x >= enemy!!.circle.x) {
                            if (bullet.y >= enemy!!.circle.y) {
                                bullet.y =
                                    (enemy!!.circle.y + ySpeed() + ((bulletSpeed.toInt()) * (ny1 - 1)))
                                bullet.x =
                                    (enemy!!.circle.x + xSpeed() + ((bulletSpeed.toInt()) * (nx1 - 1)))
                            } else if (bullet.y < enemy!!.circle.y) {
                                bullet.y =
                                    (enemy!!.circle.y + ySpeed() - ((bulletSpeed.toInt()) * (ny2 - 1)))
                                bullet.x =
                                    (enemy!!.circle.x + xSpeed() + ((bulletSpeed.toInt()) * (nx1 - 1)))
                            }
                        } else if (bullet.x < enemy!!.circle.x) {
                            if (bullet.y >= enemy!!.circle.y) {
                                bullet.y =
                                    (enemy!!.circle.y + ySpeed() + ((bulletSpeed.toInt()) * (ny1 - 1)))
                                bullet.x =
                                    (enemy!!.circle.x + xSpeed() - ((bulletSpeed.toInt()) * (nx2 - 1)))
                            } else if (bullet.y < enemy!!.circle.y) {
                                bullet.y =
                                    (enemy!!.circle.y + ySpeed() - ((bulletSpeed.toInt()) * (ny2 - 1)))
                                bullet.x =
                                    (enemy!!.circle.x + xSpeed() - ((bulletSpeed.toInt()) * (nx2 - 1)))
                            }
                        }




                    //  } else if (GameView.enemyList[index]!!.status == 0 && GameView.enemyList[index]!!.hp <= 0) {
                    //      bullet.x += 30

                    //  }


      for (enemy in GameView.enemyList) {

            fun xSpeed () : Int {
                var speed = 0
                when (enemy!!.passed) {
                    "A" -> speed = (Enemy.speed.toInt() /2) * (-1)
                    "B" -> speed = 0
                    "C" -> speed = (Enemy.speed.toInt() /2)
                    "D" -> speed = 0
                }
                return speed
            }

            fun ySpeed () : Int {
                var speed = 0
                when (enemy!!.passed) {
                    "A" -> speed = 0
                    "B" -> speed = (Enemy.speed.toInt() /2) * (-1)
                    "C" -> speed = 0
                    "D" -> speed = (Enemy.speed.toInt() /2)
                }
                return speed
            }

            var nx1 =
                (ceil(((bullet.x.toDouble() - enemy!!.circle.x.toDouble()) / bulletSpeed))).toInt()
            var nx2 =
                (ceil(((enemy!!.circle.x.toDouble() - bullet.x.toDouble()) / bulletSpeed))).toInt()
            var ny1 =
                (ceil(((bullet.y.toDouble() - enemy!!.circle.y.toDouble()) / bulletSpeed))).toInt()
            var ny2 =
                (ceil(((enemy!!.circle.y.toDouble() - bullet.y.toDouble()) / bulletSpeed))).toInt()


                if (bullet.x >= enemy!!.circle.x) {
                    if (bullet.y >= enemy!!.circle.y) {
                        bullet.y =
                            (enemy!!.circle.y + ySpeed() + ((bulletSpeed.toInt()) * (ny1 - 1)))
                        bullet.x =
                            (enemy!!.circle.x + xSpeed() + ((bulletSpeed.toInt()) * (nx1 - 1)))
                    } else if (bullet.y < enemy!!.circle.y) {
                        bullet.y =
                            (enemy!!.circle.y + ySpeed() - ((bulletSpeed.toInt()) * (ny2 - 1)))
                        bullet.x =
                            (enemy!!.circle.x + xSpeed() + ((bulletSpeed.toInt()) * (nx1 - 1)))
                    }
                } else if (bullet.x < enemy!!.circle.x) {
                    if (bullet.y >= enemy!!.circle.y) {
                        bullet.y =
                            (enemy!!.circle.y + ySpeed() + ((bulletSpeed.toInt()) * (ny1 - 1)))
                        bullet.x =
                            (enemy!!.circle.x + xSpeed() - ((bulletSpeed.toInt()) * (nx2 - 1)))
                    } else if (bullet.y < enemy!!.circle.y) {
                        bullet.y =
                            (enemy!!.circle.y + ySpeed() - ((bulletSpeed.toInt()) * (ny2 - 1)))
                        bullet.x =
                            (enemy!!.circle.x + xSpeed() - ((bulletSpeed.toInt()) * (nx2 - 1)))
                    }
                }
            }

        if (bullet.x == enemyX -40) bullet.x = enemyX
                else {
                    bullet.x -= 30
                    if (bullet.y > enemyY){
                        bullet.y -= Enemy.speed.toInt()/(Tower.towerRange.y - enemyY)
                    }else bullet.y += Enemy.speed.toInt()/(Tower.towerRange.y - enemyY)
                }
            } else if (bullet.x < enemyX) {
                if (bullet.x == enemyX +40) bullet.x = enemyX
                else bullet.x += 30
                if (bullet.y > enemyY){
                    bullet.y -= Enemy.speed.toInt()/(Tower.towerRange.y - enemyY)
                }else bullet.y += Enemy.speed.toInt()/(Tower.towerRange.y - enemyY)
            }

            if (bullet.y > enemyY) {
                if (bullet.y == enemyY -40) bullet.y = enemyY
                else bullet.y -= 30
            } else if (bullet.y < enemyY) {
                if (bullet.y == enemyY +40) bullet.y = enemyY
                else bullet.y += 30
            }

            if (bullet.x > enemyX) {
                if (bullet.y > enemyY) {
                    if (bullet.x - enemyX > bullet.y - enemyY) {
                        bullet.x -= bulletSpeed
                        bullet.y -= bulletSpeed / 2
                    } else if (bullet.x - enemyX == bullet.y - enemyY) {
                        bullet.x -= bulletSpeed
                        bullet.y -= bulletSpeed
                    } else {
                        bullet.x -= bulletSpeed / 2
                        bullet.y -= bulletSpeed
                    }
                } else if (bullet.y < enemyY) {
                    if (bullet.x - enemyX > enemyY - bullet.y) {
                        bullet.x -= bulletSpeed
                        bullet.y += bulletSpeed / 2
                    } else if (bullet.x - enemyX == enemyY - bullet.y) {
                        bullet.x -= bulletSpeed
                        bullet.y += bulletSpeed
                    } else {
                        bullet.x -= bulletSpeed / 2
                        bullet.y += bulletSpeed
                    }
                }else if (bullet.y == enemyY) bullet.x -= bulletSpeed
            } else if (bullet.x < enemyX) {
                if (bullet.y > enemyY) {
                    if (enemyX - bullet.x > bullet.y - enemyY) {
                        bullet.x += bulletSpeed
                        bullet.y -= bulletSpeed / 2
                    } else if (enemyX - bullet.x == bullet.y - enemyY) {
                        bullet.x += bulletSpeed
                        bullet.y -= bulletSpeed
                    } else {
                        bullet.x += bulletSpeed / 2
                        bullet.y -= bulletSpeed
                    }
                } else if (bullet.y < enemyY) {
                    if (enemyX - bullet.x > enemyY - bullet.y) {
                        bullet.x += bulletSpeed
                        bullet.y += bulletSpeed / 2
                    } else if (enemyX - bullet.x == enemyY - bullet.y) {
                        bullet.x += bulletSpeed
                        bullet.y += bulletSpeed
                    } else {
                        bullet.x += bulletSpeed / 2
                        bullet.y += bulletSpeed
                    }
                }else if (bullet.y == enemyY) bullet.x += bulletSpeed

            } else if (bullet.x == enemyX) {
                if (bullet.y > enemyY) bullet.y -= bulletSpeed
                else if (bullet.y < enemyX) bullet.y += bulletSpeed
                }

 */
/*
// splash damage
var x1 = (ceil(bullet.x.toDouble() - Shoot.enemyX.toDouble() / Shoot.bulletSpeed)).toInt()
var x2 = (ceil(Shoot.enemyX.toDouble() - bullet.x.toDouble() / Shoot.bulletSpeed)).toInt()
var y1 = (ceil(bullet.y.toDouble() - Shoot.enemyY.toDouble() / Shoot.bulletSpeed)).toInt()
var y2 = (ceil(Shoot.enemyY.toDouble() - bullet.y.toDouble() / Shoot.bulletSpeed)).toInt()

if (bullet.x > enemyX) bullet.x = enemyX + (bulletSpeed.toInt() * (x1-1))
if (bullet.x < enemyX) bullet.x = enemyX - (bulletSpeed.toInt() * (x2-1))

if (bullet.y > enemyY) bullet.y = enemyY + (bulletSpeed.toInt() * (y1-1))
if (bullet.y < enemyY) bullet.y = enemyY - (bulletSpeed.toInt() * (y2-1))


//

var x1 =
                (ceil(((bullet.x.toDouble() - enemy.circle.x.toDouble()) / bulletSpeed))).toInt()
            var x2 =
                (ceil(((enemy.circle.x.toDouble() - bullet.x.toDouble()) / bulletSpeed))).toInt()
            var y1 =
                (ceil(((bullet.y.toDouble() - enemy.circle.y.toDouble()) / bulletSpeed))).toInt()
            var y2 =
                (ceil(((enemy.circle.y.toDouble() - bullet.y.toDouble()) / bulletSpeed))).toInt()


if (bullet.x >= enemy.circle.x) {
                    if (bullet.y >= enemy.circle.y) {
                        bullet.y = (enemy.circle.y + ((bulletSpeed.toInt()) * (y1 - 1)))
                        bullet.x = (enemy.circle.x + ((bulletSpeed.toInt()) * (x1 - 1)))
                    } else if (bullet.y < enemy.circle.y) {
                        bullet.y = (enemy.circle.y - ((bulletSpeed.toInt()) * (y2 - 1)))
                        bullet.x = (enemy.circle.x + ((bulletSpeed.toInt()) * (x1 - 1)))
                    }
                } else if (bullet.x < enemy.circle.x) {
                    if (bullet.y >= enemy.circle.y) {
                        bullet.y = (enemy.circle.y + ((bulletSpeed.toInt()) * (y1 - 1)))
                        bullet.x = (enemy.circle.x - ((bulletSpeed.toInt()) * (x2 - 1)))
                    } else if (bullet.y < enemy.circle.y) {
                        bullet.y = (enemy.circle.y - ((bulletSpeed.toInt()) * (y2 - 1)))
                        bullet.x = (enemy.circle.x - ((bulletSpeed.toInt()) * (x2 - 1)))
                    }
                }



                 if (randomEnemyForShotBool) {
                                    var use: Enemy? = null
                                    var place = 0f
                                    var place2 = 2000f

                                    for (it in crossesAllList) {
                                        when {
                                            it.passed == 4.toByte() -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID = enemyList.indexOf(it)
                                                advancedList4.add(x)
                                            }
                                            it.passed == 3.toByte() -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID = enemyList.indexOf(it)
                                                advancedList3.add(x)
                                            }
                                            it.passed == 2.toByte() -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID = enemyList.indexOf(it)
                                                advancedList2.add(x)
                                            }
                                            it.passed == 1.toByte() -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID = enemyList.indexOf(it)
                                                advancedList1.add(x)
                                            }
                                        }
                                    }
                                    if (advancedList4.isNotEmpty()) {
                                        for (it in advancedList4) {
                                            if (it.circle!!.y > place) {
                                                place = it.circle!!.y
                                                use = enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (advancedList3.isNotEmpty()) {
                                        for (it in advancedList3) {
                                            if (it.circle!!.x > place) {
                                                place = it.circle!!.x
                                                use = enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (advancedList2.isNotEmpty()) {
                                        for (it in advancedList2) {
                                            if (it.circle!!.y < place2) {
                                                place2 = it.circle!!.y
                                                use = enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (advancedList1.isNotEmpty()) {
                                        for (it in advancedList1) {
                                            if (it.circle!!.x < place2) {
                                                place2 = it.circle!!.x
                                                use = enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else {
                                    }
                                    randomEnemyForShot = use!!
                                    movement(randomEnemyForShot)
                                    randomEnemyForShotBool = false
                                    advancedList4.removeAll(advancedList4)
                                    advancedList3.removeAll(advancedList3)
                                    advancedList2.removeAll(advancedList2)
                                    advancedList1.removeAll(advancedList1)
                                } else movement(randomEnemyForShot)
 */