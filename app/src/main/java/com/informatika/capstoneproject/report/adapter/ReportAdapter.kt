package com.informatika.capstoneproject.report.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.informatika.capstoneproject.R
import com.informatika.capstoneproject.model.NoteModel

class ReportAdapter(
    val tb_laporan: ArrayList<NoteModel.Data>,
    val listener: OnAdapterListener

):  RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_history_report,  parent, false)
    )

    override fun onBindViewHolder(holder: ReportAdapter.ViewHolder, position: Int) {
        val dataHistoryLaporan    =   tb_laporan[position]
        holder.textNama.text    =   dataHistoryLaporan.nama_pelapor
        holder.textTelp.text    =   dataHistoryLaporan.telp_pelapor
        holder.textLokasi.text  =   dataHistoryLaporan.lokasi_laporan
        holder.textIsi.text     =   dataHistoryLaporan.isi_laporan
        holder.textTgl.text     =   dataHistoryLaporan.tgl_lapor

        //Ubah
        holder.itemView.setOnClickListener {
            listener.onUpdate(dataHistoryLaporan)
        }

        //Delete Riwayat
        holder.imgDelete.setOnClickListener {
            listener.onDelete(dataHistoryLaporan)
        }
    }

    override fun getItemCount() =   tb_laporan.size

    class ViewHolder(view: View):   RecyclerView.ViewHolder(view)   {
        val textNama    =   view.findViewById<TextView>(R.id.tvNamaHistory)
        val textTelp    =   view.findViewById<TextView>(R.id.tvTelpHistory)
        val textLokasi    =   view.findViewById<TextView>(R.id.tvLokasiHistory)
        val textIsi    =   view.findViewById<TextView>(R.id.tvisiLaporanHistory)
        val textTgl    =   view.findViewById<TextView>(R.id.tvTglHistory)
        val imgDelete   =   view.findViewById<ImageView>(R.id.deleteHistory)
    }

    public fun setData(data:    List<NoteModel.Data>)   {
        tb_laporan.clear()
        tb_laporan.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {

        fun onUpdate(riwayatLapor    :   NoteModel.Data)

        fun onDelete(Hapusriwayat   :   NoteModel.Data)
    }

}