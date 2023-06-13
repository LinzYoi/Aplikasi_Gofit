package com.example.gofit.fragment.instruktur

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.gofit.activity.MainUmumActivity
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentAkunInstrukturBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.instruktur.ResponseInstrukturById
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AkunInstrukturFragment : Fragment() {
    private var _binding: FragmentAkunInstrukturBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAkunInstrukturBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())

        getInstrukturById()

        binding.btnLogout.setOnClickListener {
            val intent = Intent(requireActivity(), MainUmumActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Toast.makeText(requireActivity(), "Logout Success", Toast.LENGTH_SHORT).show()
        }

    }

    fun getInstrukturById() {
        sharedPref.getIdInstruktur()?.let { idInstruktur ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.getInstrukturById(headers, idInstruktur).enqueue(object : Callback<ResponseInstrukturById> {
                override fun onResponse(
                    call: Call<ResponseInstrukturById>,
                    response: Response<ResponseInstrukturById>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            binding.tvNamaInstruktur.text = responseBody.data.namaInstruktur
                            binding.tvIdInstruktur.text = responseBody.data.idInstruktur.toString()
                            binding.tvEmail.text = responseBody.data.email
                            binding.tvTanggalLahirInstruktur.text = responseBody.data.tanggalLahirInstruktur
                            binding.tvNoTeleponInstruktur.text = responseBody.data.noTeleponInstruktur
                            binding.tvGajiInstruktur.text = responseBody.data.gajiInstruktur.toString()
                            binding.tvAlamatInstruktur.text = responseBody.data.alamatInstruktur
                            binding.tvAkumulasiTerlambat.text = responseBody.data.akumulasiTerlambat.toString()
                        }
                    }else {
                        val errorBody = JSONObject(response.errorBody()?.string())
                        Toast.makeText(context, errorBody.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseInstrukturById>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    fun resetPassword() {

    }

}