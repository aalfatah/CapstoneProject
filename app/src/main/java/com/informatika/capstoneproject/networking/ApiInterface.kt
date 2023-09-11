package com.informatika.capstoneproject.networking

import com.informatika.capstoneproject.model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @GET("data.php")
    fun data()  :   Call<NoteModel>

    @FormUrlEncoded
    @POST("create.php")
    fun create(
        @Field("nama_pelapor")      nama_pelapor:   String,
        @Field("telp_pelapor")      telp_pelapor:   String,
        @Field("tgl_lapor")         tgl_lapor:   String,
        @Field("lokasi_laporan")    lokasi_laporan:   String,
        @Field("isi_laporan")       isi_laporan:   String,
    )  :   Call<SubmitModel>

    @FormUrlEncoded
    @POST("update.php")
    fun update(
        @Field("id_laporan")    id_laporan: String,
        @Field("nama_pelapor")      nama_pelapor:   String,
        @Field("telp_pelapor")      telp_pelapor:   String,
        @Field("tgl_lapor")         tgl_lapor:   String,
        @Field("lokasi_laporan")    lokasi_laporan:   String,
        @Field("isi_laporan")       isi_laporan:   String,
    )  :   Call<SubmitModel>

    @FormUrlEncoded
    @POST("delete.php")
    fun delete (
        @Field("id_laporan") id_laporan: String
    ) : Call<SubmitModel>

    //Profil User
    @POST("CapstoneProject/ProfilUser/loginUser.php")
    fun loginUser(
        @Field("post_username") username    :   String,
        @Field("post_password") pass_user    :   String
    ):  Call<ResponseLogin>

    @GET("top-headlines")
    fun getTechnology(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String?
    ): Call<ModelNews>

//    @FormUrlEncoded
//    @POST("editProfil.php")
//    fun editProfil(
//        @Field("id_profil")    id_profil: String,
//        @Field("nama_profil")      nama_profil:   String,
//        @Field("telp_profil")      telp_profil:   String,
//        @Field("profil")         nik_profil:   String,
//        @Field("pass_user")    pass_user:   String,
//    )  :   Call<SubmitModel>

//        @GET("top-headlines")
//    fun getHeadlines(
//        @Query("country") country: String?,
//        @Query("apiKey") apiKey: String?
//    ): Call<ModelNews>
//
//    @GET("top-headlines")
//    fun getSports(
//        @Query("country") country: String?,
//        @Query("category") category: String?,
//        @Query("apiKey") apiKey: String?
////    ): Call<ModelNews>
//
//    @GET("top-headlines")
//    fun getBusiness(
//        @Query("country") country: String?,
//        @Query("category") category: String?,
//        @Query("apiKey") apiKey: String?
//    ): Call<ModelNews>
//
//    @GET("top-headlines")
//    fun getHealth(
//        @Query("country") country: String?,
//        @Query("category") category: String?,
//        @Query("apiKey") apiKey: String?
//    ): Call<ModelNews>
//
//    @GET("top-headlines")
//    fun getEntertainment(
//        @Query("country") country: String?,
//        @Query("category") category: String?,
//        @Query("apiKey") apiKey: String?
//    ): Call<ModelNews>
//
//    @GET("everything")
//    fun getNewsSearch(
//        @Query("q") keyword: String?,
//        @Query("language") language: String?,
//        @Query("apiKey") apiKey: String?
//    ): Call<ModelNews>

}