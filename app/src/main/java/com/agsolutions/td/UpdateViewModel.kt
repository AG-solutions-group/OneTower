package com.agsolutions.td

import android.graphics.Color
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.Utils.round
import kotlin.math.ceil

class UpdateViewModel : ViewModel() {
    companion object {

    }

    var mHandler = Handler ()

    private val _level = MutableLiveData<Int> ()
    val level: LiveData<Int>
    get() = _level

    private val _lives = MutableLiveData<String> ()
    val lives: LiveData<String>
        get() = _lives

    private val _upgradePoints = MutableLiveData<Int> ()
    val upgradePoints: LiveData<Int>
        get() = _upgradePoints

    private val _upgradePointsColor = MutableLiveData<Int> ()
    val upgradePointsColor: LiveData<Int>
        get() = _upgradePointsColor

    private val _diamonds = MutableLiveData<Int> ()
    val diamonds: LiveData<Int>
        get() = _diamonds

    private val _diamondsColor = MutableLiveData<Int> ()
    val diamondsColor: LiveData<Int>
        get() = _diamondsColor

    private val _itemPoints = MutableLiveData<Int> ()
    val itemPoints: LiveData<Int>
        get() = _itemPoints

    private val _itemPointsColor = MutableLiveData<Int> ()
    val itemPointsColor: LiveData<Int>
        get() = _itemPointsColor

    private val _talentsColor = MutableLiveData<Int> ()
    val talentsColor: LiveData<Int>
        get() = _talentsColor

    private val _enemyType = MutableLiveData<String> ()
    val enemyType: LiveData<String>
        get() = _enemyType

    private val _enemyTypeSpecific = MutableLiveData<String> ()
    val enemyTypeSpecific: LiveData<String>
        get() = _enemyTypeSpecific

    private val _lvlCounter = MutableLiveData<String> ()
    val lvlCounter: LiveData<String>
        get() = _lvlCounter

    private val _talents = MutableLiveData<Int> ()
    val talents: LiveData<Int>
        get() = _talents

    private val _xpString = MutableLiveData<String> ()
    val xpString: LiveData<String>
        get() = _xpString

    private val _xp = MutableLiveData<Float> ()
    val xp: LiveData<Float>
        get() = _xp

    private val _goldColor = MutableLiveData<Int> ()
    val goldColor: LiveData<Int>
        get() = _goldColor

    private val _towerDmgString = MutableLiveData<String> ()
    val towerDmgString: LiveData<String>
        get() = _towerDmgString

    private val _towerDmg = MutableLiveData<Float> ()
    val towerDmg: LiveData<Float>
        get() = _towerDmg

    private val _towerPhyDmgString = MutableLiveData<String> ()
    val towerPhyDmgString: LiveData<String>
        get() = _towerPhyDmgString

    private val _towerPhyDmg = MutableLiveData<Float> ()
    val towerPhyDmg: LiveData<Float>
        get() = _towerPhyDmg

    private val _towerMgcDmgString = MutableLiveData<String> ()
    val towerMgcDmgString: LiveData<String>
        get() = _towerMgcDmgString

    private val _towerMgcDmg = MutableLiveData<Float> ()
    val towerMgcDmg: LiveData<Float>
        get() = _towerMgcDmg

    private val _towerCrtString = MutableLiveData<String> ()
    val towerCrtString: LiveData<String>
        get() = _towerCrtString

    private val _towerCrt = MutableLiveData<Float> ()
    val towerCrt: LiveData<Float>
        get() = _towerCrt

    private val _towerSpd = MutableLiveData<Float> ()
    val towerSpd: LiveData<Float>
        get() = _towerSpd

    private val _towerCrtDmg = MutableLiveData<Float> ()
    val towerCrtDmg: LiveData<Float>
        get() = _towerCrtDmg

    private val _towerMultiCrt = MutableLiveData<Int> ()
    val towerMultiCrt: LiveData<Int>
        get() = _towerMultiCrt

    private val _towerArmPen = MutableLiveData<Float> ()
    val towerArmPen: LiveData<Float>
        get() = _towerArmPen

