package com.agsolutions.td

import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class Tower(var dmg: Float, var phyDmg: Float, var mgcDmg: Float, var speed: Float, var crit: Float, var critDmg: Float) {

    var towerRange = TowerRadius (600.0f, 750.0f, 300.0f)
    var towerR = 0f
    var towerBonusRange = 0f
    var towerRarity = "grey"
    var towerRarityMultiplier = 0f
    var paint: Paint
    var paint2 : Paint
    var paint3 : Paint
    var selected = false
    var towerPrimaryElement = "none"
    var isSingleTarget = true
    var towerLevelBool = false

    var particleDmg = 0f
    var particleDmgBool = false

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

    var bonusmultiCrit = 1
    var overallMulticrit = 0
    var bonusDamageMultiplyer = 0.0f
    var overallDamageMultiplyer = 1.0f
    var overallTowerDmg = 0.0f
    var bonusTowerDmg = 0.0f
    var overallTowerDmgBool = 0f
    var bonusHitChance = 0.0f
    var overallTowerPhysicalDmg = 0.0f
    var bonusPhysicalDmg = 0.0f
    var overallTowerSpellDmg = 0.0f
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
    var overallDmgImmune = 0.01f
    var bonusDmgImmune = 0f
    var bonusAntiHeal = 0f

    var bagSize = 4 // 5
    var bagSizeElement = 0 // 1
    var bagSizeElementCount = 0

    var randomEnemyForShot = Enemy (0.0f,0.0f, 0.0f, 0.0f, 0.0f, 0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0)
    var randomEnemyForShotBool = true
    var randomEnemyForShotSticky = true
    var firstLastRandom = 0
    var towerFallingCount = 50
    var towerTowerNextPic = 0
    var towerTowerNextPicPlace = 0


    // talent
    var talentPoints = 1
    var xpTower = 0f
    var xpGoal1 = 0f
    var xpGoal2 = 8f
    var towerLevel = 1

    // ice
    var iceNovaCounter = 0

    // earth
    var throwBoulderCounter = 0

    // poison
    var poisonOverloadActive = false
    var poisonOverloadCounter = 0
    var poisonOverloadDuration = 0

    // wind
    var bonusSpeedWindTalentPercent = 0.0f

    // fire
    var critCounter = 0

    // wizard
    var wizardMissedLightningActive = false
    var wizardBombCounter = 0



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
    var itemPiggyBank = 1f

    var itemStartPoison = false

    // xp

    var experienceKill = 0f
    var experienceButterflyPop = 0f
    var experienceLvl = false
    var experienceEarthHit = false
    var experienceFireCrit = false
    var talentFireKill = false
    var experienceSlow = false
    var experiencePoisonKill = false
    var experienceGainUtilsAura = false
    var experienceShareUtilsAura = false
    var experienceEachHit = false
    var experienceDrop = false
    var experienceCast = false
    var talentWizardLvlToDmg = false
    var experienceMoonlight = false

    //talents
    //butterfly
    var butterflyRow1Item1 = 0
    var butterflyRow1Item2 = 0
    var butterflyRow2Item1 = 0
    var butterflyRow2Item2 = 0
    var butterflyRow3Item1 = 0
    var butterflyRow3Item2 = 0
    var butterflyRow4Item1 = 0
    var markOfTheButterfly = 1.5f
    var markOfTheButterflySlow = false
    var markOfTheButterflySpdBoost = 0f
    var markOfTheButterflyExtraShot = false
    var markOfTheButterflyExtraShotDmg = 0f
    var markOfTheButterflyDmgBoostActive = false
    var markOfTheButterflySpdBoostActive = false
    var markOfTheButterflySpdBoostActiveCounter = 0
    var markOfTheButterflySpdBoostActiveNumber = 0f
    var markOfTheButterflySpdBoostTowersNumber = 0
    var butterflyDebuffEnemyDmg = 0f
    var butterflyDebuffEnemyGoldXp = 0f
    var markOfTheButterflyEnemyId = 0
    var markofTheBitterflyColor = Color.argb(255, Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255))

    // dark
    var darkRow1Item1 = 0
    var darkRow1Item2 = 0
    var darkRow2Item1 = 0
    var darkRow2Item2 = 0
    var darkRow3Item1 = 0
    var darkRow3Item2 = 0
    var darkRow3Item3 = 0
    var darkRow4Item1 = 0
    var darkTalentFear = false
    var fearDuration = 0.0f
    var fearCounter = 0
    var darkTalentLaserCount = 0
    var darkTalentLaserAlreadyAffectedCount = 0
    var fearTimer = 0
    var slowExtraDark = 0.0f
    var slowExtraChanceDark = 0
    var darkMagicDmgPercent = 0.0f
    var darkTalentLaser = 0f
    var darkDmgDebuff = 0f
    var darkPermaKill = 0.0f

    // earth
    var earthRow1Item1 = 0
    var earthRow1Item2 = 0
    var earthRow2Item1 = 0
    var earthRow2Item2 = 0
    var earthRow3Item1 = 0
    var earthRow3Item2 = 0
    var earthRow3Item3 = 0
    var earthRow4Item1 = 0
    var earthExtraTowerDmg = 0.10f
    var splashRange = 100.0f
    var earthTalentBonusHitChance = 0f
    var throwBoulder = 0.0f
    var throwBoulderTimer = 0
    var earthTalentPhyDmgMultiplier = 1.0f

    // fire
    var fireRow1Item1 = 0
    var fireRow1Item2 = 0
    var fireRow2Item1 = 0
    var fireRow2Item2 = 0
    var fireRow3Item1 = 0
    var fireRow3Item2 = 0
    var fireRow4Item1 = 0
    var sunburn = 0
    var fireTalentBonusCritDmg = 0f
    var fireBurnTalentDmg = 0.004f
    var fireUltimateMulticritBonus = 0
    var bonusCritFire = 0.0f

    //ice
    var iceRow1Item1 = 0
    var iceRow1Item2 = 0
    var iceRow2Item1 = 0
    var iceRow2Item2 = 0
    var iceRow2Item3 = 0
    var iceRow3Item1 = 0
    var iceRow3Item2 = 0
    var iceRow4Item1 = 0
    var slowEach = 0.0f
    var iceNovaSpellDmg = 0f
    var iceNovaTimer = 270
    var iceExtraDmg = 0.0f
    var slowExtra = 0.0f
    var slowExtraChance = 0
    var slowAura = 0.0f

    // moon
    var moonRow1Item1 = 0
    var moonRow1Item2 = 0
    var moonRow2Item1 = 0
    var moonRow2Item2 = 0
    var moonRow3Item1 = 0
    var moonRow3Item2 = 0
    var moonRow4Item1 = 0
    var moonLightCount = 0
    var moonLight = 0
    var shotBounceTargets = 2
    var damageMultiplyerNight = 1f

    // poison
    var poisonRow1Item1 = 0
    var poisonRow1Item2 = 0
    var poisonRow2Item1 = 0
    var poisonRow2Item2 = 0
    var poisonRow3Item1 = 0
    var poisonRow3Item2 = 0
    var poisonRow4Item1 = 0
    var stackablePoison = 0.1f
    var poisonOverload = 0
    var entangle = 0

    // utils
    var utilsRow1Item1 = 0
    var utilsRow1Item2 = 0
    var utilsRow2Item1 = 0
    var utilsRow2Item2 = 0
    var utilsRow3Item1 = 0
    var utilsRow3Item2 = 0
    var utilsRow4Item1 = 0
    var itemQualityAura = 0f
    var itemChanceAura = 0f

    // wind
    var windRow1Item1 = 0
    var windRow1Item2 = 0
    var windRow2Item1 = 0
    var windRow2Item2 = 0
    var windRow3Item1 = 0
    var windRow3Item2 = 0
    var windRow3Item3 = 0
    var windRow4Item1 = 0
    var windExtraTowerSpd = 10f
    var windTalentDebuff = 1.0f
    var windTowerBonusTowerRange = 0
    var pushBack = 0.0f
    var tornadoRadius = 0f
    var tornadoCounter = 0
    var bonusSpeedWindTalent = 0.0f
    var windUltimatePercent = 0f

    // wizard
    var wizardRow1Item1 = 0
    var wizardRow1Item2 = 0
    var wizardRow2Item1 = 0
    var wizardRow2Item2 = 0
    var wizardRow2Item3 = 0
    var wizardRow3Item1 = 0
    var wizardRow3Item2 = 0
    var wizardRow4Item1 = 0
    var wizardMissedLightning = false
    var spellCastCD = 0f
    var spellCastCDCounter = 0
    var spellCastCDCounterHeap = 0
    var spellCastCDBool = false
    var wizardMissedLightningDmgBoost = 1f

    var wizardBombTimer = 360
    var wizardBombDmg = 10.0f
    var chainLighning = false
    var chainLightningBounceTargets = 1
    var chainLightningDmg = 0f
    var chainLightningCounter= 0
    var chainLightningTimer= 0f
    var chainLightningBonusDmg = 0f

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