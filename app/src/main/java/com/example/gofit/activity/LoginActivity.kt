package com.example.gofit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.Toast
import com.example.gofit.R
import com.example.gofit.activity.instruktur.MainInstrukturActivity
import com.example.gofit.activity.member.MainMemberActivity
import com.example.gofit.activity.mo.MainMoActivity
import com.example.gofit.app.ApiConfig
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.login.ResponseLoginInstruktur

import com.example.gofit.model.login.ResponseLoginMember
import com.example.gofit.model.login.ResponseLoginPegawai
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPref
    private lateinit var inputUsername: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var loading: ProgressBar
    private lateinit var radioMember: RadioButton
    private lateinit var radioInstruktur: RadioButton
    private lateinit var radioMO: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        callComponent()

        sharedPref = SharedPref(this)

        radioMember.setOnClickListener {
            // Set the checked state of the radio buttons
            radioMember.isChecked = true
            radioInstruktur.isChecked = false
            radioMO.isChecked = false
        }

        radioInstruktur.setOnClickListener {
            // Set the checked state of the radio buttons
            radioMember.isChecked = false
            radioInstruktur.isChecked = true
            radioMO.isChecked = false
        }

        radioMO.setOnClickListener {
            // Set the checked state of the radio buttons
            radioMember.isChecked = false
            radioInstruktur.isChecked = false
            radioMO.isChecked = true
        }

        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener {
            login()
        }
    }

    fun login() {
        if (inputUsername.text.toString().isEmpty()) {
            inputUsername.error = "Username must be filled"
            inputUsername.requestFocus()
            return
        } else if (inputPassword.text.toString().isEmpty()) {
            inputPassword.error = "Password must be filled"
            inputPassword.requestFocus()
            return
        }

        loading.visibility = View.VISIBLE

        if (radioMember.isChecked == true) {
            ApiConfig.instanceRetrofit.loginMember(inputUsername.text.toString(), inputPassword.text.toString()).enqueue(object: Callback<ResponseLoginMember> {
                override fun onResponse(call: Call<ResponseLoginMember>, response: Response<ResponseLoginMember>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            loading.visibility = View.GONE

                            sharedPref.setIdMember(inputUsername.text.toString())
                            sharedPref.setToken(response.body()!!.token)

                            sharedPref.setStatusLogin(true)

                            val intent = Intent(this@LoginActivity, MainMemberActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
                        }
                    }else {
                        loading.visibility = View.GONE
                        val errorBody = JSONObject(response.errorBody()?.string())
                        Toast.makeText(this@LoginActivity, errorBody.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLoginMember>, t: Throwable) {
                    loading.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, "Error:" + t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }else if (radioInstruktur.isChecked == true) {
            ApiConfig.instanceRetrofit.loginInstruktur(inputUsername.text.toString(), inputPassword.text.toString()).enqueue(object: Callback<ResponseLoginInstruktur> {
                override fun onResponse(call: Call<ResponseLoginInstruktur>, response: Response<ResponseLoginInstruktur>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            loading.visibility = View.GONE

                            sharedPref.setIdInstruktur(response.body()!!.data.idInstruktur.toString())
                            sharedPref.setToken(response.body()!!.token)
                            sharedPref.setStatusLogin(true)

                            val intent = Intent(this@LoginActivity, MainInstrukturActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
                        }
                    }else {
                        loading.visibility = View.GONE
                        val errorBody = JSONObject(response.errorBody()?.string())
                        Toast.makeText(this@LoginActivity, errorBody.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLoginInstruktur>, t: Throwable) {
                    loading.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, "Error:" + t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }else if (radioMO.isChecked == true) {
            ApiConfig.instanceRetrofit.loginPegawai(inputUsername.text.toString(), inputPassword.text.toString()).enqueue(object: Callback<ResponseLoginPegawai> {
                override fun onResponse(call: Call<ResponseLoginPegawai>, response: Response<ResponseLoginPegawai>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            loading.visibility = View.GONE

                            sharedPref.setIdPegawai(response.body()!!.data.idPegawai.toString())
                            sharedPref.setToken(response.body()!!.token)
                            sharedPref.setStatusLogin(true)

                            val intent = Intent(this@LoginActivity, MainMoActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
                        }
                    }else {
                        loading.visibility = View.GONE
                        val errorBody = JSONObject(response.errorBody()?.string())
                        Toast.makeText(this@LoginActivity, errorBody.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLoginPegawai>, t: Throwable) {
                    loading.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, "Error:" + t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun callComponent() {
        inputUsername = findViewById(R.id.inputUsername)
        inputPassword = findViewById(R.id.inputPassword)
        loading = findViewById(R.id.loading)
        radioMember = findViewById(R.id.radioMember)
        radioInstruktur = findViewById(R.id.radioInstruktur)
        radioMO = findViewById(R.id.radioMO)
    }

}