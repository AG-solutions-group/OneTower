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
import com.agsolutions.td.*
import com.agsolutions.td.LogIn.HttpTask
import com.agsolutions.td.Utils.round
import kotlinx.android.synthetic.main.activity_start_items_menu.*
import kotlinx.android.synthetic.main.secret_shop.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.util.*
import kotlin.collections.ArrayList

class StartItemsMenu : AppCompatActivity(), StartItemAdapter.OnClickListener, StartItemAdapter2.OnHiddenClickListener, ItemFragmentAdapter.OnStatsClickListener   {
    companion object {
        var hashItemlist= 0
        var hashHiddenItemlist = 0
        var startItemHiddenListRemove = mutableListOf<Items>()
    }

    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null
    private val adapter = StartItemAdapter(Items.startItemList, this) {

    }
    private val adapterHidden = StartItemAdapter2(Items.startItemHiddenList, this) {

    }
    private val showAdapter = ItemFragmentAdapter(com.agsolutions.td.Companion.menuItemItems, this)

    var userlevel = 0
    var newItem = 0
    var username : String? = "user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_items_menu)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        username = sharedPref!!.getString("username", "user")

        startItemsMenuBar.visibility = View.INVISIBLE

        recyclerStartMenuItem.adapter = adapter
        recyclerStartMenuItem.layoutManager =
            GridLayoutManager(this, 6)
        recyclerStartMenuItem.setHasFixedSize(true)

        recyclerStartMenuHiddenItem.adapter = adapterHidden
        recyclerStartMenuHiddenItem.layoutManager =
            GridLayoutManager(this, 6)
        recyclerStartMenuHiddenItem.setHasFixedSize(true)

        itemMenuStatsRecycler.adapter = showAdapter
        itemMenuStatsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        itemMenuStatsRecycler.setHasFixedSize(true)



        userlevel = intent.getIntExtra("userLevel", 0)

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
                        Log.d("item", itemid.toString() )
                        Items.startItemHiddenList.forEach() {
                            if (itemid == it.id) {
                                Items.startItemList.add(it)
                                startItemHiddenListRemove.add(it)
                            }
                        }
                    }
                    Items.startItemHiddenList.removeAll(startItemHiddenListRemove)

                    adapterHidden.notifyDataSetChanged()
                    adapter.notifyDataSetChanged()
                    Log.d("userdata Data:::::::", "worked")

                } else {
                    Log.d("post Data:::::::", jsonRes.getString("message"))
                }
            }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_user_start_items.php?username=" + (username))
        }else if (((sharedPref!!.getInt("hasNewItems", 0)) == 1) || ((sharedPref!!.getInt("hasNewItems", 0)) == 2)){
            var textFile = File("$filesDir/itemList.dat")
            var fis = FileInputStream(textFile)
            var ois = ObjectInputStream(fis)
            var itemListPlace = ois.readObject() as ArrayList<Int>
            for (itemid in itemListPlace) {
                Items.startItemHiddenList.forEach() {
                    if (itemid == it.id) {
                        Items.startItemList.add(it)
                        startItemHiddenListRemove.add(it)
                    }
                }
            }
            Items.startItemHiddenList.removeAll(startItemHiddenListRemove)
            adapterHidden.notifyDataSetChanged()
            adapter.notifyDataSetChanged()
        }

        quitBTN.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onClick(position: Int) {
        buyItemBTN.visibility = View.INVISIBLE

        com.agsolutions.td.Companion.menuItemItems.removeAll(com.agsolutions.td.Companion.menuItemItems)


        com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.nameicon, Items.startItemList[position].name.toString()))
        if (Items.startItemList[position].dmg > 0) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.swordandwandicon, Items.startItemList[position].dmg.round(2).toString()))
        if (Items.startItemList[position].crit > 0) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.criticon, Items.startItemList[position].crit.round(2).toString()))
        if (Items.startItemList[position].critDmg > 0) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.critdmgicon, Items.startItemList[position].critDmg.round(2).toString()))
        if (Items.startItemList[position].speed > 0) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.bowicon, Items.startItemList[position].speed.round(2).toString()))
        if (Items.startItemList[position].special.isNotBlank()) {
            com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.specialicon, Items.startItemList[position].special.toString()))
            if (Items.startItemHiddenList[position].specialFloat != 0f) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.specialicon, Items.startItemList[position].specialFloat.round(2).toString()))
        }

        showAdapter.notifyDataSetChanged()
    }

    override fun onHiddenClick(position: Int) {
        if (Items.startItemHiddenList[position].minLvl <= userlevel) {
        buyItemBTN.visibility = View.VISIBLE
        buyItemBTN.setOnClickListener() {
            Items.startItemList.add(Items.startItemHiddenList[position])
            newItem = Items.startItemHiddenList[position].id
            Items.startItemHiddenList.remove(Items.startItemHiddenList[position])
            buyItemBTN.visibility = View.INVISIBLE

            adapter.notifyDataSetChanged()
            adapterHidden.notifyItemRemoved(position)

            if (isOnline(this)) postData(position)
            else postData2(position)
            }
        }else buyItemBTN.visibility = View.INVISIBLE

        com.agsolutions.td.Companion.menuItemItems.removeAll(com.agsolutions.td.Companion.menuItemItems)


        com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.nameicon, Items.startItemHiddenList[position].name.toString()))
        com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.lvlicon, Items.startItemHiddenList[position].minLvl.toString()))
        if (Items.startItemHiddenList[position].dmg > 0) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.swordandwandicon, Items.startItemHiddenList[position].dmg.round(2).toString()))
        if (Items.startItemHiddenList[position].crit > 0) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.criticon, Items.startItemHiddenList[position].crit.round(2).toString()))
        if (Items.startItemHiddenList[position].critDmg > 0) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.critdmgicon, Items.startItemHiddenList[position].critDmg.round(2).toString()))
        if (Items.startItemHiddenList[position].speed > 0) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.bowicon, Items.startItemHiddenList[position].speed.round(2).toString()))
        if (Items.startItemHiddenList[position].special.isNotBlank()) {
            com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.specialicon, Items.startItemHiddenList[position].special.toString()))
            if (Items.startItemHiddenList[position].specialFloat != 0f) com.agsolutions.td.Companion.menuItemItems.add(ItemFragmentStrings(R.drawable.specialicon, Items.startItemHiddenList[position].specialFloat.round(2).toString()))
        }

        showAdapter.notifyDataSetChanged()

    }

    private fun postData(position: Int) {

        val json = JSONObject()
        json.put("username", username)
        json.put("itemid", newItem.toString())

        startItemsMenuBar.visibility = View.VISIBLE
        HttpTask {
            startItemsMenuBar.visibility = View.INVISIBLE
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
        }.execute("POST", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/start_items.php", json.toString())
    }

    private fun postData2(position: Int) {

            var editor = sharedPref!!.edit()
            editor.putInt("hasNewItems", 1)
            editor.apply()

        var theItemsList = ArrayList<Int>()

        if (Items.startItemList.size > 3) {
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