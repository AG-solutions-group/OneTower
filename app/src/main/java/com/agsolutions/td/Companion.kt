package com.agsolutions.td

import android.graphics.Rect
import java.io.Serializable
import java.util.concurrent.locks.ReentrantReadWriteLock

class Companion: Serializable {
        var mapPick = 0
        var mapMode = 1
        var userLevel = 0
        var itemPick = 0
        var itemList = mutableListOf<Items>()

        var build = false
        var buildListBag = mutableListOf<Items>()
        var towerList = mutableListOf<Tower>()
        var itemListBagInserter = mutableListOf<Items>()
        var itemListInserter = mutableListOf<Items>()
        var itemListInsertItem = mutableListOf<Items>()
        var dropItemList = 0
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

        var overallSpdMultiplier = 0f
        var globalDmgMultiplier = 0f

        var itemBeingMoved = false
        var itemBeingMovedSave = 0
        var itemBeingMovedSaveList = mutableListOf<Enemy>()
        var enemyListDeadSplit = mutableListOf<Enemy>()

        var enemyRemoveList = mutableListOf<Enemy>()

        var level = 0
        var lives = 8
        var livesMode2 = 30
        var end = 0

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


        // lvl ------------------------------

        var lvlHp = 15.0f
        var lvlArmor = 1.0f
        var lvlMagicArmor = 1.0f
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
        var disruptorCounter = 0
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
        var displayDropListLock = ReentrantReadWriteLock ()
        var readLockDisplayDrop = displayDropListLock.readLock()
        var writeLockDisplayDrop = displayDropListLock.writeLock()
        var towerLock = ReentrantReadWriteLock ()
        var readLockTower = towerLock.readLock()
        var writeLockTower = towerLock.writeLock()
        var itemBagLock = ReentrantReadWriteLock ()
        var readLockItemBag = itemBagLock.readLock()
        var writeLockItemBag = itemBagLock.writeLock()

        // new level----------------------------

        var levelCount = 600
        var levelCountPlace = 1140
        var enemySpawned = 0
        var enemySpawnedCount = 8
        var levelStatus = "undef"
        var levelList = mutableListOf<String>("normal", "armor", "magic armor")
        var levelListReserve = mutableListOf<String>("evade")


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

        // Poison Talents-------------------------

        var poisonCloud = 0
        var poisonCloudCounter = 0
        var poisonCloudPlaceholderCounter = 0
        var shootListPoison = mutableListOf<ShootPoisonTalent>()
        var enemiesPoisoned = 0

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
        var tornadoTimer = 1000
        var tornadoRadius = 0f
        var tornadoCounter = 0

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
    var addChainLighning = 0


        // Items -----------------------------------

        var lvlScaler = lvlXp * 1.5f
        var lvlScalerFirst = 1.0f
        var lvlScalerSecond = 1.0f
        var costBase = 12f
        var costGrey = costBase * 0.75f
        var costBlue = costBase * 1.5f
        var costEpic = costBase * 2.0f
        var costLegendary = costBase * 3.0f
        var costDia = 1

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
        var secretShopIconBool = true


        // sound ------------------------------------------------
        var logVolume = 0f
        var logVolumeEffects = 0f
        var soundBoolDay = true
        var soundBoolNight = true
        var globalSoundMusic = 30f
        var globalSoundEffects = 30f
        var maxVolume = 100.0f
    var musicChanged = false

        // animations ------------------------------------------------
        var rotationTowerX = 0f
        var rotationTowerY = 0f
        var rotationBulletX = 0f
        var rotationBulletY = 0f
        var rotationEnemyX = 0f
        var rotationEnemyY = 0f

        // shoot
        var advancedList5 = mutableListOf<Enemy>()
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
        var wiseMan = 1f

        var upgraderBool = false
        var itemStartBounce = false


        // onTouch ---------------------------------------------
        var towerClick = false
        var towerClickID = 0
        var towerClickBool = false
        var buildClickBool = false
        var enemyClick = false
        var enemySelectedBool = false
        var autoSpawnClick = false
        var spawnBool = true
        var spawnBoolCounter = 0

