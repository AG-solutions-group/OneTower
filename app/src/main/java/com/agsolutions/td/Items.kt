package com.agsolutions.td

import com.agsolutions.td.Companion.Companion.level
import com.agsolutions.td.Companion.Companion.lvlScaler
import java.io.Serializable

class Items (val id: Int, val minLvl: Short, val maxLvl: Short, var lvlAqu: Int, var goldCost: Float, var diaCost: Short, var ipCost: Float, var mpCost: Short, val name: String, var image: Int, var imageOverlay: Int, var dmg: Float, var atkDmg: Float, var mgcDmg: Float, var speed: Float, var crit: Float, var critDmg: Float, var upgrade: Int, var special: String, var specialFloat: Float, var special2: String, var specialFloat2: Float) : Serializable {

    var element = false

    companion object {



        var stt0 = Items(2100, 0, 999,0,0f, 0, 0f, 0, "Rare Earth Tower", R.drawable.talentsearth, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt1 = Items(2101, 0, 999,0,0f, 0, 0f, 0, "Rare Wizard Tower", R.drawable.talentswizard, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt2 = Items(2102, 0, 999,0,0f, 0, 0f, 0, "Rare Ice Tower", R.drawable.talentsice, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt3 = Items(2103, 0, 999,0,0f, 0, 0f, 0, "Rare Butterfly Tower", R.drawable.talentsbutterfly, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt4 = Items(2104, 0, 999,0,0f, 0, 0f, 0, "Rare Poison Tower", R.drawable.talentspoison, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt5 = Items(2105, 0, 999,0,0f, 0, 0f, 0, "Rare Moon Tower", R.drawable.moon, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt6 = Items(2106, 0, 999,0,0f, 0, 0f, 0, "Rare Wind Tower", R.drawable.talentswind, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0, "bagspace", 3.0f, "bagspace element", 2f)
        var stt7 = Items(2107, 0, 999,0,0f, 0, 0f, 0, "Rare Utils Tower", R.drawable.talentsutils, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0,"bagspace", 3.0f, "bagspace element", 2f)
        var stt8 = Items(2108, 0, 999,0,0f, 0, 0f, 0, "Rare Fire Tower", R.drawable.talentsfire, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0,"bagspace", 3.0f, "bagspace element", 2f)
        var stt9 = Items(2109, 0, 999,0,0f, 0, 0f, 0, "Rare Dark Tower", R.drawable.talentsdark, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)

        var startTowerList = mutableListOf<Items>(stt0, stt1, stt2, stt3, stt4, stt5, stt6, stt7, stt8, stt9)

        var itemListNormal = mutableListOf<Int>()
        var itemListRare = mutableListOf<Int>()
        var itemListEpic = mutableListOf<Int>()
        var itemListLegendary = mutableListOf<Int>()

        var stid0 = Items(5000, 0, 999,0,0f, 0,0f, 0,"Starter Dmg", R.drawable.wandswordgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0, "+ dmg / lvl", 0f, "", 0f)
        var stid1 = Items(5001, 1, 999,0,0f, 0,0f, 0,"Starter Speed", R.drawable.pbowgrey, R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0, "+ speed / lvl", 0f, "", 0f)
        var stid2 = Items(5002, 3, 999,0,0f, 0,0f, 0,"Starter Crit", R.drawable.pcritgrey, R.drawable.overlaytransparent, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0.0f,0, "+ crit / lvl", 0f, "", 0f)
        var stid3 = Items(5003, 4, 999,0,0f, 0,0f, 0,"Starter Physical", R.drawable.pdoubleswordsblue, R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0, "+10% physical dmg, + phyDmg/lvl", 0f, "", 0f)
        var stid4 = Items(5004, 5, 999,0,0f, 0,0f, 0,"Starter SpellDmg", R.drawable.zauberstabblue, R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"+10% magic dmg, + mgcDmg/lvl ", 0f, "", 0f)
        var stid5 = Items(5005, 6, 999,0,0f, 0,0f, 0,"Crit Master", R.drawable.pcritblue, R.drawable.overlaytransparent, 0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"+ 1 multi crit, + crit dmg/lvl, + crit/lvl", 0f, "", 0f)
        var stid6 = Items(5006, 7, 999,0,0f, 0,0f, 0,"Froster", R.drawable.frostlila, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"+ mgcDmg/lvl, + 10% slow, Ice Nova CD halved ", 0f, "", 0f)
        var stid7 = Items(5007, 8, 999,0,0f, 0,0f, 0,"Utilizer", R.drawable.utilizerpurple, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"+ 1% interest, + 2 bag slots, +10% item find", 0f, "", 0f)
        var stid8 = Items(5008, 9, 999,0,0f, 0,0f, 0,"Shield Braker", R.drawable.shieldbrakerpurple, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"ignores shields", 0f, "", 0f)
        var stid9 = Items(5009, 10, 999,0,0f, 0,0f, 0,"Natures Gift", R.drawable.itemstartpoison, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"starts with 1 point in Death Cap, +1 stack per hit", 0f, "", 0f)
        var stid10 = Items(5010, 11, 999,0,0f, 0,0f, 0,"Easy Mode", R.drawable.itemstarteasymode, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f,0,"consumable, enemies start with 10% less hp", 0f, "", 0f)
        var stid11 = Items(5011, 12, 999,0,0f, 0, 0f, 0, "Helping Hand", R.drawable.helpinghandpurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "active: +30% DMG for 5 sec. 1 min CD.", 0.0f, "", 0f)
        var stid12 = Items(5012, 13, 999,0,0f, 0, 0f, 0, "BombermXn", R.drawable.bombpurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "start with 5 bombs, bombs +10% dmg, bombs 50% cheaper", 0.0f, "", 0f)
        var stid13 = Items(5013, 14, 999,0,0f, 0, 0f, 0, "Talented", R.drawable.talentpurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "start with 2 TP, +1 TP for each killed boss/challenge", 0.0f, "", 0f)
        var stid14 = Items(5014, 15, 999,0,0f, 0, 0f, 0, "Darker than Black", R.drawable.itemstartdark, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "consumable, get 1 TP in Soul Collector at lvl 20", 0.0f, "", 0f)
        var stid15 = Items(5015, 15, 999,0,0f, 0, 0f, 0, "Wizard", R.drawable.wizardpueple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "Bombshell: +40% spelldmg scaling bomb, Lightning Bolt: +1 target", 0.0f, "", 0f)
        var stid16 = Items(5016, 15, 999,0,0f, 0, 0f, 0, "Bouncer", R.drawable.bouncepurple, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "starts with 1 TP in bounce, bounce targets doubled ", 0.0f, "", 0f)
        var stid17 = Items(5017, 15, 999,0,0f, 0, 0f, 0, "Upgrader", R.drawable.upgraderitem, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "all items 2x upgrade slots, 1 UPG point every 4 lvls, starts with 3 UPG points", 0.0f, "", 0f)

        var startItemList = mutableListOf<Items>(stid0)
        var startItemHiddenList = mutableListOf<Items>(stid1, stid2, stid3, stid4, stid5, stid6, stid7, stid8, stid9, stid10, stid11, stid12, stid13, stid14, stid15, stid16, stid17)

        //------------------------------------------------------------------

        // normal items

         var id0 = Items(0, 1, 999,0,10f, 0, 0f, 0,"Sword", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,2.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
         var id1 = Items(1, 1, 999,0,10f, 0,0f, 0, "Bow", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,5.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
         var id2 = Items(2, 1, 999,0,10f, 0, 0f, 0,"Nunchucks", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f,0.0f,0.0f, 0.0f, 1.0f, 0.0f, 0, "", 0f, "", 0f)
         var id3 = Items(3, 1, 25,0,0f, 0,0f, 0, "Magic Box", R.drawable.ic_launcher_background, R.drawable.overlaytransparent,1.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "no use cost", 0f, "", 0f)
         var id4 = Items(4, 1, 999,0,(20f), 0, 0f, 0,"Sword", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, (3.0f), 0.0f, 0, "", 0f, "", 0f)
         var id5 = Items(5, 1, 999, 0, 0f, 0, 0f, 0,"Bomb", R.drawable.bombgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "Detonate on Remove: 20% dmg to all enemies", 0f, "", 0f)
         var id6 = Items(6, 1, 999, 0, 0f, 0, 0f, 0, "Experiencer", R.drawable.xpgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: gain xp", 0f, "", 0f)
        // rare items

         var id100 = Items(100, 1, 999,0,30f, 0,0f, 0, "Rare Sword", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,3.0f, 0.0f,0.0f,0.0f, 1.0f, 0.0f, 1, "", 0f, "", 0f)
         var id101  = Items(101, 1, 25,0,0f, 0, 0f, 0,"Rare Magic Box", R.drawable.ic_launcher_background, R.drawable.overlaytransparent,1.5f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "no use cost", 0f, "", 0f)
        var id102  = Items(102, 1, 999,0,30f, 0,0f, 0, "Rare Bow", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,7.5f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
        var id103  = Items(103, 1, 999,0,30f, 0, 0f, 0,"Rare Bow", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,7.5f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
        var id104  = Items(104, 30, 999,0,(10f), 0, 0f, 0,"Lucky Charm", R.drawable.luckycharmblue, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "plus itemquality",  10.0f, "", 0f)
        var id105  = Items(105, 1, 999, 0, 0f, 1, 0f, 0,"Piggy Bank", R.drawable.luckycharmblue, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0f, 0.0f, 0, "10% more XP from enemies", 0f, "", 0f)
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
        var id217  = Items(217, 1, 999, 0, 0f, 0, 0f, 0,"Snowman", R.drawable.snowmanorange, R.drawable.overlaytransparent,((0.5f) + (level * 0.05f)), 0.0f,0.0f,((0.5f) + (level * 0.05f)), ((0.5f) + (level * 0.05f)), 0f, 3, "+5% slow on hit", 5f, "", 0f)
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

        var itemListReserveNormal = mutableListOf<Items>(id0, id1, id2, id3, id4, id5)
        var itemListReserveRare = mutableListOf<Items>(id100, id101, id102, id103, id104, id105, id106, id107)
        var itemListReserveEpic = mutableListOf<Items>(id200, id201, id202, id203, id204, id205, id206, id207, id208, id209, id210, id211, id212, id213, id214, id215, id216, id217)
        var itemListReserveLegendary = mutableListOf<Items>(id300, id301, id302, id303, id304, id305, id306, id307, id308, id309, id310, id311, id312, id313, id314, id315)
        var greyItems = mutableListOf<Int>(0, 1, 2, 3, 4, 5)
        var blueItems = mutableListOf<Int>(100, 101, 102, 103, 104, 105, 106, 107)
        var orangeItems = mutableListOf<Int>(200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217)
        var purpleItems = mutableListOf<Int>(300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315)


        // secret shop ------------------------------------------------------------------

        var sid0 = Items(1000, 1, 999,0,0f, 0, 10f, 0,"Cannon", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent,5.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
        var sid1 = Items(1001, 1, 999,0,0f, 0, 50f, 0,"Rare Cannon", R.drawable.pdoubleswordsblue, R.drawable.overlaytransparent,25.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
        var sid2 = Items(1002, 1, 999,0,0f, 0, 150f, 0,"Epic Cannon", R.drawable.pdoubleswordsorange, R.drawable.overlaytransparent,75.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
        var sid3 = Items(1003, 1, 999,0,0f, 0, 500f, 0,"Legendary Cannon", R.drawable.pdoubleswordspurple, R.drawable.overlaytransparent,250.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
        var sid9 = Items(1000, 1, 999,0,0f, 0, 10f, 0,"Cannon", R.drawable.pbowgrey, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,20.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
        var sid10 = Items(1001, 1, 999,0,0f, 0, 50f, 0,"Rare Cannon", R.drawable.pbowblue, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,100.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
        var sid11 = Items(1002, 1, 999,0,0f, 0, 150f, 0,"Epic Cannon", R.drawable.pboworange, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,300.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)
        var sid12 = Items(1003, 1, 999,0,0f, 0, 500f, 0,"Legendary Cannon", R.drawable.pbowpurple, R.drawable.overlaytransparent,0.0f,0.0f,0.0f, 1000.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f)


        var secretShopList = mutableListOf<Items>()
        var secretShopListBase = mutableListOf<Items>(sid0, sid1, sid2, sid3, sid9, sid10, sid11, sid12)


        // midnight madness ------------------------------------------------------------------

        var mid0 = Items(1100, 1, 999,0,0f, 0, 0f, 0,"Pirate Parrot", R.drawable.pirateparrotred, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "Free Slot. +15 range.", 0f, "", 0f)
        var mid1 = Items(1101, 1, 999,0,0f, 0, 0f, 0,"Pirate Flag", R.drawable.pirateflagred, R.drawable.overlaytransparent,((2.0f * com.agsolutions.td.Companion.lvlScaler) + (level * 0.075f)), 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "Free Slot", 0f, "", 0f)
        var mid2 = Items(1102, 1, 999,0,0f, 0, 0f, 0,"Pirate Hat", R.drawable.piratehatred, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,((8.0f * com.agsolutions.td.Companion.lvlScaler) + (level * 0.075f)), 0.0f, 0.0f, 0, "Free Slot", 0f, "", 0f)
        var mid3 = Items(1103, 1, 999,0,0f, 0, 0f, 0,"Pirate Hook", R.drawable.piratehookred, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, ((6.0f * com.agsolutions.td.Companion.lvlScaler) + (level * 0.075f)), 0.0f, 0, "Free Slot", 0f, "", 0f)
        var mid4 = Items(1104, 1, 999,0,0f, 0, 0f, 0,"Pirate Leg", R.drawable.piratelegred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "Free Slot; +20% DMG; -10% SPD", 0f, "", 0f)
        var mid5 = Items(1105, 1, 999,0,0f, 0, 0f, 0,"Pirate Saber", R.drawable.piratesaberred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0.0f, 0.0f, 0, "Free Slot; -10% DMG; +20% SPD", 0f, "", 0f)
        var redItems = mutableListOf<Int>(-1, 1100, 1101, 1102, 1103, 1104, 1105)

        // tower ------------------------------------------------------------------

        var towerListNormal = mutableListOf<Int>()
        var towerListRare = mutableListOf<Int>()
        var towerListEpic = mutableListOf<Int>()
        var towerListLegendary = mutableListOf<Int>()


        var eearth = Items(3000, 0, 999, 0, 0f, 1, 0f, 0, "Earth Element", R.drawable.talentsearth, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Earth Abilities", 0.0f, "Adds a medium aoe to all hits.", 0f)
        var ewizard = Items(3001, 0, 999, 0, 0f, 1, 0f, 0, "Wizard Element", R.drawable.talentswizard, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Wizard Abilities", 0.0f, "Places a bomb on a random target that deals aoe dmg.", 0f)
        var eice = Items(3002, 0, 999, 0, 0f, 1, 0f, 0, "Ice Element", R.drawable.talentsice, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Ice Abilities", 0.0f, "Ice Nova. Blasts the area each 4.5 seconds and slows enemies for a short duration.", 0f)
        var ebutterfly = Items(3003, 0, 999, 0, 0f, 1, 0f, 0, "Butterfly Element", R.drawable.talentsbutterfly, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Butterfly Abilities", 0.0f, "Target receives 150% damage at the 3rd consecutive hit.", 0f)
        var epoison = Items(3004, 0, 999, 0, 0f, 1, 0f, 0, "Poison Element", R.drawable.talentspoison, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Poison Abilities", 0.0f, "Applies a stackable poison debuff that deals magic damage over time.", 0f)
        var emoon = Items(3005, 0, 999, 0, 0f, 1, 0f, 0, "Moon Element", R.drawable.moon, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Moon Abilities", 0.0f, "Bullets bounce to 1 additional targets dealing -50% damage each bounce.", 0f)
        var ewind = Items(3006, 0, 999, 0, 0f, 1, 0f, 0, "Wind Element", R.drawable.talentswind, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Wind Abilities", 0.0f, "Multishot hits all targets. Damage is divided by target count + 2.", 0f)
        var eutils = Items(3007, 0, 999, 0, 0f, 1, 0f, 0, "Utils Element", R.drawable.talentsutils, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Utils Abilities", 0.0f, "Increases dmg and spd of nearby towers by 10%", 0f)
        var efire = Items(3008, 0, 999, 0, 0f, 1, 0f, 0, "Fire Element", R.drawable.talentsfire, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Fire Abilities", 0.0f, "Crit dmg + 0.5. Crit chance + 10. Burns enemies critically hit for 4% of max hp as magic damage.", 0f)
        var edark = Items(3009, 0, 999, 0, 0f, 1, 0f, 0, "Dark Element", R.drawable.talentsdark, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "Enables Dark Abilities", 0.0f, "Each hit has a 1.5% chance to instantly kill the target.", 0f)

    }
    var bonusCritDmgLevel = 0f
    var bonusCritLevel = 0f
    var bonusDmgLevel = 0f
    var bonusSpellDmgLevel = 0f
    var crossedOut = false
}



