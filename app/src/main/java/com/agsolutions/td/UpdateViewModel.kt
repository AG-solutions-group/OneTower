package com.agsolutions.td

import android.graphics.Color
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agsolutions.td.Companion.Companion.armorPenGain
import com.agsolutions.td.Companion.Companion.critChanceGain
import com.agsolutions.td.Companion.Companion.critDamageGain
import com.agsolutions.td.Companion.Companion.damageGain
import com.agsolutions.td.Companion.Companion.day
import com.agsolutions.td.Companion.Companion.dayNightMinute
import com.agsolutions.td.Companion.Companion.diamondsGain
import com.agsolutions.td.Companion.Companion.endlessNight
import com.agsolutions.td.Companion.Companion.enemyList
import com.agsolutions.td.Companion.Companion.evadeGlobal
import com.agsolutions.td.Companion.Companion.goldGain
import com.agsolutions.td.Companion.Companion.hintsBool
import com.agsolutions.td.Companion.Companion.hitGain
import com.agsolutions.td.Companion.Companion.interestGain
import com.agsolutions.td.Companion.Companion.itemChanceGain
import com.agsolutions.td.Companion.Companion.itemPointsGain
import com.agsolutions.td.Companion.Companion.itemQualityGain
import com.agsolutions.td.Companion.Companion.levelCount
import com.agsolutions.td.Companion.Companion.levelCountPlace
import com.agsolutions.td.Companion.Companion.levelStatus
import com.agsolutions.td.Companion.Companion.magicArmorPenGain
import com.agsolutions.td.Companion.Companion.mgcDamageGain
import com.agsolutions.td.Companion.Companion.multiCritGain
import com.agsolutions.td.Companion.Companion.phyDamageGain
import com.agsolutions.td.Companion.Companion.spdGain
import com.agsolutions.td.Companion.Companion.talentGainCountRed
import com.agsolutions.td.Companion.Companion.talentsGain
import com.agsolutions.td.Companion.Companion.upgradePointsGain
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

    private val _talentsColor = MutableLiveData<Int> ()
    val talentsColor: LiveData<Int>
        get() = _talentsColor

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

    private val _towerDmgColor = MutableLiveData<Int> ()
    val towerDmgColor: LiveData<Int>
        get() = _towerDmgColor

    private val _towerPhyDmgString = MutableLiveData<String> ()
    val towerPhyDmgString: LiveData<String>
        get() = _towerPhyDmgString

    private val _towerPhyDmg = MutableLiveData<Float> ()
    val towerPhyDmg: LiveData<Float>
        get() = _towerPhyDmg

    private val _towerPhyDmgColor = MutableLiveData<Int> ()
    val towerPhyDmgColor: LiveData<Int>
        get() = _towerPhyDmgColor

    private val _towerMgcDmgString = MutableLiveData<String> ()
    val towerMgcDmgString: LiveData<String>
        get() = _towerMgcDmgString

    private val _towerMgcDmg = MutableLiveData<Float> ()
    val towerMgcDmg: LiveData<Float>
        get() = _towerMgcDmg

    private val _towerMgcDmgColor = MutableLiveData<Int> ()
    val towerMgcDmgColor: LiveData<Int>
        get() = _towerMgcDmgColor

    private val _towerCrtString = MutableLiveData<String> ()
    val towerCrtString: LiveData<String>
        get() = _towerCrtString

    private val _towerCrt = MutableLiveData<Float> ()
    val towerCrt: LiveData<Float>
        get() = _towerCrt

    private val _towerCrtColor = MutableLiveData<Int> ()
    val towerCrtColor: LiveData<Int>
        get() = _towerCrtColor

    private val _towerSpd = MutableLiveData<Float> ()
    val towerSpd: LiveData<Float>
        get() = _towerSpd

    private val _towerSpdColor = MutableLiveData<Int> ()
    val towerSpdColor: LiveData<Int>
        get() = _towerSpdColor

    private val _towerCrtDmg = MutableLiveData<Float> ()
    val towerCrtDmg: LiveData<Float>
        get() = _towerCrtDmg

    private val _towerCrtDmgColor = MutableLiveData<Int> ()
    val towerCrtDmgColor: LiveData<Int>
        get() = _towerCrtDmgColor

    private val _towerMultiCrt = MutableLiveData<Int> ()
    val towerMultiCrt: LiveData<Int>
        get() = _towerMultiCrt

    private val _towerMultiCrtColor = MutableLiveData<Int> ()
    val towerMultiCrtColor: LiveData<Int>
        get() = _towerMultiCrtColor

    private val _towerArmPen = MutableLiveData<Float> ()
    val towerArmPen: LiveData<Float>
        get() = _towerArmPen

    private val _towerArmPenColor = MutableLiveData<Int> ()
    val towerArmPenColor: LiveData<Int>
        get() = _towerArmPenColor

    private val _towerMgcPenColor = MutableLiveData<Int> ()
    val towerMgcPenColor: LiveData<Int>
        get() = _towerMgcPenColor

    private val _towerMgcPen = MutableLiveData<Float> ()
    val towerMgcPen: LiveData<Float>
        get() = _towerMgcPen

    private val _towerHit = MutableLiveData<Int> ()
    val towerHit: LiveData<Int>
        get() = _towerHit

    private val _towerHitColor = MutableLiveData<Int> ()
    val towerHitColor: LiveData<Int>
        get() = _towerHitColor

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

    private val _bagColor = MutableLiveData<Int> ()
    val bagColor: LiveData<Int>
        get() = _bagColor

    private val _itemChance = MutableLiveData<Float> ()
    val itemChance: LiveData<Float>
        get() = _itemChance

    private val _itemChanceColor = MutableLiveData<Int> ()
    val itemChanceColor: LiveData<Int>
        get() = _itemChanceColor

    private val _itemQuality = MutableLiveData<Float> ()
    val itemQuality: LiveData<Float>
        get() = _itemQuality

    private val _itemQualityColor = MutableLiveData<Int> ()
    val itemQualityColor: LiveData<Int>
        get() = _itemQualityColor

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

    private val _pictureColor = MutableLiveData<Int> ()
    val pictureColor: LiveData<Int>
        get() = _pictureColor

    init {
        _pictureColor.value = Color.WHITE
        _goldColor.value = Color.WHITE
        _diamondsColor.value = Color.WHITE
        _talentsColor.value = Color.WHITE
        _upgradePointsColor.value = Color.WHITE
        _itemPointsColor.value = Color.WHITE
        _itemChanceColor.value = Color.WHITE
        _itemQualityColor.value = Color.WHITE
        _bagColor.value = Color.WHITE
        _towerDmgColor.value = Color.WHITE
        _towerPhyDmgColor.value = Color.WHITE
        _towerMgcDmgColor.value = Color.WHITE
        _towerCrtColor.value = Color.WHITE
        _towerCrtDmgColor.value = Color.WHITE
        _towerMultiCrtColor.value = Color.WHITE
        _towerHitColor.value = Color.WHITE
        _towerArmPenColor.value = Color.WHITE
        _towerMgcPenColor.value = Color.WHITE
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
        _bagString.value = "4"
        _bag.value = 5
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
    }

    fun update () {

        if (com.agsolutions.td.Companion.mapMode != 2) _lives.postValue(com.agsolutions.td.Companion.lives.toString())
        else _lives.postValue(com.agsolutions.td.Companion.lives.toString() + " / " + com.agsolutions.td.Companion.livesMode2.toString())

        com.agsolutions.td.Companion.readLockTower.lock ()
        try {
            if (com.agsolutions.td.Companion.towerClick) {
                var towerListIterator = com.agsolutions.td.Companion.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()
                    if (tower.selected) {

                        // tower damage
                        if (tower.overallTowerDmg > _towerDmg.value!! || damageGain) {
                            damageGain = true
                            _towerDmgColor.postValue(Color.GREEN)
                        } else _towerDmgColor.postValue(Color.WHITE)

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
                        if (tower.towerPhysicalDmg > _towerPhyDmg.value!! || phyDamageGain) {
                            phyDamageGain = true
                            _towerPhyDmgColor.postValue(Color.GREEN)
                        } else _towerPhyDmgColor.postValue(Color.WHITE)

                        when (tower.towerPhysicalDmg.toInt()) {
                            in 0..999 -> _towerPhyDmgString.postValue(tower.towerPhysicalDmg.toInt()
                                .toString())
                            in 1000..999999 -> _towerPhyDmgString.postValue((tower.towerPhysicalDmg / 1000).round(1)
                                .toString() + "k")
                            in 1000000..999999999 -> _towerPhyDmgString.postValue((tower.towerPhysicalDmg / 1000000).round(1)
                                .toString() + "M")
                        }
                        _towerPhyDmg.postValue(tower.towerPhysicalDmg)

                        // spell damage
                        if (tower.overallSpellDmg > _towerMgcDmg.value!! || mgcDamageGain) {
                            mgcDamageGain = true
                            _towerMgcDmgColor.postValue(Color.GREEN)
                        } else _towerMgcDmgColor.postValue(Color.WHITE)

                        when (tower.overallSpellDmg.toInt()) {
                            in 0..999 -> _towerMgcDmgString.postValue(tower.overallSpellDmg.toInt()
                                .toString())
                            in 1000..999999 -> _towerMgcDmgString.postValue((tower.overallSpellDmg / 1000).round(1)
                                .toString() + "k")
                            in 1000000..999999999 -> _towerMgcDmgString.postValue((tower.overallSpellDmg / 1000000).round(1)
                                .toString() + "M")
                        }
                        _towerMgcDmg.postValue(tower.overallSpellDmg)

                        // tower speed
                        if ((tower.towerAttackSpeedShow * 20).round(1) < _towerSpd.value!! || spdGain) {
                            spdGain = true
                            _towerSpdColor.postValue(Color.GREEN)
                        } else _towerSpdColor.postValue(Color.WHITE)

                        _towerSpd.postValue((tower.towerAttackSpeedShow * 20).round(1))

                        // crit chance
                        if (tower.overallCrit > _towerCrt.value!! || critChanceGain) {
                            critChanceGain = true
                            _towerCrtColor.postValue(Color.GREEN)
                        } else _towerCrtColor.postValue(Color.WHITE)

                        _towerCrtString.postValue(tower.overallCrit.round(1).toString() + "%")
                        _towerCrt.postValue(tower.overallCrit)

                        // crit damage
                        if (tower.overallCritDmg.round(1) > _towerCrtDmg.value!! || critDamageGain) {
                            critDamageGain = true
                            _towerCrtDmgColor.postValue(Color.GREEN)
                        } else _towerCrtDmgColor.postValue(Color.WHITE)

                        _towerCrtDmg.postValue(tower.overallCritDmg.round(1))

                        // multi crit
                        if ((tower.overallMulticrit - 1) > _towerCrt.value!! || multiCritGain) {
                            multiCritGain = true
                            _towerMultiCrtColor.postValue(Color.GREEN)
                        } else _towerMultiCrtColor.postValue(Color.WHITE)

                        _towerMultiCrt.postValue((tower.overallMulticrit - 1))

                        // tower hit
                        if ((tower.hitChance.toInt()) > _towerHit.value!! || hitGain) {
                            hitGain = true
                            _towerHitColor.postValue(Color.GREEN)
                        } else _towerHitColor.postValue(Color.WHITE)

                        _towerHit.postValue(tower.hitChance.toInt())

                        // armor pen
                        if ((tower.overallBonusArmorPen.round(1)) > _towerArmPen.value!! || armorPenGain) {
                            armorPenGain = true
                            _towerArmPenColor.postValue(Color.GREEN)
                        } else _towerArmPenColor.postValue(Color.WHITE)
                        _towerArmPen.postValue(tower.overallBonusArmorPen.round(1))

                        // magic pen
                        if ((tower.overallBonusMagicPen.round(1)) > _towerMgcPen.value!! || magicArmorPenGain) {
                            magicArmorPenGain = true
                            _towerMgcPenColor.postValue(Color.GREEN)
                        } else _towerMgcPenColor.postValue(Color.WHITE)
                        _towerMgcPen.postValue(tower.overallBonusMagicPen.round(1))

                        // item chance
                        if (tower.overallItemChance.round(1) > _itemChance.value!! || itemChanceGain) {
                            itemChanceGain = true
                            _itemChanceColor.postValue(Color.GREEN)
                        } else _itemChanceColor.postValue(Color.WHITE)
                        _itemChance.postValue(tower.overallItemChance.round(1))

                        // item quality
                        if (tower.overallItemQuality.round(1) > _itemQuality.value!! || itemQualityGain) {
                            itemQualityGain = true
                            _itemQualityColor.postValue(Color.GREEN)
                        } else _itemQualityColor.postValue(Color.WHITE)
                        _itemQuality.postValue(tower.overallItemQuality.round(1))

                        // bag size
                        if (tower.itemListBag.size == tower.bagSize + 1) {
                            _bagColor.postValue(Color.RED)
                        } else _bagColor.postValue(Color.WHITE)
                        _bag.postValue((tower.itemListBag.size))
                        _bagString.postValue((tower.itemListBag.size).toString() + " / " + (tower.bagSize + 1).toString())

                        break
                    }
                }
                }
                }finally {
                    com.agsolutions.td.Companion.readLockTower.unlock()
                }

        // diamonds
        if (com.agsolutions.td.Companion.diamonds > _diamonds.value!! || diamondsGain) {
            diamondsGain = true
            _diamondsColor.postValue(Color.GREEN)
        } else _diamondsColor.postValue(Color.WHITE)
        _diamonds.postValue(com.agsolutions.td.Companion.diamonds)

        // talent points
        if (com.agsolutions.td.Companion.talentPoints > _talents.value!! || talentsGain) {
            talentsGain = true
            _talentsColor.postValue(Color.GREEN)
        } else if (com.agsolutions.td.Companion.talentPoints > 1) {
            if (hintsBool) {
                talentGainCountRed++
                if (talentGainCountRed > 40) talentGainCountRed = 0
                else if (talentGainCountRed > 20) _talentsColor.postValue(Color.WHITE)
                else _talentsColor.postValue(Color.RED)
            } else _talentsColor.postValue(Color.RED)
        } else _talentsColor.postValue(Color.WHITE)
        _talents.postValue(com.agsolutions.td.Companion.talentPoints)

        // interest
        if (com.agsolutions.td.Companion.interest > _interest.value!! || interestGain) {
            interestGain = true
            _interestColor.postValue(Color.GREEN)
        } else _interestColor.postValue(Color.WHITE)

        _interest.postValue(com.agsolutions.td.Companion.interest)
        _interestString.postValue((ceil((com.agsolutions.td.Companion.interest - 1) * 100)).toInt().toString() + "%")

        // upgrade points
        if (com.agsolutions.td.Companion.upgradePoints > _upgradePoints.value!! || upgradePointsGain) {
            upgradePointsGain = true
            _upgradePointsColor.postValue(Color.GREEN)
        } else if (com.agsolutions.td.Companion.upgradePoints > 5) _upgradePointsColor.postValue(Color.RED)
        else _upgradePointsColor.postValue(Color.WHITE)
        _upgradePoints.postValue(com.agsolutions.td.Companion.upgradePoints)

        // item points
        if (com.agsolutions.td.Companion.itemPoints.toInt() > _itemPoints.value!! || itemPointsGain) {
            itemPointsGain = true
            _itemPointsColor.postValue(Color.GREEN)
        } else _itemPointsColor.postValue(Color.WHITE)
        _itemPoints.postValue(com.agsolutions.td.Companion.itemPoints.toInt())

        if (dayNightMinute < 10) _dayNight.postValue(com.agsolutions.td.Companion.dayNightHour.toString() + ":0" + com.agsolutions.td.Companion.dayNightMinute.toString())
        else _dayNight.postValue(com.agsolutions.td.Companion.dayNightHour.toString() + ":" + com.agsolutions.td.Companion.dayNightMinute.toString())
        if (day) _dayNightPic.postValue(R.drawable.suntransparent)
        else _dayNightPic.postValue(R.drawable.moontransparent)

        _textMain.postValue(com.agsolutions.td.Companion.scaleTextMain)
        _textNews.postValue(com.agsolutions.td.Companion.scaleTextNews)
        _textStats.postValue(com.agsolutions.td.Companion.scaleTextStats)
        _textStatsPic.postValue((com.agsolutions.td.Companion.scaleTextStats * 0.6).toInt().toFloat())
        _textButton.postValue(com.agsolutions.td.Companion.scaleTextButton)
        _textBig.postValue(com.agsolutions.td.Companion.scaleTextBig)

    }

    fun updateLvlStats () {
        when (com.agsolutions.td.Companion.enemyHp.toInt()){
            in 0..999 -> _lvlHp.postValue(com.agsolutions.td.Companion.Companion.enemyHp.toInt().toString()+ " /")
            in 1000..999999 -> _lvlHp.postValue((com.agsolutions.td.Companion.Companion.enemyHp/1000).round(1).toString() + "k" + " /")
            in 1000000..999999999 -> _lvlHp.postValue((com.agsolutions.td.Companion.Companion.enemyHp/1000000).round(1).toString() + "M"+ " /")
        }
        when (com.agsolutions.td.Companion.enemyMaxHp.toInt()){
            in 0..999 -> _lvlMaxHp.postValue(com.agsolutions.td.Companion.Companion.enemyMaxHp.toInt().toString())
            in 1000..999999 -> _lvlMaxHp.postValue((com.agsolutions.td.Companion.Companion.enemyMaxHp/1000).round(1).toString() + "k")
            in 1000000..999999999 -> _lvlMaxHp.postValue((com.agsolutions.td.Companion.Companion.enemyMaxHp/1000000).round(1).toString() + "M")
        }
        when (com.agsolutions.td.Companion.enemyShield.toInt()){
            in 0..999 -> _lvlShield.postValue(com.agsolutions.td.Companion.Companion.enemyShield.toInt().toString()+ " /")
            in 1000..999999 -> _lvlShield.postValue((com.agsolutions.td.Companion.Companion.enemyShield/1000).round(1).toString() + "k" + " /")
            in 1000000..999999999 -> _lvlShield.postValue((com.agsolutions.td.Companion.Companion.enemyShield/1000000).round(1).toString() + "M"+ " /")
        }
        when (com.agsolutions.td.Companion.enemyShieldMax.toInt()){
            in 0..999 -> _lvlMaxShield.postValue(com.agsolutions.td.Companion.Companion.enemyShieldMax.toInt().toString())
            in 1000..999999 -> _lvlMaxShield.postValue((com.agsolutions.td.Companion.Companion.enemyShieldMax/1000).round(1).toString() + "k")
            in 1000000..999999999 -> _lvlMaxShield.postValue((com.agsolutions.td.Companion.Companion.enemyShieldMax/1000000).round(1).toString() + "M")
        }
        when (com.agsolutions.td.Companion.enemyManaShield.toInt()){
            in 0..999 -> _lvlManaShield.postValue(com.agsolutions.td.Companion.Companion.enemyManaShield.toInt().toString()+ " /")
            in 1000..999999 -> _lvlManaShield.postValue((com.agsolutions.td.Companion.Companion.enemyManaShield/1000).round(1).toString() + "k" + " /")
            in 1000000..999999999 -> _lvlManaShield.postValue((com.agsolutions.td.Companion.Companion.enemyManaShield/1000000).round(1).toString() + "M"+ " /")
        }
        when (com.agsolutions.td.Companion.enemyManaShieldMax.toInt()){
            in 0..999 -> _lvlMaxManaShield.postValue(com.agsolutions.td.Companion.Companion.enemyManaShieldMax.toInt().toString())
            in 1000..999999 -> _lvlMaxManaShield.postValue((com.agsolutions.td.Companion.Companion.enemyManaShieldMax/1000).round(1).toString() + "k")
            in 1000000..999999999 -> _lvlMaxManaShield.postValue((com.agsolutions.td.Companion.Companion.enemyManaShieldMax/1000000).round(1).toString() + "M")
        }
        _lvlArmor.postValue(com.agsolutions.td.Companion.enemyArmorReduction.round(1).toString() + "%)")
        _lvlArmorRating.postValue("(" + com.agsolutions.td.Companion.enemyArmor.toInt().toString() + "/")
        _lvlMagicArmor.postValue(com.agsolutions.td.Companion.enemyMagicArmorReduction.round(1).toString() + "%)")
        _lvlMagicArmorRating.postValue("(" + com.agsolutions.td.Companion.enemyMagicArmor.toInt().toString() + "/")
        if (!day && endlessNight > 0) _lvlEvade.postValue((100 - (100 - ((((100 + (3f* endlessNight)) * (((com.agsolutions.td.Companion.enemyEvade + com.agsolutions.td.Companion.Companion.evadeGlobal + (com.agsolutions.td.Companion.evadeNight - (3f * endlessNight))) * 0.06f) / (1f + (0.06f * (com.agsolutions.td.Companion.enemyEvade + com.agsolutions.td.Companion.Companion.evadeGlobal+(com.agsolutions.td.Companion.evadeNight - (3f * endlessNight))))))))))).round(1).toString() + "%")
        else {
            var x = (100 - ((((100) * (((com.agsolutions.td.Companion.enemyEvade + com.agsolutions.td.Companion.evadeGlobal + com.agsolutions.td.Companion.evadeNight) * 0.06f) / (1f + (0.06f * (com.agsolutions.td.Companion.enemyEvade + evadeGlobal + com.agsolutions.td.Companion.evadeNight))))))))
            if (x > 100) x = 100f
            _lvlEvade.postValue(((100 - x)).round(1).toString() + "%")
        }
        _lvlHpReg.postValue(com.agsolutions.td.Companion.enemyHpReg.round(1))
        _lvlSpd.postValue(com.agsolutions.td.Companion.enemySpd.round(1))
        _lvlHpBar.postValue((com.agsolutions.td.Companion.Companion.enemyHp).toInt())
        _lvlMaxHpBar.postValue(com.agsolutions.td.Companion.Companion.enemyMaxHp.toInt())
        _lvlShieldBar.postValue((com.agsolutions.td.Companion.Companion.enemyShield).toInt())
        _lvlMaxShieldBar.postValue(com.agsolutions.td.Companion.Companion.enemyShieldMax.toInt())
        _lvlManaShieldBar.postValue((com.agsolutions.td.Companion.Companion.enemyManaShield).toInt())
        _lvlMaxManaShieldBar.postValue(com.agsolutions.td.Companion.Companion.enemyManaShieldMax.toInt())
        _enemyTypeSpecific.postValue(com.agsolutions.td.Companion.enemyType)
    }

    fun updateCounter () {

        if (enemyList.isEmpty()) _lvlCounter.postValue(((levelCountPlace - levelCount) / 60).toString())
        else _lvlCounter.postValue("")
    }

    fun updateXp () {
        if (com.agsolutions.td.Companion.xp.toInt() > _xp.value!! || goldGain) {
            goldGain = true
            _goldColor.postValue(Color.GREEN)
        } else _goldColor.postValue(Color.WHITE)

        when (com.agsolutions.td.Companion.xp.toInt()){
            in 0..999 -> _xpString.postValue(com.agsolutions.td.Companion.xp.toInt().toString())
            in 1000..999999 -> _xpString.postValue((com.agsolutions.td.Companion.xp/1000).round(1).toString() + "k")
            in 1000000..999999999 -> _xpString.postValue((com.agsolutions.td.Companion.xp/1000000).round(1).toString() + "M")
        }
        _xp.postValue(com.agsolutions.td.Companion.xp)
    }

    fun updateLvlStatus () {
        _enemyType.postValue(levelStatus)
    }

    fun onLvlUp () {
        _level.postValue(com.agsolutions.td.Companion.level)
    }

}