        var spawnEnemy = false
        var autoSpawn = false
        var autoSpawnCount = 0
        var touchCount = 0
        var clipRect = Rect()

        var toastGlobal = false
        var toastText = ""
        var hintItemSwipe = false
        var uiRefreshCounter = 0
        var focusMainWindow = true
        var focusTalentWindow = false
        var earthFragmentFocus = false
        var invalidate = 0
        var focusTalentFragment = false
        var showHelpTalent = true

        // menu ----------------------------------------
        var saveGame = false

        var hintsBool = true

        // tutorial -----------------------------------
        var tutorialFirstUseItemBool = true
        var tutorialFirstUseItemCounter = 0
        var tutorialFirstTowerLevelBool = true

        // display ----------------------------------------
        var dmgDisplayList = mutableListOf<DmgDisplay>()
        var dmgDisplayDropList = mutableListOf<DropDisplay>()
        var dragStatusDrag = false
        var dragStatusCantBuild = false
        var dragTower = Items(2100, 0, 999,0,0f, 0, 0f, 0, "Rare Earth Tower", R.drawable.elementearth, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var overrideTower = false
        var towerToOverride = Tower(0f, 0f, 0f, 0f, 0f, 0f)

        // fragments ----------------------------------------------------
        var fragmentItemCounter = 0
        var fragmentItemCurrentItem = 0

        // items ----------------------------------------------------

        var stt0 = Items(2100, 0, 999,0,0f, 0, 0f, 0, "Rare Earth Tower", R.drawable.elementearth, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt1 = Items(2101, 0, 999,0,0f, 0, 0f, 0, "Rare Wizard Tower", R.drawable.elementwizard, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt2 = Items(2102, 0, 999,0,0f, 0, 0f, 0, "Rare Ice Tower", R.drawable.elementice, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt3 = Items(2103, 0, 999,0,0f, 0, 0f, 0, "Rare Butterfly Tower", R.drawable.elementbutterfly, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt4 = Items(2104, 0, 999,0,0f, 0, 0f, 0, "Rare Poison Tower", R.drawable.elementpoison, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt5 = Items(2105, 0, 999,0,0f, 0, 0f, 0, "Rare Moon Tower", R.drawable.elementmoon, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt6 = Items(2106, 0, 999,0,0f, 0, 0f, 0, "Rare Wind Tower", R.drawable.elementwind, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt7 = Items(2107, 0, 999,0,0f, 0, 0f, 0, "Rare Utils Tower", R.drawable.elementutils, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0,"bagspace", 3.0f, "bagspace element", 2f)
        var stt8 = Items(2108, 0, 999,0,0f, 0, 0f, 0, "Rare Fire Tower", R.drawable.elementfire, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0,"bagspace", 3.0f, "bagspace element", 2f)
        var stt9 = Items(2109, 0, 999,0,0f, 0, 0f, 0, "Rare Dark Tower", R.drawable.elementdark, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)

        var startTowerList = mutableListOf<Items>(stt1, stt5, stt0, stt4, stt2, stt3, stt8, stt6, stt9, stt7)

        var itemListNormal = mutableListOf<Int>()
        var itemListRare = mutableListOf<Int>()
        var itemListEpic = mutableListOf<Int>()
        var itemListLegendary = mutableListOf<Int>()

        var stid0 = Items(5000, 0, 999,0,0f, 0,0f, 0,"Starter Dmg", R.drawable.wandswordgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0, "scaling dmg / lvl", 0f, "", 0f)
        var stid1 = Items(5001, 1, 999,0,0f, 0,0f, 0,"Starter Speed", R.drawable.pbowgrey, R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0, "scaling speed / lvl", 0f, "", 0f)
        var stid2 = Items(5002, 3, 999,0,0f, 0,0f, 0,"Starter Crit", R.drawable.pcritgrey, R.drawable.overlaytransparent, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0.0f,0, "scaling crit / lvl", 0f, "", 0f)
        var stid3 = Items(5003, 4, 999,0,0f, 0,0f, 0,"Starter Physical", R.drawable.pdoubleswordsblue, R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0, "+10% physical dmg", 0f, "scaling phyDmg/lvl", 0f)
        var stid4 = Items(5004, 5, 999,0,0f, 0,0f, 0,"Starter SpellDmg", R.drawable.zauberstabblue, R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"+10% magic dmg", 0f, "scaling mgcDmg/lvl", 0f)
        var stid5 = Items(5005, 6, 999,0,0f, 0,0f, 0,"Crit Master", R.drawable.pcritblue, R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"scaling crit dmg/lvl + crit/lvl", 0f, "+ 1 multi crit", 0f)
        var stid6 = Items(5006, 7, 999,0,0f, 0,0f, 0,"Froster", R.drawable.frostlila, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"scaling mgcDmg/lvl", 0f, "+ 10% slow, Ice Nova CD halved", 0f)
        var stid7 = Items(5007, 8, 999,0,0f, 0,0f, 0,"Utilizer", R.drawable.utilizerpurple, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"+ 1% interest, + 2 bag slots", 0f, "+10% item find", 0f)
        var stid8 = Items(5008, 9, 999,0,0f, 0,0f, 0,"Shield Braker", R.drawable.shieldbrakerpurple, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"ignores shields", 0f, "", 0f)

        var stid10 = Items(5010, 11, 999,0,0f, 0,0f, 0,"Easy Mode", R.drawable.itemstarteasymode, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"consumable: enemies start with 10% less hp", 0f, "", 0f)
        var stid11 = Items(5011, 12, 999,0,0f, 0, 0f, 0, "Helping Hand", R.drawable.helpinghandpurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "active: +30% DMG for 5 sec. 1 min CD.", 0.0f, "", 0f)
        var stid12 = Items(5012, 13, 999,0,0f, 0, 0f, 0, "BombermXn", R.drawable.bombpurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "start with 5 bombs", 0.0f, "bombs +10% dmg, bombs 50% cheaper", 0f)
        var stid13 = Items(5013, 14, 999,0,0f, 0, 0f, 0, "Wise", R.drawable.talentpurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "enemies drop 10% more XP", 0.0f, "", 0f)

        var stid15 = Items(5015, 15, 999,0,0f, 0, 0f, 0, "Wizard", R.drawable.wizardpueple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "Bombshell: +40% spelldmg scaling bomb", 0.0f, "Lightning Bolt: +1 target", 0f)
        var stid16 = Items(5016, 15, 999,0,0f, 0, 0f, 0, "Bouncer", R.drawable.bouncepurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "bounce targets doubled ", 0.0f, "", 0f)
        var stid17 = Items(5017, 15, 999,0,0f, 0, 0f, 0, "Upgrader", R.drawable.upgraderitem, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "all items 2x upgrade slots", 0.0f, "starts with 3 UPG points", 0f)

        var startItemList = mutableListOf<Items>(stid0)
        var startItemHiddenList = mutableListOf<Items>(stid1, stid2, stid3, stid4, stid5, stid6, stid7, stid8, stid10, stid11, stid12, stid13, stid15, stid16, stid17)
        var startItemListAll = mutableListOf<Items>(stid0, stid1, stid2, stid3, stid4, stid5, stid6, stid7, stid8, stid10, stid11, stid12, stid13, stid15, stid16, stid17)

        //------------------------------------------------------------------

        // normal items

        var id0 = Items(0, 1, 999,0,10f, 0, 0f, 0,"Sword", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,2.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
        var id1 = Items(1, 1, 999,0,10f, 0,0f, 0, "Bow", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,5.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
        var id2 = Items(2, 1, 999,0,10f, 0, 0f, 0,"Nunchucks", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f,0.0f,0.0f, 0.0f, 1.0f, 0.0f, 0, "", 0f, "", 0f)
        var id3 = Items(3, 1, 25,0,0f, 0,0f, 0, "Magic Box", R.drawable.ic_launcher_background, R.drawable.overlaytransparent,1.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "no use cost", 0f, "", 0f)
        var id4 = Items(4, 1, 999,0,(20f), 0, 0f, 0,"Sword", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, (3.0f), 0.0f, 0, "", 0f, "", 0f)
        var id5 = Items(5, 1, 999, 0, 0f, 0, 0f, 0,"Bomb", R.drawable.bombgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "Detonate on Remove: 20% dmg to all enemies", 0f, "", 0f)
        // id 6 = gold
        var id7 = Items(7, 1, 999, 0, 0f, 0, 0f, 0, "Upgrader", R.drawable.upgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: gain UP", 1f, "", 0f)
        // id8 = DIA green
        var id9 = Items(9, 1, 999, 0, 0f, 0, 0f, 0, "Experiencer", R.drawable.xpgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: gain xp", 0f, "", 0f)
        var id11 = Items(11, 1, 999, 0, 0f, 0, 0f, 0, "Lucky Charm", R.drawable.luckycharmgrey, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 1, "", 0f, "", 0f)

        // rare items

        var id100 = Items(100, 1, 999,0,30f, 0,0f, 0, "Rare Sword", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,3.0f, 0.0f,0.0f,0.0f, 1.0f, 0.0f, 1, "", 0f, "", 0f)
        var id101  = Items(101, 1, 25,0,0f, 0, 0f, 0,"Rare Magic Box", R.drawable.ic_launcher_background, R.drawable.overlaytransparent,1.5f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "no use cost", 0f, "", 0f)
        var id102  = Items(102, 1, 999,0,30f, 0,0f, 0, "Rare Bow", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,7.5f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
        var id103  = Items(103, 1, 999,0,30f, 0, 0f, 0,"Rare Bow", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,7.5f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
        var id104  = Items(104, 30, 999,0,(10f), 0, 0f, 0,"Lucky Charm", R.drawable.luckycharmblue, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "plus itemquality",  10.0f, "", 0f)
        var id106 = Items(106, 10, 999, 0, 0f, 0, 0f, 0,"Heart", R.drawable.luckycharmblue, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "+1 live", 1f, "", 0f)
        var id107 = Items(107, 10, 999, 0, 0f, 0, 0f, 0,"Number One", R.drawable.heartgreen, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "+1 talent point", 1f, "", 0f)
        // epic items

        var id200  = Items(200, 1,999, 0,30f, 1, 0f, 0,"Epic Sword", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,5.0f + (level * 0.25f), 0.0f,0.0f,0.0f, 1.0f + (level * 0.1f), 0.0f, 2, "", 0f, "", 0f)
        var id201  = Items(201, 1, 25,0,0f, 0, 0f, 0,"Epic Magic Box", R.drawable.ic_launcher_background, R.drawable.overlaytransparent,2.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 1, "no use cost", 0f, "", 0f)
        var id202  = Items(202, 1, 999,0,30f, 1, 0f, 0,"Epic Bow", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,11.25f + (level * 0.25f), 0.0f, 0.0f, 2, "", 0f, "", 0f)
        var id203  = Items(203, 1, 999,0,30f, 1, 0f, 0,"Epic Dagger", R.drawable.dagger, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 2.0f + (level * 0.3f), 0.05f, 2, "", 0f, "", 0f)
        var id204  = Items(204, 30, 999,0,(10f), 0, 0f, 0,"Lucky Charm", R.drawable.luckycharmblue, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "plus itemquality",  10.0f, "", 0f)
        var id205  = Items(205, 10, 999,0,(0f), 0, 0f, 0,"Lifeline", R.drawable.lifelineorange, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "+1 live/10 rounds ", 1f, "", 0f)
        var id206  = Items(206, 1, 30,0,(0f), 0,0f, 0, "Lifeline", R.drawable.talentorange, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "+2 talent points", 2f, "", 0f)
        var id207  = Items(207, 1, 30,0,(0f), 1, 0f, 0,"Beggar", R.drawable.batteryorange, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0f, 0.0f, 0.1f, 0, "+1 dmg/round", 1f, "", 0f)
        var id208  = Items(208, 30, 999,0,(0f), 0, 0f, 0,"Freezer", R.drawable.frostorange, R.drawable.overlaytransparent,0.0f,0.0f,0.0f, 0f, 0.0f, 0.0f, 0, "slows a random unit for 10%", 10.0f, "", 0f)
        var id209  = Items(209, 1, 50,0,(0f), 0, 0f, 0,"Coin", R.drawable.coininterest, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0f, 0.0f, 0.0f, 0, "+ 0.01 interest rate", 0.1f, "", 0f)
        var id210  = Items(210, 50, 999,0,(0f), 0, 0f, 0,"Lasso", R.drawable.lassoorange, R.drawable.overlaytransparent,0.0f,0.0f,0.0f, 0f, 0.0f, 0.0f, 0, "throws X lassos that stun", 1.0f, "", 0f)
        var id211  = Items(211, 30, 999,0,(0f), 0, 0f, 0,"20/20", R.drawable.critdmgorange, R.drawable.overlaytransparent,0f,0.0f,0.0f, 0f, ((2f*0) + (level * 0.015f)), 0.05f*0, 3, "", 0.0f, "", 0f)
        var id212  = Items(212, 40, 999,0,(0f), 0, 0f, 0,"One Punch Thingy", R.drawable.hitorange, R.drawable.overlaytransparent,((0.5f) + (level * 0.075f)), 0.0f,0.0f,0f, 0f, 0f, 3, "+ X hit", 1f+(0f), "", 0f)
        var id213  = Items(213, 25, 999,0,0f, 0, 0f, 0,"Last Stance", R.drawable.laststanceorange, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "+10% dmg/live lost", 10f, "", 0f)
        var id214  = Items(214, 1, 999,0,0f, 0,0f, 0, "Sniper", R.drawable.sniperorange, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "snipes a single target", 0.0f, "", 0f)
        var id215  = Items(215, 40, 999, 0, 0f, 0, 0f, 0,"Shredder", R.drawable.shredderorange, R.drawable.overlaytransparent, ((0.5f) + (level * 0.075f)),0.0f,0.0f, 0f, 0f, 0f, 3, "- X armor", (2f), "", 0f)
        var id216  = Items(216, 40, 999, 0, 0f, 0, 0f, 0,"Shield Braker", R.drawable.magicbrakerorange, R.drawable.overlaytransparent,((0.5f) + (level * 0.075f)),0.0f,0.0f, 0f, 0f, 0f, 3, "- X mgc armor", (2f), "", 0f)
        var id217  = Items(217, 1, 999, 0, 0f, 0, 0f, 0,"SnowmXn", R.drawable.snowmanorange, R.drawable.overlaytransparent,((0.5f) + (level * 0.05f)), 0.0f,0.0f,((0.5f) + (level * 0.05f)), ((0.5f) + (level * 0.05f)), 0f, 3, "+5% slow on hit", 5f, "", 0f)
        var id218 = Items(218, 20, 999, 0, 0f, 0, 0f, 0, "Brain Damage", R.drawable.braindamageorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+1% dmg/towerlvl", 0f, "", 0f)

    // legendary items

        var id300 = Items(300, 1, 999,0,50f, 1, 0f, 0,"Legendary Sword", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,7.0f + (level * 0.5f), 0.0f,0.0f,0.0f, 1.0f + (level * 0.2f), 0.0f, 4, "", 0f, "", 0f)
        var id301 = Items(301, 1, 25,0,0f, 0, 0f, 0,"Legendary Magic Box", R.drawable.ic_launcher_background,R.drawable.overlaytransparent, 3.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 2, "no use cost", 0f, "", 0f)
        var id302 = Items(302, 1, 999, 0,50f, 1,0f, 0, "Legendary Bow", R.drawable.pdoubleswordsgrey,R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,16.87f + (level * 0.5f), 0.0f, 0.0f, 4, "", 0f, "", 0f)
        var id303 = Items(303, 1, 999,0,100f, 1, 0f, 0,"Legendary Dagger", R.drawable.dagger, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 3.0f + (level * 0.5f), 0.1f, 2, "", 0f, "", 0f)
        var id304 = Items(304, 20, 100,0,0f, 1,0f, 0, "Frost Aura", R.drawable.wisp, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.1f, 0, "Slow aura +5%", 0f, "", 0f)
        var id305 = Items(305, 1, 999,0,50f, 1,0f, 0, "Disruptor", R.drawable.wisp,R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.1f, 0, "Adds 2 dmg per enemy in tower range", 0f, "", 0f)
        var id306 = Items(306, 50, 999,0,(0f), 1, 0f, 0,"Beggar", R.drawable.bagicon3,R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.1f, 0, "+1 bag slot", 1f, "", 0f)
        var id307 = Items(307, 30, 999,0,(0f), 1, 0f, 0,"Beggar", R.drawable.multibarrelpurple, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,((4.0f) + (level * 0.15f)), 0.0f, 0.1f, 0, "+1 multishot", 1f, "", 0f)
        var id308 = Items(308, 30, 999,0,(0f), 0, 0f, 0," Legendary Freezer", R.drawable.frostorange, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0f, 0.0f, 0.0f, 0, "slows a random unit for 10%", 10.0f, "", 0f)
        var id309 = Items(309, 50, 999,0,(0f), 0, 0f, 0,"Lasso", R.drawable.lassopurple, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0f, 0.0f, 0.0f, 0, "throws X lassos that stun", 3.0f, "", 0f)
        var id310 = Items(310, 50, 999,0,0f, 0, 0f, 0,"BullZ-I", R.drawable.bullseyepurple, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0f, 0.0f, ((9.0f*0) + (level * 0.15f)), 5, "+1 multicrit", 1f, "", 0f)
        var id311 = Items(311, 30, 999,0,(0f), 0, 0f, 0,"21/20", R.drawable.critdmgpurple, R.drawable.overlaytransparent,0f,0.0f,0.0f, 0f, ((3f*0) + (level * 0.03f)), 0.1f*0, 5, "", 0.0f, "", 0f)
        var id312 = Items(312, 50, 999,0,(0f), 0, 0f, 0,"Slow Death", R.drawable.slowdeathpurple, R.drawable.overlaytransparent,0f,0.0f,0.0f, 0f, 0f, 0f, 0, "burns 3% hp per attack", 0.03f, "", 0f)
        var id313 = Items(313, 10, 999, 0, 0f, 0,0f, 0, "Lovely!", R.drawable.heartdiagreen, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "+5 lives", 5f, "", 0f)
        var id314 = Items(314, 50, 999, 0, 0f, 0, 0f, 0, "Legendary Shredder", R.drawable.shredderpurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,((6.0f * lvlScaler) + (level * 0.15f)), 0f, 0f, 0, "reduces armor by X per hit", 0.5f, "", 0f)
        var id315 = Items(315, 50, 999, 0, 0f, 0, 0f, 0, "Legendary Magic Braker", R.drawable.magicbrakerpurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,((6.0f * lvlScaler) + (level * 0.15f)), 0f, 0f, 0, "reduces magic armor by X per hit", 0.5f, "", 0f)
        var id316 = Items(316, 20, 999, 0, 0f, 0, 0f, 0, "Buddha", R.drawable.braindamageorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+1% dmg/% towerlvl", 0f, "", 0f)

        var itemListReserveNormal = mutableListOf<Items>(id0, id1, id2, id3, id4, id5, id9)
        var itemListReserveRare = mutableListOf<Items>(id100, id101, id102, id103, id104, id106, id107)
        var itemListReserveEpic = mutableListOf<Items>(id200, id201, id202, id203, id204, id205, id206, id207, id208, id209, id210, id211, id212, id213, id214, id215, id216, id217, id218)
        var itemListReserveLegendary = mutableListOf<Items>(id300, id301, id302, id303, id304, id305, id306, id307, id308, id309, id310, id311, id312, id313, id314, id315, id316)
        var greyItems = mutableListOf<Int>(0, 1, 2, 3, 4, 10)
        var blueItems = mutableListOf<Int>(100, 101, 102, 103, 104, 105)
        var orangeItems = mutableListOf<Int>(200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218)
        var purpleItems = mutableListOf<Int>(300, 301, 302, 303, 304, 305, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316)


        // secret shop ------------------------------------------------------------------

        var secretShopList = mutableListOf<Items>()
        var shopList = mutableListOf<Items>()

        // midnight madness ------------------------------------------------------------------

        var mid0 = Items(1100, 1, 999,0,0f, 0, 0f, 0,"Pirate Parrot", R.drawable.pirateparrotred, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "Free Slot. +15 range.", 0f, "", 0f)
        var mid1 = Items(1101, 1, 999,0,0f, 0, 0f, 0,"Pirate Flag", R.drawable.pirateflagred, R.drawable.overlaytransparent,((2.0f * lvlScaler) + (level * 0.075f)), 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "Free Slot", 0f, "", 0f)
        var mid2 = Items(1102, 1, 999,0,0f, 0, 0f, 0,"Pirate Hat", R.drawable.piratehatred, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,((8.0f * lvlScaler) + (level * 0.075f)), 0.0f, 0.0f, 0, "Free Slot", 0f, "", 0f)
        var mid3 = Items(1103, 1, 999,0,0f, 0, 0f, 0,"Pirate Hook", R.drawable.piratehookred, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, ((6.0f * lvlScaler) + (level * 0.075f)), 0.0f, 0, "Free Slot", 0f, "", 0f)
        var mid4 = Items(1104, 1, 999,0,0f, 0, 0f, 0,"Pirate Leg", R.drawable.piratelegred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "Free Slot; +20% DMG; -10% SPD", 0f, "", 0f)
        var mid5 = Items(1105, 1, 999,0,0f, 0, 0f, 0,"Pirate Saber", R.drawable.piratesaberred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0.0f, 0.0f, 0, "Free Slot; -10% DMG; +20% SPD", 0f, "", 0f)
        var redItems = mutableListOf<Int>(-1, 1100, 1101, 1102, 1103, 1104, 1105)

        // tower ------------------------------------------------------------------

        var towerListNormal = mutableListOf<Int>()
        var towerListRare = mutableListOf<Int>()
        var towerListEpic = mutableListOf<Int>()
        var towerListLegendary = mutableListOf<Int>()


        var eearth = Items(3000, 0, 999, 0, 0f, 1, 0f, 0, "Earth Element", R.drawable.elementearth, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Earth Abilities", 0.0f, "Adds a medium aoe to all hits.", 0f)
        var ewizard = Items(3001, 0, 999, 0, 0f, 1, 0f, 0, "Wizard Element", R.drawable.elementwizard, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Wizard Abilities", 0.0f, "Places a bomb on a random target that deals aoe dmg.", 0f)
        var eice = Items(3002, 0, 999, 0, 0f, 1, 0f, 0, "Ice Element", R.drawable.elementice, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Ice Abilities", 0.0f, "Ice Nova. Blasts the area each 4.5 seconds and slows enemies for a short duration.", 0f)
        var ebutterfly = Items(3003, 0, 999, 0, 0f, 1, 0f, 0, "Butterfly Element", R.drawable.elementbutterfly, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Butterfly Abilities", 0.0f, "Target receives 150% damage at the 3rd consecutive hit.", 0f)
        var epoison = Items(3004, 0, 999, 0, 0f, 1, 0f, 0, "Poison Element", R.drawable.elementpoison, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Poison Abilities", 0.0f, "Applies a stackable poison debuff that deals magic damage over time.", 0f)
        var emoon = Items(3005, 0, 999, 0, 0f, 1, 0f, 0, "Moon Element", R.drawable.elementmoon, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Moon Abilities", 0.0f, "Bullets bounce to 1 additional target dealing -50% damage. Moonlight. ", 0f)
        var ewind = Items(3006, 0, 999, 0, 0f, 1, 0f, 0, "Wind Element", R.drawable.elementwind, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Wind Abilities", 0.0f, "Multishot hits all targets. Damage is divided by target count + 2.", 0f)
        var eutils = Items(3007, 0, 999, 0, 0f, 1, 0f, 0, "Utils Element", R.drawable.elementutils, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Utils Abilities", 0.0f, "Increases dmg and spd of nearby towers by 5%", 0f)
        var efire = Items(3008, 0, 999, 0, 0f, 1, 0f, 0, "Fire Element", R.drawable.elementfire, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Fire Abilities", 0.0f, "Crit dmg + 0.5. Crit chance + 10. Burns enemies critically hit for 4% of max hp as magic damage.", 0f)
        var edark = Items(3009, 0, 999, 0, 0f, 1, 0f, 0, "Dark Element", R.drawable.elementdark, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Dark Abilities", 0.0f, "Each hit has a 1.5% chance to instantly kill the target.", 0f)

}