    private val _towerMgcPen = MutableLiveData<Float> ()
    val towerMgcPen: LiveData<Float>
        get() = _towerMgcPen

    private val _towerHit = MutableLiveData<Int> ()
    val towerHit: LiveData<Int>
        get() = _towerHit

    private val _towerLevel = MutableLiveData<Int> ()
    val towerLevel: LiveData<Int>
        get() = _towerLevel

    private val _towerLevelXp = MutableLiveData<Int> ()
    val towerLevelXp: LiveData<Int>
        get() = _towerLevelXp

    private val _towerLevelXpMin = MutableLiveData<Int> ()
    val towerLevelXpMin: LiveData<Int>
        get() = _towerLevelXpMin

    private val _towerLevelXpProg = MutableLiveData<Int> ()
    val towerLevelXpProg: LiveData<Int>
        get() = _towerLevelXpProg

    private val _towerRange = MutableLiveData<Int> ()
    val towerRange: LiveData<Int>
        get() = _towerRange

    private val _towerXpMulti = MutableLiveData<Int> ()
    val towerXpMulti: LiveData<Int>
        get() = _towerXpMulti

    private val _towerGoldMulti = MutableLiveData<Int> ()
    val towerGoldMulti: LiveData<Int>
        get() = _towerGoldMulti

    private val _lvlHp = MutableLiveData<String> ()
    val lvlHp: LiveData<String>
        get() = _lvlHp

    private val _lvlMaxHp = MutableLiveData<String> ()
    val lvlMaxHp: LiveData<String>
        get() = _lvlMaxHp

    private val _lvlShield = MutableLiveData<String> ()
    val lvlShield: LiveData<String>
        get() = _lvlShield

    private val _lvlMaxShield = MutableLiveData<String> ()
    val lvlMaxShield: LiveData<String>
        get() = _lvlMaxShield

    private val _lvlManaShield = MutableLiveData<String> ()
    val lvlManaShield: LiveData<String>
        get() = _lvlManaShield

    private val _lvlMaxManaShield = MutableLiveData<String> ()
    val lvlMaxManaShield: LiveData<String>
        get() = _lvlMaxManaShield

    private val _lvlHpBar = MutableLiveData<Int> ()
    val lvlHpBar: LiveData<Int>
        get() = _lvlHpBar

    private val _lvlMaxHpBar = MutableLiveData<Int> ()
    val lvlMaxHpBar: LiveData<Int>
        get() = _lvlMaxHpBar

    private val _lvlShieldBar = MutableLiveData<Int> ()
    val lvlShieldBar: LiveData<Int>
        get() = _lvlShieldBar

    private val _lvlMaxShieldBar = MutableLiveData<Int> ()
    val lvlMaxShieldBar: LiveData<Int>
        get() = _lvlMaxShieldBar

    private val _lvlManaShieldBar = MutableLiveData<Int> ()
    val lvlManaShieldBar: LiveData<Int>
        get() = _lvlManaShieldBar

    private val _lvlMaxManaShieldBar = MutableLiveData<Int> ()
    val lvlMaxManaShieldBar: LiveData<Int>
        get() = _lvlMaxManaShieldBar

    private val _lvlArmor = MutableLiveData<String> ()
    val lvlArmor: LiveData<String>
        get() = _lvlArmor

    private val _lvlArmorRating = MutableLiveData<String> ()
    val lvlArmorRating: LiveData<String>
        get() = _lvlArmorRating

    private val _lvlMagicArmor = MutableLiveData<String> ()
    val lvlMagicArmor: LiveData<String>
        get() = _lvlMagicArmor

    private val _lvlMagicArmorRating = MutableLiveData<String> ()
    val lvlMagicArmorRating: LiveData<String>
        get() = _lvlMagicArmorRating

    private val _lvlEvade = MutableLiveData<String> ()
    val lvlEvade: LiveData<String>
        get() = _lvlEvade

    private val _lvlHpReg = MutableLiveData<Float> ()
    val lvlHpReg: LiveData<Float>
        get() = _lvlHpReg

