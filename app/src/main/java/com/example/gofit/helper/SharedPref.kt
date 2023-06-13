package com.example.gofit.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPref(activity: Activity) {

    val login = "login"
    val mypref = "MAIN_PRF"
    val sp: SharedPreferences

    init {
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean) {
        sp.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin(): Boolean {
        return sp.getBoolean(login, false)
    }

    fun getIdMember(): String? {
        return sp.getString("id_member", null)
    }

    fun setIdMember(id_member: String) {
        sp.edit().putString("id_member", id_member).apply()
    }

    fun getIdInstruktur(): String? {
        return sp.getString("id_instruktur", null)
    }

    fun setIdInstruktur(id_instruktur: String) {
        sp.edit().putString("id_instruktur", id_instruktur).apply()
    }

    fun getIdPegawai(): String? {
        return sp.getString("id_pegawai", null)
    }

    fun setIdPegawai(id_pegawai: String) {
        sp.edit().putString("id_pegawai", id_pegawai).apply()
    }

    fun getIdJadwalHarian(): Int? {
        return sp.getInt("id_jadwal_harian", 0) // 0 adalah nilai default jika tidak ada data yang tersimpan
    }

    fun setIdJadwalHarian(id_jadwal_harian: Int) {
        sp.edit().putInt("id_jadwal_harian", id_jadwal_harian).apply()
    }

    //set text dalam tambah booking kelas
    fun getTanggal(): String? {
        return sp.getString("tanggal", null)
    }

    fun setTanggal(tanggal: String) {
        sp.edit().putString("tanggal", tanggal).apply()
    }

    fun getNamaInstruktur(): String? {
        return sp.getString("nama_instruktur", null)
    }

    fun setNamaInstruktur(nama_instruktur: String) {
        sp.edit().putString("nama_instruktur", nama_instruktur).apply()
    }

    fun getNamaKelas(): String? {
        return sp.getString("nama_kelas", null)
    }

    fun setNamaKelas(nama_kelas: String) {
        sp.edit().putString("nama_kelas", nama_kelas).apply()
    }
    //set text dalam tambah booking kelas

    fun getIdBookingKelas(): String? {
        return sp.getString("id_booking_kelas", null)
    }

    fun setIdBookingKelas(id_booking_kelas: String) {
        sp.edit().putString("id_booking_kelas", id_booking_kelas).apply()
    }

    fun getToken(): String? {
        return sp.getString("token", null)
    }

    fun setToken(token: String) {
        sp.edit().putString("token", token).apply()
    }

}