package com.example.gofit.model.jadwalHarian

import com.google.gson.annotations.SerializedName

data class ResponseJadwalHarian(

	@field:SerializedName("data")
	val data: List<DataItemJadwalHarian>,

	@field:SerializedName("message")
	val message: String
)

data class JadwalUmumForeignKey(

	@field:SerializedName("hari")
	val hari: String,

	@field:SerializedName("slot_kelas")
	val slotKelas: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("jam")
	val jam: String,

	@field:SerializedName("created_at")
	val createdAt: Any,

	@field:SerializedName("id_kelas")
	val idKelas: Int,

	@field:SerializedName("tanggal")
	val tanggal: String,

	@field:SerializedName("kelas_foreign_key")
	val kelasForeignKey: KelasForeignKey,

	@field:SerializedName("id_instruktur")
	val idInstruktur: Int,

	@field:SerializedName("id_jadwal_umum")
	val idJadwalUmum: Int
)

data class InstrukturForeignKey(

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

data class DataItemJadwalHarian(

	@field:SerializedName("id_jadwal_harian")
	val idJadwalHarian: Int,

	@field:SerializedName("slot_kelas")
	val slotKelas: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: Any,

	@field:SerializedName("instruktur_foreign_key")
	val instrukturForeignKey: InstrukturForeignKey,

	@field:SerializedName("tanggal")
	val tanggal: String,

	@field:SerializedName("id_instruktur")
	val idInstruktur: Int,

	@field:SerializedName("jadwal_umum_foreign_key")
	val jadwalUmumForeignKey: JadwalUmumForeignKey,

	@field:SerializedName("id_jadwal_umum")
	val idJadwalUmum: Int,

	@field:SerializedName("status")
	val status: String
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
