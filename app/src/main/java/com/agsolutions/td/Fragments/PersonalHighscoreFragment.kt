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
import com.agsolutions.td.databinding.FragmentOverallHighscoreBinding
import com.agsolutions.td.databinding.FragmentPersonalHighscoreBinding
import com.agsolutions.td.databinding.FragmentPersonalHighscoreModeTwoBinding
import org.json.JSONArray
import org.json.JSONObject


class PersonalHighscoreFragment : Fragment() {
    private val adapter = HighscoreAdapter(Highscore.personalHighscoreList)

    private var _binding: FragmentPersonalHighscoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalHighscoreBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            personalHighscoreRecycler.adapter = adapter
            personalHighscoreRecycler.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            personalHighscoreRecycler.setHasFixedSize(true)

            Highscore.personalHighscoreList.removeAll { true }
            personalHighscoreBar.visibility = View.VISIBLE
        }

        HttpTask {
            binding.personalHighscoreBar.visibility = View.INVISIBLE
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

    private var _binding: FragmentPersonalHighscoreModeTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalHighscoreModeTwoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            personalHighscoreRecycler.adapter = adapter
            personalHighscoreRecycler.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            personalHighscoreRecycler.setHasFixedSize(true)

            Highscore.personalHighscoreListModeTwo.removeAll { true }
            personalHighscoreBarModeTwo.visibility = View.VISIBLE
        }

        HttpTask {
            binding.personalHighscoreBarModeTwo.visibility = View.INVISIBLE
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