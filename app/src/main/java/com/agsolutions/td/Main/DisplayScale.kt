package com.agsolutions.td.Main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.Main.GameViewDisplayScale.Companion.scaleBackground
import com.agsolutions.td.R
import com.agsolutions.td.RadioAdapterDisplayScale
import kotlinx.android.synthetic.main.activity_display_scale.*

class DisplayScale : AppCompatActivity() {
    companion object {
        var scaleTextNews = 0f
        var firstStart: Int? = null

    }

    private var PRIVATE_MODE = 0
    var mHandler = Handler()
    var scaleTextMain = 0f
    var scaleTextStats = 0f
    var scaleTextButton = 0f
    var scalePicPlace = 1.5
    var radioListDisplay = mutableListOf<String>("Example Text", "Example Text2", "Example Text3")
    var sharedPref: SharedPreferences? = null


    private val radioAdapter = RadioAdapterDisplayScale(radioListDisplay)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_scale)

        var screenDensityX = getResources().getDisplayMetrics().density
        scalePicPlace *= screenDensityX

        recyclerRadio.adapter = radioAdapter
        recyclerRadio.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerRadio.setHasFixedSize(true)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        firstStart = sharedPref!!.getInt("firstStart", 1)

        if(firstStart == 1) {
            scaleBackground = 4f * screenDensityX
            scaleTextMain = 10f * screenDensityX
            showTextMainScaleTV.text = scaleTextMain.toString()
            levelTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            levelShowTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            livesTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            livesShowTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())

            scaleTextNews= 10f * screenDensityX
            showTextNewsScaleTV.text = scaleTextNews.toString()

            scaleTextStats = 10f * screenDensityX
            showTextStatsScaleTV.text = scaleTextStats.toString()
            xpTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            useNumberTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            interestTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            upgradeNumberTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            unusedItemsTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            bonusItemQualityTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            bagSizeTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            talentTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            itemChanceTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            xpTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            useNumberTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            interestTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            upgradeNumberTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            unusedItemsTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            bonusItemQualityTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            bagSizeTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            talentTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            itemChanceTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()


            xpShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            useNumberShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            interestShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            upgradeNumberShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            unusedItemsShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            bonusItemQualityShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            bagUsedShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            talentShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            itemChanceShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())

       //     scaleTextButton =  10f * screenDensityX
       //     showTextButtonScaleTV.text = scaleTextButton.toString()
       //     saveBTN.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextButton.toFloat())

            radioAdapter.notifyDataSetChanged()
        }else {
            scaleBackground = sharedPref!!.getFloat("ScaleBackground", 10f).toFloat()
            scaleTextMain = sharedPref!!.getFloat("ScaleTextMain", 9f) / screenDensityX.toFloat()
            scaleTextNews = sharedPref!!.getFloat("ScaleTextNews", 8f) / screenDensityX.toFloat()
            scaleTextStats = sharedPref!!.getFloat("ScaleTextStats", 8f) / screenDensityX.toFloat()


            showTextMainScaleTV.text = scaleTextMain.toString()
            levelTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            levelShowTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            livesTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            livesShowTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())

            showTextNewsScaleTV.text = scaleTextNews.toString()

            showTextStatsScaleTV.text = scaleTextStats.toString()
            xpTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            useNumberTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            interestTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            upgradeNumberTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            unusedItemsTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            bonusItemQualityTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            bagSizeTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            talentTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            itemChanceTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            xpTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            useNumberTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            interestTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            upgradeNumberTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            unusedItemsTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            bonusItemQualityTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            bagSizeTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            talentTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            itemChanceTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()


            xpShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            useNumberShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            interestShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            upgradeNumberShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            unusedItemsShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            bonusItemQualityShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            bagUsedShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            talentShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            itemChanceShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())

            //     scaleTextButton = sharedPref!!.getFloat("ScaleTextButton", 8f) / screenDensityX.toFloat()
        //    showTextButtonScaleTV.text = scaleTextButton.toString()
        //    saveBTN.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextButton.toFloat())

            radioAdapter.notifyDataSetChanged()
        }
        showBackgroundScaleTV.text = scaleBackground.toString()

        plusButton.setOnClickListener() {
            scaleBackground += 1
            showBackgroundScaleTV.text = scaleBackground.toString()
        }
        minusButton.setOnClickListener() {
                scaleBackground -= 1
            showBackgroundScaleTV.text = scaleBackground.toString()
        }

        plusButtonTextMain.setOnClickListener() {
            scaleTextMain += 1f
            showTextMainScaleTV.text = scaleTextMain.toString()
            levelTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            levelShowTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            livesTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            livesShowTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())

        }

        minusButtonTextMain.setOnClickListener() {
            scaleTextMain -= 1f
            showTextMainScaleTV.text = scaleTextMain.toString()
            levelTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            levelShowTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            livesTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
            livesShowTVX.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextMain.toFloat())
        }

        plusButtonTextNews.setOnClickListener() {
            scaleTextNews += 1f
            showTextNewsScaleTV.text = scaleTextNews.toString()

            radioAdapter.notifyDataSetChanged()
        }
        minusButtonTextNews.setOnClickListener() {
            scaleTextNews -= 1f
            showTextNewsScaleTV.text = scaleTextNews.toString()

            radioAdapter.notifyDataSetChanged()
        }

        plusButtonTextStats.setOnClickListener() {
            scaleTextStats += 1f
            showTextStatsScaleTV.text = scaleTextStats.toString()

            xpTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            useNumberTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            interestTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            upgradeNumberTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            unusedItemsTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            bonusItemQualityTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            bagSizeTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            talentTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            itemChanceTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            xpTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            useNumberTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            interestTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            upgradeNumberTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            unusedItemsTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            bonusItemQualityTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            bagSizeTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            talentTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            itemChanceTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()


            xpShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            useNumberShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            interestShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            upgradeNumberShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            unusedItemsShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            bonusItemQualityShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            bagUsedShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            talentShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            itemChanceShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())

            radioAdapter.notifyDataSetChanged()
        }
        minusButtonTextStats.setOnClickListener() {
            scaleTextStats -= 1f
            showTextStatsScaleTV.text = scaleTextStats.toString()

            xpTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            useNumberTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            interestTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            upgradeNumberTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            unusedItemsTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            bonusItemQualityTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            bagSizeTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            talentTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            itemChanceTV.layoutParams.height = (scaleTextStats * scalePicPlace).toInt()
            xpTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            useNumberTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            interestTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            upgradeNumberTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            unusedItemsTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            bonusItemQualityTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            bagSizeTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            talentTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()
            itemChanceTV.layoutParams.width = (scaleTextStats * scalePicPlace).toInt()


            xpShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            useNumberShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            interestShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            upgradeNumberShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            unusedItemsShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            bonusItemQualityShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            bagUsedShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            talentShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())
            itemChanceShowTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextStats.toFloat())

            radioAdapter.notifyDataSetChanged()
        }

        /*
        plusButtonTextButton.setOnClickListener() {
            scaleTextButton += 0.5f
            showTextButtonScaleTV.text = scaleTextButton.toString()

            saveBTN.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextButton.toFloat())

            radioAdapter.notifyDataSetChanged()
        }
        minusButtonTextButton.setOnClickListener() {
            scaleTextButton -= 0.5f
            showTextButtonScaleTV.text = scaleTextButton.toString()

            saveBTN.setTextSize(TypedValue.COMPLEX_UNIT_SP, scaleTextButton.toFloat())

            radioAdapter.notifyDataSetChanged()
        }

         */



        saveBTN.setOnClickListener() {

            var editor = sharedPref!!.edit()
            editor.putInt("firstStart", 0)
            editor.putFloat("ScaleBackground", scaleBackground)
            editor.putFloat("ScaleTextMain", scaleTextMain * screenDensityX)
            editor.putFloat("ScaleTextNews", scaleTextNews * screenDensityX)
            editor.putFloat("ScaleTextStats", scaleTextStats * screenDensityX)
            editor.putFloat("ScaleTextButton", scaleTextButton * screenDensityX)
            editor.apply()

            mHandler.postDelayed({
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 100)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

/*
 <TextView
            android:id="@+id/textButtonTV"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="12sp"
            android:text="Text Button"
            android:shadowColor="#000000"
            android:shadowDx="1.5"
            android:shadowDy="1.3"
            android:shadowRadius="1.6"
            app:layout_constraintBottom_toTopOf="@+id/plusButtonTextStats"
            app:layout_constraintEnd_toStartOf="@+id/minusButton"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="@+id/plusButtonTextButton" />

        <ImageButton
            android:id="@+id/plusButtonTextButton"
            android:layout_width="40dp"
            android:layout_height="40dp"
            app:layout_constraintBottom_toTopOf="@+id/plusButtonTextStats"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.502"
            app:layout_constraintStart_toEndOf="@+id/minusButton"
            app:srcCompat="@android:drawable/ic_media_next" />

        <TextView
            android:id="@+id/showTextButtonScaleTV"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:shadowColor="#000000"
            android:shadowDx="1.5"
            android:shadowDy="1.3"
            android:shadowRadius="1.6"
            android:text="Number"
            android:textSize="12sp"
            app:layout_constraintBottom_toTopOf="@+id/plusButtonTextStats"
            app:layout_constraintEnd_toStartOf="@+id/plusButton"
            app:layout_constraintStart_toEndOf="@+id/minusButton"
            app:layout_constraintTop_toTopOf="@+id/plusButtonTextButton" />

        <ImageButton
            android:id="@+id/minusButtonTextButton"
            android:layout_width="40dp"
            android:layout_height="40dp"
            app:layout_constraintBottom_toTopOf="@+id/minusButtonTextStats"
            app:layout_constraintEnd_toStartOf="@+id/plusButton"
            app:layout_constraintStart_toStartOf="parent"
            app:srcCompat="@android:drawable/ic_media_previous" />

 */