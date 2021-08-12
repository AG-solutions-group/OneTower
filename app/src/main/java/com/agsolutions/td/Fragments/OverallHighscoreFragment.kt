package com.agsolutions.td.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.LogIn.HttpTask
import com.agsolutions.td.Main.Highscore
import com.agsolutions.td.Main.HighscoreAdapter
import com.agsolutions.td.Main.HighscoreAtributes
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_overall_highscore.*
import kotlinx.android.synthetic.main.fragment_overall_highscore.overallHighscoreRecycler
import kotlinx.android.synthetic.main.fragment_overall_highscore_mode_two.*
import org.json.JSONArray
import org.json.JSONObject


class OverallHighscoreFragment : Fragment() {
    private val adapter = HighscoreAdapter(Highscore.overallHighscoreList)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overall_highscore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        recycler()
    }

    private fun recycler() {
        overallHighscoreRecycler.adapter = adapter
        overallHighscoreRecycler.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
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
        }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_overall_highscore_map_11.php")

    }

}

class OverallHighscoreFragmentModeTwo : Fragment() {
    private val adapter = HighscoreAdapter(Highscore.overallHighscoreListModeTwo)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overall_highscore_mode_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        recycler()
    }

    private fun recycler() {
        overallHighscoreRecycler.adapter = adapter
        overallHighscoreRecycler.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        overallHighscoreRecycler.setHasFixedSize(true)

        Highscore.overallHighscoreListModeTwo.removeAll { true }
        overallHighscoreBarModeTwo.visibility = View.VISIBLE

        HttpTask {
            overallHighscoreBarModeTwo.visibility = View.INVISIBLE
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
        }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_overall_highscore_map_12.php")

    }

}