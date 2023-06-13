package com.example.gofit.model.login

import com.google.gson.annotations.SerializedName

data class ResponseLoginMember(

	@field:SerializedName("data")
	val data: DataMember,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token_type")
	val tokenType: String,

	@field:SerializedName("token")
	val token: String
)

data class DataMember(

	@field:SerializedName("nama_member")
	val namaMember: String,

	@field:SerializedName("alamat_member")
	val alamatMember: String,

	@field:SerializedName("masa_berlaku_member")
	val masaBerlakuMember: String,

	@field:SerializedName("no_telepon_member")
	val noTeleponMember: String,

	@field:SerializedName("sisa_deposit_uang_member")
	val sisaDepositUangMember: Int,

	@field:SerializedName("id_member")
	val idMember: String,

	@field:SerializedName("tanggal_lahir_member")
	val tanggalLahirMember: String,

	@field:SerializedName("jenis_kelamin_member")
	val jenisKelaminMember: String,

	@field:SerializedName("status_member")
	val statusMember: String,

	@field:SerializedName("email")
	val email: String
)
