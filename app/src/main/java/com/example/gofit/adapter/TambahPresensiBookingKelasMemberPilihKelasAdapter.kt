package com.example.gofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gofit.databinding.RvTambahPresensiBookingKelasPilihKelasBinding
import com.example.gofit.model.jadwalHarian.DataItemJadwalHarian

class TambahPresensiBookingKelasMemberPilihKelasAdapter(private val item: ArrayList<DataItemJadwalHarian>) : RecyclerView.Adapter<TambahPresensiBookingKelasMemberPilihKelasAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    inner class ViewHolder(val binding: RvTambahPresensiBookingKelasPilihKelasBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val data = item[position]
                    itemClickListener?.onItemClick(data)
                }
            }
        }

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
        val binding = RvTambahPresensiBookingKelasPilihKelasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = item[position]
        holder.bind(data)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(data: DataItemJadwalHarian)
    }
}
