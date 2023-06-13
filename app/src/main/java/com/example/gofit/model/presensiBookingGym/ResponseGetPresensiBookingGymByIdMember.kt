package com.example.gofit.model.presensiBookingGym

import com.google.gson.annotations.SerializedName

data class ResponseGetPresensiBookingGymByIdMember(

	@field:SerializedName("data")
	val data: List<DataItemGetPresensiBookingGymByIdMember>,

	@field:SerializedName("message")
	val message: String
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

data class DataItemGetPresensiBookingGymByIdMember(

	@field:SerializedName("waktu_presensi")
	val waktuPresensi: String,

	@field:SerializedName("tanggal_yang_dibooking")
	val tanggalYangDibooking: String,

	@field:SerializedName("id_member")
	val idMember: String,

	@field:SerializedName("slot_waktu")
	val slotWaktu: String,

	@field:SerializedName("member_foreign_key")
	val memberForeignKey: MemberForeignKey,

	@field:SerializedName("tanggal_booking")
	val tanggalBooking: String,

	@field:SerializedName("id_booking_gym")
	val idBookingGym: String
)