    private val _lvlSpd = MutableLiveData<Float> ()
    val lvlSpd: LiveData<Float>
        get() = _lvlSpd

    private val _bagString = MutableLiveData<String> ()
    val bagString: LiveData<String>
        get() = _bagString

    private val _bag = MutableLiveData<Int> ()
    val bag: LiveData<Int>
        get() = _bag

    private val _bagStringElement = MutableLiveData<String> ()
    val bagStringElement: LiveData<String>
        get() = _bagStringElement

    private val _bagElement = MutableLiveData<Int> ()
    val bagElement: LiveData<Int>
        get() = _bagElement

    private val _itemChance = MutableLiveData<Float> ()
    val itemChance: LiveData<Float>
        get() = _itemChance

    private val _itemQuality = MutableLiveData<Float> ()
    val itemQuality: LiveData<Float>
        get() = _itemQuality

    private val _interestString = MutableLiveData<String> ()
    val interestString: LiveData<String>
        get() = _interestString

    private val _interest = MutableLiveData<Float> ()
    val interest: LiveData<Float>
        get() = _interest

    private val _interestColor = MutableLiveData<Int> ()
    val interestColor: LiveData<Int>
        get() = _interestColor

    private val _dayNight = MutableLiveData<String> ()
    val dayNight: LiveData<String>
        get() = _dayNight

    private val _dayNightPic = MutableLiveData<Int> ()
    val dayNightPic: LiveData<Int>
        get() = _dayNightPic

    private val _textMain = MutableLiveData<Float> ()
    val textMain: LiveData<Float>
        get() = _textMain

    private val _textNews = MutableLiveData<Float> ()
    val textNews: LiveData<Float>
        get() = _textNews

    private val _textStats = MutableLiveData<Float> ()
    val textStats: LiveData<Float>
        get() = _textStats

    private val _textStatsPic = MutableLiveData<Float> ()
    val textStatsPic: LiveData<Float>
        get() = _textStatsPic

    private val _textButton = MutableLiveData<Float> ()
    val textButton: LiveData<Float>
        get() = _textButton

    private val _textBig = MutableLiveData<Float> ()
    val textBig: LiveData<Float>
        get() = _textBig

    private val _pictureDmg = MutableLiveData<Int> ()
    val pictureDmg: LiveData<Int>
        get() = _pictureDmg

    private val _pictureValue = MutableLiveData<String> ()
    val pictureValue: LiveData<String>
        get() = _pictureValue

    private val _towerRarity = MutableLiveData<String> ()
    val towerRarity: LiveData<String>
        get() = _towerRarity

    private val _towerRarityMultiplier = MutableLiveData<String> ()
    val towerRarityMultiplier: LiveData<String>
        get() = _towerRarityMultiplier