/*
if (towerPhysicalDmg > overallSpellDmg && towerPhysicalDmg > overallTowerDmg * 1.25){
            _pictureDmg.postValue(R.drawable.swordicon)
            if (com.agsolutions.td.Companion.towerPhysicalDmg > _towerPhyDmg.value!! || phyDamageGain) {
                _pictureColor.postValue(Color.GREEN)
            } else _pictureColor.postValue(Color.WHITE)
            when (towerPhysicalDmg.toInt()){
                in 0..999 -> _pictureValue.postValue(towerPhysicalDmg.toInt().toString())
                in 1000..999999 -> _pictureValue.postValue((towerPhysicalDmg/1000).round(1).toString() + "k")
                in 1000000..999999999 -> _pictureValue.postValue((towerPhysicalDmg/1000000).round(1).toString() + "M")
            }
        } else if (overallSpellDmg > towerPhysicalDmg && overallSpellDmg > overallTowerDmg * 1.25) {
            _pictureDmg.postValue(R.drawable.wandicon)
            if (com.agsolutions.td.Companion.overallSpellDmg > _towerMgcDmg.value!! || mgcDamageGain) {
                _pictureColor.postValue(Color.GREEN)
            } else _pictureColor.postValue(Color.WHITE)
            when (com.agsolutions.td.Companion.overallSpellDmg.toInt()){
                in 0..999 -> _pictureValue.postValue(com.agsolutions.td.Companion.overallSpellDmg.toInt().toString())
                in 1000..999999 -> _pictureValue.postValue((com.agsolutions.td.Companion.overallSpellDmg /1000).round(1).toString() + "k")
                in 1000000..999999999 -> _pictureValue.postValue((com.agsolutions.td.Companion.overallSpellDmg /1000000).round(1).toString() + "M")
            }
        } else {
            _pictureDmg.postValue(R.drawable.swordandwandicon)
            if (com.agsolutions.td.Companion.overallTowerDmg > _towerDmg.value!! || damageGain) {
                _pictureColor.postValue(Color.GREEN)
            } else _pictureColor.postValue(Color.WHITE)
            when (overallTowerDmg.toInt()){
                in 0..999 -> _pictureValue.postValue(overallTowerDmg.toInt().toString())
                in 1000..999999 -> _pictureValue.postValue((overallTowerDmg/1000).round(1).toString() + "k")
                in 1000000..999999999 -> _pictureValue.postValue((overallTowerDmg/1000000).round(1).toString() + "M")
            }
        }
 */