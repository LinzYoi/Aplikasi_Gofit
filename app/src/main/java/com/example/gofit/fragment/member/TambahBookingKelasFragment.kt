package com.example.gofit.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.gofit.R
import com.example.gofit.activity.member.MainMemberActivity
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentTambahBookingKelasBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.presensiBookingKelas.ResponsePresensiBookingKelas
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahBookingKelasFragment : Fragment() {
    private var _binding: FragmentTambahBookingKelasBinding? = null
    private val binding get() = _binding!!
    private val ITEM_JENIS_PEMBAYARAN = arrayOf(
        "Deposit Kelas",
        "Deposit Uang",
    )

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTambahBookingKelasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())
        setExposedDropdownMenu()

        binding.tanggal.isEnabled = false
        binding.namaInstruktur.isEnabled = false
        binding.namaKelas.isEnabled = false

        binding.tanggal.setText(sharedPref.getTanggal())
        binding.namaInstruktur.setText(sharedPref.getNamaInstruktur())
        binding.namaKelas.setText(sharedPref.getNamaKelas())

        binding.btnTambahPresensiBookingKelas.setOnClickListener() {
            tambahPresensiBookingKelas()
        }

        binding.btnCancelTambahPresensiBookingKelas.setOnClickListener() {
            (activity as MainMemberActivity).changeFragment(BookingKelasFragment())
        }
    }

    fun tambahPresensiBookingKelas() {
        if (binding.jenisPembayaran.text.toString().isEmpty()) {
            binding.jenisPembayaran.error = "Jenis Pembayaran Harus Diisi"
            binding.jenisPembayaran.requestFocus()
            return
        }

        binding.loading.visibility = View.VISIBLE

        sharedPref.getIdMember()?.let { idMember ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.presensiBookingKelas(headers, idMember, sharedPref.getIdJadwalHarian() ?: 0, binding.jenisPembayaran.text.toString()).enqueue(object:
                Callback<ResponsePresensiBookingKelas> {
                override fun onFailure(call: Call<ResponsePresensiBookingKelas>, t: Throwable) {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error:" + t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ResponsePresensiBookingKelas>, response: Response<ResponsePresensiBookingKelas>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            binding.loading.visibility = View.GONE
                            (activity as MainMemberActivity).changeFragment(BookingKelasFragment())
                            Toast.makeText(requireContext(), "Tambah Booking Kelas Success", Toast.LENGTH_SHORT).show()
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

    fun setExposedDropdownMenu() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(), R.layout.item_list, ITEM_JENIS_PEMBAYARAN)
        binding.jenisPembayaran.setAdapter(adapter)
    }
}