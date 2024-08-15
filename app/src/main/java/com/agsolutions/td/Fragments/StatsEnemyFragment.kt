package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.UpdateViewModel
import com.agsolutions.td.databinding.FragmentStatsEnemyBinding


class StatsEnemyFragment (var updateViewModel: UpdateViewModel) : Fragment() {

    private var _binding: FragmentStatsEnemyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsEnemyBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh()
        setListeners()
        observeViewmodel()
    }

    fun setListeners() {

        val scalePics = 1.5

        with(binding) {
            lvlArmorTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            lvlArmorTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            lvlMagicArmorTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            lvlMagicArmorTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            lvlEvadeTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            lvlEvadeTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            lvlHpRegTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            lvlHpRegTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
            lvlSpdTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
            lvlSpdTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()

            shieldBar.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Earth Shield - use physical dmg to break"
            }
            manaShieldBar.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Mana Shield - use magic dmg to break"
            }
            hpBar.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Hit Point Bar"
            }
            lvlTypeTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Enemy Type"
            }
            lvlArmorTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Armor - reduces physical damage done (Armor Rating / Physical Damage Reduction)"
            }
            lvlArmorShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Armor - reduces physical damage done (Armor Rating / Physical Damage Reduction)"
            }
            lvlArmorRatingShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Armor - reduces physical damage done (Armor Rating / Physical Damage Reduction)"
            }
            lvlMagicArmorTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Magic Armor - reduces spell damage done (Magic Armor Rating / Spell Damage Reduction)"
            }
            lvlMagicArmorShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Magic Armor - reduces spell damage done (Magic Armor Rating / Spell Damage Reduction)"
            }
            lvlMagicArmorRatingShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText =
                    "Magic Armor - reduces spell damage done (Magic Armor Rating / Spell Damage Reduction)"
            }
            lvlEvadeTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Evade chance - chance to evade attacks in percent"
            }
            lvlEvadeShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Evade chance - chance to evade attacks in percent"
            }
            lvlHpRegTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Hitpoint Regeneration per tick"
            }
            lvlHpRegShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Hitpoint Regeneration per tick"
            }
            lvlSpdTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Movement Speed"
            }
            lvlSpdShowTV.setOnClickListener() {
                companionList.toastGlobal = true
                companionList.toastText = "Movement Speed"
            }
        }
    }

    fun observeViewmodel() {

        with(updateViewModel) {

            lvlShieldBar.observe(viewLifecycleOwner) { lvlShieldBar ->
                binding.shieldBar.progress = lvlShieldBar
            }
            lvlManaShieldBar.observe(viewLifecycleOwner) { lvlManaShieldBar ->
                binding.manaShieldBar.progress = lvlManaShieldBar
            }
            lvlHpBar.observe(viewLifecycleOwner) { lvlHpBar ->
                binding.hpBar.progress = lvlHpBar
            }
            lvlMaxHpBar.observe(viewLifecycleOwner) { lvlMaxHpBar ->
                binding.hpBar.max = lvlMaxHpBar
            }
            lvlMaxShieldBar.observe(viewLifecycleOwner) { lvlMaxShieldBar ->
                binding.shieldBar.max = lvlMaxShieldBar
            }
            lvlMaxManaShieldBar.observe(viewLifecycleOwner) { lvlMaxManaShieldBar ->
                binding.manaShieldBar.max = lvlMaxManaShieldBar
            }
            lvlShield.observe(viewLifecycleOwner) { lvlShield ->
                binding.lvlShieldShowTV.text = lvlShield.toString()
            }
            lvlManaShield.observe(viewLifecycleOwner) { lvlManaShield ->
                binding.lvlmanaShieldShowTV.text = lvlManaShield.toString()
            }
            lvlMaxManaShield.observe(viewLifecycleOwner) { lvlMaxManaShield ->
                binding.lvlMaxManaShieldShowTV.text = lvlMaxManaShield.toString()
            }
            lvlMaxShield.observe(viewLifecycleOwner) { lvlMaxShield ->
                binding.lvlMaxShieldShowTV.text = lvlMaxShield.toString()
            }
            lvlHp.observe(viewLifecycleOwner) { lvlHp ->
                binding.lvlHpShowTV.text = lvlHp.toString()
            }
            lvlMaxHp.observe(viewLifecycleOwner) { lvlMaxHp ->
                binding.lvlMaxHpShowTV.text = lvlMaxHp.toString()
            }
            enemyTypeSpecific.observe(viewLifecycleOwner) { lvlType ->
                binding.lvlTypeTV.text = lvlType
            }
            lvlArmor.observe(viewLifecycleOwner) { lvlArmor ->
                binding.lvlArmorShowTV.text = lvlArmor.toString()
            }
            lvlArmorRating.observe(viewLifecycleOwner) { lvlArmorRating ->
                binding.lvlArmorRatingShowTV.text = lvlArmorRating.toString()
            }
            lvlMagicArmor.observe(viewLifecycleOwner) { lvlMagicArmor ->
                binding.lvlMagicArmorShowTV.text = lvlMagicArmor.toString()
            }
            lvlMagicArmorRating.observe(viewLifecycleOwner) { lvlMagicArmorRating ->
                binding.lvlMagicArmorRatingShowTV.text = lvlMagicArmorRating.toString()
            }
            lvlEvade.observe(viewLifecycleOwner) { lvlEvade ->
                binding.lvlEvadeShowTV.text = lvlEvade.toString()
            }
            lvlHpReg.observe(viewLifecycleOwner) { lvlHpReg ->
                binding.lvlHpRegShowTV.text = lvlHpReg.toString()
            }
            lvlSpd.observe(viewLifecycleOwner) { lvlSpd ->
                binding.lvlSpdShowTV.text = lvlSpd.toString()
            }



            textMain.observe(viewLifecycleOwner) { textMain ->
                binding.lvlShieldShowTV.textSize = textMain
                binding.lvlMaxShieldShowTV.textSize = textMain
                binding.lvlmanaShieldShowTV.textSize = textMain
                binding.lvlMaxManaShieldShowTV.textSize = textMain
                binding.lvlHpShowTV.textSize = textMain
                binding.lvlMaxHpShowTV.textSize = textMain
                binding.lvlTypeTV.textSize = textMain
            }

            textStats.observe(viewLifecycleOwner) { textStats ->
                binding.lvlArmorShowTV.textSize = textStats
                binding.lvlArmorRatingShowTV.textSize = textStats
                binding.lvlMagicArmorShowTV.textSize = textStats
                binding.lvlMagicArmorRatingShowTV.textSize = textStats
                binding.lvlEvadeShowTV.textSize = textStats
                binding.lvlHpRegShowTV.textSize = textStats
                binding.lvlSpdShowTV.textSize = textStats
            }
        }
    }

    fun refresh(){

        with(binding) {
            if (companionList.enemyShield > 0) {
                shieldBar.visibility = View.VISIBLE
                lvlShieldShowTV.visibility = View.VISIBLE
                lvlMaxShieldShowTV.visibility = View.VISIBLE
            } else {
                shieldBar.visibility = View.INVISIBLE
                lvlShieldShowTV.visibility = View.INVISIBLE
                lvlMaxShieldShowTV.visibility = View.INVISIBLE
            }

            if (companionList.enemyManaShield > 0) {
                manaShieldBar.visibility = View.VISIBLE
                lvlmanaShieldShowTV.visibility = View.VISIBLE
                lvlMaxManaShieldShowTV.visibility = View.VISIBLE
            } else {
                manaShieldBar.visibility = View.INVISIBLE
                lvlmanaShieldShowTV.visibility = View.INVISIBLE
                lvlMaxManaShieldShowTV.visibility = View.INVISIBLE
            }
        }
    }
}