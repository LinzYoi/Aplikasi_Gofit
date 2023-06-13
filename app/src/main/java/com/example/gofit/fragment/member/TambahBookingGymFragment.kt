package com.example.gofit.fragment.member

import android.app.DatePickerDialog
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
import com.example.gofit.databinding.FragmentTambahBookingGymBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.presensiBookingGym.ResponsePresensiBookingGym
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class TambahBookingGymFragment : Fragment() {
    private var _binding: FragmentTambahBookingGymBinding? = null
    private val binding get() = _binding!!
    private val ITEM_SLOT_WAKTU = arrayOf(
        "07:00-09:00",
        "09:00-11:00",
        "11:00-13:00",
        "13:00-15:00",
        "15:00-17:00",
        "17:00-19:00",
        "19:00-21:00",
    )

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTambahBookingGymBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())
        setExposedDropdownMenu()

        val calendar = Calendar.getInstance()
        val tahun = calendar.get(Calendar.YEAR)
        val bulan = calendar.get(Calendar.MONTH)
        val hari = calendar.get(Calendar.DAY_OF_MONTH)

        binding.tanggalYangDibooking.setOnFocusChangeListener { view, b ->
            val datePicker =
                this?.let { it1 ->
                    DatePickerDialog(requireContext(), { view, year, month, dayOfMonth ->
                        binding.tanggalYangDibooking.setText("${year}-${(month.toInt() + 1).toString()}-${dayOfMonth}")
                    }, tahun, bulan, hari)
                }
            if(b){
                datePicker?.datePicker?.minDate = System.currentTimeMillis()
                datePicker?.show()
            }else{
                datePicker?.hide()
            }
        }

        binding.btnTambahPresensiBookingGym.setOnClickListener() {
            tambahPresensiBookingGym()
        }

        binding.btnCancelTambahPresensiBookingGym.setOnClickListener() {
            (activity as MainMemberActivity).changeFragment(BookingGymFragment())
        }
    }

    fun tambahPresensiBookingGym() {
        if (binding.tanggalYangDibooking.text.toString().isEmpty()) {
            binding.tanggalYangDibooking.error = "Tanggal Yang Dibooking Harus Diisi"
            binding.tanggalYangDibooking.requestFocus()
            return
        }else if (binding.slotWaktu.text.toString().isEmpty()) {
            binding.slotWaktu.error = "Slot Waktu Harus Diisi"
            binding.slotWaktu.requestFocus()
            return
        }

        binding.loading.visibility = View.VISIBLE

        sharedPref.getIdMember()?.let { idMember ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.presensiBookingGym(headers, idMember, binding.tanggalYangDibooking.text.toString(), binding.slotWaktu.text.toString()).enqueue(object: Callback<ResponsePresensiBookingGym> {
                override fun onFailure(call: Call<ResponsePresensiBookingGym>, t: Throwable) {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error:" + t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ResponsePresensiBookingGym>, response: Response<ResponsePresensiBookingGym>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            binding.loading.visibility = View.GONE
                            (activity as MainMemberActivity).changeFragment(BookingGymFragment())
                            Toast.makeText(requireContext(), "Tambah Booking Gym Success", Toast.LENGTH_SHORT).show()
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
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(), R.layout.item_list, ITEM_SLOT_WAKTU)
        binding.slotWaktu.setAdapter(adapter)
    }

}