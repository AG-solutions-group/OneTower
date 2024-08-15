package com.agsolutions.td.LogIn

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.Main.MainActivity
import com.agsolutions.td.R
import com.agsolutions.td.databinding.ActivityLogInScreenBinding
import com.agsolutions.td.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import org.json.JSONObject

class LogInScreen : AppCompatActivity() {

    private val activity = this@LogInScreen
    private lateinit var inputValidation: InputValidation
    var sharedPrefZ: SharedPreferences? = null
    private var PRIVATE_MODE = 0
    private lateinit var binding: ActivityLogInScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun initListeners() {

        binding.appCompatButtonLogin!!.setOnClickListener(){
            verifyFromSQLite()
        }
        binding.textViewLinkRegister!!.setOnClickListener() {
            val intentRegister = Intent(applicationContext, Register::class.java)
            startActivity(intentRegister)
        }
    }

    private fun initObjects() {

        inputValidation = InputValidation(activity)

    }

    private fun verifyFromSQLite() {

        if (!inputValidation!!.isInputEditTextFilled(
                binding.textInputEditTextUsername!!,
                binding.textInputLayoutUsername!!,
                "Please enter a valid username")) {
            return
        } else if (!inputValidation!!.isInputEditTextFilled(
                binding.textInputEditTextPassword!!,
                binding.textInputLayoutPassword!!,
                getString(R.string.error_message_password))) {
            return
        } else {
            val json = JSONObject()
            json.put("username", binding.textInputEditTextUsername.text.toString())
            json.put("password", binding.textInputEditTextPassword.text.toString())

            binding.progressBar.visibility = View.VISIBLE
            HttpTask {
                binding.progressBar.visibility = View.INVISIBLE
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
                    var editor = sharedPrefZ!!.edit()
                    editor.putInt("firstStart", 1)
                    editor.apply()
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                    finish()
                  //  Log.d("userdata Data:::::::", id.toString())
                } else {
                  //  Log.d("post Data:::::::", json_res.getString("message"))
                    Snackbar.make(binding.nestedScrollView,
                        json_res.getString("message"),
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }.execute("POST", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/login.php", json.toString())


        }
    }

    private fun emptyInputEditText() {
        binding.textInputEditTextUsername!!.text = null
        binding.textInputEditTextPassword!!.text = null
    }

}
