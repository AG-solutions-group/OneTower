package com.agsolutions.td.Main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.activity_pick_map.*

class PickMap : AppCompatActivity() {
    var mHandler = Handler()
    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_map)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        var scaleScreen = sharedPref!!.getFloat("ScaleBackground", 10f)
        var height = getResources().getDisplayMetrics().heightPixels

        window.setLayout((600.0f * (scaleScreen /10)).toInt(), (height * 0.6f).toInt())
        window.setGravity(Gravity.RIGHT)
        window.setElevation(10F)

        val retoure = intent.getIntExtra("return", 1)
        if (retoure != 0) {
            mHandler.postDelayed({
                finish()
            }, 100)
        }

        /*
        tutorialIV.setOnClickListener(){
            intent = Intent (this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("pickMap", 0)
            intent.putExtra("pickMode", 1)
            startActivity(intent)
            exitProcess(0)
        }

         */
        mapOneIV.setOnClickListener(){
            intent = Intent (this, PickMode::class.java)
            startActivity(intent)
        }

       /* mapTwoIV.setOnClickListener(){
            intent = Intent (this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("pickMap", 2)
            startActivity(intent)
            finish()
        }

        */
    }
    override fun onBackPressed() {
        super.onBackPressed()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

/*

<ImageView
                android:id="@+id/mapTwoIV"
                android:layout_width="150dp"
                android:layout_height="150dp"
                android:layout_marginTop="12dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/mapTwoTV"
                app:srcCompat="@drawable/map2day" />

            <TextView
                android:id="@+id/mapTwoTV"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="32dp"
                android:text="Dark Woods"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/mapOneIV" />

 */