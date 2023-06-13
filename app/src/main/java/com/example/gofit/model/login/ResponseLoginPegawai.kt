package com.example.gofit.model.login

import com.google.gson.annotations.SerializedName

data class ResponseLoginPegawai(

	@field:SerializedName("data")
	val data: DataPegawai,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token_type")
	val tokenType: String,

	@field:SerializedName("token")
	val token: String
)

data class DataPegawai(

	@field:SerializedName("tanggal_lahir_pegawai")
	val tanggalLahirPegawai: String,

	@field:SerializedName("no_telepon_pegawai")
	val noTeleponPegawai: String,

	@field:SerializedName("nama_pegawai")
	val namaPegawai: String,

	@field:SerializedName("alamat_pegawai")
	val alamatPegawai: String,

	@field:SerializedName("role_pegawai")
	val rolePegawai: String,

	@field:SerializedName("id_pegawai")
	val idPegawai: Int,

	@field:SerializedName("email")
	val email: String
)
