package com.example.gofit.model.instruktur

import com.google.gson.annotations.SerializedName

data class ResponsePerizinanInstruktur(

	@field:SerializedName("data")
	val data: DataPerizinanInstruktur,

	@field:SerializedName("message")
	val message: String
)

data class DataPerizinanInstruktur(

	@field:SerializedName("keterangan_perizinan")
	val keteranganPerizinan: String,

	@field:SerializedName("id_jadwal_harian")
	val idJadwalHarian: Int,

	@field:SerializedName("tanggal_pembuatan_perizinan")
	val tanggalPembuatanPerizinan: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("tanggal_perizinan")
	val tanggalPerizinan: String,

	@field:SerializedName("created_at")
	val createdAt: Any,

	@field:SerializedName("id_perizinan")
	val idPerizinan: Int,

	@field:SerializedName("id_instruktur")
	val idInstruktur: String
)
