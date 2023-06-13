package com.example.gofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gofit.databinding.RvPresensiBookingGymMemberBinding
import com.example.gofit.model.presensiBookingGym.DataItemGetPresensiBookingGymByIdMember

class PresensiBookingGymMemberAdapter(private val item: ArrayList<DataItemGetPresensiBookingGymByIdMember>): RecyclerView.Adapter<PresensiBookingGymMemberAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RvPresensiBookingGymMemberBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItemGetPresensiBookingGymByIdMember) {
            with(binding) {
                tvTanggalBooking.text = data.tanggalBooking
                tvTanggalYangDibooking.text = data.tanggalYangDibooking
                tvSlotWaktu.text = data.slotWaktu
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvPresensiBookingGymMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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