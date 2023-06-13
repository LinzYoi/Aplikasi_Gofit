package com.example.gofit.fragment.instruktur

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gofit.R
import com.example.gofit.activity.instruktur.MainInstrukturActivity
import com.example.gofit.adapter.PerizinanInstrukturAdapter
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentIzinInstrukturBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.instruktur.DataItemGetPerizinanInstrukturByIdInstruktur
import com.example.gofit.model.instruktur.ResponseGetPerizinanInstrukturByIdInstruktur
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class IzinInstrukturFragment : Fragment() {
    private var _binding: FragmentIzinInstrukturBinding? = null
    private  val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIzinInstrukturBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())

        getPerizinanInstruktur()

        binding.btnTambahPerizinanInstruktur.setOnClickListener {
            (activity as MainInstrukturActivity).changeFragment(TambahPerizinanInstrukturFragmentPilihKelas())
        }
    }

    fun getPerizinanInstruktur() {
        sharedPref.getIdInstruktur()?.let { idInstruktur ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.getPerizinanInstrukturByIdInstruktur(headers, idInstruktur).enqueue(object:
                Callback<ResponseGetPerizinanInstrukturByIdInstruktur> {
                override fun onResponse(
                    call: Call<ResponseGetPerizinanInstrukturByIdInstruktur>,
                    response: Response<ResponseGetPerizinanInstrukturByIdInstruktur>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            loadRecyleViewPerizinanInstruktur(responseBody.data as ArrayList<DataItemGetPerizinanInstrukturByIdInstruktur>)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseGetPerizinanInstrukturByIdInstruktur>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    fun loadRecyleViewPerizinanInstruktur(item: ArrayList<DataItemGetPerizinanInstrukturByIdInstruktur>) {
        val adapter = PerizinanInstrukturAdapter(item)
        binding.rvPerizinanInstruktur.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPerizinanInstruktur.adapter = adapter
    }
}