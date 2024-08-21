package com.agsolutions.td.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.LogIn.HttpTask
import com.agsolutions.td.LogIn.MAIN_URL
import com.agsolutions.td.Main.Highscore
import com.agsolutions.td.Main.HighscoreAdapter
import com.agsolutions.td.Main.HighscoreAtributes
import com.agsolutions.td.databinding.FragmentOverallHighscoreBinding
import com.agsolutions.td.databinding.FragmentOverallHighscoreModeTwoBinding
import org.json.JSONArray
import org.json.JSONObject


class OverallHighscoreFragment : Fragment() {
    private val adapter = HighscoreAdapter(Highscore.overallHighscoreList)

    private var _binding: FragmentOverallHighscoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverallHighscoreBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler()
    }

    private fun recycler() {

        with(binding) {
            overallHighscoreRecycler.adapter = adapter
            overallHighscoreRecycler.layoutManager =
                LinearLayoutManager(this@OverallHighscoreFragment.activity, LinearLayoutManager.VERTICAL, false)
            overallHighscoreRecycler.setHasFixedSize(true)

            Highscore.overallHighscoreList.removeAll { true }
            overallHighscoreBar.visibility = View.VISIBLE

            HttpTask {
                overallHighscoreBar.visibility = View.INVISIBLE
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                }
                println(it)
                val jsonRes = JSONObject(it)
                if (jsonRes.getString("status") == "true") {
                    var jsonArray = JSONArray(jsonRes.getString("data"))
                    for (i in 0 until jsonArray.length()) {
                        var scorelist = jsonArray.getJSONObject(i)
                        val username = scorelist.getString("username")
                        val highscore = scorelist.getInt("highscore")
                        val date = scorelist.getString("date")

                        Highscore.overallHighscoreList.add(HighscoreAtributes(username, highscore, date))
                    }
                    adapter.notifyDataSetChanged()
                    Log.d("userdata Data:::::::", "worked")

                } else {
                    Log.d("post Data:::::::", jsonRes.getString("message"))
                }
            }.execute("GET", "$MAIN_URL/get_overall_highscore_map_11.php")

        }
    }
}

class OverallHighscoreFragmentModeTwo : Fragment() {
    private val adapter = HighscoreAdapter(Highscore.overallHighscoreListModeTwo)

    private var _binding: FragmentOverallHighscoreModeTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverallHighscoreModeTwoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler()
    }

    private fun recycler() {

        with(binding) {

            overallHighscoreRecycler.adapter = adapter
            overallHighscoreRecycler.layoutManager =
                LinearLayoutManager(this@OverallHighscoreFragmentModeTwo.activity, LinearLayoutManager.VERTICAL, false)
            overallHighscoreRecycler.setHasFixedSize(true)

            Highscore.overallHighscoreListModeTwo.removeAll { true }
            overallHighscoreBarModeTwo.visibility = View.VISIBLE

        }

        HttpTask {
            binding.overallHighscoreBarModeTwo.visibility = View.INVISIBLE
            if (it == null) {
                println("connection error")
                return@HttpTask
            }
            println(it)
            val jsonRes = JSONObject(it)
            if (jsonRes.getString("status") == "true") {
                var jsonArray = JSONArray(jsonRes.getString("data"))
                for (i in 0 until jsonArray.length()) {
                    var scorelist = jsonArray.getJSONObject(i)
                    val username = scorelist.getString("username")
                    val highscore = scorelist.getInt("highscore")
                    val date = scorelist.getString("date")

                    Highscore.overallHighscoreListModeTwo.add(HighscoreAtributes(username, highscore, date))
                }
                adapter.notifyDataSetChanged()
                Log.d("userdata Data:::::::", "worked")

            } else {
                Log.d("post Data:::::::", jsonRes.getString("message"))
            }
        }.execute("GET", "$MAIN_URL/get_overall_highscore_map_12.php")

    }

}