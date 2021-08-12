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
import com.agsolutions.td.Main.Highscore.Companion.usernameX
import com.agsolutions.td.Main.HighscoreAdapter
import com.agsolutions.td.Main.HighscoreAtributes
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_personal_highscore.*
import kotlinx.android.synthetic.main.fragment_personal_highscore.personalHighscoreRecycler
import kotlinx.android.synthetic.main.fragment_personal_highscore_mode_two.*
import org.json.JSONArray
import org.json.JSONObject


class PersonalHighscoreFragment : Fragment() {
    private val adapter = HighscoreAdapter(Highscore.personalHighscoreList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_highscore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personalHighscoreRecycler.adapter = adapter
        personalHighscoreRecycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        personalHighscoreRecycler.setHasFixedSize(true)

        Highscore.personalHighscoreList.removeAll { true }
        personalHighscoreBar.visibility = View.VISIBLE


        HttpTask {
            personalHighscoreBar.visibility = View.INVISIBLE
            if (it == null || it == "") {
                println("connection error")
                return@HttpTask
            }
            println(it)
            val jsonRes = JSONObject(it)
            if (jsonRes.getString("status") == "true") {
                var jsonArray = JSONArray(jsonRes.getString("data"))
                for (i in 0 until jsonArray.length()) {
                    var scorelist = jsonArray.getJSONObject(i)
                    val username = usernameX
                    val highscore = scorelist.getInt("highscore")
                    val date = scorelist.getString("date")

                    Highscore.personalHighscoreList.add(HighscoreAtributes(username, highscore, date))
                }
                adapter.notifyDataSetChanged()
                Log.d("userdata Data:::::::", "worked")

            } else {
                Log.d("post Data:::::::", jsonRes.getString("message"))
            }
        }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_user_highscore_map_11.php?username="+(usernameX))

    }
}

class PersonalHighscoreFragmentModeTwo : Fragment() {
    private val adapter = HighscoreAdapter(Highscore.personalHighscoreListModeTwo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_highscore_mode_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personalHighscoreRecycler.adapter = adapter
        personalHighscoreRecycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        personalHighscoreRecycler.setHasFixedSize(true)

        Highscore.personalHighscoreListModeTwo.removeAll { true }
        personalHighscoreBarModeTwo.visibility = View.VISIBLE

        HttpTask {
            personalHighscoreBarModeTwo.visibility = View.INVISIBLE
            if (it == null || it == "") {
                println("connection error")
                return@HttpTask
            }
            println(it)
            val jsonRes = JSONObject(it)
            if (jsonRes.getString("status") == "true") {
                var jsonArray = JSONArray(jsonRes.getString("data"))
                for (i in 0 until jsonArray.length()) {
                    var scorelist = jsonArray.getJSONObject(i)
                    val username = usernameX
                    val highscore = scorelist.getInt("highscore")
                    val date = scorelist.getString("date")

                    Highscore.personalHighscoreListModeTwo.add(HighscoreAtributes(username, highscore, date))
                }
                adapter.notifyDataSetChanged()
                Log.d("userdata Data:::::::", "worked")

            } else {
                Log.d("post Data:::::::", jsonRes.getString("message"))
            }
        }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_user_highscore_map_12.php?username="+(usernameX))

    }
}