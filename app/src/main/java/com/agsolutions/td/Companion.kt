package com.agsolutions.td

import com.agsolutions.td.Main.HighscoreAtributes
import java.io.Serializable
import java.util.concurrent.locks.ReentrantReadWriteLock

class Companion: Serializable {
    companion object {
        var mapPick = 0
        var mapMode = 1
        var userLevel = 0
        var itemPick = 0
        var itemList = mutableListOf<Items>(Items(2000, 0, 999,0,0f, 0, 0f, 0, "earth", R.drawable.talentsearth, R.drawable.overlaytransparent,10f, 0.0f,0.0f,45f, 1f, 2f, 0, "Enables Earth Talent", 0.0f, "", 0f))
        var globalItemListBag = mutableListOf<Items>()
        var build = false
        var buildListBag = mutableListOf<Items>()
        var towerList = mutableListOf<Tower>()
        var itemListBagInserter = mutableListOf<Items>()
        var dropItemList = 0
        var insertItem = 0
        var insertItemBag = 0
        var overallXp = 0.0f
        var xp = 0.0f
        var enemyList = mutableListOf<Enemy>()
        var shootList = mutableListOf<Shoot>()
        var shootReserve = 0
        var shootButterflyReserve = 0
        var radioList = mutableListOf("Evil News:")
        var radioAdd = 0
        var itemFragmentEnemyList = mutableListOf<ItemFragmentStrings>()
        var itemListStartItems = mutableListOf<ItemFragmentStrings>()
        var menuItemItems = mutableListOf<ItemFragmentStrings>()
        var diamonds = 1
        var upgradePoints  = 1



        var enemiesKilled = 0
        var secretShopBool = true
        var itemPoints = 0f
        var mysteryPoints = 0
        var isSingleTarget = true
        var evadeGlobal = 0f
        var gameSpeedAdjuster = 1f

        var wasOffline = false
        var wasOfflineHighscore = mutableListOf<HighscoreAtributes>()
        var levelZero = false

        var timer = 0
        var timerEliteMob = 0
        var dmgDone = 0.0f

        var autoAttackDmg = 0.0f
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
        var antiHeal = 0f
        var dmgImmune = 0.01f
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

        var towerCount = 0

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
        var poisonTowerCount = 0
        var poisonTowerHighestDmg = Tower(0f, 0f, 0f, 0f)
        var wizardTowerCount = 0
        var wizardTowerHighestDmg = Tower(0f, 0f, 0f, 0f)
        var iceTowerCount = 0

        // Talents ---------------------------
        var talentPoints = 60

        // Fire Talents-------------------------

        var bonusCritFire = 0.0f
        var globalMultiCrit = 1
        var fireUltimateMulticritBonus = 0
        var fireBurnTalent = 0
        var sunburn = 0
        var fireTalentBonusCrit = 0f
        var fireTalentBonusCritDmg = 0f

        // Ice Talents-------------------------
        var slowEach = 0.0f
        var slowExtra = 0.0f
        var slowExtraChance = 0
        var iceExtraDmg = 0.0f
        var slowAura = 0.0f
        var iceShardCounter = 0
        var iceShardCounter2 = 0
        var iceShardCounter4 = 0
        var iceShardSpeed = 0
        var shardSpeed: Float = 3.0F
        var shardStart = TowerRadius (600.0f, 750.0f, 5.0f)
        var shootListIce = mutableListOf<ShootIceTalent>()
        var iceShardCounter3 = 0
        var iceNova = false
        var iceNovaTimer = 0


        // Poison Talents-------------------------
        var stackablePoison = 0.0f
        var poisonCloud = 0
        var poisonCloudCounter = 0
        var poisonCloudPlaceholderCounter = 0
        var shootListPoison = mutableListOf<ShootPoisonTalent>()
        var entangle = 0
        var poisonOverload = 0
        var poisonTalentPest = 0f
        var poisonPestCount = 0
        var globalBonusSpellDmgPercent = 0.0f

        // Wind Talents-------------------------
        var pushBack = 0.0f
        var bonusSpeedWindTalent = 0.0f
        var windTalentDebuff = 1.0f

        var tornadoRadius = 0f
        var tornadoCounter = 0
        var randomEnemyTornado = Enemy (0.0f,0.0f, 0.0f, 0.0f, 0.0f, 0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0)
        var randomEnemyTornadoBool = true
        var shootListTornado = mutableListOf<ShootTornadoTalent>()
        var tornadoPlaceholderCounter = 0
        var windUltimatePercent = 0f
        var rotateShotMulti = 0f
        var rotateTornado = 0f
        var windTalentBonusSpeed = 0f
        var globalBonusTowerRange = 0
        var windTowerBonusTowerRange = 0
        var windBonusDamageMultiplyer = 0f
        var talentMultishot = false

        // Earth Talents-------------------------
        var splashRange = 0.0f
        var earthTalentBonusHitChance = 0f
        var earthTalentBonusPhysicalDmg = 0f
        var throwBoulder = 0.0f

        var throwBoulderTimer = 0
        var earthTalentPhyDmgMultiplier = 1.0f
        var earthArmorSmasher = 1.0f

        // Dark Talents---------------------------
        var darkPermaKill = 0.0f
        var instaKill = 0.0f
        var slowExtraDark = 0.0f
        var slowExtraChanceDark = 0
        var darkMagicDmgPercent = 0.0f
        var fearDuration = 0.0f

        var fearTimer = 0
        var darkTalentFear = false
        var darkTalentLaser = 0f


        var dropItemDarkUltimate = 0

        // Utils -----------------------------------
        var itemChance = 17.5f
        var globalBonusItemChance = 0f
        var globalBonusItemQuality = 0f
        var additionalUpgrade = 0
        var additionalDia = 0


        // Moon Talents---------------------------

        var dayNightHour = 12
        var dayNightMinute = 0
        var dayNightCounter = 0
        var dayNightVariable = 8
        var day = true
        var damageMultiplyerNight = 1f
        var endlessNight = 0
        var endlessNightActive = false
        var endlessNightActiveCounter = 0
        var endlessNightButtonCounter = 0
        var endlessNightBool = true
        var moonLight = 0

        var shotBounceTargets = 1
        var shotBounce = false
        var moonTalentItemCost = 0f
        var moonTalentEvadeNight = false
        var rotateShotBounce = 0f

        // Butterfly Talents---------------------------

        var singleTargetBoost = false
        var singleTargetMultiplyer = 1f
        var markOfTheButterfly = 1f
        var markOfTheButterflySpdBoost = 0f
        var markOfTheButterflySlow = false
        var markOfTheButterflyDmgBoostActive = false
        var markOfTheButterflySpdBoostActive = false
        var markOfTheButterflySpdBoostActiveCounter = 0
        var markOfTheButterflySpdBoostActiveNumber = 0f
        var markOfTheButterflyExtraShot = false
        var markOfTheButterflyExtraShotDmg = 0f


        // Wizard Talents---------------------------

        var wizardBomb = false

        var wizardBombTimer = 0
        var wizardBombDmg = 0f
        var wizardMagicArmorSmasher = 1f
        var spellCastCD = 0f
        var spellCastCDCounter = 0
        var spellCastCDCounterHeap = 0
        var spellCastCDBool = false
        var wizardMissedLightning = false

        var wizardMissedLightningDmgBoost = 1f
        var wizardMine = false
        var wizardMineCounter = 0
        var wizardMineTimer = 0f
       var wizardMinePlaced = 0
        var shootListMine = mutableListOf<ShootMineTalent>()
        var minePlaceholderCounter = 0
        var chainLighning = false
        var chainLightningBounceTargets = 1
        var chainLightningDmg = 0f
        var chainLightningCounter= 0
        var chainLightningTimer= 0f
        var chainLightningBonusDmg = 0f
        var wizardBombStartItemDmg = 0f
        var wizardLightningStartItemTargets = 0


        // Items -----------------------------------




        var itemLassoCount = 0





        var itemSniperPro = false

        var randomEnemyForSniper = Enemy (0.0f,0.0f, 0.0f, 0.0f, 0.0f, 0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0)
        var particleDmg = 0f
        var particleDmgBool = false
        var itemFastDraw = false
        var itemPiggyBank = 1f
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
        var talentsGain = false
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
        var itemStartPoison = false
        var itemStartTalentPoints = false
        var itemDarkUltimate = false
        var itemStartBounce = false
        var upgraderBool = false

        // onTouch ---------------------------------------------
        var towerClick = false
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

        // startMenu ----------------------------------------------------
        var enemyCount = 0

        var startScreenTimerTower = 0
        var startScreenTowerBool = true

        // fragments ----------------------------------------------------
        var fragmentItemCounter = 0
        var fragmentItemCurrentItem = 0
        var scrollOldPositionX = 0
        var scrollOldPositionY = 0

    }
}