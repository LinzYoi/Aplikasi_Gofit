package com.example.gofit.app

import com.example.gofit.model.instruktur.ResponseGetPerizinanInstrukturByIdInstruktur
import com.example.gofit.model.instruktur.ResponseInstrukturById
import com.example.gofit.model.instruktur.ResponsePerizinanInstruktur
import com.example.gofit.model.jadwalHarian.ResponseJadwalHarian
import com.example.gofit.model.login.ResponseLoginInstruktur
import com.example.gofit.model.login.ResponseLoginMember
import com.example.gofit.model.login.ResponseLoginPegawai
import com.example.gofit.model.member.ResponseDepositKelasMemberByIdMember
import com.example.gofit.model.member.ResponseMemberById
import com.example.gofit.model.mo.ResponsePegawaiById
import com.example.gofit.model.presensiBookingGym.ResponseGetPresensiBookingGymByIdMember
import com.example.gofit.model.presensiBookingGym.ResponsePresensiBookingGym
import com.example.gofit.model.presensiBookingKelas.ResponseDestroyPresensiBookingKelas
import com.example.gofit.model.presensiBookingKelas.ResponseGetPresensiBookingKelasByIdMember
import com.example.gofit.model.presensiBookingKelas.ResponsePresensiBookingKelas
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("loginMember")
    fun loginMember(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Call<ResponseLoginMember>

    @FormUrlEncoded
    @POST("loginInstruktur")
    fun loginInstruktur(
        @Field("username") username:String,
        @Field("password") password:String,
    ):Call<ResponseLoginInstruktur>

    @FormUrlEncoded
    @POST("loginPegawai")
    fun loginPegawai(
        @Field("username") username:String,
        @Field("password") password:String,
    ):Call<ResponseLoginPegawai>

    @GET("jadwalHarian")
    fun getJadwalHarian():Call<ResponseJadwalHarian>

    @GET("jadwalHarian/{id}")
    fun getJadwalHarianByIdInstruktur(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id_instruktur: String,
    ):Call<ResponseJadwalHarian>

    @GET("member/{id}")
    fun getMemberById(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id_member: String
    ): Call<ResponseMemberById>

    @GET("instruktur/{id}")
    fun getInstrukturById(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id_instruktur: String
    ): Call<ResponseInstrukturById>

    @GET("pegawai/{id}")
    fun getPegawaiById(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id_pegawai: String
    ): Call<ResponsePegawaiById>

    @FormUrlEncoded
    @POST("presensiBookingGym")
    fun presensiBookingGym(
        @HeaderMap headers: Map<String, String>,
        @Field("id_member") id_member: String,
        @Field("tanggal_yang_dibooking") tanggal_yang_dibooking: String,
        @Field("slot_waktu") slot_waktu: String,
    ):Call<ResponsePresensiBookingGym>

    @FormUrlEncoded
    @POST("presensiBookingKelas")
    fun presensiBookingKelas(
        @HeaderMap headers: Map<String, String>,
        @Field("id_member") id_member: String,
        @Field("id_jadwal_harian") id_jadwal_harian: Int,
        @Field("jenis_pembayaran") jenis_pembayaran: String,
    ):Call<ResponsePresensiBookingKelas>

    @FormUrlEncoded
    @POST("perizinanInstruktur")
    fun perizinanInstruktur(
        @HeaderMap headers: Map<String, String>,
        @Field("id_instruktur") id_member: String,
        @Field("id_jadwal_harian") id_jadwal_harian: Int,
        @Field("tanggal_perizinan") tanggal_perizinan: String,
        @Field("keterangan_perizinan") keterangan_perizinan: String,
        ):Call<ResponsePerizinanInstruktur>

    @GET("presensiBookingGym/{id}")
    fun getPresensiBookingGymByIdMember(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id_member: String,
    ):Call<ResponseGetPresensiBookingGymByIdMember>

    @GET("presensiBookingKelas/{id}")
    fun getPresensiBookingKelasByIdMember(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id_member: String,
    ):Call<ResponseGetPresensiBookingKelasByIdMember>

    @DELETE("presensiBookingKelas/{id}")
    fun destroyPresensiBookingKelas(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id_booking_kelas: String,
    ):Call<ResponseDestroyPresensiBookingKelas>

    @GET("getDepositKelasMember/{id}")
    fun getDataDepositKelasMemberByIdMember(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id_member: String,
    ):Call<ResponseDepositKelasMemberByIdMember>

    @GET("perizinanInstruktur/{id}")
    fun getPerizinanInstrukturByIdInstruktur(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id_instruktur: String,
    ):Call<ResponseGetPerizinanInstrukturByIdInstruktur>
}