    init {
        _talentsColor.value = Color.WHITE
        _goldColor.value = Color.WHITE
        _diamondsColor.value = Color.WHITE
        _upgradePointsColor.value = Color.WHITE
        _itemPointsColor.value = Color.WHITE
        _bagString.value = "4"
        _bag.value = 5
        _bagStringElement.value = "4"
        _bagElement.value = 5
        _level.value = 0
        _xpString.value = "0"
        _xp.value = 0f
        _talents.value = 0
        _lvlCounter.value = ""
        _enemyType.value = "undef"
        _enemyTypeSpecific.value = "undef"
        _itemPoints.value = 0
        _diamonds.value = 1
        _upgradePoints.value = 0
        _lives.value = "8"
        _towerDmgString.value = "0"
        _towerDmg.value = 0f
        _towerPhyDmgString.value = "0"
        _towerPhyDmg.value = 0f
        _towerMgcDmgString.value = "0"
        _towerMgcDmg.value = 0f
        _towerCrtString.value = ""
        _towerCrt.value = 0f
        _towerSpd.value = 0.0f
        _towerCrtDmg.value = 0f
        _towerMultiCrt.value = 1
        _towerLevel.value = 0
        _towerLevelXp.value = 0
        _towerLevelXpMin.value = 0
        _towerLevelXpProg.value = 0
        _towerRange.value = 0
        _towerXpMulti.value = 0
        _towerGoldMulti.value = 0
        _lvlHp.value = "0.0"
        _lvlMaxHp.value = "0.0"
        _lvlShield.value = "0.0"
        _lvlMaxShield.value = "0.0"
        _lvlManaShield.value = "0.0"
        _lvlMaxManaShield.value = "0.0"
        _lvlHpBar.value = 0
        _lvlMaxHpBar.value = 0
        _lvlShieldBar.value = 0
        _lvlMaxShieldBar.value = 0
        _lvlManaShieldBar.value = 0
        _lvlMaxManaShieldBar.value = 0
        _lvlArmor.value = ""
        _lvlArmorRating.value = ""
        _lvlMagicArmor.value = ""
        _lvlMagicArmorRating.value = ""
        _lvlEvade.value = ""
        _lvlHpReg.value = 0.0f
        _lvlSpd.value = 0.0f
        _towerHit.value = 100
        _towerArmPen.value = 0f
        _towerMgcPen.value = 0f
        _itemChance.value = 0.0f
        _itemQuality.value = 0.0f
        _interestString.value = "0%"
        _interest.value = 0f
        _interestColor.value = Color.WHITE
        _dayNight.value= "12:00"
        _textMain.value= 12.0f
        _textNews.value= 11.0f
        _textStats.value= 11.0f
        _textStatsPic.value= 11f
        _textButton.value= 11.0f
        _textBig.value= 11.0f
        _pictureDmg.value = 0
        _towerRarity.value = ""
        _towerRarityMultiplier.value = ""
    }

