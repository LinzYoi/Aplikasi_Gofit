package com.example.gofit.model.presensiBookingKelas

import com.google.gson.annotations.SerializedName

data class ResponsePresensiBookingKelas(

	@field:SerializedName("data")
	val data: DataPresensiBookingKelas,

	@field:SerializedName("message")
	val message: String
)

data class DataPresensiBookingKelas(

	@field:SerializedName("id_jadwal_harian")
	val idJadwalHarian: Int,

	@field:SerializedName("tarif")
	val tarif: Int,

	@field:SerializedName("tanggal_yang_dibooking")
	val tanggalYangDibooking: String,

	@field:SerializedName("id_member")
	val idMember: String,

	@field:SerializedName("tanggal_booking")
	val tanggalBooking: String,

	@field:SerializedName("id_booking_kelas")
	val idBookingKelas: String,

	@field:SerializedName("jenis_pembayaran")
	val jenisPembayaran: String
)
