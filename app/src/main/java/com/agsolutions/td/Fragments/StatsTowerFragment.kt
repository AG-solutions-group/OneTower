package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.UpdateViewModel
import com.agsolutions.td.databinding.FragmentStatsTowerBinding

class StatsTowerFragment (var updateViewModel: UpdateViewModel) : Fragment() {

    private var _binding: FragmentStatsTowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsTowerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeViewmodel()
    }

    fun setListeners() {

        val scalePics = 1.5

        with(binding) {

            rarityTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            rarityTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            dmgTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            dmgTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            phyDmgTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            phyDmgTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            spellDmgTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            spellDmgTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            speedTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            speedTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            critTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            critTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            critDmgTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            critDmgTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            multiCrtTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            multiCrtTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            hitTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            hitTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            armorPenTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            armorPenTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            magicPenTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            magicPenTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            itemChanceTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            itemChanceTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            bonusItemQualityTV.layoutParams.height =
                (companionList.scaleTextStats * scalePics).toInt()
            bonusItemQualityTV.layoutParams.width =
                (companionList.scaleTextStats * scalePics).toInt()
            bagSizeTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            bagSizeTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            bagSizeElementTV.layoutParams.height =
                (companionList.scaleTextStats * scalePics).toInt()
            bagSizeElementTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            rangeTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            rangeTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            xpMultiTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            xpMultiTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            goldMultiTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            goldMultiTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()

            rarityTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Rarity - rarity of tower"
            }
            dmgTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Damage - bonus physical and spell damage"
            }
            dmgShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Damage - bonus physical and spell damage"
            }
            phyDmgTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Physical Damage - bonus physical damage"
            }
            phyDmgShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Physical Damage - bonus physical damage"
            }
            spellDmgTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Spell Damage - bonus spell damage"
            }
            spellDmgShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Spell Damage - bonus spell damage"
            }
            speedTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Attack Speed - frequency of tower attacks"
            }
            speedShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Attack Speed - frequency of tower attacks"
            }
            critTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Critical Damage Chance - chance of hitting a critical attack"
            }
            critShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Critical Damage Chance - chance of hitting a critical attack"
            }
            critDmgTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Critical Damage - damage multiplier of critical hits"
            }
            critDmgShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Critical Damage - damage multiplier of critical hits"
            }
            multiCrtTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Multi Crit - gives critical hits the chance to multiply critical damage"
            }
            multiCrtShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Multi Crit - gives critical hits the chance to multiply critical damage"
            }
            hitTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Hit Chance - chance to hit enemies in percent"
            }
            hitShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Hit Chance - chance to hit enemies in percent"
            }
            armorPenTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Armor Penetration - rating to reduce enemies armor, making them more vulnerable to physical attacks"
            }
            armorPenShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Armor Penetration - rating to reduce enemies armor, making them more vulnerable to physical attacks"
            }
            magicPenTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Magic Armor Penetration - rating to reduce enemies magic armor, making them more vulnerable to physical attacks"
            }
            magicPenShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Magic Armor Penetration - rating to reduce enemies magic armor, making them more vulnerable to physical attacks"
            }
            itemChanceTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Item Find - chance to find items in percent"
            }
            itemChanceShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Item Find - chance to find items in percent"
            }
            bonusItemQualityTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Item Quality - rating to find better items"
            }
            bonusItemQualityShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Item Quality - rating to find better items"
            }
            bagSizeTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Bag Size - number of items you can use"
            }
            bagUsedShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Bag Size - number of items you can use"
            }
            rangeTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Range - how far your tower can fire"
            }
            rangeShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Range - how far your tower can fire"
            }
            xpMultiTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Xp Multiplier - number of extra experience you gain in %"
            }
            xpMultiShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Xp Multiplier - number of extra experience you gain in %"
            }
            goldMultiTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Gold Multiplier - number of extra gold you earn in %"
            }
            goldMultiShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Gold Multiplier - number of extra gold you earn in %"
            }
        }
    }

    fun observeViewmodel() {

        with(updateViewModel) {

            towerRarity.observe(viewLifecycleOwner) { rarity ->
                binding.rarityShowTV.text = rarity
            }
            towerMgcPen.observe(viewLifecycleOwner) { mgcPen ->
                binding.magicPenShowTV.text = mgcPen.toString()
            }
            towerArmPen.observe(viewLifecycleOwner) { armorPen ->
                binding.armorPenShowTV.text = armorPen.toString()
            }
            towerHit.observe(viewLifecycleOwner) { hit ->
                binding.hitShowTV.text = hit.toString()
            }
            towerMultiCrt.observe(viewLifecycleOwner) { multiCrt ->
                binding.multiCrtShowTV.text = multiCrt.toString()
            }
            towerCrtDmg.observe(viewLifecycleOwner) { critDmg ->
                binding.critDmgShowTV.text = critDmg.toString()
            }
            towerCrtString.observe(viewLifecycleOwner) { critString ->
                binding.critShowTV.text = critString
            }
            towerSpd.observe(viewLifecycleOwner) { speed ->
                binding.speedShowTV.text = speed.toString()
            }
            towerGoldMulti.observe(viewLifecycleOwner) { goldMulti ->
                binding.goldMultiShowTV.text = goldMulti.toString()
            }
            towerXpMulti.observe(viewLifecycleOwner) { xpMulti ->
                binding.xpMultiShowTV.text = xpMulti.toString()
            }
            towerRange.observe(viewLifecycleOwner) { range ->
                binding.rangeShowTV.text = range.toString()
            }
            towerMgcDmgString.observe(viewLifecycleOwner) { mgcDmg ->
                binding.spellDmgShowTV.text = mgcDmg
            }
            towerPhyDmgString.observe(viewLifecycleOwner) { phyDmg ->
                binding.phyDmgShowTV.text = phyDmg
            }
            towerDmgString.observe(viewLifecycleOwner) { dmg ->
                binding.dmgShowTV.text = dmg
            }
            bagStringElement.observe(viewLifecycleOwner) { bagString ->
                binding.bagUsedElementShowTV.text = bagString
            }
            bagString.observe(viewLifecycleOwner) { bagSize ->
                binding.bagUsedShowTV.text = bagSize.toString()
            }
            itemQuality.observe(viewLifecycleOwner) { itemQuality ->
                binding.bonusItemQualityShowTV.text = itemQuality.toString()
            }
            itemChance.observe(viewLifecycleOwner) { itemChance ->
                binding.itemChanceShowTV.text = itemChance.toString()
            }
            towerLevel.observe(viewLifecycleOwner) { level ->
                binding.xpShowTV.text = level.toString()
            }
            towerLevelXpProg.observe(viewLifecycleOwner) { prog ->
                binding.xpBar.progress = prog
            }
            towerLevelXpMin.observe(viewLifecycleOwner) { min ->
                binding.xpBar.min = min
            }
            towerLevelXp.observe(viewLifecycleOwner) { max ->
                binding.xpBar.max = max
            }
            towerRarityMultiplier.observe(viewLifecycleOwner) { rarityMult ->
                binding.rarityMultiShowTV.text = rarityMult.toString()
            }

            textStats.observe(viewLifecycleOwner) { size ->
                binding.rarityShowTV.textSize = size
                binding.rarityMultiShowTV.textSize = size
                binding.xpShowTV.textSize = size
                binding.itemChanceShowTV.textSize = size
                binding.bonusItemQualityShowTV.textSize = size
                binding.bagUsedShowTV.textSize = size
                binding.bagUsedElementShowTV.textSize = size
                binding.dmgShowTV.textSize = size
                binding.phyDmgShowTV.textSize = size
                binding.spellDmgShowTV.textSize = size
                binding.rangeShowTV.textSize = size
                binding.xpMultiShowTV.textSize = size
                binding.goldMultiShowTV.textSize = size
                binding.speedShowTV.textSize = size
                binding.critShowTV.textSize = size
                binding.critDmgShowTV.textSize = size
                binding.multiCrtShowTV.textSize = size
                binding.hitShowTV.textSize = size
                binding.armorPenShowTV.textSize = size
                binding.magicPenShowTV.textSize = size
            }
        }
    }
}