package com.example.gofit.model.login

import com.google.gson.annotations.SerializedName

data class ResponseLoginInstruktur(

	@field:SerializedName("data")
	val data: DataInstruktur,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token_type")
	val tokenType: String,

	@field:SerializedName("token")
	val token: String
)

data class DataInstruktur(

	@field:SerializedName("nama_instruktur")
	val namaInstruktur: String,

	@field:SerializedName("no_telepon_instruktur")
	val noTeleponInstruktur: String,

	@field:SerializedName("tanggal_lahir_instruktur")
	val tanggalLahirInstruktur: String,

	@field:SerializedName("alamat_instruktur")
	val alamatInstruktur: String,

	@field:SerializedName("gaji_instruktur")
	val gajiInstruktur: Int,

	@field:SerializedName("id_instruktur")
	val idInstruktur: Int,

	@field:SerializedName("email")
	val email: String
)