    fun update () {

        if (GameActivity.companionList.mapMode != 2) _lives.postValue(GameActivity.companionList.lives.toString())
        else _lives.postValue(companionList.lives.toString() + " / " + GameActivity.companionList.livesMode2.toString())

        GameActivity.companionList.readLockTower.lock ()
        try {
            if (GameActivity.companionList.towerClick) {
                var towerListIterator = GameActivity.companionList.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()
                    if (tower.selected) {

                        // tower damage

                        when (tower.overallTowerDmg.toInt()) {
                            in 0..999 -> _towerDmgString.postValue(tower.overallTowerDmg.toInt()
                                .toString())
                            in 1000..999999 -> _towerDmgString.postValue((tower.overallTowerDmg / 1000).round(1)
                                .toString() + "k")
                            in 1000000..999999999 -> _towerDmgString.postValue((tower.overallTowerDmg / 1000000).round(1)
                                .toString() + "M")
                        }
                        _towerDmg.postValue(tower.overallTowerDmg)

                        // physical damage

                        when (tower.overallTowerPhysicalDmg.toInt()) {
                            in 0..999 -> _towerPhyDmgString.postValue(tower.overallTowerPhysicalDmg.toInt()
                                .toString())
                            in 1000..999999 -> _towerPhyDmgString.postValue((tower.overallTowerPhysicalDmg / 1000).round(1)
                                .toString() + "k")
                            in 1000000..999999999 -> _towerPhyDmgString.postValue((tower.overallTowerPhysicalDmg / 1000000).round(1)
                                .toString() + "M")
                        }
                        _towerPhyDmg.postValue(tower.overallTowerPhysicalDmg)

                        // spell damage

                        when (tower.overallTowerSpellDmg.toInt()) {
                            in 0..999 -> _towerMgcDmgString.postValue(tower.overallTowerSpellDmg.toInt()
                                .toString())
                            in 1000..999999 -> _towerMgcDmgString.postValue((tower.overallTowerSpellDmg / 1000).round(1)
                                .toString() + "k")
                            in 1000000..999999999 -> _towerMgcDmgString.postValue((tower.overallTowerSpellDmg / 1000000).round(1)
                                .toString() + "M")
                        }
                        _towerMgcDmg.postValue(tower.overallTowerSpellDmg)

                        // tower speed

                        _towerSpd.postValue((tower.towerAttackSpeedShow * 20).round(1))

                        // crit chance

                        _towerCrtString.postValue(tower.overallCrit.round(1).toString() + "%")
                        _towerCrt.postValue(tower.overallCrit)

                        // crit damage

                        _towerCrtDmg.postValue(tower.overallCritDmg.round(1))

                        // multi crit

                        _towerMultiCrt.postValue((tower.overallMulticrit - 1))

                        // tower hit

                        _towerHit.postValue(tower.hitChance.toInt())

                        // armor pen

                        _towerArmPen.postValue(tower.overallBonusArmorPen.round(1))

                        // magic pen

                        _towerMgcPen.postValue(tower.overallBonusMagicPen.round(1))

                        // item chance

                        _itemChance.postValue(tower.overallItemChance.round(1))

                        // item quality

                        _itemQuality.postValue(tower.overallItemQuality.round(1))

                        // bag size

                        _bag.postValue((tower.itemListBag.size))
                        _bagString.postValue((tower.itemListBag.size - tower.bagSizeElementCount).toString() + "/" + (tower.bagSize + 1).toString())
                        _bagStringElement.postValue((tower.bagSizeElementCount).toString() + "/" + (tower.bagSizeElement + 1).toString())

                        // talent points
                        if (GameActivity.companionList.towerList.size == 0) _talents.postValue(0)
                        else {
                            if (tower.talentPoints > _talents.value!!) {
                                _talentsColor.postValue(Color.GREEN)
                            } else if (tower.talentPoints > 1) {
                                if (GameActivity.companionList.hintsBool) {
                                    GameActivity.companionList.talentGainCountRed++
                                    if (GameActivity.companionList.talentGainCountRed > 40) GameActivity.companionList.talentGainCountRed = 0
                                    else if (GameActivity.companionList.talentGainCountRed > 20) _talentsColor.postValue(Color.WHITE)
                                    else _talentsColor.postValue(Color.RED)
                                } else _talentsColor.postValue(Color.RED)
                            } else _talentsColor.postValue(Color.WHITE)
                            _talents.postValue(tower.talentPoints)
                        }

                        // tower range
                        _towerRange.postValue(tower.towerR.toInt())

                        // tower xp multi
                        _towerXpMulti.postValue(tower.bonusXpMultiplier.toInt())

                        // tower gold multi
                        _towerGoldMulti.postValue(tower.bonusGoldMultiplier.toInt())

                        // xp
                        _towerLevel.postValue(tower.towerLevel)
                        _towerLevelXp.postValue((tower.xpGoal2 * 10).toInt())
                        _towerLevelXpMin.postValue((tower.xpGoal1 * 10).toInt())
                        _towerLevelXpProg.postValue((tower.xpTower * 10).toInt())

                        // rarity
                        when (tower.towerRarity){
                            "basic" -> {
                                _towerRarity.postValue(("Basic"))
                                _towerRarityMultiplier.postValue("(* ${tower.towerRarityMultiplier.round(2)})")
                            }
                            "rare" -> {
                                _towerRarity.postValue(("Rare"))
                                _towerRarityMultiplier.postValue("(* ${tower.towerRarityMultiplier.round(2)})")
                            }
                            "epic" -> {
                                _towerRarity.postValue(("Epic"))
                                _towerRarityMultiplier.postValue("(* ${tower.towerRarityMultiplier.round(2)})")
                            }
                            "legendary" -> {
                                _towerRarity.postValue(("Legend"))
                                _towerRarityMultiplier.postValue("(* ${tower.towerRarityMultiplier.round(2)})")
                            }
                        }
                        break
                    }
                }
                }
                }finally {
            GameActivity.companionList.readLockTower.unlock()
                }

        // diamonds
        if (GameActivity.companionList.diamonds > _diamonds.value!! || GameActivity.companionList.diamondsGain) {
            GameActivity.companionList.diamondsGain = true
            _diamondsColor.postValue(Color.GREEN)
        } else _diamondsColor.postValue(Color.WHITE)
        _diamonds.postValue(GameActivity.companionList.diamonds)

        // interest
        if (GameActivity.companionList.interest > _interest.value!! || GameActivity.companionList.interestGain) {
            GameActivity.companionList.interestGain = true
            _interestColor.postValue(Color.GREEN)
        } else _interestColor.postValue(Color.WHITE)

