package com.agsolutions.td.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.R
import com.agsolutions.td.UpdateViewModel
import com.agsolutions.td.databinding.FragmentStatsTowerBinding
import kotlinx.android.synthetic.main.fragment_stats.*
import kotlinx.android.synthetic.main.fragment_stats_tower.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatsTowerFragment (var updateViewModel: UpdateViewModel) : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding: FragmentStatsTowerBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_stats_tower,
            container,
            false
        )
        binding.updateViewModel = updateViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var scalePics = 1.5

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
        bonusItemQualityTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
        bonusItemQualityTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
        bagSizeTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
        bagSizeTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
        bagSizeElementTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
        bagSizeElementTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
        rangeTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
        rangeTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
        xpMultiTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
        xpMultiTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()
        goldMultiTV.layoutParams.height = (companionList.scaleTextStats * scalePics).toInt()
        goldMultiTV.layoutParams.width = (companionList.scaleTextStats * scalePics).toInt()

        rarityTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Rarity - rarity of tower"
        }
        dmgTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Damage - bonus physical and spell damage"
        }
        dmgShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Damage - bonus physical and spell damage"
        }
        phyDmgTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Physical Damage - bonus physical damage"
        }
        phyDmgShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Physical Damage - bonus physical damage"
        }
        spellDmgTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Spell Damage - bonus spell damage"
        }
        spellDmgShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Spell Damage - bonus spell damage"
        }
        speedTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Attack Speed - frequency of tower attacks"
        }
        speedShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Attack Speed - frequency of tower attacks"
        }
        critTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Critical Damage Chance - chance of hitting a critical attack"
        }
        critShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Critical Damage Chance - chance of hitting a critical attack"
        }
        critDmgTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Critical Damage - damage multiplier of critical hits"
        }
        critDmgShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Critical Damage - damage multiplier of critical hits"
        }
        multiCrtTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Multi Crit - gives critical hits the chance to multiply critical damage"
        }
        multiCrtShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Multi Crit - gives critical hits the chance to multiply critical damage"
        }
        hitTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Hit Chance - chance to hit enemies in percent"
        }
        hitShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Hit Chance - chance to hit enemies in percent"
        }
        armorPenTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Armor Penetration - rating to reduce enemies armor, making them more vulnerable to physical attacks"
        }
        armorPenShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Armor Penetration - rating to reduce enemies armor, making them more vulnerable to physical attacks"
        }
        magicPenTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Magic Armor Penetration - rating to reduce enemies magic armor, making them more vulnerable to physical attacks"
        }
        magicPenShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Magic Armor Penetration - rating to reduce enemies magic armor, making them more vulnerable to physical attacks"
        }
        itemChanceTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Item Find - chance to find items in percent"
        }
        itemChanceShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Item Find - chance to find items in percent"
        }
        bonusItemQualityTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Item Quality - rating to find better items"
        }
        bonusItemQualityShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Item Quality - rating to find better items"
        }
        bagSizeTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Bag Size - number of items you can use"
        }
        bagUsedShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Bag Size - number of items you can use"
        }
        rangeTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Range - how far your tower can fire"
        }
        rangeShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Range - how far your tower can fire"
        }
        xpMultiTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Xp Multiplier - number of extra experience you gain in %"
        }
        xpMultiShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Xp Multiplier - number of extra experience you gain in %"
        }
        goldMultiTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Gold Multiplier - number of extra gold you earn in %"
        }
        goldMultiShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Gold Multiplier - number of extra gold you earn in %"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

    }
}