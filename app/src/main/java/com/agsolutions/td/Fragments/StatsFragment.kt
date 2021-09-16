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
import com.agsolutions.td.databinding.FragmentStatsBinding
import kotlinx.android.synthetic.main.fragment_stats.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatsFragment (var updateViewModel: UpdateViewModel) : Fragment() {

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
        val binding: FragmentStatsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_stats,
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

        /*
        xpTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        xpTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        useNumberTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        useNumberTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        interestTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        interestTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        talentTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        talentTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        upgradeNumberTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        upgradeNumberTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        unusedItemsTV.layoutParams.height = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()
        unusedItemsTV.layoutParams.width = (com.agsolutions.td.Companion.scaleTextStats * scalePics).toInt()


        xpTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Gold - primary currency to buy items"
        }
        xpShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Gold - primary currency to buy items"
        }
        useNumberTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Diamonds - secondary currency to buy items"
        }
        useNumberShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Diamonds - secondary currency to buy items"
        }
        interestTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Interest - get additional gold percentage at the end of the round"
        }
        interestShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Interest - get additional gold percentage at the end of the round"
        }
        talentTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Talent Points - used to acquire talents"
        }
        talentShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Talent Points - used to acquire talents"
        }
        upgradeNumberTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Upgrade Points - used to upgrade items"
        }
        upgradeNumberShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Upgrade Points - used to upgrade items"
        }
        unusedItemsTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Points - currency to buy special items"
        }
        unusedItemsShowTV.setOnClickListener(){
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Points - currency to buy special items"
        }

         */

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

    }
}