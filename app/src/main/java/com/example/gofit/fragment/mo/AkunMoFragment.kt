package com.example.gofit.fragment.mo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gofit.R
import com.example.gofit.activity.MainUmumActivity
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentAkunMoBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.mo.ResponsePegawaiById
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AkunMoFragment : Fragment() {
    private var _binding: FragmentAkunMoBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAkunMoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())

        getPegawaiById()

        binding.btnLogout.setOnClickListener {
            val intent = Intent(requireActivity(), MainUmumActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Toast.makeText(requireActivity(), "Logout Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun getPegawaiById() {
        sharedPref.getIdPegawai()?.let { idPegawai ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.getPegawaiById(headers, idPegawai).enqueue(object :
                Callback<ResponsePegawaiById> {
                override fun onResponse(
                    call: Call<ResponsePegawaiById>,
                    response: Response<ResponsePegawaiById>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            binding.tvNamaPegawai.text = responseBody.data.namaPegawai
                            binding.tvIdPegawai.text = responseBody.data.idPegawai.toString()
                            binding.tvEmail.text = responseBody.data.email
                            binding.tvTanggalLahirPegawai.text = responseBody.data.tanggalLahirPegawai
                            binding.tvNoTeleponPegawai.text = responseBody.data.noTeleponPegawai
                            binding.tvAlamatPegawai.text = responseBody.data.alamatPegawai
                        }
                    }else {
                        val errorBody = JSONObject(response.errorBody()?.string())
                        Toast.makeText(context, errorBody.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponsePegawaiById>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}