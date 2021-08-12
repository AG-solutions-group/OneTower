package com.agsolutions.td.LogIn

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.agsolutions.td.Main.MainActivity
import com.agsolutions.td.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_log_in_screen.*
import org.json.JSONArray
import org.json.JSONObject

class LogInScreen : AppCompatActivity() {

    private val activity = this@LogInScreen
    private lateinit var inputValidation: InputValidation
    var sharedPrefZ: SharedPreferences? = null
    private var PRIVATE_MODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_screen)

            sharedPrefZ = getSharedPreferences("Global", PRIVATE_MODE)

            var id = sharedPrefZ!!.getInt("id", 0)
            if (id > 0){
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        initViews()
        initListeners()
        initObjects()

    }

    private fun initViews() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun initListeners() {

        appCompatButtonLogin!!.setOnClickListener(){
            verifyFromSQLite()
        }
        textViewLinkRegister!!.setOnClickListener() {
            val intentRegister = Intent(applicationContext, Register::class.java)
            startActivity(intentRegister)
        }
    }

    private fun initObjects() {

        inputValidation = InputValidation(activity)

    }

    private fun verifyFromSQLite() {

        if (!inputValidation!!.isInputEditTextFilled(
                textInputEditTextUsername!!,
                textInputLayoutUsername!!,
                getString(R.string.error_message_email))) {
            return
        } else if (!inputValidation!!.isInputEditTextFilled(
                textInputEditTextPassword!!,
                textInputLayoutPassword!!,
                getString(R.string.error_message_password))) {
            return
        } else {
            val json = JSONObject()
            json.put("username", textInputEditTextUsername.text.toString())
            json.put("password", textInputEditTextPassword.text.toString())

            progressBar.visibility = View.VISIBLE
            HttpTask {
                progressBar.visibility = View.INVISIBLE
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                }
                println(it)
                val json_res = JSONObject(it)
                if (json_res.getString("status").equals("true")) {
                    var id = 0
                    var jsonArray = JSONArray(json_res.getString("data"))
                    for (i in 0..(jsonArray.length() - 1)) {
                        val item = jsonArray.getJSONObject(i)
                        id = item.getInt("id")

                    }
                    emptyInputEditText()
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                    finish()
                  //  Log.d("userdata Data:::::::", id.toString())
                } else {
                  //  Log.d("post Data:::::::", json_res.getString("message"))
                    Snackbar.make(nestedScrollView,
                        json_res.getString("message"),
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }.execute("POST", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/login.php", json.toString())


        }
    }

    private fun emptyInputEditText() {
        textInputEditTextUsername!!.text = null
        textInputEditTextPassword!!.text = null
    }

}
