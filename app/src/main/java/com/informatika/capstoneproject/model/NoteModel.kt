package com.informatika.capstoneproject.model

import  java.io.Serializable

class NoteModel (
    val tb_laporan  :   List<Data>
)   {
    data class Data (
        val id_laporan:    String?,
        val nama_pelapor:   String?,
        val telp_pelapor:   String?,
        val tgl_lapor:  String?,
        val lokasi_laporan: String?,
        val isi_laporan:    String?,
        val img_laporan:    String?
        ) : Serializable
}