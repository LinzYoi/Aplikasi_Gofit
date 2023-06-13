package com.example.gofit.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gofit.R
import com.example.gofit.activity.member.MainMemberActivity
import com.example.gofit.adapter.PresensiBookingGymMemberAdapter
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentBookingGymBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.presensiBookingGym.DataItemGetPresensiBookingGymByIdMember
import com.example.gofit.model.presensiBookingGym.ResponseGetPresensiBookingGymByIdMember
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookingGymFragment : Fragment() {
    private var _binding: FragmentBookingGymBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingGymBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())

        getPresensiBookingGymMember()

        binding.btnTambahBookingGym.setOnClickListener {
            (activity as MainMemberActivity).changeFragment(TambahBookingGymFragment())
        }
    }

    fun getPresensiBookingGymMember() {
        sharedPref.getIdMember()?.let { idMember ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.getPresensiBookingGymByIdMember(headers, idMember).enqueue(object:
                Callback<ResponseGetPresensiBookingGymByIdMember> {
                override fun onResponse(
                    call: Call<ResponseGetPresensiBookingGymByIdMember>,
                    response: Response<ResponseGetPresensiBookingGymByIdMember>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            loadRecyleViewPresensiBookingGymMember(responseBody.data as ArrayList<DataItemGetPresensiBookingGymByIdMember>)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseGetPresensiBookingGymByIdMember>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }
    }

    fun loadRecyleViewPresensiBookingGymMember(item: ArrayList<DataItemGetPresensiBookingGymByIdMember>) {
        val adapter = PresensiBookingGymMemberAdapter(item)
        binding.rvPresensiBookingGymMember.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPresensiBookingGymMember.adapter = adapter
    }
}