package com.agsolutions.td

import android.graphics.Color
import android.graphics.Paint

class Tower(var dmg: Float, var speed: Float, var crit: Float, var critDmg: Float) {

    var towerRange = TowerRadius (600.0f, 750.0f, 300.0f)
    var towerR = 0f
    var towerBonusRange = 0f
    var paint: Paint
    var paint2 : Paint
    var paint3 : Paint
    var selected = false

    var timertower = 0
    var shootCounter = 0
    var crossesAllList = mutableListOf<Enemy>()
    var crossesNoneList = mutableListOf<Enemy>()
    var overallTowerSpd = 0f
    var bonusTowerSpeed = 0f
    var towerAttackSpeed = 0.0f
    var towerAttackSpeedShow = 0.0f
    var bonusCrit = 0.0f
    var overallCrit = 0.0f
    var levelScalerCritBool = 0f
    var towermultiCrit = 1
    var overallMulticrit = 0
    var bonusDamageMultiplyer = 0.0f
    var overallDamageMultiplyer = 1.0f
    var overallTowerDmg = 0.0f
    var bonusTowerDmg = 0.0f
    var overallTowerDmgBool = 0f
    var bonusHitChance = 0.0f
    var towerPhysicalDmg = 0.0f
    var bonusPhysicalDmg = 0.0f

    var overallSpellDmg = 0.0f
    var bonusSpellDamage = 0f
    var overallSpellDmgMultiplyer = 1.0f
    var hitChance = 100.0f
    var bonusCritDmg = 0f

    var overallItemChance = 0.0f
    var bonusItemChance = 0.0f
    var overallItemQuality = 0f
    var bonusItemQuality = 0.0f
    var overallCritDmg = 0f

    var bonusArmorPen = 0f
    var bonusMagicPen = 0f
    var armorPenPerHit = 0f
    var magicPenPerHit = 0f

    var overallBonusArmorPen = 0f
    var overallBonusMagicPen = 0f

    var bagSize = 4 // 5

    var randomEnemyForShot = Enemy (0.0f,0.0f, 0.0f, 0.0f, 0.0f, 0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0)
    var randomEnemyForShotBool = true
    var randomEnemyForShotSticky = true
    var firstLastRandom = 0
    var towerFallingCount = 50
    var towerTowerNextPic = 0
    var towerTowerNextPicPlace = 0

    var multishot = false

    // talent

    // ice
    var iceNovaCounter = 0

    // earth
    var throwBoulderCounter = 0

    // poison
    var poisonOverloadActive = false
    var poisonOverloadCounter = 0
    var poisonOverloadDuration = 0

    // dark
    var fearCounter = 0
    var darkTalentLaserCount = 0
    var darkTalentLaserAlreadyAffectedCount = 0

    // wind
    var bonusSpeedWindTalentPercent = 0.0f

    // fire
    var critCounter = 0

    // wizard
    var wizardMissedLightningActive = false
    var wizardBombCounter = 0

    // moon
    var moonLightCount = 0

    // items
    var itemListBag = mutableListOf<Items>()
    var bonusDamageMultiplyerItemLastStance = 0.0f
    var itemLastStance = 0f
    var itemBonusHitChance = 0.0f
    var itemLasso = 0
    var itemFrost = 0f
    var itemFrostCount = 0
    var itemDisruptor = 0.0f
    var itemSniper = 0
    var itemSniperCount = 0
    var itemPikaDmg = 0f
    var itemPikaSpd = 0f
    var itemPikaCrit = 0f
    var itemSlowDeath = 0.0f

 //   var tower: Tower = Tower(10.0f, 45.0f, 1.0f, 2.0f)

    init {
        paint = Paint()
        paint.isAntiAlias
        paint.isFilterBitmap
        paint.color = Color.BLACK
        paint2 = Paint()
        paint2.isAntiAlias
        paint2.isFilterBitmap
        paint2.strokeWidth = 2f
        paint2.color = (Color.parseColor("#9EEC5B"))
        paint3 = Paint()
        paint3.isAntiAlias
        paint3.isFilterBitmap
        paint3.strokeWidth = 2f
        paint3.color = Color. BLACK

    }
}


class DmgDisplay (var indexx: Enemy, var dmgReceived: String, var dmgCount: Int, var dmgCountPosition: Int, var paint: Paint, var positionX: Int, var positionY: Int){

    var displayDmgDelete = false
}