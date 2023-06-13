package com.example.gofit.fragment.member

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gofit.activity.MainUmumActivity
import com.example.gofit.activity.member.MainMemberActivity
import com.example.gofit.app.ApiConfig
import com.example.gofit.databinding.FragmentAkunMemberBinding
import com.example.gofit.helper.SharedPref
import com.example.gofit.model.member.ResponseMemberById
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AkunMemberFragment : Fragment() {
    private var _binding: FragmentAkunMemberBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAkunMemberBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPref(requireActivity())

        getMemberById()

        binding.btnDepositKelas.setOnClickListener {
            (activity as MainMemberActivity).changeFragment(DepositKelasMemberFragment())
        }

        binding.btnLogout.setOnClickListener {
            val intent = Intent(requireActivity(), MainUmumActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            Toast.makeText(requireActivity(), "Logout Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun getMemberById() {
        sharedPref.getIdMember()?.let { idMember ->
            val token = sharedPref.getToken()

            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $token"

            ApiConfig.instanceRetrofit.getMemberById(headers, idMember).enqueue(object : Callback<ResponseMemberById> {
                override fun onResponse(
                    call: Call<ResponseMemberById>,
                    response: Response<ResponseMemberById>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            binding.tvNamaMember.text = responseBody.data.member.namaMember
                            binding.tvIdMember.text = responseBody.data.member.idMember
                            binding.tvStatusMember.text = responseBody.data.member.statusMember
                            binding.tvEmail.text = responseBody.data.member.email
                            binding.tvTanggalLahirMember.text = responseBody.data.member.tanggalLahirMember
                            binding.tvMasaBerlakuMember.text = responseBody.data.member.masaBerlakuMember
                            binding.tvNoTeleponMember.text = responseBody.data.member.noTeleponMember
                            binding.tvSisaDepositUangMember.text = responseBody.data.member.sisaDepositUangMember.toString()
                            binding.tvAlamatMember.text = responseBody.data.member.alamatMember
                        }
                    }else {
                        val errorBody = JSONObject(response.errorBody()?.string())
                        Toast.makeText(context, errorBody.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseMemberById>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}