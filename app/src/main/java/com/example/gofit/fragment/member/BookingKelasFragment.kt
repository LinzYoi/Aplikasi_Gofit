package com.example.gofit.fragment.member

import PresensiBookingKelasMemberAdapter
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gofit.R
import com.example.gofit.activity.member.MainMemberActivity
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentBookingKelasBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.presensiBookingKelas.PresensiBookingKelasItem
import com.example.gofit.model.presensiBookingKelas.ResponseDestroyPresensiBookingKelas
import com.example.gofit.model.presensiBookingKelas.ResponseGetPresensiBookingKelasByIdMember
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookingKelasFragment : Fragment() {
    private var _binding: FragmentBookingKelasBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingKelasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())

        getPresensiBookingKelasMember()

        binding.btnTambahBookingKelas.setOnClickListener {
            (activity as MainMemberActivity).changeFragment(TambahBookingKelasFragmentPilihKelas())
        }
    }

    fun getPresensiBookingKelasMember() {
        sharedPref.getIdMember()?.let { idMember ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.getPresensiBookingKelasByIdMember(headers, idMember).enqueue(object :
                Callback<ResponseGetPresensiBookingKelasByIdMember> {
                override fun onResponse(
                    call: Call<ResponseGetPresensiBookingKelasByIdMember>,
                    response: Response<ResponseGetPresensiBookingKelasByIdMember>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            loadRecyclerViewPresensiBookingKelasMember(responseBody.data.presensiBookingKelas)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseGetPresensiBookingKelasByIdMember>, t: Throwable) {
                    TODO()
                }
            })
        }
    }

    fun loadRecyclerViewPresensiBookingKelasMember(item: List<PresensiBookingKelasItem>) {
        val adapter = PresensiBookingKelasMemberAdapter(item)
        binding.rvPresensiBookingKelasMember.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPresensiBookingKelasMember.adapter = adapter

        adapter.setOnDeleteButtonClickListener(object : PresensiBookingKelasMemberAdapter.OnItemClickListener {
            override fun onDeleteButtonClick(data: PresensiBookingKelasItem) {
                sharedPref.setIdBookingKelas(data.idBookingKelas)

                val alertDialogBuilder = AlertDialog.Builder(requireContext())
                alertDialogBuilder.setTitle("Konfirmasi Hapus")
                alertDialogBuilder.setMessage("Apakah Anda yakin ingin membatalkan booking kelas ini?")
                alertDialogBuilder.setPositiveButton("Ya") { dialog, _ ->
                    // Panggil fungsi untuk menghapus item dari server
                    deletePresensiBookingKelasItem(data)
                    dialog.dismiss()
                }
                alertDialogBuilder.setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        })
    }

    private fun deletePresensiBookingKelasItem(data: PresensiBookingKelasItem) {
        sharedPref.getIdBookingKelas()?.let { idBookingKelas ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.destroyPresensiBookingKelas(headers, idBookingKelas).enqueue(object :
                Callback<ResponseDestroyPresensiBookingKelas> {
                override fun onResponse(
                    call: Call<ResponseDestroyPresensiBookingKelas>,
                    response: Response<ResponseDestroyPresensiBookingKelas>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            // Merefresh fragment dengan menggantinya dengan fragment yang sama
                            val fragmentTransaction = parentFragmentManager.beginTransaction()
                            fragmentTransaction.replace(R.id.container, BookingKelasFragment())
                            fragmentTransaction.commit()
                            Toast.makeText(requireActivity(), "Cancel Booking Kelas Success", Toast.LENGTH_SHORT).show()
                        }
                    }else {
                        val errorBody = JSONObject(response.errorBody()?.string())
                        Toast.makeText(requireContext(), errorBody.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseDestroyPresensiBookingKelas>, t: Throwable) {
                    TODO()
                }
            })
        }
    }
}