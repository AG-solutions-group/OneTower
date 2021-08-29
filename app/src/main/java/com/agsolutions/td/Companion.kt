package com.agsolutions.td

import android.graphics.Rect
import com.agsolutions.td.Main.HighscoreAtributes
import java.io.Serializable
import java.util.concurrent.locks.ReentrantReadWriteLock

class Companion: Serializable {
    companion object {
        var mapPick = 0
        var mapMode = 1
        var userLevel = 0
        var itemPick = 0
        var itemList = mutableListOf<Items>()
        var globalItemListBag = mutableListOf<Items>()
        var build = false
        var buildListBag = mutableListOf<Items>()
        var towerList = mutableListOf<Tower>()
        var itemListBagInserter = mutableListOf<Items>()
        var itemListInserter = mutableListOf<Items>()
        var dropItemList = 0
        var insertItem = 0
        var insertItemBag = 0
        var overallXp = 0.0f
        var enemyList = mutableListOf<Enemy>()
        var shootList = mutableListOf<Shoot>()
        var shootReserve = 0
        var shootButterflyReserve = 0
        var radioList = mutableListOf("Evil News:")
        var radioAdd = 0
        var itemFragmentEnemyList = mutableListOf<ItemFragmentStrings>()
        var itemListStartItems = mutableListOf<ItemFragmentStrings>()
        var menuItemItems = mutableListOf<ItemFragmentStrings>()



        // tutorial
        var enemiesKilled = 0

        // timer spawn on lvl up
        var timer = 0
        var timerEliteMob = 0


        var secretShopBool = true
        var itemPoints = 0f
        var mysteryPoints = 0
        var gameSpeedAdjuster = 1f

        var wasOffline = false
        var wasOfflineHighscore = mutableListOf<HighscoreAtributes>()
        var levelZero = false


        var overallSpdMultiplier = 0f
        var globalDmgMultiplier = 0f



        var level = 0
        var lives = 8
        var livesMode2 = 30
        var end = 0
        var livesItem = 0
        var levelCountBool = true
        var levelCountSecondBool = true

        var globalArmorPen = 0f
        var globalMagicPen = 0f

        var evadeNight = 0f


        var hpRegDebuffGlobal = 0f
        var scaleScreen = 0f
        var scaleTextMain = 9f
        var scaleTextNews = 8f
        var scaleTextStats = 8f
        var scaleTextButton = 8f
        var scaleTextBig = 12f
        var screenDensity = 0f
        var screenDense = 0f
        var screenDensityHeight = 0
        var screenDensityWidth = 0

        var manaShieldBool = false
        var shieldBool = false
        var allShieldsBool = false
        var enemySizeBoss = 0
        var bigNumberScaler = 1f
        var highscore = 0
        var personalHighscore = 0

        var refresh = false

        var test = 0
        var test2 = 0



        // lvl ------------------------------

        var lvlHp = 15.0f
        var lvlArmor = 0.0f
        var lvlMagicArmor = 0.0f
        var lvlEvade = 0.0f
        var lvlHpReg = 1.0f
        var lvlXp = 1.0f
        var lvlXpConstant = 1.5f
        var lvlSpd = 3.0f
        var interest = 1.03f

        var enemyHp = 15f
        var enemyMaxHp = 15f
        var enemyShield = 0f
        var enemyShieldMax = 0f
        var enemyManaShield = 0f
        var enemyManaShieldMax = 0f
        var enemyArmor = 1.0f
        var enemyArmorReduction = 0f
        var enemyMagicArmor = 1.0f
        var enemyMagicArmorReduction = 0f
        var enemyEvade = 1.0f
        var enemyHpReg = 1.0f
        var enemySpd = 3.0f
        var enemyType = "undef"
        var healCounter = 0
        var tankBool = true

        var levelScalerCrit = 1f

        var levelScalerSpeed = 1f
        var levelScalerSpeedBool = 0f
        var levelScalerCritDmg = 1f

        var levelScalerItemChance = 1f
        var levelScalerItemQuality = 1f



        // locks ------------------------------
        var enemyLock = ReentrantReadWriteLock ()
        var readLockEnemy = enemyLock.readLock()
        var writeLockEnemy = enemyLock.writeLock()
        var shotLock = ReentrantReadWriteLock ()
        var readLockShot = shotLock.readLock()
        var writeLockShot = shotLock.writeLock()
        var shotIceLock = ReentrantReadWriteLock ()
        var readLockIce = shotIceLock.readLock()
        var writeLockIce = shotIceLock.writeLock()
        var shotPoisonLock = ReentrantReadWriteLock ()
        var readLockPoison = shotPoisonLock.readLock()
        var writeLockPoison = shotPoisonLock.writeLock()
        var shotMultiLock = ReentrantReadWriteLock ()
        var readLockMulti = shotMultiLock.readLock()
        var writeLockMulti = shotMultiLock.writeLock()
        var shotMineLock = ReentrantReadWriteLock ()
        var readLockMine = shotMineLock.readLock()
        var writeLockMine = shotMineLock.writeLock()
        var shotTornadoLock = ReentrantReadWriteLock ()
        var readLockTornado = shotTornadoLock.readLock()
        var writeLockTornado = shotTornadoLock.writeLock()
        var displayListLock = ReentrantReadWriteLock ()
        var readLockDisplay = displayListLock.readLock()
        var writeLockDisplay = displayListLock.writeLock()
        var towerLock = ReentrantReadWriteLock ()
        var readLockTower = towerLock.readLock()
        var writeLockTower = towerLock.writeLock()

        // new level----------------------------

        var levelCount = 600
        var levelCountPlace = 1140
        var enemySpawned = 0
        var levelStatus = "undef"
        var levelList = mutableListOf<String>("normal")
        var levelListReserve = mutableListOf<String>("armor", "evade", "magic armor", "shortcut")
        var scaler = 1f

        // tower
        var towerCount = 0
        var poisonTowerCount = 0
        var poisonTowerHighestDmg = Tower(0f,0f, 0f, 0f, 0f, 0f)
        var wizardTowerCount = 0
        var wizardTowerHighestDmg = Tower(0f,0f, 0f, 0f, 0f, 0f)
        var iceTowerCount = 0
        var fireTowerCount = 0
        var moonTowerCount = 0
        var windTowerCount = 0
        var utilsTowerCount = 0
        var darkTowerCount = 0
        var butterflyTowerCount = 0
        var earthTowerCount = 0

        // Testing ---------------------------

        var gold = 20.0f
        var diamonds = 1
        var upgradePoints  = 1

        // Ice Talents-------------------------
        var iceShard = false
        var iceShardCounter = 0
        var iceShardCounter2 = 0
        var iceShardCounter4 = 0
        var iceShardSpeed = 150
        var shardSpeed: Float = 3.0F
        var shardStart = TowerRadius (600.0f, 750.0f, 5.0f)
        var shootListIce = mutableListOf<ShootIceTalent>()
        var iceShardCounter3 = 0
        var iceShardTowerId = 0

        // Poison Talents-------------------------

        var poisonCloud = 0
        var poisonCloudCounter = 0
        var poisonCloudPlaceholderCounter = 0
        var shootListPoison = mutableListOf<ShootPoisonTalent>()


        var poisonTalentPest = 0f
        var poisonPestCount = 0
        var globalBonusSpellDmgPercent = 0.0f

        // Wind Talents-------------------------






        var shootListTornado = mutableListOf<ShootTornadoTalent>()
        var tornadoPlaceholderCounter = 0

        var rotateShotMulti = 0f
        var rotateTornado = 0f
        var windTalentBonusSpeed = 0f
        var globalBonusTowerRange = 0



        // Earth Talents-------------------------


        var earthTalentBonusPhysicalDmg = 0f


        // Dark Talents---------------------------

        var instaKill = 1.5f
        var dropItemDarkUltimate = 0

        // Utils -----------------------------------
        var itemChance = 20.0f
        var globalBonusItemChance = 0f
        var globalBonusItemQuality = 0f



        // Moon Talents---------------------------

        var dayNightHour = 12
        var dayNightMinute = 0
        var dayNightCounter = 0
        var dayNightVariable = 8
        var day = true

        var endlessNight = 0
        var endlessNightActive = false
        var endlessNightActiveCounter = 0
        var endlessNightButtonCounter = 0
        var endlessNightBool = true



        var moonTalentItemCost = 0f
        var moonTalentEvadeNight = false
        var rotateShotBounce = 0f

        // Wizard Talents---------------------------



        var wizardMagicArmorSmasher = 1f



        var wizardMine = false
        var wizardMineCounter = 0
        var wizardMineTimer = 500f
       var wizardMinePlaced = 0
        var shootListMine = mutableListOf<ShootMineTalent>()
        var minePlaceholderCounter = 0

        var wizardBombStartItemDmg = 0f
        var wizardLightningStartItemTargets = 0


        // Items -----------------------------------




        var itemLassoCount = 0





        var itemSniperPro = false

        var randomEnemyForSniper = Enemy (0.0f,0.0f, 0.0f, 0.0f, 0.0f, 0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0)

        var itemFastDraw = false

        var itemBoring = false
        var itemDetonate = false
        var itemUsed = false


        var lvlScaler = lvlXp * 1.0f
        var lvlScalerFirst = 1.0f
        var lvlScalerSecond = 1.0f
        var costBase = 15f
        var costBlue = costBase * 2.0f
        var costEpic = costBase * 3.0f
        var costLegendary = costBase * 5.0f
        var costDia: Short = 1

        // stats gain -----------------------------------
        var goldGain = false
        var goldGainCount = 0
        var diamondsGain = false
        var diamondsGainCount = 0

        var talentsGainCount = 0
        var talentGainCountRed = 0
        var interestGain = false
        var interestGainCount = 0
        var upgradePointsGain = false
        var upgradePointsGainCount = 0
        var itemPointsGain = false
        var itemPointsGainCount = 0
        var itemChanceGain = false
        var itemChanceGainCount = 0
        var itemQualityGain = false
        var itemQualityGainCount = 0
        var bagSizeGain = false
        var bagSizeGainCount = 0
        var damageGain = false
        var damageGainCount = 0
        var phyDamageGain = false
        var phyDamageGainCount = 0
        var mgcDamageGain = false
        var mgcDamageGainCount = 0
        var spdGain = false
        var spdGainCount = 0
        var critChanceGain = false
        var critChanceGainCount = 0
        var critDamageGain = false
        var critDamageGainCount = 0
        var multiCritGain = false
        var multiCritGainCount = 0
        var hitGain = false
        var hitGainCount = 0
        var armorPenGain = false
        var armorPenGainCount = 0
        var magicArmorPenGain = false
        var magicArmorPenGainCount = 0

        // bag inside ---------------------------------
        var magicBoxCount = 0
        var maceCount = 0
        var bowCount = 0
        var swordCount = 0
        var luckyCharmCount = 0

        // mystery points ------------------------------
        var mysteryAllRounderBool = true
        var mysterySongBool = true
        var mysteryClownBool = true
        var mysteryMaceBool = true
        var mysteryBowBool = true
        var mysterySwordBool = true
        var mysteryLuckyCharmBool = true
        var mysteryPirateHunterBool = true
        var itemListSecretShop = mutableListOf<ItemFragmentStrings>()
        var pirateItemCount = 0
        var livesMpCounter = false
        var bossesKilled = 0
        var bossesLeaked = 0
        var challengesKilled = 0
        var challengesKilledBool = true
        var mysteryBombsUsedBool = true
        var mysteryColdHeartBool = true
        var mysteryWokeBool = true
        var mysteryWokeCount = 0


        // midnight madness ---------------------------------
        var itemListMidnightMadness = mutableListOf<ItemFragmentStrings>()
        var itemListBagMysteryEventCancelled = mutableListOf<Items>()
        var midnightMadnessSaveList = mutableListOf<String>()
        var midnightMadnessDisableItems = false
        var midnightMadnessMidas = false
        var midnightMadnessMidasGold = 1f
        var midnightMadnessMidasGoldCost = 0f
        var midnightMadnessWind = false
        var midnightMadnessWindLvlSpeedReduce = 0f
        var midnightMadnessExtraSpawnBool = false
        var midnightMadnessExtraSpawn = 0
        var midnightMadnessEvent = ""
        var midnightMadnessEventRadio = ""


        // sound ------------------------------------------------
        var logVolume = 0f
        var logVolumeEffects = 0f
        var soundBoolDay = true
        var soundBoolNight = true
        var globalSoundMusic = 30f
        var globalSoundEffects = 30f
        var maxVolume = 100.0f

        // animations ------------------------------------------------
        var rotationTowerX = 0f
        var rotationTowerY = 0f
        var rotationBulletX = 0f
        var rotationBulletY = 0f
        var rotationEnemyX = 0f
        var rotationEnemyY = 0f
        var skeletonCount = 0


        // shoot
        var advancedList4 = mutableListOf<Enemy>()
        var advancedList3 = mutableListOf<Enemy>()
        var advancedList2 = mutableListOf<Enemy>()
        var advancedList1 = mutableListOf<Enemy>()

        // active ability ------------------------------------------------
        var activeAbilityList = mutableListOf<ActiveAbility>()
        var insertSpell = 0
        var bombActiveAbility = 0
        var activeAbilityPlace = 0
        var insertSpellBomb = 0
        var bombsUsedCounter = 0
        var bombDamage = 0f
        var bombCost = false
        var activeAbilityHelpingHandBool = true
        var activeAbilityHelpingHandButtonCounter = 0
        var activeAbilityHelpingHandActiveCounter = 0

        // placeholder ---------------------------------------------
        var placeholder = 0f

        // start items ---------------------------------------------
        var iceNovaTimerStartItem = 0
        var shieldBrakerItem = 1



        var itemStartBounce = false


        // onTouch ---------------------------------------------
        var towerClick = false
        var towerClickID = 0
        var towerClickBool = false
        var buildClickBool = false
        var enemyClick = false
        var enemySelectedBool = false
        var spawnDoubleClick = false
        var spawnDoubleClickCounter = 0
        var spawnEnemy = false
        var autoSpawn = false
        var autoSpawnCount = 0
        var autoSpawnCountBool = true
        var touchCount = 0
        var touchCountCounter = 0
        var toastGlobal = false
        var toastText = ""
        var hintItemSwipe = false
        var towerStatsClick = false
        var uiRefreshCounter = 0
        var focusMainWindow = true
        var focusTalentWindow = false
        var earthFragmentFocus = false
        var invalidate = 0
        var focusEarthFragment = false
        var showHelpTalent = true

        // menu ----------------------------------------
        var saveGame = false
        var continueGame = false
        var hintsBool = true

        // tutorial -----------------------------------
        var tutorialFirstUseItemBool = true
        var tutorialFirstUseItemCounter = 0

        // display ----------------------------------------
        var dmgDisplayList = mutableListOf<DmgDisplay>()
        var dragRect = Rect(0, 0, 0, 0)
        var dragRectList = mutableListOf<Rect>(
            Rect(0, 0, 1600, 300),
            Rect(0, 1300, 1600, 1600),

            Rect(200, 950, 950, 1100),
            Rect(200, 475, 850, 625),
            Rect(900, 950, 1000, 1250),
            Rect(375, 475, 525, 850),
            Rect(200, 475, 350, 1100),
            Rect(750, 475, 875, 1250)
        )
        var dragStatusDrag = false
        var dragStatusCantBuild = false

        // startMenu ----------------------------------------------------
        var enemyCount = 0

        var startScreenTimerTower = 0
        var startScreenTowerBool = true

        // fragments ----------------------------------------------------
        var fragmentItemCounter = 0
        var fragmentItemCurrentItem = 0


    }
}