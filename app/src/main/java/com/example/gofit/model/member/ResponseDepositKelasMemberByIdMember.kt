package com.example.gofit.model.member

import com.google.gson.annotations.SerializedName

data class ResponseDepositKelasMemberByIdMember(

	@field:SerializedName("data")
	val data: List<DataItemDepositKelasMemberByIdMember>,

	@field:SerializedName("message")
	val message: String
)

data class KelasForeignKey(

	@field:SerializedName("harga_kelas")
	val hargaKelas: Int,

	@field:SerializedName("kapasitas_kelas")
	val kapasitasKelas: Int,

	@field:SerializedName("nama_kelas")
	val namaKelas: String,

	@field:SerializedName("id_kelas")
	val idKelas: Int
)

data class DataItemDepositKelasMemberByIdMember(

	@field:SerializedName("tanggal_kadaluarsa")
	val tanggalKadaluarsa: String,

	@field:SerializedName("id_member")
	val idMember: String,

	@field:SerializedName("id_kelas")
	val idKelas: Int,

	@field:SerializedName("member_foreign_key")
	val memberForeignKey: MemberForeignKey,

	@field:SerializedName("kelas_foreign_key")
	val kelasForeignKey: KelasForeignKey,

	@field:SerializedName("id_deposit_kelas_member")
	val idDepositKelasMember: String,

	@field:SerializedName("sisa_deposit")
	val sisaDeposit: Int
)

data class MemberForeignKey(

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
