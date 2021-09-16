package com.agsolutions.td

import java.io.Serializable

class Items (val id: Int, val minLvl: Int, val maxLvl: Int, var lvlAqu: Int, var goldCost: Float, var diaCost: Int, var ipCost: Float, var mpCost: Int, val name: String, var image: Int, var imageOverlay: Int, var dmg: Float, var atkDmg: Float, var mgcDmg: Float, var speed: Float, var crit: Float, var critDmg: Float, var upgrade: Int, var special: String, var specialFloat: Float, var special2: String, var specialFloat2: Float) : Serializable {

    var element = false

    companion object {

    }
    var bonusCritDmgLevel = 0f
    var bonusCritLevel = 0f
    var bonusDmgLevel = 0f
    var bonusSpellDmgLevel = 0f
    var crossedOut = false
}



