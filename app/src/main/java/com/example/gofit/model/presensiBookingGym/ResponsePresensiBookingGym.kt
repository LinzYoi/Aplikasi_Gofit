package com.example.gofit.model.presensiBookingGym

import com.google.gson.annotations.SerializedName

data class ResponsePresensiBookingGym(

	@field:SerializedName("data")
	val data: DataPresensiBookingGym,

	@field:SerializedName("message")
	val message: String,
)

data class DataPresensiBookingGym(

	@field:SerializedName("waktu_presensi")
	val waktuPresensi: String?,

	@field:SerializedName("tanggal_yang_dibooking")
	val tanggalYangDibooking: String,

	@field:SerializedName("id_member")
	val idMember: String,

	@field:SerializedName("slot_waktu")
	val slotWaktu: String,

	@field:SerializedName("tanggal_booking")
	val tanggalBooking: String,

	@field:SerializedName("id_booking_gym")
	val idBookingGym: String,
)
