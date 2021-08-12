package com.agsolutions.td.LogIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.agsolutions.td.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray
import org.json.JSONObject

class Register : AppCompatActivity() {

        private val activity = this@Register
        private lateinit var inputValidation: InputValidation

        operator fun JSONArray.iterator(): Iterator<JSONObject> =
            (0 until length()).asSequence().map { get(it) as JSONObject }.iterator()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_register)

            initViews()
            initListeners()
            initObjects()

        }

        private fun initViews() {
            progressBar.visibility = View.INVISIBLE
        }

        /**     * This method is to initialize listeners     */
        private fun initListeners() {
            appCompatButtonRegister!!.setOnClickListener() {
                postData()
            }
            appCompatTextViewLoginLink!!.setOnClickListener() {
                finish()
            }

        }

        private fun initObjects() {
            inputValidation = InputValidation(activity)
        }

        private fun postData() {
            if (!inputValidation!!.isInputEditTextFilled(
                    textInputEditTextName,
                    textInputLayoutName,
                    getString(R.string.error_message_name))) {
                return
            } else if (!inputValidation!!.isInputEditTextEmail(
                    textInputEditTextUsername,
                    textInputLayoutUsername,
                    getString(R.string.error_message_email))) {
                return
            } else if (!inputValidation!!.isInputEditTextFilled(
                    textInputEditTextPassword,
                    textInputLayoutPassword,
                    getString(R.string.error_message_password))) {
                return
            } else if (!inputValidation!!.isInputEditTextMatches(
                    textInputEditTextPassword, textInputEditTextConfirmPassword,
                    textInputLayoutConfirmPassword,
                    getString(R.string.error_password_match))) {
                return
            } else{
                Snackbar.make(nestedScrollView!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()

                val json = JSONObject()
                json.put("email", textInputEditTextUsername.text.toString())
                json.put("username", textInputEditTextName.text.toString())
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
                        Snackbar.make(
                            nestedScrollView!!,
                            json_res.getString("message"),
                            Snackbar.LENGTH_LONG
                        ).show()
                        emptyInputEditText()
                        finish()
                    } else {
                        Log.d("post Data:::::::", json_res.getString("message"))
                        Snackbar.make(
                            nestedScrollView!!,
                            json_res.getString("message"),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }.execute("POST", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/register.php", json.toString())
            }

        }

        private fun emptyInputEditText() {
            textInputEditTextName!!.text = null
            textInputEditTextUsername!!.text = null
            textInputEditTextPassword!!.text = null
            textInputEditTextConfirmPassword!!.text = null
        }

        companion object {
            val TAG = "Register.."    }
}
