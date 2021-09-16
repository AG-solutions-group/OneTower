package com.agsolutions.td.Fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.R
import com.agsolutions.td.UpdateViewModel
import com.agsolutions.td.databinding.FragmentStatsEnemyBinding
import kotlinx.android.synthetic.main.fragment_stats.*
import kotlinx.android.synthetic.main.fragment_stats_enemy.*
import kotlinx.android.synthetic.main.fragment_stats_tower.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatsEnemyFragment (var updateViewModel: UpdateViewModel) : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding: FragmentStatsEnemyBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_stats_enemy,
            container,
            false
        )
        binding.updateViewModel = updateViewModel
        binding.lifecycleOwner = this
        val view = binding.root

        /*
        view.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent): Boolean {
                    val x = event!!.x
                    val y = event.y

                    if (x > 0 && y > 0)  {
                        Log.d("hi", toastEnemy.toString())
                        toastEnemy = true
                    }

                return true
                }
            })


         */
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh()

        var scalePics = 1.5

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

        shieldBar.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Earth Shield - use physical dmg to break"
        }
        manaShieldBar.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Mana Shield - use magic dmg to break"
        }
        hpBar.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Hit Point Bar"
        }
        lvlTypeTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Enemy Type"
        }
        lvlArmorTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Armor - reduces physical damage done (Armor Rating / Physical Damage Reduction)"
        }
        lvlArmorShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Armor - reduces physical damage done (Armor Rating / Physical Damage Reduction)"
        }
        lvlArmorRatingShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Armor - reduces physical damage done (Armor Rating / Physical Damage Reduction)"
        }
        lvlMagicArmorTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Magic Armor - reduces spell damage done (Magic Armor Rating / Spell Damage Reduction)"
        }
        lvlMagicArmorShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Magic Armor - reduces spell damage done (Magic Armor Rating / Spell Damage Reduction)"
        }
        lvlMagicArmorRatingShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Magic Armor - reduces spell damage done (Magic Armor Rating / Spell Damage Reduction)"
        }
        lvlEvadeTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Evade chance - chance to evade attacks in percent"
        }
        lvlEvadeShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Evade chance - chance to evade attacks in percent"
        }
        lvlHpRegTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Hitpoint Regeneration per tick"
        }
        lvlHpRegShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Hitpoint Regeneration per tick"
        }
        lvlSpdTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Movement Speed"
        }
        lvlSpdShowTV.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Movement Speed"
        }

    }

    fun refresh(){

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

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

    }
}