package com.example.gofit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gofit.adapter.JadwalHarianUmumAdapter
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.ActivityMainUmumBinding
import com.example.gofit.model.jadwalHarian.DataItemJadwalHarian
import com.example.gofit.model.jadwalHarian.ResponseJadwalHarian
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

class MainUmumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainUmumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainUmumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getJadwalHarian()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun getJadwalHarian() {
        ApiConfig.instanceRetrofit.getJadwalHarian().enqueue(object: Callback<ResponseJadwalHarian> {
            override fun onResponse(
                call: Call<ResponseJadwalHarian>,
                response: Response<ResponseJadwalHarian>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        loadRecyleViewJadwalHarian(responseBody.data as ArrayList<DataItemJadwalHarian>)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseJadwalHarian>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadRecyleViewJadwalHarian(item: ArrayList<DataItemJadwalHarian>) {
        val adapter = JadwalHarianUmumAdapter(item)
        binding.rvJadwalHarianUmum.layoutManager = LinearLayoutManager(this)
        binding.rvJadwalHarianUmum.adapter = adapter
    }
}


