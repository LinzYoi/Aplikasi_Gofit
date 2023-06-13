package com.example.gofit.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gofit.activity.member.MainMemberActivity
import com.example.gofit.adapter.JadwalHarianUmumAdapter
import com.example.gofit.adapter.TambahPresensiBookingKelasMemberPilihKelasAdapter
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentTambahBookingKelasPilihKelasBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.jadwalHarian.DataItemJadwalHarian
import com.example.gofit.model.jadwalHarian.ResponseJadwalHarian
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahBookingKelasFragmentPilihKelas : Fragment() {
    private var _binding: FragmentTambahBookingKelasPilihKelasBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTambahBookingKelasPilihKelasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())


        getJadwalHarian()
    }

    fun getJadwalHarian() {
        ApiConfig.instanceRetrofit.getJadwalHarian().enqueue(object:
            Callback<ResponseJadwalHarian> {
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
        val adapter = TambahPresensiBookingKelasMemberPilihKelasAdapter(item)
        binding.rvTambahPresensiBookingKelasPilihKelas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTambahPresensiBookingKelasPilihKelas.adapter = adapter

        adapter.setOnItemClickListener(object : TambahPresensiBookingKelasMemberPilihKelasAdapter.OnItemClickListener {
            override fun onItemClick(data: DataItemJadwalHarian) {
                sharedPref.setIdJadwalHarian(data.idJadwalHarian)
                sharedPref.setTanggal(data.tanggal)
                sharedPref.setNamaInstruktur(data.instrukturForeignKey.namaInstruktur)
                sharedPref.setNamaKelas(data.jadwalUmumForeignKey.kelasForeignKey.namaKelas)
                (activity as MainMemberActivity).changeFragment(TambahBookingKelasFragment())
            }
        })
    }
}