        _interest.postValue(GameActivity.companionList.interest)
        _interestString.postValue((ceil((GameActivity.companionList.interest - 1) * 100)).toInt().toString() + "%")

        // upgrade points
        if (GameActivity.companionList.upgradePoints > _upgradePoints.value!! || GameActivity.companionList.upgradePointsGain) {
            GameActivity.companionList.upgradePointsGain = true
            _upgradePointsColor.postValue(Color.GREEN)
        } else if (GameActivity.companionList.upgradePoints > 5) _upgradePointsColor.postValue(Color.RED)
        else _upgradePointsColor.postValue(Color.WHITE)
        _upgradePoints.postValue(GameActivity.companionList.upgradePoints)

        // item points
        if (GameActivity.companionList.itemPoints.toInt() > _itemPoints.value!! || GameActivity.companionList.itemPointsGain) {
            GameActivity.companionList.itemPointsGain = true
            _itemPointsColor.postValue(Color.GREEN)
        } else _itemPointsColor.postValue(Color.WHITE)
        _itemPoints.postValue(GameActivity.companionList.itemPoints.toInt())

        if (GameActivity.companionList.dayNightMinute < 10) _dayNight.postValue(GameActivity.companionList.dayNightHour.toString() + ":0" + companionList.dayNightMinute.toString())
        else _dayNight.postValue(GameActivity.companionList.dayNightHour.toString() + ":" + GameActivity.companionList.dayNightMinute.toString())
        if (GameActivity.companionList.day) _dayNightPic.postValue(R.drawable.suntransparent)
        else _dayNightPic.postValue(R.drawable.moontransparent)

