package com.example.gofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gofit.databinding.RvPerizinanInstrukturBinding
import com.example.gofit.model.instruktur.DataItemGetPerizinanInstrukturByIdInstruktur

class PerizinanInstrukturAdapter(private val item: ArrayList<DataItemGetPerizinanInstrukturByIdInstruktur>): RecyclerView.Adapter<PerizinanInstrukturAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RvPerizinanInstrukturBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItemGetPerizinanInstrukturByIdInstruktur) {
            with(binding) {
                tvNamaKelas.text = data.jadwalHarianForeignKey.jadwalUmumForeignKey.kelasForeignKey.namaKelas
                tvTanggalPerizinan.text = data.tanggalPerizinan
                tvTanggalPembuatanPerizinan.text = data.tanggalPembuatanPerizinan
                tvTanggalKonfirmasiPerizinan.text = data.tanggalKonfirmasiPerizinan
                tvStatusPerizinan.text = data.statusPerizinan
                tvKeteranganPerizinan.text = data.keteranganPerizinan
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvPerizinanInstrukturBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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