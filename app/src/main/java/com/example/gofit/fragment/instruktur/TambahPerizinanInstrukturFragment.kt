package com.example.gofit.fragment.instruktur

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gofit.R
import com.example.gofit.activity.instruktur.MainInstrukturActivity
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentTambahPerizinanInstrukturBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.instruktur.ResponsePerizinanInstruktur
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahPerizinanInstrukturFragment : Fragment() {
    private var _binding: FragmentTambahPerizinanInstrukturBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTambahPerizinanInstrukturBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())

        binding.tanggalPerizinan.isEnabled = false
        binding.namaKelas.isEnabled = false

        binding.tanggalPerizinan.setText(sharedPref.getTanggal())
        binding.namaKelas.setText(sharedPref.getNamaKelas())

        binding.btnTambahPerizinanInstruktur.setOnClickListener() {
            tambahPerizinanInstruktur()
        }

        binding.btnCancelTambahPerizinanInstruktur.setOnClickListener() {
            (activity as MainInstrukturActivity).changeFragment(IzinInstrukturFragment())
        }
    }

    fun tambahPerizinanInstruktur() {
        if (binding.keteranganPerizinan.text.toString().isEmpty()) {
            binding.keteranganPerizinan.error = "Keterangan Harus Diisi"
            binding.keteranganPerizinan.requestFocus()
            return
        }

        binding.loading.visibility = View.VISIBLE

        sharedPref.getIdInstruktur()?.let { idInstruktur ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.perizinanInstruktur(headers, idInstruktur, sharedPref.getIdJadwalHarian() ?: 0, sharedPref.getTanggal().toString(), binding.keteranganPerizinan.text.toString()).enqueue(object:
                Callback<ResponsePerizinanInstruktur> {
                override fun onFailure(call: Call<ResponsePerizinanInstruktur>, t: Throwable) {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error:" + t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ResponsePerizinanInstruktur>, response: Response<ResponsePerizinanInstruktur>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            binding.loading.visibility = View.GONE
                            (activity as MainInstrukturActivity).changeFragment(IzinInstrukturFragment())
                            Toast.makeText(requireContext(), "Buat Izin Kelas Success", Toast.LENGTH_SHORT).show()
                        }
                    }else {
                        binding.loading.visibility = View.GONE
                        val errorBody = JSONObject(response.errorBody()?.string())
                        Toast.makeText(requireContext(), errorBody.getString("message"), Toast.LENGTH_SHORT).show()
                    }

                }
            })
        }
    }
}