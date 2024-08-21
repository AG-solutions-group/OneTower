package com.agsolutions.td.Main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.GameActivity
import com.agsolutions.td.ItemFragmentAdapter
import com.agsolutions.td.ItemFragmentStrings
import com.agsolutions.td.Items
import com.agsolutions.td.LogIn.HttpTask
import com.agsolutions.td.LogIn.MAIN_URL
import com.agsolutions.td.R
import com.agsolutions.td.StartItemAdapter
import com.agsolutions.td.StartItemAdapter2
import com.agsolutions.td.Utils.round
import com.agsolutions.td.databinding.ActivityStartItemsMenuBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class StartItemsMenu : AppCompatActivity(), StartItemAdapter.OnClickListener, StartItemAdapter2.OnHiddenClickListener, ItemFragmentAdapter.OnStatsClickListener   {
    companion object {
        var hashItemlist= 0
        var hashHiddenItemlist = 0
        var startItemHiddenListRemove = mutableListOf<Items>()
    }

    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null
    private val adapter = StartItemAdapter(GameActivity.companionList.startItemList, this) {

    }
    private val adapterHidden = StartItemAdapter2(GameActivity.companionList.startItemHiddenList, this) {

    }
    private val showAdapter = ItemFragmentAdapter(GameActivity.companionList.menuItemItems, this)

    var userlevel = 0
    var newItem = 0
    var username : String? = "user"

    private lateinit var binding: ActivityStartItemsMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartItemsMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        username = sharedPref!!.getString("username", "user")

        with(binding) {
            startItemsMenuBar.visibility = View.INVISIBLE

            recyclerStartMenuItem.adapter = adapter
            recyclerStartMenuItem.layoutManager =
                GridLayoutManager(this@StartItemsMenu, 6)
            recyclerStartMenuItem.setHasFixedSize(true)

            recyclerStartMenuHiddenItem.adapter = adapterHidden
            recyclerStartMenuHiddenItem.layoutManager =
                GridLayoutManager(this@StartItemsMenu, 6)
            recyclerStartMenuHiddenItem.setHasFixedSize(true)

            itemMenuStatsRecycler.adapter = showAdapter
            itemMenuStatsRecycler.layoutManager =
                LinearLayoutManager(this@StartItemsMenu, LinearLayoutManager.VERTICAL, false)
            itemMenuStatsRecycler.setHasFixedSize(true)
        }

        userlevel = intent.getIntExtra("userLevel", 0)

        if (isOnline(this)) {

            if ((sharedPref!!.getInt("hasNewItems", 0)) == 1) {
                var editor = sharedPref!!.edit()
                editor.putInt("hasNewItems", 2)
                editor.apply()

                var usernameX = sharedPref!!.getString("username", "user")

                var textFile = File("$filesDir/itemList.dat")
                var fis = FileInputStream(textFile)
                var ois = ObjectInputStream(fis)
                var itemListPlace = ois.readObject() as ArrayList<Int>
                var itemListPost = ArrayList<Int>()
                for (itemid in itemListPlace) {
                    itemListPost.add(itemid)
                }

                itemListPost.forEach() {
                    val json = JSONObject()
                    json.put("username", usernameX)
                    json.put("itemid", it.toString())

                    HttpTask {
                        if (it == null) {
                            println("DATA - connection error")
                            return@HttpTask
                        }
                        println(it)
                        val json_res = JSONObject(it)
                        if (json_res.getString("status").equals("true")) {
                            Log.d("post Data true :::::::", json_res.getString("message"))
                            post1()
                        } else {
                            Log.d("post Data else:::::::", json_res.getString("message"))
                        }
                    }.execute("POST", "$MAIN_URL/start_items.php", json.toString())
                }
            }else {
                post1()
            }
        }else {
            // if (((sharedPref!!.getInt("hasNewItems", 0)) == 1) || ((sharedPref!!.getInt("hasNewItems", 0)) == 2)){
            var textFile = File("$filesDir/itemList.dat")
            var fis = FileInputStream(textFile)
            var ois = ObjectInputStream(fis)
            var itemListPlace = ois.readObject() as ArrayList<Int>
            for (itemid in itemListPlace) {
                GameActivity.companionList.startItemHiddenList.forEach() {
                    if (itemid == it.id) {
                        GameActivity.companionList.startItemList.add(it)
                        startItemHiddenListRemove.add(it)
                    }
                }
            }
            GameActivity.companionList.startItemHiddenList.removeAll(startItemHiddenListRemove)
            adapterHidden.notifyDataSetChanged()
            adapter.notifyDataSetChanged()
        }

        binding.quitBTN.setOnClickListener {
            var idList = mutableListOf<Int>()
            if (isOnline(this)) {
                HttpTask {
                    // progressMainBar.visibility = View.INVISIBLE
                    if (it == null) {
                        println("connection error")
                        return@HttpTask
                    }
                    println(it)
                    val jsonRes = JSONObject(it)
                    if (jsonRes.getString("status") == "true") {
                        var jsonArray = JSONArray(jsonRes.getString("data"))
                        for (i in 0 until jsonArray.length()) {
                            var itemlist = jsonArray.getJSONObject(i)
                            var itemid = itemlist.getInt("itemid")
                            idList.add(itemid)
                        }
                        val textFile2 = File("$filesDir/itemList.dat")
                        if (!textFile2.exists()) {
                            textFile2.createNewFile()
                        } else {
                            textFile2.delete()
                            textFile2.createNewFile()
                        }
                        val fos = FileOutputStream(textFile2)
                        val oos = ObjectOutputStream(fos)
                        var writeList = ArrayList(idList)
                        oos.writeObject(writeList)
                        oos.close()
                        fos.close()
                        Log.d("userdata Data:::::::", "worked")

                    } else {
                        Log.d("post Data:::::::", jsonRes.getString("message"))
                    }
                }.execute("GET", "$MAIN_URL/get_user_start_items.php?username=" + (username))
            }
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun post1 (){
        HttpTask {
            // progressMainBar.visibility = View.INVISIBLE
            if (it == null) {
                println("connection error")
                return@HttpTask
            }
            println(it)
            val jsonRes = JSONObject(it)
            if (jsonRes.getString("status") == "true") {
                var jsonArray = JSONArray(jsonRes.getString("data"))
                for (i in 0 until jsonArray.length()) {
                    var itemlist = jsonArray.getJSONObject(i)
                    var itemid = itemlist.getInt("itemid")
                    GameActivity.companionList.startItemHiddenList.forEach() {
                        if (itemid == it.id) {
                            GameActivity.companionList.startItemList.add(it)
                            startItemHiddenListRemove.add(it)
                        }
                    }
                }
                GameActivity.companionList.startItemHiddenList.removeAll(startItemHiddenListRemove)

                adapterHidden.notifyDataSetChanged()
                adapter.notifyDataSetChanged()
                Log.d("userdata Data:::::::", "worked")

            } else {
                Log.d("post Data:::::::", jsonRes.getString("message"))
            }
        }.execute("GET", "$MAIN_URL/get_user_start_items.php?username=" + (username))
    }

    override fun onClick(position: Int) {
        binding.buyItemBTN.visibility = View.INVISIBLE

        GameActivity.companionList.menuItemItems.removeAll(GameActivity.companionList.menuItemItems)


        GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.nameicon, GameActivity.companionList.startItemList[position].name.toString()))
        if (GameActivity.companionList.startItemList[position].dmg > 0) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.swordandwandicon, GameActivity.companionList.startItemList[position].dmg.round(2).toString()))
        if (GameActivity.companionList.startItemList[position].crit > 0) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.criticon, GameActivity.companionList.startItemList[position].crit.round(2).toString()))
        if (GameActivity.companionList.startItemList[position].critDmg > 0) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.critdmgicon, GameActivity.companionList.startItemList[position].critDmg.round(2).toString()))
        if (GameActivity.companionList.startItemList[position].speed > 0) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.bowicon, GameActivity.companionList.startItemList[position].speed.round(2).toString()))
        if (GameActivity.companionList.startItemList[position].special.isNotBlank()) {
            GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startItemList[position].special.toString()))
            if (GameActivity.companionList.startItemHiddenList[position].specialFloat != 0f) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startItemList[position].specialFloat.round(2).toString()))
        }

        showAdapter.notifyDataSetChanged()
    }

    override fun onHiddenClick(position: Int) {

        with(binding) {
            if (GameActivity.companionList.startItemHiddenList[position].minLvl <= userlevel) {
                buyItemBTN.visibility = View.VISIBLE
                buyItemBTN.setOnClickListener() {
                    GameActivity.companionList.startItemList.add(GameActivity.companionList.startItemHiddenList[position])
                    newItem = GameActivity.companionList.startItemHiddenList[position].id
                    GameActivity.companionList.startItemHiddenList.remove(GameActivity.companionList.startItemHiddenList[position])
                    buyItemBTN.visibility = View.INVISIBLE

                    adapter.notifyDataSetChanged()
                    adapterHidden.notifyItemRemoved(position)

                    if (isOnline(this@StartItemsMenu)) postData(position)
                    else postData2(position)
                }
            } else buyItemBTN.visibility = View.INVISIBLE
        }

        GameActivity.companionList.menuItemItems.removeAll(GameActivity.companionList.menuItemItems)


        GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.nameicon, GameActivity.companionList.startItemHiddenList[position].name.toString()))
        GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.lvlicon, GameActivity.companionList.startItemHiddenList[position].minLvl.toString()))
        if (GameActivity.companionList.startItemHiddenList[position].dmg > 0) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.swordandwandicon, GameActivity.companionList.startItemHiddenList[position].dmg.round(2).toString()))
        if (GameActivity.companionList.startItemHiddenList[position].crit > 0) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.criticon, GameActivity.companionList.startItemHiddenList[position].crit.round(2).toString()))
        if (GameActivity.companionList.startItemHiddenList[position].critDmg > 0) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.critdmgicon, GameActivity.companionList.startItemHiddenList[position].critDmg.round(2).toString()))
        if (GameActivity.companionList.startItemHiddenList[position].speed > 0) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.bowicon, GameActivity.companionList.startItemHiddenList[position].speed.round(2).toString()))
        if (GameActivity.companionList.startItemHiddenList[position].special.isNotBlank()) {
            GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startItemHiddenList[position].special.toString()))
            if (GameActivity.companionList.startItemHiddenList[position].specialFloat != 0f) GameActivity.companionList.menuItemItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startItemHiddenList[position].specialFloat.round(2).toString()))
        }

        showAdapter.notifyDataSetChanged()

    }

    private fun postData(position: Int) {

        val json = JSONObject()
        json.put("username", username)
        json.put("itemid", newItem.toString())

        binding.startItemsMenuBar.visibility = View.VISIBLE
        HttpTask {
            binding.startItemsMenuBar.visibility = View.INVISIBLE
            if (it == null) {
                println("connection error")
                return@HttpTask
            }
            println(it)
            val json_res = JSONObject(it)
            if (json_res.getString("status").equals("true")) {

            } else {
                Log.d("post Data:::::::", json_res.getString("message"))
            }
        }.execute("POST", "$MAIN_URL/start_items.php", json.toString())
    }

    private fun postData2(position: Int) {

            var editor = sharedPref!!.edit()
            editor.putInt("hasNewItems", 1)
            editor.apply()

        var theItemsList = ArrayList<Int>()

        if (GameActivity.companionList.startItemList.size > 3) {
            var textFile = File("$filesDir/itemList.dat")
            var fis = FileInputStream(textFile)
            var ois = ObjectInputStream(fis)
            var itemListPlace = ois.readObject() as ArrayList<Int>
            itemListPlace.forEach() {
                theItemsList.add(it)
            }
        }
        theItemsList.add(newItem)

            val textFile2 = File("$filesDir/itemList.dat")
            if (!textFile2.exists()) {
                textFile2.createNewFile()
            } else {
                textFile2.delete()
                textFile2.createNewFile()
            }
            val fos = FileOutputStream(textFile2)
            val oos = ObjectOutputStream(fos)
            var writeList = ArrayList(theItemsList)
            oos.writeObject(writeList)
            oos.close()
            fos.close()


    }

    override fun onStatsClick(position: Int) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        var idList = mutableListOf<Int>()
        if (isOnline(this)) {
            HttpTask {
                // progressMainBar.visibility = View.INVISIBLE
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                }
                println(it)
                val jsonRes = JSONObject(it)
                if (jsonRes.getString("status") == "true") {
                    var jsonArray = JSONArray(jsonRes.getString("data"))
                    for (i in 0 until jsonArray.length()) {
                        var itemlist = jsonArray.getJSONObject(i)
                        var itemid = itemlist.getInt("itemid")
                        GameActivity.companionList.startItemListAll.forEach() {
                            if (itemid == it.id) {
                                idList.add(it.id)
                            }
                        }
                    }

                    val textFile2 = File("$filesDir/itemList.dat")
                    if (!textFile2.exists()) {
                        textFile2.createNewFile()
                    } else {
                        textFile2.delete()
                        textFile2.createNewFile()
                    }
                    val fos = FileOutputStream(textFile2)
                    val oos = ObjectOutputStream(fos)
                    var writeList = ArrayList(idList)
                    oos.writeObject(writeList)
                    oos.close()
                    fos.close()
                    Log.d("userdata Data:::::::", "worked")

                } else {
                    Log.d("post Data:::::::", jsonRes.getString("message"))
                }
            }.execute("GET", "$MAIN_URL/get_user_start_items.php?username=" + (username))
        }

        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}