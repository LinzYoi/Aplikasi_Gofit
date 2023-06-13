package com.example.gofit.model.presensiBookingKelas

import com.google.gson.annotations.SerializedName

data class ResponseGetPresensiBookingKelasByIdMember(

	@field:SerializedName("data")
	val data: DataItemGetPresensiBookingKelasByIdMember,

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

data class DataItemGetPresensiBookingKelasByIdMember(

	@field:SerializedName("deposit_kelas_member")
	val depositKelasMember: List<DepositKelasMemberItem>,

	@field:SerializedName("presensi_booking_kelas")
	val presensiBookingKelas: List<PresensiBookingKelasItem>
)

data class DepositKelasMemberItem(

	@field:SerializedName("tanggal_kadaluarsa")
	val tanggalKadaluarsa: String,

	@field:SerializedName("id_member")
	val idMember: String,

	@field:SerializedName("id_kelas")
	val idKelas: Int,

	@field:SerializedName("id_deposit_kelas_member")
	val idDepositKelasMember: String,

	@field:SerializedName("sisa_deposit")
	val sisaDeposit: Int
)

data class InstrukturForeignKey(

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

data class PresensiBookingKelasItem(

	@field:SerializedName("waktu_presensi")
	val waktuPresensi: Any,

	@field:SerializedName("id_jadwal_harian")
	val idJadwalHarian: Int,

	@field:SerializedName("tarif")
	val tarif: Any,

	@field:SerializedName("tanggal_yang_dibooking")
	val tanggalYangDibooking: String,

	@field:SerializedName("id_member")
	val idMember: String,

	@field:SerializedName("member_foreign_key")
	val memberForeignKey: MemberForeignKey,

	@field:SerializedName("tanggal_booking")
	val tanggalBooking: String,

	@field:SerializedName("id_booking_kelas")
	val idBookingKelas: String,

	@field:SerializedName("status_member")
	val statusMember: Any,

	@field:SerializedName("jadwal_harian_foreign_key")
	val jadwalHarianForeignKey: JadwalHarianForeignKey,

	@field:SerializedName("jenis_pembayaran")
	val jenisPembayaran: String
)

data class JadwalHarianForeignKey(

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
