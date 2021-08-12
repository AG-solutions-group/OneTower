package com.agsolutions.td.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

        dmgTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        dmgTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        phyDmgTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        phyDmgTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        spellDmgTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        spellDmgTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        speedTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        speedTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        critTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        critTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        critDmgTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        critDmgTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        multiCrtTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        multiCrtTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        hitTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        hitTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        armorPenTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        armorPenTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        magicPenTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        magicPenTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        itemChanceTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        itemChanceTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        bonusItemQualityTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        bonusItemQualityTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        bagSizeTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        bagSizeTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()

        dmgTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Damage - bonus physical and spell damage"
        }
        dmgShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Damage - bonus physical and spell damage"
        }
        phyDmgTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Physical Damage - bonus physical damage"
        }
        phyDmgShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Physical Damage - bonus physical damage"
        }
        spellDmgTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Spell Damage - bonus spell damage"
        }
        spellDmgShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Spell Damage - bonus spell damage"
        }
        speedTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Attack Speed - frequency of tower attacks"
        }
        speedShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Attack Speed - frequency of tower attacks"
        }
        critTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Critical Damage Chance - chance of hitting a critical attack"
        }
        critShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Critical Damage Chance - chance of hitting a critical attack"
        }
        critDmgTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Critical Damage - damage multiplier of critical hits"
        }
        critDmgShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Critical Damage - damage multiplier of critical hits"
        }
        multiCrtTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Multi Crit - gives critical hits the chance to multiply critical damage"
        }
        multiCrtShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Multi Crit - gives critical hits the chance to multiply critical damage"
        }
        hitTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Hit Chance - chance to hit enemies in percent"
        }
        hitShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Hit Chance - chance to hit enemies in percent"
        }
        armorPenTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Armor Penetration - rating to reduce enemies armor, making them more vulnerable to physical attacks"
        }
        armorPenShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Armor Penetration - rating to reduce enemies armor, making them more vulnerable to physical attacks"
        }
        magicPenTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Magic Armor Penetration - rating to reduce enemies magic armor, making them more vulnerable to physical attacks"
        }
        magicPenShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Magic Armor Penetration - rating to reduce enemies magic armor, making them more vulnerable to physical attacks"
        }
        itemChanceTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Find - chance to find items in percent"
        }
        itemChanceShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Find - chance to find items in percent"
        }
        bonusItemQualityTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Quality - rating to find better items"
        }
        bonusItemQualityShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Quality - rating to find better items"
        }
        bagSizeTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Bag Size - number of items you can use"
        }
        bagUsedShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Bag Size - number of items you can use"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

    }
}