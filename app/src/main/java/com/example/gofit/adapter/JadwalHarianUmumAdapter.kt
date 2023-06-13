package com.example.gofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gofit.databinding.RvJadwalHarianUmumBinding
import com.example.gofit.model.jadwalHarian.DataItemJadwalHarian

class JadwalHarianUmumAdapter(private val item: ArrayList<DataItemJadwalHarian>): RecyclerView.Adapter<JadwalHarianUmumAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RvJadwalHarianUmumBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItemJadwalHarian) {
            with(binding) {
                tvHari.text = data.jadwalUmumForeignKey.hari
                tvTanggal.text = data.tanggal
                tvKelas.text = data.jadwalUmumForeignKey.kelasForeignKey.namaKelas
                tvNamaInstruktur.text = data.instrukturForeignKey.namaInstruktur
                tvSlotKelas.text = data.slotKelas.toString()
                tvJam.text = data.jadwalUmumForeignKey.jam
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvJadwalHarianUmumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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