        _textMain.postValue(GameActivity.companionList.scaleTextMain)
        _textNews.postValue(GameActivity.companionList.scaleTextNews)
        _textStats.postValue(GameActivity.companionList.scaleTextStats)
        _textStatsPic.postValue((GameActivity.companionList.scaleTextStats * 0.6).toInt().toFloat())
        _textButton.postValue(GameActivity.companionList.scaleTextButton)
        _textBig.postValue(GameActivity.companionList.scaleTextBig)

    }

    fun updateLvlStats () {
        when (GameActivity.companionList.enemyHp.toInt()){
            in 0..999 -> _lvlHp.postValue(GameActivity.companionList.enemyHp.toInt().toString()+ " /")
            in 1000..999999 -> _lvlHp.postValue((GameActivity.companionList.enemyHp/1000).round(1).toString() + "k" + " /")
            in 1000000..999999999 -> _lvlHp.postValue((GameActivity.companionList.enemyHp/1000000).round(1).toString() + "M"+ " /")
        }
        when (GameActivity.companionList.enemyMaxHp.toInt()){
            in 0..999 -> _lvlMaxHp.postValue(GameActivity.companionList.enemyMaxHp.toInt().toString())
            in 1000..999999 -> _lvlMaxHp.postValue((GameActivity.companionList.enemyMaxHp/1000).round(1).toString() + "k")
            in 1000000..999999999 -> _lvlMaxHp.postValue((GameActivity.companionList.enemyMaxHp/1000000).round(1).toString() + "M")
        }
        when (GameActivity.companionList.enemyShield.toInt()){
            in 0..999 -> _lvlShield.postValue(companionList.enemyShield.toInt().toString()+ " /")
            in 1000..999999 -> _lvlShield.postValue((GameActivity.companionList.enemyShield/1000).round(1).toString() + "k" + " /")
            in 1000000..999999999 -> _lvlShield.postValue((GameActivity.companionList.enemyShield/1000000).round(1).toString() + "M"+ " /")
        }
        when (GameActivity.companionList.enemyShieldMax.toInt()){
            in 0..999 -> _lvlMaxShield.postValue(GameActivity.companionList.enemyShieldMax.toInt().toString())
            in 1000..999999 -> _lvlMaxShield.postValue((GameActivity.companionList.enemyShieldMax/1000).round(1).toString() + "k")
            in 1000000..999999999 -> _lvlMaxShield.postValue((GameActivity.companionList.enemyShieldMax/1000000).round(1).toString() + "M")
        }
        when (GameActivity.companionList.enemyManaShield.toInt()){
            in 0..999 -> _lvlManaShield.postValue(GameActivity.companionList.enemyManaShield.toInt().toString()+ " /")
            in 1000..999999 -> _lvlManaShield.postValue((GameActivity.companionList.enemyManaShield/1000).round(1).toString() + "k" + " /")
            in 1000000..999999999 -> _lvlManaShield.postValue((GameActivity.companionList.enemyManaShield/1000000).round(1).toString() + "M"+ " /")
        }
        when (GameActivity.companionList.enemyManaShieldMax.toInt()){
            in 0..999 -> _lvlMaxManaShield.postValue(companionList.enemyManaShieldMax.toInt().toString())
            in 1000..999999 -> _lvlMaxManaShield.postValue((companionList.enemyManaShieldMax/1000).round(1).toString() + "k")
            in 1000000..999999999 -> _lvlMaxManaShield.postValue((companionList.enemyManaShieldMax/1000000).round(1).toString() + "M")
        }
        _lvlArmor.postValue(companionList.enemyArmorReduction.round(1).toString() + "%)")
        _lvlArmorRating.postValue("(" + companionList.enemyArmor.toInt().toString() + "/")
        _lvlMagicArmor.postValue(companionList.enemyMagicArmorReduction.round(1).toString() + "%)")
        _lvlMagicArmorRating.postValue("(" + companionList.enemyMagicArmor.toInt().toString() + "/")
        if (!companionList.day && companionList.endlessNight > 0) _lvlEvade.postValue((100 - (100 - ((((100 + (3f* companionList.endlessNight)) * (((companionList.enemyEvade + (companionList.evadeNight - (3f * companionList.endlessNight))) * 0.06f) / (1f + (0.06f * (companionList.enemyEvade +(companionList.evadeNight - (3f * companionList.endlessNight))))))))))).round(1).toString() + "%")
        else {
            var x = (100 - ((((100) * (((companionList.enemyEvade + companionList.evadeNight) * 0.06f) / (1f + (0.06f * (companionList.enemyEvade + companionList.evadeNight))))))))
            if (x > 100) x = 100f
            _lvlEvade.postValue(((100 - x)).round(1).toString() + "%")
        }
        _lvlHpReg.postValue(companionList.enemyHpReg.round(1))
        _lvlSpd.postValue(companionList.enemySpd.round(1))
        _lvlHpBar.postValue((companionList.enemyHp).toInt())
        _lvlMaxHpBar.postValue(companionList.enemyMaxHp.toInt())
        _lvlShieldBar.postValue((companionList.enemyShield).toInt())
        _lvlMaxShieldBar.postValue(companionList.enemyShieldMax.toInt())
        _lvlManaShieldBar.postValue((companionList.enemyManaShield).toInt())
        _lvlMaxManaShieldBar.postValue(companionList.enemyManaShieldMax.toInt())
        _enemyTypeSpecific.postValue(companionList.enemyType)
    }

    fun updateCounter () {

        if (companionList.enemyList.isEmpty()) _lvlCounter.postValue(((companionList.levelCountPlace - companionList.levelCount) / 60).toString())
        else _lvlCounter.postValue("")
    }

    fun updateXp () {
        if (companionList.gold.toInt() > _xp.value!! || companionList.goldGain) {
            companionList.goldGain = true
            _goldColor.postValue(Color.GREEN)
        } else _goldColor.postValue(Color.WHITE)

        when (companionList.gold.toInt()){
            in 0..999 -> _xpString.postValue(companionList.gold.toInt().toString())
            in 1000..999999 -> _xpString.postValue((companionList.gold/1000).round(1).toString() + "k")
            in 1000000..999999999 -> _xpString.postValue((companionList.gold/1000000).round(1).toString() + "M")
        }
        _xp.postValue(companionList.gold)
    }

    fun updateLvlStatus () {
        _enemyType.postValue(companionList.levelStatus)
    }

    fun onLvlUp () {
        _level.postValue(companionList.level)
    }
}