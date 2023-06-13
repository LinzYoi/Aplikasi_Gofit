package com.example.gofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gofit.databinding.RvDepositKelasMemberByIdBinding
import com.example.gofit.model.member.DataItemDepositKelasMemberByIdMember

class DepositKelasMemberAdapter(private val item: ArrayList<DataItemDepositKelasMemberByIdMember>): RecyclerView.Adapter<DepositKelasMemberAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RvDepositKelasMemberByIdBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItemDepositKelasMemberByIdMember) {
            with(binding) {
                tvNamaKelas.text = data.kelasForeignKey.namaKelas
                tvHargaKelas.text = data.kelasForeignKey.hargaKelas.toString()
                tvSisaDeposit.text = data.sisaDeposit.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvDepositKelasMemberByIdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = item[position]
        holder.bind(data)
    }
}