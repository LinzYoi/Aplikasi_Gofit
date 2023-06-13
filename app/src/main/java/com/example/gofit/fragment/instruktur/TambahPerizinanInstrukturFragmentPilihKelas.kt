package com.example.gofit.fragment.instruktur

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gofit.R
import com.example.gofit.activity.instruktur.MainInstrukturActivity
import com.example.gofit.adapter.TambahPerizinanInstrukturPilihKelasAdapter
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentTambahPerizinanInstrukturPilihKelasBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.jadwalHarian.DataItemJadwalHarian
import com.example.gofit.model.jadwalHarian.ResponseJadwalHarian
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahPerizinanInstrukturFragmentPilihKelas : Fragment() {
    private var _binding: FragmentTambahPerizinanInstrukturPilihKelasBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTambahPerizinanInstrukturPilihKelasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())


        getJadwalHarianByIdInstruktur()
    }

    fun getJadwalHarianByIdInstruktur() {
        sharedPref.getIdInstruktur()?.let { idInstruktur ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.getJadwalHarianByIdInstruktur(headers, idInstruktur).enqueue(object :
                Callback<ResponseJadwalHarian> {
                override fun onResponse(
                    call: Call<ResponseJadwalHarian>,
                    response: Response<ResponseJadwalHarian>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            loadRecyleViewJadwalHarianByIdInstruktur(responseBody.data as ArrayList<DataItemJadwalHarian>)
                        }
                    }else {
                        val errorBody = JSONObject(response.errorBody()?.string())
                        Toast.makeText(context, errorBody.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseJadwalHarian>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    fun loadRecyleViewJadwalHarianByIdInstruktur(item: ArrayList<DataItemJadwalHarian>) {
        val adapter = TambahPerizinanInstrukturPilihKelasAdapter(item)
        binding.rvTambahPerizinanInstrukturPilihKelas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTambahPerizinanInstrukturPilihKelas.adapter = adapter

        adapter.setOnItemClickListener(object : TambahPerizinanInstrukturPilihKelasAdapter.OnItemClickListener {
            override fun onItemClick(data: DataItemJadwalHarian) {
                sharedPref.setIdJadwalHarian(data.idJadwalHarian)
                sharedPref.setTanggal(data.tanggal)
                sharedPref.setNamaKelas(data.jadwalUmumForeignKey.kelasForeignKey.namaKelas)
                (activity as MainInstrukturActivity).changeFragment(TambahPerizinanInstrukturFragment())
            }
        })
    }
}