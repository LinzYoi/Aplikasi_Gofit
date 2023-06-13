package com.example.gofit.model.instruktur

import com.example.gofit.model.login.DataInstruktur
import com.google.gson.annotations.SerializedName

data class ResponseInstrukturById(

	@field:SerializedName("data")
	val data: DataInstrukturById,

	@field:SerializedName("message")
	val message: String
)

data class DataInstrukturById(

	@field:SerializedName("nama_instruktur")
	val namaInstruktur: String,

	@field:SerializedName("no_telepon_instruktur")
	val noTeleponInstruktur: String,

	@field:SerializedName("tanggal_lahir_instruktur")
	val tanggalLahirInstruktur: String,

	@field:SerializedName("akumulasi_terlambat")
	val akumulasiTerlambat: Int,

	@field:SerializedName("alamat_instruktur")
	val alamatInstruktur: String,

	@field:SerializedName("gaji_instruktur")
	val gajiInstruktur: Int,

	@field:SerializedName("id_instruktur")
	val idInstruktur: Int,

	@field:SerializedName("email")
	val email: String
)
