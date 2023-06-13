package com.example.gofit.fragment.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gofit.activity.member.MainMemberActivity
import com.example.gofit.adapter.DepositKelasMemberAdapter

import com.example.gofit.app.ApiConfig

import com.example.gofit.databinding.FragmentDepositKelasMemberBinding
import com.example.gofit.helper.SharedPref

import com.example.gofit.model.member.DataItemDepositKelasMemberByIdMember
import com.example.gofit.model.member.ResponseDepositKelasMemberByIdMember
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DepositKelasMemberFragment : Fragment() {
    private var _binding: FragmentDepositKelasMemberBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDepositKelasMemberBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())

        getDataDepositKelasMember()
    }

    fun getDataDepositKelasMember() {
        sharedPref.getIdMember()?.let { idMember ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

                ApiConfig.instanceRetrofit.getDataDepositKelasMemberByIdMember(headers, idMember).enqueue(object: Callback<ResponseDepositKelasMemberByIdMember> {
                    override fun onResponse(
                        call: Call<ResponseDepositKelasMemberByIdMember>,
                        response: Response<ResponseDepositKelasMemberByIdMember>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                loadRecyleViewDepositKelasMember(responseBody.data as ArrayList<DataItemDepositKelasMemberByIdMember>)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseDepositKelasMemberByIdMember>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

        }
    }

    fun loadRecyleViewDepositKelasMember(item: ArrayList<DataItemDepositKelasMemberByIdMember>) {
        val adapter = DepositKelasMemberAdapter(item)
        binding.rvDepositKelasMember.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDepositKelasMember.adapter = adapter
    }

}