package com.agsolutions.td.LogIn

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.R
import com.agsolutions.td.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import org.json.JSONObject

class Register : AppCompatActivity() {

        private val activity = this@Register
        private lateinit var inputValidation: InputValidation
    private lateinit var binding: ActivityRegisterBinding


    operator fun JSONArray.iterator(): Iterator<JSONObject> =
            (0 until length()).asSequence().map { get(it) as JSONObject }.iterator()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityRegisterBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            initViews()
            initListeners()
            initObjects()

        }

        private fun initViews() {
            binding.progressBar.visibility = View.INVISIBLE
        }

        /**     * This method is to initialize listeners     */
        private fun initListeners() {
            binding.appCompatButtonRegister!!.setOnClickListener() {
                postData()
            }
            binding.appCompatTextViewLoginLink!!.setOnClickListener() {
                finish()
            }

        }

        private fun initObjects() {
            inputValidation = InputValidation(activity)
        }

        private fun postData() {
            if (!inputValidation!!.isInputEditTextFilled(
                    binding.textInputEditTextName,
                    binding.textInputLayoutName,
                    getString(R.string.error_message_name))) {
                return
            } else if (!inputValidation!!.isInputEditTextFilled(
                    binding.textInputEditTextPassword,
                    binding.textInputLayoutPassword,
                    getString(R.string.error_message_password))) {
                return
            } else if (!inputValidation!!.isInputEditTextMatches(
                    binding.textInputEditTextPassword, binding.textInputEditTextConfirmPassword,
                    binding.textInputLayoutConfirmPassword,
                    getString(R.string.error_password_match))) {
                return
            } else{
                Snackbar.make(binding.nestedScrollView!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()

                val json = JSONObject()
                json.put("username", binding.textInputEditTextName.text.toString())
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
                        Snackbar.make(
                            binding.nestedScrollView!!,
                            json_res.getString("message"),
                            Snackbar.LENGTH_LONG
                        ).show()
                        emptyInputEditText()
                        finish()
                    } else {
                        Log.d("post Data:::::::", json_res.getString("message"))
                        Snackbar.make(
                            binding.nestedScrollView!!,
                            json_res.getString("message"),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }.execute("POST", "$MAIN_URL/register.php", json.toString())
            }

        }

        private fun emptyInputEditText() {
            binding.textInputEditTextName!!.text = null
            binding.textInputEditTextPassword!!.text = null
            binding.textInputEditTextConfirmPassword!!.text = null
        }

        companion object {
            val TAG = "Register.."    }
}
