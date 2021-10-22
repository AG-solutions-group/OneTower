package com.agsolutions.td

import android.graphics.Canvas
import android.graphics.Color
import java.io.Serializable

class Shoot (): Serializable {

    var bulletSpeed: Float = 10.0F
    var bullet = TowerRadius(600.0f, 750.0f, 5.0f)
    var broken = 0
    var bounceLeft = 0
    var alreadyBounced = 0
    var alreadyBouncedReset = false
    var alreadyBouncedResetChain = false
    var id = -1
    var idchain = -1
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
    var hitEnemyId = -1
    var hitEnemyIdChain = -1
    var collisionCount = 0

    fun draw(canvas: Canvas) {

    //    canvas.drawCircle(bullet.x.toFloat(), bullet.y.toFloat(), bullet.r.toFloat(), paint)

    }

    fun update() {

            //   var crossesAllListIteratorX = crossesAllList.listIterator()
            //   while (crossesAllListIteratorX.hasNext()) {
            //       var enemy = crossesAllListIteratorX.next()

            if (sniper) {
                if (GameActivity.companionList.towerList[towerId].crossesNoneList.contains(GameActivity.companionList.towerList[towerId].randomEnemyForSniper)) movement(GameActivity.companionList.towerList[towerId].randomEnemyForSniper)
                else if (GameActivity.companionList.towerList[towerId].crossesNoneList.isNotEmpty()) {
                    GameActivity.companionList.towerList[towerId].randomEnemyForSniper = GameActivity.companionList.towerList[towerId].crossesNoneList.random()
                    movement(GameActivity.companionList.towerList[towerId].randomEnemyForSniper)
                }  // random
                else {
                    bullet.x = GameActivity.companionList.towerList[towerId].towerRange.x
                    bullet.y = GameActivity.companionList.towerList[towerId].towerRange.y
                    broken = 1
                }
            } else if (chainLightning) {
                if(alreadyBounced > 0 && GameActivity.companionList.enemyList.isNotEmpty()) {
                    if (idchain >= 0 && idchain < GameActivity.companionList.enemyList.size && alreadyBouncedResetChain && idchain != hitEnemyIdChain) {
                        movement(GameActivity.companionList.enemyList[idchain])
                    } else {
                            var squaredDistancePlaceholder = 185f * 185f
                            var squareDistanceList = mutableListOf<Enemy>()
                        GameActivity.companionList.readLockEnemy.lock()
                        try {
                            var enemyListIterator = GameActivity.companionList.enemyList.listIterator()
                            while (enemyListIterator.hasNext()) {
                                var itX = enemyListIterator.next()
                                if (GameActivity.companionList.enemyList.indexOf(itX) != hitEnemyIdChain) {

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
                            }
                        } finally {
                            GameActivity.companionList.readLockEnemy.unlock()
                        }

                            if (squareDistanceList.isNotEmpty()) {
                                var enemm = squareDistanceList.random()

                                if (enemm.circle!!.x > bullet.x){
                                    bullet.x += 20
                                }
                                else if (enemm.circle!!.x < bullet.x){
                                    bullet.x -= 20
                                }
                                if (enemm.circle!!.y > bullet.y){
                                    bullet.y += 20
                                }
                                else if (enemm.circle!!.y < bullet.y){
                                    bullet.y -= 20
                                }

                                movement(enemm)
                                idchain = GameActivity.companionList.enemyList.indexOf(enemm)
                                alreadyBouncedResetChain = true
                                squareDistanceList.removeAll(squareDistanceList)
                            } else {
                                GameActivity.companionList.towerList[towerId].randomEnemyChainBool = true
                                broken = 1
                            }
                    }
                }else {
                    if (GameActivity.companionList.towerList[towerId].randomEnemyChainBool && GameActivity.companionList.towerList[towerId].crossesAllList.isNotEmpty()) {
                        GameActivity.companionList.towerList[towerId].randomEnemyChainBool = false
                        GameActivity.companionList.towerList[towerId].randomEnemyForShotChain = GameActivity.companionList.towerList[towerId].crossesAllList.random()
                        movement(GameActivity.companionList.towerList[towerId].randomEnemyForShotChain)
                    } else if (!GameActivity.companionList.towerList[towerId].randomEnemyChainBool && GameActivity.companionList.enemyList.contains(GameActivity.companionList.towerList[towerId].randomEnemyForShotChain)) movement(GameActivity.companionList.towerList[towerId].randomEnemyForShotChain)
                    else if (GameActivity.companionList.towerList[towerId].crossesAllList.isNotEmpty()) GameActivity.companionList.towerList[towerId].randomEnemyChainBool = true
                    else {
                        GameActivity.companionList.towerList[towerId].randomEnemyChainBool = true
                        broken = 1
                    }
                }
            }else if (GameActivity.companionList.towerList[towerId].towerPrimaryElement == "wind" && multiShotBullet && GameActivity.companionList.enemyList.isNotEmpty()) {
                if (firstEnemyMultiBool && GameActivity.companionList.enemyList!!.contains(firstEnemyMulti)){
                    movementMulti(firstEnemyMulti!!)
                }
                else {
                    if (id >= GameActivity.companionList.towerList[towerId].crossesAllList.size) {
                        bullet.x = GameActivity.companionList.towerList[towerId].towerRange.x
                        bullet.y = GameActivity.companionList.towerList[towerId].towerRange.y
                        broken = 1
                    } else {
                        if (GameActivity.companionList.towerList[towerId].crossesAllList.isNotEmpty()) {
                            firstEnemyMulti = (GameActivity.companionList.towerList[towerId].crossesAllList[id])
                            movementMulti(firstEnemyMulti!!)
                            firstEnemyMultiBool = true
                        } else {
                            bullet.x = GameActivity.companionList.towerList[towerId].towerRange.x
                            bullet.y = GameActivity.companionList.towerList[towerId].towerRange.y
                            broken = 1
                        }
                    }
                }
            } else if (alreadyBounced > 0 && GameActivity.companionList.towerList[towerId].towerPrimaryElement == "moon") {
                if (id >= 0 && id < GameActivity.companionList.enemyList.size && alreadyBouncedReset && id != hitEnemyId) {
                    movement(GameActivity.companionList.enemyList[id])
                } else if (GameActivity.companionList.enemyList.isNotEmpty()) {
                    var squaredDistancePlaceholder = 185f * 185f
                    var squareDistanceList = mutableListOf<Enemy>()

                    GameActivity.companionList.readLockEnemy.lock()
                    try {
                    var enemyListIterator = GameActivity.companionList.enemyList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var itX = enemyListIterator.next()
                        if (GameActivity.companionList.enemyList.indexOf(itX) != hitEnemyId) {

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
                    }
                    } finally {
                        GameActivity.companionList.readLockEnemy.unlock()
                    }

                    if (squareDistanceList.isNotEmpty()) {
                        var enemm = squareDistanceList.random()

                        if (enemm.circle!!.x > bullet.x){
                            bullet.x += 20
                        }
                        else if (enemm.circle!!.x < bullet.x){
                            bullet.x -= 20
                        }
                        if (enemm.circle!!.y > bullet.y){
                            bullet.y += 20
                        }
                        else if (enemm.circle!!.y < bullet.y){
                            bullet.y -= 20
                        }

                        movement(enemm)
                        id = GameActivity.companionList.enemyList.indexOf(enemm)
                        alreadyBouncedReset = true
                        squareDistanceList.removeAll(squareDistanceList)
                    } else {
                        GameActivity.companionList.towerList[towerId].randomEnemyChainBool = true
                        broken = 1
                    }
                } else {
                    GameActivity.companionList.towerList[towerId].randomEnemyChainBool = true
                    broken = 1
                }
            } else {
                if (GameActivity.companionList.towerList[towerId].crossesAllList.isNotEmpty()) {
                    var tank = false
                    GameActivity.companionList.readLockEnemy.lock()
                    try {
                    var crossesAllListIterator = GameActivity.companionList.towerList[towerId].crossesAllList.listIterator()
                    while (crossesAllListIterator.hasNext()) {
                        var it = crossesAllListIterator.next()
                        if (it.name == "tank") {
                            tank = true
                            movement(it)
                        }
                    }
                    } finally {
                        GameActivity.companionList.readLockEnemy.unlock()
                    }
                    if (tank) {
                    } else {
                            if (GameActivity.companionList.towerList[towerId].firstLastRandom == 3) {
                                if (GameActivity.companionList.towerList[towerId].randomEnemyForShotBool) {
                                    var use: Enemy? = null
                                    var place = 0f
                                    var place2 = 2000f

                                    GameActivity.companionList.readLockEnemy.lock()
                                    try {
                                    var crossesAllListIteratorZ = GameActivity.companionList.towerList[towerId].crossesAllList.listIterator()
                                    while (crossesAllListIteratorZ.hasNext()) {
                                        var it = crossesAllListIteratorZ.next()
                                        when (it.path[it.point].third) {
                                            1 -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID =
                                                    GameActivity.companionList.enemyList.indexOf(it)
                                                GameActivity.companionList.advancedList1.add(x)
                                            }
                                            2 -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID =
                                                    GameActivity.companionList.enemyList.indexOf(it)
                                                GameActivity.companionList.advancedList2.add(x)
                                            }
                                            3 -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID =
                                                    GameActivity.companionList.enemyList.indexOf(it)
                                                GameActivity.companionList.advancedList3.add(x)
                                            }
                                            4 -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID =
                                                    GameActivity.companionList.enemyList.indexOf(it)
                                                GameActivity.companionList.advancedList4.add(x)
                                            }
                                            5 -> {
                                                var x =
                                                    Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                                x.circle!!.x = it.circle!!.x
                                                x.circle!!.y = it.circle!!.y
                                                x.pickEnemyID =
                                                    GameActivity.companionList.enemyList.indexOf(it)
                                                GameActivity.companionList.advancedList5.add(x)
                                            }
                                        }
                                    }
                                    if (GameActivity.companionList.advancedList5.isNotEmpty()) {
                                        for (it in GameActivity.companionList.advancedList5) {
                                            if (it.circle!!.y > place) {
                                                place = it.circle!!.y
                                                use = GameActivity.companionList.enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (GameActivity.companionList.advancedList4.isNotEmpty()) {
                                        for (it in GameActivity.companionList.advancedList4) {
                                            if (it.circle!!.x > place) {
                                                place = it.circle!!.x
                                                use = GameActivity.companionList.enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (GameActivity.companionList.advancedList3.isNotEmpty()) {
                                        for (it in GameActivity.companionList.advancedList3) {
                                            if (it.circle!!.y < place2) {
                                                place = it.circle!!.y
                                                use = GameActivity.companionList.enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (GameActivity.companionList.advancedList2.isNotEmpty()) {
                                        for (it in GameActivity.companionList.advancedList2) {
                                            if (it.circle!!.x < place2) {
                                                place2 = it.circle!!.x
                                                use = GameActivity.companionList.enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else if (GameActivity.companionList.advancedList1.isNotEmpty()) {
                                        for (it in GameActivity.companionList.advancedList1) {
                                            if (it.circle!!.y < place2) {
                                                place2 = it.circle!!.y
                                                use = GameActivity.companionList.enemyList[it.pickEnemyID]
                                            }
                                        }
                                    } else {
                                    }
                                    if (use != null) {
                                        GameActivity.companionList.towerList[towerId].randomEnemyForShot = use!!
                                        movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                                        GameActivity.companionList.towerList[towerId].randomEnemyForShotBool = false
                                        GameActivity.companionList.advancedList5.removeAll(GameActivity.companionList.advancedList5)
                                        GameActivity.companionList.advancedList4.removeAll(GameActivity.companionList.advancedList4)
                                        GameActivity.companionList.advancedList3.removeAll(GameActivity.companionList.advancedList3)
                                        GameActivity.companionList.advancedList2.removeAll(GameActivity.companionList.advancedList2)
                                        GameActivity.companionList.advancedList1.removeAll(GameActivity.companionList.advancedList1)
                                    } else {
                                    }
                                    } finally {
                                        GameActivity.companionList.readLockEnemy.unlock()
                                    }
                                } else movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                            } else if (GameActivity.companionList.towerList[towerId].firstLastRandom == 1) {
                                if (GameActivity.companionList.towerList[towerId].randomEnemyForShotBool){
                                for (it in GameActivity.companionList.enemyList) {
                                    when {
                                        it.circleXMovement == "xminus" && GameActivity.companionList.towerList[towerId].crossesAllList.contains(it) -> {
                                            GameActivity.companionList.towerList[towerId].randomEnemyForShot = it
                                            movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                                            GameActivity.companionList.towerList[towerId].randomEnemyForShotBool = false
                                            break
                                        }
                                        it.circleYMovement == "yminus" && GameActivity.companionList.towerList[towerId].crossesAllList.contains(it) -> {
                                            GameActivity.companionList.towerList[towerId].randomEnemyForShot = it
                                            movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                                            GameActivity.companionList.towerList[towerId].randomEnemyForShotBool = false
                                            break
                                        }
                                        it.circleXMovement == "xplus" && GameActivity.companionList.towerList[towerId].crossesAllList.contains(it) -> {
                                            GameActivity.companionList.towerList[towerId].randomEnemyForShot = it
                                            movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                                            GameActivity.companionList.towerList[towerId].randomEnemyForShotBool = false
                                            break
                                        }
                                        it.circleYMovement == "yplus" && GameActivity.companionList.towerList[towerId].crossesAllList.contains(it) -> {
                                            GameActivity.companionList.towerList[towerId].randomEnemyForShot = it
                                            movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                                            GameActivity.companionList.towerList[towerId].randomEnemyForShotBool = false
                                            break
                                        }
                                    }
                                }
                                } else movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                            } else if (GameActivity.companionList.towerList[towerId].firstLastRandom == 0) {
                            if (GameActivity.companionList.towerList[towerId].randomEnemyForShotSticky) {
                                var use: Enemy? = null
                                var place = 0f
                                var place2 = 2000f

                                GameActivity.companionList.readLockEnemy.lock()
                                try {
                                var crossesAllListIteratorZ = GameActivity.companionList.towerList[towerId].crossesAllList.listIterator()
                                while (crossesAllListIteratorZ.hasNext()) {
                                    var it = crossesAllListIteratorZ.next()
                                    when (it.circleXMovement) {
                                        "xplus" ->{
                                            var x =
                                                Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                            x.circle!!.x = it.circle!!.x
                                            x.circle!!.y = it.circle!!.y
                                            x.pickEnemyID = GameActivity.companionList.enemyList.indexOf(it)
                                            GameActivity.companionList.advancedList3.add(x)
                                        }
                                        "xminus" -> {
                                            var x =
                                                Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                            x.circle!!.x = it.circle!!.x
                                            x.circle!!.y = it.circle!!.y
                                            x.pickEnemyID = GameActivity.companionList.enemyList.indexOf(it)
                                            GameActivity.companionList.advancedList1.add(x)
                                        }

                                    }
                                    when (it.circleYMovement) {
                                        "yplus" -> {
                                            var x =
                                                Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                            x.circle!!.x = it.circle!!.x
                                            x.circle!!.y = it.circle!!.y
                                            x.pickEnemyID = GameActivity.companionList.enemyList.indexOf(it)
                                            GameActivity.companionList.advancedList4.add(x)
                                        }
                                        "yminus" -> {
                                            var x =
                                                Enemy(1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, Color.RED)
                                            x.circle!!.x = it.circle!!.x
                                            x.circle!!.y = it.circle!!.y
                                            x.pickEnemyID = GameActivity.companionList.enemyList.indexOf(it)
                                            GameActivity.companionList.advancedList2.add(x)
                                        }
                                    }
                                }
                                } finally {
                                    GameActivity.companionList.readLockEnemy.unlock()
                                }
                                if (GameActivity.companionList.advancedList4.isNotEmpty()) {
                                    for (it in GameActivity.companionList.advancedList4) {
                                        if (it.circle!!.y > place) {
                                            place = it.circle!!.y
                                            use = GameActivity.companionList.enemyList[it.pickEnemyID]
                                        }
                                    }
                                } else if (GameActivity.companionList.advancedList3.isNotEmpty()) {
                                    for (it in GameActivity.companionList.advancedList3) {
                                        if (it.circle!!.x > place) {
                                            place = it.circle!!.x
                                            use = GameActivity.companionList.enemyList[it.pickEnemyID]
                                        }
                                    }
                                } else if (GameActivity.companionList.advancedList2.isNotEmpty()) {
                                    for (it in GameActivity.companionList.advancedList2) {
                                        if (it.circle!!.y < place2) {
                                            place2 = it.circle!!.y
                                            use = GameActivity.companionList.enemyList[it.pickEnemyID]
                                        }
                                    }
                                } else if (GameActivity.companionList.advancedList1.isNotEmpty()) {
                                    for (it in GameActivity.companionList.advancedList1) {
                                        if (it.circle!!.x < place2) {
                                            place2 = it.circle!!.x
                                            use = GameActivity.companionList.enemyList[it.pickEnemyID]
                                        }
                                    }
                                } else {
                                }
                                if (use != null) {
                                    GameActivity.companionList.towerList[towerId].randomEnemyForShot = use!!
                                    movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                                    GameActivity.companionList.towerList[towerId].randomEnemyForShotSticky = false
                                    GameActivity.companionList.advancedList4.removeAll(GameActivity.companionList.advancedList4)
                                    GameActivity.companionList.advancedList3.removeAll(GameActivity.companionList.advancedList3)
                                    GameActivity.companionList.advancedList2.removeAll(GameActivity.companionList.advancedList2)
                                    GameActivity.companionList.advancedList1.removeAll(GameActivity.companionList.advancedList1)
                                } else {
                                }
                            } else movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                        } else {
                                if (GameActivity.companionList.towerList[towerId].randomEnemyForShotBool) {
                                    GameActivity.companionList.towerList[towerId].randomEnemyForShot = GameActivity.companionList.towerList[towerId].crossesAllList.random()
                                    movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                                    GameActivity.companionList.towerList[towerId].randomEnemyForShotBool = false
                                }  // random
                                else movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                            }
                        }
                } else {
                    if (GameActivity.companionList.enemyList.contains(GameActivity.companionList.towerList[towerId].randomEnemyForShot)) {
                        movement(GameActivity.companionList.towerList[towerId].randomEnemyForShot)
                    } else {
                        GameActivity.companionList.towerList[towerId].randomEnemyForShotBool = true
                            bullet.x = GameActivity.companionList.towerList[towerId].towerRange.x
                            bullet.y = GameActivity.companionList.towerList[towerId].towerRange.y
                            broken = 1
                    }
                }
            }
    }


        fun movementMulti(enemy: Enemy) {

                    fun xSpeed(): Int {
                        var speed = 0
                        when (enemy.circleXMovement) {
                            "xminus" -> speed = (enemy.speed.toInt()) * (-1)
                            "xplus" -> speed = (enemy.speed.toInt())
                        }
                        return speed
                    }

                    fun ySpeed(): Int {
                        var speed = 0
                        when (enemy.circleYMovement) {
                            "yminus" -> speed = (enemy.speed.toInt()) * (-1)
                            "yplus" -> speed = (enemy.speed.toInt())
                        }
                        return speed
                    }

                    val nx =
                        if (bullet.x > (enemy.circle!!.x + xSpeed())) ((bullet.x - (enemy.circle!!.x + xSpeed().toFloat())) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                        else (((enemy.circle!!.x + xSpeed().toFloat()) - bullet.x) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                    val ny =
                        if (bullet.y > (enemy.circle!!.y + xSpeed())) ((bullet.y - (enemy.circle!!.y + ySpeed().toFloat())) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                        else (((enemy.circle!!.y + ySpeed().toFloat()) - bullet.y) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))

                    val n =
                        if (nx > ny) (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster) / nx
                        else (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster) / ny


                    if (GameActivity.companionList.enemyList.contains(enemy)) {
                        GameActivity.companionList.rotationEnemyX = enemy.circle!!.x
                        GameActivity.companionList.rotationEnemyY = enemy.circle!!.y

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
                        bullet.x = GameActivity.companionList.towerList[towerId].towerRange.x
                        bullet.y = GameActivity.companionList.towerList[towerId].towerRange.y
                        broken = 1
                    }
        }


        fun movement(enemy: Enemy) {

                fun xSpeed(): Int {
                    var speed = 0
                    when (enemy.circleXMovement) {
                        "xminus" -> speed = (enemy.speed.toInt()) * (-1)
                        "xplus" -> speed = (enemy.speed.toInt())

                    }
                    return speed
                }

                fun ySpeed(): Int {
                    var speed = 0
                    when (enemy.circleYMovement) {
                        "yminus" -> speed = (enemy.speed.toInt()) * (-1)
                        "yplus" -> speed = (enemy.speed.toInt())
                    }
                    return speed
                }

                var nx =
                    if (bullet.x > (enemy.circle!!.x + xSpeed())) ((bullet.x - (enemy.circle!!.x + xSpeed().toFloat())) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                    else (((enemy.circle!!.x + xSpeed().toFloat()) - bullet.x) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                var ny =
                    if (bullet.y > (enemy.circle!!.y + xSpeed())) ((bullet.y - (enemy.circle!!.y + ySpeed().toFloat())) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                    else (((enemy.circle!!.y + ySpeed().toFloat()) - bullet.y) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))

                var n =
                    if (nx > ny) (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster) / nx
                    else (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster) / ny

                if (sniper) {
                    if (GameActivity.companionList.towerList[towerId].crossesNoneList.contains(enemy)) {
                        GameActivity.companionList.rotationEnemyX = enemy.circle!!.x
                        GameActivity.companionList.rotationEnemyY = enemy.circle!!.y

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
                        bullet.x = GameActivity.companionList.towerList[towerId].towerRange.x
                        bullet.y = GameActivity.companionList.towerList[towerId].towerRange.y
                        broken = 1
                    }
                } else if (chainLightning) {
                    if (GameActivity.companionList.enemyList.contains(enemy)) {
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
                        bullet.x = GameActivity.companionList.towerList[towerId].towerRange.x
                        bullet.y = GameActivity.companionList.towerList[towerId].towerRange.y
                        GameActivity.companionList.towerList[towerId].randomEnemyChainBool = true
                        broken = 1
                    }

                } else if (alreadyBounced > 0 && (GameActivity.companionList.towerList[towerId].towerPrimaryElement == "moon")) {
                    GameActivity.companionList.rotationEnemyX = enemy.circle!!.x
                    GameActivity.companionList.rotationEnemyY = enemy.circle!!.y
                    if (GameActivity.companionList.enemyList.contains(enemy)) {
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
                        bullet.x = GameActivity.companionList.towerList[towerId].towerRange.x
                        bullet.y = GameActivity.companionList.towerList[towerId].towerRange.y
                        GameActivity.companionList.towerList[towerId].randomEnemyForShotBool = true
                        broken = 1
                    }

                } else {
                    if (GameActivity.companionList.enemyList.contains(enemy)) {
                        GameActivity.companionList.rotationTowerX = enemy.circle!!.x
                        GameActivity.companionList.rotationTowerY = enemy.circle!!.y
                        GameActivity.companionList.rotationEnemyX = enemy.circle!!.x
                        GameActivity.companionList.rotationEnemyY = enemy.circle!!.y
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
                        GameActivity.companionList.towerList[towerId].randomEnemyForShotBool = true
                            bullet.x = GameActivity.companionList.towerList[towerId].towerRange.x
                            bullet.y = GameActivity.companionList.towerList[towerId].towerRange.y
                        broken = 1
                    }
                }
        }
}